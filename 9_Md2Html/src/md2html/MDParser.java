package md2html;

import java.util.*;
import java.util.regex.*;

public class MDParser {
    private static class Pair<A, B> {
        public final A first;
        public final B second;
        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }

//    private static final String RED = "\u001B[31;1m";
//    private static final String GREEN = "\u001B[32m";
//    private static final String YELLOW = "\u001B[33m";
//    private static final String BLUE = "\u001B[34m";
//    private static final String PURPLE = "\u001B[35m";
//    private static final String CYAN = "\u001B[36m";
//    private static final String WHITE = "\u001B[37m";
//    private static final String END = "\u001B[0m";

    private final String markdown;
    private final String html;
    private final static String whatever = "([\\S\\s]+?)"; // "((.|\\s)+?)"; // `.` cannot be `\n`
    private final static String shield = "(?<!\\\\)";

    public MDParser(String md) {
        this.markdown = md;
        this.html = parse(md);
    }

    public String toMarkdown() {
        return markdown;
    }

    public String toHTML() {
        return html;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static <T> ArrayList<T> zip(List<T> a, List<T> b) {
        ArrayList<T> res = new ArrayList<>();
        for (int i = 0; i < Math.max(a.size(), b.size()); i++) {
            if (i < a.size()) res.add(a.get(i));
            if (i < b.size()) res.add(b.get(i));
        }
        return res;
    }
    private static <T> ArrayList<T> zip(Pair<List<T>, List<T>> p) {
        return zip(p.first, p.second);
    }

    private static Pair<List<String>, List<String>> split(String text, String regex) {
         List<String> noMatch = Arrays.asList(text.split(regex));
         List<String> allMatch = new ArrayList<String>();
         Matcher m = Pattern.compile(regex).matcher(text);
         while (m.find()) {
             allMatch.add(m.group());
         }
         return new Pair<>(noMatch, allMatch);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
     *    \*((.|\s)+)\*  _((.|\s)+)_ \*\*((.|\s)+)\*\* __((.|\s)+)__
     *    \-\-((.|\s)+)\-\-
     *    `((.|\s)+)`
     *    \[ (process) \]\( (href) \)
     */
    private final static Map<String, HtmlTagTable> tagByMD = Map.of(
        "**", HtmlTagTable.STRONG,
        "__", HtmlTagTable.STRONG_,
        "*", HtmlTagTable.EMPHASIS,
        "_", HtmlTagTable.EMPHASIS_,
        "--", HtmlTagTable.STRIKEOUT,
        "`", HtmlTagTable.CODE
    );


    private List<DocumentNode> process(String src, String regex) {
        if (regex.isEmpty()) return List.of(new Text(src));

        var all = split(src, regex);
        var noMatches = all.first;
        var matches = all.second;

//        System.out.println(RED + ": " + regex + END + " : " + noMatches + " : " + matches);

        List<DocumentNode> matchModified = new ArrayList<>();
        for (var particularMatch : matches) {
            Pattern paragraph = Pattern.compile("^" + regex + "$");
            Matcher matcher = paragraph.matcher(particularMatch);
            if (!matcher.matches()) throw new RuntimeException("An error occurred while regex were processing.");

            matchModified.add(new DocumentNode(
                    List.of(new Text(matcher.group(2))),
                    tagByMD.get(matcher.group(1)),
                    new HashMap<>()
            ));
        }

        List<DocumentNode> noMatchModified = new ArrayList<>();
        for (var particularNoMatch : noMatches) {
             noMatchModified.add(new DocumentNode(List.of(new Text(particularNoMatch))));
        }

        return process(new DocumentNode(zip(noMatchModified, matchModified)).toHTML(), nextRegex(regex));
    }

    private String nextRegex(String re) {
        String [] regex = {
                "(\\*\\*)" + whatever + shield + "\\1",
                "(__)" + whatever + shield + "\\1",
                "(\\*)" + whatever + shield + "\\1",
                "(_)" + whatever + shield + "\\1",
                "(\\-\\-)" + whatever + shield + "\\1",
                "(`)" + whatever + shield + "\\1"
        };
        for (int i = 0; i < regex.length - 1; i++) {
            if (regex[i].equals(re)) return regex[i + 1];
        }
        return "";
    }





    private List<DocumentNode> parseParticularParagraph(String src) {
        String linkRe = "\\[" + whatever + "\\]\\(" + whatever + "\\)";
        List<String> refs = new ArrayList<>();

        var all = split(src, linkRe);
        var noMatches = all.first;
        var matches = all.second;

        List<DocumentNode> matchModified = new ArrayList<>();
        for (var particularMatch : matches) {
            Pattern paragraph = Pattern.compile("^" + linkRe + "$");
            Matcher matcher = paragraph.matcher(particularMatch);
            if (!matcher.matches()) throw new RuntimeException("An error occurred while regex were processing.");
            refs.add(matcher.group(2));

            matchModified.add(new DocumentNode(
                    List.of(new Text(matcher.group(1))),
                    HtmlTagTable.LINK,
                    new HashMap<>()
            ));
        }

        List<DocumentNode> noMatchModified = new ArrayList<>();
        for (var particularNoMatch : noMatches) {
            noMatchModified.add(new Text(particularNoMatch));
        }


        src = new DocumentNode(zip(noMatchModified, matchModified)).toHTML();

        String startRe = "(\\*\\*)" + whatever + shield + "\\1";
        var ans = new DocumentNode(process(src, startRe)).toHTML(); // process in always 1 element

        StringBuilder result = new StringBuilder();
        String[] tokens = ans.split("<a>");
        for (int i = 0; i < tokens.length - 1; i++) {
            result.append(tokens[i]).append("<a href='").append(refs.get(i)).append("'>");
        }
        result.append(tokens[tokens.length - 1]);

        return List.of(new Text(result.toString()));
    }


    private Paragraph parseHeader(String src) {
        Pattern header = Pattern.compile("^(#+)\\s+([\\S\\s]+)$");
        Matcher matcher = header.matcher(src);
        if (matcher.matches()) {
            return new Paragraph(
                    (int)matcher.group(1).chars().filter(ch -> ch == '#').count(),
                    parseParticularParagraph(matcher.group(2))
            );
        }
        return new Paragraph(0, parseParticularParagraph(src));
    }

    private String parse(String md) {
        md = md.replace(System.lineSeparator(), "\n");
        md = md.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");

        var paragraphs = new ArrayList<Paragraph>();
        var parted = md.split("[" + System.lineSeparator() + "]{2,}"); // System.lineSeparator() + System.lineSeparator()
        for (String paragraphSource : Arrays.stream(parted).filter(s -> !s.trim().isEmpty()).toArray(String[]::new)) {
            paragraphs.add(parseHeader(paragraphSource.replaceFirst("^[\\n]++", "").replaceFirst("\\s++$", ""))); // remove \n from end
        }

        return new HTMLDocument(paragraphs).toHTML().replace("\\*", "*").replace("\\_", "_");
    }
}

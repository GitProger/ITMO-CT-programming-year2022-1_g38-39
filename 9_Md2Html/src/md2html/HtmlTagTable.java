package md2html;
import java.util.Map;

public enum HtmlTagTable {
    EMPHASIS("*", "*", "em"),
    EMPHASIS_("_", "_", "em"),
    STRONG("**", "**", "strong"),
    STRONG_("__", "__", "strong"),
    STRIKEOUT("--", "--", "s"),
    CODE("`", "`", "code"),
    P("#", "", "p"),
    LINK("[", "]", "a");


    public final String mdOpen;
    public final String mdClose;
    public final String htmlTag;

    HtmlTagTable(String mdOpen, String mdClose, String htmlTag) {
        this.mdOpen = mdOpen;
        this.mdClose = mdClose;
        this.htmlTag = htmlTag;
    }

    @Override
    public String toString() {
        return htmlTag;
    }

    public String opening(Map<String, String> attr) {
        StringBuilder sb = new StringBuilder("<").append(htmlTag);
        for (var entry : attr.entrySet()) {
            sb.append(" ").append(entry.getKey()).append("='").append(entry.getValue()).append("'");
        }
        return sb.append(">").toString();
    }

    public String closing() { return "</" + htmlTag + ">"; }

}


package md2html;

import java.util.List;

public class Paragraph extends AbstractHTMLAble {
    private final int lvl;
    private final String tag;

    public Paragraph(int lvl, List<? extends DocumentNode> ch) {
        super(ch);
        this.lvl = lvl;
        tag = lvl == 0 ? "p" : "h" + Math.min(lvl, 6);
    }

    protected void toHTML(StringBuilder sb) {
        if (lvl != -1) sb.append("<").append(tag).append(">");
        subToHTML(sb);
        if (lvl != -1) sb.append("</").append(tag).append(">");
        sb.append(System.lineSeparator());
    }
}

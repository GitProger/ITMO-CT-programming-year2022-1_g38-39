package md2html;
import java.util.ArrayList;
import java.util.List;

public class HTMLDocument extends AbstractHTMLAble {
    public HTMLDocument(List<? extends Paragraph> children) { // List<Paragraph>
        super(children);
    }
    public HTMLDocument() {
        super(new ArrayList<>());
    }
    protected void toHTML(StringBuilder sb) {
        subToHTML(sb);
    }
}
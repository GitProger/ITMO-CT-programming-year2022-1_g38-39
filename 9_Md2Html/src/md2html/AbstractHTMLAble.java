package md2html;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHTMLAble implements TransHTMLAble {
    protected List<AbstractHTMLAble> children;

    public AbstractHTMLAble(List<? extends AbstractHTMLAble> children) {
        this.children = List.copyOf(children);
    }

    protected void subToHTML(StringBuilder sb) {
        for (var e : children) {
            e.toHTML(sb);
        }
    }

    protected abstract void toHTML(StringBuilder sb);
    @Override
    public String toHTML() {
        var sb = new StringBuilder();
        toHTML(sb);
        return sb.toString();
    }
}

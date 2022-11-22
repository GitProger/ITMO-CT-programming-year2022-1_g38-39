package md2html;

import java.util.*;

public class Text extends DocumentNode {
    public final String text;
    public Text(String text) { super(null, new HashMap<>()); this.text = text; }

    protected void toHTML(StringBuilder sb) {
        sb.append(text);
    }
}

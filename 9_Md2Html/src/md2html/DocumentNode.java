package md2html;

import java.util.*;

public class DocumentNode extends AbstractHTMLAble {
     private final HtmlTagTable htw;
     private final Map<String, String> attr;

    public DocumentNode(List<? extends DocumentNode> children) {
        this(children, null, new HashMap<>());
    }

    public DocumentNode(List<? extends DocumentNode> children, HtmlTagTable htw, Map<String, String> attr) {
         super(children);
         this.htw = htw;
         this.attr = attr;
     }

     public DocumentNode(HtmlTagTable htw, Map<String, String> attr) {
         this(new ArrayList<>(), htw, attr);
     }

     @Override
     protected void toHTML(StringBuilder sb) {
         if (htw != null) sb.append(htw.opening(attr));
         subToHTML(sb);
         if (htw != null) sb.append(htw.closing());
     }
}

package markup;

import java.util.ArrayList;
import java.util.List;

public final class ListItem {
    private final static TexWrapper tw = new TexWrapper(TexTags.ITEM.toString(), "", false);
    private final List<Itemizable> contents;

    public void toTex(StringBuilder sb) {
        sb.append(tw.open);
        for (var piece : contents) {
            piece.toTex(sb);
        }
        sb.append(tw.close);
    }
    public ListItem(List<Itemizable> list) { this.contents = new ArrayList<>(list); }
}

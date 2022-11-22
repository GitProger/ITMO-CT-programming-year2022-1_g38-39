package markup;

import java.util.ArrayList;
import java.util.List;

class BaseList implements Itemizable {
    private final List<ListItem> items;
    private final TexWrapper tw;
    @Override
    public void toTex(StringBuilder sb) {
        sb.append(tw.open);
        for (var piece : items) {
            piece.toTex(sb);
        }
        sb.append(tw.close);
    }
    @Override
    public void toMarkdown(StringBuilder sb) {
    }
    public BaseList(List<ListItem> list, TexWrapper tw) {
        this.items = new ArrayList<>(list);
        this.tw = tw;
    }
}

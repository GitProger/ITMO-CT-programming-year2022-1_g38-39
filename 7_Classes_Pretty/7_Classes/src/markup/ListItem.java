package markup;

import java.util.List;

public class ListItem extends MultipleText  {
    private final static TexWrapper tw = new TexWrapper(TexTags.ITEM.toString(), "", false);
    ListItem(String s) { super(List.of(new Text(s)), "", tw);}
    ListItem(TextElement e) { super(List.of(e), "", tw); }
    ListItem(List<TextElement> list) { super(list, "", tw); }
}

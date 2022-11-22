package markup;

import java.util.List;

public class UnorderedList extends MultipleText {
    private final static TexWrapper tw = new TexWrapper(
            TexTags.BEGIN.fold(TexTags._ITEMIZE),
            TexTags.END.fold(TexTags._ITEMIZE)
    );
    UnorderedList(String s) { super(List.of(new Text(s)), "", tw);}
    UnorderedList(TextElement e) { super(List.of(e), "", tw); }
    UnorderedList(List<TextElement> list) { super(list, "", tw); }

//    private static List<TextElement> toTextElementList(List<ListItem> list) { return new ArrayList<>(list); }
//    public UnorderedList(List<ListItem> list) { super(toTextElementList(list), "", tw); }
//    public UnorderedList(ListItem item) { this(List.of(item)); }
}

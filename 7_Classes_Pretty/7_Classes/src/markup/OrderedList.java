package markup;

import java.util.List;

public class OrderedList extends MultipleText {
    private final static TexWrapper tw = new TexWrapper(
            TexTags.BEGIN.fold(TexTags._ENUMERATE),
            TexTags.END.fold(TexTags._ENUMERATE)
    );
    OrderedList(String s) { super(List.of(new Text(s)), "", tw);}
    OrderedList(TextElement e) { super(List.of(e), "", tw); }
    OrderedList(List<TextElement> list) { super(list, "", tw); }
}

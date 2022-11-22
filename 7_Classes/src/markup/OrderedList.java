package markup;

import java.util.ArrayList;
import java.util.List;

public final class OrderedList extends BaseList {
    private final static TexWrapper tw = new TexWrapper(
            TexTags.BEGIN.fold(TexTags._ENUMERATE),
            TexTags.END.fold(TexTags._ENUMERATE)
    );
    public OrderedList(List<ListItem> list) { super(list, tw); }
    public OrderedList(ListItem li) { super(List.of(li), tw); }
}

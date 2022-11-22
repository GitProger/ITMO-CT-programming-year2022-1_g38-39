package markup;

import java.util.ArrayList;
import java.util.List;

public final class UnorderedList extends BaseList {
    private final static TexWrapper tw = new TexWrapper(
            TexTags.BEGIN.fold(TexTags._ITEMIZE),
            TexTags.END.fold(TexTags._ITEMIZE)
    );
    public UnorderedList(List<ListItem> list) { super(list, tw); }
    public UnorderedList(ListItem li) { super(List.of(li), tw); }
}

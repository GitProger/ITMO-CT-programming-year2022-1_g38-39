package markup;

import java.util.List;

public final class Strikeout extends MultipleText {
    private final static TexWrapper tw = new TexWrapper(TexTags.STRIKEOUT.toString(), "", true);
    Strikeout(String s) { super(List.of(new Text(s)), MDWrappers.STRIKEOUT.toString(), tw);}
    Strikeout(TextElement e) { super(List.of(e), MDWrappers.STRIKEOUT.toString(), tw); }
    Strikeout(List<TextElement> list) { super(list, MDWrappers.STRIKEOUT.toString(), tw); }
}

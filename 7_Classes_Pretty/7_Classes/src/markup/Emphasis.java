package markup;
import java.util.List;

public final class Emphasis extends MultipleText {
    private final static TexWrapper tw = new TexWrapper(TexTags.EMPHASIS.toString(), "", true);
    Emphasis(String s) { super(List.of(new Text(s)), MDWrappers.EMPHASIS.toString(), tw);}
    Emphasis(TextElement e) { super(List.of(e), MDWrappers.EMPHASIS.toString(), tw); }
    Emphasis(List<TextElement> list) { super(list, MDWrappers.EMPHASIS.toString(), tw); }
}

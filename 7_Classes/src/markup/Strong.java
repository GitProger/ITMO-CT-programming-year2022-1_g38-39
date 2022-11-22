package markup;
import java.util.List;

public final class Strong extends MultipleText {
    private final static TexWrapper tw = new TexWrapper(TexTags.STRONG.toString(), "", true);
    public Strong(String s) { super(List.of(new Text(s)), MDWrappers.STRONG.toString(), tw);}
    public Strong(TextElement e) { super(List.of(e), MDWrappers.STRONG.toString(), tw); }
    public Strong(List<TextElement> list) { super(list, MDWrappers.STRONG.toString(), tw); }
}

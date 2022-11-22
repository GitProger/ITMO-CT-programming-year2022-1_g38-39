package markup;
import java.util.List;

public final class Strong extends MultipleText {
    private final static TexWrapper tw = new TexWrapper(TexTags.STRONG.toString(), "", true);
    Strong(String s) { super(List.of(new Text(s)), MDWrappers.STRONG.toString(), tw);}
    Strong(TextElement e) { super(List.of(e), MDWrappers.STRONG.toString(), tw); }
    Strong(List<TextElement> list) { super(list, MDWrappers.STRONG.toString(), tw); }
}

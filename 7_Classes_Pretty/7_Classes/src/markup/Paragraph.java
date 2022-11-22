package markup;
import java.util.List;

public final class Paragraph extends MultipleText {
    Paragraph(List<TextElement> list) { super(list, "", new TexWrapper("", "", false)); }
}

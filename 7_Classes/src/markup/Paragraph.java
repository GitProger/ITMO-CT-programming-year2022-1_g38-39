package markup;
import java.util.ArrayList;
import java.util.List;

public final class Paragraph implements Itemizable {
    private final List<TextElement> contents;
    @Override
    public void toMarkdown(StringBuilder sb) {
        for (var piece : contents) {
            piece.toMarkdown(sb);
        }
    }
    @Override
    public void toTex(StringBuilder sb) {
        for (var piece : contents) {
            piece.toTex(sb);
        }
    }

    public Paragraph(List<TextElement> list) { this.contents = new ArrayList<>(list); }
}

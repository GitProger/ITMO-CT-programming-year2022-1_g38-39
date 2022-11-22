package markup;
import java.util.List;
import java.util.ArrayList;

public class MultipleText implements TextElement {
    private final List<TextElement> contents = new ArrayList<>();
    private final String mdWrapper;
    private TexWrapper texW;

    public MultipleText(List<TextElement> list, String mdWrapper) {
        contents.addAll(list);
        this.mdWrapper = mdWrapper;
    }
    public MultipleText(List<TextElement> list, String mdWrapper, TexWrapper texW) {
        this(list, mdWrapper);
        this.texW = texW;
    }
    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(mdWrapper);
        for (var piece : contents) {
            piece.toMarkdown(sb);
        }
        sb.append(mdWrapper);
    }
    @Override
    public void toTex(StringBuilder sb) {
        sb.append(texW.open);
        for (var piece : contents) {
            piece.toTex(sb);
        }
        sb.append(texW.close);
    }
}

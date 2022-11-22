package markup;

public final class Text implements TextElement {
    private final String text;
    Text(String s) { text = s; }
    @Override
    public void toMarkdown(StringBuilder sb) { sb.append(text); }
    @Override
    public void toTex(StringBuilder sb) { sb.append(text); }
}

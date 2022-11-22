package markup;

public interface TextElement {
    void toMarkdown(StringBuilder sb);
    void toTex(StringBuilder sb);
}
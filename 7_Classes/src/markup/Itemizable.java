package markup;

public interface Itemizable {
    void toMarkdown(StringBuilder sb);
    void toTex(StringBuilder sb);
}

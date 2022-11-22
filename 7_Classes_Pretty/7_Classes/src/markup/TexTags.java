package markup;

public enum TexTags {
    BEGIN("\\begin"),
    END("\\end"),
    ITEM("\\item "),
    EMPHASIS("\\emph"),
    STRONG("\\textbf"),
    STRIKEOUT("\\textst"),

    _ENUMERATE("enumerate"), // _ prefix means no '\'
    _ITEMIZE("itemize");

    private final String text;
    TexTags(final String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return text;
    }
    public String fold(TexTags in) {
        return text + "{" + in + "}";
    }
}

package markup;

public enum MDWrappers {
    EMPHASIS("*"),
    STRONG("__"),
    STRIKEOUT("~");

    private final String text;
    MDWrappers(final String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return text;
    }
}

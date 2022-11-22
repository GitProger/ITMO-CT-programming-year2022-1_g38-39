package markup;

public class TexWrapper {
    public final String open;
    public final String close;
    TexWrapper(String open, String close, boolean brace) {
        if (brace) {
            this.open = open + "{";
            this.close = "}";
        } else {
            this.open = open;
            this.close = close;
        }
    }
    TexWrapper(String open, String close) { this(open, close, false); }
}

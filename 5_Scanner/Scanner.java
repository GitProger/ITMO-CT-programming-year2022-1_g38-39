import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Scanner {
    private final int BLOCK_SIZE = 4096; // debug 4
    private final char[] buffer = new char[BLOCK_SIZE];
    private final Reader stream;

    private String cache = null;

    private long cacheLong = 0L;
    private int cacheInt = 0;
    private boolean noIntCache = true;
    private boolean noLongCache = true;

    private int bufferLen = -1;
    private int pointer = -1;
////////////////////////////////////////////////////////////////////////////////

    public Scanner(Reader r) {
        stream = r;
    }

    public Scanner(InputStream is) {
        this(new InputStreamReader(is));
    }

    public Scanner(String input) {
        this(new StringReader(input));
    }

    private boolean isDelimiter(char c) {
        return Character.isWhitespace(c);
    }

    private boolean isDelimiter(char c, boolean lineFlag) {
        if (lineFlag) {
            return c == '\n';
//            return System.lineSeparator().indexOf(c) != -1;
//            return new String("\r\n").indexOf(c) != -1;
        } else {
            return isDelimiter(c);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////
    private String extractCache() {
        String local = cache;
        cache = null;
        cacheInt = 0;
        cacheLong = 0;
        noIntCache = true;
        noLongCache = true;
        return local;
    }

    private int updateBuffer() throws IOException {
        if (pointer >= bufferLen) {
            pointer = 0;
            return (bufferLen = stream.read(buffer));
        } else {
            return bufferLen;
        }
    }

    public boolean staffHasNext(boolean lineFlag) throws IOException {
        StringBuilder token = new StringBuilder(BLOCK_SIZE);

        while (updateBuffer() != -1) {
            if (!lineFlag) {
                while (token.length() == 0 && pointer < bufferLen && isDelimiter(buffer[pointer], lineFlag)) {
                    pointer++;
                }
            }
            int tokenEnd = pointer;
            boolean endFlag = false;

            while (tokenEnd < bufferLen) {
                if (buffer[tokenEnd] == '\r') tokenEnd++; // 
                if (isDelimiter(buffer[tokenEnd], lineFlag)) { // :NOTE: one character separator
                    endFlag = true;
                    break;
                }
                tokenEnd++;
            }

            boolean excess = lineFlag && (tokenEnd > pointer) && buffer[tokenEnd - 1] == '\r';
            token.append(buffer, pointer, tokenEnd - (excess ? 1 : 0) - pointer); // лишний \r
            pointer = tokenEnd;
            if (lineFlag) {
                pointer++;
            }
            cache = token.toString();

            if (endFlag) {
                return true;
            }
        }

        return token.length() != 0;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public boolean hasNext() {
        try {
            return staffHasNext(false);
        } catch (IOException e) {
            return false;
        }
    }

    public String next() {
        if (cache == null && !hasNext()) { //  noStringCache
            throw new NoSuchElementException();
        }
        return extractCache();
    }

    public boolean hasNextLine() {
        try {
            return staffHasNext(true);
        } catch (IOException e) {
            return false;
        }
    }

    public String nextLine() {
        if (cache == null && !hasNextLine()) {
            throw new NoSuchElementException();
        }
        return extractCache();
    }


    public boolean hasNextLong() {
        try {
            cacheLong = parseLong(next());
            noLongCache = false;
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public long nextLong() {
        if (noLongCache && !hasNextLong()) {
            throw new NoSuchElementException();
        }
        long t = cacheLong;
        extractCache();
        return t;
    }

    public boolean hasNextInt() {
        try {
            cacheInt = parseInt(next());
            noIntCache = false;
            return true;
        } catch (NumberFormatException e) {
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public int nextInt() {
        if (noIntCache && !hasNextInt()) {
            throw new NoSuchElementException();
        }
        int t = cacheInt;
        extractCache();
        return t;
    }

    private static int getRadix(String num) {
        return ((Character.toLowerCase(num.charAt(num.length() - 1)) == 'o') ? 8 : 10);
    }

    private static String goodString(String num, int radix) {
        char[] s = new char[num.length() - (radix == 8 ? 1 : 0)];

        for (int i = 0; i < s.length; i++) {
            s[i] = num.charAt(i);
            if (!Character.isDigit(s[i]) && s[i] != '-') {
                s[i] = (char) (s[i] - 'a' + '0');
            }
        }
        return new String(s);
    }

    private static long parseLong(String num) {
        int radix = getRadix(num);
        return Long.parseLong(goodString(num, radix), radix);
    }

    private static int parseInt(String num) {
        int radix = getRadix(num);
        if (radix == 8) {
            return Integer.parseUnsignedInt(goodString(num, radix), radix); //!
        } else {
            return Integer.parseInt(goodString(num, radix), radix);
        }
    }
}
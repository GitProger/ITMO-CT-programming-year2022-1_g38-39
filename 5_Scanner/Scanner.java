import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Map;
import java.util.HashMap;

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

    private boolean isDelimeter(char c) {
        return Character.isWhitespace(c);
    }
    private boolean isDelimeter(char c, boolean lineFlag) {
        if (lineFlag) {
            return System.lineSeparator().indexOf(c) != -1;
        } else {
            return isDelimeter(c);
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
                while (token.length() == 0 && pointer < bufferLen && isDelimeter(buffer[pointer], lineFlag)) {
                    pointer++;
                }
            }
            int tokenEnd = pointer;
            boolean endFlag = false;

            while (tokenEnd < bufferLen) {
                if (isDelimeter(buffer[tokenEnd], lineFlag)) {
                    endFlag = true;
                    break;
                }
                tokenEnd++;
            }

            token.append(buffer, pointer, tokenEnd - pointer);
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
        } catch (RuntimeException e) {
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

    private static long parseLong(String num) {
        int radix = ((Character.toLowerCase(num.charAt(num.length() - 1)) == 'o') ? 8 : 10);
        if (radix == 8) {
            num = num.substring(0, num.length() - 1);
        }
        char[] s = new char[num.length()];

        for (int i = 0; i < s.length; i++) {
            s[i] = num.charAt(i);
            if (!Character.isDigit(s[i]) && s[i] != '-') {
                s[i] = (char)(s[i] - 'a' + '0');
            }
        }
        return Long.parseLong(new String(s), radix);
    }

    private static int parseInt(String num) {
        return (int)parseLong(num);
    }
}

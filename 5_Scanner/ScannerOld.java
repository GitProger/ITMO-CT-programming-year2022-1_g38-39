import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.IOException;
import java.util.NoSuchElementException;

//import java.util.concurrent.TimeUnit;

public class ScannerOld {
    private final int BLOCK_SIZE = 4096; // debug 4
    private final char[] buffer = new char[BLOCK_SIZE];
    private final Reader stream;

    private Object cache;
    private int bufferLen = -1;
    private int pointer = -1;
////////////////////////////////////////////////////////////////////////////////

    public ScannerOld(Reader r) {
        stream = r;
    }
    public ScannerOld(InputStream is) {
        this(new InputStreamReader(is));
    }
    public ScannerOld(String input) {
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
    private Object extractCache() {
        Object local = cache;
        cache = null;
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
        String token = "";

        while (updateBuffer() != -1) {
            if (!lineFlag) { // no line-by-line read
                while (token.isEmpty() && pointer < bufferLen && isDelimeter(buffer[pointer], lineFlag)) {
                    pointer++;
                    // token isEmpty, because, if block size is 4, and we got '1234' we will skip '\n' in the next block
                }
            }
            int tokenEnd = pointer;
            boolean endFlag = false; // have we built a token?

            while (tokenEnd < bufferLen) {
                if (isDelimeter(buffer[tokenEnd], lineFlag)) { // reached a delimeter
                    endFlag = true;
                    break;
                }
                tokenEnd++;
            }

            /*
          //  if (!token.isEmpty()) {
                System.err.println(token + ": " + pointer + " " + tokenEnd + " " + bufferLen);
                for (int i = 0; i < Math.min(20, bufferLen); i++) {
                    char c = buffer[i];
                    if (c == '\n') System.err.print("\\n"); else
                    if (c == '\t') System.err.print("\\t"); else
                    System.err.print(c);
                }
                System.err.println("\n------------------------");
                try {TimeUnit.SECONDS.sleep(1);}catch(Exception e){};
        //    }
            //*/          

            token += new String(buffer, pointer, tokenEnd - pointer);
            pointer = tokenEnd;
            if (lineFlag) { // line-by-line read
                pointer++;
            }
            cache = token;

            if (endFlag) {
                return true;
            }
        }

        return !token.isEmpty();
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
        if (cache == null && !hasNext()) { // nobody will check one and take another
            throw new NoSuchElementException();
        }
        return (String)extractCache();
    }

    public boolean hasNextLong() {
        try {
            cache = Long.parseLong(next());
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
    public long nextLong() {
        if (cache == null && !hasNextLong()) {
            throw new NoSuchElementException();
        }
        return (Long)extractCache();
    }

    public boolean hasNextInt() {
        try {
            cache = Integer.parseInt(next());
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
    public int nextInt() {
        if (cache == null && !hasNextInt()) {
            throw new NoSuchElementException();
        }
        return (Integer)extractCache();
    }

    public boolean hasNextLine() {
        try {
            return staffHasNext(true);
        } catch (IOException e) {
            return false;
        }
    }
    public String nextLine() { // ""
        if (cache == null && !hasNextLine()) {
            throw new NoSuchElementException();
        }
        return (String)extractCache();
    }
}
package md2html;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static String readFromFile(String name) {
        try (
                FileInputStream fis = new FileInputStream(name);
                InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8)
        ) {
            StringWriter buffer = new StringWriter();
            char[] bufferArray = new char[8 * 1024];
            int chars = reader.read(bufferArray);
            while (chars >= 0) {
                buffer.write(bufferArray, 0, chars);
                chars = reader.read(bufferArray);
            }
            fis.close();
            reader.close();
            return buffer.toString();
        } catch (FileNotFoundException e) {
            System.out.printf("'%s' does not exist.%n", name);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
    public static void printToFile(String fileName, String s) {
        try (Writer out = new BufferedWriter(new FileWriter(fileName, StandardCharsets.UTF_8))) {
            out.write(s);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + "not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Unhandled error");
            e.printStackTrace();
        }
    }

    private static int test = 0;
    private static void log(String[] args) {
        test++;
        printToFile("/tmp/input" + test + ".txt", readFromFile(args[0]));
        printToFile("/tmp/output" + test + ".txt", new MDParser(readFromFile(args[0])).toHTML());
    }

    public static void main(String[] args) {
//        log(args);
//        args = new String[] {"input.md", "output.html"};
        printToFile(args[1], new MDParser(readFromFile(args[0])).toHTML());
    }
}

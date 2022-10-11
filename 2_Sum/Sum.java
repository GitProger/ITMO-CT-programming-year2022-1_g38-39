public class Sum {
    private static int particularSum(final String arg) {
        int result = 0;
        int lastStart = 0, lastLength = 0;
        for (int i = 0; i < arg.length(); i++) {
            boolean ws = Character.isWhitespace(arg.charAt(i));
            if (!ws) {
                lastLength++;
            } else if (lastLength == 0) {
                lastStart++;
            }
            if ((ws || i == arg.length() - 1) && lastLength != 0) {
                result += Integer.parseInt(arg.substring(lastStart, lastStart + lastLength));
                lastLength = 0;
                lastStart = i + 1;
            }
        }
        return result;
    }

    public static void main(String[] argv) {
        int result = 0;
        for (String arg : argv) {
            result += particularSum(arg);
        }
        System.out.println(result);
    }
}
// java Sum.java $(echo -e "1 \t\n\r\f\u000B\u001C\u001D\u001E\u001F 2 \r\t \n    \n\u000B3")
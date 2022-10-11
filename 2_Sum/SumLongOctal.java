public class SumLongOctal {
    private static long myParse(String s) {
        if (Character.toLowerCase(s.charAt(s.length() - 1)) != 'o') {
            return Long.parseLong(s);
        } else {
            return Long.parseLong(s.substring(0, s.length() - 1), 8);
        }
    }

    private static long particularSum(final String arg) {
        long result = 0;
        int lastStart = 0, lastLength = 0;
        for (int i = 0; i < arg.length(); i++) {
            boolean ws = Character.isWhitespace(arg.charAt(i));
            if (!ws) {
                lastLength++;
            } else if (lastLength == 0) {
                lastStart++;
            }
            if ((ws || i == arg.length() - 1) && lastLength != 0) {
                result += myParse(arg.substring(lastStart, lastStart + lastLength));
                lastLength = 0;
                lastStart = i + 1;
            }
        }
        return result;
    }


    public static void main(String[] argv) {
        long result = 0;
        for (String arg : argv) {
            result += particularSum(arg);
        }
        System.out.println(result);
    }
}
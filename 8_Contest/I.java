import java.util.*;
import java.io.*;

public class I {
    /*  ^y             U
     *  |             L+R
     *  |              D
     *  +-----> x
     */
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int xL = Integer.MAX_VALUE, yD = Integer.MAX_VALUE;
        int xR = Integer.MIN_VALUE, yU = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt(); 
            int y = in.nextInt();
            int h = in.nextInt();
            xL = Math.min(xL, x - h);
            xR = Math.max(xR, x + h);
            yD = Math.min(yD, y - h);
            yU = Math.max(yU, y + h);
        }
        int xOpt = (xL + xR) / 2, yOpt = (yD + yU) / 2;
        int hOpt = (Math.max(xR - xL, yU - yD) + 1) / 2;
        System.out.println(xOpt + " " + yOpt + " " + hOpt);
    }
}

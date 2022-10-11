import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import java.net.*;
import java.net.http.*;
import java.io.*;
import java.util.*;
/**
 * Run this code with provided arguments.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
@SuppressWarnings("MagicNumber")
public final class RunMe {
    private RunMe() {
        // Utility class
    }

    public static void main(final String[] args) {
        final byte[] password = parseArgs(args);

        //key0(password);
        //System.out.println("The first key was low-hanging fruit, can you find others?");
        //System.out.println("Try to read, understand and modify code in keyX(...) functions");

       // key1(password);
       // key2(password);
       // key3(password);
       // key4(password);
    //    key5(password);
       // key6(password);
       // key7(password);
       // key8(password);
       // key9(password);
       // key10(password);
       // key11(password);
       // key12(password);
       // key13(password);
        key14(password);
       // key15(password);
       // key16(password);
       // key17(password);
    }
    // 15

    private static void key0(final byte[] password) {
        // The result of print(...) function depends only on explicit arguments
        print(0, 0, password);
    }


    private static void key1(final byte[] password) {
       // while ("1".length() == 1) {
       // }

        print(1, 5487520208732485324L, password);
    }


    private static void key2(final byte[] password) {
      /*  int result = 0;
        for (int i = 0; i < 300_000; i++) {
            for (int j = 0; j < 300_000; j++) {
                for (int k = 0; k < 300_000; k++) {
                    result ^= (i * 7) | (j + k);
                    result ^= result << 1;
                }
            }
        }*/

        print(2, 2475802485702485454L, password);
    }


    private static void key3(final byte[] password) {
        int result = 0;
        for (int i = 0; i < 2022; i++) {
            for (int j = 0; j < 2022; j++) {
                for (int k = 0; k < 2022; k++) {
                    for (int p = 0; p < 12; p++) {
                        result ^= (i * 7) | (j + k * 5) & ~p;
                        result ^= result << 1;
                    }
                }
            }
        }

        print(3, result, password);
    }


    private static void key4(final byte[] password) {
//111001110011000010001000001000001111000000111010101101001001000
//
//11100111001100001000100000100000...............................
//111001110011000010001000001000001111000000111010101101001001000 <-
//111001110011000010001000001000000001011100001010001111001011000 <- i 8329482346525171288
//                                11100111001100001000100000100000
//

   //     for (long i = Long.MIN_VALUE; i < Long.MAX_VALUE; i++) {
  //          if ((i ^ (i >>> 32)) == 8329482348347087432L) {
 //               print(4, i, password);
//            }
//        }
        print(4, 8329482346525171288L, password);
    }


    private static final long PRIME = 1073741783;



    
    private static BigInteger get2(long n, long d) {
        var p = BigInteger.valueOf(PRIME);
        if (d == 1L) {
            return BigInteger.valueOf((n % PRIME) * ((n - 1) % PRIME) / 2);
        }
        long temp_s = 0L;
        for (long i = n - n % d; i < n; i++) {
            temp_s += i / d;
            temp_s %= PRIME;
        }
        var x = get2(n / d, 1L).multiply(BigInteger.valueOf(d));
        System.out.println("2: " + get2(n / d, 1L) + " " + d + " " + BigInteger.valueOf(d).multiply(get2(n / d, 1L)));
        return x.add(BigInteger.valueOf(temp_s + PRIME)).mod(p);
    }

    private static long get(long n, long d) {
        if (d == 1L) {
            return (n % PRIME) * ((n - 1) % PRIME) / 2;
        }
        long temp_s = 0L;
        for (long i = n - n % d; i < n; i++) {
            temp_s += i / d;
            temp_s %= PRIME;
        }
        System.out.println("1: " + get(n / d, 1L) + " " + d + " " + (get(n / d, 1L) % PRIME) * d);
        return ((get(n / d, 1L) * d + PRIME) % PRIME + temp_s + PRIME) % PRIME;
    }

    private static void key5(final byte[] password) {
        final long n = 1_000_000_000_000_000L + ByteBuffer.wrap(password).getInt(); // 268441891
/*
>>> def get(n, d):
...     s = 0
...     for i in range(n):
...         s += i // d
...     return s
>>> get(33, 1) * 3 + 100 // 3, get(100, 3)
(1617, 1617)
>>> get(14, 1) * 7 + 98 // 7 + 99 // 7,   get(100, 7)
(665, 665)
*/

//        long result = 0;
//        for (long i = 0; i < n; i++) {
//            result = (result + i / 2 + i / 3 + i / 4 + i / 2022) % PRIME;
//        }
       // while (true) {
       //     var in = new Scanner(System.in);
       //     var a = in.nextLong();
       //     System.out.println((get(a, 2).add(get(a, 3)).add(get(a, 4)).add(get(a, 2022)).mod(BigInteger.valueOf(PRIME))).longValue());
       // }


        BigInteger res = get2(n, 2).add(get2(n, 3)).add(get2(n, 4)).add(get2(n, 2022));

        System.out.println((get(n, 2) + get(n, 3) + get(n, 4) + get(n, 2022)) % PRIME);
        System.out.println(res.mod(BigInteger.valueOf(PRIME)));

        long result = get(n, 2) + get(n, 3) + get(n, 4) + get(n, 2022);
        print(5, result % PRIME, password);
    }


    private static void key6(final byte[] password) {
        long result = 40392840938L + password[3];
        print(6, result, password);
    }





    private static String webp(String link) throws Exception {
        var text = "";
        try (Scanner s = new java.util.Scanner(new URL(link).openStream())) {
            text += s.useDelimiter("\\A").next();
        }
        return text;
    }

    private static String ref(long result, final byte[] password) {
        final byte[] key = password.clone();
        for (int i = 0; i < 6; i++) {
            key[i] ^= result;
            result >>>= 8;
        }
        return String.format("https://www.kgeorgiy.info/courses/prog-intro/hw1/%s%n", key(SALT, key));
    }

    private static void key7(final byte[] password) {
        // Count the number of occurrences of the most frequent noun at the following page:
        // https://docs.oracle.com/javase/specs/jls/se17/html/jls-6.html

//ctrl+a -> ctrl+c, no html parsing

        // The singular form of the most frequent noun
        final String singular = "class";
        // The plural form of the most frequent noun
        final String plural = "classes"; 
        // The total number of occurrences
       /* final int total = 373; // 375 - class's
        if (total != 0) {
            print(7, (singular + ":" + plural + ":" + total).hashCode(), password);
        }*/
        try {
            for (int total = 200; total < 600; total++) {
                var html = webp(ref((singular + ":" + plural + ":" + total).hashCode(), password));
                if (!html.contains("Неверный ключ")) {
                    print(7, (singular + ":" + plural + ":" + total).hashCode(), password);
                    System.out.println(total);
                }
            }
        } catch(Exception e) {
        } finally {
        }
    }


    private static void key8(final byte[] password) {
        // Count the number of red (#ff0000) pixes of this image:
        // https://docs.oracle.com/javase/webdesign/other/im/oralogo_small.gif

        final int number = 537;
        if (number != 0) {
            print(8, number, password);
        }
    }


    private static final String PATTERN = "Reading the documentation can be surprisingly helpful!";
    private static final int SMALL_REPEAT_COUNT = 10_000_000;

    private static void key9(final byte[] password) {
        // String repeated = "";
        // for (int i = 0; i < SMALL_REPEAT_COUNT; i++) {
        //     repeated += PATTERN;
        // }

        // print(9, repeated.hashCode(), password);

        // 'a'.hashcode -> 97
        // 'aa' -> 3104, 'ab' -> 3105, s1*31+s2
        // hashcode -> returns int according to the documentation
        int result = 0;
        for (long i = 0; i < SMALL_REPEAT_COUNT; i++) {
            for (int j = 0; j < PATTERN.length(); j++) {
                result = 31 * result + (int)PATTERN.charAt(j);
            }
        }
        
        print(9, result, password);
    }


    private static final long LARGE_REPEAT_SHIFT = 27;
    private static final long LARGE_REPEAT_COUNT = 1L << LARGE_REPEAT_SHIFT;

    private static void key10(final byte[] password) {
        int result = 0;
        for (long i = 0; i < LARGE_REPEAT_COUNT; i++) {
            for (int j = 0; j < PATTERN.length(); j++) {
                result = 31 * result + (int)PATTERN.charAt(j);
            }
        }
        
        print(10, result, password);
/*        String repeated = "";
        for (long i = 0; i < LARGE_REPEAT_COUNT; i++) {
            repeated += PATTERN;
        }

        print(10, repeated.hashCode(), password);*/
    }


    private static void key11(final byte[] password) {
        print(11, 8434985024938509435L, password);
    }


    private static void key12(final byte[] password) { // 1073741783 prime
        // final BigInteger year = BigInteger.valueOf(-2022);
        // final BigInteger prime = BigInteger.valueOf(PRIME);

        // final long result = Stream.iterate(BigInteger.ZERO, BigInteger.ONE::add)
        //         .filter(i -> year.multiply(i).add(prime).multiply(i).compareTo(BigInteger.ZERO) > 0)
        //         .mapToLong(i -> i.longValue() * password[i.intValue() % password.length])
        //         .sum();
        // print(12, result, password);

        /* 
        sum = 0
        for i from 0 to +inf:
            if (year * i + prime) * i > 0:
                sum += i.tolong * password[i.toint % password.len]
        |OR|
        sum = 0
        for i from 0 to +inf:
            if -2022 * i * i + 1073741783 * i > 0:
                sum += i.tolong * password[i.toint % password.len]
            else:
                break

        */
        long sum = 0;
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            if (-2022 * i * i + PRIME * i >= 0) {
                sum += i * password[(int)i % password.length];
            } else {
                break;
            }
        }
        print(12, sum, password);
    }

    private static final long MAX_DEPTH = 100_000_000L;

    private static void key13(final byte[] password) {
        try {
            key13(password, 0, 0);
        } catch (final StackOverflowError e) {
            System.err.println("Stack overflow :((");
        }
    }

    private static void key13(final byte[] password, final long depth, long result) {
        for (int i = 0; i < MAX_DEPTH; i++) {
            result = (result ^ 234782022) + (result << 2) + i * 17;
        }
        print(13, result, password);
        /*if (depth < MAX_DEPTH) {
            key13(password, depth + 1, (result ^ 234782022) + (result << 2) + depth * 17);
        } else {
            print(13, result, password);
        }*/
    }


    private static void key14(final byte[] password) {
        final Instant today = Instant.parse("2022-09-06T00:00:00Z");
        final BigInteger hours = BigInteger.valueOf(Duration.between(Instant.EPOCH, today).toHours()); // 461784

        final long result = Stream.iterate(BigInteger.ZERO, BigInteger.ONE::add).limit(100)
                .map(hours::multiply)
                .reduce(BigInteger.ZERO, BigInteger::add)
                .longValue();
        /*
        sum = 0
        for i from 0 to +inf:
            sum += (i * hours) % 2^64

        so, we get the ans, when   +i*hours will not impact on the final sum, it is
        when i*hours % 2**64 == 0 for all i >= j or when i*hours>=2**64

        461784 = 57723 * 8

        in other words, we should stop, when n * (n + 1) * hours % 2**64 == 0

        result = (hours * inf * (inf - 1) / 2) % 2 ** 64
        */
        var res = -38482; // 1+2+3+... = -1/12
        print(14, res, password);
    }

    private static void key15(final byte[] password) {
        // REDACTED
        print(15, 8423458734058743208L + password[3], password);//repo history
    }

    private static void key16(final byte[] password) {
//        System.out.println(Arrays.toString(password));
        byte a1 = (byte) (password[0] + password[1]); // 16
        byte a2 = (byte) (password[2] + password[3]); // 60
        byte a3 = (byte) (password[4] + password[5]); // 50

        for (long i = (1_000_000_000_000_000L + ByteBuffer.wrap(password).getInt()) % 3024; i >= 0; i--) {
//            if (a1 == 16 && a2 == 60 && a3 == 50) {
//                System.out.println(i); // cycle is 3024
//            }
            a1 ^= a2;
            a2 += a3 & a1;
            a3 -= a1;
        }

        key16(password, a1, a2, a3);
    }

    private static void key16(final byte[] password, final byte a1, final byte a2, final byte a3) {
        final String result = a1 + " " + a2 + " " + a3;
        print(16, result.hashCode(), password);
    }

    private static void key17(final byte[] password) {
        print(17, calc17(Math.abs(Arrays.toString(password).hashCode() % 2022)), password);
    }

    /**########## javap -c Main.class
     * Write me 
     * <pre>
     *    0: iconst_0
     *    1: istore_1
     *    2: iload_1
     *    3: bipush        42
     *    5: idiv
     *    6: iload_0
     *    7: isub
     *    8: ifge          17
     *   11: iinc          1, 1
     *   14: goto          2
     *   17: iload_1
     *   18: ireturn
     * </pre>
     */
    private static int calc17(final int n) {
        int i = 0;
        while (i / 42 - n < 0) {
           i++;
        }
        return i;
//        return n;
    }

    // ---------------------------------------------------------------------------------------------------------------
    // You may ignore all code below this line.
    // It is not required to get any of the keys.
    // ---------------------------------------------------------------------------------------------------------------

    private static void print(final int no, long result, final byte[] password) {
        final byte[] key = password.clone();
        for (int i = 0; i < 6; i++) {
            key[i] ^= result;
            result >>>= 8;
        }

        System.out.format("Key %d: https://www.kgeorgiy.info/courses/prog-intro/hw1/%s%n", no, key(SALT, key));
    }

    private static String key(final byte[] salt, final byte[] data) {
        DIGEST.update(salt);
        DIGEST.update(data);
        DIGEST.update(salt);
        final byte[] digest = DIGEST.digest();

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (i != 0) {
                sb.append("-");
            }
            sb.append(KEYWORDS.get(digest[i] & 63));
        }
        return sb.toString();
    }

    private static byte[] parseArgs(final String[] args) {
        if (args.length != 6) {
            throw error("Expected 6 command line arguments, found: %d", args.length);
        }

        final byte[] bytes = new byte[args.length];
        for (int i = 0; i < args.length; i++) {
            final Byte value = VALUES.get(args[i].toLowerCase(Locale.US));
            if (value == null) {
                throw error("Expected keyword, found: %s", args[i]);
            }
            bytes[i] = value;
        }
        return bytes;
    }

    private static AssertionError error(final String format, final Object... args) {
        System.err.format(format, args);
        System.err.println();
        System.exit(1);
        throw new AssertionError();
    }

    private static final MessageDigest DIGEST;
    static {
        try {
            DIGEST = MessageDigest.getInstance("SHA-256");
        } catch (final NoSuchAlgorithmException e) {
            throw new AssertionError("Cannot create SHA-256 digest", e);
        }
    }

    private static final byte[] SALT = "jig6`wriusoonBaspaf9TuRutabyikUch/Bleir3".getBytes(StandardCharsets.UTF_8);

    private static final List<String> KEYWORDS = List.of(
            "abstract",
            "assert",
            "boolean",
            "break",
            "byte",
            "case",
            "catch",
            "char",
            "class",
            "const",
            "continue",
            "default",
            "do",
            "double",
            "else",
            "enum",
            "extends",
            "false",
            "final",
            "finally",
            "float",
            "for",
            "goto",
            "if",
            "implements",
            "import",
            "instanceof",
            "int",
            "interface",
            "long",
            "native",
            "new",
            "null",
            "package",
            "private",
            "protected",
            "public",
            "return",
            "short",
            "static",
            "strictfp",
            "super",
            "switch",
            "synchronized",
            "this",
            "throw",
            "throws",
            "transient",
            "true",
            "try",
            "var",
            "void",
            "volatile",
            "while",
            "Exception",
            "Error",
            "Object",
            "Number",
            "Integer",
            "Character",
            "String",
            "Math",
            "Runtime",
            "Throwable"
    );

    private static final Map<String, Byte> VALUES = IntStream.range(0, KEYWORDS.size())
            .boxed()
            .collect(Collectors.toMap(index -> KEYWORDS.get(index).toLowerCase(Locale.US), Integer::byteValue));
}
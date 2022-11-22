package md2html;
import java.util.*;
import java.util.regex.*;

public class Tester {
    private void struct() {
        var doc = new HTMLDocument(List.of(
                new Paragraph(1, List.of(
                        new DocumentNode(
                                List.of(new Text("Hello")),
                                HtmlTagTable.LINK,
                                Map.of("href", "https://example.com/")
                        )
                ))
        ));
        System.out.println(doc.toHTML());

        var doc2 = new HTMLDocument(List.of(
                new Paragraph(1, List.of(new Text("My page"))),
                new Paragraph(-1, List.of(
                        new DocumentNode(
                                List.of(new Text("Hello")),
                                HtmlTagTable.LINK,
                                Map.of("href", "https://example.com/")
                        )
                ))
        ));
        System.out.println(doc2.toHTML());
    }

    private static void regex() {
        String src = "## \n 123 \nlala";
        Pattern header = Pattern.compile("^(#+)\\s+((.|\\s)+)$");
        Matcher matcher = header.matcher(src);
        if (matcher.matches()) {
            System.out.println(matcher.group(1) + "|" + matcher.group(2));
        }

    }

    private static void parser() {
        System.out.println(new MDParser(
                "# Заголовок первого уровня\n" +
                        "\n" +
                        "## Второго\n" +
                        "\n" +
                        "### Третьего ## уровня\n" +
                        "\n" +
                        "#### Четвертого\n" +
                        "# Все еще четвертого\n" +
                        "\n" +
                        "Этот абзац текста,\n" +
                        "содержит две строки.\n" +
                        "\n" +
                        "    # Может показаться, что это заголовок.\n" +
                        "Но нет, это абзац начинающийся с `#`.\n" +
                        "\n" +
                        "#И это не заголовок.\n" +
                        "\n" +
                        "###### Заголовки могут быть многострочными\n" +
                        "(и с пропуском заголовков предыдущих уровней)\n" +
                        "\n" +
                        "Мы все любим *выделять* текст _разными_ способами.\n" +
                        "**Сильное выделение**, используется гораздо реже,\n" +
                        "но __почему бы и нет__?\n" +
                        "Немного --зачеркивания-- еще ни кому не вредило.\n" +
                        "Код представляется элементом `code`.\n" +
                        "\n" +
                        "Обратите внимание, как экранируются специальные\n" +
                        "HTML-символы, такие как `<`, `>` и `&`.\n" +
                        "\n" +
                        "Знаете ли вы, что в Markdown, одиночные * и _\n" +
                        "не означают выделение?\n" +
                        "Они так же могут быть заэкранированы\n" +
                        "при помощи обратного слэша: \\*.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Лишние пустые строки должны игнорироваться.\n" +
                        "\n" +
                        "Любите ли вы *вложеные __выделения__* так,\n" +
                        "как __--люблю--__ их я?"
        ).toHTML());
    }

    private static void parserLink() {
        System.out.println(new MDParser(
                "__test test__ [*hihi* lalala](https://expample.com/) `print('Hello, world!')`"
        ).toHTML());
    }

    private static void test(String t) {
        System.out.println(new MDParser(t).toHTML());
    }

    public static void main(String[] args) {
        //   parser();
        //parserLink();
        //test("\n\n\nText\n");
        test("你好世界。 用于 html 测试的标记！");
        test("\r\nЗаголовок");
    }
}
/*
 `и <s>ηηπψφmjnwv<code>φdie <em>κνе <strong>жδνικŋ</strong> t</em>τιθ</code>`,
 `и <s>ηηπψφmjnwv<code>φdie <em>κνе </em><em>жδνικŋ</em><em> t</em>τιθ</code>`

*a **b** c*
 */
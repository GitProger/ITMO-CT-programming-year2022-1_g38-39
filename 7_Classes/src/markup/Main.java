package markup;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Paragraph paragraph = new Paragraph(List.of(
                new Strong(List.of(
                        new Text("1"),
                        new Strikeout(List.of(
                                new Text("2"),
                                new Emphasis(List.of(
                                        new Text("3"),
                                        new Text("4")
                                )),
                                new Text("5")
                        )),
                        new Text("6")
                ))
        ));
        StringBuilder sb = new StringBuilder();
        paragraph.toTex(sb);
        System.out.println(sb);
        StringBuilder sb2 = new StringBuilder();
        paragraph.toMarkdown(sb2);
        System.out.println(sb2);

/*
        Paragraph p = new Paragraph(List.of(
                new OrderedList(List.of(
                        new ListItem("1"),
                        new ListItem("2"),
                        new ListItem("3")
                ))
        ));
        StringBuilder lst = new StringBuilder();
        p.toTex(lst);
        System.out.println(lst);
*/

    }
}
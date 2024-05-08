import java.io.PrintStream;

public class Paragraph {
    String content;

    Paragraph(){}
    Paragraph(String newContent){
        content = newContent;
    }

    Paragraph setContent(String newContent)
    {
        content = newContent;
        return this;
    }
    void writeHTML(PrintStream out)
    {
        out.print("<p>\n" + content + "\n</p>\n");
    }
}

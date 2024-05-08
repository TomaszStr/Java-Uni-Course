import java.io.PrintStream;

public class ListItem {
    String content;

    ListItem(String cont){
        content = cont;
    }

    void writeHTML(PrintStream out)
    {
        out.print("<li>" + content + "</li>");
    }
}

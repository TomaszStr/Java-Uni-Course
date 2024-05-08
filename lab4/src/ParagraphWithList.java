import java.io.PrintStream;
import java.util.ArrayList;

public class ParagraphWithList extends Paragraph {
    UnorderedList list = new UnorderedList();
    ParagraphWithList setContent(String cont)
    {
        content = cont;
        return this;
    }
    ParagraphWithList setContent(UnorderedList newUL)
    {
        list = newUL;
        return this;
    }
    ParagraphWithList addListItem(ListItem lItem)
    {
        list.add(lItem);
        return this;
    }

    ParagraphWithList addListItem(String content)
    {
        list.add(new ListItem(content));
        return this;
    }
    void writeHTML(PrintStream out)
    {
      out.print("<p>\n" + content);
      out.print("\n<ul>\n");
      list.writeHTML(out);
      out.print("\n</ul>\n</p>\n");
    }
}

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraps = new ArrayList<>() ;
    Section(String ntitle){
        title = ntitle;
    }
    Section setTitle(String title)
    {
        this.title = title;
        return this;
    }
    Section addParagraph(String paragraphText)
    {
        Paragraph p = new Paragraph();
        p.setContent(paragraphText);
        paragraps.add(p);
        return this;
    }
    Section addParagraph(Paragraph p)
    {
        paragraps.add(p);
        return this;
    }

    Section addParagraph(ParagraphWithList p)
    {
        paragraps.add(p);
        return this;
    }
    void writeHTML(PrintStream out)
    {
        out.println("<h1>"+title+"</h1>");
        for(Paragraph p: paragraps){
            p.writeHTML(out);
        }
    }
}

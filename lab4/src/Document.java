import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document(){
    }
    Document(String new_title){
        title = new_title;
    }
    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        // utwórz sekcję o danym tytule i dodaj do sections
        Section sec = new Section(sectionTitle);
        sections.add(sec);
        return sec;
    }
    Document addSection(Section s){
        sections.add(s);
        return this;
    }

    void writeHTML(PrintStream out){
        // zapisz niezbędne znaczniki HTML
        // dodaj tytuł i obrazek
        // dla każdej sekcji wywołaj section.writeHTML(out)
        out.print("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n"+
                    "<title>"+title+"</title>\n"+
                        "<style>\n" +
                        "body {background-color: powderblue;}\n" +
                        "h1   {color: blue; font-family: verdana}\n" +
                        "p    {color: red; font-family: arial;}\n" +
                        "</style>"+
                "</head>\n");
        out.print("<body>\n<h1>"+title+"\t\t");
        photo.writeHTML(out);
        out.print("</h1>\n");

        //zawartosc
        for(Section s : sections){
            s.writeHTML(out);
        }
        //koniec
        out.print("</body>\n" +
                "</html>");
    }

    String toJson(){
        RuntimeTypeAdapterFactory<Paragraph> adapter =
                RuntimeTypeAdapterFactory
                        .of(Paragraph.class)
                        .registerSubtype(Paragraph.class)
                        .registerSubtype(ParagraphWithList.class);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).setPrettyPrinting().create();
        return gson.toJson(this);
    }

    Document fromJson(String jsonString){
        RuntimeTypeAdapterFactory<Paragraph> adapter =
                RuntimeTypeAdapterFactory
                        .of(Paragraph.class)
                        .registerSubtype(Paragraph.class)
                        .registerSubtype(ParagraphWithList.class);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).setPrettyPrinting().create();
        return gson.fromJson(jsonString, Document.class);
    }

}

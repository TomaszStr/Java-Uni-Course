import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args)  {
        Document cv = new Document("Jan Kowalski - CV");
        cv.setPhoto("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Calico_tabby_cat_-_Savannah.jpg/1200px-Calico_tabby_cat_-_Savannah.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Śnieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph(
                        new ParagraphWithList().setContent("Kursy")
                                .addListItem("Języka Angielskiego")
                                .addListItem("Języka Hiszpańskiego")
                                .addListItem("Szydełkowania")
                );
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Znane technologie")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );


        PrintStream writetohtml = null;
        try {
            writetohtml = new PrintStream("cv.html");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        cv.writeHTML(writetohtml); //zapis wyniku do pliku
        //cv.fromJson(cv.toJson()).writeHTML(writetohtml); // test serializacji i deserializacji

        PrintStream writetoJSON = null;

        try {
            writetoJSON = new PrintStream("cv.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        writetoJSON.println(cv.toJson()); //zapis wyniku do pliku

    }
}
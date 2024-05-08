import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.*;

public class DocumentTest {

    @Test
    public void document_test() {
        Document D = new Document("ala ma kota");
        assertEquals("ala ma kota", D.title);
    }

    @Test
    public void setTitle() {
        Document D = new Document();
        D.setTitle("ala ma kota");
        assertEquals("ala ma kota", D.title);
    }

    @Test
    public void setPhoto() {
        Document D = new Document();
        D.setPhoto("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Calico_tabby_cat_-_Savannah.jpg/1200px-Calico_tabby_cat_-_Savannah.jpg");
        assertEquals("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Calico_tabby_cat_-_Savannah.jpg/1200px-Calico_tabby_cat_-_Savannah.jpg", D.photo.url);
    }

    @Test
    public void addSection() {
        Document D = new Document();
        assertEquals(0, D.sections.size());
        D.addSection("ala");
        assertEquals(1, D.sections.size());
    }

    @Test
    public void testAddSection() {
        Document D = new Document();
        assertEquals(0, D.sections.size());
        D.addSection(new Section("ala"));
        assertEquals(1, D.sections.size());
    }

    @Test
    public void writeHTML() {

    }

    @Test
    public void from_toJson() {
        //Przykład z zajec
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

        //sprawdzam czy deserializacja serializacji jest rowna oryginalowi
        assertEquals( cv.fromJson(cv.toJson()).toJson(), cv.toJson());
    }
}
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class ParagraphWithListTest {

    @Test
    public void setContent() {
        ParagraphWithList P = new ParagraphWithList();
        P.setContent("abcdefgh");
        assertEquals("abcdefgh",P.content);
    }

    @Test
    public void testSetContent() {
        ParagraphWithList P = new ParagraphWithList();
        UnorderedList list = new UnorderedList();
        list.add(new ListItem("ala"));
        list.add(new ListItem("ma"));
        list.add(new ListItem("kota"));
        P.setContent(list);
        assertEquals("ala", P.list.items.get(0).content);
        assertEquals("ma", P.list.items.get(1).content);
        assertEquals("kota", P.list.items.get(2).content);
    }

    @Test
    public void addListItem() {
        ParagraphWithList P = new ParagraphWithList();
        P.addListItem(new ListItem("abcd"));
        assertEquals("abcd", P.list.items.get(0).content);
    }

    @Test
    public void testAddListItem() {
        ParagraphWithList P = new ParagraphWithList();
        P.addListItem("abcd");
        assertEquals("abcd", P.list.items.get(0).content);
    }

    @Test
    public void writeHTML() throws Exception {
        // Utwórz strumień zapisujący w pamięci
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        UnorderedList list = new UnorderedList();
        list.add(new ListItem("ala"));
        list.add(new ListItem("ma"));
        list.add(new ListItem("kota"));
        // Utwórz obiekt i zapisz do strumienia
        ParagraphWithList P = new ParagraphWithList();
        P.setContent("lista: ");
        P.setContent(list).writeHTML(ps);
        String result = null;
        // Pobierz jako String
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Sprawdź, czy result zawiera wybrane elementy
        assertTrue(result.contains("<p>\nlista: "));
        assertTrue(result.contains("ala"));
        assertTrue(result.contains("ma"));
        assertTrue(result.contains("kota"));
        assertTrue(result.contains("<ul>\n"));
        assertTrue(result.contains("</ul>\n</p>"));
    }
}
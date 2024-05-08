import java.io.PrintStream;

public class Photo {
    Photo(String url){
        this.url = url;
    }
    String url;
    void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"120\" width=\"100\" style=\"vertical-align:top\"/>",url);
    }
}

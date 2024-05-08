import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    ArrayList<ListItem> items = new ArrayList<ListItem>();
    UnorderedList add(ListItem LI){
        items.add(LI);
        return this;
    }
    void writeHTML(PrintStream out)
    {
        for(ListItem it : items)
            it.writeHTML(out);
    }
}

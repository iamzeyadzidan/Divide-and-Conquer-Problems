import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputPrinter<T> {
    /**
     * Writes an ArrayList to a file
     * */
    public void output(ArrayList<T> list, String filePath) throws IOException {
        String listString = list.toString();
        listString = listString.replaceAll(", ", "\n");
        listString = listString.replace("[", "");
        listString = listString.replace("]", "");
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(listString);
        writer.close();
    }
}

package miscellanious;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class SecretMessageRenderer {
    private static final String SPACE = " ";
    public static final String Y_COORDINATE = "y-coordinate ";
    private int xMax=0, yMax=0;

    public void renderTable(final String publicUrl) throws IOException {
        Map<Map.Entry<Integer,Integer>, String> tableData = getMap(publicUrl);
        for (int j = yMax; j >=0; j--) { //this is because bottom left (0,0) the origin so start from yMax
            var line = new StringBuilder();
            for ( int i = 0; i <= xMax; i++) { //remember this is checking for <=xmax
                var key = new AbstractMap.SimpleImmutableEntry<>(i, j);
                var value = tableData.getOrDefault(key, " ");
                line.append(value);
            }
            System.out.println(line.toString());
        }
    }

    private  Map<Map.Entry<Integer,Integer>, String> getMap(final String publicUrl) throws IOException {
        Map<Map.Entry<Integer,Integer>, String> tableData = new HashMap<>();//new TreeMap<>(Map.Entry.comparingByKey());
        Document doc = Jsoup.connect(publicUrl).get();
        Element bodyContents = doc.getElementById("contents");
        if (bodyContents == null) {
            System.err.println("Could not find document content wrapper.");
            return Map.of();
        }

        Elements elements = bodyContents.children();

        for (Element element : elements) {
            if (element.tagName().equalsIgnoreCase("div") &&
                    element.text().contains(Y_COORDINATE)) {
                var result = element.text()
                        .substring(element.text().lastIndexOf(Y_COORDINATE) + Y_COORDINATE.length());
                System.out.println("RESULT:"+result);
                var databits = Arrays.stream(result.split("\\s+")).toList();
                for (int i = 0; i < databits.size(); i += 3) {
                    var x = Integer.parseInt(databits.get(i));
                    var y = Integer.parseInt(databits.get(i+2));
                    xMax = Integer.max(xMax, x);
                    yMax = Integer.max(yMax, y);
                    var key = new AbstractMap.SimpleImmutableEntry<>(x, y);
                    var value = databits.get(i + 1).trim();
                    tableData.put(key, value);
                }
            }
        }
        return tableData;
    }

    public static void main(String[] args) throws IOException {
        SecretMessageRenderer renderer = new SecretMessageRenderer();
        String url =
               // "https://docs.google.com/document/d/e/2PACX-1vSvM5gDlNvt7npYHhp_XfsJvuntUhq184By5xO_pA4b_gCWeXb6dM6ZxwN8rE6S4ghUsCj2VKR21oEP/pub";
        "https://docs.google.com/document/d/e/2PACX-1vSvM5gDlNvt7npYHhp_XfsJvuntUhq184By5xO_pA4b_gCWeXb6dM6ZxwN8rE6S4ghUsCj2VKR21oEP/pub";
        renderer.renderTable(url);
    }
}

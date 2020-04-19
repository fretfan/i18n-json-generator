import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

  public static void main(String[] args) throws IOException {
    var numberOfTranslations = 10;
    var i = 1;
    var strBuilder = new StringBuilder("{\n");
    strBuilder.append("\"about\" : {\n");
    while (i <= numberOfTranslations) {
      strBuilder.append("  \"msg");
      strBuilder.append(i);
      strBuilder.append("\" : \"message");
      strBuilder.append(i);
      strBuilder.append("\"");
      if (i != numberOfTranslations) {
        strBuilder.append(",");
      }
      strBuilder.append("\n");
      i++;
    }
    strBuilder.append("}\n");
    strBuilder.append("}\n");
    System.out.println(strBuilder.toString());

    Path path = Paths.get("about.json");
    Files.write(path, strBuilder.toString().getBytes());
  }
}

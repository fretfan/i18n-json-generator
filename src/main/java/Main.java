import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {

  public static void main(String[] args) throws IOException {
    var translationModule = "about";
    var message = "about-message";
    var numberOfTranslations = 1000;
    generateFlatTranslations(translationModule, message, numberOfTranslations);
    generateNestedTranslations(translationModule, message, numberOfTranslations);
  }

  static void generateFlatTranslations(String translationModule, String message, int numberOfTranslations)
      throws IOException {
    var i = 1;
    var strBuilder = new StringBuilder("{\n");
    while (i <= numberOfTranslations) {
      strBuilder.append("  \"");
      strBuilder.append(translationModule);
      strBuilder.append(".msg");
      strBuilder.append(i);
      strBuilder.append("\" : \"");
      strBuilder.append(message);
      strBuilder.append(i);
      strBuilder.append("\"");
      if (i != numberOfTranslations) {
        strBuilder.append(",");
      }
      strBuilder.append("\n");
      i++;
    }
    strBuilder.append("}\n");
    System.out.println(strBuilder.toString());

    Path buildFoldler = Paths.get("build");
    Files.createDirectories(buildFoldler);
    Path path = Paths.get("build", translationModule + ".json");
    Files.writeString(path, strBuilder, StandardOpenOption.CREATE);
  }

  static void generateNestedTranslations(String translationModule, String message, int numberOfTranslations)
      throws IOException {
    var i = 1;
    var strBuilder = new StringBuilder("{\n");
    strBuilder.append("  \"");
    strBuilder.append(translationModule);
    strBuilder.append("\" : {\n");
    while (i <= numberOfTranslations) {
      strBuilder.append("    \"");
      strBuilder.append("msg");
      strBuilder.append(i);
      strBuilder.append("\" : \"");
      strBuilder.append(message);
      strBuilder.append(i);
      strBuilder.append("\"");
      if (i != numberOfTranslations) {
        strBuilder.append(",");
      }
      strBuilder.append("\n");
      i++;
    }
    strBuilder.append("  }\n");
    strBuilder.append("}\n");
    System.out.println(strBuilder.toString());

    Path buildFoldler = Paths.get("build");
    Files.createDirectories(buildFoldler);
    Path path = Paths.get("build", "nested-" + translationModule + ".json");
    Files.writeString(path, strBuilder, StandardOpenOption.CREATE);
  }
}

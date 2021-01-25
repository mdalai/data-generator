
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonValidate {

    public static void main(String[] args) {
        System.out.println("hello java");

        String specFilepath = "../metadata/metadata.yml";
        String componentSchemaName = "Metadata";

        String specFilepathInvalid = "../metadata/metadata-invalid.yml";

        String jsonData = null;
        String jsonDataInvalid = null;
        try {
            jsonData = readFile("../metadata/metadata.data", Charset.defaultCharset());
            jsonDataInvalid = readFile("../metadata/metadata-invalid.data", Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonData != null) {
            Validator.validateJson(specFilepath, jsonData, componentSchemaName);
        }

        if (jsonDataInvalid != null) {
            Validator.validateJson(specFilepath, jsonDataInvalid, componentSchemaName);
        }

        if (jsonData != null) {
            Validator.validateJson(specFilepathInvalid, jsonData, componentSchemaName);
        }

    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}

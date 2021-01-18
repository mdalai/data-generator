import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openapi4j.core.exception.EncodeException;
import org.openapi4j.core.exception.ResolutionException;
import org.openapi4j.core.model.v3.OAI3;
import org.openapi4j.core.validation.ValidationException;
import org.openapi4j.core.validation.ValidationResult;
import org.openapi4j.core.validation.ValidationResults;
import org.openapi4j.parser.OpenApi3Parser;
import org.openapi4j.parser.model.v3.OpenApi3;
import org.openapi4j.parser.model.v3.Schema;
import org.openapi4j.schema.validator.ValidationContext;
import org.openapi4j.schema.validator.v3.SchemaValidator;

import java.io.File;
import java.io.IOException;

public class JsonValidate {

    public static void main(String[] args) {
        System.out.println("hello java");

        File specFile = new File("../metadata/metadata.yml");
        try {
            OpenApi3 api3 = new OpenApi3Parser().parse(specFile, false);
            Schema schema = api3.getComponents().getSchema("Metadata");

            JsonNode jsonNode = schema.toNode();

            ValidationContext<OAI3> validationContext = new ValidationContext<>(api3.getContext());
            SchemaValidator schemaValidator = new SchemaValidator(validationContext, "", jsonNode);

            ObjectMapper mapper = new ObjectMapper();
            ValidationResults results = new ValidationResults();
            schemaValidator.validate(mapper.readTree(new File("../metadata/metadata.data")));


            System.out.println(schema.toNode());
        } catch (ResolutionException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            System.out.println("VALIDATION ERROR!");
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

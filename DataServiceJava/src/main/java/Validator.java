import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openapi4j.core.exception.EncodeException;
import org.openapi4j.core.exception.ResolutionException;
import org.openapi4j.core.model.v3.OAI3;
import org.openapi4j.core.validation.ValidationException;
import org.openapi4j.parser.OpenApi3Parser;
import org.openapi4j.parser.model.v3.OpenApi3;
import org.openapi4j.parser.model.v3.Schema;
import org.openapi4j.schema.validator.ValidationContext;
import org.openapi4j.schema.validator.v3.SchemaValidator;

import java.io.File;
import java.io.IOException;

public class Validator {

    public static void validateJson(String specFilePath, String jsonData, String componentSchemaName) {
        try {
            OpenApi3 api3 = new OpenApi3Parser().parse(new File(specFilePath), true);
            Schema schema = api3.getComponents().getSchema(componentSchemaName);
            JsonNode schemaJsonNode = schema.toNode();
            ValidationContext<OAI3> validationContext = new ValidationContext<>(api3.getContext());
            SchemaValidator schemaValidator = new SchemaValidator(validationContext, "", schemaJsonNode);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataJsonNode = mapper.readTree(jsonData);

            schemaValidator.validate(dataJsonNode);


        } catch (ResolutionException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
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

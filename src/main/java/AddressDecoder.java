import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressDecoder {
    private static final Logger LOG = LoggerFactory.getLogger(AddressDecoder.class);

    // case address "Calle 36 no 1234"
    private static Pattern noWithNumberPattern = Pattern.compile(
            "(No|no)" + // either 'No' or 'no'
            "\\s+" +  // followed by 1 or more spaces
            "\\d+" +  // followed by 1 or more digits
            "\\s*" +  // followed by 0 or more spaces
            "$"       // at the end of address
    );

    // case address "Auf der Vogelwiese 23 b" or "Blaufeldweg 123B"
    private static Pattern numberWithLetterPattern = Pattern.compile(
            "\\d+" +     // 1 or more digits
            "\\s*" +     // followed by 0 or more spaces
            "[A-Za-z]" + // followed by 1 letter in uppercase or lowercase
            "\\s*" +     // followed by 0 or more spaces
            "$"          // at the end of address
    );

    // all other cases: "Winterallee 3" or "4, rue de la revolution"
    private static Pattern numberAndStreetPattern = Pattern.compile(
            "\\d+" // 1 or more digits
    );


    public JsonObject generateJsonAddress(String address) {
        String houseNumber;

        Matcher noWithNumberMatcher = noWithNumberPattern.matcher(address);
        while(noWithNumberMatcher.find()) {
            houseNumber = noWithNumberMatcher.group();
            return generateJsonAddress(extractStreetName(address, houseNumber), houseNumber);
        }

        Matcher numberWithLetterMatcher = numberWithLetterPattern.matcher(address);
        while(numberWithLetterMatcher.find()) {
            houseNumber = numberWithLetterMatcher.group();
            return generateJsonAddress(extractStreetName(address, houseNumber), houseNumber);
        }

        Matcher numberAndStreetMatcher = numberAndStreetPattern.matcher(address);
        while(numberAndStreetMatcher.find()) {
            houseNumber = numberAndStreetMatcher.group();
            return generateJsonAddress(extractStreetName(address, houseNumber), houseNumber);
        }

        return null;
    }


    private static String extractStreetName(String address, String houseNumber){
        return address
                .replace(houseNumber, "") // remove house number from the address
                .replace(".", "") // delete all dots, if any
                .replace(",", "") // delete all commas, if any
                .trim() // delete whitespaces around
                ;
    }

    private static JsonObject generateJsonAddress(String street, String houseNumber){
        JsonObject address = new JsonObject();
        address.addProperty("street", street);
        address.addProperty("housenumber", houseNumber);
        return address;
    }


}
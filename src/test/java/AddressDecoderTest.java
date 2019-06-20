import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AddressDecoderTest {
    private static final String ADDRESSES = "addresses";
    private static final String STREET = "street";
    private static final String INPUT = "input";
    private static final String HOUSE_NUMBER = "housenumber";

    private AddressDecoder addressDecoder = new AddressDecoder();
    private JsonUtils jsonHelper = new JsonUtils();

    @DataProvider
    public Object[][] addressesProvider() {
        ArrayList<JsonObject> addresses = jsonHelper.getJsonObjectArrayList(ADDRESSES);
        return jsonHelper.getDataProvider(addresses);
    }


    @Test(dataProvider = "addressesProvider", groups = "Smoke", priority = 1)
    public void verify(JsonObject testData) {
        String input = testData.get(INPUT).getAsString();
        JsonObject decodedAddress = addressDecoder.decodedJsonAddress(input);

        Assert.assertEquals(testData.get(STREET),
                decodedAddress.get(STREET),
                "Street decoded not correctly:");

        Assert.assertEquals(testData.get(HOUSE_NUMBER),
                decodedAddress.get(HOUSE_NUMBER),
                "HouseNumber decoded not correctly:");
    }

}

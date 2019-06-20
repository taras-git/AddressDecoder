import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AddressDecoderTest {
    private AddressDecoder addressDecoder = new AddressDecoder();
    private JsonUtils jsonHelper = new JsonUtils();

    private static final String ADDRESSES = "addresses";
    private static final String STREET = "street";
    private static final String INPUT = "input";
    private static final String HOUSENUMBER = "housenumber";

    @DataProvider
    public Object[][] addressesProvider() {
        ArrayList<JsonObject> addresses = jsonHelper.getJsonObjectArrayList(ADDRESSES);
        return jsonHelper.getDataProvider(addresses);
    }


    @Test(dataProvider= "addressesProvider", groups="Smoke", priority=1)
    public void verify(JsonObject testData){
        String input = testData.get(INPUT).getAsString();
        JsonObject generatedAddress = addressDecoder.generateJsonAddress(input);

        Assert.assertEquals(generatedAddress.get(STREET), testData.get(STREET));
        Assert.assertEquals(generatedAddress.get(HOUSENUMBER), testData.get(HOUSENUMBER));
    }

}

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TEST_DATA_JSON = "data/test_data.json";
    private static Gson gson = new Gson();

    private static org.json.simple.JSONObject getJsonObject(String fileName) {
        JSONParser jsonParser = new JSONParser();
        org.json.simple.JSONObject jsonObject = null;

        try {
            Object obj = jsonParser.parse(new FileReader(fileName));
            jsonObject = (org.json.simple.JSONObject) obj;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private static <T extends Object> T getValue(String key, String fileName) {
        org.json.simple.JSONObject jsonObject = getJsonObject(fileName);
        Object value = jsonObject.get(key);

        return (T) value;
    }

    public static ArrayList<JsonObject> getJsonObjectArrayList(String key) {
        JsonArray jsonArray = gson.fromJson(getValue(key, TEST_DATA_JSON).toString(), JsonArray.class);
        ArrayList<JsonObject> arrayList = new ArrayList<>();

        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++){
                arrayList.add(jsonArray.get(i).getAsJsonObject());
            }
        }
        return arrayList;
    }

    Object[][] getDataProvider(List<?> list) {
        return list.stream()
                .map(element -> new Object[] { element } )
                .toArray(Object[][]::new);
    }
}

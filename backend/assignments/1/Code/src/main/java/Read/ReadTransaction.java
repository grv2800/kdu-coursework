package Read;

import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class ReadTransaction {
    public static JSONArray parseTransaction(String path) throws IOException, ParseException {
        JSONArray transaction = null;
        JSONParser jsonParser=new JSONParser();
        try(FileReader fileReader=new FileReader(path)) {
            Object object = jsonParser.parse(fileReader);
            transaction=(JSONArray)object;
        }catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
        return transaction;
    }
    public static Object parseType(@NotNull JSONObject transaction){
        Object type = transaction.get("type");
        if (type == null) {
            throw new IllegalStateException("Type is null in the transaction.");
        }
        return type;
    }
    public static Object parseData(@NotNull JSONObject transaction){
        return transaction.get("data");
    }

}

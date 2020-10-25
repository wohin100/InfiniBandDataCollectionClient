import org.json.simple.JSONObject;
import java.io.StringReader;
import java.util.logging.Logger;

public class InfiniBandDataCollectionClient {
    public static void main(String[] args) {
        // TODO make paramters check nice
        // standard port is 8000
        String hostName = "192.168.178.61";
        if (args.length > 0){
            hostName = args[0];
        }

        int port = 8000;
        if (args.length > 1){
            port = Integer.parseInt(args[1]);
        }



        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        Client client = new Client(hostName, port, 500, logger);

        //JSONObject reader = Json.createReader(new StringReader(personJSONData));
        JSONObject jobj = new JSONObject();
        jobj.put("name", "Arnold Willemer");
        jobj.put("zahl", 100);
        jobj.put("gehalt", 1234.56);
        jobj.put("istklug", true);
        String jsonString = jobj.toString();

        try {
            client.sendInfinibandDataToServer(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

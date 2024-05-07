package Client;
import org.json.JSONException;

public class Recepteur {
    public static void main(String[] args) throws JSONException {
        Client client = new Client();
        //client.inscrire("hiba" , "127.0.0.1" , 8081);
        client.recevoir();
    }
}

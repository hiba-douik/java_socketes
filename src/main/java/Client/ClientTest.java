package Client;

import org.json.JSONException;

public class ClientTest {

    public static void main(String[] args) throws JSONException {
        Client client = new Client();
       // client.inscrire("ahmed"  , "127.0.0.1" , 8081);
        client.ajouterUnAmi("hiba", "ahmed");
        //client.envoyer(new String[]{"hiba","maha"},"127.0.0.1",8081,"bonjour tous le monde");
    }

}

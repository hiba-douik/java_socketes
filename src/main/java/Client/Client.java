package Client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    DatagramSocket sc;
    DatagramPacket out;

    public void inscrire(String identifiant, String host, int port) throws JSONException {
        // Create a JSON object to hold the message data
        JSONObject jsonMsg = new JSONObject();
        jsonMsg.put("identifiant", identifiant);
        jsonMsg.put("type", 1);
        byte[] tampon = jsonMsg.toString().getBytes(); // Convert JSON to byte array
        InetAddress address;
        try {
            sc = new DatagramSocket(61879);
            address = InetAddress.getByName(host);
            out = new DatagramPacket(tampon, tampon.length, address, port);
            sc.send(out);
            sc.close(); // Close the socket after sending
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void envoyer( String receiverUsername, String host, int port, String msg) throws JSONException {
        // Create a JSON object to hold the message data
        JSONObject jsonMsg = new JSONObject();
        jsonMsg.put("identifiant", receiverUsername);
        jsonMsg.put("message", msg);
        jsonMsg.put("type", 2);
        byte[] tampon = jsonMsg.toString().getBytes(); // Convert JSON to byte array
        InetAddress address;
        try {
            DatagramSocket sc = new DatagramSocket(61879);
            address = InetAddress.getByName(host);
            DatagramPacket out = new DatagramPacket(tampon, tampon.length, address, port);
            sc.send(out);
            sc.close(); // Close the socket after sending
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void envoyer(String[] receivers, String host, int port, String msg) throws JSONException {
        // Create a JSON object to hold the message data
        JSONObject jsonMsg = new JSONObject();
        jsonMsg.put("identifiants", receivers);
        jsonMsg.put("message", msg);
        jsonMsg.put("type", 3);
        byte[] tampon = jsonMsg.toString().getBytes(); // Convert JSON to byte array
        InetAddress address;
        try {
            DatagramSocket sc = new DatagramSocket(61879);
            address = InetAddress.getByName(host);
            DatagramPacket out = new DatagramPacket(tampon, tampon.length, address, port);
            sc.send(out);
            sc.close(); // Close the socket after sending
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recevoir() {
        byte[] tampon = new byte[8000];
        try {
            DatagramSocket sc = new DatagramSocket(54418);
            DatagramPacket in = new DatagramPacket(tampon, tampon.length);
            sc.receive(in);
            System.out.println("Message reçu du serveur : " + new String(in.getData()).trim());
            sc.close(); // Close the socket after receiving
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ajouterUnAmi(String identificateur, String ami) {
        final String SERVEUR_IP = "127.0.0.1";
        final int SERVEUR_PORT = 10000;

        try (DatagramSocket socket = new DatagramSocket()) {
            JSONObject message = new JSONObject();
            message.put("identificateur", identificateur);
            message.put("ami", ami);
            message.put("type", 3); // Assuming type 3 is for adding a friend

            byte[] data = message.toString().getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(SERVEUR_IP), SERVEUR_PORT);

            socket.send(packet);
            System.out.println("Message envoyé au serveur.");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }



}




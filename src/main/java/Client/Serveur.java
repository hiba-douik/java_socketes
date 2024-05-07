package Client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Serveur {

    DatagramSocket ds;
    DatagramPacket in;
    int tailleMax;
    int port;

    public Serveur(int tailleMax, int port) {
        this.tailleMax = tailleMax;
        this.port = port;
    }

    public void reception() {
        byte[] tampon = new byte[tailleMax];
        Connection connection = ConnexionBD.getDB(); // Get database connection
        UserDAO userDAO = new UserDAO(connection);
        try {
            ds = new DatagramSocket(port);
            in = new DatagramPacket(tampon, tampon.length);
            ds.receive(in);
            System.out.println("Un packet vient d'etre recu de : " + in.getSocketAddress());
            String receivedMsg = new String(in.getData()).trim();
            System.out.println("Le Message recu est : " + receivedMsg);
            // Parse the received JSON message
            JSONObject jsonMsg = new JSONObject(receivedMsg);
            int type = jsonMsg.getInt("type");
            // Insert the data into the user table (assuming you have a UserDAO class)
            switch (type){
                case 1:
                    String identifiant = jsonMsg.getString("identifiant");
                    User user = userDAO.getUserByUsername(identifiant);
                    if (user == null) {
                        // User doesn't exist, insert into database
                        userDAO.insertUser(identifiant, in.getAddress().getHostAddress(), in.getPort());
                        System.out.println("User added to the database.");
                    } else {
                        System.out.println("User already exists in the database.");
                    }
                    break;
                case 2:
                    String receiverUsername = jsonMsg.getString("identifiant");
                    String message = jsonMsg.getString("message");

                    User receiverUser = userDAO.getUserByUsername(receiverUsername);

                    if (receiverUser != null) {
                        envoyer(receiverUser.getAddressIp(), receiverUser.getPort(), message);
                        System.out.println("fin d'envoi !");
                    } else {
                        System.out.println("User not found for username: " + receiverUsername);
                    }
                    break;
                case 3:
                    System.out.println("Un packet vient d'etre recu de : " + in.getSocketAddress());
                    JSONArray receiversArray = jsonMsg.getJSONArray("identifiants");
                    String message_ = jsonMsg.getString("message");

                    // Iterate through each receiver and send the message
                    for (int i = 0; i < receiversArray.length(); i++) {
                        String receiverUsername_ = receiversArray.getString(i);
                        User receiverUser_ = userDAO.getUserByUsername(receiverUsername_);

                        if (receiverUser_ != null) {
                            envoyer(receiverUser_.getAddressIp(), receiverUser_.getPort(), message_);
                            System.out.println("Message sent to: " + receiverUsername_);
                        } else {
                            System.out.println("User not found for username: " + receiverUsername_);
                        }
                    }
                    break;
                case 4:
                    String identificateurAmi = jsonMsg.getString("identificateur");
                    String ami = jsonMsg.getString("ami");

                    // Appel de la méthode ajouterAmi pour ajouter l'ami dans la base de données
                    ajouterAmi(identificateurAmi, ami);
                    break;
            }
            ds.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


    public void envoyer(String receiverAddressIp, int receiverPort, String message) throws IOException {
        InetAddress clientAddress = InetAddress.getByName(receiverAddressIp);
        DatagramSocket socket = new DatagramSocket(); // Create a new DatagramSocket for sending

        byte[] tampon = message.getBytes();
        DatagramPacket out = new DatagramPacket(tampon, tampon.length, clientAddress, receiverPort);
        socket.send(out);

        socket.close(); // Close the socket after sending
    }
    public void ajouterAmi(String identificateur, String ami) {
        final String URL = "jdbc:mysql://localhost:3306/chat";
        final String UTILISATEUR = "root";
        final String MOT_DE_PASSE = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE)) {
                String sql = "INSERT INTO ami (utilisateur, ami) VALUES (?, ?)";
                try (PreparedStatement psInsert = con.prepareStatement(sql)) {
                    psInsert.setString(1, identificateur);
                    psInsert.setString(2, ami);
                    psInsert.executeUpdate();

                    System.out.println("Données insérées dans la table ami avec succès !");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}




package Client;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnexionBD {

    private static Connection connection;

    public static Connection getDB() {
        String url = "jdbc:mysql://localhost:3306/user";
        String utilisateur = "root";
        String motDePasse = "";
        try {
            // Chargement du driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connexion à la base de données
            connection = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion réussie à la base de données !");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur de chargement du driver JDBC : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }

        return connection;
    }
}
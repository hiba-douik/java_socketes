package Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class amiDAO {
    private Connection connection;

    public amiDAO(Connection connection) {
        this.connection = connection;
    }

    public void ajouterAmi(String utilisateur, String ami) {
        String query = "INSERT INTO ami (utilisateur, ami) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utilisateur);
            statement.setString(2, ami);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Ami ajouté avec succès.");
            } else {
                System.out.println("Impossible d'ajouter l'ami.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

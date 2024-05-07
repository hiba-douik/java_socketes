package Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User getUserByUsername(String username) {
        String query = "SELECT * FROM user WHERE username = ?";
        User user = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String addressIp = resultSet.getString("addressIp");
                int port = resultSet.getInt("port");
                user = new User(id, username, addressIp, port);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void insertUser(String username, String addressIp, int port) {
        String query = "INSERT INTO user (username, addressIp, port) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, addressIp);
            statement.setInt(3, port);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User inserted successfully.");
            } else {
                System.out.println("Failed to insert user.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
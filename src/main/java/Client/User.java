package Client;

public class User {
    private int id;
    private String username;
    private String addressIp;
    private int port;

    public User(int id, String username, String addressIp, int port) {
        this.id = id;
        this.username = username;
        this.addressIp = addressIp;
        this.port = port;
    }
    // Getters and setters for the class properties

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddressIp() {
        return addressIp;
    }

    public void setAddressIp(String addressIp) {
        this.addressIp = addressIp;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", addressIp='" + addressIp + '\'' +
                ", port=" + port +
                '}';
    }
}
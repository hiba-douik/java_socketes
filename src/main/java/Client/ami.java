package Client;

public class ami {
    private int id;
    private String utilisateur;
    private String ami;

    public ami(int id, String utilisateur, String ami) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.ami = ami;
    }

    // Getters and setters for the class properties

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getAmi() {
        return ami;
    }

    public void setAmi(String ami) {
        this.ami = ami;
    }

    @Override
    public String toString() {
        return "Ami{" +
                "id=" + id +
                ", utilisateur='" + utilisateur + '\'' +
                ", ami='" + ami + '\'' +
                '}';
    }
}

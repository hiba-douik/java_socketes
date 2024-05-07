package Client;

public class ServeurTest {
    public static void main(String[] args) {
        Serveur serveur = new Serveur(8000,8081);
        serveur.reception();
    }
}

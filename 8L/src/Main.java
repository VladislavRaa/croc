import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String film = "/Users/vladislavraa/croc/8L/films.txt";
        String users = "/Users/vladislavraa/croc/8L/users.txt";
        PlacesForKisses plc = new PlacesForKisses(film, users);
        plc.debug(new User("1,4,2"));
    }
}

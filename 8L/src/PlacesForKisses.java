import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlacesForKisses {
    private final static int TOP = 3;
    private double ratingUP = 100;
    private double ratingDOWN = 90;
    private static HashMap<String, String> allFilms = new HashMap<>();
    private static ArrayList<User> users = new ArrayList<User>();
    private static HashMap<String, Integer> raitingList = new HashMap<>();

    public PlacesForKisses(String filmsList, String usersList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filmsList))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tempString = line.split(",");
                allFilms.put(tempString[0], tempString[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(usersList))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(new User(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setCurrentScore(User currentUser) {
        for (User someUser : users) {
            someUser.setScore(calcByScore(someUser, currentUser));
        }
    }

    public void debug(User user) {
        setCurrentScore(user);
        sortUsersByScore();
        filterByScore(user);
    }

    private double calcByScore(User someUser, User currentUser) {
        double score = 0;
        final double percent = 100.0 / currentUser.getAmount();
        for (int i = 0; i < someUser.getAmount(); i++) {
            for (int j = 0; j < currentUser.getAmount(); j++) {
                if (currentUser.equals(someUser)) {
                    continue;
                }
                if (currentUser.getWatched().get(j).equals(someUser.getWatched().get(i))) {
                    score += percent;
                }
            }
        }
        return score;
    }

    private void filterByScore(User user) {
        for (User someUser : users) {
            for (String i : someUser.getWatched()) {
                if (someUser.getScore() >= ratingDOWN && someUser.getScore() <= ratingUP) {
                    if (raitingList.containsKey(i)) {
                        raitingList.put(i, raitingList.get(i) + 1);
                    } else {
                        raitingList.put(i, 1);
                    }
                }
            }
        }
        for (String i : user.getWatched()) {
            raitingList.remove(i);
        }
        if (raitingList.size() < TOP && ratingDOWN >= 0) {
            ratingDOWN -= 10;
            ratingUP -= 10;
            filterByScore(user);
        } else {
            for (Map.Entry<String, Integer> entry : raitingList.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }

    private void sortUsersByScore() {
        users.sort((o1, o2) -> Double.compare(o2.getScore(), o1.getScore()));
        for (User i : users) {
            System.out.println(i.getScore() + " : " + i.getWatched().toString());
        }
    }
}

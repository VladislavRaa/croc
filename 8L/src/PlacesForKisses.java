import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PlacesForKisses {
    private final static int TOP = 3;
    private double ratingUP = 100;
    private double ratingDOWN = 90;
    private static HashMap<String, String> allFilms = new HashMap<>();
    private static ArrayList<User> users = new ArrayList<User>();
    private static HashMap<String, Integer> raitingList = new HashMap<>();
    private static Iterator iter = users.iterator();
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
        for (User u : users) {
            System.out.println(u.getScore() + " " + u.getWatched().toString());
        }
        filterByScore(user);
        System.out.println(raitingList.toString());
    }

    private double calcByScore(User someUser, User currentUser) {
        double score = 0;
        final double percent = 100.0 / currentUser.getAmount();
        for (String i : someUser.getWatched()) {
            for (String j : currentUser.getWatched()) {
                if (currentUser.equals(someUser)) {
                    continue;
                }
                if (i.equals(j)) {
                    score += percent;
                }
            }
        }
        return score;
    }

    private void filterByScore(User user) {

        for (User someUser : users) {
            for (String i : someUser.getWatched()) {
                if (someUser.getScore() >= ratingDOWN && someUser.getScore() <= ratingUP && !user.getWatched().contains(i)) {
                    if (raitingList.containsKey(i)) {
                        raitingList.put(i, raitingList.get(i) + 1);
                    } else {
                        raitingList.put(i, 1);
                    }
                }
            }
        }

        if (raitingList.size() < TOP && ratingDOWN >= 0) {
            ratingDOWN -= 10;
            ratingUP -= 10;
            //filterByScore(user);
        } else {
            for (Map.Entry<String, Integer> entry : raitingList.entrySet()) {
                //System.out.println(entry.getKey() + " " + entry.getValue());
            }

        }
    }

    private void sortUsersByScore() {
        users.sort((o1, o2) -> Double.compare(o2.getScore(), o1.getScore()));
    }
}

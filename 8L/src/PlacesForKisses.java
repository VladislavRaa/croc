import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class PlacesForKisses {
    private final static int TOP = 3;
    private double ratingDOWN = 90;
    private static HashMap<String, String> allFilms = new HashMap<>();
    private static ArrayList<User> users = new ArrayList<User>();
    private static HashMap<String, Integer> raitingList = new HashMap<>();
    private int index = 0;

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

    public StringBuffer runner(User user) {
        setCurrentScore(user);
        sortUsersByScore();
        filterByScore(user);
        return getResultByDesc();
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
        for (int i = index; i < users.size(); i++) {
            for (String elemOfWatched : users.get(i).getWatched()) {
                if (users.get(i).getScore() >= ratingDOWN && !user.getWatched().contains(elemOfWatched)) {
                    if (raitingList.containsKey(elemOfWatched)) {
                        raitingList.put(elemOfWatched, raitingList.get(elemOfWatched) + 1);
                    } else {
                        raitingList.put(elemOfWatched, 1);
                    }
                    index++;
                }
            }
        }
        if (raitingList.size() <= TOP && ratingDOWN >= 0) {
            ratingDOWN -= 10;
            filterByScore(user);
        }
    }

    private StringBuffer getResultByDesc() {
        ArrayList<Integer> byTotalRating = new ArrayList<>(raitingList.values());
        byTotalRating.sort((o1, o2) -> Integer.compare(o2, o1));
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < byTotalRating.size() && i < TOP; i++) {
            result.append(getKeyFromValue(raitingList, byTotalRating.get(i))).append("\n");
        }
        return result;
    }

    private void sortUsersByScore() {
        users.sort((o1, o2) -> Double.compare(o2.getScore(), o1.getScore()));
    }

    private static String getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                String resultString = o.toString();
                hm.remove(o);
                return resultString;
            }
        }
        return null;
    }
}

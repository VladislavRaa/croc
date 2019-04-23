import java.util.ArrayList;
import java.util.Objects;

public class User {
    private ArrayList<String> watched;
    private double score;

    User(String films) {
        watched = new ArrayList<>();
        for (String film : films.split(",")) {
            watched.add(film);
        }
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return this.score;
    }

    public int getAmount() {
        return watched.size();
    }

    public ArrayList<String> getWatched() {
        return watched;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(watched, user.watched);
    }

    @Override
    public int hashCode() {
        return Objects.hash(watched);
    }
}

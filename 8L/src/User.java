import java.util.HashSet;
import java.util.Objects;

public class User {
    private HashSet<String> watched = new HashSet<String>();
    private double score;
    User(String films) {
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

    public HashSet<String> getWatched() {
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

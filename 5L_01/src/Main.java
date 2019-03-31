import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<String> blackList = new HashSet<>();
        String[] defaultBlackList = {"java", "c++", "python", "golang", "c"};
        Collections.addAll(blackList, defaultBlackList);
        ArrayList<String> comments = new ArrayList<>();

        comments.add("1: it's first comment");
        comments.add("2: it's new comment with C++");
        comments.add("3: it's new comment about politics");
        comments.add("4: it's new about feminism");
        comments.add("5: it's new comment about Russia");
        comments.add("6: it's new comment about python and C");
        comments.add("7: it's new comment about Golang");

        UserComments fltr = new UserComments();
        fltr.filterComments(comments, blackList);

        for(String i: comments) {
            System.out.println(i);
        }
    }
}

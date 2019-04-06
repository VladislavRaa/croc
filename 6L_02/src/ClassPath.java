import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class ClassPath {
    private ArrayDeque<String> resultPath = new ArrayDeque<String>();

    public String getPath(String path) {
        String[] tempString = path.split("/");
        for (String i : tempString) {
            if (i.equals(".")) {
                continue;
            } else if (i.equals("..")) {
                resultPath.poll();
            } else {
                resultPath.push(i);
            }
        }
        StringBuilder res = new StringBuilder();
        res.append("../");
        while (!resultPath.isEmpty()) {
            String next = resultPath.poll();
            res.append(next + "/");
        }
        return res.toString();
    }
}

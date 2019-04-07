import java.util.ArrayDeque;

public class ClassPath {
    private ArrayDeque<String> resultPath = new ArrayDeque<String>();
    public String getPath(String path) {
        String[] tempString = path.split("/");
        for (String i : tempString) {
            switch (i) {
                case ".":
                    continue;
                case "..":
                    if (resultPath.isEmpty()) {
                        resultPath.push("..");
                    } else {
                        resultPath.pop();
                    } break;
                default:
                    resultPath.push(i);
                    break;
            }
        }
        StringBuilder result = new StringBuilder();
        while (!resultPath.isEmpty()) {
            String next = resultPath.poll();
            result.insert(0, next + "/");
        }
        return result.toString();
    }
}

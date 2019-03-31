import java.util.*;

public final class UserComments implements BlackListFilter {
    public void filterComments(List<String> comments, Set<String> blackList) {
        String tempStr;
        final String patern = "\\p{P}?[ \\t\\n\\r]+";
        Iterator iterator = comments.iterator();
        while (iterator.hasNext()) {
            tempStr = iterator.next().toString();
            for (String i : tempStr.split(patern)) {

                if (blackList.contains(i.toLowerCase())) {
                    iterator.remove();
                    break;
                }

            }
        }
    }
}

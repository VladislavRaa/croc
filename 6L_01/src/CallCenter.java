import java.util.*;

public class CallCenter{


    public void countLog(ArrayList<String> log) { //1,2 oper
        String[] tempString;
        Integer tempTotalTime;
        Integer greatestTotalTime = 0;
        String[] tempTime ;
        HashMap<String, String[]> operators = new HashMap<>();//operator 1,2

        for (String operator : log) {
            tempString = operator.split(",");
            greatestTotalTime = Integer.valueOf(tempString[1]) - Integer.valueOf(tempString[0]);
            if (operators.containsKey(tempString[2])) {
                tempTime = operators.get(tempString[2]);
                tempTotalTime = Integer.valueOf(tempTime[1]) - Integer.valueOf(tempTime[0]);

                if (tempTotalTime < greatestTotalTime) {
                    operators.put(tempString[2], new String[]{tempString[0], tempString[1], String.valueOf(Integer.valueOf(tempString[1]) - Integer.valueOf(tempString[0]))});
                }
            } else {
                operators.put(tempString[2], new String[]{tempString[0], tempString[1], String.valueOf(Integer.valueOf(tempString[1]) - Integer.valueOf(tempString[0]))});
            }
        }
        ArrayList<String[]> byTotalTime = new ArrayList<>(operators.values());

        //byTotalTime.sort(Collections.reverseOrder());
       /* byTotalTime.sort((obj1, obj2) -> {
            int one = Integer.parseInt(obj1[0]);
            int two = Integer.parseInt(obj2[0]);
            if(one > two) {
                return one;
            }else {
                return two;
            }
        });*/
        byTotalTime.sort((o1, o2) -> {
            int one = Integer.parseInt(o1[2]);
            int two = Integer.parseInt(o2[2]);
            return Integer.compare(two, one);

        });
        for (int i = 0; i<3; i++){
            System.out.println(getKeyFromValue(operators, byTotalTime.get(i)));
        }
    }

   private Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
}
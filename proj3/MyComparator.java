import java.util.Comparator;
import java.util.PriorityQueue;

public class MyComparator implements Comparator<String>{
    @Override
    public int compare(String x, String y)   {
    TST ts = new TST();
    if ((Double)ts.get(y) > (Double)ts.get(x)){
    	return 1;
    }
    if ((Double)ts.get(y) < (Double)ts.get(x)){
    	return -1;
    }
    return 0;
    }
}

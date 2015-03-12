package ngordnet;

public class WordLengthProcessor implements YearlyRecordProcessor {
    public double process(YearlyRecord yearlyRecord) {
        Iterator<String> xxx = yearlyRecord.words.iterator();
        int up = 0;
        int down = 0;
        while (xxx.hasNext()) {
            String in2 = xxx.next();
            up += in2.length() * yearlyRecord.count(in2);
            down += yearlyRecord.count(in2);
        }
        double result = up / down;
        return result;
    }

}

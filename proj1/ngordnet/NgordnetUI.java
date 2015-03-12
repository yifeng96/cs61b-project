package ngordnet;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;

/**
 * Provides a simple user interface for exploring WordNet and NGram data.
 * 
 * @author [yournamehere mcjones]
 */
public class NgordnetUI {
    public static void main(String[] args) {
        In in = new In("./ngordnet/ngordnetui.config");
        System.out.println("Reading ngordnetui.config...");

        String wordFile = in.readString();
        String countFile = in.readString();
        String synsetFile = in.readString();
        String hyponymFile = in.readString();
        System.out.println("\nBased on ngordnetui.config, using the following: "
                           + wordFile + ", " + countFile + ", " + synsetFile +
                           ", and " + hyponymFile + ".");

        System.out.println("\nFor tips on implementing NgordnetUI, see ExampleUI.java.");
        WordNet wn=new WordNet(synsetFile,hyponymFile);
        NGramMap ngm= new NGramMap(wordFile,countFile);
        while (true) {
            System.out.print("> ");
            String line = StdIn.readLine();
            String[] rawTokens = line.split(" ");
            String command = rawTokens[0];
            String[] tokens = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
            switch (command) {
                case "quit": 
                    return;
                case "help":
                    In in = new In("help.txt");
                    String helpStr = in.readAll();
                    System.out.println(helpStr);
                    break;  
                case "range": 
                    int startDate = Integer.parseInt(tokens[0]); 
                    int endDate = Integer.parseInt(tokens[1]);
                    System.out.println("Start date: " + startDate);
                    System.out.println("End date: " + endDate);
                    break;
                case "count": 
                    int count= ngm.countInYear(tokens[0],tokens[1])
                    System.out.println(count);
                case "hyponyms":
                    System.out.println(wn.hyponyms(tokens[0]));
                case "history":
                      for (int i =0;i<tokens.length ;i++ ) {
                        plotWeightHistory(ngm,tokens[i],startDate,endDate);
                      }
                case "hypohist":
                      plotCategoryWeights(ngm,wn,tokens,startDate,endDate);
                case "wordlength":
                      plotProcessedHistory(ngm,startDate,endDate,yrp);
                case "zipf year":
                      plotZipfsLaw(ngm,)
                default:
                    System.out.println("Invalid command.");  
                    break;
            }
        }
    }
}

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.Collections;
import java.util.*;


public class Gitlet implements Serializable {
    
private static int commitid;
static HashMap <Integer,Node> committree;
static int currentcommit;
static HashSet<File> remove;
static HashMap<String,Set<Integer>> mass;
private static String bra;
static HashMap<String,Set<Integer>>bragroup;
Object[] saved;





    public static void main(String[] args) {
    
    Gitlet git = new Gitlet();

    Scanner in = new Scanner(System.in);
    Object[] saved = null;
    

    try
      {
         FileInputStream fileIn = new FileInputStream(".gitlet/gitlet.ser");
         ObjectInputStream inn = new ObjectInputStream(fileIn);
         saved = (Object[]) inn.readObject();
         inn.close();
         fileIn.close();
        commitid = (int)saved[0];
        
         committree = (HashMap<Integer,Node>)saved[1];
         currentcommit = (int) saved[2];
         remove=(HashSet<File>) saved[3] ;
        mass=(HashMap<String,Set<Integer>>)saved[4] ;
         bra = (String) saved[5];
       bragroup = (HashMap<String,Set<Integer>>)saved[6] ;  
        
      }catch(IOException i)
      {
         ;
         
      }catch(ClassNotFoundException c)
      {
         System.out.println("Employee class not found");
         c.printStackTrace();
         
      }
      catch (NullPointerException a)
      {
        a.printStackTrace();      }
        


        
        
            String[] tokens = new String[args.length - 1];
            System.arraycopy(args, 1, tokens, 0, args.length - 1);
            
            String command = args[0];
            
            switch (command) {
                case "init":
                git.init();
               
                git.save();
                
                
                break; 
                case "add":
                git.add(tokens[0]);
                git.save();
                break;
                case "commit":
                if (args.length<=1){
                    System.out.println( "Please enter a commit message");
                }
                else{
                    git.commit(tokens[0]);
                }
                git.save();
                break;
                case "rm" :
                git.remove(tokens[0]);
                git.save();
                break;
                case "log" :
                git.log();
                git.save();
                break;
                case "global-log":
                git.globallog();
                git.save();
                break;
                case "find":
                git.find(tokens[0]);
                git.save();
                break;
                case "branch":
                git.branch(tokens[0]);
                git.save();
                break;
                case "status":
                git.status();
                git.save();
                break;
                case "checkout":
                
                System.out.println("Warning: The command you entered may alter the files in your working directory. Uncommitted changes may be lost. Are you sure you want to continue? (yes/no)");
                String s = in.nextLine();
                
                if (s.equals("yes")) {
                    
                   if (args.length<=2){
                    git.checkout(tokens[0]);
                }
                else{git.checkouts(Integer.parseInt(tokens[0]),tokens[1]);  
                }
                }
                git.save();
                break;
                case "rm-branch":
                git.rmb(tokens[0]);
                git.save();
                break;
                case "reset":
                git.reset(Integer.parseInt(args[1]));
                git.save();
                break;
                case "merge":
                System.out.println("Warning: The command you entered may alter the files in your working directory. Uncommitted changes may be lost. Are you sure you want to continue? (yes/no)");
                String k = in.nextLine();
                if (k .equals("yes")) {
                git.merge (args[1]);
            }
            git.save();
            break;
                case "rebase":
                System.out.println("Warning: The command you entered may alter the files in your working directory. Uncommitted changes may be lost. Are you sure you want to continue? (yes/no)");
                String ok = in.nextLine();
                if (ok .equals("yes")) {
                git.rebase (args[1]);
            }   
            git.save();
            break;
                case "i-rebase":
                System.out.println("Warning: The command you entered may alter the files in your working directory. Uncommitted changes may be lost. Are you sure you want to continue? (yes/no)");
                String okk = in.nextLine();
                if (okk .equals("yes")) {
                git.irebase (args[1]);
            }
            git.save();
                break;

			default:
				break;


           
    }
     
}
    
    public void save (){
        saved = new Object[]{
            commitid,committree,currentcommit,remove,mass,bra,bragroup
        };

                try
      {

         FileOutputStream fileOut =
         new FileOutputStream(".gitlet/gitlet.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(saved);
         out.close();
         fileOut.close();
      }catch(IOException i)
      {
          i.printStackTrace();
      }
    }
    public void save2 (){

                try
      {

         FileOutputStream fileOut =
         new FileOutputStream(".gitlet/gitlet2.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(commitid);
         out.close();
         fileOut.close();
      }catch(IOException i)
      {
          i.printStackTrace();
      }
    }
    
    
    public void init (){
        File a = new File (".gitlet/commit");
        if (a.exists()) {
            System.out.println("A gitlet version control system already exists in the current directory");
            return;
        }
        committree = new HashMap();
        remove = new HashSet();
        mass = new HashMap();
        bra = "master";
        bragroup = new HashMap();
        commitid = 0;
        currentcommit = 0;
        a.mkdirs();
        File c = new File (".gitlet/commited/");
        c.mkdirs();
        Set<Integer> newvalue = new HashSet();
        newvalue.add(0);
        mass.put("initial commit", newvalue);
        Set<Integer> newvalue1 = new HashSet();
        newvalue1.add(0);
        bragroup.put(bra, newvalue1);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());    
      
        committree.put (commitid,new Node (commitid,currentcommit,"initial commit",date,bra));
        String id =  Integer.toString(commitid);
        File path = new File(".gitlet/commited/"+id);
        path.mkdirs();       

        

}
    


    public void add (String filename){
    	File a = new File (filename);
        
        if (!a.exists()) {
            System.out.println("File does not exist");
            return;
        }
        String id =  Integer.toString(commitid);
        File test = new File (".gitlet/commited/"+id+"/"+filename);
        if (test.exists()){
            try{
            if (Arrays.equals(Files.readAllBytes(a.toPath()),Files.readAllBytes(test.toPath()))){
                System.out.println("File has not been modified since the last commit");
                return;
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
}
        String path= ".gitlet/commit/"+ new File(filename).getName() ;

        File b = new File (path);
        try{
        if (!b.getParentFile().exists()) {
			b.getParentFile().mkdirs();
		}
       
        Files.copy(a.toPath(),b.toPath());
    }
     catch (IOException e){
        ;
     }   


        save();
    }

    public void commit(String message ) {
        File dir = new File(".gitlet/commit");
        File[] files = dir.listFiles();
        if (files.length == 0&&remove.size()==0 ) {
            System.out.println("No changes added to the commit");
            return;
        }
        commitid+=1;        

        if (mass.containsKey(message)) {
                    mass.get(message).add(commitid);
                } else {
                    Set<Integer> newvalue = new HashSet();
                    newvalue.add(commitid);
                    mass.put(message, newvalue);
                }
        if (bragroup.containsKey(bra)) {
                    bragroup.get(bra).add(commitid);
                } else {
                    Set<Integer> newvalue1 = new HashSet();
                    newvalue1.add(commitid);
                    bragroup.put(bra, newvalue1);
                }

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());    
      
        committree.put (commitid,new Node (commitid,currentcommit,message,date,bra));
        String id =  Integer.toString(commitid);
        File path = new File(".gitlet/commited/"+id);
        path.mkdirs();
        String lastid =  Integer.toString(currentcommit);
        File lastdir = new File(".gitlet/commited/"+lastid);
        try{
        for(File file : lastdir.listFiles()) {
            if (!remove.contains(file)) {
                
                Files.copy(file.toPath(),
        (new File(path +"/"+ file.getName())).toPath(),
        StandardCopyOption.REPLACE_EXISTING);
            }

            }
       
}          catch (NullPointerException a)
      {
        a.printStackTrace();      }

            catch (IOException e){
                e.printStackTrace();
            }


        for(File file : files) {
        try{
        Files.copy(file.toPath(),
        (new File(path + "/" + file.getName())).toPath(),
        StandardCopyOption.REPLACE_EXISTING);
    }
    catch (IOException e){
        e.printStackTrace();
    }
}
        for(File file: dir.listFiles()){ 
            file.delete();
    }
    currentcommit = commitid;
    remove.clear();
}
    private class Node implements Serializable{
        
    private int commitid;
    int parentid;
    String message;
    String time;
    String bra;






        public Node(int commitid, int parentid, String message,String time, String bra){
            this.commitid = commitid;
            this.parentid = parentid;
            this.message = message;
            this.time = time;
            this.bra = bra;
            
        }
        
    }

    public void remove (String name) {
        File a = new File (".gitlet/commit/"+ new File(name).getName());
        String id =  Integer.toString(commitid);
        File b = new File (".gitlet/commited/"+id+"/"+new File(name).getName());
        if (!a.exists()&&!b.exists()) {
            System.out.println("No reason to remove the file");
            return;
        }
        if (a.exists()) {
            a.delete();
        }
        if (b.exists()) {
            remove.add(b);
        }
    }
    public void log () {
        int i = currentcommit;
        while (i > 0){
            System.out.println("====");
            String id =  Integer.toString(i);
            System.out.println("Commit "+id+".");
            Node node = committree.get(i);
            System.out.println(node.time);
            System.out.println(node.message);
            System.out.println("");
            i = node.parentid;

        }
            System.out.println("====");
            System.out.println("Commit 0.");
            Node node = committree.get(0);
            System.out.println(node.time);
            System.out.println(node.message);
    }
    public void globallog () {
        int i = commitid;
        while (i > 0){
            System.out.println("====");
            String id =  Integer.toString(i);
            System.out.println("Commit "+id+".");
            Node node = committree.get(i);
            System.out.println(node.time);
            System.out.println(node.message);
            System.out.println("");
            i = i-1;
        }
        System.out.println("====");
            System.out.println("Commit 0.");
            Node node = committree.get(0);
            System.out.println(node.time);
            System.out.println(node.message);
        }    
    public void find (String message){
        if (!mass.containsKey(message)) {
            System.out.println("Found no commit with that message");
            return;
        }
        for(int i : mass.get(message) ){
            System.out.println(i);
        }
    }
    public void branch(String name){
        if (bragroup.containsKey(name)) {
            System.out.println("A branch with that name already exists");
            return ;
        }
        Set<Integer> newvalue = new HashSet();
        
        int i = currentcommit;
        while (i > 0){
            newvalue.add(i);
            i = committree.get(i).parentid;
        }                   
        newvalue.add(0); 
        bragroup.put(name, newvalue);
    }
    public void status(){
        System.out.println("=== Branches ===");
        for (String key:bragroup.keySet()){
            if (key.equals (bra)) {
                System.out.println("*"+key);
            }
            else{
                System.out.println(key);
            }        
        }
        System.out.println(" ");
        System.out.println("=== Staged Files ===");
        File dir = new File(".gitlet/commit");
        for(File file: dir.listFiles()){ 
            System.out.println(file.getName());
        }    
        System.out.println(" ");
        System.out.println("=== Files Marked for Removal ===");
        for (File a: remove){
            System.out.println(a.getName());
        }
    }
    public void checkout(String name){
        String id =  Integer.toString(commitid);
        File path = new File(".gitlet/commited/"+id+"/"+new File(name).getName());
        
        if (!bragroup.containsKey(name)&&!path.exists()) {
            System.out.println("File does not exist in the most recent commit, or no such branch exists");
            return;
        }
        if (name.equals(bra)) {
            System.out.println("No need to checkout the current branch");
            return;
        }
        if (bragroup.containsKey(name)){
            int x = Collections.max(bragroup.get(name));
            String lastid =  Integer.toString(x);
            File lastdir = new File(".gitlet/commited/"+lastid);            
            for(File file : lastdir.listFiles()) {
        try{
        Files.copy(file.toPath(),
        (new File(file.getName())).toPath(),
        StandardCopyOption.REPLACE_EXISTING);
    }
    catch (IOException e){
        e.printStackTrace();
    }
}
    bra = name;
    currentcommit = x;
        }
        else{
            
            int x = Collections.max(bragroup.get(bra));
            String lastid =  Integer.toString(x);
            File last = new File(".gitlet/commited/"+lastid+"/"+new File(name).getName());       
          
            
        try{
        Files.copy(last.toPath(),
        new File(name).toPath(),
        StandardCopyOption.REPLACE_EXISTING);
    }
    catch (IOException e){
        e.printStackTrace();
    }
    currentcommit = x;
    }
}
    public void checkouts (int id, String file){
        String ids = Integer.toString(id);
        if (!new File(".gitlet/commited/"+ids).exists()) {
            System.out.println("No commit with that id exists");
            return;
        }
        if (!new File(".gitlet/commited/"+ids+"/"+file).exists()){
            System.out.println("File does not exist in that commit");
        }            
        File last = new File(".gitlet/commited/"+ids+"/"+file);                       
        try{
        Files.copy(last.toPath(),
        new File(file).toPath(),
        StandardCopyOption.REPLACE_EXISTING);
    }
    catch (IOException e){
        e.printStackTrace();
    }
    currentcommit = id;
    }

    public void rmb(String name){
        if (!bragroup.containsKey(name)) {
            System.out.println("A branch with that name does not exist");
            return;
        }

        if (bra.equals(name)) {
            System.out.println("Cannot remove the current branch");
            return;
        }
        if (name.equals("master")) {
            System.out.println("Cannot remove the master branch");
            return;
        }
        bragroup.remove(name);

    }

    public void reset(int id){
            String ids = Integer.toString(id);
        if (!new File(".gitlet/commited/"+ids).exists()) {
            System.out.println("No commit with that id exists");
            return;
        }
        String lastid =  Integer.toString(id);
        File lastdir = new File(".gitlet/commited/"+lastid);            
        for(File file : lastdir.listFiles()) {
        try{
        Files.copy(file.toPath(),
        (new File(file.getName())).toPath(),
        StandardCopyOption.REPLACE_EXISTING);
    }
    catch (IOException e){
        e.printStackTrace();
    }
    }
    currentcommit = id;
}
    public void merge(String name){
        if (!bragroup.containsKey(name)) {
            System.out.println("A branch with that name does not exist");
            return;
        }

        if (bra.equals(name)) {
            System.out.println("Cannot merge a branch with itself");
            return ;
        }
        Set<Integer> myset = new HashSet();
        for (Integer igr : bragroup.get(bra)) {
        if (bragroup.get(name).contains(igr)) {
        myset.add(igr);
        }
        } 
        int x = Collections.max(myset);

        int y = Collections.max(bragroup.get(bra));
        
        int z = Collections.max(bragroup.get(name));

        String node =  Integer.toString(x);
        String current =  Integer.toString(y);
        String given =  Integer.toString(z);
        try{
        for(File file : new File(".gitlet/commited/"+given).listFiles()){
            if (!new File(".gitlet/commited/"+node+"/"+file.getName()).exists()||!new File(".gitlet/commited/"+current+"/"+file.getName()).exists()) {
                Files.copy(file.toPath(),
                new File(file.getName()).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
            }
        
            
               
                else if (!Arrays.equals(Files.readAllBytes(new File(".gitlet/commited/"+given+"/"+file.getName()).toPath()),Files.readAllBytes(new File(".gitlet/commited/"+node+"/"+file.getName()).toPath()))) {
                    if (Arrays.equals(Files.readAllBytes(new File(".gitlet/commited/"+current+"/"+file.getName()).toPath()),Files.readAllBytes(new File(".gitlet/commited/"+node+"/"+file.getName()).toPath()))) {
                        Files.copy(file.toPath(),
                new File(file.getName()).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
                    }
                    else {
                    File nf = new File(file.getName()+".conflicted");
                        nf.createNewFile();
                        Files.copy(file.toPath(),
                        nf.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
                    }
                }
               }
           }
           catch (IOException e){
        e.printStackTrace();
    }

        }


    

    public void rebase (String name){
        if (!bragroup.containsKey(name)) {
        System.out.println("A branch with that name does not exist");
        return;
        }

    

        if (bra.equals(name)) {
            System.out.println("Cannot rebase a branch onto itself");
            return ;
        }
        int i = currentcommit;
        
        int m = Collections.max(bragroup.get(name));
        while (i > 0){
            if (i==m) {
                System.out.println("Already up-to-date");
                return;
            }
            Node node = committree.get(i);
            i = node.parentid; 

        }
        while (m > 0){
            if (currentcommit==m) {
            for (Integer igr : bragroup.get(name)) {
        if (!bragroup.get(bra).contains(igr)) {
        bragroup.get(bra).add(igr);
                return;
            }
            Node node2 = committree.get(m);
            m = node2.parentid; 

        }}}
        Set<Integer> myset = new HashSet();
        for (Integer igr : bragroup.get(bra)) {
        if (bragroup.get(name).contains(igr)) {
        myset.add(igr);
        }
        } 
        int x = Collections.max(myset);
        int y = Collections.max(bragroup.get(name));
        String node =  Integer.toString(x);
        String given =  Integer.toString(y);
        currentcommit = y;
        List<Integer> myList = new ArrayList();
        for (Integer igr : bragroup.get(bra)) {
        if (!bragroup.get(name).contains(igr)) {
        myList.add(igr);
        }
        } 

        Collections.sort(myList);
        Object [] a = myList.toArray();
        for (int z = 0; z<a.length;z++ ) {
            String mes = committree.get(a[z]).message;
            File lastdir = new File (".gitlet/commited/"+a[z]);
            for(File file : lastdir.listFiles()) {
             {
                try{
                Files.copy(file.toPath(),
        (new File(".gitlet/commit/" + file.getName())).toPath(),
        StandardCopyOption.REPLACE_EXISTING);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            }
       
}
        for (File rm:new File (".gitlet/commited/"+y).listFiles() ) {
            if (!new File(".gitlet/commit/" + rm.getName()).exists()) {
                remove.add(rm);
            }
        }
            commit(mes);
        }

        
}
    public void irebase(String name){
        if (!bragroup.containsKey(name)) {
        System.out.println("A branch with that name does not exist");
        return;
        }

    

        if (bra == name) {
            System.out.println("Cannot rebase a branch onto itself");
            return ;
        }
        int i = currentcommit;
        int m = Collections.max(bragroup.get(name));
        while (i > 0){
            if (i==m) {
                System.out.println("Already up-to-date");
                return;
            }
            Node node = committree.get(i);
            i = node.parentid; 

        }
while (m > 0){
            if (currentcommit==m) {
            for (Integer igr : bragroup.get(name)) {
        if (!bragroup.get(bra).contains(igr)) {
        bragroup.get(bra).add(igr);
                return;
            }
            Node node2 = committree.get(m);
            m = node2.parentid; 

        }}}
        Set<Integer> myset = new HashSet();
        for (Integer igr : bragroup.get(bra)) {
        if (bragroup.get(name).contains(igr)) {
        myset.add(igr);
        }
        } 
        int x = Collections.max(myset);
        int y = Collections.max(bragroup.get(name));
        String node =  Integer.toString(x);
        String given =  Integer.toString(y);
        currentcommit = y;
        List<Integer> myList = new ArrayList();
        for (Integer igr : bragroup.get(bra)) {
        if (!bragroup.get(name).contains(igr)) {
        myList.add(igr);
        }
        } 

        Collections.sort(myList);
        Object [] a = myList.toArray();
        for (int z = 0; z<a.length;z++ ) {
            Scanner in = new Scanner(System.in);
            System.out.println("Currently replaying:");
            System.out.println("====");
            String id =  a[z] +"";
            System.out.println("Commit "+id+".");
            Node nodes = committree.get(a[z]);
            System.out.println(nodes.time);
            System.out.println(nodes.message);
            System.out.println("");
            System.out.println("Would you like to (c)ontinue, (s)kip this commit, or change this commit's (m)essage?");
            String okk = in.nextLine();
            String answer = sup(okk);
            String answer2 = "youknow";
        if (answer.equals("s")){
        if (z==0||z==a.length-1){
            System.out.println("Please enter again.");
            String okkk = in.nextLine();
             answer2 = sup2(okkk);
        }
    }

            if (answer.equals("c")||answer2.equals("c")){

            String mes = committree.get(a[z]).message;
            File lastdir = new File (".gitlet/commited/"+(String)a[z]);
            for(File file : lastdir.listFiles()) {
             {
                try{
                Files.copy(file.toPath(),
        (new File(".gitlet/commit/" + file.getName())).toPath(),
        StandardCopyOption.REPLACE_EXISTING);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            }
       
}
            commit(mes);
        }
        if (answer.equals("m")||answer2.equals("m")) {
        System.out.println("Please enter a new message for this commit.");

        String mes = in.nextLine();
            File lastdir = new File (".gitlet/commited/"+a[z]);
            for(File file : lastdir.listFiles()) {
             {
                try{
                Files.copy(file.toPath(),
        (new File(".gitlet/commit/" + file.getName())).toPath(),
        StandardCopyOption.REPLACE_EXISTING);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            }
       
}
            commit(mes);
    }

    }


    }
    private String sup(String input){
        Scanner in = new Scanner(System.in);
        if (input.equals("c")||input.equals("s")||input.equals("m") ){
            return input;
        }
        System.out.println("Please input again");
        String okk = in.nextLine();
        return sup(okk);
    }
    private String sup2(String input){
        Scanner in = new Scanner(System.in);
        if (input.equals("c")||input.equals("m") ){
            return input;
        }
        System.out.println("Please input again");
        String okk = in.nextLine();
        return sup(okk);
    }
}










/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4 {
    public void Find() {
        URLResource s = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String b = s.asString();  
        b = b.toLowerCase();
        int tag = 0;
        int end = 0;
        String whole = "";
       
        while(tag != -1){
         tag = b.indexOf("youtube", tag +1);  
         if (tag != -1) {
          end = b.indexOf("\"", tag+1);
          whole = b.substring(tag, end);
      
         }
         System.out.println("\""+whole+"\"");
        }
    }
}

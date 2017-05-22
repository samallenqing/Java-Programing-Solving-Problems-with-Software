
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {
        public void main(){
            FileResource f = new FileResource("exportdata.csv");
            CSVParser p = f.getCSVParser();
            String a = countryinfo(p,"Nauru");
            
            p = f.getCSVParser();
            String b = listExportersTwoProducts(p, "cotton", "flowers");
            
            p = f.getCSVParser();
            int c = numberOfExporters(p, "cocoa");
            
            p = f.getCSVParser();
            bigExporters(p, "$999,999,999,999");
            
            System.out.println("\n"+a +"\n");
            System.out.println(b+"\n");
            System.out.println("The number of Countries export the good:" + c);
            
    }
        
        public String countryinfo(CSVParser parser, String q){
            String x = "";
            for(CSVRecord record : parser){
                String a= record.get("Country");
                if (a.contains(q)){
                    String e = record.get("Exports");
                    String v = record.get("Value (dollars)");
                    x = a + ":  " + e + ":  " + v;
                } 
          }
          if (x.isEmpty()){
              x = "Not Fund";
          }
          return x;
    }
       
       public String listExportersTwoProducts(CSVParser parser, 
                                                String exportItem1, String exportItem2){
         String c = "";
           for (CSVRecord r : parser){
               String a = r.get("Exports");
               String b = r.get("Country");
               if (a.contains(exportItem1) && a.contains(exportItem2)){
                   c += b + "\n";
               } 
        }
        if(c.isEmpty()){
         c = "Not Fund";      
        } 
        return c;
    }
    
    public int numberOfExporters (CSVParser parser, String exportItem){
        int n = 0;
        for (CSVRecord r : parser){
            String e = r.get("Exports");
            if (e.contains(exportItem)){
                n++;
            }
        }
        return n;
    }
    
    public void bigExporters (CSVParser parser, String $){
        for (CSVRecord r : parser){
           String m = r.get("Value (dollars)");
           String c = r.get("Country");
           if (m.length() > $.length()){
               System.out.printf("%1$-20s %2$20s\n", c, m);
            }
        }
    }
}

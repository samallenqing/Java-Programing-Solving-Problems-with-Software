
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public String twoOccurrences(String stringa, String stringb){
    int indexa = stringb.indexOf(stringa);
    int c = stringa.length();
    int indexb = 0;
    int count = 0;
     while (indexb  != -1){
        indexb = stringb.indexOf(stringa, indexb + 1) ;
        if (indexb > 0){
        count = count + 1;
        System.out.println(count); 
        }
    }
        if (count >= 2){
            String e = "true";
            return e;
        }
        else{
           String e = "false";
            return e;
        }
    }

    public String lastpart (String stringa, String stringb) {
        int t = stringb.indexOf(stringa);
        int l = stringa.length();
        String c;
        if (t != -1) {
            
           return c = stringb.substring(t+l);
        }
        else {
            
        return   c = stringb;
        }
    }
    
    public void lastpart () {
        String stringa = "tb";
        String stringb = "xxxxxxxxxtabxxxxxx";
        String n = stringa;
        String m = stringb;
        String s = lastpart(n,m);
        System.out.println(s);
    }
    public void test(){
      String stringa = "ab";
      String stringb = "xxxabababxxxxxabbaxxxx";
      String c = twoOccurrences(stringa,stringb);
      System.out.println(c);
    }
}

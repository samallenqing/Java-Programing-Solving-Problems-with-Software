
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int HowMany (String stringA, String stringB){
    int occ = stringB.indexOf(stringA);
    int count = 0;
    int length = stringA.length();
    if (occ == -1){
        return -1;
    }
     while (occ != -1){
         occ = stringB.indexOf(stringA, occ + length);
         count = count +1;
     }
    return count;
  }
  
  
  public void test(){
      String x = "abababaaaab";
      String y = "ab";
      int z = HowMany(y,x);
      System.out.println(z);
    }
}

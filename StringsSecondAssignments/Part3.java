
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class Part3 {
    
  public int FindStopCodon(String DNA, int StartIndex, String stopCodon){
   int StopIndex = DNA.indexOf(stopCodon, StartIndex + 3);
   int length = StopIndex - StartIndex;
   if (length % 3 == 0 ){
    return StopIndex;  
    }
   else {
      StopIndex = DNA.indexOf(stopCodon, StopIndex + 1);
    }
    return -1;
  }


  public String FindGene(String DNA, int next){
    int StartIndex = DNA.indexOf("ATG", next);
    
    if (StartIndex == -1){
       return"";
    }
    int TAAIndex = FindStopCodon(DNA, StartIndex, "TAA");
    int TGAIndex = FindStopCodon(DNA, StartIndex, "TGA");
    int TAGIndex = FindStopCodon(DNA, StartIndex, "TAG");
    int CurrentIndex = 0;
    if ( TAAIndex == -1 || TGAIndex != -1 && TGAIndex < TAAIndex){
        CurrentIndex = TGAIndex;
    }
    else{
        CurrentIndex = TAAIndex;    
    }
    if (CurrentIndex == -1 || TAGIndex != -1 && TAGIndex < CurrentIndex){
       CurrentIndex = TAGIndex; 
    }
    if (CurrentIndex == -1){
       return"";  
    }
    return  DNA.substring(StartIndex, CurrentIndex + 3);
    }
    
  public int CountGenes (String DNA){
      int StartIndex = 0;
      int CurrentGeneIndex = 0;
      int n = 0;
      while (true){
          String CurrentGene = FindGene(DNA, StartIndex);
          CurrentGeneIndex = DNA.indexOf("ATG", StartIndex);
          StartIndex = CurrentGeneIndex + CurrentGene.length();
          System.out.println(CurrentGene);
          n = n + 1;
          if ( CurrentGene.isEmpty()){
              break;
        }
    }
    return n-1;
   }
  public void test() {
       FileResource a = new FileResource("gene.txt");  
       int n = 0;
       for (String f : a.lines()){
       n = CountGenes(f);
    }
       System.out.println("There are-----"+ n +"-----genes in this DNA");
  }
}
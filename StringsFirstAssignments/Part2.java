
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

     public String findSimpleGene(String dna, String StartCodon, String StopCodon) {
    
         if (StartCodon != "ATG") {
         int StartIndex  =   dna.indexOf("ATG");
         int StopIndex   =   dna.indexOf("TAA");
         while ( StopIndex != -1) {
    
        if  ((StopIndex - StartIndex) % 3 == 0) {
            return dna.substring(StartIndex, StopIndex + 3);
            
        }
        else {int Length = StopIndex - StartIndex;
          StopIndex =  dna.indexOf(Length, StopIndex + 1);
        }
    }
        }
        else{
            int StartIndex  =   dna.indexOf("atg");
            int StopIndex   =   dna.indexOf("taa");
            while ( StopIndex != -1) {
    
        if  ((StopIndex - StartIndex) % 3 == 0) {
            return dna.substring(StartIndex, StopIndex + 3);
            
        }
        else {int Length = StopIndex - StartIndex;
           StopIndex =  dna.indexOf(Length, StopIndex + 1);
        }
    }
        }
        
    
    return "There is no more gene";
    }

    public void testFindGeneSimple() {
        //             v  v  v  v  v
        String dna = "AATGCCCTAA";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna,"ATG","TAA");
        System.out.println("Gene is " + gene);
        //       v  v  v  v  v  v  v  v
   
    }
}


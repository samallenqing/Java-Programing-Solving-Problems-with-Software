//video Coding StorageResource class in lesson Using the StorageResource Class

/**
 * Write a description of AllGenesStored here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class AllGenesStored {
    public int findStopCodon(String dnaStr,
                             int startIndex, 
                             String stopCodon){                                 
            int currIndex = dnaStr.indexOf(stopCodon,startIndex+3);
            while (currIndex != -1 ) {
               int diff = currIndex - startIndex;
               if (diff % 3  == 0) {
                   return currIndex;
               }
               else {
                   currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
               }
            }
            return -1;
        
    }
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int minIndex = 0;
        if (taaIndex == -1 ||
            (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 ||
            (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex,minIndex + 3);
    }
    public StorageResource getAllGenes(String dna) {
      //create an empty StorageResource, call it geneList
      StorageResource geneList = new StorageResource();
      //Set startIndex to 0
      int startIndex = 0;
      //Repeat the following steps
      while ( true ) {
          //Find the next gene after startIndex
          String currentGene = findGene(dna, startIndex);
          //If no gene was found, leave this loop 
          if (currentGene.isEmpty()) {
              break;
          }
          //Add that gene to geneList
          geneList.add(currentGene);
          //Set startIndex to just past the end of the gene
          startIndex = dna.indexOf(currentGene, startIndex) +
                       currentGene.length();
        }
      //Your answer is geneList
      return geneList;
    }
    public void testOn(String dna) {
        int n = 0; float cg = 0; int m = 0;
        int k = 0; int a = 0; int b = 0;
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()) {
            System.out.println(g);
            n = n + 1;
            cg = cgRatio(g);
            a = g.length();
            if ( a > b){
               b = a; 
            }
            if ( g.length() > 60){
                k = k +1;
            }
            if (cg > 0.35){
              m = m + 1; 
            }
        }
        System.out.println("The number of genes:   " +  n);
        System.out.println("Longer than 60:   " + k);
        System.out.println("cgRatio larger than 0.35     " + m);
        System.out.println("The length of the longest gene is:   " + b);
    }
    public void test() {
        FileResource s = new FileResource("1.txt");
        String f = s.asString();
        testOn(f);

    }
    public float cgRatio(String dna){
         int n = 0; int m = 0; int k = 0; float c = 0; float l = 0; float r = 0;
         float d = 0; float e = 0;
        while ( n !=-1){
          n = dna.indexOf("C", n+1);
          d = d +1;
        }
        while ( m !=-1){
          m = dna.indexOf("G", m+1);
          c = c+1; 
       }
       e = c + d-1;
       l = dna.length();
       r = e/l;
       System.out.println(r);
       return r;
    }
}
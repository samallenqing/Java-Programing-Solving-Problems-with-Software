
/**
 * Write a description of totalbirth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class totalbirth {
    public void totalBirths(){
        int numberofGirls = 0;
        int numberofBoys = 0;
        int totalNames = 0;
        FileResource f = new FileResource();
        for (CSVRecord r : f.getCSVParser(false)){
            String  gender = r.get(1);
            if (gender.contains("F")){
                numberofGirls ++;
            }else{
               numberofBoys ++; 
            }
        }
        totalNames = numberofBoys + numberofGirls;
        System.out.println("Number of girls name =  "+numberofGirls +"\nNumber of boys namme =  " +
                            numberofBoys +"\nTotal number of names =   " + totalNames);
    }
    
    public int getRank(int year, String Name, String gender){
      FileResource f = new FileResource("/Users/qinqing/Desktop/1/us_babynames_by_year/yob" + String.valueOf(year) +".csv");
      int rank = 0;
      for (CSVRecord r : f.getCSVParser(false)){
          String tagName = r.get(0);
          String tagGender = r.get(1);
          if (tagGender.equals(gender)){
              rank ++;
              if(tagName.equals(Name)){
              return rank;
             }
          }
        }
        return -1;
    }
    
    public String getName(int year, String gender, int rank){
        FileResource f = new FileResource("/Users/qinqing/Desktop/1/us_babynames_by_year/yob"+ String.valueOf(year)+".csv");
        String name = "";
        int tagRank = 0;
        for (CSVRecord r : f.getCSVParser(false)){
            if (r.get(1).equals(gender)){
                tagRank ++;
                if (tagRank == rank){
                    return r.get(0);
                }
            }
        }
        return "No Name"; 
    }
    
    public void testRank(){
        int rank = getRank(1971, "Frank", "M");
        if (rank != -1){
          System.out.println(rank);  
        }
        else{
          System.out.println("Does not have this name in the file");  
        }
    }
    
    public void testName(){
        String name = getName(1982,"M",450);
        System.out.println(name);
    }
    
    public String whatIsNameInYear(String name, int year, int newyear, String gender){
        FileResource f = new FileResource("/Users/qinqing/Desktop/1/us_babynames_by_year/yob"+ String.valueOf(year)+".csv");
        int rank = 0;
        for (CSVRecord r : f.getCSVParser(false)){
            if (r.get(1).equals(gender)){
                rank ++;
                if ( r.get(0).equals(name)){
                    break;
                }
            }
        }
        
        FileResource ff = new FileResource("/Users/qinqing/Desktop/1/us_babynames_by_year/yob"
                                    + String.valueOf(newyear)+".csv");
        int count = 0;            
        for (CSVRecord rr : ff.getCSVParser(false)){
            if (rr.get(1).equals(gender)){
                count ++;
                if (count == rank){
                   return rr.get(0); 
                }
            }
        }
        return "No Such Name";
    }
    
    public void testWhatName(){
        String name = whatIsNameInYear("Owen", 1974, 2014, "M");
        System.out.println("New name in 2014    " + name);
    }
    
    
    public int yearOfHighestRank(String name, String gender){
        int rank = 1000000;
		int yearHigh = 0;
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()){
			String fileName = f.getName();
			int year = Integer.parseInt(fileName.replaceAll("[\\D]", ""));
			FileResource ff = new FileResource(f);
			int currRank = 0;
			int count = 0;
			for(CSVRecord r : ff.getCSVParser(false)){
				
				if(r.get(1).equals(gender)) {
					count++;		
					if(r.get(0).equals(name)) {
						currRank = count;
						break;
					}
					
				}
				
			}
			
			if(currRank != 0 && currRank < rank){
				rank = currRank;
				yearHigh = year;
			}
		
		}
		
		return yearHigh;
    }
    
    public void testHighestRank(){
        int year = yearOfHighestRank("Mich", "M");
        System.out.println("Highest year is    " + year);
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int fileNumber = 0; double sum = 0; double ave = 0; int lable = 0;
        for (File f: dr.selectedFiles()){
            FileResource ff = new FileResource(f);
            int currentRank = 0;
            //double label = 0; 
            fileNumber ++;
            for(CSVRecord r : ff.getCSVParser(false)){
                if (r.get(1).equals(gender)){
                    currentRank ++;
                    if(r.get(0).equals(name)){
                       sum += currentRank;
                        break;
                    }
                    else{
                        ave = -1;
                }
            }
            
           }
        }
        ave = sum/fileNumber;
        return ave;
    }
    
    public void testgetAverageRank(){
        double ave = getAverageRank("Susan", "F");
        if ( ave != -1){
        System.out.println("Ave rank is    " + ave);
       }
       else{
        System.out.println("No such name in selected files");
       }
    
    }
    
    public int getTotalBirthsRankedHigher(String name, String gender,int year){
      FileResource f = new FileResource("/Users/qinqing/Desktop/1/us_babynames_by_year/yob"+ 
                        String.valueOf(year)+".csv"); 
      int total = 0;  int number = 0; int diff = 0;
      for (CSVRecord r : f.getCSVParser(false)){
          if(r.get(1).equals(gender)){
              number = Integer.parseInt(r.get(2));
              total = total +number;
              if (r.get(0).equals(name)){
                   diff = number;
                   break;
                }
            }
        }
       total = total - diff;
        return total;
    }
    
    public void testgetTotalBirthsRankedHigher(){
        int number = getTotalBirthsRankedHigher("Drew", "M", 1990);
        System.out.println("There are   "+number+"      more people not use this name");
    }
}


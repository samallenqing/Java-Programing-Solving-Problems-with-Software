
/**
 * Write a description of Wheather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;


public class Wheather {
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestHour = null;
        for (CSVRecord current : parser){
            if (coldestHour == null){
                coldestHour = current;
            }
            else{
                double currentTemperature = Double.parseDouble(current.get("TemperatureF"));
                double coldestTemperature = Double.parseDouble(coldestHour.get("TemperatureF"));
               if ( currentTemperature < coldestTemperature){
                   coldestHour = current;
                }
            }
        }
        return coldestHour;
    }
    
    
    public void testColdestHourInFile(){
      FileResource f = new FileResource();
      CSVRecord r = coldestHourInFile(f.getCSVParser());
      String Temperature = r.get("TemperatureF");
      String Time = r.get("DateUTC");
      System.out.printf("%1$-1s %2$5s %3$15s\n","The Coldest temperature and time is:",Temperature, Time );
    }
    
    public String fileWithColdestTemperature(){
        CSVRecord coldestRecord = null;
        DirectoryResource dr = new DirectoryResource();
        String filename = "";

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord record = coldestHourInFile(fr.getCSVParser());
            double recordTemp = Double.parseDouble(record.get("TemperatureF"));
            if (recordTemp == -9999) {
                continue;
            }
            
            if (coldestRecord == null) {
                coldestRecord = record;
                filename = f.getAbsolutePath();
            } else {
                double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));
                if (recordTemp < coldestTemp) {
                    coldestRecord = record;
                    filename = f.getAbsolutePath();
                }
            }

        }
        return filename;
    
    }
    
    public void test(){
      String f = fileWithColdestTemperature();
      System.out.println(f);
      FileResource a = new FileResource(f);
      String r = new File(f).getName();
      CSVRecord h = coldestHourInFile(a.getCSVParser());
      String Te = h.get("TemperatureF");
      String Time = h.get("DateUTC");
      System.out.println("Coldest day was in file:  " + r +"\n");
      System.out.println("Coldest temperature on that day was:  "+ Te+"\n");
      for (CSVRecord w : a.getCSVParser()){
          String e = w.get("TemperatureF");
          String ee = w.get("DateUTC");
          System.out.printf("%1$-5s %2$10s\n",ee,e);
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestH = null;
        for (CSVRecord r : parser){
            if (r.get("Humidity").contains("N/A")){
                continue;
            }
            if (lowestH == null){
                lowestH = r;
            }else{
               double HH = Double.parseDouble(lowestH.get("Humidity"));
               double currentHH = Double.parseDouble(r.get("Humidity"));
               if( HH > currentHH){
                   lowestH = r;
                }
            }
        }
        return lowestH;
    }
    
    public void testLowestHumidityInFile(){
        FileResource f = new FileResource();
        CSVRecord r = lowestHumidityInFile(f.getCSVParser());
        String Time = r.get("DateUTC");
        String Hum = r.get("Humidity");
        System.out.printf("%1$-5s %2$3s %3$3s %4$10s","Lowest Humidity was:",Hum,"at",Time);
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
     DirectoryResource dr = new DirectoryResource();
     CSVRecord lowestH = null;
     for (File f : dr.selectedFiles()){
         FileResource ff = new FileResource (f);
         CSVRecord r = lowestHumidityInFile(ff.getCSVParser());
         if ( lowestH == null){
             lowestH = r;
            }else{
                double currentH = Double.parseDouble(r.get("Humidity"));
                double lowestHH = Double.parseDouble(lowestH.get("Humidity"));
              if( currentH < lowestHH){
                  lowestHH = currentH;
                  lowestH = r;
            }
        }
    }
     return lowestH;
  }

  public void testmanyHH(){
      CSVRecord r =lowestHumidityInManyFiles();
      String Hum = r.get("Humidity");
      String t = r.get("DateUTC");
      System.out.printf("%1$-3s %2$3s %3$4s %4$25s","Lowest Humidity was",Hum,"at",t);
    }
    
  public double averageTemperatureInFile(CSVParser parser){
      int n = 0;
      double s = 0;
     for ( CSVRecord r : parser){
         double t = Double.parseDouble(r.get("TemperatureF"));
          s = s + t;
         n ++;
        }
        double ave = s/n;
        return ave;
  }
  
  public void testAveTem(){
      FileResource f = new FileResource();
      double aveTem = averageTemperatureInFile(f.getCSVParser());
      System.out.println("Average temperature in file is    " + aveTem);
    }
  
  public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
      double sumT = 0;
      int n = 0;
      for(CSVRecord r : parser){
         if (r.get("Humidity").contains("N/A")){
             continue;
            }
            double hh = Double.parseDouble( r.get("Humidity"));
            
         if ( hh > value){
             double t = Double.parseDouble(r.get("TemperatureF"));
             sumT = sumT + t;
             n = n + 1;
            }
        }
        double aveT = sumT/n;
        return aveT;
    }
    
  public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource f = new FileResource();
        CSVParser parser = f.getCSVParser();
        double AveTem = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (Double.isNaN(AveTem)){
            System.out.println("No temperatures with that humidity");
        }
        else{
        System.out.println("Average Temp when high Humidity is  " + AveTem);
    }
    }
}

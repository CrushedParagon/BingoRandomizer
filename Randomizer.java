import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Randomizer{
   private static int COLUMNS;
   private static int ROWS;
   private static int ELEMENTS;
   public static String[] random;
   
   public static int getRows(){
      return ROWS;
   }
   
   public static int getColumns(){
      return COLUMNS;
   }
   
   public static void main(String[] args){
      try{
      Randomizer.readBingo("bingo.txt");
      }catch (Exception e){
         System.out.println(e);
      }
      RandomizerGUI.rowColumnGUI();
      
      
      
   }
   
   
   public static void Next(int sheets){
      COLUMNS=RandomizerGUI.getColumns();
      ROWS=RandomizerGUI.getRows();
      ELEMENTS=COLUMNS*ROWS;
      for(int i=0; i<sheets; i++){
         String[] selected=selectItems(random);
         RandomizerGUI.makeGUI(selected,i);
      }
   }
   
   public static void readBingo(String filename) throws FileNotFoundException,IOException{
      BufferedReader in=new BufferedReader(new FileReader(filename));
      String line=in.readLine();
      int numLines=Integer.parseInt(line);
      random=new String[numLines];
      for(int i=0; i<numLines; i++){
         line=in.readLine().trim();
         random[i]=line;
      }
      in.close();
   }
   
   public static String[] selectItems(String[] items){
      String[] toReturn=new String[ELEMENTS];
      String[] duplicate=new String[items.length];
      for(int i=0; i<items.length; i++){
         duplicate[i]=items[i];
      }
      for(int i=0; i<ELEMENTS; i++){
         int random=(int)(Math.random()*(duplicate.length-i));
         toReturn[i]=duplicate[random];
         for(int j=random; j<(items.length-i-1); j++){
            duplicate[j]=duplicate[j+1];
         }
      }
      return toReturn;
   }
}
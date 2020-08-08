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
   
   
   public static void Next(){
      COLUMNS=RandomizerGUI.getColumns();
      ROWS=RandomizerGUI.getRows();
      ELEMENTS=COLUMNS*ROWS;
   
      String[] selected=selectItems(random);
      
      RandomizerGUI.makeGUI(selected);
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
   }
   
   public static String[] selectItems(String[] items){
      String[] toReturn=new String[ELEMENTS];
      for(int i=0; i<ELEMENTS; i++){
         int random=(int)(Math.random()*(items.length-i));
         toReturn[i]=items[random];
         for(int j=random; j<(items.length-i-1); j++){
            items[j]=items[j+1];
         }
      }
      return toReturn;
   }
}
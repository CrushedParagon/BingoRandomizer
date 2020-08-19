import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Randomizer{
   
   //variables that define the shape of the bingo sheet
   private static int COLUMNS;
   private static int ROWS;
   private static int ELEMENTS;
   //an array that stores the Strings read in from file
   public static String[] random;
   
   //simple accessor methods that I don't believe ever get used
   public static int getRows(){
      return ROWS;
   }
   
   public static int getColumns(){
      return COLUMNS;
   }
   
   
   public static void main(String[] args){
      //tries to read in the bingo.txt file
      try{
      Randomizer.readBingo("./bingo.txt");
      }catch (Exception e){//catches all exceptions and prints out the message for debug purposes
         System.out.println(e);
      }
      RandomizerGUI.rowColumnGUI();//creates the first GUI
      
      
      
   }
   
   //a method called by the first GUI that creates the second one
   public static void Next(int sheets){
      COLUMNS=RandomizerGUI.getColumns();
      ROWS=RandomizerGUI.getRows();
      ELEMENTS=COLUMNS*ROWS;
      String[][] allSelected=new String[sheets][];
      for(int i=0; i<sheets; i++){
         allSelected[i]=selectItems(random);
         
      }
      RandomizerGUI.makeGUI(allSelected);
   }
   
   //reads in the txt file
   public static void readBingo(String filename) throws FileNotFoundException,IOException{
      BufferedReader in=new BufferedReader(new FileReader(filename));
      String line=in.readLine();
      //the first line should be an integer
      int numLines=Integer.parseInt(line);
      
      random=new String[numLines];//initializes the instance array 
      for(int i=0; i<numLines; i++){//fills in with the contents of the txt file
         line=in.readLine().trim();
         random[i]=line;
      }
      in.close();
   }
   
   //randomly selects ELEMENTS items from items
   public static String[] selectItems(String[] items){
      //creates a new array of ELEMENTS length
      String[] toReturn=new String[ELEMENTS];
      //duplicate random so that we don't damage items
      String[] duplicate=new String[items.length];
      for(int i=0; i<items.length; i++){
         duplicate[i]=items[i];
      }
      for(int i=0; i<ELEMENTS; i++){
         //selects a random int from 0 to duplicate.length-i-1 (since you'll never get duplicate.length-1 with Math.random)
         int random=(int)(Math.random()*(duplicate.length-i));
         toReturn[i]=duplicate[random];//fills in the array that will be returned with a random index from duplicate
         for(int j=random; j<(items.length-i-1); j++){
            duplicate[j]=duplicate[j+1];//shifts elements to create a partially filled array
         }
      }
      return toReturn;
   }
}
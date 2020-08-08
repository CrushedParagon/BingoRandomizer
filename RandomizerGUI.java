import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;

public class RandomizerGUI{
   private static int COLUMNS=3;
   private static int ROWS=3;
   private static int ELEMENTS=9;
   private static JTextField field1;
   private static JTextField field2;
   private static JFrame frame;
   
   public static int getRows(){
      return ROWS;
   }
   
   public static int getColumns(){
      return COLUMNS;
   }
   
   public static void makeGUI(String[] selected){
      GridLayout layout=new GridLayout(ROWS,COLUMNS);
      JFrame frame=new JFrame("Bingo");
      JPanel panel=new JPanel();
      
      panel.setLayout(layout);
      for(int i=0; i<ELEMENTS; i++){
         JTextField area=new JTextField(selected[i]);
         area.setBackground(Color.lightGray);
         area.setHorizontalAlignment(JTextField.CENTER);
         area.setEditable(false);
         double fontSize=1140.0/((double)selected[i].length()*(double)COLUMNS);
         area.setFont(new Font("Courier",Font.BOLD,(int)fontSize-1));
         panel.add(area);
      }
      
      frame.add(panel);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(720,720);
      frame.setVisible(true);
   }
   
   public static void rowColumnGUI(){
      frame=new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel panel=(JPanel)frame.getContentPane();
      GridLayout layout=new GridLayout(1,5);
      panel.setLayout(layout);
      field1=new JTextField("");
      field2=new JTextField("");
      JButton button=new JButton("Enter");
      button.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e){
      int rows=0;
      int columns=0;
      try{
         rows=Integer.parseInt(field1.getText());
         columns=Integer.parseInt(field2.getText());
      }catch (NumberFormatException nf){
         
      }
      if(rows>0 && columns>0){
         ROWS=rows;
         COLUMNS=columns;
         ELEMENTS=ROWS*COLUMNS;
         frame.setVisible(false);
         Randomizer.Next();
      }
      
   }

      });
      panel.add(new JLabel("Rows"));
      panel.add(field1);
      panel.add(new JLabel("Columns"));
      panel.add(field2);
      panel.add(button);
      frame.setSize(500,100);
      frame.setVisible(true);
   }
   
      
   
}
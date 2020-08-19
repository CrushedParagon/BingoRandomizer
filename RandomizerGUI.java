import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.text.*;


public class RandomizerGUI{
   
   private static int COLUMNS=3;//COLUMNS and ROWS default to 3, should be initialized via first GUI
   private static int ROWS=3;
   private static int ELEMENTS=9;
   private static int ITERATIONS=1;
   
  // private static JPanel panel;
   
   public static int getRows(){
      return ROWS;
   }
   
   public static int getColumns(){
      return COLUMNS;
   }
   
   public static void makeGUI(String[][] allSelected){
      //uses grid layout since the sheet is a grid
      GridLayout layout=new GridLayout(ROWS,COLUMNS);
      
      //sets gaps for aesthetic purposes
      layout.setHgap(5);
      layout.setVgap(5);
      
      JFrame frame=new JFrame("Bingo");
      
      JTabbedPane tab=new JTabbedPane();
      
      for(int i=0; i<ITERATIONS; i++){
         String[] selected=allSelected[i];
         
         JPanel panel=new JPanel();
         panel.setFocusable(true);
         
         panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
         panel.getInputMap().put(KeyStroke.getKeyStroke("control S"),"Save");
         panel.getActionMap().put("Save",new SaveAction(i,panel));
         
         panel.setLayout(layout);
         
         for(int j=0; j<ELEMENTS; j++){//draws the elements into the grid
            JTextPane textPane=new JTextPane();//each pane is a JTextPane
            textPane.setFocusable(false);//not focusable, as this made saving difficult
            //various style junk to center the text horizontally (I could not find a way to center vertically)
            StyledDocument doc = textPane.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
         
            textPane.setFont(new Font("Courier",Font.BOLD,15));
            textPane.setText(selected[j]);//adds the text
            textPane.setBackground(Color.lightGray);
            textPane.setEditable(false);//not editable
            panel.add(textPane);//added to panel
         }
         panel.setPreferredSize(new Dimension(1000,1000));
         panel.setSize(1000,1000);
         String tabName="Sheet "+(i+1);
         tab.addTab(tabName,panel);
         
      }
      
      tab.setFocusable(false);

      frame.getContentPane().add(tab);
      frame.setVisible(true);
      

      
      frame.pack();
      
      if(ITERATIONS>1){
         for(int i=0; i<ITERATIONS; i++){
            JComponent panel=(JComponent)(tab.getComponentAt(i));
            saveImage(i,panel);
         }
      }
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      

      }
      
   
   public static void rowColumnGUI(){//the first GUI that appears
      JFrame frame2=new JFrame();
      frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel panel=(JPanel)frame2.getContentPane();//Uses entire frame area
      GridLayout layout=new GridLayout(1,7);//another gridLayout
      panel.setLayout(layout);
      //three blank textFields
      JTextField field1=new JTextField("");
      JTextField field2=new JTextField("");
      JTextField field3=new JTextField("");
      //a button that submits data
      JButton button=new JButton("Enter");
      button.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e){
      int rows=0;//defaults to 0 so a value must be entered
      int columns=0;
      int iterations=1;//defaults to 1 if no value is entered
      
      try{//tries to parse values as integers
         rows=Integer.parseInt(field1.getText());
         columns=Integer.parseInt(field2.getText());
         iterations=Integer.parseInt(field3.getText());
      }catch (NumberFormatException nf){
         
      }
      //checks to make sure a row and column value have been entered, and are not less than or equal to 0
      if(rows>0 && columns>0 && iterations>0){
         ROWS=rows;
         ITERATIONS=iterations;
         COLUMNS=columns;
         ELEMENTS=ROWS*COLUMNS;
         frame2.setVisible(false);
         //calls a funtion that removes this window and calls the other GUI
         Randomizer.Next(ITERATIONS);
      }
      
   }

      });
      //adding all components to Frame
      panel.add(new JLabel("Rows"));
      panel.add(field1);
      panel.add(new JLabel("Columns"));
      panel.add(field2);
      panel.add(new JLabel("Sheets"));
      panel.add(field3);
      panel.add(button);
      frame2.setSize(500,100);
      frame2.pack();
      frame2.setVisible(true);

   }
   
   public static void saveImage(int num,JComponent panel){
      Dimension size = panel.getSize(); //gets the size of the panel
      BufferedImage image = new BufferedImage(
                    size.width, size.height 
                              , BufferedImage.TYPE_INT_RGB); //creates a BufferedImage with the size data
        Graphics2D g2 = image.createGraphics();//creates a 2D graphic from the BufferedImage
        panel.paint(g2);//paints onto the 2D graphic with the panel contents
        try
        {
            String file="bingo"+num+".png";
            ImageIO.write(image, "png", new File(file));//writes the image as bingo(x).png
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
   }
   
      
   
}
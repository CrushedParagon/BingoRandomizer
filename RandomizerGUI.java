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
   
   public static void makeGUI(String[] selected, int i){
      GridLayout layout=new GridLayout(ROWS,COLUMNS);
      layout.setHgap(5);
      layout.setVgap(5);
      JFrame frame=new JFrame("Bingo");
      JPanel panel=new JPanel();
      panel.setFocusable(true);
      panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
      panel.getInputMap().put(KeyStroke.getKeyStroke("control S"),"Save");
      panel.getActionMap().put("Save",new SaveAction(i,panel));
      
      
      panel.setLayout(layout);
      for(int j=0; j<ELEMENTS; j++){
         JTextPane textPane=new JTextPane();
         textPane.setFocusable(false);
         StyledDocument doc = textPane.getStyledDocument();
         SimpleAttributeSet center = new SimpleAttributeSet();
         StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
         doc.setParagraphAttributes(0, doc.getLength(), center, false);
         textPane.setFont(new Font("Courier",Font.BOLD,15));
         textPane.setText(selected[j]);
         textPane.setBackground(Color.lightGray);
         textPane.setEditable(false);
         panel.add(textPane);
      }
      
      frame.add(panel);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000,1000);
      frame.setVisible(true);
      if(ITERATIONS>1){
          saveImage(i,panel);
      }
      panel.requestFocus();
         }
   
   public static void rowColumnGUI(){
      JFrame frame2=new JFrame();
      frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel panel=(JPanel)frame2.getContentPane();
      GridLayout layout=new GridLayout(1,7);
      panel.setLayout(layout);
      JTextField field1=new JTextField("");
      JTextField field2=new JTextField("");
      JTextField field3=new JTextField("");
      JButton button=new JButton("Enter");
      button.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e){
      int rows=0;
      int columns=0;
      int iterations=1;
      try{
         rows=Integer.parseInt(field1.getText());
         columns=Integer.parseInt(field2.getText());
         iterations=Integer.parseInt(field3.getText());
      }catch (NumberFormatException nf){
         
      }
      if(rows>0 && columns>0 && iterations>0){
         ROWS=rows;
         ITERATIONS=iterations;
         COLUMNS=columns;
         ELEMENTS=ROWS*COLUMNS;
         frame2.setVisible(false);
         Randomizer.Next(ITERATIONS);
      }
      
   }

      });
      panel.add(new JLabel("Rows"));
      panel.add(field1);
      panel.add(new JLabel("Columns"));
      panel.add(field2);
      panel.add(new JLabel("Sheets"));
      panel.add(field3);
      panel.add(button);
      frame2.setSize(500,100);
      frame2.setVisible(true);

   }
   
   public static void saveImage(int num,JComponent panel){
      Dimension size = panel.getSize();
      BufferedImage image = new BufferedImage(
                    size.width, size.height 
                              , BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        panel.paint(g2);
        try
        {
            String file="bingo"+num+".png";
            ImageIO.write(image, "png", new File(file));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
   }
   
      
   
}
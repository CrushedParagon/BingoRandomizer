import javax.swing.AbstractAction;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JComponent;

public class SaveAction extends AbstractAction{
   private int num;
   private JComponent panel;
   public SaveAction(int i, JComponent panel){
      num=i;
      this.panel=panel;
   }
   public void actionPerformed(ActionEvent e){
      //just calls saveImage
         RandomizerGUI.saveImage(num,panel);
      
   }
}
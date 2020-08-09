import javax.swing.AbstractAction;
import java.awt.event.*;

public class SaveAction extends AbstractAction{
   private int num;
   public SaveAction(int i){
      num=i;
   }
   public void actionPerformed(ActionEvent e){
      
         RandomizerGUI.saveImage(num);
      
   }
}
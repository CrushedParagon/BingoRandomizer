import javax.swing.AbstractAction;
import java.awt.event.*;

public class SaveAction extends AbstractAction{
   public SaveAction(String s){
   }
   public void actionPerformed(ActionEvent e){
      
         RandomizerGUI.saveImage();
      
   }
}
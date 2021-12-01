import javax.swing.JFrame;
/**
*FrameGuiMain:Pokemon.
*@author Danny Luu
*@since 04/20/21
*/

public class PokemonGUI {

   /**
   *main method.
   *@param args not used
   */
   public static void main(String[] args) {
      
      JFrame frame = new JFrame("Pokemon GUI");
      // Default close operation
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Main Content Panel
      frame.add(new PokemonPanel());
      frame.getContentPane();
      
      //Setup and Display frame
      frame.pack();
      frame.setVisible(true);
   
   } // Closes main method
} // Closes class
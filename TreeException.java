/**
*Constructs a BST Exception.
*@author Danny Luu
*@since 11/19/2021
*/

public class TreeException extends RuntimeException {
   
   /** 
   * Constructs an Empty TreeException object. 
   */
   public TreeException() {
      super();
   }
   
   /** 
   * Constructs a TreeException object.
   * @param newMessage custom error message 
   */
   public TreeException(String newMessage) {
      super(newMessage);
   }

}
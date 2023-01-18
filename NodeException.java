/**
*Constructs a BST Node Exception.
*@author Danny Luu
*@since 11/19/2021
*/

public class NodeException extends RuntimeException {
   
   /** 
   * Constructs an Empty NodeException object. 
   */
   public NodeException() {
      super();
   }
   
   /** 
   * Constructs a NodeException object.
   * @param newMessage custom error message 
   */
   public NodeException(String newMessage) {
      super(newMessage);
   }

}
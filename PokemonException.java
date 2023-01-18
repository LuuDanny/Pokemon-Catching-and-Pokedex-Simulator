/**
*Constructs a pokemon Exception.
*@author Danny Luu
*@since 10/21/2021
*/

public class PokemonException extends RuntimeException {
   
   /** 
   * Constructs an Empty PokemonException object. 
   */
   public PokemonException() {
      super();
   }
   
   /** 
   * Constructs a PokemonException object.
   * @param newMessage custom error message 
   */
   public PokemonException(String newMessage) {
      super(newMessage);
   }

}
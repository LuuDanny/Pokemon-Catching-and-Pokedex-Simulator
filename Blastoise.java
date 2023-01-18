/**
*Michelle's COPY OF Pokemon "Blastoise" Object class.
*Child to Wartortle and Squirtle
*@since 09/27/2021
*@author Danny Luu
*/

public class Blastoise extends Wartortle {
   /** Create minimum attack power, 
        defense power, stamina power
   */
   
   /** minimum attack Blastoise. */
   static final int BASE_ATTACK_POWER = 171;
   /** minimum defense Blastoise. */
   static final int BASE_DEFENSE_POWER = 207;
   /** minimum stamina Blastoise. */
   static final int BASE_STAMINA_POWER = 188;

   /** Constructor with name.
   * @param name The user-defined name.
   */
   public Blastoise(String name) {
      super("Blastoise", name, 9, 1.6, 85.5, BASE_ATTACK_POWER, 
            BASE_DEFENSE_POWER, BASE_STAMINA_POWER); 
   }   
   /** constructor if name is not given. */
   public Blastoise() {
      super("Blastoise", "Blastoise", 9, 1.6, 85.5, BASE_ATTACK_POWER, 
            BASE_DEFENSE_POWER, BASE_STAMINA_POWER);
   } 
/* close class */
}

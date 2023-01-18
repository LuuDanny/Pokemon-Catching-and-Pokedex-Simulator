/**
*Michelle's COPY OF Pokemon "Wartortle" Object class.
*Child to Squirtle
*@since 09/27/2021
*@author Ursula Nichols
*/

public class Wartortle extends Squirtle {
   /** create minimum attack power, 
      defense power, stamina power
   */
   
   /** minimum attack Wartortle. */
   static final int BASE_ATTACK_POWER = 126;
   /** minimum defense Wartortle. */
   static final int BASE_DEFENSE_POWER = 155;
   /** minimum stamina Wartortle. */
   static final int BASE_STAMINA_POWER = 153;

 
   /** 
   * Constructor with species and name for subclasses.
   *@param species The Pokemon's species.
   *@param name The optional user-given name.
   *@param num The Pokedex number for this species.
   *@param ht The height of this Pokemon.
   *@param wt The weight of this Pokemon. 
   *@param baseAttackPwr The low limit of Attack Power for species.
   *@param baseDefensePwr The low limit of Defense Power for species.
   *@param baseStaminaPwr The low limit of Stamina Power for speices. 
   */
   protected Wartortle(String species, String name, int num, double ht, 
           double wt, int baseAttackPwr, int baseDefensePwr, int baseStaminaPwr) {
   
      super(species, name, num, ht, wt, 
             baseAttackPwr, baseDefensePwr, baseStaminaPwr);
                      
   }

    /** Constructor with name.
    * @param name The user-defined name.
    */
   public Wartortle(String name) {
      super("Wartortle", name, 8, 1, 22.5, BASE_ATTACK_POWER, 
          BASE_DEFENSE_POWER, BASE_STAMINA_POWER); //idk what else to put here
   }   
    /** constructor if name is not given. */
   public Wartortle() {
      super("Wartortle", "Wartortle", 8, 1, 22.5, BASE_ATTACK_POWER, 
          BASE_DEFENSE_POWER, BASE_STAMINA_POWER); //idk what else
   } 
/* close class */
}

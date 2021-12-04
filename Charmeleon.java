/**
* Michelle's COPY OF Charmeleon object.
* Subclass to Charmander
* @author Jakob Oxinio
* @since 9.28.2021
*/
public class Charmeleon extends Charmander {
   
   /**Minimum attack for species.*/
   static final int BASE_ATTACK_POWER = 158;
   /**Minimum defense for species.*/
   static final int BASE_DEFENSE_POWER = 126;
   /**Minimum stamina for species.*/
   static final int BASE_STAMINA_POWER = 151;
   
   /**
   * Constructor with name.
   * @param name The user-defined name
   */
   public Charmeleon(String name) {
      super("Charmeleon", name, 5, 1.1, 19.0, "", BASE_ATTACK_POWER,
         BASE_DEFENSE_POWER, BASE_STAMINA_POWER);
   
   } //Construct 1
   
   /**
   * Constructor with no name.
   */
   public Charmeleon() {
      super("Charmeleon", "Charmeleon", 5, 1.1, 19.0, "", BASE_ATTACK_POWER,
         BASE_DEFENSE_POWER, BASE_STAMINA_POWER);
   
   } //Construct 2
   
   /**
   * Constructor for subclass below Charmeleon.
   * @param species The species of the pokemon
   * @param name The user-defined name
   * @param num The given number of the pokemon
   * @param ht The height
   * @param wt The weight
   * @param type2 The pokemon's second type
   * @param baseAttackPwr Base attack power
   * @param baseDefensePwr Base defense power
   * @param baseStaminaPwr Base stamina power
   */
   protected Charmeleon(String species, String name, int num,
       double ht, double wt, String type2,
       int baseAttackPwr, int baseDefensePwr, int baseStaminaPwr) {
      
      super(species, name, num, ht, wt, type2, baseAttackPwr, baseDefensePwr, baseStaminaPwr);
      
   }
} //Class

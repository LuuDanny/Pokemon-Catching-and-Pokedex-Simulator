import java.util.Random;
/**
* MICHELLE'S COPY OF Charmander subclass.
* Single Fire type interface
* Parent class to Charmeleon
* @author Jakob Oxino
* @since 9.25.21
*/
public class Charmander extends Pokemon implements FireType {
   
   /**Minimum attack for species.*/
   static final int BASE_ATTACK_POWER = 116;
   /**Minimum defense for species.*/
   static final int BASE_DEFENSE_POWER = 93;
   /**Minimum stamina for species.*/
   static final int BASE_STAMINA_POWER = 118;

   /**
   * Constructor with name.
   * Uses pokemon superclass contructor
   * @param name The user-defined name
   */
   public Charmander(String name) {
      super("Charmander", name, 4, FIRE_COLOR, 0.6, 8.5,
         FIRE_TYPE, "", BASE_ATTACK_POWER, BASE_DEFENSE_POWER,
         BASE_STAMINA_POWER);
      //Species dependent
      chooseFastAttack();
      chooseSpecialAttack();
   } //Construct 1
   
   /**
   * Constructor without name.
   * Uses pokemon superclass constructor
   */
   public Charmander() {
      super("Charmander", "Charmander", 4, FIRE_COLOR, 0.6, 8.5,
         FIRE_TYPE, "", BASE_ATTACK_POWER, BASE_DEFENSE_POWER,
         BASE_STAMINA_POWER);
      //Species dependent
      chooseFastAttack();
      chooseSpecialAttack();   
   } //Construct 2
   
   /**
   * Constructor for subclass.
   * Takes subclass information and passes through to superclass constructor
   * @param species The species of the pokemon
   * @param name Given name of the pokemon
   * @param num The pokemon's number
   * @param ht Height
   * @param wt Weight
   * @param type2 The pokemon's second type
   * @param baseAttackPwr Base attack power
   * @param baseDefensePwr Base defense
   * @param baseStaminaPwr Base stamina power
   */
   protected Charmander(String species, String name, int num,
       double ht, double wt, String type2,
       int baseAttackPwr, int baseDefensePwr, int baseStaminaPwr) {
         
      super(species, name, num, FIRE_COLOR, ht, wt, FIRE_TYPE,
         type2, baseAttackPwr, baseDefensePwr, baseStaminaPwr);
         
      chooseFastAttack();
      chooseSpecialAttack();
   } //Construct for subclass
      
   /**
   * Randomly gets fast attack from Fire type interface array.
   * Gets the attack power also
   */
   protected void chooseFastAttack() {
      //Random generator and index for array selection
      Random randGen = new Random();
      int index = 0;
      
      //Uses the generated index to select attack name and power from array
      index = randGen.nextInt(FIRE_FAST_ATTACKS.length);
      fastAttack = FIRE_FAST_ATTACKS[index];
      fastAttackPower = FIRE_FAST_ATK_POWER[index];   
   } //Fast attack method
   
   /**
   * Randomly gets the special attack from Fire type interface array.
   *  Gets the attack power also
   */
   protected void chooseSpecialAttack() {
      //Random generator and index for array selection\
      Random randGen = new Random();
      int index = 0;
      
      //Uses the generated index to select attack name and power from array
      index = randGen.nextInt(FIRE_SPECIAL_ATTACKS.length);
      specialAttack = FIRE_SPECIAL_ATTACKS[index];
      specialAttackPower = FIRE_SPECIAL_ATK_POWER[index];
   } //Special attack method
   
   /**
   * Reduces pokemons Hp when being attacked.
   * @param damage The amount of hp to take off
   */
   protected void beAttacked(int damage) {
      
      damage = damage / defensePower;
      
      if (hP > damage) {
         hP = hP - damage;
      } else {
         hP = 0;
      }  
   } //be attacked method
   
   /**
   * Fast attack calculation.
   * Creates a string to be displayed explaining how effective attack is
   * @param victim The pokemon being attacked
   * @return String Explains attack and effectiveness
   */
   public String performFastAttack(Pokemon victim) {
      Random randGen = new Random();
      double damage = 0.0;
      double modifier = 1.0;
      double damageDivisor = 250.0;
      
      String s = "";
      String vType = victim.getType1();
      
      modifier = (double) (randGen.nextInt(16) + 85) / 100.0;
      s = name + " performed " + fastAttack + " on " + victim.getSpecies();
      
      if (vType.equals("Grass") || vType.equals("Ice") || vType.equals("Bug")
          || vType.equals("Steel")) {
                 
         s = s + "\n It was super effective!";
         modifier = modifier * 2;
      } else if (vType.equals("Fire") || vType.equals("Water") || vType.equals("Rock")
            || vType.equals("Dragon")) {
            
         s = s + "\n It was not very effective.";
         modifier = modifier * 0.5;
      }
        //Damage calculations
      damage = (((2 * level) + 10) / damageDivisor)
         * attackPower * (specialAttackPower + 2) * modifier;
        
        //Modifier calculates damage based on type circumstances 
      victim.beAttacked((int) damage);
      return s;
   } //Performing the fast attack
   
   /**
   * Special attack calculation.
   * Creates a string to be displayed explaing how effective attack is
   * @param victim The pokemon being attacked
   * @return String Explains attack and effectiveness
   */
   public String performSpecialAttack(Pokemon victim) {
      Random randGen = new Random();
      double damage = 0.0;
      double modifier = 1.0;
      double damageDivisor = 250.0;
      
      String s = "";
      String vType = victim.getType1();
         
      modifier = (double) (randGen.nextInt(16) + 85) / 100.0;
      s = name + " performed " + specialAttack + " on " + victim.getSpecies();
   
      if (vType.equals("Grass") || vType.equals("Ice") || vType.equals("Bug")
          || vType.equals("Steel")) {
                 
         s = s + "\n It was super effective!";
         modifier = modifier * 2;
      } else if (vType.equals("Fire") || vType.equals("Water") || vType.equals("Rock")
            || vType.equals("Dragon")) {
            
         s = s + "\n It was not very effective.";
         modifier = modifier * 0.5;
      }
        //Damage calculations
      damage = (((2 * level) + 10) / damageDivisor)
         * attackPower * (specialAttackPower + 2) * modifier;
        
        //Modifier calculates damage based on type circumstances 
      victim.beAttacked((int) damage);      
      
      return s;
   } //Performing the special attack
   
} //Class

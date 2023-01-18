import java.util.Random;

/**
*Pokemon "Squirtle" Object class.
*Parent class to Wartortle
*@author: Michelle Ho
*@since: 09/28/2021
*/
public class Squirtle extends Pokemon implements WaterType {
   /** Minimum attack power for Squirtle. */
   static final int BASE_ATTACK_POWER = 94;
   /** Minimum defense power for Squirtle. */
   static final int BASE_DEFENSE_POWER = 121;
   /** Minimum stamina power for Squirtle. */
   static final int BASE_STAMINA_POWER = 127;
   
   /*************** 3 Constructors ***************/
   
   /**
   *Constructor with no name.
   *uses Pokemon superclass constructor
   */
   public Squirtle() {
      super("Squirtle", "Squirtle", 7, WATER_COLOR, 0.5, 9.0, WATER_TYPE,
         "", BASE_ATTACK_POWER, BASE_DEFENSE_POWER, BASE_STAMINA_POWER);
      chooseFastAttack();
      chooseSpecialAttack();
   }
   
   /**
   *Constructor with name.
   *uses Pokemon superclass constructor
   *@param name - the user's input for Pokemon name
   */
   public Squirtle(String name) {
      super("Squirtle", name, 7, WATER_COLOR, 0.5, 9.0, WATER_TYPE,
         "", BASE_ATTACK_POWER, BASE_DEFENSE_POWER, BASE_STAMINA_POWER);
      //chooses a random attack
      chooseFastAttack();
      chooseSpecialAttack();
   }
   
   /**
   *Constructor for Squirtle's subclasses.
   *@param species - Pokemon's species
   *@param name - the user's input for Pokemon name
   *@param num - Pokemon's number
   *@param ht - Pokemon's height
   *@param wt - Pokemon's weight
   *@param baseAttackPower - Pokemon's minimum attack power
   *@param baseDefensePower - Pokemon's minimum defense power
   *@param baseStaminaPower - Pokemon's minimum stamina power
   */
   protected Squirtle(String species, String name, int num, double ht,
         double wt, int baseAttackPower, int baseDefensePower,
         int baseStaminaPower) {
      super(species, name, num, WATER_COLOR, ht, wt, WATER_TYPE,
            "", baseAttackPower, baseDefensePower, baseStaminaPower);
      //chooses a random attack
      chooseFastAttack();
      chooseSpecialAttack();
   }
   
   /*************** Choosing attacks ***************/
   
   /**
   *Randomly chooses a fast attack from WaterType's array.
   */
   protected void chooseFastAttack() {
      Random ranGen = new Random();
      int index;
      
      index = ranGen.nextInt(WATER_FAST_ATTACKS.length);
      fastAttack = WATER_FAST_ATTACKS[index];
      fastAttackPower = WATER_FAST_ATK_POWER[index];     
   }
   
   /**
   *Randomly chooses a special attack from WaterType's array.
   */
   protected void chooseSpecialAttack() {
      Random ranGen = new Random();
      int index;
      
      index = ranGen.nextInt(WATER_SPECIAL_ATTACKS.length);
      specialAttack = WATER_SPECIAL_ATTACKS[index];
      specialAttackPower = WATER_SPECIAL_ATK_POWER[index];  
   }
   
   /*************** In-game performance ***************/
   
   /**
   *Pokemon uses fast attack and explains the attack details.
   *@param victim - Pokemon being attacked
   *@return String - states attack and effectiveness
   */
   public String performFastAttack(Pokemon victim) {
      Random ranGen = new Random();
      double damage = 0.0;
      double modifier = 1.0;
      double damageDivisor = 250.0;
      
      String s = "";
      String vType = victim.getType1();
      
      //random modifier between 0.85 and 1.00
      modifier = (double) (ranGen.nextInt(16) + 85) / 100.0;      
      s = name + " performed " + fastAttack + " on " + victim.getSpecies();
      
      //check effectiveness of attack     
      if (vType.equals("Fire") || vType.equals("Ground") 
             || vType.equals("Rock")) {
            
         s = s + "\n It was super effective!";
         modifier = modifier * 2.0;          
      } else if (vType.equals("Water") || vType.equals("Grass") 
             || vType.equals("Dragon")) { 
         s = s + "\n It was not very effective.";
         modifier = modifier * 0.5;
      } //closes if loop
     
      //check for same types for bonus
      if (type1.equals(vType) && type2.equals(victim.getType2())) {
         modifier = modifier *  1.5;
      } //closes if loop
      
      //bulbapedia damage formula:
      damage = (((2 * level) + 10) / damageDivisor) 
         * attackPower * (specialAttackPower + 2) * modifier;      
      //perform hit on victim pokemon
      victim.beAttacked((int) damage);
      return s;   
   }
   
   /**
   *Pokemon uses special attack and explains the attack details.
   *@param victim - Pokemon being attacked
   *@return String - states attack and effectiveness
   */
   public String performSpecialAttack(Pokemon victim) {
      Random ranGen = new Random();
      double damage = 0.0;
      double modifier = 1.0;
      double damageDivisor = 250.0;
      
      String s = "";
      String vType = victim.getType1();
      
      //random modifier between 0.85 and 1.00
      modifier = (double) (ranGen.nextInt(16) + 85) / 100.0;      
      s = name + " performed " + specialAttack + " on " + victim.getSpecies();
      
      //check effectiveness of attack     
      if (vType.equals("Fire") || vType.equals("Ground") 
             || vType.equals("Rock")) {
            
         s = s + "\n It was super effective!";
         modifier = modifier * 2.0;          
      } else if (vType.equals("Water") || vType.equals("Grass") 
             || vType.equals("Dragon")) { 
         s = s + "\n It was not very effective.";
         modifier = modifier * 0.5;
      } //closes if loop
     
      //check for same types for bonus
      if (type1.equals(vType) && type2.equals(victim.getType2())) {
         modifier = modifier *  1.5;
      } //closes if loop
      
      //bulbapedia damage formula:
      damage = (((2 * level) + 10) / damageDivisor) 
         * attackPower * (specialAttackPower + 2) * modifier;      
      //perform hit on victim pokemon
      victim.beAttacked((int) damage);
      return s;   
   }
   
   /*************** Getting attacked ***************/
   
   /**
   *Pokemon gets attack (becomes victim).
   *HP decreases by damage/defensePower
   *HP can't get lower than 0
   *@param damage - amt of points to take off from HP
   */
   protected void beAttacked(int damage) {
      damage = damage / defensePower;
      
      if (hP > damage) {
         hP = hP - damage;
      } else {
         hP = 0;
      } //ends if loop
   }
   
}

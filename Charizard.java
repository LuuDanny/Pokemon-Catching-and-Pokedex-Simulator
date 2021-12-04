import java.util.Random;

/**
*Pokemon "Charizard" Object class.
*Subclass of Charmeleon
*@author: Michelle Ho
*@since: 09/28/2021
*/
public class Charizard extends Charmeleon implements FireType, FlyingType {

   /** Minimum attack power for Charizard. */
   static final int BASE_ATTACK_POWER = 223;
   /** Minimum defense power for Charizard. */
   static final int BASE_DEFENSE_POWER = 173;
   /** Minimum stamina power for Charizard. */
   static final int BASE_STAMINA_POWER = 186;
   
   //Boolean to choose between the 2 types
   /** For fast attack type. */
   protected boolean fastIsFire = true;
   /** For special attack type. */
   protected boolean specialIsFire = true;
   
   
   /*************** 2 Constructors ***************/
   
   /**
   *Constructor with no name.
   *uses Pokemon superclass constructor
   */
   public Charizard() {
      super("Charizard", "Charizard", 6, 1.7, 90.5, FLYING_TYPE, 
         BASE_ATTACK_POWER, BASE_DEFENSE_POWER, BASE_STAMINA_POWER);
     
      chooseFastAttack();
      chooseSpecialAttack();     
   }
   
   /**
   *Constructor with name.
   *uses Pokemon superclass constructor
   *@param name - the user's input for Pokemon name
   */
   public Charizard(String name) {
      super("Charizard", name, 6, 1.7, 90.5, FLYING_TYPE,
         BASE_ATTACK_POWER, BASE_DEFENSE_POWER, BASE_STAMINA_POWER);
         
      chooseFastAttack();
      chooseSpecialAttack();
   }
   
   /*************** Choosing attacks ***************/
   
   /**
   *Randomly chooses a fast attack from FireType's array.
   */
   protected void chooseFastAttack() {
      Random ranGen = new Random();
      int index;
      
      fastIsFire = ranGen.nextBoolean();
      
      if (fastIsFire) {     
         index = ranGen.nextInt(FIRE_FAST_ATTACKS.length);
         fastAttack = FIRE_FAST_ATTACKS[index];
         fastAttackPower = FIRE_FAST_ATK_POWER[index]; 
      } else { //attack is a flying type
         index = ranGen.nextInt(FLYING_FAST_ATTACKS.length);
         fastAttack = FLYING_FAST_ATTACKS[index];
         fastAttackPower = FLYING_FAST_ATK_POWER[index]; 
      } 
   }
   
   /**
   *Randomly chooses a special attack from FireType's array.
   */
   protected void chooseSpecialAttack() {
      Random ranGen = new Random();
      int index;
      
      specialIsFire = ranGen.nextBoolean();
      
      if (specialIsFire) {     
         index = ranGen.nextInt(FIRE_SPECIAL_ATTACKS.length);
         specialAttack = FIRE_SPECIAL_ATTACKS[index];
         specialAttackPower = FIRE_SPECIAL_ATK_POWER[index]; 
      } else { //attack is a flying type 
         index = ranGen.nextInt(FLYING_SPECIAL_ATTACKS.length);
         specialAttack = FLYING_SPECIAL_ATTACKS[index];
         specialAttackPower = FLYING_SPECIAL_ATK_POWER[index]; 
      } 
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
      if (fastIsFire) { //if attack is a fire type
         if (vType.equals("Grass") || vType.equals("Bug")
             || vType.equals("Ice") || vType.equals("Steel")) {
            
            s = s + "\n It was super effective!";
            modifier = modifier * 2.0;          
         } else if (vType.equals("Fire") || vType.equals("Water") || vType.equals("Ground") 
             || vType.equals("Rock")) { 
            s = s + "\n It was not very effective.";
            modifier = modifier * 0.5;
         }
         
      } else { //if attack is a flying type
         if (vType.equals("Grass") || vType.equals("Bug")
               || vType.equals("Fighting")) {
            s = s + "\n It was super effective!";
            modifier = modifier * 2.0;
         } else if (vType.equals("Rock") || vType.equals("Steel") 
             || vType.equals("Electric")) { 
            s = s + "\n It was not very effective.";
            modifier = modifier * 0.5;
         }
      }
      //check for same types for bonus
      if (type1.equals(vType) && type2.equals(victim.getType2())) {
         modifier = modifier *  1.5;
      }
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
      if (fastIsFire) { //if attack is a fire type
         if (vType.equals("Grass") || vType.equals("Bug") 
             || vType.equals("Ice") || vType.equals("Steel")) {          
            s = s + "\n It was super effective!";
            modifier = modifier * 2.0;                      
         } else if (vType.equals("Fire") || vType.equals("Water") || vType.equals("Ground") 
             || vType.equals("Rock")) { 
            s = s + "\n It was not very effective.";
            modifier = modifier * 0.5;
         } //closes if/else if loop
         
      } else { //if attack is a flying type
         if (vType.equals("Grass") || vType.equals("Bug")
               || vType.equals("Fighting")) {
            s = s + "\n It was super effective!";
            modifier = modifier * 2.0;
         } else if (vType.equals("Rock") || vType.equals("Steel") 
             || vType.equals("Electric")) { 
            s = s + "\n It was not very effective.";
            modifier = modifier * 0.5;
         } //closes else/if/else if loop
      } //ends if/else loop
      
      //check for same types for bonus
      if (type1.equals(vType) && type2.equals(victim.getType2())) {
         modifier = modifier *  1.5;
      } //ends if loop
      
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

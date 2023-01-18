/**
* interface for Normal type Pokemon.
* Fails checkstyle because no methods.
* @author Danny Luu, Satoko Yajima, Ula Nicholas, Jakob Oxinio
* @since 10/20/2021
*/
public interface NormalType {
   /** constants **/
   /** Type name String. */
   String NORMAL_TYPE = new String("Normal");
   /** Type color String. */
   String NORMAL_COLOR = new String("Normal");
   /** Array of type fast attack names. */
   String[] NORMAL_FAST_ATTACKS = {"Cut", "Pound", "Quick Attack", "Scratch", 
      "Tackle"};
   /** Array of type special attack names. */
   String[] NORMAL_SPECIAL_ATTACKS = {"Hyper Beam", "Vice Grip", "Swift", 
      "Horn Attack", "Stomp", "Hyper Fang", "Body Slam", "Rest", "Struggle"};
   /** Array of fast attack powers, coincides with name array. */
   int[] NORMAL_FAST_ATK_POWER = {12, 7, 8, 8, 10}; 
   /** Array of special attack powers, coincides with name array. */     
   int[] NORMAL_SPECIAL_ATK_POWER = {120, 25, 30, 25, 30, 35, 40, 35, 15};
}
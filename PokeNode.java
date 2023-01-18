 /**
 * Class for a binary node.
 * @author Danny Luu
 * @since 11/19/2021
 */
public class PokeNode {
   
   /** Pokemon stored in Node. */
   private Pokemon poke;
   /** Count of duplicate pokemon. */
   private int catchCount;
   /** The number of times the pokemon has been seen. */
   private int seenCount;
   /** Link to left child Node. */
   private PokeNode leftChild;
   /** Link to right child Node. */
   private PokeNode rightChild;
   
   /**
	* Constructor.
	* @param p The address of the object that is stored by the node
   * @param count The count of the pokemon
	* @param lChild The address of the left child
	* @param rChild The address of the right child
	*/
   public PokeNode(Pokemon p, int spotted, int caught, PokeNode lChild, PokeNode rChild) {
      this.poke = p;
      this.seenCount = spotted;
      this.catchCount = caught;
      this.leftChild = lChild;
      this.rightChild = rChild;
   
   }
   
   //============= getMethod ==================
   /**
   * Gets this Node's Pokemon.
   * @return Pokemon poke the stored pokemon
   */
   public Pokemon getPokemon() {
      return this.poke;
   }
   
   /**
   * Gets the Pokemon's pokedex number.
   * @return int Pokemon's pokedex number
   */
   public int getKey() {
      return this.poke.getNumber();
   }
   
   /**
   * Gets the seen count of a specific pokemon species.
   * @return int seenCount catch count of a pokemon
   */
   public int getSeen() {
      return this.seenCount;
   }
   
   /**
   * Gets the catch count of a specific pokemon species.
   * @return int catchCount catch count of a pokemon
   */
   public int getCount() {
      return this.catchCount;
   }
   
   /**
   * Gets the left child of a given node.
   * @return PokeNode leftChild
   */
   public PokeNode getLChild() {
      return this.leftChild;
   }
   
   /**
   * Gets the right child of a given node.
   * @return PokeNode rightChild
   */
   public PokeNode getRChild() {
      return this.rightChild;
   }
   
   //============= setMethod ==================
   
   /**
   * Sets the catchCount to a new int.
   * Only used during node removal
   * @param newCount The new catchCount
   */
   public void setCount(int newCount) {
      this.catchCount = newCount;
   }
   
   /**
   * Increment the catchCount in a given node.
   */
   public void increaseSeen() {
      seenCount++;
   }
   
   /**
   * Increment the catchCount in a given node.
   */
   public void increaseCount() {
      catchCount++;
   }
   
   /**
   * Decrement speciesCount in a given node.
   * @exception NodeException if catchCount becomes < 1
   */
   public void decreaseCount()throws NodeException {
      if (catchCount <= 1) {
         throw new NodeException("Error: Catch count cannot be decreased past 1!");
      } else {
         catchCount--;
      }
   }
   
  /**
  * Sets the left child of a given node.
  * @param newLChild the new left Child's return adress
  */
   public void setLChild(PokeNode newLChild) {
      this.leftChild = newLChild;
   }
   
   /**
   * Sets the right child of a given node.
   * @param newRChild the new right Child's return adress
   */
   public void setRChild(PokeNode newRChild) {
      this.rightChild = newRChild;
   }
   
   /**
   * Sets this Node's Pokemon.
   * Only used during node removal
   * @param newPokemon the new pokemon's return adress
   */
   public void setPokemon(Pokemon newPokemon) {
      this.poke = newPokemon;
   }
   
}
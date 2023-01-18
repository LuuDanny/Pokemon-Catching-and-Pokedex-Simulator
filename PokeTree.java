/**
* Class for a Binary Search Tree.
* @author Danny Luu
* @since 11/19/2021
*/
public class PokeTree {
   
   //============= Instance Variables ==================
   /** Root node. */
   private PokeNode root = null;
   /** Print String. */
   private String s = new String("");
   
   //============= Constructor ==================
   /**
   * Constructor with no parameter.
   */
   public PokeTree() {
   }
    
   //============= Seen Method =================
   /**
   * Wrapper method that calls recursive seen method.
   * @param p  The object to be added
   */
   public void seen(Pokemon p) {
      root = seen(root, p);
   }
   
   /**
   * Recursive seen method.
   * @param node The root of the tree/subtree
   * @param p The pokemon being Seen
   * @return PokeNode node The current node
   */
   private PokeNode seen(PokeNode node, Pokemon p) {
   //Base Case: Empty tree or end of a leaf.
      if (node == null) {
         node = new PokeNode(p, 1, 0, null, null);
         return node;
      } 
      //Base Case: Duplicate node.
      else if (p.getNumber() - node.getKey() == 0) {
         node.increaseSeen();
         return node;
      }
      //Recursive Case: Move left if new Pokemon's number is lower than current node's key.
      else if (p.getNumber() - node.getKey() < 0) {
         //Sets the node's left child to the left subtree with item added.
         node.setLChild(this.seen(node.getLChild(), p));
         return node;
      }
      //Recursive Case: Move right if new Pokemon's number is highier than current node's key.
      else {
         //Sets the node's right child to the right subtree with item added.
         node.setRChild(this.seen(node.getRChild(), p));
         return node;
      }
   } //Cose recurse add method 
   
   
    
   //============= Add Method =================
   /**
   * Wrapper method that calls recursive add method.
   * @param p  The object to be added
   */
   public void add(Pokemon p) {
      root = add(root, p);
   }
   
   /**
   * Recursive add method.
   * @param node The root of the tree/subtree
   * @param p The pokemon being added
   * @return PokeNode node The current node
   */
   private PokeNode add(PokeNode node, Pokemon p) {
   //Base Case: Empty tree or end of a leaf.
      if (node == null) {
         node = new PokeNode(p, 1, 1, null, null);
         return node;
      } 
      //Base Case: Duplicate node.
      else if (p.getNumber() - node.getKey() == 0) {
         node.increaseCount();
         return node;
      }
      //Recursive Case: Move left if new Pokemon's number is lower than current node's key.
      else if (p.getNumber() - node.getKey() < 0) {
         //Sets the node's left child to the left subtree with item added.
         node.setLChild(this.add(node.getLChild(), p));
         return node;
      }
      //Recursive Case: Move right if new Pokemon's number is highier than current node's key.
      else {
         //Sets the node's right child to the right subtree with item added.
         node.setRChild(this.add(node.getRChild(), p));
         return node;
      }
   } //Cose recurse add method
   
   
   
   //============= Get Method =================
   /**
   * Wrapper method that calls recursive get method. 
   * @param searchKey Key of the desired pokemon
   * @return the pokemon in the tree with matching key.
   */
   public Pokemon get(Pokemon searchKey) {
      return this.get(root, searchKey);
   }
   
   /**
   * Recursive get method.
   * @param node The root of the tree/subtree
   * @param searchKey The key of the desired pokemon
   * @return Pokemon The pokemon with the matching key
   * @throws TreeException if pokemon not found
   */
   private Pokemon get(PokeNode node, Pokemon searchKey) throws TreeException {
      //Base Case: Not found (searchKey does not matches), throw exception.
      if (node == null) {
         throw new TreeException("Pokemon not found!");
      } 
      //Base Case: Found (searchKey matches), return the Pokemon address
      else if (searchKey.getNumber() - node.getKey() == 0) {
         return node.getPokemon();
      }
      //Recursive Case: Move left if searchKey is lower than current node's key.
      else if (searchKey.getNumber() - node.getKey() < 0) {
         return this.get(node.getLChild(), searchKey);
      }
      //Recursive Case: Move right if searchKey is highier than current node's key. 
      else {
         return this.get(node.getRChild(), searchKey);
      }
   } //Cose recurse get method
   
   
   
   //============= Remove Method =================
   /**
   * Wrapper method that calls recursive remove method.
   * @param searchKey The pokemon to be remove
   */
   public void remove(Pokemon searchKey) {
      root = this.remove(root, searchKey);
   }
   
   /**
   * Recursive remove method.
   * @param node The root of the tree/subtree
   * @param searchKey The pokemon to be removed
   * @return root of current subtree
   * @throws TreeException if pokemon not found
   */
   private PokeNode remove(PokeNode node, Pokemon searchKey) {
      //Base Case: Not found (searchKey does not matches), throw exception.
      if (node == null) {
         throw new TreeException("Item not found!");
      } 
      //Base Case: Found (searchKey matches)
      else if (searchKey.getNumber() - node.getKey() == 0) {
         //If catchCount > 1, decrement
         if (node.getCount() > 1) {
            node.decreaseCount();
            return node;
         }
         //If catchCount == 1, remove
         else {
            node = this.remove(node);
            return node;
         }
      }
      //Recursive Case: Move left if searchKey is lower than current node's key.
      else if (searchKey.getNumber() - node.getKey() < 0) {
         node.setLChild(this.remove(node.getLChild(), searchKey));
         return node;
      }
      //Recursive Case: Move right if searchKey is highier than current node's key. 
      else {
         node.setRChild(this.remove(node.getRChild(), searchKey));
         return node;
      }
   } //Cose recurse remove method
   
   /**
   * Helper method that takes a node out of tree.
   * @param node The node to remove
   * @return The node that replaces removed node or null.
   */
   private PokeNode remove(PokeNode node) {
      //Case 1: Node is a leaf, return null
      if (node.getLChild() == null && node.getRChild() == null) {
         return null;
      }
      //Case 2: Node has a single right child node, return a reference to the right child node
      else if (node.getLChild() == null) {
         return node.getRChild();
      }
      //Case 3: Node has a single left child node, then return a reference to the left child node
      else if (node.getRChild() == null) {
         return node.getLChild();
      }
      //Case 4: Node has two child nodes
      else {
         //Gets pokemon with largest searchKey in Left Subtree
         Pokemon largestPokemonInLSubtree = this.getPokemonWithLargestSearchKey(node.getLChild());
         //Gets the count of the pokemon with largest searchKey in Left Subtree
         int largestPokemonCountInLSubtree = this.getCountOfLargestSearchKey(node.getLChild());
         //Replace the node's current pokemon with new pokemon
         node.setPokemon(largestPokemonInLSubtree);
         //Replace the node's current count with new count
         node.setCount(largestPokemonCountInLSubtree);
         //Removes the rightmost node in the left subtree
         node.setLChild(this.removeNodeWithLargestSearchKey(node.getLChild()));
         return node;
      }
   }

   /**
   * Returns the Pokemon with the largest search key in the (sub)tree.
   * Helper method for removing interior nodes.
   * @param node The root of the tree/subtree
   * @return The Pokemon with largest key
   */
   private Pokemon getPokemonWithLargestSearchKey(PokeNode node) {
      //No right child, return current node
      if (node.getRChild() == null) {
         return node.getPokemon();
      }
      //Recursively seach through right child nodes
      else {
         return this.getPokemonWithLargestSearchKey(node.getRChild());
      }
   }
   
   /**
   * Returns the counts of the Pokemon with the largest search key in the (sub)tree.
   * Helper method for removing interior nodes.
   * @param node The root of the tree/subtree
   * @return The catch count of the Pokemon with largest key
   */
   private int getCountOfLargestSearchKey(PokeNode node) {
      //No right child, return current node
      if (node.getRChild() == null) {
         return node.getCount();
      }
      //Recursively seach through right child nodes
      else {
         return this.getCountOfLargestSearchKey(node.getRChild());
      }
   }
   
   /**
   * Removes the node with the largest search key.
   * Helper method for removing interior nodes.
   * Remove the node formerly occupied by item with largest search key.
   * @param node The root of the tree/subtree
   * @return root of (sub)tree with node removed.
   */
   private PokeNode removeNodeWithLargestSearchKey(PokeNode node) {
      //No right child, replace it with left child
      if (node.getRChild() == null) {
         return node.getLChild();
      }
      //Recursively seach through right child nodes
      else {
         node.setRChild(this.removeNodeWithLargestSearchKey(node.getRChild()));
         return node;
      }
   }
   
   
   //============= Print Method =================
   /**
   * Wrapper method that calls recursive inorder Print method. 
   * @return String the string representation of the tree
   */
   public String printPokeTree() {
      s = ""; //Resets the string so that it doesn't append itself
      s = printPokeTree(root);
      return s;
   }
   
   /**
   * Inorder Print method.
   * @param node The root of the tree/subtree 
   * @return String the string representation of the tree
   */
   private String printPokeTree(PokeNode node) {
      
      if (node != null) {
         printPokeTree(node.getLChild());
         s = s + "\n   Number: " + node.getPokemon().getNumber() 
               + "\n   Species: " + node.getPokemon().getSpecies() 
               + "\n   Type: " + node.getPokemon().getType()
               + "\n   Height: " + node.getPokemon().getHeight() 
               + "     Weight: " + node.getPokemon().getWeight()
               + "\n   Found: " + node.getSeen() 
               + "        Caught: " + node.getCount() + "\n";
         printPokeTree(node.getRChild());
      }
      return s;
   } //Coses Print method  
} //Closes Class
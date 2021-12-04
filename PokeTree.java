import java.util.HashMap;
/**
* Class for a Binary Search Tree.
* @author Danny Luu
* @since 11/19/2021
*/
public class PokeTree {
   
   //============= Instance Variables ==================
   /** Root node. */
   private PokeNode root = null;
   
   //============= Constructor ==================
   /**
   * Constructor with no parameter.
   */
   public PokeTree() {
   }
    
    
    
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
         node = new PokeNode(p, 1, null, null);
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
   */
   public void printPokeTree() {
      this.printPokeTree(root);
   }
   
   /**
   * Inorder Print method.
   * @param node The root of the tree/subtree 
   */
   private void printPokeTree(PokeNode node) {
      //System.out.println(pokeACSII("Pokemon"));
      
      if (node != null) {
         printPokeTree(node.getLChild());
         System.out.println("" + node.getPokemon().toString() + "\nCount: " + node.getCount());
         System.out.println(pokeACSII(node.getPokemon().getSpecies())); 
         printPokeTree(node.getRChild());
      }
   } //Coses Print method
   

   //============= ACSII HashMap =================
   /**
   * HashMap containing pokemon ACSII.
   * @param poke The key for the desired pokemon
   * @return String ASCII of the pokemon
   */
   public String pokeACSII(String poke) {
      String image = new String("");
      HashMap<String, String> pokemonACSII = new HashMap<String, String>();
      
      String bulbasaur = new String("                                                 /"
            + "\n" + "                              _,.------....___,.' ',.-."
            + "\n" + "                           ,-'          _,.--\"        |"
            + "\n" + "                         ,'         _.-'              ."
            + "\n" + "                        /   ,     ,'                   `"
            + "\n" + "                       .   /     /                     ``."
            + "\n" + "                       |  |     .                       \\.\\"
            + "\n" + "             ____      |___._.  |       __               \\ `."
            + "\n" + "           .'    `---\"\"       ``\"-.--\"'`  \\               .  \\"
            + "\n" + "          .  ,            __               `              |   ."
            + "\n" + "          `,'         ,-\\\"'  .               \\             |    L"
            + "\n" + "         ,'          '    _.'                -._          /    |"
            + "\n" + "        ,`-.    ,\\\".   `--\'                      >.      ,'     |"
            + "\n" + "       . .'\\'   `-'       __    ,  ,-.         /  `.__.-      ,'"
            + "\n" + "       ||:, .           ,'  ;  /  / \\ `        `.    .      .'/"
            + "\n" + "       j|:D  \\          `--'  ' ,'_  . .         `.__, \\   , /"
            + "\n" + "      / L:_  |                 .  \"' :_;                `.'.'"
            + "\n" + "      .    \"\"'                  \"\"\"\"\"'                    V"
            + "\n" + "       `.                                 .    `.   _,..  `"
            + "\n" + "         `,_   .    .                _,-'/    .. `,'   __  `"
            + "\n" + "          ) \\`._        ___....----\"'  ,'   .'  \\ |   '  \\  ."
            + "\n" + "         /   `. \"`-.--\"'         _,' ,'     `---' |    `./  |"
            + "\n" + "        .   _  `\"\"'--.._____..--\"   ,             '         |"
            + "\n" + "        | .\" `. `-.                /-.           /          ,"
            + "\n" + "        | `._.'    `,_            ;  /         ,'          ."
            + "\n" + "       .'          /| `-.        . ,'         ,           ,"
            + "\n" + "       '-.__ __ _,','    '`-..___;-...__   ,.'\\ ____.___.'"
            + "\n" + "       `\"^--'..'   '-`-^-'\"--    `-^-'`.''\"\"\"\"\"`.,^.`.--' "
            + "\n");
      pokemonACSII.put("Bulbasaur", bulbasaur);
      
      String ivysaur = new String("                                    ,'\"`.,./."
            + "\n" + "                                  ,'        Y',\".."
            + "\n" + "                                ,'           \\  | \\"
            + "\n" + "                               /              . |  `"
            + "\n" + "                              /               | |   \\"
            + "\n" + "                 __          .                | |    ."
            + "\n" + "            _   \\  `. ---.   |                | j    |"
            + "\n" + "           / `-._\\   `Y   \\  |                |.     |"
            + "\n" + "          _`.    ``    \\   \\ |..              '      |,-'\"\"7,...."
            + "\n" + "          l     '-.     . , `|  | , |`. , ,  /,     ,'    '/   ,'_,.-."
            + "\n" + "          `-..     `-.  : :     |/ `   ' \"\\,' | _  /          '-'    /___"
            + "\n" + "           \\\"\"' __.,.-`.: :        /   /._    l'.,'"
            + "\n" + "            `--,   _.-' `\".           /__ `'-.' '         ."
            + "\n" + "            ,---..._,.--\"\"\"\"\"\"\"--.__..----,-.'   .  /    .'   ,.--"
            + "\n" + "            |                          ,':| /    | /     ;.,-'--      ,.-"
            + "\n" + "            |     .---.              .'  :|'     |/ ,.-='\"-.`\"`' _   -.'"
            + "\n" + "            /    \\    /               `. :|--.  _L,\"---.._        \"----'"
            + "\n" + "          ,' `.   \\ ,'           _,     `''   ``.-'       `-  -..___,'"
            + "\n" + "         . ,.  .   `   __     .-'  _.-           `.     .__    \\"
            + "\n" + "         |. |`        \"  ;   !   ,.  |             `.    `.`'---'"
            + "\n" + "         ,| |C\\       ` /    | ,' |(]|            -. |-..--`"
            + "\n" + "        /  \"'--'       '      /___|__]        `.  `- |`."
            + "\n" + "       .       ,'                   ,   /       .    `. \\"
            + "\n" + "         \\                      .,-'  ,'         .     `-."
            + "\n" + "          x---..`.  -'  __..--'\"/\"\"\"\"\"  ,-.      |   |   |"
            + "\n" + "         / \\--._'-.,.--'     _`-    _. ' /       |     -.|"
            + "\n" + "        ,   .   `-..__ ...--'  _,.-' | `   ,.-.  ;   /  '|"
            + "\n" + "       .  _,'         '\"-----\"\"      |    `   | /  ,'    ;"
            + "\n" + "       |-'  .-.    `._               |     `._// ,'     /"
            + "\n" + "      _|    `-'   _,' \"`--.._________|        `,'    _ /."
            + "\n" + "     //\\   ,-._.'\"/\\__,.   _,\"     /_\\__/`. /'.-.'.-/_,`-' "
            + "\n" + "     `-\"`\"' v'    `\"  `-`-\"              `-'`-`  `'"
            + "\n");
      pokemonACSII.put("Ivysaur", ivysaur);
      
      String venusaur = new String("                              _._       _,._"
            + "\n" + "                           _.'   `. ' .'   _`."
            + "\n" + "                   ,\"\"\"/`\"\"-.-.,/. ` V'\\-,`.,--/\"\"\".\"-.."
            + "\n" + "                 ,'    `...,' . ,\\-----._|     `.   /   \\"
            + "\n" + "                `.            .`  -'`\"\" .._   :> `-'   `."
            + "\n" + "               ,'  ,-.  _,.-'| `..___ ,'   |'-..__   .._ L"
            + "\n" + "              .    \\_ -'   `-'     ..      `.-' `.`-.'_ .|"
            + "\n" + "              |   ,',-,--..  ,--../  `.  .-.    , `-.  ``."
            + "\n" + "              `.,' ,  |   |  `.  /'/,,.\\/  |    \\|   |"
            + "\n" + "                   `  `---'    `j   .   \\  .     '   j"
            + "\n" + "                 ,__`\"        ,'|`'\\_/`.'\\'        |\\-'-, _,."
            + "\n" + "          .--...`-. `-`. /    '- ..      _,    /\\ ,' .--\"'  ,'\"."
            + "\n" + "        _'-\"\"-    --  _`'-.../ __ '.'`-^,_`-\"\"\"\"---....__  ' _,-`"
            + "\n" + "      _.----`  _..--.'        |  \"`-..-\" __|'\"'         .\"\"-. \"\"'--.._"
            + "\n" + "     /        '    /     ,  _.+-.'  ||._'   \"\"\"\". .          `     .__\\"
            + "\n" + "    `---    /        /  / j'       _/|..`  -. `-`\\ \\   \\  \\   `.  \\ `-.."
            + "\n" + "   ,\" _.-' /    /` ./  /`_|_,-\"   ','|       `. | -'`._,   L  \\ .  `.   |"
            + "\n" + "   `\"' /  /  / ,__...-----| _.,  ,'            `|----.._`-.|' |. .` ..  ."
            + "\n" + "      /  '| /.,/   \\--.._ `-,' ,          .  '`.'  __,., '  ''``._ \\ \\`,'"
            + "\n" + "     /_,'---  ,     \\`._,-` \\ //  / . \\    `._,  -`,  / / _   |   `-L -"
            + "\n" + "      /       `.     ,  ..._ ' `_/ '| |\\ `._'       '-.'   `.,'     |"
            + "\n" + "     '         /    /  ..   `.  `./ | ; `.'    ,\"\" ,.  `.    \\      |"
            + "\n" + "      `.     ,'   ,'   | |\\  |       \"        |  ,'\\ |   \\    `    ,L"
            + "\n" + "      /|`.  /    '     | `-| '                  /`-' |    L    `._/  \\"
            + "\n" + "     / | .`|    |  .   `._.'                   `.__,'   .  |     |  (`"
            + "\n" + "    '-\"\"-'_|    `. `.__,._____     .    _,        ____ ,-  j     \".-'\"'"
            + "\n" + "           \\      `-.  \\/.    `\"--.._    _,.---'\"\"\\/  \"_,.'     /-'"
            + "\n" + "            )        `-._ '-.        `--\"      _.-'.-\"\"        `."
            + "\n" + "           ./            `,. `\".._________...\"\"_.-\"`.          _j"
            + "\n" + "          /_\\.__,\"\".   ,.'  \"`-...________.---\"     .\".   ,.  / \\"
            + "\n" + "                 \\_/\"\"\"-'                           `-'--(_,`\"`-` "
            + "\n");
      pokemonACSII.put("Venusaur", venusaur);
      
      String charmander = new String("                    _.--\"\"`-.."
            + "\n" + "                  ,'          `."
            + "\n" + "                ,'          __  `."
            + "\n" + "               /|          \" __   \\"
            + "\n" + "              , |           / |.   ."
            + "\n" + "              |,'          !_.'|   |"
            + "\n" + "            ,'             '   |   |"
            + "\n" + "           /              |`--'|   |"
            + "\n" + "          |                `---'   |"
            + "\n" + "           .   ,                   |                       ,\"."
            + "\n" + "            ._     '           _'  |                    , ' \\ `"
            + "\n" + "        `.. `.`-...___,...---\"\"    |       __,.        ,`\"   L,|"
            + "\n" + "        |, `- .`._        _,-,.'   .  __.-'-. /        .   ,    \\"
            + "\n" + "      -:..     `. `-..--_.,.<       `\"      / `.        `-/ |   ."
            + "\n" + "        `,         \"\"\"\"'     `.              ,'         |   |  ',,"
            + "\n" + "          `.      '            '            /          '    |'. |/"
            + "\n" + "            `.   |              \\       _,-'           |       ''"
            + "\n" + "              `._'               \\   '\"\\                .      |"
            + "\n" + "                 |                '     \\                `._  ,'"
            + "\n" + "                 |                 '     \\                 .'|"
            + "\n" + "                 |                 .      \\                | |"
            + "\n" + "                 |                 |       L              ,' |"
            + "\n" + "                 `                 |       |             /   '"
            + "\n" + "                  \\                |       |           ,'   /"
            + "\n" + "                ,' \\               |  _.._ ,-..___,..-'    ,'"
            + "\n" + "               /     .             .      `!             ,j'"
            + "\n" + "              /       `.          /        .           .'/"
            + "\n" + "             .          `.       /         |        _.'.'"
            + "\n" + "              `.          7`'---'          |------\"'_.'"
            + "\n" + "             _,.`,_     _'                ,''-----\"'"
            + "\n" + "         _,-_    '       `.     .'      ,\\"
            + "\n" + "         -\" /`.         _,'     | _  _  _.|"
            + "\n" + "          \"\"--'---\"\"\"\"\"'        `' '! |! /"
            + "\n" + "                                  `\" \" -'"
            + "\n");
      pokemonACSII.put("Charmander", charmander);
      
      String charmeleon = new String("                      ,-'`\\"
            + "\n" + "                  _,\"'    j"
            + "\n" + "           __....+       /               ."
            + "\n" + "       ,-'\"             /               ; `-._.'."
            + "\n" + "      /                (              ,'       .'"
            + "\n" + "     |            _.    \\             \\   ---._ `-."
            + "\n" + "     ,|    ,   _.'  Y    \\             `- ,'   \\   `.`."
            + "\n" + "     l'    \\ ,'._,\\ `.    .              /       ,--. l"
            + "\n" + "  .,-        `._  |  |    |              \\       _   l ."
            + "\n" + " /              `\"--'    /              .'       ``. |  )"
            + "\n" + ".\\    ,                 |                .        \\ `. '"
            + "\n" + "`.                .     |                '._  __   ;. \\'"
            + "\n" + "  `-..--------...'       \\                  `'  `-\"'.  \\"
            + "\n" + "      `......___          `._                        |  \\"
            + "\n" + "               /`            `..                     |   ."
            + "\n" + "              /|                `-.                  |    L"
            + "\n" + "             / |               \\   `._               .    |"
            + "\n" + "           ,'  |,-\"-.   .       .     `.            /     |"
            + "\n" + "         ,'    |     '   \\      |       `.         /      |"
            + "\n" + "       ,'     /|       \\  .     |         .       /       |"
            + "\n" + "     ,'      / |        \\  .    +          \\    ,'       .'"
            + "\n" + "    .       .  |         \\ |     \\          \\_,'        / j"
            + "\n" + "    |       |  L          `|      .          `        ,' '"
            + "\n" + "    |    _. |   \\          /      |           .     .' ,'"
            + "\n" + "    |   /  `|    \\        .       |  /        |   ,' .'"
            + "\n" + "    |   ,-..\\     -.     ,        | /         |,.' ,'"
            + "\n" + "    `. |___,`    /  `.   /`.       '          |  .'"
            + "\n" + "      '-`-'     j     ` /.\"7-..../|          ,`-'"
            + "\n" + "                |        .'  / _/_|          ."
            + "\n" + "                `,       `\"'/\"'    \\          `."
            + "\n" + "                  `,       '.       `.         |"
            + "\n" + "             __,.-'         `.        \\'       |"
            + "\n" + "            /_,-'\\          ,'        |        _."
            + "\n" + "             |___.---.   ,-'        .-':,-\"`\\,' ."
            + "\n" + "                  L,.--\"'           '-' |  ,' `-.\\"
            + "\n" + "                                        `.'"
            + "\n");
      pokemonACSII.put("Charmeleon", charmeleon);
      
      String charizard = new String("                 .\"-,.__"
            + "\n" + "                 `.     `.  ,"
            + "\n" + "              .--'  .._,'\"-' `."
            + "\n" + "             .    .'         `'"
            + "\n" + "             `.   /          ,'"
            + "\n" + "               `  '--.   ,-\"'"
            + "\n" + "                `\"`   |  \\"
            + "\n" + "                   -. \\, |"
            + "\n" + "                    `--Y.'      ___."
            + "\n" + "                         \\     L._, \\"
            + "\n" + "               _.,        `.   <  <\\                _"
            + "\n" + "             ,' '           `, `.   | \\            ( `"
            + "\n" + "          ../, `.            `  |    .\\`.           \\ \\_"
            + "\n" + "         ,' ,..  .           _.,'    ||\\l            )  '\"."
            + "\n" + "        , ,'   \\           ,'.-.`-._,'  |           .  _._`."
            + "\n" + "      ,' /      \\ \\        `' ' `--/   | \\          / /   ..\\"
            + "\n" + "    .'  /        \\ .         |\\__ - _ ,'` `        / /     `.`."
            + "\n" + "    |  '          ..         `-...-\"  |  `-'      / /        . `."
            + "\n" + "    | /           |L__           |    |          / /          `. `."
            + "\n" + "   , /            .   .          |    |         / /             ` `"
            + "\n" + "  / /          ,. ,`._ `-_       |    |  _   ,-' /               ` \\"
            + "\n" + " / .           \\\"`_/. `-_ \\_,.  ,'    +-' `-'  _,        ..,-.    \\`."
            + "\n" + ".  '         .-f    ,'   `    '.       \\__.---'     _   .'   '     \\ \\"
            + "\n" + "' /          `.'    l     .' /          \\..      ,_|/   `.  ,'`     L`"
            + "\n" + "|'      _.-\"\"` `.    \\ _,'  `            \\ `.___`.'\"`-.  , |   |    | \\"
            + "\n" + "||    ,'      `. `.   '       _,...._        `  |    `/ '  |   '     .|"
            + "\n" + "||  ,'          `. ;.,.---' ,'       `.   `.. `-'  .-' /_ .'    ;_   ||"
            + "\n" + "|| '              V      / /           `   | `   ,'   ,' '.    !  `. ||"
            + "\n" + "||/            _,-------7 '              . |  `-'    l         /    `||"
            + "\n" + ". |          ,' .-   ,' ||               | .-.        `.      .'     ||"
            + "\n" + " `'        ,'    `\".'    |               |    `.        '. -.'       `'"
            + "\n" + "          /      ,'      |               |,'    \\-.._,.'/'"
            + "\n" + "          .     /        .               .       \\    .''"
            + "\n" + "        .`.    |         `.             /         :_,'.'"
            + "\n" + "          \\ `...\\   _     ,'-.        .'         /_.-'"
            + "\n" + "           `-.__ `,  `'   .  _.>----''.  _  __  /"
            + "\n" + "                .'        /\"'          |  \"'   '_"
            + "\n" + "               /_|.-'\\ ,\".             '.'`__'-( \\"
            + "\n" + "                 / ,\"'\"\\,'               `/  `-.|\""
            + "\n");
      pokemonACSII.put("Charizard", charizard);
      
      String squirtle = new String("                     _,........__"
            + "\n" + "                  ,-'            \"`-."
            + "\n" + "                ,'                   `-."
            + "\n" + "              ,'                        \\"
            + "\n" + "            ,'                           ."
            + "\n" + "            .'\\               ,\"\".       `"
            + "\n" + "           ._.'|             / |  `       \\"
            + "\n" + "           |   |            `-.'  ||       `."
            + "\n" + "           |   |            '-._,'||       | \\"
            + "\n" + "           .`.,'             `..,'.'       , |`-."
            + "\n" + "           l                       .'`.  _/  |   `."
            + "\n" + "           `-.._'-   ,          _ _'   -\" \\  .     `"
            + "\n" + "      `.\"\"\"\"\"'-.`-...,---------','         `. `....__."
            + "\n" + "      .'        `\"-..___      __,'\\          \\  \\     \\"
            + "\n" + "      \\_ .          |   `\"\"\"\"'    `.           . \\     \\"
            + "\n" + "        `.          |              `.          |  .     L"
            + "\n" + "          `.        |`--...________.'.        j   |     |"
            + "\n" + "            `._    .'      |          `.     .|   ,     |"
            + "\n" + "               `--,\\       .            `7\"\"' |  ,      |"
            + "\n" + "                  ` `      `            /     |  |      |    _,-'\"\"\"`-."
            + "\n" + "                   \\ `.     .          /      |  '      |  ,'          `."
            + "\n" + "                    \\  v.__  .        '       .   \\    /| /              \\"
            + "\n" + "                     \\/    `\"\"\\\"\"\"\"\"\"\"`."
            + "       \\   \\  /.''                |"
            + "\n" + "                      `        .        `._ ___,j.  `/ .-       ,---.     |"
            + "\n" + "                      ,`-.      \\         .\"     `.  |/        j     `    |"
            + "\n" + "                     /    `.     \\       /         \\ /         |     /    j"
            + "\n" + "                    |       `-.   7-.._ .          |\"          '         /"
            + "\n" + "                    |          `./_    `|          |            .     _,'"
            + "\n" + "                    `.           / `----|          |-............`---'"
            + "\n" + "                      \\          \\      |          |"
            + "\n" + "                     ,'           )     `.         |"
            + "\n" + "                      7____,,..--'      /          |"
            + "\n" + "                                        `---.__,--.'"
            + "\n");
      pokemonACSII.put("Squirtle", squirtle);
      
      String wartortle = new String("           __                                _.--'\"7"
            + "\n" + "          `. `--._                        ,-'_,-  ,'"
            + "\n" + "           ,'  `-.`-.                   /' .'    ,|"
            + "\n" + "           `.     `. `-     __...___   /  /     - j"
            + "\n" + "             `.     `  `.-\"\"        \" .  /       /"
            + "\n" + "               \\     /                ` /       /"
            + "\n" + "                \\   /                         ,'"
            + "\n" + "                '._'_               ,-'       |"
            + "\n" + "                   | \\            ,|  |   ...-'"
            + "\n" + "                   || `         ,|_|  |   | `             _..__"
            + "\n" + "                  /|| |          | |  |   |  \\  _,_    .-\"     `-."
            + "\n" + "                 | '.-'          |_|_,' __!  | /|  |  /           \\"
            + "\n" + "         ,-...___ .=                  ._..'  /`.| ,`,.      _,.._ |"
            + "\n" + "        |   |,.. \\     '  `'        ____,  ,' `--','  |    /      |"
            + "\n" + "       ,`-..'  _)  .`-..___,---'_...._/  .'      '-...'   |      /"
            + "\n" + "      '.__' \"\"'      `.,------'\"'      ,/            ,     `.._.' `."
            + "\n" + "        `.             | `--........,-'.            .         \\     \\"
            + "\n" + "          `-.          .   '.,--\"\"     |           ,'\\        |      ."
            + "\n" + "             `.       /     |          L          ,\\  .       |  .,---."
            + "\n" + "               `._   '      |           \\        /  .  L      | /   __ `."
            + "\n" + "                  `-.       |            `._   ,    l   .    j |   '  `. ."
            + "\n" + "                    |       |               `\"' |  .    |   /  '      .' |"
            + "\n" + "                    |       |                   j  |    |  /  , `.__,'   |"
            + "\n" + "                    `.      L                 _.   `    j ,'-'           |"
            + "\n" + "                     |`\"---..\\._______,...,--' |   |   /|'      /        j"
            + "\n" + "                     '       |                 |   .  / |      '        /"
            + "\n" + "                      .      .              ____L   \\'  j    -',       /"
            + "\n" + "                     / `.     .          _,\"     \\   | /  ,-','      ,'"
            + "\n" + "                    /    `.  ,'`-._     /         \\  i'.,'_,'      .'"
            + "\n" + "                   .       `.      `-..'             |_,-'      _.'"
            + "\n" + "                   |         `._      |            ''/      _,-'"
            + "\n" + "                   |            '-..._\\             `__,.--'"
            + "\n" + "                  ,'           ,' `-.._`.            ."
            + "\n" + "                 `.    __      |       \"'`.          |"
            + "\n" + "                   `-\"'  `\"\"\"\"'            7         `."
            + "\n" + "                                          `---'--.,'\"`'"
            + "\n");
      pokemonACSII.put("Wartortle", wartortle);
      
      String blastoise = new String("                              _"
            + "\n" + "                   _,..-\"\"\"--' `,.-\"."
            + "\n" + "                 ,'      __.. --',  |"
            + "\n" + "               _/   _.-\"' |    .' | |       ____"
            + "\n" + "         ,.-\"\"'    `-\"+.._|     `.' | `-..,',--.`."
            + "\n" + "        |   ,.                      '    j 7    l \\__"
            + "\n" + "        |.-'                            /| |    j||  ."
            + "\n" + "        `.                   |         / L`.`\"\"','|\\  \\"
            + "\n" + "          `.,----..._       ,'`\"'-.  ,'   \\ `\"\"'  | |  l"
            + "\n" + "            Y        `-----'       v'    ,'`,.__..' |   ."
            + "\n" + "             `.                   /     /   /     `.|   |"
            + "\n" + "               `.                /     l   j       ,^.  |L"
            + "\n" + "                 `._            L       +. |._   .' \\|  | \\"
            + "\n" + "                   .`--...__,..-'\"\"'-._  l L  \"\"\"    |  |  \\"
            + "\n" + "                 .'  ,`-......L_       \\  \\ \\     _.'  ,'.  l"
            + "\n" + "              ,-\"`. / ,-.---.'  `.      \\  L..--\"'  _.-^.|   l"
            + "\n" + "        .-\"\".'\"`.  Y  `._'   '    `.     | | _,.--'\"     |   |"
            + "\n" + "         `._'   |  |,-'|      l     `.   | |\"..          |   l"
            + "\n" + "         ,'.    |  |`._'      |      `.  | |_,...---\"\"\"\"\"`    L"
            + "\n" + "        /   |   j _|-' `.     L       | j ,|              |   |"
            + "\n" + "       `--,\"._,-+' /`---^..../._____,.L',' `.             |\\  |"
            + "\n" + "          |,'      L                   |     `-.          | \\j"
            + "\n" + "                   .                    \\       `,        |  |"
            + "\n" + "                    \\                __`.Y._      -.     j   |"
            + "\n" + "                     \\           _.,'       `._     \\    |  j"
            + "\n" + "                     ,-\"`-----\"\"\"\"'           |`.    \\  7   |"
            + "\n" + "                    /  `.        '            |  \\    \\ /   |"
            + "\n" + "                   |     `      /             |   \\    Y    |"
            + "\n" + "                   |      \\    .             ,'    |   L_.-')"
            + "\n" + "                    L      `.  |            /      ]     _.-^._"
            + "\n" + "                     \\   ,'  `-7         ,-'      / |  ,'      `-._"
            + "\n" + "                    _,`._       `.   _,-'        ,',^.-            `."
            + "\n" + "                 ,-'     v....  _.`\"',          _:'--....._______,.-'"
            + "\n" + "               ._______./     /',,-'\"'`'--.  ,-'  `."
            + "\n" + "                        \"\"\"\"\"`.,'         _\\`----...' "
            + "\n" + "                               --------\"\"'"
            + "\n");
      pokemonACSII.put("Blastoise", blastoise);
      
      image = pokemonACSII.get(poke);
      if (image == null) {
         image = "No image\n";
         return image;
      } else {
         return image;
      }
   }
   
   
}
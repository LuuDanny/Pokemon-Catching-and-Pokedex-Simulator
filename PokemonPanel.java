import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;
/**
*Main Content Panel: Pokemon.
*@author Danny L. and Jerome W.
*@since 04/20/21
*/

public class PokemonPanel extends JPanel {
   
   //============= Constant Variables ==================
   /** Range for the random pokemon generation. */
   private static final int RANGE = 13;
   /** Range for the random coin flip of catching the pokemon. */
   private static final int COINFLIP = 2;
   /** String msg welcom. */
   private static final String WELCOME = new String(
         "  Welcome to the world of Pokemon. Let's catch them all!");
   /** String msg prompting hunting. */
   private static final String HUNTMSG = new String(
         "\n  --> Press \"Hunt\" to search for a new Pokemon.");
   /** String msg prompting catching. */
   private static final String CATCHMSG = new String(
         "\n  --> Press \"Catch\" to attempt to capture the Pokemon.");
   /** String msg for prompting naming. */
   private static final String NAMEMSG = new String(
         "\n  --> If you would like to name your pokemon, then enter the"
         + "\n      name into the \"Pokemon Name\" field and press \"Name\"");
   
   //============= Instance Variables ==================
   /** pokedex BST. */
   private PokeTree pokedexBST = new PokeTree();
   /** Pokemon object. */
   private Pokemon poke = new Bulbasaur();
   /** string variable. */
   private String placeHolder = new String("   Pokemon Name");
   /** string variable. */
   private String sTemp = new String("");
   /** int variable. */
   private int i = 0;
   /** int variable keeps track of how many pokemon have been caught. */
   private int counter = -1;
   /** boolean variable. */
   private boolean foundPokemon = false;
   /** boolean variable. */
   private boolean namePokemon = false;
   /** Empty array list. */
   private ArrayList<Pokemon> backpackArray = new ArrayList<>();
   /** Priorty Queue sorted order depends on user's choise. */
   private PriorityQueue<Pokemon> backpackQueue = new PriorityQueue<>();
   
   /** Empty image. */
   private ImageIcon empty = new ImageIcon("Images/Empty.png");
   /** Welcome image. */
   private ImageIcon welcome = new ImageIcon("Images/Welcome.png");
   /** Pokemon image. */
   private ImageIcon pokeball = new ImageIcon("Images/Pokeball.png");
   /** Pokedex image. */
   private ImageIcon pokedex = new ImageIcon("Images/Pokedex.png");
   /** Backpack image. */
   private ImageIcon backpack = new ImageIcon("Images/Backpack.png");
   /** Bulbasaur image. */
   private ImageIcon bulbasaur = new ImageIcon("Images/Bulbasaur.png");
   /** Ivysaur image. */
   private ImageIcon ivysaur = new ImageIcon("Images/Ivysaur.png");
   /** Venusaur image. */
   private ImageIcon venusaur = new ImageIcon("Images/Venusaur.png");
   /** Charmander image. */
   private ImageIcon charmander = new ImageIcon("Images/Charmander.png");
   /** Charmeleon image. */
   private ImageIcon charmeleon = new ImageIcon("Images/Charmeleon.png");
   /** Charizard image. */
   private ImageIcon charizard = new ImageIcon("Images/Charizard.png");
   /** Squirtle image. */
   private ImageIcon squirtle = new ImageIcon("Images/Squirtle.png");
   /** Wartortle image. */
   private ImageIcon wartortle = new ImageIcon("Images/Wartortle.png");
   /** Blastoise image. */
   private ImageIcon blastoise = new ImageIcon("Images/Blastoise.png");
   /** Eevee image. */
   private ImageIcon eevee = new ImageIcon("Images/Eevee.png");
   /** Vaporeon image. */
   private ImageIcon vaporeon = new ImageIcon("Images/Vaporeon.png");
   /** Jolteon image. */
   private ImageIcon jolteon = new ImageIcon("Images/Jolteon.png");
   /** Flareon image. */
   private ImageIcon flareon = new ImageIcon("Images/Flareon.png");
   
   /** Text field. */
   private JTextField namingField = new JTextField(placeHolder, 12);
   /** Text area. */
   private JTextArea textArea = new JTextArea(5, 40);
   /** Text area. */
   private JTextArea textArea2 = new JTextArea(50, 20);
   /** Text pane. */
   private JTextArea textPokedex = new JTextArea("");
   /** Text pane. */
   private JTextArea textBackpack = new JTextArea(20, 31);
   
   /** Make textPokedex scrollable. */ 
   private JScrollPane scroll = new JScrollPane(textPokedex, 
       JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
       JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       
   /** Make textBackpack scrollable. */ 
   private JScrollPane scrollBag = new JScrollPane(textBackpack, 
       JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
       JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

   /** Label for pokemon image. */
   private JLabel image = new JLabel(welcome);
   /** Label. */
   private JLabel lPokemon = new JLabel("Pokemon");
   /** Label. */
   private JLabel lPokedex = new JLabel("Pokedex");
   /** Label. */
   private JLabel lBackpack = new JLabel("Backpack");
   /** Label. */
   private JLabel lSort = new JLabel("Sort By:  ");
   
   /** Button. */
   private JButton bPokemon = new JButton(pokeball);
   /** Button. */
   private JButton bPokedex = new JButton(pokedex);
   /** Button. */
   private JButton bBackpack = new JButton(backpack);
   /** Button. */
   private JButton bSort = new JButton("Sort");
   /** Button. */
   private JButton bHunt = new JButton("Hunt");
   /** Button. */
   private JButton bCatch = new JButton("Catch");
   /** Button. */
   private JButton bName = new JButton("Name");
   
   /** Choise. */
   private static Choice sortChoice = new Choice();

   /** Top Panel: Holds Card-Panels. */
   private JPanel deckPanel = new JPanel();
   
   /** Card-Panel: Pokemon. */
   private JPanel cardPokemon = new JPanel();
   /** Pokemon Sub Panel. */
   private JPanel pokemonTop = new JPanel();
   /** Pokemon Sub Panel. */
   private JPanel pokemonBottom = new JPanel();
   /** Pokemon Sub Panel. */
   private JPanel pokemonContainer = new JPanel();
   /** Pokemon Sub Panel. */
   private JPanel pokemonContainerTop = new JPanel();
   /** Pokemon Sub Panel. */
   private JPanel pokemonContainerMiddle = new JPanel();
   /** Pokemon Sub Panel. */
   private JPanel pokemonContainerBottom = new JPanel();
   /** Pokemon Sub Panel. */
   private JPanel pokemonContainerButton = new JPanel();
     
   /** Card-Panel: Pokedex. */
   private JPanel cardPokedex = new JPanel();
   /** Pokedex Sub Panel. */
   private JPanel pokedexTop = new JPanel();
   /** Pokedex Sub Panel. */
   private JPanel pokedexBottom = new JPanel();
   /** Pokemon Sub Panel. */
   private JPanel pokedexContainer = new JPanel();
   
   /** Card-Panel: Backpack. */
   private JPanel cardBackpack = new JPanel();
   /** Backpack Sub Panel. */
   private JPanel backpackTop = new JPanel();
   /** Backpack Sub Panel. */
   private JPanel backpackBottom = new JPanel();
   /** Backpack Sub Panel. */
   private JPanel backpackContainer = new JPanel();
   /** Backpack Sub Panel. */
   private JPanel backpackContainerTop = new JPanel();
   /** Backpack Sub Panel. */
   private JPanel backpackContainerBottom = new JPanel();
   
   /**Bottom Panel: Buttons for card navigation. */
   private JPanel buttonPanel = new JPanel();
   /** Pokemon Sub Panel. */
   private JPanel buttonContainer = new JPanel();

   /** Background Color .*/
   private Color cBlue = new Color(142, 239, 255);
   
   /** Border margin 1.*/
   private Border margin1 = new MatteBorder(10, 0, 0, 0, cBlue);
   /** Border margin 2.*/
   private Border margin2 = new MatteBorder(0, 0, 20, 0, cBlue);
   /** Border Black line.*/
   private Border blackline = BorderFactory.createLineBorder(Color.black, 2, true);
   /** Border Titled border.*/
   private TitledBorder title = BorderFactory.createTitledBorder(blackline, "Gotta catch 'em all!");
   
   /** Font: Header text.*/
   private Font headerF = new Font("Cambrian", Font.BOLD, 20);
   /** Font: General text.*/
   private Font textF = new Font("Arial", Font.PLAIN, 15);
   /** Font: Title border text.*/
   private Font titleF = new Font("Arial", Font.ITALIC, 14);
  
   /**
   * Constructor holds everything.
   */
   public PokemonPanel() { 
      //Design of text areas, fields, and scroll
      textArea.setMaximumSize(textArea.getPreferredSize());
      textArea.setBorder(blackline);
      textArea.setEditable(false);
      textArea.setFont(textF);
      textArea2.setMaximumSize(textArea2.getPreferredSize());
      textArea2.setBorder(blackline);
      textArea2.setEditable(false);
      textArea2.setFont(textF);
      scroll.setBorder(blackline);
      scroll.setMaximumSize(new Dimension(407, 431));
      textPokedex.setBorder(null);
      textPokedex.setEditable(false);
      textPokedex.setFont(textF);
      scrollBag.setBorder(blackline);
      scrollBag.setMaximumSize(textBackpack.getPreferredSize());
      textBackpack.setBorder(null);
      textBackpack.setEditable(false);
      textBackpack.setFont(textF);
      namingField.setMaximumSize(namingField.getPreferredSize());
      namingField.setBorder(blackline);
      namingField.setFont(textF);
      namingField.setForeground(new Color(150, 150, 150)); 
      lPokemon.setFont(headerF);
      lPokedex.setFont(headerF);
      lBackpack.setFont(headerF);
      
      //Design of Buttons and Choise
      bHunt.setMaximumSize(new Dimension(70, 35));
      bCatch.setMaximumSize(new Dimension(70, 35));
      bName.setMaximumSize(new Dimension(70, 35));
      bSort.setMaximumSize(new Dimension(70, 35));
      sortChoice.setMaximumSize(new Dimension(119, 35));
      title.setTitleFont(titleF);
      
      //Design of Main Panel
      this.setLayout(new BorderLayout());
      this.setPreferredSize(new Dimension(700, 740));
      deckPanel.setLayout(new CardLayout());
      
      //Design for Pokemon Card-Panel and its sub-panels
      cardPokemon.setLayout(new BorderLayout());
      pokemonTop.setBackground(cBlue);
      pokemonBottom.setBackground(cBlue);
      pokemonContainer.setLayout(new BoxLayout(pokemonContainer, BoxLayout.Y_AXIS));
      pokemonContainer.setPreferredSize(new Dimension(600, 500));
      pokemonContainer.setBorder(new CompoundBorder(margin1, blackline));
      pokemonContainerTop.setLayout(new BoxLayout(pokemonContainerTop, BoxLayout.X_AXIS));
      pokemonContainerMiddle.setLayout(new BoxLayout(pokemonContainerMiddle, BoxLayout.X_AXIS));
      pokemonContainerBottom.setLayout(new BoxLayout(pokemonContainerBottom, BoxLayout.X_AXIS));
      pokemonContainerBottom.setBorder(title);
      pokemonContainerButton.setLayout(new BoxLayout(pokemonContainerButton, BoxLayout.Y_AXIS));
      
      //Design for Pokedex Card-Panel and its sub-panels
      cardPokedex.setLayout(new BorderLayout());
      pokedexTop.setBackground(cBlue);
      pokedexBottom.setBackground(cBlue);
      pokedexContainer.setLayout(new BoxLayout(pokedexContainer, BoxLayout.X_AXIS));
      pokedexContainer.setPreferredSize(new Dimension(600, 500));
      pokedexContainer.setBorder(new CompoundBorder(margin1, blackline));
      
      //Design for Backpack Card-Panel and its sub-panels
      cardBackpack.setLayout(new BorderLayout());
      backpackTop.setBackground(cBlue);
      backpackBottom.setBackground(cBlue);
      backpackContainer.setLayout(new BoxLayout(backpackContainer, BoxLayout.Y_AXIS));
      backpackContainer.setPreferredSize(new Dimension(600, 500));
      backpackContainer.setBorder(new CompoundBorder(margin1, blackline));
      backpackContainerTop.setLayout(new BoxLayout(backpackContainerTop, BoxLayout.X_AXIS));
      backpackContainerTop.setPreferredSize(new Dimension(300, 50));
      backpackContainerTop.setBorder(blackline);
      backpackContainerBottom.setPreferredSize(new Dimension(400, 100));
   
      //Design for Button panel
      buttonPanel.setBackground(cBlue);
      buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));
      buttonContainer.setBorder(new CompoundBorder(margin2, blackline));
   
      //========================== Main Panel ==========================
      this.add("Center", deckPanel);
      this.add("South", buttonPanel);
      
      //========================== Deck Panel ==========================
      deckPanel.add(cardPokemon, "pokemon");
      deckPanel.add(cardPokedex, "pokedex");
      deckPanel.add(cardBackpack, "backpack");
      
      //========================== Pokemon Card Panel ==========================
      cardPokemon.add("North", pokemonTop);
      cardPokemon.add("Center", pokemonBottom);
      
      pokemonTop.add(lPokemon);
      pokemonBottom.add(pokemonContainer);
      
      pokemonContainer.add(Box.createRigidArea(new Dimension(0, 20)));
      pokemonContainer.add(pokemonContainerTop);
      pokemonContainer.add(Box.createRigidArea(new Dimension(0, 5)));
      pokemonContainer.add(pokemonContainerMiddle);
      pokemonContainer.add(Box.createRigidArea(new Dimension(0, 5)));
      pokemonContainer.add(pokemonContainerBottom);
      pokemonContainer.add(Box.createRigidArea(new Dimension(0, 20)));
      
      pokemonContainerTop.add(Box.createRigidArea(new Dimension(20, 0)));
      pokemonContainerTop.add(image);
      pokemonContainerTop.add(Box.createRigidArea(new Dimension(60, 0)));
      pokemonContainerTop.add(textArea2);
      
      pokemonContainerMiddle.add(Box.createRigidArea(new Dimension(330, 40)));
      pokemonContainerMiddle.add(namingField);
      pokemonContainerMiddle.add(Box.createRigidArea(new Dimension(14, 0)));
      pokemonContainerMiddle.add(bName);
      
      pokemonContainerBottom.add(Box.createRigidArea(new Dimension(10, 100)));
      pokemonContainerBottom.add(textArea);
      pokemonContainerBottom.add(Box.createRigidArea(new Dimension(12, 0)));
      pokemonContainerBottom.add(pokemonContainerButton);
      pokemonContainerBottom.add(Box.createRigidArea(new Dimension(10, 0)));
      
      pokemonContainerButton.add(bHunt);
      pokemonContainerButton.add(Box.createRigidArea(new Dimension(0, 10)));
      pokemonContainerButton.add(bCatch);
      
      textArea.setText(WELCOME + HUNTMSG);
      
      namingField.addFocusListener(new GUIFocusListener());
      bName.addActionListener(new GUIListener());
      bHunt.addActionListener(new GUIListener());
      bCatch.addActionListener(new GUIListener());
      
      //========================== Pokedex Card-Panel ==========================
      cardPokedex.add("North", pokedexTop);
      cardPokedex.add("Center", pokedexBottom);
      
      pokedexTop.add(lPokedex);
      pokedexBottom.add(pokedexContainer);
   
      pokedexContainer.add(Box.createRigidArea(new Dimension(94, 0)));
      pokedexContainer.add(scroll); //Contains textPokedex
      
      //========================== Backpack Card-Panel ==========================
      cardBackpack.add("North", backpackTop);
      cardBackpack.add("Center", backpackBottom);
      
      backpackTop.add(lBackpack);
      backpackBottom.add(backpackContainer);
      
      backpackContainer.add(Box.createRigidArea(new Dimension(0, 28)));
      backpackContainer.add(backpackContainerTop);
      backpackContainer.add(Box.createRigidArea(new Dimension(0, 12)));
      backpackContainer.add(backpackContainerBottom);
      backpackContainer.add(Box.createRigidArea(new Dimension(0, 20)));
      
      backpackContainerTop.add(Box.createRigidArea(new Dimension(20, 0)));
      backpackContainerTop.add(lSort);
      backpackContainerTop.add(sortChoice);
      backpackContainerTop.add(Box.createRigidArea(new Dimension(125, 0)));
      backpackContainerTop.add(bSort);
      backpackContainerTop.add(Box.createRigidArea(new Dimension(17, 0)));
      
      backpackContainerBottom.add(scrollBag);
      
      //Adding backpack sorting options
      sortChoice.add("Recent");
      sortChoice.add("Number");
      sortChoice.add("Name");
      sortChoice.add("HP");
      sortChoice.add("CP");
      
      bSort.addActionListener(new GUIListener());
      
      //========================== Button Panel ==========================
      buttonPanel.add(buttonContainer);
      
      buttonContainer.add(Box.createRigidArea(new Dimension(36, 140)));
      buttonContainer.add(bPokemon);
      buttonContainer.add(Box.createRigidArea(new Dimension(36, 0)));
      buttonContainer.add(bPokedex);
      buttonContainer.add(Box.createRigidArea(new Dimension(36, 0)));
      buttonContainer.add(bBackpack);
      buttonContainer.add(Box.createRigidArea(new Dimension(37, 0)));
      
      bPokemon.addActionListener(new GUIListener()); 
      bPokedex.addActionListener(new GUIListener()); 
      bBackpack.addActionListener(new GUIListener()); 
   }
   
   /**
   * private class for FocusListener.
   */
   private class GUIFocusListener implements FocusListener {
      /**
      * Focus gained.
      * @param event Field gains focus
      */
      public void focusGained(FocusEvent event) {  
         if (namingField.getText().equals(placeHolder)) {
            namingField.setText("");  
            namingField.setForeground(new Color(50, 50, 50));
         }  
      }
      
      /**
      * Focus lost.
      * @param event Field gains focus
      */
      public void focusLost(FocusEvent event) { 
         if (namingField.getText().isEmpty()) {  
            namingField.setText(placeHolder);  
            namingField.setForeground(new Color(150, 150, 150));  
         }  
      } 
   } //Closes Gui focus listener
   
   /**
   * Get Gets the user's choise of sort for the backpack.
   * Used in Pokemon
   * @return choice menu index
   */
   public static int getChoice() {
      return sortChoice.getSelectedIndex();
   }
   
   /**
   * private class for ActionListener.
   */
   private class GUIListener implements ActionListener {
      /**only method for ActionListener, actionPerformed.
      * @param event button is clicked
      */
      public void actionPerformed(ActionEvent event) {
         CardLayout card = (CardLayout) (deckPanel.getLayout());
         Random ranNum = new Random();
         String tempName = new String("");
      
         //Changes the top-panel(card) to the pokemon capture panel
         if (event.getSource() == bPokemon) {
            card.show(deckPanel, "pokemon");
         }
         //Changes the top-panel(card) to the pokedex panel
         if (event.getSource() == bPokedex) {
            card.show(deckPanel, "pokedex");
            textPokedex.setText(pokedexBST.printPokeTree());
         }
         //Changes the top-panel(card) to the backpack panel
         if (event.getSource() == bBackpack) {
            card.show(deckPanel, "backpack");
         }
         //Sorts the backpack depending on the users choice
         if (event.getSource() == bSort) {
            //Resets string value
            sTemp = "";
            //Sort by recent
            if (sortChoice.getSelectedIndex() == 0) {
               for (i = backpackArray.size() - 1; i >= 0; i--) {
                  sTemp = sTemp + backpackArray.get(i).toString() + "\n";
               }
               textBackpack.setText(sTemp);
            } //Sort by everything else
            else {
               //Adds all the pokemon in the array to the priorityQueue
               for (i = 0; i < backpackArray.size(); i++) {
                  backpackQueue.add(backpackArray.get(i));
               }
               //Prints the pokemon in the priority queue in the order based on user's choise
               while (backpackQueue.size() > 0) {
                  sTemp = sTemp + backpackQueue.poll().toString() + "\n";
               }
               textBackpack.setText(sTemp); 
            }
         }
         //Randomly generates a new pokemon to capture
         if (event.getSource() == bHunt) {
            i = ranNum.nextInt(RANGE) + 1;
            foundPokemon = true;
            namePokemon = false;
            switch(i) {
               case 1:
                  image.setIcon(bulbasaur);
                  poke = new Bulbasaur();
                  hunt(poke);
                  break;
               case 2:
                  image.setIcon(ivysaur);
                  poke = new Ivysaur();
                  hunt(poke);
                  break;
               case 3:
                  image.setIcon(venusaur);
                  poke = new Venusaur();
                  hunt(poke);
                  break;
               case 4:
                  image.setIcon(charmander);
                  poke = new Charmander();
                  hunt(poke);
                  break;
               case 5:
                  image.setIcon(charmeleon);
                  poke = new Charmeleon();
                  hunt(poke);
                  break;
               case 6:
                  image.setIcon(charizard);
                  poke = new Charizard();
                  hunt(poke);
                  break;
               case 7:
                  image.setIcon(squirtle);
                  poke = new Squirtle();
                  hunt(poke);
                  break;
               case 8:
                  image.setIcon(wartortle);
                  poke = new Wartortle();
                  hunt(poke);
                  break;
               case 9:
                  image.setIcon(blastoise);
                  poke = new Blastoise();
                  hunt(poke);
                  break; 
               case 10:
                  image.setIcon(eevee);
                  poke = new Eevee();
                  hunt(poke);
                  break;
               case 11:
                  image.setIcon(vaporeon);
                  poke = new Vaporeon();
                  hunt(poke);
                  break;   
               case 12:
                  image.setIcon(jolteon);
                  poke = new Jolteon();
                  hunt(poke);
                  break;
               case 13:
                  image.setIcon(flareon);
                  poke = new Flareon();
                  hunt(poke);
                  break;
               default:
                  System.out.println("Ops");
                  break;
            }
         }
         //Attemps to capture the pokemon
         if (event.getSource() == bCatch) {
            i = ranNum.nextInt(COINFLIP);
            // There is no pokemon
            if (!foundPokemon) { 
               textArea.setText("  There is no Pokemon to catch. Go hunting!"
                     + HUNTMSG);
            } // The pokemon escapes
            else if (i == 0) { 
               textArea.setText("  " + poke.getSpecies() + " has escaped!" 
                     + HUNTMSG);
               //Resests the fields
               image.setIcon(empty);
               textArea2.setText(null);
               foundPokemon = false;
            } //The pokemon is captured
            else if (i == 1) { 
               pokedexBST.add(poke);
               backpackArray.add(poke);
               counter++;
               textArea.setText("  Congragulations, you caught " + poke.getSpecies() + "!" 
                     + HUNTMSG + NAMEMSG);
               //Resests the fields
               image.setIcon(empty);
               textArea2.setText(null);
               foundPokemon = false;
               namePokemon = true;
            }
         }
         //Attemps to name the pokemon
         if (event.getSource() == bName) {
            // There is pokemon, but have not catched it yet
            if (!namePokemon && foundPokemon) { 
               textArea.setText("  You have not caught this pokemon yet. Go hunting/catching!"
                     + HUNTMSG + CATCHMSG);
            } // There is no pokemon
            else if (!namePokemon) { 
               textArea.setText("  There is no Pokemon to name. Go hunting!"
                     + HUNTMSG);
            } // There is pokemon, but no name
            else if (namingField.getText().equals(placeHolder) 
                  || namingField.getText().trim().equals("")) { 
               textArea.setText("  No name chosen. " 
                     + "\n  " + poke.getSpecies() + "'s name has been set to " + poke.getSpecies()
                     + "." + HUNTMSG);
            } // There is pokemon and a name has been chosen
            else { 
               textArea.setText("  Congragulations! " + poke.getSpecies() 
                     + "'s name has been set to " + namingField.getText() + "."
                     + HUNTMSG);
               poke.setName(namingField.getText());
               backpackArray.set(counter, poke);
               
            }
            // Reset naming field
            namePokemon = false;
            namingField.setText(placeHolder);
            namingField.setForeground(new Color(150, 150, 150)); 
         }
      } //Closes Action performed
   } //Closes Gui listener
   
   //============= hunt method ==================
   /**
   * Adds the spotted pokemon to the pokedex, increasing its seen count.
   * Sets the textArea's text depending on the pokemon
   * @param p the pokemon being hunted object
   */
   public void hunt(Pokemon p) {
      //Add pokemon to BST, incrementing the seen counter
      pokedexBST.seen(p);
      textArea.setText("  A wild " + p.getSpecies() + " has appeared!" 
            + HUNTMSG + CATCHMSG);
      
      textArea2.setText("   Number: " + p.getNumber()
            + "\n\n   Species: " + p.getSpecies() 
            + "\n\n   Type: " + p.getType()
            + "\n\n   Height: " + p.getHeight() 
            + "\n\n   Weight: " + p.getWeight()
            + "\n\n   HP: " + p.getHP() 
            + "\n\n   CP: " + p.getCP() + "\n");
   }
} // Closes class


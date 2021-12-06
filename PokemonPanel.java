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
   private static final int RANGE = 9;
   /** Range for the random coin flip of catching the pokemon. */
   private static final int COINFLIP = 2;
   
   //============= Instance Variables ==================
   /** pokedex BST. */
   private PokeTree pokedexBST = new PokeTree();
   /** Pokemon object. */
   private Pokemon poke = null;
   /** int variable. */
   private int i = 0;
   /** boolean variable. */
   private boolean foundPokemon = false;
   
   /** Empty image. */
   private ImageIcon empty = new ImageIcon("Images/Empty.png");
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
   
   /** Text field. */
   private JTextField namingField = new JTextField(40);
   /** Text area. */
   private JTextArea textArea = new JTextArea(5, 40);
   /** Text area. */
   private JTextArea textArea2 = new JTextArea(40, 20);
   /** Text pane. */
   private JTextArea textPokedex = new JTextArea("");
   
   /** Make textPokedex scrollable. */ 
   private JScrollPane scroll = new JScrollPane(textPokedex, 
       JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
       JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);



   /** Label for pokemon image. */
   private JLabel image = new JLabel(empty);
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
   
   /** Choise. */
   private Choice sortChoise = new Choice();

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
   
   /**Bottom Panel: Buttons for card navigation. */
   private JPanel buttonPanel = new JPanel();
   /** Pokemon Sub Panel. */
   private JPanel buttonContainer = new JPanel();

   /** Background Color .*/
   private Color cBlue = new Color(142, 239, 255);
   /** Background Color .*/
   private Color cRed = new Color(255, 20, 0);
   
   /** Border margin 1.*/
   private Border margin1 = new MatteBorder(10, 0, 0, 0, cBlue);
   /** Border margin 2.*/
   private Border margin2 = new MatteBorder(0, 0, 20, 0, cBlue);
   /** Border Black line.*/
   private Border blackline = BorderFactory.createLineBorder(Color.black, 2, true);
   /** Border Titled border.*/
   private TitledBorder title = BorderFactory.createTitledBorder(blackline, "Gotta catch 'em all!");
   
   /** Set new font face and size.*/
   private Font f = new Font("Arial", Font.PLAIN, 15);
  
   /**
   * Constructor holds everything.
   */
   public PokemonPanel() { 
      //Design of titled border
      title.setTitleFont(new Font("Arial", Font.ITALIC, 14));
      
      //Design of text areas
      textArea.setMaximumSize(textArea.getPreferredSize());
      textArea.setBorder(blackline);
      textArea.setEditable(false);
      textArea.setFont(f);
      textArea2.setMaximumSize(textArea2.getPreferredSize());
      textArea2.setBorder(blackline);
      textArea2.setEditable(false);
      textArea2.setFont(f);
      textArea2.setMargin(new Insets(15, 15, 15, 15));
      scroll.setBorder(blackline);
      scroll.setMaximumSize(new Dimension(400, 380));
      textPokedex.setBorder(null);
      textPokedex.setEditable(false);
      textPokedex.setFont(f);
      
      //Design of buttons
      bHunt.setMaximumSize(new Dimension(70, 35));
      bCatch.setMaximumSize(new Dimension(70, 35));
      
      //Design of Main Panel
      this.setLayout(new BorderLayout());
      this.setPreferredSize(new Dimension(700, 670));
      deckPanel.setLayout(new CardLayout());
      
      //Design for Pokemon Card-Panel and its sub-panels
      cardPokemon.setLayout(new BorderLayout());
      pokemonTop.setBackground(cBlue);
      pokemonBottom.setBackground(cBlue);
      pokemonContainer.setLayout(new BoxLayout(pokemonContainer, BoxLayout.Y_AXIS));
      pokemonContainer.setPreferredSize(new Dimension(600, 440));
      pokemonContainer.setBorder(new CompoundBorder(margin1, blackline));
      pokemonContainerTop.setLayout(new BoxLayout(pokemonContainerTop, BoxLayout.X_AXIS));
      pokemonContainerBottom.setLayout(new BoxLayout(pokemonContainerBottom, BoxLayout.X_AXIS));
      pokemonContainerBottom.setBorder(title);
      pokemonContainerButton.setLayout(new BoxLayout(pokemonContainerButton, BoxLayout.Y_AXIS));
      
      //Design for Pokedex Card-Panel and its sub-panels
      cardPokedex.setLayout(new BorderLayout());
      pokedexTop.setBackground(cBlue);
      pokedexBottom.setBackground(cBlue);
      pokedexContainer.setLayout(new BoxLayout(pokedexContainer, BoxLayout.X_AXIS));
      pokedexContainer.setPreferredSize(new Dimension(600, 440));
      pokedexContainer.setBorder(new CompoundBorder(margin1, blackline));
      
      //Design for Backpack Card-Panel and its sub-panels
      cardBackpack.setLayout(new BorderLayout());
      backpackTop.setLayout(new BoxLayout(backpackTop, BoxLayout.X_AXIS));

      //Design for Button panel
      buttonPanel.setBackground(cBlue);
      buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));
      buttonContainer.setBorder(new CompoundBorder(margin2, blackline));
      
      //Temporary so that we can see the borders of each panel
      backpackTop.setBackground(cBlue);
      backpackBottom.setBackground(cRed);
 
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
      pokemonContainer.add(Box.createRigidArea(new Dimension(0, 15)));
      pokemonContainer.add(pokemonContainerBottom);
      pokemonContainer.add(Box.createRigidArea(new Dimension(0, 20)));
      
      pokemonContainerTop.add(Box.createRigidArea(new Dimension(20, 0)));
      pokemonContainerTop.add(image);
      pokemonContainerTop.add(Box.createRigidArea(new Dimension(60, 0)));
      pokemonContainerTop.add(textArea2);
      
      pokemonContainerBottom.add(Box.createRigidArea(new Dimension(10, 100)));
      pokemonContainerBottom.add(textArea);
      pokemonContainerBottom.add(Box.createRigidArea(new Dimension(12, 0)));
      pokemonContainerBottom.add(pokemonContainerButton);
      pokemonContainerBottom.add(Box.createRigidArea(new Dimension(10, 0)));
      
      pokemonContainerButton.add(bHunt);
      pokemonContainerButton.add(Box.createRigidArea(new Dimension(0, 10)));
      pokemonContainerButton.add(bCatch);
      
      bHunt.addActionListener(new GUIListener());
      bCatch.addActionListener(new GUIListener());
      
      //========================== Pokedex Card-Panel ==========================
      cardPokedex.add("North", pokedexTop);
      cardPokedex.add("Center", pokedexBottom);
      
      pokedexTop.add(lPokedex);
      pokedexBottom.add(pokedexContainer);

      pokedexContainer.add(Box.createRigidArea(new Dimension(97, 0)));
      pokedexContainer.add(scroll); //Contains textPokedex
      
      //========================== Backpack Card-Panel ==========================
      cardBackpack.add("North", backpackTop);
      cardBackpack.add("Center", backpackBottom);
      
      //Adding stuff to top sub-panel of backpack card
      backpackTop.add(Box.createRigidArea(new Dimension(10, 0)));
      backpackTop.add(lBackpack);
      backpackTop.add(Box.createHorizontalGlue());
      backpackTop.add(lSort);
      backpackTop.add(sortChoise);
      backpackTop.add(Box.createRigidArea(new Dimension(25, 0)));
      backpackTop.add(bSort);
      backpackTop.add(Box.createRigidArea(new Dimension(25, 0)));
      //Adding backpack sorting options
      sortChoise.add("Recent");
      sortChoise.add("Number");
      sortChoise.add("Name");
      sortChoise.add("HP");
      sortChoise.add("CP");
      
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
   * private class for ActionListener.
   */
   private class GUIListener implements ActionListener {
      /**only method for ActionListener, actionPerformed.
      * @param event button is clicked
      */
      public void actionPerformed(ActionEvent event) {
         CardLayout card = (CardLayout) (deckPanel.getLayout());
         Random ranNum = new Random();

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
         //Randomly generates a new pokemon to capture
         if (event.getSource() == bHunt) {
            i = ranNum.nextInt(RANGE) + 1;
            foundPokemon = true;
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
               default:
                  System.out.println("Error");
                  break;
            }
         }
         //Attemps to capture the pokemon
         if (event.getSource() == bCatch) {
            i = ranNum.nextInt(COINFLIP);
            if (!foundPokemon) {
               textArea.setText("  There is no Pokemon to catch. Go hunting!"
                     + "\n\n  --> Press the \"Hunt\" button to search for a Pokemon.");
            } else if (true) {
               textArea.setText("  " + poke.getSpecies() + " has escaped!" 
                     + "\n\n  --> Press the \"Hunt\" button to search for a new Pokemon.");
               //Resests the fields
               image.setIcon(empty);
               textArea2.setText(null);
               foundPokemon = false;
            } else {
            
            }
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
      pokedexBST.seen(p);
      textArea.setText("  A wild " + p.getSpecies() + " has appeared!" 
            + "\n\n  --> Press the \"Hunt\" button to search for a new Pokemon."
            + "\n  --> Press the \"Catch\" button to attempt to capture the Pokemon.");
      textArea2.setText("   Number: " + p.getNumber() 
            + "\n\n   Species: " + p.getSpecies() 
            + "\n\n   Type: " + p.getType()
            + "\n\n   Height: " + p.getHeight() 
            + "\n\n   Weight: " + p.getWeight()
            + "\n\n   HP: " + p.getHP() 
            + "\n\n   CP: " + p.getCP());
   }

   
   
} // Closes class


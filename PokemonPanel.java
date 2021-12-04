import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.util.Random;


/**
*Main Content Panel: Pokemon.
*@author Danny Luu
*@since 04/20/21
*/

public class PokemonPanel extends JPanel {
   
   
   /** Pokemon image. */
   ImageIcon pokemon = new ImageIcon("Images/Pokemon.png");
   /** Pokedex image. */
   ImageIcon pokedex = new ImageIcon("Images/Pokedex.png");
   /** Backpack image. */
   ImageIcon backpack = new ImageIcon("Images/Backpack.png");
   /** Charmander image. */
   ImageIcon charmander = new ImageIcon("Images/Charmander.png");
   /** Charizard image. */
   ImageIcon charizard = new ImageIcon("Images/Charizard.png");
   
   
   /** Text area. */
   private JTextArea textArea = new JTextArea(5, 40);
   /** Text area. */
   private JTextArea textArea2 = new JTextArea(40, 20);

   /** Label image. */
   JLabel image = new JLabel(charmander);
   /** Label image. */
   //JLabel imgCharmander = new JLabel(charmander);
   
   /** Label. */
   private JLabel lPokemon = new JLabel("Pokemon");
   /** Label. */
   private JLabel lPokedex = new JLabel("Pokedex");
   /** Label. */
   private JLabel lBackpack = new JLabel("Backpack");
   /** Label. */
   private JLabel lSort = new JLabel("Sort By:  ");
   
   /** Button. */
   private JButton bPokemon = new JButton(pokemon);
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
   private JPanel pokemonContainerBottomButton = new JPanel();
   
      
   /** Card-Panel: Pokedex. */
   private JPanel cardPokedex = new JPanel();
      /** Pokedex Sub Panel. */
   private JPanel pokedexTop = new JPanel();
   /** Pokedex Sub Panel. */
   private JPanel pokedexBottom = new JPanel();
   
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
   private Border margin1 = new MatteBorder(10,0,0,0, cBlue);;
   /** Border margin 2.*/
   private Border margin2 = new MatteBorder(0,0,20,0, cBlue);
   /** Border Black line.*/
   private Border blackline = BorderFactory.createLineBorder(Color.black, 2);
   /** Border Titled border.*/
   private TitledBorder title = BorderFactory.createTitledBorder(blackline, "Gotta catch 'em all!");
      
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
      textArea2.setMaximumSize(textArea2.getPreferredSize());
      textArea2.setBorder(blackline);
      textArea2.setEditable(false);
      
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
      pokemonContainerBottomButton.setLayout(new BoxLayout(pokemonContainerBottomButton, BoxLayout.Y_AXIS));
      
      //Design for Pokedex Card-Panel and its sub-panels
      cardPokedex.setLayout(new BorderLayout());
      
      //Design for Backpack Card-Panel and its sub-panels
      cardBackpack.setLayout(new BorderLayout());
      backpackTop.setLayout(new BoxLayout(backpackTop, BoxLayout.X_AXIS));

      //Design for Button panel
      buttonPanel.setBackground(cBlue);
      buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));
      buttonContainer.setBorder(new CompoundBorder(margin2, blackline));
      
      
      
      //Temporary so that we can see the borders of each panel
      pokedexTop.setBackground(cBlue);
      pokedexBottom.setBackground(cRed);
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
      
      pokemonContainer.add(Box.createRigidArea(new Dimension(0,20)));
      pokemonContainer.add(pokemonContainerTop);
      pokemonContainer.add(Box.createRigidArea(new Dimension(0,15)));
      pokemonContainer.add(pokemonContainerBottom);
      pokemonContainer.add(Box.createRigidArea(new Dimension(0,20)));
      
      pokemonContainerTop.add(Box.createRigidArea(new Dimension(20,0)));
      pokemonContainerTop.add(image);
      pokemonContainerTop.add(Box.createRigidArea(new Dimension(60,0)));
      pokemonContainerTop.add(textArea2);
      
      pokemonContainerBottom.add(Box.createRigidArea(new Dimension(10,100)));
      pokemonContainerBottom.add(textArea);
      pokemonContainerBottom.add(Box.createRigidArea(new Dimension(12,0)));
      pokemonContainerBottom.add(pokemonContainerBottomButton);
      pokemonContainerBottom.add(Box.createRigidArea(new Dimension(10,0)));
      
      pokemonContainerBottomButton.add(bHunt);
      pokemonContainerBottomButton.add(Box.createRigidArea(new Dimension(0,10)));
      pokemonContainerBottomButton.add(bCatch);
      
      bHunt.addActionListener(new GUIListener());
      bCatch.addActionListener(new GUIListener());
      
      //========================== Pokedex Card-Panel ==========================
      cardPokedex.add("North", pokedexTop);
      cardPokedex.add("Center", pokedexBottom);
      
      pokedexTop.add(lPokedex);
      
      
      
      
      
      
      //========================== Backpack Card-Panel ==========================
      cardBackpack.add("North", backpackTop);
      cardBackpack.add("Center", backpackBottom);
      
      //Adding stuff to top sub-panel of backpack card
      backpackTop.add(Box.createRigidArea(new Dimension(10,0)));
      backpackTop.add(lBackpack);
      backpackTop.add(Box.createHorizontalGlue());
      backpackTop.add(lSort);
      backpackTop.add(sortChoise);
      backpackTop.add(Box.createRigidArea(new Dimension(25,0)));
      backpackTop.add(bSort);
      backpackTop.add(Box.createRigidArea(new Dimension(25,0)));
      //Adding backpack sorting options
      sortChoise.add("Recent");
      sortChoise.add("Number");
      sortChoise.add("Name");
      sortChoise.add("HP");
      sortChoise.add("CP");
      
      
      
      //========================== Button Panel ==========================
      buttonPanel.add(buttonContainer);
      
      buttonContainer.add(Box.createRigidArea(new Dimension(36,140)));
      buttonContainer.add(bPokemon);
      buttonContainer.add(Box.createRigidArea(new Dimension(36,0)));
      buttonContainer.add(bPokedex);
      buttonContainer.add(Box.createRigidArea(new Dimension(36,0)));
      buttonContainer.add(bBackpack);
      buttonContainer.add(Box.createRigidArea(new Dimension(37,0)));
      
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
         CardLayout card = (CardLayout)(deckPanel.getLayout());
         Random ranNum = new Random();
         int range = 9;
         int i = 0;
         Pokemon poke;
         
         if (event.getSource() == bPokemon) {
            card.show(deckPanel, "pokemon");
         }
         if (event.getSource() == bPokedex) {
            card.show(deckPanel, "pokedex");
         }
         if (event.getSource() == bBackpack) {
            card.show(deckPanel, "backpack");
         }
         if (event.getSource() == bHunt) {
            i = ranNum.nextInt(range) + 1;
            image.setIcon(charizard);


              
         }
      }
   }
   
   
} // Closes class
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
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

      /** Background Color .*/
   private Color cBlue = new Color(142, 239, 255);
   /** Background Color .*/
   private Color cRed = new Color(255, 20, 0);
      
   /**
   * Constructor holds everything.
   */
   public PokemonPanel() { 
      this.setLayout(new BorderLayout());
      this.setPreferredSize(new Dimension(700, 600));
      deckPanel.setLayout(new CardLayout());
      cardPokemon.setLayout(new BorderLayout());
      cardPokedex.setLayout(new BorderLayout());
      cardBackpack.setLayout(new BorderLayout());
      backpackTop.setLayout(new BoxLayout(backpackTop, BoxLayout.LINE_AXIS));
      
      //Temporary so that we can see the borders of each panel
      pokemonTop.setBackground(cBlue);
      pokemonBottom.setBackground(cRed);
      pokedexTop.setBackground(cBlue);
      pokedexBottom.setBackground(cRed);
      backpackTop.setBackground(cBlue);
      backpackBottom.setBackground(cRed);
      buttonPanel.setBackground(cBlue);
      

      
      this.add("Center", deckPanel);
      //Adding card panels to the deck"
      deckPanel.add(cardPokemon, "pokemon");
      deckPanel.add(cardPokedex, "pokedex");
      deckPanel.add(cardBackpack, "backpack");
      
      //Adding sub-panels to pokemon card
      cardPokemon.add("North", pokemonTop);
      cardPokemon.add("Center", pokemonBottom);
      
      pokemonTop.add(lPokemon);
      
      //Adding sub-panels to pokedex card
      cardPokedex.add("North", pokedexTop);
      cardPokedex.add("Center", pokedexBottom);
      
      pokedexTop.add(lPokedex);
      
      //Adding sub-panels to backpack card
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
      
      
      this.add("South", buttonPanel);
      //Adding buttons to button panel
      buttonPanel.add(bPokemon);
      buttonPanel.add(bPokedex);
      buttonPanel.add(bBackpack);
      
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
         
         if (event.getSource() == bPokemon) {
            card.show(deckPanel, "pokemon");
         }
         if (event.getSource() == bPokedex) {
            card.show(deckPanel, "pokedex");
         }
         if (event.getSource() == bBackpack) {
            card.show(deckPanel, "backpack");
         }
         if (event.getSource() == bSort) {
         
         }
      }
   }
   
} // Closes class
package GUI;

import javax.swing.JTextArea;

import Logic.*;
import TextUI.DisplayInterface;

public class GuiDisplay implements DisplayInterface {

    private JTextArea textArea;
    private JTextArea cardDesArea;
    private Game game;
    
    public GuiDisplay(Game game, JTextArea textArea, JTextArea cardDesArea) {
        this.game = game;
        this.textArea = textArea;
        this.cardDesArea = cardDesArea;
    }
    
    @Override
    public void showActionMenu() {
        textArea.append(String.format("\n"));
        textArea.append(String.format("%s\n", "Please select your choices"));
        textArea.append(String.format("%s\n", "1. Lay card(s)"));
        textArea.append(String.format("%s\n", "2. Take Money"));
        textArea.append(String.format("%s\n", "3. Draw Card(s)"));
        textArea.append(String.format("%s\n", "4. Activate Card"));
        textArea.append(String.format("%s\n", "5. End Turn"));
        textArea.append(String.format("%s\n", "Enter your choice (1-5): "));
    }

    @Override
    public void showField() {
        Field field = game.getField();
        Player player1 = game.getPlayer(Player.PLAYER1);
        Player player2 = game.getPlayer(Player.PLAYER2);
        
        textArea.setText(String.format("==============   Cards on field   ===================\n"));
        textArea.append(String.format("Number in the brackets represent card's defense value\n"));
        textArea.append(String.format("\n"));
        textArea.append(String.format("%14s      Dice Disc      %s\n", player1.getName(), player2.getName()));
        for(int i = 1; i <= Field.SIZE; i++) {
            
            if (field.getCard(player1.getId(), i) == null) {
                textArea.append(String.format("%16s    ", "Empty"));
            } else if (field.getCard(player1.getId(), i).isFaceUp()){
                textArea.append(String.format("%16s (%d)", field.getCard(player1.getId(), i).getName(),
                        field.getCard(player1.getId(), i).getDefense()));
            } else {
                textArea.append(String.format("%16s    ", "Face-down Card"));
            }
            
            textArea.append(String.format("%s%d%s", "    ", i, "    "));
            
            if (field.getCard(player2.getId(), i) == null) {
                textArea.append(String.format("    %s", "Empty"));
            } else if (field.getCard(player2.getId(), i).isFaceUp()){
                textArea.append(String.format("(%d) %s", field.getCard(player2.getId(), i).getDefense(), 
                        field.getCard(player2.getId(), i).getName()));
            } else {
                textArea.append(String.format("    %s", "Face-down Card "));
            }
            textArea.append(String.format("\n"));
        }
        textArea.append(String.format("\n"));
        textArea.append(String.format("General Victory Stock Pile: %d\n", game.getVictoryPoint()));
        textArea.append(String.format("Draw Pile: %d\n", game.getDrawDeck().getNumOfCards()));
        textArea.append(String.format("Discard Pile: %d\n", game.getDiscardPile().getNumOfCards()));
        textArea.append(String.format("\n"));
        textArea.setCaretPosition(textArea.getDocument().getLength());

    }

    @Override
    public void showMainMenu() {
        textArea.append(String.format("\n"));
        textArea.append(String.format("%s\n", "Welcome to Roma"));
        textArea.append(String.format("%s\n", "Please select your choices"));
        textArea.append(String.format("%s\n", "1. Read How To Play"));
        textArea.append(String.format("%s\n", "2. Start playing"));
        textArea.append(String.format("%s\n", "3. Exit"));
        textArea.append(String.format("%s\n", "Enter your choice (1-3): "));
    }

    @Override
    public void showRules() {
        textArea.append(String.format("\n"));
        textArea.append(String.format("%s\n", "The players take it in turns to make a " +
                "complete move. A move is made up of 3 phases, which are carried " +
                "out in the following order:"));
        textArea.append(String.format("%s\n", "Phase 1: Add up score for unoccupied dice discs"));
        textArea.append(String.format("%s\n", "Phase 2: Throw the dice"));
        textArea.append(String.format("%s\n", "Phase 3: Carry out the actions"));
    }

    @Override
    public void showStatus() {
        
    }

    @Override
    public void showText(String text) {
        textArea.append(String.format("%s\n", text));
    }

    @Override
    public void listCard(Deck deck) {
    }

    @Override
    public void clear() {
        textArea.setText("");
    }

    @Override
    public void showCard(String s) {
        cardDesArea.setText(s);
    }

}

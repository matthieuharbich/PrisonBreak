/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Player;
import model.item.Item;
import model.item.Transportable;

/**
 *
 * @author Onur Erdogan
 */
public class DropCmd extends Command{
    Transportable t;

    @Override
    public boolean execute(Player player) {
        clearOutputString();
            if (hasSecondWord()) {
                String itemName = getSecondWord();
                Transportable item = (Transportable) player.getItem(itemName);
                t = item;
                if ( item == null) {
                    appendToOutputString("This item doesn't exist");
                } else {
                    player.removeItem(item.getNAME());
                    if (item.isIMMORTAL()) {
                     player.getCurrentRoom().addItem(item); 
                    }
                    appendToOutputString("You just dropped the " + item.toString() +" in "+player.getCurrentRoom().getDescription() +".\n");
                }

            } else {
                // if there is no second word, we don't know where to go...
                appendToOutputString("drop what?");
            }
            return false;
    }
    
    public Transportable getDropItem(){
        return t;
    }
    
}

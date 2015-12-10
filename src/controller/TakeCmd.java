/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Player;
import model.Room;
import model.item.Item;
import model.item.Transportable;

/**
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
public class TakeCmd extends Command {

    public TakeCmd() {
    }

    /*
     * Take a object wich is in the room
     * 
     * Take a object   
     */
    public boolean execute(Player player) {

        clearOutputString();
        if (hasSecondWord()) {
            String nameItem = getSecondWord();
            Item item = player.getCurrentRoom().getItem(nameItem);
            if (item == null) {
                appendToOutputString("This item doesn't exist in this room!");
            } else {

                if (player.getCurrentRoom().getItem(nameItem) instanceof Transportable) {

                    Transportable itemTrans = (Transportable) item;
                    if (itemTrans.getWEIGHT() + player.getWeightItems() > GlobalVariable.MAX_WEIGHT) {
                        appendToOutputString("You can't carry this object because your maximum weight will be exceeded");
                    } else {
                        player.addItem(itemTrans);
                        player.getCurrentRoom().removeItem(item.getNAME());
                        appendToOutputString("You just took the " + item.getNAME() + " !");
                    }

                } else {
                    appendToOutputString("This item is fixed you can't take it");
                }

            }

        } else {
            appendToOutputString("Please specify the item that you want to take!");

        }
        return false;
    }
}

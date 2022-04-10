package me.evasive.quests.questshop;

import javafx.scene.paint.Material;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class ShopItem {
    /*
     * ID = Quest shop item ID (Unique to each shop item type)
     * name = Name of item in game
     * lore = String list for item lore in game
     * amount = amount of said item being given
     * material = material of item
     * command = t/f if command is being run instead of an item being given
     */
    private int ID;
    private String name;
    private ArrayList<String> lore;
    private int amount;
    private Material material;
    private Boolean command = false;
    private Rarity rarity;
}

package mrpg.rexgood;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemGenerator {
    public ItemStack MakeItemStack(Material material,String name,Integer custom){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setCustomModelData(custom);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public ItemStack MakeItemStack(Material material,Integer custom){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(custom);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public ItemStack MakeItemStack(Material material,String lore1,String lore2,Integer custom){
        ItemStack itemStack = new ItemStack(material);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(lore1);
        lore.add(lore2);
        itemStack.setLore(lore);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(custom);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}

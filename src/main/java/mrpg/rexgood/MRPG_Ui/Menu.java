package mrpg.rexgood.MRPG_Ui;

import mrpg.rexgood.ItemGenerator;
import mrpg.rexgood.Teleport;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class Menu {
    public static Inventory MenuInventory = Bukkit.createInventory(null,36,"메뉴");
    public static void SetMenu(){//메뉴 설정
        ItemGenerator itemGenerator = new ItemGenerator();
        MenuInventory.setItem(10,itemGenerator.MakeItemStack(Material.GRASS_BLOCK,"야생으로 이동",0));
        MenuInventory.setItem(11,itemGenerator.MakeItemStack(Material.GRASS_BLOCK,"테스트월드으로 이동",0));
        MenuInventory.setItem(12,itemGenerator.MakeItemStack(Material.GHAST_TEAR,"공허...",0));
        MenuInventory.setItem(13,itemGenerator.MakeItemStack(Material.GHAST_TEAR,"스탯",0));
        MenuInventory.setItem(14,itemGenerator.MakeItemStack(Material.ITEM_FRAME,"상점",0));

    }
    public void ClickMenu(InventoryClickEvent e) {
        Teleport teleport = new Teleport();
        AcornShop acornShop = new AcornShop();

        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);

        switch (e.getSlot()) {
            case 10:
                teleport.PTeleport(p, Bukkit.getWorld("MRPG_World"), -5000, 5000);
                p.sendMessage("야생월드로 이동했습니다");
                p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                break;
            case 11:
                teleport.Teleport(p, Bukkit.getWorld("World"), 0, -60, 0);
                p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                break;

            case 12:
                teleport.Teleport(p, Bukkit.getWorld("MineFarm_World"), 0, -60, 0);
                break;

            case 13:
                ScoreBoard scoreBoard = new ScoreBoard();
                scoreBoard.setScoreBoard(p);
                StatsMenu.SetStatsMenu(p);
                p.openInventory(StatsMenu.StatsMenuInventory);
                p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                break;
            case 14:
                p.openInventory(AcornShop.AcornShopMainInventory);
                acornShop.SetAcornShop();
        }
    }
}

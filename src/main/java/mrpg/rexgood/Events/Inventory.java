package mrpg.rexgood.Events;
import mrpg.rexgood.MRPG_Ui.AcornShop;
import mrpg.rexgood.MRPG_Ui.Menu;
import mrpg.rexgood.MRPG_Ui.StatsMenu;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class Inventory implements Listener {
    //인벤토리 이벤트 처리
    @EventHandler
    void InventoryOpen(InventoryOpenEvent e) {
        if (e.getPlayer().getType() == EntityType.PLAYER) {
            for (ItemStack itemStack : e.getInventory().getContents()) {
                if (itemStack != null && itemStack.getItemMeta() != null) {//인첸트 감지 코드
                    if (itemStack.getItemMeta().hasEnchants()) {
                        for (Enchantment enchant : itemStack.getEnchantments().keySet()) {
                            ItemMeta itemMeta = itemStack.getItemMeta();
                            itemMeta.removeEnchant(enchant);
                            itemStack.setItemMeta(itemMeta);
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    void InventoryClose(InventoryCloseEvent e){
    }


    @EventHandler
    void InventoryClicked(InventoryClickEvent e){
        //인벤토리 클릭시
        if(e.getClickedInventory()==null){return;}
        Player p = (Player) e.getWhoClicked();

        //호출
        Menu menu = new Menu();
        StatsMenu statsMenu = new StatsMenu();
        AcornShop acornShopfile = new AcornShop();
        AcornShop acornShop = new AcornShop();
        //특정 인벤토리일때 실행
        if (e.getClickedInventory().equals(Menu.MenuInventory)) {//메뉴일때
            menu.ClickMenu(e);
        }
        if (e.getClickedInventory().equals(StatsMenu.StatsMenuInventory)){//스탯메뉴일때
            statsMenu.ClickStatsMenu(e);
        }
        if (e.getClickedInventory().equals(AcornShop.AcornShopMainInventory)){//도토리상점(메인)일때

            acornShopfile.ClickAcornShopMain(e);
        }
        if (e.getClickedInventory().equals(AcornShop.AcornShopMineralInventory)||
                e.getClickedInventory().equals(AcornShop.AcornShopCropsInventory)){//도토리상점(메인)일때
            acornShopfile.ClickAcornShop(e);
        }
        if (e.getClickedInventory().equals(AcornShop.AcornShopMineralInventory)||
                e.getClickedInventory().equals(AcornShop.AcornShopCropsInventory)){//도토리상점(메인)일때
            acornShopfile.ClickAcornShop(e);
        }
        if (e.getClickedInventory().equals(acornShopfile.InventoryOpen.get(p.getUniqueId()))){
            acornShopfile.ClickAcornShopItem(e);
        }

        }
    }

package mrpg.rexgood.MRPG_Ui;

import mrpg.rexgood.Files.PlayerInfo;
import mrpg.rexgood.ItemGenerator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AcornShop {
    public static HashMap<UUID, Inventory> InventoryOpen = new HashMap<UUID, Inventory>();
    public static Inventory AcornShopMainInventory = Bukkit.createInventory(null,36,"도토리 상점");
    public static Inventory AcornShopMineralInventory = Bukkit.createInventory(null,36,"도토리 상점(광물)");
    public static Inventory AcornShopCropsInventory = Bukkit.createInventory(null,36,"도토리 상점(작물)");
    public void SetAcornShop(){//도토리 상점 아이템 설정
        ItemGenerator itemGenerator = new ItemGenerator();
        mrpg.rexgood.Files.AcornShop acornShopfile = new mrpg.rexgood.Files.AcornShop();
        //도토리상점(메인)
        AcornShopMainInventory.setItem(10,itemGenerator.MakeItemStack(Material.RAW_IRON,"광물 상점",0));
        AcornShopMainInventory.setItem(11,itemGenerator.MakeItemStack(Material.WHEAT,"작물 상점",0));
        //도토리상점(광물)
        SetShopItem(AcornShopMineralInventory,10,Material.COAL);
        SetShopItem(AcornShopMineralInventory,11,Material.CHARCOAL);
        SetShopItem(AcornShopMineralInventory,12,Material.RAW_IRON);
        SetShopItem(AcornShopMineralInventory,13,Material.RAW_COPPER);
        SetShopItem(AcornShopMineralInventory,14,Material.RAW_GOLD);
        SetShopItem(AcornShopMineralInventory,15,Material.EMERALD);
        SetShopItem(AcornShopMineralInventory,16,Material.LAPIS_LAZULI);
        SetShopItem(AcornShopMineralInventory,19,Material.REDSTONE);
        SetShopItem(AcornShopMineralInventory,20,Material.DIAMOND);
        SetShopItem(AcornShopMineralInventory,21,Material.ANCIENT_DEBRIS);
        SetShopItem(AcornShopMineralInventory,22,Material.QUARTZ);
        SetShopItem(AcornShopMineralInventory,23,Material.AMETHYST_SHARD);
        //도토리상점(작물)
        SetShopItem(AcornShopCropsInventory,10,Material.WHEAT_SEEDS);
        SetShopItem(AcornShopCropsInventory,11,Material.WHEAT);
        SetShopItem(AcornShopCropsInventory,12,Material.CARROT);
        SetShopItem(AcornShopCropsInventory,13,Material.GOLDEN_CARROT);
        SetShopItem(AcornShopCropsInventory,14,Material.POTATO);
        SetShopItem(AcornShopCropsInventory,15,Material.MELON_SLICE);
        SetShopItem(AcornShopCropsInventory,16,Material.SWEET_BERRIES);
        SetShopItem(AcornShopCropsInventory,19,Material.GLOW_BERRIES);
        SetShopItem(AcornShopCropsInventory,20,Material.NETHER_WART);
        SetShopItem(AcornShopCropsInventory,21,Material.CACTUS);
        SetShopItem(AcornShopCropsInventory,22,Material.COCOA_BEANS);
    }
    public void OpenShop(Player p,Material material){
        ItemGenerator itemGenerator = new ItemGenerator();
        mrpg.rexgood.Files.AcornShop acornShopfile = new mrpg.rexgood.Files.AcornShop();
        Inventory AcornShop = Bukkit.createInventory(null,36,"도토리 상점("+material.name()+")");
        AcornShop.setItem(10,itemGenerator.MakeItemStack(Material.GHAST_TEAR,"3개 삭제",0));
        AcornShop.setItem(11,itemGenerator.MakeItemStack(Material.GHAST_TEAR,"2개 삭제",0));
        AcornShop.setItem(12,itemGenerator.MakeItemStack(Material.GHAST_TEAR,"1개 삭제",0));
        AcornShop.setItem(13,itemGenerator.MakeItemStack(material,ChatColor.GREEN+"구매가격 : "+Integer.valueOf(acornShopfile.GetFile(material+".buy")),ChatColor.RED+"판매가격 : "+Integer.valueOf(acornShopfile.GetFile(material+".sell")),0));
        AcornShop.setItem(14,itemGenerator.MakeItemStack(Material.GHAST_TEAR,"1개 추가",0));
        AcornShop.setItem(15,itemGenerator.MakeItemStack(Material.GHAST_TEAR,"2개 추가",0));
        AcornShop.setItem(16,itemGenerator.MakeItemStack(Material.GHAST_TEAR,"3개 추가",0));
        AcornShop.setItem(21,itemGenerator.MakeItemStack(Material.LIME_STAINED_GLASS,"구매",0));
        AcornShop.setItem(22,itemGenerator.MakeItemStack(Material.BARRIER,"취소",0));
        AcornShop.setItem(23,itemGenerator.MakeItemStack(Material.RED_STAINED_GLASS,"판매",0));

        InventoryOpen.put(p.getUniqueId(),AcornShop);
        p.openInventory(AcornShop);

    }
    public void ClickAcornShopItem(InventoryClickEvent e){
        //호출
        ScoreBoard scoreBoard = new ScoreBoard();
        PlayerInfo playerInfo = new PlayerInfo();
        mrpg.rexgood.Files.AcornShop acornShopfile = new mrpg.rexgood.Files.AcornShop();
        //선언
        Player p = (Player) e.getWhoClicked();
        ItemStack MainItem = e.getClickedInventory().getItem(13);
        Integer amount = MainItem.getAmount();
        //인벤토리 슬롯에 따라서 코드 실행
        switch(e.getSlot()){
            case 10: if (amount>3){
                amount = amount-3;
            }
            break;

            case 11: if (amount>2){
                amount = amount-2;
            }
            break;

            case 12: if (amount>1){
                amount = amount-1;
            }
            break;

            case 14: if (amount<64){
                amount = amount+1;
            }
            break;

            case 15: if (amount<63){
                amount = amount+2;
            }
            break;

            case 16: if (amount<62){
                amount = amount+3;
            }
            break;

            case 21: if (Integer.valueOf(playerInfo.GetPlayerFile(p,"Acorin")) >= Integer.valueOf(acornShopfile.GetFile(MainItem.getType()+".buy"))*amount && Integer.valueOf(acornShopfile.GetFile(MainItem.getType()+".buy"))*amount != 0)
            {//플레이어의 아코린이 구매아이템의 상점가격*개수 보다 많고, 가격이 0이 아니라면 실행
                //아코인 - (구매아이템가격*구매개수)
                playerInfo.SetPlayerFile(p,"Acorin",String.valueOf(Integer.valueOf(playerInfo.GetPlayerFile(p,"Acorin"))-Integer.valueOf(acornShopfile.GetFile(MainItem.getType()+".buy"))*amount));
                //플레이어에게 아이템 추가
                ItemStack itemStack = new ItemStack(MainItem.getType());
                itemStack.setAmount(amount);
                p.getInventory().addItem(itemStack);
                p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                //점수판 업데이트
                scoreBoard.setScoreBoard(p);
            }else{p.sendMessage("아코린이 부족합니다.");}
            break;

            case 22: p.openInventory(AcornShopMainInventory);
            break;

            case 23: if (p.getInventory().containsAtLeast(new ItemStack(MainItem.getType()),amount))
            {
                //아코인 + (구매아이템가격*구매개수)
                playerInfo.SetPlayerFile(p,"Acorin",String.valueOf(Integer.valueOf(playerInfo.GetPlayerFile(p,"Acorin"))+Integer.valueOf(acornShopfile.GetFile(MainItem.getType()+".sell"))*amount));
                //플레이어 아이템 삭제
                p.getInventory().setItem(p.getInventory().first(MainItem.getType()),new ItemStack(MainItem.getType(),p.getInventory().getItem(p.getInventory().first(MainItem.getType())).getAmount()-amount));
                p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER,1,1);
                //점수판 업데이트
                scoreBoard.setScoreBoard(p);
            }else{p.sendMessage("아이템이 부족합니다");}
        }
        e.getClickedInventory().getItem(13).setAmount(amount);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN+"구매가격 : "+Integer.valueOf(acornShopfile.GetFile(MainItem.getType()+".buy"))*amount);
        lore.add(ChatColor.RED+"판매가격 : "+Integer.valueOf(acornShopfile.GetFile(MainItem.getType()+".sell"))*amount);
        MainItem.setLore(lore);
        e.setCancelled(true);
    }

    public void ClickAcornShopMain(InventoryClickEvent e){
        //선언
        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);
        switch (e.getSlot()){
            case 10: p.openInventory(AcornShopMineralInventory);
            break;
            case 11: p.openInventory(AcornShopCropsInventory);
            break;
        }
    }

    public void ClickAcornShop(InventoryClickEvent e){
        if(e.getCurrentItem()!=null){
            Player p = (Player) e.getWhoClicked();
            e.setCancelled(true);

            OpenShop(p, e.getCurrentItem().getType());
        }
    }
    private void SetShopItem(Inventory inventory,Integer slot,Material material){

        ItemGenerator itemGenerator = new ItemGenerator();
        mrpg.rexgood.Files.AcornShop acornShopfile = new mrpg.rexgood.Files.AcornShop();

        if (acornShopfile.GetFile(material.name()+".buy") == null || acornShopfile.GetFile(material.name()+".sell") == null){
            acornShopfile.SetFile(material.name()+".buy","0");
            acornShopfile.SetFile(material.name()+".sell","0");
        }

        inventory.setItem(slot,itemGenerator.MakeItemStack(material,ChatColor.GREEN+"구매가격 : "+Integer.valueOf(acornShopfile.GetFile(material+".buy")),ChatColor.RED+"판매가격 : "+Integer.valueOf(acornShopfile.GetFile(material+".sell")),0));
    }
}

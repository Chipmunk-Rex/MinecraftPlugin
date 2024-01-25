package mrpg.rexgood.MRPG_Ui;

import mrpg.rexgood.Files.PlayerInfo;
import mrpg.rexgood.ItemGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public class StatsMenu {//스탯메뉴 인벤토리 설정
    public static Inventory StatsMenuInventory = Bukkit.createInventory(null,36,"스탯");
    public static void SetStatsMenu(Player p){
        PlayerInfo playerInfo = new PlayerInfo();
        ItemGenerator itemGenerator = new ItemGenerator();
        Integer point = Integer.valueOf(playerInfo.GetPlayerFile(p,"Stats.Point"));
        playerInfo.GetPlayerFile(p,"Stats.Hp");
        playerInfo.GetPlayerFile(p,"Stats.Mp");
        playerInfo.GetPlayerFile(p,"Stats.Str");
        StatsMenuInventory.setItem(0,itemGenerator.MakeItemStack(Material.PLAYER_HEAD,"내 스탯",1));
        StatsMenuInventory.setItem(10,itemGenerator.MakeItemStack(Material.GHAST_TEAR,"체력/",1));
        StatsMenuInventory.setItem(13,itemGenerator.MakeItemStack(Material.GHAST_TEAR,"마나",2));
        StatsMenuInventory.setItem(16,itemGenerator.MakeItemStack(Material.GHAST_TEAR,"힘",3));
        StatsMenuInventory.setItem(19,itemGenerator.MakeItemStack(Material.GHAST_TEAR,playerInfo.GetPlayerFile(p,"Stats.Hp"),1));
        StatsMenuInventory.setItem(22,itemGenerator.MakeItemStack(Material.GHAST_TEAR,playerInfo.GetPlayerFile(p,"Stats.Mp"),2));
        StatsMenuInventory.setItem(25,itemGenerator.MakeItemStack(Material.GHAST_TEAR,playerInfo.GetPlayerFile(p,"Stats.Str"),3));
    }

    public void ClickStatsMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);
        PlayerInfo playerInfo = new PlayerInfo();
        switch (e.getSlot()) {
            case 0://스탯보기
                p.sendMessage("현재 능력치");
                p.sendMessage("보유 포인트: " + AddStats(p, 10));
                p.sendMessage("체력: " + playerInfo.GetPlayerFile(p, "Stats.Hp"));
                p.sendMessage("마나: " + playerInfo.GetPlayerFile(p, "Stats.Mp"));
                p.sendMessage("힘: " + playerInfo.GetPlayerFile(p, "Stats.Str"));
                e.getClickedInventory().close();
                p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                break;
            case 10://hp
                p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                break;

            case 13://mp
                p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                break;

            case 16://str
                p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                break;

            case 19://hp
                AddStats(p, 1);
                p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);

                break;

            case 22://mp
                AddStats(p, 2);
                p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                break;

            case 25://str
                AddStats(p, 3);
                p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                break;
        }
    }

    public static int AddStats(Player p, Integer StatsNum){
        //?
        PlayerInfo playerInfo = new PlayerInfo();
        ItemGenerator itemGenerator = new ItemGenerator();
        //변수 선언
        Integer Point = Integer.valueOf(playerInfo.GetPlayerFile(p,"Stats.Point"));
        Integer Hp = Integer.valueOf(playerInfo.GetPlayerFile(p,"Stats.Hp"));
        Integer Mp = Integer.valueOf(playerInfo.GetPlayerFile(p,"Stats.Mp"));
        Integer Str = Integer.valueOf(playerInfo.GetPlayerFile(p,"Stats.Str"));
        //스탯 상승
        if (Point > Hp + Mp + Str)//만약 포인트가 모든 스탯을 합한것보다 크다면
        {
            switch (StatsNum) {
                case 1: //hp
                    Hp = Hp + 1;
                    playerInfo.SetPlayerFile(p,"Stats.Hp", String.valueOf(Hp));
                    p.sendMessage("체력을 올렸습니다.");
                    p.sendMessage("현재 체력: "+ Hp);
                    p.setMaxHealth(Hp+20);
                    p.setHealthScale(20);
                    StatsMenuInventory.setItem(19,itemGenerator.MakeItemStack(Material.GHAST_TEAR,Hp.toString(),1));
                    break;
                case 2:
                    Mp = Mp + 1;
                    playerInfo.SetPlayerFile(p,"Stats.Mp", String.valueOf(Mp));
                    p.sendMessage("마나을 올렸습니다.");
                    p.sendMessage("현재 마나: "+ Mp);
                    StatsMenuInventory.setItem(22,itemGenerator.MakeItemStack(Material.GHAST_TEAR,Mp.toString(),2));
                    break;
                case 3:
                    Str = Str + 1;
                    playerInfo.SetPlayerFile(p,"Stats.Str", String.valueOf(Str));
                    p.sendMessage("힘을 올렸습니다.");
                    p.sendMessage("현재 힘: "+ Str);
                    StatsMenuInventory.setItem(25,itemGenerator.MakeItemStack(Material.GHAST_TEAR,Str.toString(),3));
                    break;
                case 10:
                    return Point-Hp-Mp-Str;
                default:
                    throw new IllegalStateException("이것은 없는 스탯번호입니다: " + StatsNum);
            }
        }else{p.sendMessage("포인트가 부족합니다");}
        return 0;
    }
}

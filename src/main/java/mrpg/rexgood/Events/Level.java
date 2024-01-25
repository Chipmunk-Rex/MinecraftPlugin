package mrpg.rexgood.Events;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import mrpg.rexgood.Files.PlayerInfo;
import mrpg.rexgood.MRPG_Ui.ScoreBoard;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Level implements Listener {//레벨관련 이벤트 처리




    @EventHandler
    void expLimit(EntityDeathEvent e){
        if(e.getEntity().getKiller()!=null){
        Player p = e.getEntity().getKiller();
        ScoreBoard scoreBoard = new ScoreBoard();
        PlayerInfo playerInfo = new PlayerInfo();
        //스코어보드 변경
        scoreBoard.setScoreBoard(p);

        
        for (ItemStack itemStack : e.getDrops()){
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

        if (p != null) {
            if(e.getEntity().getType() != EntityType.PLAYER) {
                if (playerInfo.GetPlayerFile(p, "KillCounts." + e.getEntity().getName()) == null) {
                    playerInfo.SetPlayerFile(p, "KillCounts." + e.getEntity().getName(), "1");
                } else {
                    playerInfo.SetPlayerFile(p, "KillCounts." + e.getEntity().getName(), String.valueOf(Integer.valueOf(playerInfo.GetPlayerFile(p, "KillCounts." + e.getEntity().getName())) + 1));
                }
                if (Integer.valueOf(playerInfo.GetPlayerFile(p, "KillCounts." + e.getEntity().getName())) < 32) {
                    ChangeExp(p, 16);
                }
                }
            }
            e.setDroppedExp(0);
        }
    }
    public void ChangeExp(Player p,Integer exp){
        PlayerInfo playerInfo = new PlayerInfo();
        //경험치
        Integer xp = Integer.valueOf(playerInfo.GetPlayerFile(p,"Experience")) + exp;
        Integer level = XpCount(xp,1);
        Integer NowXp = XpCount(xp,2);
        Integer MaxXp = XpCount(xp,3);
        //값 저장
        playerInfo.SetPlayerFile(p,"Level",level.toString());
        playerInfo.SetPlayerFile(p,"Stats.Point",String.valueOf(level*4));
        //플레이어
        playerInfo.SetPlayerFile(p,"Experience",xp.toString());
        p.setLevel(level);//레벨 설정
        if(Integer.valueOf(playerInfo.GetPlayerFile(p,"Level"))<level){//레벨업 감지시
            p.playSound(p,Sound.ENTITY_PLAYER_LEVELUP,1,1);
            p.sendMessage("레벨업!!");
            p.sendMessage("현재 레벨: "+level);
        }
    }

    @EventHandler
    void levelchange(PlayerExpChangeEvent e) {
        Player p = e.getPlayer();
        ScoreBoard scoreBoard = new ScoreBoard();
        PlayerInfo playerInfo = new PlayerInfo();
        //경험치 추가
        playerInfo.AddPlayerXp(p,e.getAmount());
        //이벤트 종료
        e.setAmount(0);
    }
    public static Integer XpCount(Integer xp,Integer RetrunNum){//레벨,경험치,최대경험치를 리턴
        Integer MaxXp = 16;
        Integer Level = 0;
        while(xp > MaxXp ){
            xp = xp - MaxXp;
            MaxXp = MaxXp + 16;
            Level = Level+1;
        }
        switch (RetrunNum) {
            case 1: return Level;
            case 2: return xp;
            case 3: return MaxXp;

            default:
                throw new IllegalStateException("없는 리턴넘버입니다: " + RetrunNum);
        }
    }
}

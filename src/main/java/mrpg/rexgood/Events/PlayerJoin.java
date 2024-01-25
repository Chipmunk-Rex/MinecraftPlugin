package mrpg.rexgood.Events;

import mrpg.rexgood.Files.PlayerInfo;
import mrpg.rexgood.MRPG;
import mrpg.rexgood.MRPG_Ui.ScoreBoard;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

import static org.bukkit.Bukkit.getServer;

public class PlayerJoin implements Listener {//플레이어 접속시 이벤트 처리

    public static MRPG plugin;
    public static void setPlugin(MRPG MainPlugin) {
        plugin = MainPlugin;
    }

    @EventHandler
    void PJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        ScoreBoard scoreBoard = new ScoreBoard();


        PlayerInfo playerInfo = new PlayerInfo();
        if(playerInfo.Makefile(p)){
            //인첸트아이템 삭제
            for (ItemStack itemStack : p.getInventory().getContents()){
                if (itemStack != null && itemStack.getItemMeta() != null){
                    if (itemStack.getItemMeta().hasEnchants()){
                        for(Enchantment enchant : itemStack.getEnchantments().keySet()){
                            ItemMeta itemMeta = itemStack.getItemMeta();
                            itemMeta.removeEnchant(enchant);
                            itemStack.setItemMeta(itemMeta);
                        }
                    }
                    }
            }
            //접속 설정
            p.setMaxHealth(Double.valueOf(playerInfo.GetPlayerFile(p,"Stats.Hp"))+20);
            p.setHealthScale(20);
            e.setJoinMessage(p.getName() + "님이 서버에 접속했습니다");            //접속 메세지
            playerInfo.SetPlayerFile(p,"Name",p.getName().toString()); //이름 변경 확인을 위해 매 접속마다 변경
            Integer Join = Integer.valueOf(playerInfo.GetPlayerFile(p,"JoinCount"))+1;//접속횟수 +1
            p.sendMessage(String.valueOf(Join));                              //접속횟수 메세지 출력
            playerInfo.SetPlayerFile(p,"JoinCount",String.valueOf(Join));//접속횟수 저장
        }else{
            //첫 접속시 설정
            playerInfo.SetPlayerFile(p,"Acorin","0");
            playerInfo.SetPlayerFile(p,"KillCounts.Player","0");
            playerInfo.SetPlayerFile(p,"JoinCount","1");//접속횟수
            playerInfo.SetPlayerFile(p,"Name",p.getName().toString());//이름
            playerInfo.SetPlayerFile(p,"Experience","0");
            playerInfo.SetPlayerFile(p,"Level","0");
            playerInfo.SetPlayerFile(p,"Stats","0");
            playerInfo.SetPlayerFile(p,"Stats.Point","0");
            playerInfo.SetPlayerFile(p,"Stats.Hp","0");
            playerInfo.SetPlayerFile(p,"Stats.Mp","0");
            playerInfo.SetPlayerFile(p,"Stats.Str","0");
            e.setJoinMessage(e.getPlayer().getName() + "님이 처음 서버에 접속했습니다");
        }
        scoreBoard.setScoreBoard(p);
    }
}

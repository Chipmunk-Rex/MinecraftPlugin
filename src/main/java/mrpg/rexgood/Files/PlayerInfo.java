package mrpg.rexgood.Files;

import mrpg.rexgood.MRPG;
import mrpg.rexgood.MRPG_Ui.ScoreBoard;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

import static mrpg.rexgood.Events.Level.XpCount;

public class PlayerInfo{//플레이어의 정보를 저장하는 파일을 설정
    public static MRPG plugin;
    public static void setPlugin(MRPG MainPlugin) {
        plugin = MainPlugin;
    }

    public boolean Makefile(Player p){//파일 생성
        File file = new File("plugins\\MRPG\\Players", p.getUniqueId().toString() + ".yml");
        try {
            if (file.createNewFile()) {
                System.out.println("플레이어정보 파일이 생성되었습니다.");
                return false;
            } else {
                System.out.println("플레이어정보 파일이 이미 존재합니다.");
                return true;
            }
        } catch (IOException IoE) {
            IoE.printStackTrace();
            return false;
        }

    }
    public void SetPlayerFile(Player p,String par,String chi) {
        File file = new File("plugins\\MRPG\\Players", p.getUniqueId() + ".yml");
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set(par,chi);
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String GetPlayerFile(Player p,String par){//
        File file = new File("plugins\\MRPG\\Players", p.getUniqueId() + ".yml");
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        try {
            return fileConfiguration.get(par).toString();
        }catch (Exception e){
            return null;
        }
    }
    public void AddPlayerXp(Player p,Integer addxp){
        if(Integer.valueOf(GetPlayerFile(p,"Level"))>XpCount(Integer.valueOf(GetPlayerFile(p,"Experience")),1)){
                p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP,1,1);
                p.sendMessage("레벨업!!");
                p.sendMessage("현재 레벨: "+XpCount(Integer.valueOf(GetPlayerFile(p,"Experience ")),1));
            }
        ScoreBoard scoreBoard = new ScoreBoard();
        Integer xp = Integer.valueOf(GetPlayerFile(p,"Experience"))+addxp;
        //저장
        p.setLevel(XpCount(xp,1));
        SetPlayerFile(p,"Stats.Point",String.valueOf(Integer.valueOf(GetPlayerFile(p,"Level"))*4));
        SetPlayerFile(p,"Experience",xp.toString());
        SetPlayerFile(p,"Level", XpCount(xp,1).toString());

        scoreBoard.setScoreBoard(p);
    }
}


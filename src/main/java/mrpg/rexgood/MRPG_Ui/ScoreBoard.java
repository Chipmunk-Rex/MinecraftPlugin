package mrpg.rexgood.MRPG_Ui;

import mrpg.rexgood.Events.Level;
import mrpg.rexgood.Files.PlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreBoard {
    public void setScoreBoard(Player p) {
        PlayerInfo playerInfo = new PlayerInfo();
        Level levelEvent = new Level();
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("다람쥐의 RPG서버", "dummy", ChatColor.BOLD + "다람쥐의 RPG서버 제작실");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score onlineName = objective.getScore(ChatColor.GRAY + "현재 온라인 플레이어");
        objective.getScore(" ").setScore(11);
        objective.getScore(ChatColor.BOLD + "온라인 : " + Bukkit.getServer().getOnlinePlayers().size()).setScore(10);
        objective.getScore(ChatColor.BOLD + "아코린 : "  + playerInfo.GetPlayerFile(p,"Acorin") + "개").setScore(1);
        objective.getScore(ChatColor.BOLD + "레벨 : "+playerInfo.GetPlayerFile(p,"Level")).setScore(3);
        objective.getScore(ChatColor.BOLD + "경험치 : "
                + Level.XpCount(Integer.valueOf(playerInfo.GetPlayerFile(p,"Experience")),2) + " / "
                + Level.XpCount(Integer.valueOf(playerInfo.GetPlayerFile(p,"Experience")),3)).setScore(2);
        p.setScoreboard(scoreboard);
    }
}

package mrpg.rexgood;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import mrpg.rexgood.Events.*;
import mrpg.rexgood.Events.Stats;
import mrpg.rexgood.Files.BlockExp;
import mrpg.rexgood.Files.PlayerInfo;
import mrpg.rexgood.MRPG_Ui.AcornShop;
import mrpg.rexgood.MRPG_Ui.Menu;
import mrpg.rexgood.MRPG_Ui.StatsMenu;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.IOException;

public final class MRPG extends JavaPlugin{//메인 플러그인

    @Override
    public void onEnable() {
        //플러그인 실행(이벤트)
        getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        getServer().getPluginManager().registerEvents(new Level(),this);
        getServer().getPluginManager().registerEvents(new Interaction(),this);
        getServer().getPluginManager().registerEvents(new Inventory(),this);
        getServer().getPluginManager().registerEvents(new Stats(),this);
        getServer().getPluginManager().registerEvents(new Block(),this);
        getServer().setMotd("§6§l다람쥐§f§l의 RPG서버 제작실");
        getLogger().info("플러그인 활성화");

        //스코어보드
        Scoreboard scoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();

        //메뉴 설정
        mrpg.rexgood.MRPG_Ui.Menu.SetMenu();
        //world
        WorldCreator worldCreator = new WorldCreator("MineFarm_World");
        worldCreator.type(WorldType.FLAT);
        worldCreator.generator(new EmptyChunckGenerator());

        getServer().createWorld(worldCreator);
        getServer().createWorld(WorldCreator.name("MRPG_World"));

        //폴더 생성
        mrpg.rexgood.Files.AcornShop acornShop = new mrpg.rexgood.Files.AcornShop();
        BlockExp blockExp = new BlockExp();

        blockExp.Makefile();
        acornShop.Makefile();
        File folder = new File("plugins","MRPG");
        folder.mkdir();
        File Playerfolder = new File("plugins\\MRPG","Players");
        Playerfolder.mkdir();
    }

    public static WorldEditPlugin getWorldEdit(){
        return (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
    }

    @Override
    public void onDisable() {
        getLogger().info("플러그인 비활성화");
    }
}

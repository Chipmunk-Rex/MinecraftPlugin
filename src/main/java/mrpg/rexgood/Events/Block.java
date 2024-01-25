package mrpg.rexgood.Events;

import mrpg.rexgood.Files.BlockExp;
import mrpg.rexgood.Files.PlayerInfo;
import mrpg.rexgood.MRPG_Ui.ScoreBoard;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;

public class Block implements Listener {
    @EventHandler
    void BlockPlace(BlockPlaceEvent e){//블럭 설치 이벤트
        if (e.getBlock().getType() == Material.ANVIL){
            e.setCancelled(true);
        }
    }
    @EventHandler
    void  Farming(PlayerHarvestBlockEvent e){
        Player p = e.getPlayer();
        switch (e.getHarvestedBlock().getType()){
            case SWEET_BERRY_BUSH:
            default: p.sendMessage("뭘 수확하신거죠...?");
        }
    }

    @EventHandler
    void BlockBreak(BlockBreakEvent e){//블럭 파괴 이벤트
        Player p = e.getPlayer();
        PlayerInfo playerInfo = new PlayerInfo();
        BlockExp blockExp = new BlockExp();
        ScoreBoard scoreBoard = new ScoreBoard();
        if (e.getBlock().getType() == Material.PUMPKIN){e.getBlock().setType(Material.CARVED_PUMPKIN);}
        if (e.getBlock().getType() == Material.CARROT){e.getBlock().getDrops();}
        if(blockExp.GetFile(e.getBlock().getType().toString()) != null){//BlockExp파일에 부서진 블럭이 있다면
            if(e.getBlock().getBlockData() instanceof Ageable && ((Ageable) e.getBlock().getBlockData()).getAge() != ((Ageable) e.getBlock().getBlockData()).getMaximumAge()){return;}
            //소리재생
            p.playSound(p,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
            //플레이어 경험치를 기존 경험치 + BlockExp파일의 부서진 블럭의 경험치량 만큼 증가시킨다
            playerInfo.AddPlayerXp(p,Integer.valueOf(blockExp.GetFile(e.getBlock().getType().toString())));
        }
        e.setExpToDrop(0);
    }
}

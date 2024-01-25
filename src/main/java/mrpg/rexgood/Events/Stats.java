package mrpg.rexgood.Events;

import mrpg.rexgood.Files.PlayerInfo;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Stats implements Listener {
    @EventHandler
    void playerattack(EntityDamageByEntityEvent e){
        if (e.getDamager().getType() == EntityType.PLAYER){
            PlayerInfo playerInfo = new PlayerInfo();
            Player p = (Player) e.getDamager();
            Integer Damage = 0;
            Integer Str = Integer.valueOf(playerInfo.GetPlayerFile(p,"Stats.Str"));
                Damage = Str/3;
                e.setDamage(e.getDamage()+Damage);
                p.sendMessage(e.getFinalDamage()-e.getFinalDamage()%0.5 + "만큼의 데미지를 입혔습니다");
        }
        if (e.getEntity().getType() == EntityType.PLAYER){
            PlayerInfo playerInfo = new PlayerInfo();
            Player p = (Player) e.getEntity();
            p.sendMessage(e.getDamage()-e.getDamage()%0.5+"만큼의 아파요!!");
        }else{
            Entity entity = e.getEntity();
            if (entity.isDead()){
            }
        }
    }
}

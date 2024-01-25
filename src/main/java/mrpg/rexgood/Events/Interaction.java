package mrpg.rexgood.Events;

import mrpg.rexgood.MRPG_Ui.Menu;
import mrpg.rexgood.RandomGenerator;
import mrpg.rexgood.Teleport;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import static org.apache.logging.log4j.LogManager.getLogger;

public class Interaction implements Listener {//여러가지 상호작용 처리(잡다한것들)

    @EventHandler
    void ShiftF(PlayerSwapHandItemsEvent e) {//양손바꾸기 이벤트
        e.setCancelled(true);
        Player p = e.getPlayer();
        if (p.isSneaking() == true) {//움크리기중일때
            p.openInventory(Menu.MenuInventory);
        }
    }
    @EventHandler
    void Enchant(EnchantItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    void PortalEvent(PlayerPortalEvent e){
        World GetWorld = e.getFrom().getWorld();
        switch (e.getCause()){
            case NETHER_PORTAL:
                switch (GetWorld.getEnvironment()){
                    case NORMAL :
                        e.getTo().setWorld(Bukkit.getWorld("world_nether"));
                    break;

                    case NETHER :
                    e.setTo(e.getFrom().multiply(8.0D));
                    e.getTo().setWorld(Bukkit.getWorld("MRPG_World"));
                    break;

                    default:
                        getLogger().info("Interaction 클래스 버그남!!?");
                }
        }
    }
}

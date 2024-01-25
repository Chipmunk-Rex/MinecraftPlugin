package mrpg.rexgood;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Teleport {//텔레포트 관련 처리

    public void Teleport(Player p,World world,int x, int y, int z) {//월드, 좌표값을 받고 위치를 리턴
        Location location = new Location(world, x, y, z);
        p.teleport(location);
    }


    public void PTeleport(Player p, World world, int min, int max) {
        int X = RandomGenerator.MakeNumber(min, max);
        int Y = 0;
        int Z = RandomGenerator.MakeNumber(min, max);
        Location plocation = new Location(world, X, Y, Z);
        plocation.setY(plocation.getWorld().getHighestBlockYAt(plocation));
        p.teleport(plocation);
    }
}

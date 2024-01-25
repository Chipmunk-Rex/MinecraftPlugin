package mrpg.rexgood.Files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BlockExp{
    public boolean Makefile(){//파일 생성
        File file = new File("plugins\\MRPG", "BlockExp"+ ".yml");
        try {
            if (file.createNewFile()) {
                System.out.println("블럭경험치 파일이 생성되었습니다.");
                return false;
            } else {
                System.out.println("블럭경험치 파일이 이미 존재합니다.");
                return true;
            }
        } catch (IOException IoE) {
            IoE.printStackTrace();
            return false;
        }
    }
    public void SetFile(String par,String chi) {
        File file = new File("plugins\\MRPG", "BlockExp" + ".yml");
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set(par,chi);
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String GetFile(String par) {
        File file = new File("plugins\\MRPG", "BlockExp" + ".yml");
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        try {
            return fileConfiguration.get(par).toString();
        } catch (Exception e) {
            return null;
        }
    }
}

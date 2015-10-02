package pl.dans.plugins.bowfighters;

import pl.dans.plugins.bowfighters.commands.BfCommandExecutor;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import pl.dans.plugins.bowfighters.listeners.CraftListener;
import pl.dans.plugins.bowfighters.listeners.InteractionListener;
import pl.dans.plugins.bowfighters.listeners.MeleeDamageListener;


public class Bowfighters extends JavaPlugin {
    
    private boolean running = false;
    
    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}onEnable", ChatColor.RED);
        
        getServer().getPluginManager().registerEvents(new MeleeDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new CraftListener(this), this);
        getServer().getPluginManager().registerEvents(new InteractionListener(this), this);
        
        getCommand("bf").setExecutor(new BfCommandExecutor(this));
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}onDisable", ChatColor.RED);
    }

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    
    
}

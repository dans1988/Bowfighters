package pl.dans.plugins.bowfighters.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import pl.dans.plugins.bowfighters.Bowfighters;


public class CraftListener implements Listener {
    private final Bowfighters bowfighters;

    public CraftListener(Bowfighters bowfighters) {
        this.bowfighters = bowfighters;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onCraft(final CraftItemEvent craftItemEvent)  {
        
        if (!bowfighters.getRunning()) {
            return;
        }
        
        if(craftItemEvent.getCurrentItem().getType().equals(Material.STONE_SWORD)
                || craftItemEvent.getCurrentItem().getType().equals(Material.IRON_SWORD)
                || craftItemEvent.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)
                || craftItemEvent.getCurrentItem().getType().equals(Material.IRON_AXE)
                || craftItemEvent.getCurrentItem().getType().equals(Material.DIAMOND_AXE)) {
            craftItemEvent.setCancelled(true);
            
            Player player = Bukkit.getServer().getPlayer(craftItemEvent.getWhoClicked().getName());
            
            if (player != null) {
                player.sendMessage(ChatColor.RED + "You can't craft that in bowfighters!");
            }
        }
    }
}

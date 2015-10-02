package pl.dans.plugins.bowfighters.listeners;

import org.bukkit.ChatColor;
import pl.dans.plugins.bowfighters.Bowfighters;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;


public class MeleeDamageListener implements Listener{
    
    private final Bowfighters bowfighters;

    public MeleeDamageListener(Bowfighters bowfighters) {
        this.bowfighters = bowfighters;
    }
    
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void damage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        
        if (!bowfighters.getRunning()) {
            return;
        }
        
        if(!(entityDamageByEntityEvent.getDamager() instanceof Player)
                || !(entityDamageByEntityEvent.getEntity() instanceof Player)) {
            return;
        }

        Player attacker = (Player) entityDamageByEntityEvent.getDamager();
        
        Player victim = (Player) entityDamageByEntityEvent.getEntity();
        
        if (attacker.equals(victim)) {
            return;
        }
        
        
        ItemStack tool = attacker.getItemInHand();
        
        if (//tool.getType().equals(Material.STONE_SWORD)
                //|| tool.getType().equals(Material.IRON_SWORD)
                //|| tool.getType().equals(Material.DIAMOND_SWORD)
                //|| tool.getType().equals(Material.IRON_AXE)
                //|| tool.getType().equals(Material.DIAMOND_AXE)
                //|| 
                tool.getType().equals(Material.DIAMOND_PICKAXE)) {
            
            entityDamageByEntityEvent.setCancelled(true);
            
            attacker.sendMessage(ChatColor.RED + "PvP damage with that item is disabled!");
        }
    }
}

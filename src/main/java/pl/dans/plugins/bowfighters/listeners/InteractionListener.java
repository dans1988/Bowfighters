package pl.dans.plugins.bowfighters.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import pl.dans.plugins.bowfighters.Bowfighters;


public class InteractionListener implements Listener {

    private final Bowfighters bowfighters;

    public InteractionListener(Bowfighters bowfighters) {
        this.bowfighters = bowfighters;
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteract(final PlayerInteractEntityEvent playerInteractEntityEvent) {

        if (!bowfighters.getRunning()) {
            return;
        }

        if (playerInteractEntityEvent.getPlayer().getItemInHand().getType().equals(Material.STONE_SWORD)
                || playerInteractEntityEvent.getPlayer().getItemInHand().getType().equals(Material.IRON_SWORD)
                || playerInteractEntityEvent.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_SWORD)
                || playerInteractEntityEvent.getPlayer().getItemInHand().getType().equals(Material.IRON_AXE)
                || playerInteractEntityEvent.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_AXE)) {
            playerInteractEntityEvent.setCancelled(true);

            Player player = playerInteractEntityEvent.getPlayer();

            if (player != null) {
                player.sendMessage(ChatColor.RED + "You can't use that in bowfighters!");
            }
        }
    }

    @EventHandler
    public void onPickUp(final PlayerPickupItemEvent playerPickupItemEvent) {

        if (!bowfighters.getRunning() || playerPickupItemEvent.isCancelled()) {
            return;
        }

        if (playerPickupItemEvent.getItem().getItemStack().getType().equals(Material.STONE_SWORD)
                || playerPickupItemEvent.getItem().getItemStack().getType().equals(Material.IRON_SWORD)
                || playerPickupItemEvent.getItem().getItemStack().getType().equals(Material.DIAMOND_SWORD)
                || playerPickupItemEvent.getItem().getItemStack().getType().equals(Material.IRON_AXE)
                || playerPickupItemEvent.getItem().getItemStack().getType().equals(Material.DIAMOND_AXE)) {
            playerPickupItemEvent.setCancelled(true);

            Player player = playerPickupItemEvent.getPlayer();

            if (player != null) {
                player.sendMessage(ChatColor.RED + "You can't pick that up in bowfighters!");
            }
        }

    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent inventoryClickEvent) {
        
        if (!bowfighters.getRunning() || inventoryClickEvent.isCancelled()) {
            return;
        }
        
        if (inventoryClickEvent.getCurrentItem() == null) {
            return;
        }
        
        if (inventoryClickEvent.getCurrentItem().getType().equals(Material.STONE_SWORD)
                || inventoryClickEvent.getCurrentItem().getType().equals(Material.IRON_SWORD)
                || inventoryClickEvent.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)
                || inventoryClickEvent.getCurrentItem().getType().equals(Material.IRON_AXE)
                || inventoryClickEvent.getCurrentItem().getType().equals(Material.DIAMOND_AXE)) {
            
            
            inventoryClickEvent.setCancelled(true);
            inventoryClickEvent.setCurrentItem(null);
            

            Player player = (Player)inventoryClickEvent.getWhoClicked();

            if (player != null) {
                player.sendMessage(ChatColor.RED + "You can't pick that up in bowfighters!");
            }
        }
        
    }
}

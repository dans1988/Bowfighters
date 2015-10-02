package pl.dans.plugins.bowfighters.commands;

import pl.dans.plugins.bowfighters.Bowfighters;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;


public class BfCommandExecutor implements CommandExecutor {
    
    private final Bowfighters bowfighters;

    public BfCommandExecutor(Bowfighters bowfighters) {
        this.bowfighters = bowfighters;
    }
    

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args == null || args.length == 0) {
            return false;
        }
        
        String subcommand = args[0];
        
        
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        
        EnchantmentStorageMeta esm = (EnchantmentStorageMeta) book.getItemMeta();
        esm.addStoredEnchant(Enchantment.ARROW_INFINITE, 1, true);
        book.setItemMeta(esm);
        
        

        ItemStack arrow = new ItemStack(Material.ARROW);

        ItemStack string = new ItemStack(Material.STRING, 2);
        
        if (subcommand.compareToIgnoreCase("start") == 0) {
            
            if (bowfighters.getRunning()) {
                sender.sendMessage(ChatColor.RED + "Bowfighters is already running!");
                return true;
            }
            
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                
                player.getInventory().addItem(book, arrow, string);                
                
            }
            
            bowfighters.setRunning(true);
            
            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Bowfighters started! Items were distributed!");
            
            
        } else if (subcommand.compareToIgnoreCase("stop") == 0) {
            
            if (!bowfighters.getRunning()) {
                sender.sendMessage(ChatColor.RED + "Bowfighters is not running!");
                return true;
            }
            
            bowfighters.setRunning(false);
            
            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Bowfighters stopped!");
            
            
        } else if (subcommand.compareToIgnoreCase("resume") == 0) {
            
            if (bowfighters.getRunning()) {
                sender.sendMessage(ChatColor.RED + "Bowfighters is already running!");
                return true;
            }
            
            bowfighters.setRunning(true);
            
            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Bowfighters resumed!");
            
            
        } else if (subcommand.compareToIgnoreCase("status") == 0) {
            
            if (bowfighters.getRunning()) {
                sender.sendMessage(ChatColor.GOLD + "Bowfighters is running!");
            } else {
                sender.sendMessage(ChatColor.GOLD + "Bowfighters is stopped!");
            }

            
            
        } else if (subcommand.compareToIgnoreCase("items") == 0) {
            
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "You must specify a player!");
                return true;
            }
            
            if (!bowfighters.getRunning()) {
                sender.sendMessage(ChatColor.RED + "Bowfighters is not running!");
                return true;
            }
            
            String playerName = args[1];
            Player player = Bukkit.getPlayer(playerName);
            
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "Player is not online!");
                return true;
            }
            
            player.getInventory().addItem(book, arrow, string); 
            
            sender.sendMessage(ChatColor.GOLD + "Giving items to " + player.getName() + ".");
            player.sendMessage(ChatColor.GREEN + "You received the bowfighter items.");
            
        } 
        
        return true;
    }
    
    
}

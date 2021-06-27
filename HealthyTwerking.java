package me.infiware.healthytwerking;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class HealthyTwerking extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("The program has now started up!!!");
        getServer().getPluginManager().registerEvents(this,this);
    }

    // Best plugin below

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity().getPlayer();
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "FATALITY");
    }

    double twerkHealth = 1;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("extremetwerk")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                player.sendMessage(ChatColor.DARK_GREEN + "Shifting now gives " + ChatColor.RED + "10.0 HP");
                twerkHealth = 10.0;

            }
        }
        if(command.getName().equals("normaltwerk")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                player.sendMessage(ChatColor.DARK_GREEN + "Shifting now gives " + ChatColor.RED + "1.0 HP");
                twerkHealth = 1.0;

            }
        }
        if(command.getName().equals("supertwerk")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                player.sendMessage(ChatColor.DARK_GREEN + "Shifting now gives " + ChatColor.RED + "2.5 HP");
                twerkHealth = 2.5;

            }
        }
        if(command.getName().equals("sadtwerk")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                player.sendMessage(ChatColor.DARK_GREEN + "Shifting now gives " + ChatColor.RED + "0.5 HP");
                twerkHealth = 0.5;

            }
        }
        if(command.getName().equals("notwerk")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                player.sendMessage(ChatColor.DARK_GREEN + "Shifting now gives " + ChatColor.RED + "0.0 HP");
                twerkHealth = 0.0;

            }
        }



        return true;
    }

    // Actual Challenge

    @EventHandler
    public void onTwerkHeal(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        try {
            player.setHealth(player.getHealth() + twerkHealth);
        }
        catch(Exception e){
            double currentHealth = player.getHealth();
            currentHealth = 20 - currentHealth;

            player.setHealth(player.getHealth() + currentHealth);
        }
    }


}

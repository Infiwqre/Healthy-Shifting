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
        System.out.println(ChatColor.YELLOW + "The program has now started up!!!");
        System.out.println(ChatColor.GREEN + "Thank you for using OrganizedTeleportation by: Infiware");
        System.out.println(ChatColor.GREEN + "Please give a rating on spigotmc.org if you enjoy this plugin");
        getServer().getPluginManager().registerEvents(this,this);
    }

    // Best plugin below

    double twerkHealth = 1.0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;


        if(command.getName().equalsIgnoreCase("twerkHealth")) {
            if (sender instanceof Player) {
                if (args.length > 0) {
                    try {
                        twerkHealth = Double.parseDouble(args[0]);
                        player.sendMessage(ChatColor.DARK_GREEN + "Shifting now gives " + ChatColor.RED + twerkHealth + " HP");
                    } catch (IllegalArgumentException e) {
                        player.sendMessage(ChatColor.RED + "You must give a double as an argument");
                        player.sendMessage(ChatColor.RED + "Ex: 2.0, 0.1, 4.20 & etc.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You must give a double as an argument");
                    player.sendMessage(ChatColor.RED + "Ex: 2.0, 0.1, 4.20 & etc.");
                }
            }
        }
        if(command.getName().equals("notwerk")){
            if (sender instanceof Player){
                player.sendMessage(ChatColor.DARK_GREEN + "Shift Healing is now Disabled");

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

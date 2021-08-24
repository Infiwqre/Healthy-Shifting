package me.infiware.healthytwerking;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class HealthyTwerking extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println(ChatColor.YELLOW + "The program has now started up!!!");
        System.out.println(ChatColor.GREEN + "Thank you for using HealthyTwerking by: Infiware");
        System.out.println(ChatColor.GREEN + "Please give a rating on spigotmc.org if you enjoy this plugin");

        getCommand("twerkHealth").setExecutor(this);
        getCommand("noTwerk").setExecutor(this);

        getServer().getPluginManager().registerEvents(this,this);
    }

    public double twerkHealth = 1.0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        Player p = (Player) sender;


        if(command.getName().equalsIgnoreCase("twerkHealth")){


            if (sender instanceof Player) {
                if (args.length > 0) {
                    try {
                        twerkHealth = Double.parseDouble(args[0]);
                        p.sendMessage(ChatColor.DARK_GREEN + "Shifting now gives " + ChatColor.RED + twerkHealth + " HP");
                    } catch (IllegalArgumentException e) {
                        p.sendMessage(ChatColor.RED + "You must give a double as an argument");
                        p.sendMessage(ChatColor.RED + "Ex: 0.1, 6.9, 4.20 & etc.");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "You must give a double as an argument");
                    p.sendMessage(ChatColor.RED + "Ex: 0.1, 6.9, 4.20 & etc.");
                }
            }else{
                System.out.println("You must be a player to use this command");
            }
        }else if(command.getName().equalsIgnoreCase("noTwerk")){

            if (sender instanceof Player){

                p.sendMessage(ChatColor.DARK_GREEN + "Shift Healing is now Disabled");
                twerkHealth = 0;
            }else{
                System.out.println("You must be a player to use this command");
            }

        }






        return true;
    }

    @EventHandler
    public void onTwerkHeal(PlayerToggleSneakEvent event) {
        Player p = event.getPlayer();
        try {
            p.setHealth(p.getHealth() + twerkHealth);
        }
        catch(Exception e){
            double currentHealth = p.getHealth();
            currentHealth = 20 - currentHealth;

            p.setHealth(p.getHealth() + currentHealth);
        }
    }


}

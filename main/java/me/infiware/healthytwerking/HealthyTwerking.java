package me.infiware.healthytwerking;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class HealthyTwerking extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println(ChatColor.YELLOW + "The program has now started up!!!");
        System.out.println(ChatColor.GREEN + "Thank you for using HealthyTwerking by: Infiware");
        System.out.println(ChatColor.GREEN + "Please give a rating on spigotmc.org if you enjoy this plugin");

        getCommand("twerkHealth").setExecutor(this);
        getCommand("noTwerk").setExecutor(this);
        getCommand("twerkDelay").setExecutor(this);

        getServer().getPluginManager().registerEvents(this,this);
    }

    public double twerkHealth = 1.0;
    public long twerkDelayTime = 0;

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

        }else if(command.getName().equalsIgnoreCase("twerkDelay")){
            if (sender instanceof Player) {
                if (args.length > 0) {
                    try {
                        twerkDelayTime = Long.parseLong(args[0]);
                        p.sendMessage(ChatColor.DARK_GREEN + "There is now a " + ChatColor.RED + twerkDelayTime + ChatColor.DARK_GREEN + " millisecond delay");
                    } catch (IllegalArgumentException e) {
                        p.sendMessage(ChatColor.RED + "You must give an integer/long as an argument");
                        p.sendMessage(ChatColor.RED + "Ex: 0, 69, 420 & etc.");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "You must give an integer/long as an argument");
                    p.sendMessage(ChatColor.RED + "Ex: 01, 69, 420 & etc.");
                }
            }else{
                System.out.println("You must be a player to use this command");
            }
        }






        return true;
    }

    @EventHandler
    public void onTwerkHeal(PlayerToggleSneakEvent event){
        Player p = event.getPlayer();
        if (twerkDelayTime == 0) {
            try {
                p.setHealth(p.getHealth() + twerkHealth);
            }
            catch(Exception e){
                double currentHealth = p.getHealth();
                currentHealth = 20 - currentHealth;

                p.setHealth(p.getHealth() + currentHealth);
            }
        }else if (twerkDelayTime > 0) {
            try {
                Thread.sleep(twerkDelayTime);
                try {
                    p.setHealth(p.getHealth() + twerkHealth);
                }
                catch(Exception e){
                    double currentHealth = p.getHealth();
                    currentHealth = 20 - currentHealth;

                    p.setHealth(p.getHealth() + currentHealth);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }else if (twerkDelayTime < 0){
            p.sendMessage(ChatColor.RED + "You must give a positive number as an argument");
        }else{
            p.sendMessage(ChatColor.RED + "Please give a valid argument");
            p.sendMessage(ChatColor.RED + "Ex: 01, 69, 420 & etc.");
        }
    }


}

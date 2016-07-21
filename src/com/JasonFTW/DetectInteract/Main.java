package com.JasonFTW.DetectInteract;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	@Override
	public void onEnable(){
		loadConfiguration();
		getLogger().info("Config Found, loading!");
		
		getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public void onDisable(){

	}

	File configFile = new File(this.getDataFolder(), "config.yml");
	FileConfiguration users = YamlConfiguration.loadConfiguration(configFile);

	public void loadConfiguration(){
		//creating defaults
		this.saveDefaultConfig();
		getConfig().options().copyDefaults(true); 
		saveConfig();
		
		//String[] defaultItems = {"257", "270", "274", "278", "285"};
		
		//String path = "Settings.Items";
		//getConfig().set(path, Arrays.asList(defaultItems));
		//getConfig().addDefault("", "# Items support IDs only.");
		//getConfig().addDefault("", "# Get ids from: http://minecraft-ids.grahamedgecombe.com/");
		//getConfig().addDefault("", "# Created by JasonFTW");
		//getConfig().addDefault(path, "");
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public int getItems(){
		for(int i : getConfig().getIntegerList("Settings.Items")){
			return i;
		}
		return -1;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void playerInteract(PlayerInteractEvent e){
		if(e.getItem().equals(Material.getMaterial(getItems())) || e.getItem() != null || getItems() != -1){
			if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
				getServer().getLogger().info("Player " + e.getPlayer() + " RIGHT CLICKED with item " + e.getItem());
			}
			else if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
				getServer().getLogger().info("Player " + e.getPlayer() + " LEFT CLICKED with item " + e.getItem());
			}
		}
	}
}
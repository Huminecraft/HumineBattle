package com.humine.main;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.aypi.Aypi;
import com.humine.events.ArmorDeadEvent;
import com.humine.events.ConnectDropPlayerEvent;
import com.humine.events.ConnectPlayerEvent;
import com.humine.events.DeconnectPlayerEvent;
import com.humine.util.ArmorStand;
import com.humine.util.ArmorStandGestion;

public class BattleMain extends JavaPlugin
{

	private static BattleMain instance;
	private static HashMap<String, ArmorStand> armorStands;

	@Override
	public void onEnable()
	{
		// Initiation
		instance = this;
		armorStands = new HashMap<String, ArmorStand>();
		
		// Declaration des evenements
		this.getServer().getPluginManager().registerEvents(new ArmorDeadEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ConnectPlayerEvent(), this);
		this.getServer().getPluginManager().registerEvents(new DeconnectPlayerEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ConnectDropPlayerEvent(), this);

	}

	@Override
	public void onDisable()
	{
		for (Entry<String, ArmorStand> entry : ArmorStandGestion.getArmorStands().entrySet())
		{
			entry.getValue().getArmorStand().remove();
		}

		ArmorStandGestion.getArmorStands().clear();
	}

	public static void sendMessage(CommandSender sender, String message)
	{
		sender.sendMessage(message);
	}
	
	public static void sendBroadcastMessage(String message)
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			sendMessage(player, message);
		}
	}

	public static BattleMain getInstance()
	{
		return instance;
	}

	public HashMap<String, ArmorStand> getArmorStands()
	{
		return armorStands;
	}
	
	public void removeTimer(Player player)
	{
		boolean finish = false;
		int i = 0;

		while (i < Aypi.getTimerManager().getTimers().size() && finish == false)
		{
			if (Aypi.getTimerManager().getTimers().get(i).getName().equals(player.getName()))
			{
				Aypi.getTimerManager().getTimers().remove(Aypi.getTimerManager().getTimers().get(i));
				finish = true;
			}

			i++;
		}

	}

}

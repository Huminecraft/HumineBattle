package com.humine.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.humine.main.BattleMain;
import com.humine.util.ArmorStand;
import com.humine.util.ArmorStandGestion;

public class ConnectPlayerEvent implements Listener
{

	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		final Player player = event.getPlayer();
		ArmorStand armorStand = ArmorStandGestion.getPlayerArmorStand(player);

		if (armorStand != null)
		{
			if (armorStand.isArmorStandDead())
			{
				player.setHealth(0.0);
				armorStand.setPlayerAlive(false);
			}
			else
			{
				armorStand.setArmorStandDead(true);
				armorStand.setPlayerAlive(true);
			}
				

			armorStand.getArmorStand().remove();
			
			if (armorStand.canRemove())
			{
				ArmorStandGestion.removePlayerArmorStand(player);
			}

			BattleMain.getInstance().removeTimer(player);
		}

	}

}

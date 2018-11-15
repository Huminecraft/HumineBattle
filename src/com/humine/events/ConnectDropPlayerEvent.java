package com.humine.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.humine.util.ArmorStand;
import com.humine.util.ArmorStandGestion;

public class ConnectDropPlayerEvent implements Listener
{

	@EventHandler
	public void onDrop(PlayerDeathEvent event)
	{

		Player player = event.getEntity();
		ArmorStand armorStand = ArmorStandGestion.getPlayerArmorStand(player);

		if (armorStand != null)
		{
			event.getDrops().clear();
			armorStand.setPlayerBlockDrop(true);

			if (armorStand.canRemove())
			{
				ArmorStandGestion.removePlayerArmorStand(player);
			}
		}

	}
}

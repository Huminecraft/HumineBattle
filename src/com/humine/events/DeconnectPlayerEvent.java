package com.humine.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.aypi.utils.Timer;
import com.aypi.utils.inter.TimerFinishListener;
import com.humine.main.BattleMain;
import com.humine.util.ArmorStand;
import com.humine.util.ArmorStandGestion;

public class DeconnectPlayerEvent implements Listener
{

	@EventHandler
	public void onQuit(PlayerQuitEvent event)
	{
		final Player player = event.getPlayer();
		Timer timer;

		if (player.getGameMode() == GameMode.SURVIVAL)
		{
			
			ArmorStand playerArmorStand = new ArmorStand(player);
			ArmorStandGestion.getArmorStands().put(player.getName(), playerArmorStand);
			BattleMain.getInstance().removeTimer(player);
			
			timer = new Timer(BattleMain.getInstance(), 30, player.getName(), new TimerFinishListener() {

				@Override
				public void execute()
				{
					ArmorStand armorStand = ArmorStandGestion.getPlayerArmorStand(player);
					
					if (armorStand != null)
					{
						if (armorStand.isArmorStandDead())
						{
							player.setHealth(0.0);
						}
						else
						{
							armorStand.getArmorStand().remove();
						}

						if (armorStand.canRemove())
						{
							ArmorStandGestion.removePlayerArmorStand(player);
						}
						BattleMain.sendBroadcastMessage("ArmorStand: " + armorStand.getArmorStandName());
					}

				}
			});

			timer.start();
		}

	}
}

package com.humine.events;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.humine.main.BattleMain;
import com.humine.util.ArmorStand;
import com.humine.util.ArmorStandGestion;

public class ArmorDeadEvent implements Listener
{

	@EventHandler
	public void onDead(EntityDamageByEntityEvent event)
	{
		Entity victim = event.getEntity();
		Entity damager = event.getDamager();

		if (victim instanceof org.bukkit.entity.ArmorStand && damager instanceof Player)
		{
			org.bukkit.entity.ArmorStand entityArmorStand = (org.bukkit.entity.ArmorStand) victim;
			ArmorStand armorStand = ArmorStandGestion.getPlayerArmorStand(entityArmorStand);

			if (armorStand != null)
			{
				if (armorStand.getHealth() > 0)
				{
					armorStand.setHealth((byte) (armorStand.getHealth() - 3));
					makeBloodParticle(armorStand.getArmorStand().getLocation());
				}
				
				if(armorStand.getHealth() <= 0 && armorStand.isArmorStandDead() == false)
				{
					armorStand.getArmorStand().setInvulnerable(false);
					armorStand.getArmorStand().setHealth(0.0);
					armorStand.setArmorStandDead(true);

					if (armorStand.isArmorStandDrop() == false)
					{
						dropArmor(armorStand.getArmorStand(), armorStand.getArmorStandInventory());
						armorStand.setArmorStandDrop(true);
					}
					
					makeBloodParticle(armorStand.getArmorStand().getLocation());

					for (Player player : Bukkit.getOnlinePlayers())
					{
						BattleMain.sendMessage(player, armorStand.getArmorStandName() + " was slain by "
								+ damager.getName() + " (Disconnected)");
					}
				}
			}
		}
	}

	private void dropArmor(org.bukkit.entity.ArmorStand armor, ItemStack[] inventory)
	{
		if (inventory != null)
		{
			for (int i = 0; i < inventory.length; i++)
			{
				if (inventory[i] != null)
				{
					if (inventory[i].getType() != Material.AIR)
					{
						armor.getLocation().getWorld().dropItem(armor.getLocation(), inventory[i]);
					}
				}
			}
		}
	}

	private void makeBloodParticle(Location loc)
	{
		DustOptions dustOptions = new DustOptions(Color.RED, (float) 10.0);
		loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 10, 0, 0.5, 0, 2, dustOptions);
	}
}

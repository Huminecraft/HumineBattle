package com.humine.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.humine.main.BattleMain;


public class ArmorStand {

	private String armorStandName;
	private org.bukkit.entity.ArmorStand armorStand;
	private ItemStack[] armorStandInventory;
	
	private boolean armorStandDrop;
	private boolean armorStandDead;
	private boolean playerBlockDrop;
	private boolean playerAlive;
	private byte health;
	
	public ArmorStand(Player player) {
		
		this.armorStandName = player.getName();
		
		this.armorStand = player.getWorld().spawn(player.getLocation(), org.bukkit.entity.ArmorStand.class);
		this.armorStand = configArmorStand(this.armorStand, player);
		
		this.armorStandInventory = player.getInventory().getContents();
		
		
		this.armorStandDrop = false;
		this.armorStandDead = false;
		this.playerBlockDrop = false;
		this.playerAlive = false;
		
		this.health = 20;
		
	}
	
	private org.bukkit.entity.ArmorStand configArmorStand(org.bukkit.entity.ArmorStand armorStand, Player player) {
		org.bukkit.entity.ArmorStand Armor = armorStand;
		
		//DEBUT Configuration de l'armorStand
		Armor.setBasePlate(false);
		Armor.setSmall(false);
		Armor.setArms(true);
		Armor.setCollidable(true);
		Armor.setCustomName(player.getName());
		Armor.setCustomNameVisible(true);
		Armor.setVisible(true);
		Armor.setGravity(true);
		Armor.setInvulnerable(true);
		Armor.setHealth(20.0);
		//FIN Configuration de l'armorStand
		
		//DEBUT Mise en place des items sur le corp de l'armorStand
		if(player.getInventory().getHelmet() != null)
			Armor.setHelmet(player.getInventory().getHelmet());
		
		if(player.getInventory().getChestplate() != null)
			Armor.setChestplate(player.getInventory().getChestplate());
		
		if(player.getInventory().getLeggings() != null)
			Armor.setLeggings(player.getInventory().getLeggings());
		
		if(player.getInventory().getBoots() != null)
			Armor.setBoots(player.getInventory().getBoots());
		//FIN Mise en place des items sur le corp de l'armorStand
		
		return Armor;
	}
	
	public boolean canRemove() {
		if((this.armorStandDead && this.playerAlive) || (this.armorStandDead && this.armorStandDrop && this.playerBlockDrop))
			return true;
		else
			return false;
	}
	
	public void booleanDebug()
	{
		BattleMain.sendBroadcastMessage("===BOOLEAN===");
		
		if(this.armorStandDead)
			BattleMain.sendBroadcastMessage("armorStand dead : " + ChatColor.GREEN + this.armorStandDead);
		else
			BattleMain.sendBroadcastMessage("armorStand dead : " + ChatColor.RED + this.armorStandDead);
		
		if(this.armorStandDrop)
			BattleMain.sendBroadcastMessage("armorStand drop : " + ChatColor.GREEN + this.armorStandDrop);
		else
			BattleMain.sendBroadcastMessage("armorStand drop : " + ChatColor.RED + this.armorStandDrop);
		
		if(this.playerBlockDrop)
			BattleMain.sendBroadcastMessage("playerBlock drop : " + ChatColor.GREEN + this.playerBlockDrop);
		else
			BattleMain.sendBroadcastMessage("playerBlock drop : " + ChatColor.RED + this.playerBlockDrop);
		
		if(this.playerAlive)
			BattleMain.sendBroadcastMessage("player alive : " + ChatColor.GREEN + this.playerAlive);
		else
			BattleMain.sendBroadcastMessage("player alive : " + ChatColor.RED + this.playerAlive);
		
		BattleMain.sendBroadcastMessage("===BOOLEAN===");
	}

	public ItemStack[] getArmorStandInventory()
	{
		return armorStandInventory;
	}

	public void setArmorStandInventory(ItemStack[] armorStandInventory)
	{
		this.armorStandInventory = armorStandInventory;
	}

	public boolean isArmorStandDrop()
	{
		return armorStandDrop;
	}

	public void setArmorStandDrop(boolean armorStandDrop)
	{
		this.armorStandDrop = armorStandDrop;
	}

	public boolean isArmorStandDead()
	{
		return armorStandDead;
	}

	public void setArmorStandDead(boolean armorStandDead)
	{
		this.armorStandDead = armorStandDead;
	}

	public boolean isPlayerBlockDrop()
	{
		return playerBlockDrop;
	}

	public void setPlayerBlockDrop(boolean playerBlockDrop)
	{
		this.playerBlockDrop = playerBlockDrop;
	}

	public boolean isPlayerAlive()
	{
		return playerAlive;
	}

	public void setPlayerAlive(boolean playerAlive)
	{
		this.playerAlive = playerAlive;
	}

	public String getArmorStandName()
	{
		return armorStandName;
	}

	public org.bukkit.entity.ArmorStand getArmorStand()
	{
		return armorStand;
	}

	public byte getHealth()
	{
		return health;
	}

	public void setHealth(byte health)
	{
		this.health = health;
	}
}

package com.humine.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.entity.Player;

import com.humine.main.BattleMain;

public abstract class ArmorStandGestion
{

	// Contient tout les armorStand des Joueurs qui se sont deconnectes

	/*
	 * Renvoie l'armorStand du jeu, Renvoie null si le joueur n'a pas
	 * d'armorStand
	 * 
	 * @param player pour recuperer l'armorStand du joueur
	 */
	public static ArmorStand getPlayerArmorStand(Player player)
	{
		Set<String> players = BattleMain.getInstance().getArmorStands().keySet();
		Iterator<String> iterator = players.iterator();
		boolean find = false;
		ArmorStand armorStand = null;

		while (iterator.hasNext() && find == false)
		{
			String p = iterator.next();
			if (BattleMain.getInstance().getArmorStands().get(p).getArmorStandName().equals(player.getName()))
			{
				armorStand = BattleMain.getInstance().getArmorStands().get(p);
				find = true;
			}
		}

		return armorStand;
	}

	/*
	 * Renvoie l'armorStand du package du jeu (com.humine.util), Renvoie null si
	 * l'armorstand n'est pas lie
	 * 
	 * @param entityArmorStand pour recuperer l'armorStand et le relier a notre
	 * class ArmorStand
	 */
	public static ArmorStand getPlayerArmorStand(org.bukkit.entity.ArmorStand entityArmorStand)
	{
		boolean find = false;
		ArmorStand armorStand = null;

		Set<String> players = BattleMain.getInstance().getArmorStands().keySet();
		Iterator<String> iterator = players.iterator();

		while (iterator.hasNext() && find == false)
		{
			String player = iterator.next();
			if (BattleMain.getInstance().getArmorStands().get(player).getArmorStandName()
					.equals(entityArmorStand.getName()))
			{
				armorStand = BattleMain.getInstance().getArmorStands().get(player);
				find = true;
			}
		}

		return armorStand;
	}

	/*
	 * Permet de supprimer de la liste le couple player/armorStand, renvoie vrai
	 * si le couple est supprime sinon cela renvoie false
	 * 
	 * @param player pour recuperer l'armorStand du joueur
	 */
	public static boolean removePlayerArmorStand(Player player)
	{
		ArmorStand armorStand = getPlayerArmorStand(player);

		if (armorStand != null)
		{
			BattleMain.getInstance().getArmorStands().remove(armorStand.getArmorStandName());
			return true;
		}

		return false;
	}

	
	public static HashMap<String, ArmorStand> getArmorStands()
	{
		return BattleMain.getInstance().getArmorStands();
	}

}

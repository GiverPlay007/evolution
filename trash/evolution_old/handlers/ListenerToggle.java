package me.giverplay.evolution_old.handlers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import me.giverplay.evolution_old.Evolution;
import me.giverplay.evolution_old.api.manager.PlayerManager;

public class ListenerToggle implements Listener
{
	@EventHandler
	public void onClick(InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		PlayerManager manager = Evolution.getInstance().getPlayer(player.getName());
		InventoryView inv = player.getOpenInventory();
		ItemStack item = e.getCurrentItem();
		
		if((inv.getTitle() == "§0§lConfigs") && (item != null))
		{
			e.setCancelled(true);
			
			if(item.getType() == Material.WRITABLE_BOOK)
			{
				manager.setScoreboardEnable(!manager.isScoreboardEnabled());
				player.closeInventory();
			}
			
			if(item.getType() == Material.ENDER_PEARL)
			{
				manager.setTPAEnabled(!manager.isTPAEnabled());
				player.closeInventory();
			}
			
			if(item.getType() == Material.BOOK)
			{
				manager.setTellEnabled(!manager.getTellEnabled());
				player.closeInventory();
			}
		}
	}
}

package me.giverplay.evolution.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.giverplay.evolution.Evolution;
import me.giverplay.evolution.api.RankNovo;
import me.giverplay.evolution.api.comando.Comando;
import me.giverplay.evolution.api.comando.ComandoType;
import me.giverplay.evolution.api.manager.PlayerManager;

public class ComandoRankupNovo extends Comando
{
	private Evolution plugin;
	
	public ComandoRankupNovo()
	{
		super("rankup", ComandoType.PLAYER, "/rankup");
		plugin = Evolution.getInstance();
	}
	
	@Override
	public void execute(CommandSender sender, String[] args)
	{
		PlayerManager player = plugin.getPlayer(sender.getName());
		
		if(player.getRank().isLastRank())
		{
			player.sendMessage("§eVocê já está no último rank, meu consagrado");
			return;
		}
		
		final RankNovo proximo = plugin.getRanks().get(player.getRank().getNextRank());
		
		if(player.getLevel() < proximo.getMinLevel())
		{
			player.sendMessage("\n§cVocê não possui o seguinte requisito: §fNível " + proximo.getMinLevel() + "\n");
			return;
		}
		
		player.setRank(proximo);
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			plugin.sendAction(p, "§6" + player.getName() + " §eupou para " + proximo.getPrefix());
		}
	}
}

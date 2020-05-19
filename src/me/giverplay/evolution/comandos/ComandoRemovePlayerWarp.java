package me.giverplay.evolution.comandos;

import org.bukkit.command.CommandSender;

import me.giverplay.evolution.api.EvolutionAPI;
import me.giverplay.evolution.api.PlayerWarp;
import me.giverplay.evolution.api.comando.Comando;
import me.giverplay.evolution.api.comando.ComandoType;
import me.giverplay.evolution.api.manager.PlayerManager;

public class ComandoRemovePlayerWarp extends Comando
{
	
	public ComandoRemovePlayerWarp()
	{
		super("removeplayerwarp", ComandoType.PLAYER, "/removeplayerwarp <warp>");		
	}
	
	@Override
	public void execute(CommandSender sender, String[] args)
	{
		PlayerManager player = EvolutionAPI.getPlayer(sender.getName());
		
		if(!player.isVip()){
			player.sendMessage("�eEste � um recurso VIP! Compre qualquer grupo VIP para ter acesso a esse comando =D");
			return;
		}
		
		if(args.length == 0){
			player.sendMessage(getUsage());
			return;
		}
		
		PlayerWarp warp = EvolutionAPI.getPlayerWarp(player.getName(), args[0]);
		
		if(warp == null){
			player.sendMessage("�cVoc� n�o possui esta warp");
			return;
		}
		
		warp.deleteWarp();
		player.sendMessage("�cWarp deletada: �f" + warp.getWarpName());
	}
}

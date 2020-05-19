package me.giverplay.evolution.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.giverplay.evolution.api.EvolutionAPI;
import me.giverplay.evolution.api.comando.Comando;
import me.giverplay.evolution.api.comando.ComandoType;

public class ComandoHomeOf extends Comando
{
	public ComandoHomeOf()
	{
		super("home-of", ComandoType.PLAYER, "/home-of <jogador> [casa]");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args)
	{
    Player player = (Player) sender;
    
    if (!player.hasPermission("evolution.homeof"))
    {
        player.sendMessage("�cSem permiss�o");
        return;
    }

    if(args.length == 0)
    {
    	player.sendMessage(getUsage());
    	return;
    }

    Player target = Bukkit.getPlayer(args[0]);

    if(target == null)
    {
        player.sendMessage("�CJogador offline ou desconhecido");
    }

    EvolutionAPI.teleportHomeOf(player, target.getUniqueId().toString(), args);
	}
}

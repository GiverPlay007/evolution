package me.giverplay.evolution_old.comandos;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.giverplay.evolution_old.Evolution;
import me.giverplay.evolution_old.api.comando.Comando;
import me.giverplay.evolution_old.api.comando.ComandoType;

public class ComandoHome extends Comando
{
	public ComandoHome()
	{
		super("home", ComandoType.PLAYER, "/home [casa]");
	}

	@Override
	public void execute(CommandSender sender, String[] args)
	{
		Player player = (Player) sender;
		String uuid = player.getUniqueId().toString();

		Evolution.getInstance().teleportHome(player, uuid, args);
	}
}
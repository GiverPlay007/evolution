package me.giverplay.evolution_old.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.giverplay.evolution_old.Evolution;
import me.giverplay.evolution_old.api.comando.Comando;
import me.giverplay.evolution_old.api.comando.ComandoType;
import me.giverplay.evolution_old.api.manager.PlayerManager;

public class ComandoTell extends Comando
{
	public ComandoTell()
	{
		super("tell", ComandoType.PLAYER, "/tell [<jogador> <mensagem>]");
	}

	@Override
	public void execute(CommandSender sender, String[] args)
	{
		PlayerManager player = Evolution.getInstance().getPlayer(sender.getName());

		if(args.length == 0)
		{
			player.setTellEnabled(!player.getTellEnabled());
			return;
		}
		
		if(args.length == 1)
		{
			player.sendMessage(getUsage());
			return;
		}

		Player alvo = (Player) Bukkit.getPlayer(args[0]);

		if(alvo == null)
		{
			player.sendMessage("§cJogador não encontrado!");
			return;
		}

		if(alvo == player.getPlayer())
		{
			sender.sendMessage("§cVocê não pode mandar um tell para você mesmo");
			return;
		}
		
		PlayerManager target = Evolution.getInstance().getPlayer(alvo.getName());
		
		if(!target.getTellEnabled())
		{
			sender.sendMessage("§cJogador com tell desativado!");
			return;
		}

		if(!player.getTellEnabled())
		{
			player.setTellEnabled(true);
		}

		StringBuilder sb = new StringBuilder();

		for(int i = 1; i < args.length; i++)
		{
			sb.append(" ");
			sb.append(args[i]);
		} 

		String msg = sb.toString();

		sender.sendMessage("§6[§eMensagem para " + target.getName() + "§6]§f" + msg);
		target.sendMessage("§6[§eMensagem de " + sender.getName() + "§6]§f" + msg);

		target.setReply(player.getPlayer());
		player.setReply(target.getPlayer());
	}
}

package me.repocord.server_manager.commands;

import me.repocord.server_manager.helpers.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "Help", "Shows you help about the bot.");
    }

    @Override
    protected void execute(String[] args, GuildMessageReceivedEvent event) {
        // TODO finish help command
        event.getChannel().sendMessage("Test").queue();
    }

    @Override
    protected void execute(String[] args, PrivateMessageReceivedEvent event) {

    }
}

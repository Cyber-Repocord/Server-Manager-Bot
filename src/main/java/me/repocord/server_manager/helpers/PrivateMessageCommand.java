package me.repocord.server_manager.helpers;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class PrivateMessageCommand extends Command {
    protected PrivateMessageCommand(String id, String name, String description) {
        super(true, false, id, name, description);
    }

    @Override
    public void execute(String[] args, GuildMessageReceivedEvent event) {}
}

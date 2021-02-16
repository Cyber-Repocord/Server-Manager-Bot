package me.repocord.server_manager.helpers;

import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

public abstract class GuildCommand extends Command {
    protected GuildCommand(String id, String name, String description) {
        super(false, true, id, name, description);
    }

    @Override
    protected void execute(String[] args, PrivateMessageReceivedEvent event) {}
}

package me.repocord.server_manager.helpers;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

public abstract class Command {
    private final boolean dms;
    private final boolean guilds;
    private final String id;
    private final String name;
    private final String description;

    protected Command(boolean dms, boolean guilds, String id, String name, String description) {
        this.dms = dms;
        this.guilds = guilds;
        this.id = id;
        this.name = name;
        this.description = description;
    }
    protected Command(String id, String name, String description) {
        this(true, true, id, name, description);
    }

    public final void execute(GuildMessageReceivedEvent event) {
        if (guilds) {
            String[] args = event.getMessage().getContentRaw().split(" ");
            execute(args, event);
        }
    }
    public final void execute(PrivateMessageReceivedEvent event) {
        if (dms) {
            String[] args = event.getMessage().getContentRaw().split(" ");
            execute(args, event);
        }
    }

    public final String getID() {
        return id;
    }
    public final String getName() {
        return name;
    }
    public final String getDescription() {
        return description;
    }

    abstract protected void execute(String[] args, GuildMessageReceivedEvent event);
    abstract protected void execute(String[] args, PrivateMessageReceivedEvent event);
}

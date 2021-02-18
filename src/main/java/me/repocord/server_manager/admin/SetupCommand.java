package me.repocord.server_manager.admin;

import me.repocord.server_manager.helpers.GuildCommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class SetupCommand extends GuildCommand {
    public SetupCommand() {
        super("setup", "Setup Command", "Sets channels, points etc. ADMIN ONLY");
    }

    @Override
    protected void execute(String[] args, GuildMessageReceivedEvent event) {

    }
}

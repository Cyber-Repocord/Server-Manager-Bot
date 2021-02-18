package me.repocord.server_manager.admin;

import me.repocord.server_manager.Config;
import me.repocord.server_manager.helpers.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

public class BackupCommand extends Command {
    public BackupCommand() {
        super("backup", "Backup", "Backs data up to a specified channel.");
    }

    @Override
    protected void execute(String[] args, GuildMessageReceivedEvent event) throws Exception {
        Config.getConfigFile().backupNow();
    }

    @Override
    protected void execute(String[] args, PrivateMessageReceivedEvent event) throws Exception {
        Config.getConfigFile().backupNow();
    }
}

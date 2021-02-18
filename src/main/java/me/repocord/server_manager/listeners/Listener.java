package me.repocord.server_manager.listeners;

import me.repocord.server_manager.Config;
import me.repocord.server_manager.Logger;
import me.repocord.server_manager.helpers.Command;
import me.repocord.server_manager.helpers.Module;
import me.repocord.server_manager.helpers.StatusManager;
import me.repocord.server_manager.admin.AdminModule;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class Listener extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (isAdmin(event.getAuthor().getId())) {
            if (executeAdmin(event)) return;
        }
        execute(event);
    }
    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        if (isAdmin(event.getAuthor().getId())) {
            if (executeAdmin(event)) return;
        }
        execute(event);
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        Logger.log("Bot is ready!");
        StatusManager.start(event.getJDA());

        List<Guild> guilds = event.getJDA().getGuilds();
        TextChannel channel = null;
        for (Guild guild : guilds) {
            for (TextChannel textChannel : guild.getTextChannels()) {
                if (textChannel.getId().equals("811879847256915989")) channel = textChannel;
            }
        }
        if (channel == null) Logger.error("Couldn't find channel for backup.");
        else Config.setConfigFile(channel);
    }
    // TODO add reaction listener

    private void execute(GuildMessageReceivedEvent event) {
        String commandAsString = event.getMessage().getContentRaw().split(" ")[0];
        Command command;
        for (Module module : Config.getModules()) {
            command = module.getCommandWithPrefix(commandAsString);
            if (command != null) {
                command.execute(event);
                return;
            }
        }
    }
    private boolean executeAdmin(GuildMessageReceivedEvent event) {
        String commandAsString = event.getMessage().getContentRaw().split(" ")[0];
        Module module = new AdminModule();
        Command command = module.getCommandWithPrefix(commandAsString);
        if (command != null) {
            command.execute(event);
            return true;
        }
        return false;
    }

    private void execute(PrivateMessageReceivedEvent event) {
        String commandAsString = event.getMessage().getContentRaw().split(" ")[0];
        Command command;
        for (Module module : Config.getModules()) {
            command = module.getCommandWithPrefix(commandAsString);
            if (command != null) {
                command.execute(event);
                return;
            }
        }
    }
    private boolean executeAdmin(PrivateMessageReceivedEvent event) {
        String commandAsString = event.getMessage().getContentRaw().split(" ")[0];
        Module module = new AdminModule();
        Command command = module.getCommandWithPrefix(commandAsString);
        if (command != null) {
            command.execute(event);
            return true;
        }
        return false;
    }

    private boolean isAdmin(String id) {
        return Config.getAdmins().contains(id);
    }
}

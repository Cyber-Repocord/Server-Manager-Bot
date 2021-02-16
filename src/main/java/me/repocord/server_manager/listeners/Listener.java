package me.repocord.server_manager.listeners;

import me.repocord.server_manager.Config;
import me.repocord.server_manager.Logger;
import me.repocord.server_manager.helpers.Command;
import me.repocord.server_manager.helpers.Module;
import me.repocord.server_manager.helpers.StatusManager;
import me.repocord.server_manager.modules.AdminModule;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

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
    }

    // Guild
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

    // DMs
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
package me.repocord.server_manager.commands;

import me.repocord.server_manager.Config;
import me.repocord.server_manager.helpers.Command;
import me.repocord.server_manager.helpers.Module;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.awt.*;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "Help", "Shows you help about the bot.");
    }

    @Override
    protected void execute(String[] args, GuildMessageReceivedEvent event) {
        // TODO finish help command
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
        builder.setTitle("Help menu");
        builder.setDescription("To get help about specific command type \"" + Config.PREFIX + "help command <command>\".\nTo get help about specific module type \"" + Config.PREFIX + "help <module>\".");
        builder.setColor(new Color(0x0DD0FF));
        for (Module module : Config.getModules()) {
            builder.addField(new MessageEmbed.Field(module.getTitle() + " (" + module.getID() + ")", module.getDescription(), true));
        }
        event.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    protected void execute(String[] args, PrivateMessageReceivedEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
        builder.setTitle("Help menu");
        builder.setDescription("To get help about specific command type \"" + Config.PREFIX + "help command <command>\".\nTo get help about specific module type \"" + Config.PREFIX + "help <module>\".");
        builder.setColor(new Color(0x0DD0FF));
        for (Module module : Config.getModules()) {
            builder.addField(new MessageEmbed.Field(module.getTitle() + " (" + module.getID() + ")", module.getDescription(), true));
        }
        event.getChannel().sendMessage(builder.build()).queue();
    }
}

package me.repocord.server_manager.helpers;

import me.repocord.server_manager.Config;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    final List<Command> commands;
    private final String title;
    private final String description;
    private final String id;

    protected Module(String title, String description, String id) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.commands = getCommandsForModule(new ArrayList<>());
    }

    public final String getTitle() {
        return title;
    }
    public final String getDescription() {
        return description;
    }
    public final String getID() {
        return id;
    }
    public final List<Command> getCommands() {
        return commands;
    }

    public final Command getCommandById(String id) {
        for (Command command : commands) {
            if (command.getID().equalsIgnoreCase(id)) return command;
        }
        return null;
    }
    public final Command getCommandWithPrefix(String id) {
        for (Command command : commands) {
            if ((Config.PREFIX + command.getID()).equalsIgnoreCase(id)) return command;
        }
        return null;
    }

    protected abstract List<Command> getCommandsForModule(List<Command> commands);
}

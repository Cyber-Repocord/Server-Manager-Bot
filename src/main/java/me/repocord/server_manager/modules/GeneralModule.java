package me.repocord.server_manager.modules;

import me.repocord.server_manager.commands.HelpCommand;
import me.repocord.server_manager.helpers.Command;
import me.repocord.server_manager.helpers.Module;

import java.util.List;

public final class GeneralModule extends Module {
    public GeneralModule() {
        super("General", "Contains all the general commands!", "general");
    }

    protected List<Command> getCommandsForModule(List<Command> commands) {
        commands.add(new HelpCommand());
        return commands;
    }
}

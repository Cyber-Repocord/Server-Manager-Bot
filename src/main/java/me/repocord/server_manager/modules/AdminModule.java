package me.repocord.server_manager.modules;

import me.repocord.server_manager.helpers.Command;
import me.repocord.server_manager.helpers.Module;

import java.util.List;

public final class AdminModule extends Module {
    public AdminModule() {
        super("Admin", "Admin commands", "admin");
    }

    @Override
    protected List<Command> getCommandsForModule(List<Command> commands) {
        return commands;
    }
}

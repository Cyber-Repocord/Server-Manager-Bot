package me.repocord.server_manager;

import me.repocord.server_manager.helpers.Module;
import me.repocord.server_manager.helpers.StatusManager;
import me.repocord.server_manager.modules.GeneralModule;
import net.dv8tion.jda.api.entities.Activity;

import java.util.ArrayList;
import java.util.List;

public final class Config {
    // public vars
    public static final String PREFIX = "s!";
    public static final String TOKEN = ""; // new Scanner(System.in).nextLine();
    public static final boolean USE_SHARDING = false;
    public static final int SHARD_COUNT = 0;
    public static final int POINTS_COOLDOWN_IN_MINUTES = 3;

    // private vars
    private static final List<Module> modules = new ArrayList<>();
    private static final List<String> admins = new ArrayList<>();
    private static final List<StatusManager.Status> statuses = new ArrayList<>();

    // public methods
    public static List<Module> getModules() { return modules; }
    public static List<String> getAdmins() { return admins; }
    public static List<StatusManager.Status> getStatuses() { return statuses; }

    // static
    static {
        modules.add(new GeneralModule());

        admins.add("542243770113064961");

        statuses.add(new StatusManager.Status(Activity.ActivityType.WATCHING, jda -> jda.getGuilds().get(0).retrieveMetaData().complete().getApproximateMembers() + " members"));
        statuses.add(new StatusManager.Status(Activity.ActivityType.WATCHING, jda -> "type " + Config.PREFIX + "help for help"));

    }
}

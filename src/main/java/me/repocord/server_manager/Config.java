package me.repocord.server_manager;

import me.repocord.server_manager.helpers.Module;
import me.repocord.server_manager.helpers.StatusManager;
import me.repocord.server_manager.modules.GeneralModule;
import net.dv8tion.jda.api.entities.Activity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Config {
    private Config() {}
    // public vars
    public static final String PREFIX = "s!";
    public static final String TOKEN = getToken(); // new Scanner(System.in).nextLine();
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
        // TODO add more modules and commands
        modules.add(new GeneralModule());

        admins.add("542243770113064961");

        statuses.add(new StatusManager.Status(Activity.ActivityType.WATCHING, jda -> jda.getGuilds().get(0).retrieveMetaData().complete().getApproximateMembers() + " members"));
        statuses.add(new StatusManager.Status(Activity.ActivityType.WATCHING, jda -> "type " + Config.PREFIX + "help for help"));

    }

    private static String getToken() {
        // return ""; // <-- Put your token here, if you don't wanna use file reading, also you have to comment the code (after return) for it to work

        String MAC_PATH = "/Users/max/Desktop/token.txt";
        String WIN_PATH = "/Users/max/Desktop/token.txt";

        String token;

        try {
            Scanner file;
            if (System.getProperty("os.name").equals("Mac OS X")) {
                file = new Scanner(new File(MAC_PATH));
            } else {
                file = new Scanner(new File(WIN_PATH));
            }
            token = file.nextLine();
            file.close();
        } catch (IOException e) {
            System.out.print("Couldn't read token from file. Please enter it here: ");
            Scanner input = new Scanner(System.in);

            token = input.nextLine();
        }
        return token;
    }
}

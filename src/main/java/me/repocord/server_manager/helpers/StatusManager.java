package me.repocord.server_manager.helpers;

import me.repocord.server_manager.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;

import java.util.Timer;
import java.util.TimerTask;

public final class StatusManager {
    private StatusManager() {}

    private static boolean started = false;
    private static JDA jda;

    public static void start(JDA jda) {
        if (started) return;
        started = true;

        if (Config.getStatuses().isEmpty()) return;

        StatusManager.jda = jda;

        startThread();
    }

    private static void startThread() {
        Timer timer = new Timer();
        TimerTask task = new StatusChanger();
        timer.schedule(task, 0, 3000);
    }

    private static class StatusChanger extends TimerTask {
        private int index = 0;

        @Override
        public void run() {
            if (index >= Config.getStatuses().size()) index = 0;
            jda.getPresence().setActivity(Config.getStatuses().get(index).getActivity(jda));

            index++;
        }
    }

    public static class Status {
        private final Activity.ActivityType type;
        private final Updater updater;

        public Status(Activity.ActivityType type, Updater updater) {
            this.type = type;
            this.updater = updater;
        }

        public final Activity getActivity(JDA jda) {
            return Activity.of(type, updater.run(jda));
        }
    }

    public interface Updater {
        String run(JDA jda);
    }
}

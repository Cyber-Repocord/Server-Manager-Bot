package me.repocord.server_manager.helpers;

import me.repocord.server_manager.Logger;
import net.dv8tion.jda.api.entities.TextChannel;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public final class ConfigFile {
    // TODO
    private final File file;
    private Data data;

    private ConfigFile(String path, boolean backup, TextChannel channel, int intervalInHours) throws IOException {
        File file = new File(path);
        if (file.getPath().endsWith(".json")) throw new IOException("File is not a .json file!");
        this.file = file;

        if (!file.exists()) {
            data = new Data();
            save();
        } else {
            data = read();
        }
        if (backup) {
            Backupper backupper = new Backupper(intervalInHours, channel);
            backupper.start();
        }
    }
    public ConfigFile(String path) throws IOException {
        this(path, false, null, 0);
    }
    public ConfigFile(String path, TextChannel channel, int intervalInHours) throws IOException {
        this(path, true, channel, intervalInHours);
    }

    private Data read() {
        return new Data();
    }
    public void save() {

    }
    private void reload() {
        data = read();
    }

    private final class Data {

    }

    private final class Backupper {
        private boolean started = false;
        private final int intervalInHours;
        private final TextChannel channel;

        public Backupper(int intervalInHours, TextChannel channel) {
            this.intervalInHours = intervalInHours;
            this.channel = channel;
        }

        public void start() {
            if (!started) started = true;
            else return;

            Timer timer = new Timer();
            TimerTask task = new BackupTask();

            int intervalToMillis = intervalInHours * 3600000;

            timer.schedule(task, intervalToMillis, intervalToMillis);
        }

        private final class BackupTask extends TimerTask {

            @Override
            public void run() {
                try {
                    save();
                    channel.sendFile(file).queue();
                } catch (Exception e) {
                    Logger.error("Couldn't save or send data file while back-upping.");
                }

            }
        }
    }
}

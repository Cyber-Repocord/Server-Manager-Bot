package me.repocord.server_manager.helpers;

import me.repocord.server_manager.Logger;
import net.dv8tion.jda.api.entities.TextChannel;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public final class ConfigFile {
    // TODO
    private final File file;
    private Data data;
    private final TextChannel channel;

    private ConfigFile(String path, boolean backup, TextChannel channel, int intervalInMinutes) throws IOException {
        File file = new File(path);
        if (!file.getPath().endsWith(".json")) throw new IOException("File is not a .json file!");
        this.file = file;
        this.channel = channel;

        if (!file.exists()) {
            data = new Data();
            save();
        } else {
            data = read();
        }
        if (backup) {
            if (intervalInMinutes <= 0) intervalInMinutes = 1;
            Backupper backupper = new Backupper(intervalInMinutes);
            backupper.start();
        }
    }
    public ConfigFile(String path, TextChannel channel, int intervalInMinutes) throws IOException {
        this(path, true, channel, intervalInMinutes);
    }
    public ConfigFile(String path, TextChannel channel) throws IOException {
        this(path, false, channel, 0);
    }

    private Data read() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));

        in.readLine();
        return new Data();
    }

    public void save() throws IOException {
        if (!file.exists()) if (!file.createNewFile()) throw new IOException("Couldn't create file!");
        FileWriter out = new FileWriter(file);
        out.write("Saved at " + Logger.getTime());
        out.close();
    }

    public void backupNow() throws IOException {
        save();
        channel.sendFile(file).queue();
    }

    public void reload(boolean save) throws IOException {
        if (save) save();
        data = read();
    }

    private static final class Data {
        public String toJSON() {
            return "";
        }
    }

    private final class Backupper {
        private boolean started = false;
        private final int intervalInMinutes;

        public Backupper(int intervalInMinutes) {
            this.intervalInMinutes = intervalInMinutes;
        }

        public void start() {
            if (!started) started = true;
            else return;

            Timer timer = new Timer();
            TimerTask task = new BackupTask();

            int intervalToMillis = intervalInMinutes * 60000;

            timer.schedule(task, intervalToMillis, intervalToMillis);
        }

        private final class BackupTask extends TimerTask {

            @Override
            public void run() {
                try {
                    backupNow();
                } catch (Exception e) {
                    Logger.error("Couldn't backup automatically. There was a problem: " + e.getMessage());
                }
            }
        }
    }
}

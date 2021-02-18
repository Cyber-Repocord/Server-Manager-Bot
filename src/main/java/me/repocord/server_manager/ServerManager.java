package me.repocord.server_manager;

import me.repocord.server_manager.helpers.ConfigFile;
import me.repocord.server_manager.listeners.Listener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public final class ServerManager {
    private ServerManager() {}

    public static void main(String[] args) {
        try {
            if (Config.USE_SHARDING) start(Config.TOKEN, Config.SHARD_COUNT);
            else start(Config.TOKEN);
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    private static void start(String token) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.addEventListeners(new Listener());
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.build();
    }

    private static void start(String token, int shardCount) throws LoginException {
        JDABuilder shardBuilder = JDABuilder.createDefault(token);

        shardBuilder.addEventListeners(new Listener());
        shardBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        for (int i = 0; i < shardCount; i++)
        {
            shardBuilder.useSharding(i, shardCount).build();
        }
    }
}

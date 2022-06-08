package tokyo.ramune;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import tokyo.ramune.config.Config;
import tokyo.ramune.util.Logger;

public class Main {

    private static Config config;
    private static JDA jda;

    public static void main(String[] args) {
        config = new Config();
        config.load();

        if (config.getBotToken().equals("TOKEN")) {
            Logger.warn("Please change bot token in config.json");
            return;
        }

        try {
            jda = JDABuilder.createDefault(config.getBotToken())
                    .setActivity(Activity.listening(config.getBotToken() + "helpでヘルプを表示"))
                    .build();
        } catch (Exception e) {
            Logger.warn("Couldn't login discord bot");
            Logger.warn("Bot Token was wrong?");
            return;
        }
    }

    public static Config getConfig() {
        return config;
    }

    public static JDA getJDA() {
        return jda;
    }
}
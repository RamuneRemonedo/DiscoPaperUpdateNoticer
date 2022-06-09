package tokyo.ramune;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import tokyo.ramune.config.Config;
import tokyo.ramune.discord.command.CommandHandler;
import tokyo.ramune.discord.command.paper.GetBuildsCommand;
import tokyo.ramune.discord.command.paper.GetProjectsCommand;
import tokyo.ramune.discord.command.paper.GetVersionsCommand;
import tokyo.ramune.discord.command.util.HelpCommand;
import tokyo.ramune.discord.listener.CommandListener;
import tokyo.ramune.papermc.PaperMCAPIHandler;
import tokyo.ramune.papermc.PaperMCUpdate;
import tokyo.ramune.util.Logger;

import java.util.ArrayList;

public class Main {

    private static Config config;
    private static JDA jda;
    private final static ArrayList<PaperMCUpdate> paperMCUpdates = new ArrayList<>();

    public static void main(String[] args) {
        config = new Config();
        config.load();

        if (config.getBotToken().equals("TOKEN")) {
            Logger.warn("Please change bot token in config.json");
            return;
        }

        try {
            jda = JDABuilder.createDefault(config.getBotToken())
                    .setActivity(Activity.listening(config.getPrefix() + "helpでヘルプを表示"))
                    .build();
            jda.awaitReady();
            Logger.info("Successfully login discord bot");
            Logger.info("Login as " + jda.getSelfUser().getName() + " / " + jda.getSelfUser().getId());
        } catch (Exception e) {
            Logger.warn("Couldn't login discord bot");
            Logger.warn("Bot Token was wrong?");
            return;
        }

        addListener();
        registerCommands();

        // papermc
        try {
            for (String project : PaperMCAPIHandler.getProjects()) {
                paperMCUpdates.add(new PaperMCUpdate(project));
                Logger.info("project: " + project);
            }
            Logger.info("Successfully get projects from PaperMC!!");
        } catch (Exception e) {
            Logger.warn("Couldn't get projects from PaperMC API...");
        }
    }

    private static void addListener() {
        jda.addEventListener(new CommandListener());
    }

    private static void registerCommands() {
        CommandHandler.registerCommand(new HelpCommand());
        CommandHandler.registerCommand(new GetProjectsCommand());
        CommandHandler.registerCommand(new GetVersionsCommand());

        CommandHandler.registerCommand(new GetBuildsCommand());
    }

    public static Config getConfig() {
        return config;
    }

    public static JDA getJDA() {
        return jda;
    }

    public static ArrayList<PaperMCUpdate> getPaperMCUpdates() {
        return paperMCUpdates;
    }
}
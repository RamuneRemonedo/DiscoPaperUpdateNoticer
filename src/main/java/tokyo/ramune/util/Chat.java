package tokyo.ramune.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import tokyo.ramune.Main;

import java.awt.*;
import java.util.Random;

public class Chat {

    private static final JDA jda = Main.getJDA();

    public static Message success(MessageChannel target, String title, String description) {
        final Message[] message = new Message[1];
        target.sendMessageEmbeds(new EmbedBuilder()
                .setColor(Color.GREEN)
                .setAuthor(jda.getSelfUser().getName(), jda.getInviteUrl(Permission.ADMINISTRATOR), jda.getSelfUser().getAvatarUrl())
                .setTitle(title, jda.getInviteUrl(Permission.ADMINISTRATOR))
                .setDescription(description)
                .build()
        ).queue(_message -> message[0] = _message);
        return message[0];
    }

    public static void error(MessageChannel target, String title, String description) {
        target.sendMessageEmbeds(new EmbedBuilder()
                .setColor(Color.RED)
                .setAuthor(jda.getSelfUser().getName(), jda.getInviteUrl(Permission.ADMINISTRATOR), jda.getSelfUser().getAvatarUrl())
                .setTitle(title, jda.getInviteUrl(Permission.ADMINISTRATOR))
                .setDescription(description)
                .build()
        ).queue();
    }

    public static void requirePermission(MessageChannel target, String permission) {
        target.sendMessageEmbeds(new EmbedBuilder()
                .setColor(Color.MAGENTA)
                .setAuthor(jda.getSelfUser().getName(), jda.getInviteUrl(Permission.ADMINISTRATOR), jda.getSelfUser().getAvatarUrl())
                .setTitle("権限不足", jda.getInviteUrl(Permission.ADMINISTRATOR))
                .setDescription("それを実行するのには権限が必要です。 必要な権限: " + permission)
                .build()
        ).queue();
    }

    public static void guildMemberJoined(MessageChannel target, User joinedUser) {
        target.sendMessageEmbeds(new EmbedBuilder()
                .setColor(Color.getHSBColor((float) new Random().nextInt(255), (float) new Random().nextInt(255), (float) new Random().nextInt(255)))
                .setAuthor(jda.getSelfUser().getName(), jda.getInviteUrl(Permission.ADMINISTRATOR), jda.getSelfUser().getAvatarUrl())
                .setTitle(Main.getConfig().getJoinTitle(), jda.getInviteUrl(Permission.ADMINISTRATOR).replace("{user}", joinedUser.getName()))
                .setDescription(Main.getConfig().getJoinDescription().replace("{user}", joinedUser.getName()))
                .build()
        ).queue();
    }

    public static void guildMemberLeft(MessageChannel target, User leftUser) {
        target.sendMessageEmbeds(new EmbedBuilder()
                .setColor(Color.getHSBColor((float) new Random().nextInt(255), (float) new Random().nextInt(255), (float) new Random().nextInt(255)))
                .setAuthor(jda.getSelfUser().getName(), jda.getInviteUrl(Permission.ADMINISTRATOR), jda.getSelfUser().getAvatarUrl())
                .setTitle(Main.getConfig().getLeaveTitle(), jda.getInviteUrl(Permission.ADMINISTRATOR).replace("{user}", leftUser.getName()))
                .setDescription(Main.getConfig().getLeaveDescription().replace("{user}", leftUser.getName()))
                .build()
        ).queue();
    }
}

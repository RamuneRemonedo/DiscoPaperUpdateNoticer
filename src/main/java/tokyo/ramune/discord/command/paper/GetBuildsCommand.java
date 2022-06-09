package tokyo.ramune.discord.command.paper;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.Main;
import tokyo.ramune.discord.command.Command;
import tokyo.ramune.discord.command.CommandTag;
import tokyo.ramune.papermc.PaperMCAPIHandler;
import tokyo.ramune.util.Chat;

import java.util.Arrays;

public class GetBuildsCommand implements Command {
    @NotNull
    @Override
    public String getCommandName() {
        return "builds";
    }

    @NotNull
    @Override
    public CommandTag getTag() {
        return CommandTag.PAPER;
    }

    @NotNull
    @Override
    public String getDescription() {
        return "ビルドを確認します";
    }

    @Override
    public void onCommand(@NotNull String[] args, @NotNull MessageReceivedEvent event) {
        if (args.length <= 2) {
            Chat.error(event.getChannel(), "引数エラー", "コマンド例: " + Main.getConfig().getPrefix() + "builds (プロジェクト名) (バージョン)");
            return;
        }
        try {
            String builds = "";
            int i = 1;
            for (String build : PaperMCAPIHandler.getBuilds(args[1], args[2])) {
                builds = builds + "・" + build+ "  ";
                if (i % 7 == 0) {
                    builds = builds + "\n";
                }
                i++;
            }
            Chat.success(event.getChannel(), "現在のビルド一覧", builds);
        } catch (Exception e) {
            Chat.error(event.getChannel(), "ビルドの取得に失敗しました", Arrays.toString(e.getStackTrace()));
        }
    }
}

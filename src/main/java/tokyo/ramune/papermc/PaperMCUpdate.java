package tokyo.ramune.papermc;

import tokyo.ramune.Main;
import tokyo.ramune.util.Chat;

import java.util.Timer;
import java.util.TimerTask;

public class PaperMCUpdate {

    private final String project;
    private String currentVersion = "version";
    private String currentBuild = "build";

    public PaperMCUpdate(String project) {
        this.project = project;
        update(false);
        startAutoCheck();
    }

    public String getProject() {
        return project;
    }

    public String getLatestVersion() {
        update(false);
        return currentVersion;
    }

    public String getLatestBuild() {
        update(false);
        return currentBuild;
    }

    private void update(boolean announce) {
        checkVersion(announce);
        checkBuild(announce);
    }

    private void checkVersion(boolean announce) {
        try {
            String[] versions = PaperMCAPIHandler.getVersions(project);
            String version = versions[versions.length - 1];

            if (version.equals("")) {
                return;
            }
            if (currentVersion.equals(version)) {
                return;
            }
            currentVersion = version;
            if (!announce) {
                return;
            }
            checkBuild(false);
            BuildInfo buildInfo = PaperMCAPIHandler.getBuildInfo(project, currentVersion, currentBuild);
            Chat.announceUpdate(Main.getJDA().getTextChannelById(Main.getConfig().getAnnounceChannelID()), "新しいアップデートです!!", "プロジェクト名: " + project + "\nバージョン: " + currentVersion + "\nビルド: " + currentBuild + "\nサマリー: " + buildInfo.summary());
        } catch (Exception ignored) {
        }
    }

    private void checkBuild(boolean announce) {
        try {
            String[] builds = PaperMCAPIHandler.getBuilds(project, currentVersion);
            String build = builds[builds.length - 1];

            if (build.equals("")) {
                return;
            }
            if (currentBuild.equals(build)) {
                return;
            }
            currentBuild = build;
            if (!announce) {
                return;
            }
            BuildInfo buildInfo = PaperMCAPIHandler.getBuildInfo(project, currentVersion, build);
            Chat.announceUpdate(Main.getJDA().getTextChannelById(Main.getConfig().getAnnounceChannelID()), "新しいアップデートです!!", "プロジェクト名: " + project + "\nバージョン: " + currentVersion + "\nビルド: " +currentBuild + "\nサマリー: " + buildInfo.summary());
        } catch (Exception ignored) {
        }
    }

    private void startAutoCheck() {
        new Timer().schedule(
                new TimerTask() {
            @Override
            public void run() {
                update(true);
            }
        }
        , 30 * 60 * 1000);
    }
}

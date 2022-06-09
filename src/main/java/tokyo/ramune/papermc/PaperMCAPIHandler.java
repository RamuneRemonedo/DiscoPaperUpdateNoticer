package tokyo.ramune.papermc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PaperMCAPIHandler {

    public static String[] getProjects() throws Exception {
        URL url = new URL("https://api.papermc.io/v2/projects");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String xml = "", line = "";
        while(true) {
            if (!((line = reader.readLine()) != null)) break;
            xml += line;
        }
        reader.close();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(xml);
        return jsonNode.findValue("projects").toString()
                .replace("\"", "")
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "")
                .split(",");
    }

    public static String[] getVersions(String project) throws Exception {
        URL url = new URL("https://api.papermc.io/v2/projects/" + project);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
        StringBuilder xml = new StringBuilder();
        String line = "";
        while(true) {
            if (!((line = reader.readLine()) != null)) break;
            xml.append(line);
        }
        reader.close();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(xml.toString());
        return jsonNode.findValue("versions").toString()
                .replace("\"", "")
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "")
                .split(",");
    }

    public static String[] getBuilds(String project, String version) throws Exception {
        URL url = new URL("https://api.papermc.io/v2/projects/" + project + "/versions/" + version);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
        StringBuilder xml = new StringBuilder();
        String line = "";
        while(true) {
            if (!((line = reader.readLine()) != null)) break;
            xml.append(line);
        }
        reader.close();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(xml.toString());
        if (jsonNode.findValue("builds").toString()
                .replace("\"", "")
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "").equals("")) {
            return null;
        }
        return jsonNode.findValue("builds").toString()
                .replace("\"", "")
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "")
                .split(",");
    }

    public static BuildInfo getBuildInfo(String project, String version, String build) throws Exception {
        URL url = new URL("https://api.papermc.io/v2/projects/" + project + "/versions/" + version + "/builds/" + build);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
        StringBuilder xml = new StringBuilder();
        String line = "";
        while(true) {
            if (!((line = reader.readLine()) != null)) break;
            xml.append(line);
        }
        reader.close();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(xml.toString());

        return new BuildInfo(build, jsonNode.findPath("changes").findValuesAsText("summary").toString());
    }
}

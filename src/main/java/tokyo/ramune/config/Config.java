package tokyo.ramune.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import tokyo.ramune.util.Logger;

import java.nio.file.Paths;

public class Config {
    private static ObjectMapper mapper;
    private static String       json;
    private static JsonNode     jsonNode;
    private static ObjectNode   objectNode;

    private static String       token, prefix, announceChannelID;


    public void load() {
        mapper = new ObjectMapper();
        try {
            json                = mapper.readTree(Paths.get("./config.json").toFile()).toString();
            jsonNode            = mapper.readTree(json);
            objectNode          = jsonNode.deepCopy();

            token = objectNode.get("token").asText();
            prefix = objectNode.get("prefix").asText();

            announceChannelID = objectNode.get("announce-channel-ID").asText();
        }catch (Exception e) {
            Logger.warn(e.toString());
            Logger.warn("Couldn't load config.json");
        }
    }

    public void save() {
        try {
            mapper.writeValue(Paths.get("./config.json").toFile(), objectNode);
        }catch (Exception e) {
            Logger.warn("Couldn't save config.json");
        }
    }

    public String getBotToken() {
        Logger.info("The Bot Token is " + token);
        return token;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getAnnounceChannelID() {
        return announceChannelID;
    }
}
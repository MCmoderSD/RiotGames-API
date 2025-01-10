package de.MCmoderSD.riot.objects;

import com.fasterxml.jackson.databind.JsonNode;

public class Account {

    // Attributes
    private final String puuid;
    private final String gameName;
    private final String tagLine;

    public Account(String puuid, String gameName, String tagLine) {
        this.puuid = puuid;
        this.gameName = gameName;
        this.tagLine = tagLine;
    }

    public Account(JsonNode json) {
        this.puuid = json.get("puuid").asText();
        this.tagLine = json.get("tagLine").asText();
        this.gameName = json.get("gameName").asText();
    }

    // Methods
    public String getPUUID() {
        return puuid;
    }

    public String getGameName() {
        return gameName;
    }

    public String getTagLine() {
        return tagLine;
    }
}
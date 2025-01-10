package de.MCmoderSD.riot.objects;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents an account with a PUUID, game name, and tag line.
 */
public class Account {

    // Attributes
    private final String puuid;
    private final String gameName;
    private final String tagLine;

    /**
     * Constructs an Account object with the specified PUUID, game name, and tag line.
     *
     * @param puuid    the PUUID of the account
     * @param gameName the game name of the account
     * @param tagLine  the tag line of the account
     */
    public Account(String puuid, String gameName, String tagLine) {
        this.puuid = puuid;
        this.gameName = gameName;
        this.tagLine = tagLine;
    }

    /**
     * Constructs an Account object from a JSON node.
     *
     * @param json the JSON node containing account information
     */
    public Account(JsonNode json) {
        this.puuid = json.get("puuid").asText();
        this.tagLine = json.get("tagLine").asText();
        this.gameName = json.get("gameName").asText();
    }

    /**
     * Returns the PUUID of the account.
     *
     * @return the PUUID of the account
     */
    public String getPUUID() {
        return puuid;
    }

    /**
     * Returns the game name of the account.
     *
     * @return the game name of the account
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * Returns the tag line of the account.
     *
     * @return the tag line of the account
     */
    public String getTagLine() {
        return tagLine;
    }
}
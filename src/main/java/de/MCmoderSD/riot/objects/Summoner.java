package de.MCmoderSD.riot.objects;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents a summoner in the game.
 */
public class Summoner {

    // Attributes
    private final String summonerId;
    private final String accountId;
    private final String puuid;
    private final int profileIconId;
    private final int summonerLevel;

    /**
     * Constructs a Summoner object with the specified attributes.
     *
     * @param summonerId     the ID of the summoner
     * @param accountId      the account ID of the summoner
     * @param puuid          the PUUID of the summoner
     * @param profileIconId  the profile icon ID of the summoner
     * @param summonerLevel  the level of the summoner
     */
    public Summoner(String summonerId, String accountId, String puuid, int profileIconId, int summonerLevel) {
        this.summonerId = summonerId;
        this.accountId = accountId;
        this.puuid = puuid;
        this.profileIconId = profileIconId;
        this.summonerLevel = summonerLevel;
    }

    /**
     * Constructs a Summoner object from a JSON node.
     *
     * @param json the JSON node containing summoner information
     */
    public Summoner(JsonNode json) {
        this.summonerId = json.get("id").asText();
        this.accountId = json.get("accountId").asText();
        this.puuid = json.get("puuid").asText();
        this.profileIconId = json.get("profileIconId").asInt();
        this.summonerLevel = json.get("summonerLevel").asInt();
    }

    /**
     * Returns the ID of the summoner.
     *
     * @return the ID of the summoner
     */
    public String getSummonerId() {
        return summonerId;
    }

    /**
     * Returns the account ID of the summoner.
     *
     * @return the account ID of the summoner
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Returns the PUUID of the summoner.
     *
     * @return the PUUID of the summoner
     */
    public String getPuuid() {
        return puuid;
    }

    /**
     * Returns the profile icon ID of the summoner.
     *
     * @return the profile icon ID of the summoner
     */
    public int getProfileIconId() {
        return profileIconId;
    }

    /**
     * Returns the level of the summoner.
     *
     * @return the level of the summoner
     */
    public int getSummonerLevel() {
        return summonerLevel;
    }
}
package de.MCmoderSD.riot.objects;

import com.fasterxml.jackson.databind.JsonNode;

public class Summoner {

    // Attributes
    private final String summonerId;
    private final String accountId;
    private final String puuid;
    private final int profileIconId;
    private final int summonerLevel;

    public Summoner(String id, String accountId, String puuid, int profileIconId, int summonerLevel) {
        this.summonerId = id;
        this.accountId = accountId;
        this.puuid = puuid;
        this.profileIconId = profileIconId;
        this.summonerLevel = summonerLevel;
    }

    public Summoner(JsonNode json) {
        this.summonerId = json.get("id").asText();
        this.accountId = json.get("accountId").asText();
        this.puuid = json.get("puuid").asText();
        this.profileIconId = json.get("profileIconId").asInt();
        this.summonerLevel = json.get("summonerLevel").asInt();
    }

    // Methods
    public String getSummonerId() {
        return summonerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getPuuid() {
        return puuid;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }
}
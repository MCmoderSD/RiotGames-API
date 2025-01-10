package de.MCmoderSD.riot.objects;

import com.fasterxml.jackson.databind.JsonNode;

@SuppressWarnings("unused")
public class Entry {

    // Attributes
    private final String leagueId;
    private final String tier;
    private final String rank;
    private final String summonerId;
    private final int leaguePoints;
    private final int wins;
    private final int losses;
    private final boolean veteran;
    private final boolean inactive;
    private final boolean freshBlood;
    private final boolean hotStreak;

    public Entry(String leagueId, String tier, String rank, String summonerId, int leaguePoints, int wins, int losses, boolean veteran, boolean inactive, boolean freshBlood, boolean hotStreak) {
        this.leagueId = leagueId;
        this.tier = tier;
        this.rank = rank;
        this.summonerId = summonerId;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
        this.veteran = veteran;
        this.inactive = inactive;
        this.freshBlood = freshBlood;
        this.hotStreak = hotStreak;
    }

    public Entry(JsonNode json) {
        this.leagueId = json.get("leagueId").asText();
        this.tier = json.get("tier").asText();
        this.rank = json.get("rank").asText();
        this.summonerId = json.get("summonerId").asText();
        this.leaguePoints = json.get("leaguePoints").asInt();
        this.wins = json.get("wins").asInt();
        this.losses = json.get("losses").asInt();
        this.veteran = json.get("veteran").asBoolean();
        this.inactive = json.get("inactive").asBoolean();
        this.freshBlood = json.get("freshBlood").asBoolean();
        this.hotStreak = json.get("hotStreak").asBoolean();
    }

    // Methods
    public String getLeagueId() {
        return leagueId;
    }

    public String getTier() {
        return tier;
    }

    public String getRank() {
        return rank;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public boolean isVeteran() {
        return veteran;
    }

    public boolean isInactive() {
        return inactive;
    }

    public boolean isFreshBlood() {
        return freshBlood;
    }

    public boolean isHotStreak() {
        return hotStreak;
    }
}
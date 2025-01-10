package de.MCmoderSD.riot.objects;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents an entry in the league.
 */
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

    /**
     * Constructs an Entry object with the specified attributes.
     *
     * @param leagueId     the ID of the league
     * @param tier         the tier of the league
     * @param rank         the rank within the tier
     * @param summonerId   the ID of the summoner
     * @param leaguePoints the league points
     * @param wins         the number of wins
     * @param losses       the number of losses
     * @param veteran      whether the summoner is a veteran
     * @param inactive     whether the summoner is inactive
     * @param freshBlood   whether the summoner is fresh blood
     * @param hotStreak    whether the summoner is on a hot streak
     */
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

    /**
     * Constructs an Entry object from a JSON node.
     *
     * @param json the JSON node containing entry information
     */
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

    /**
     * Returns the ID of the league.
     *
     * @return the ID of the league
     */
    public String getLeagueId() {
        return leagueId;
    }

    /**
     * Returns the tier of the league.
     *
     * @return the tier of the league
     */
    public String getTier() {
        return tier;
    }

    /**
     * Returns the rank within the tier.
     *
     * @return the rank within the tier
     */
    public String getRank() {
        return rank;
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
     * Returns the league points.
     *
     * @return the league points
     */
    public int getLeaguePoints() {
        return leaguePoints;
    }

    /**
     * Returns the number of wins.
     *
     * @return the number of wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Returns the number of losses.
     *
     * @return the number of losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Returns whether the summoner is a veteran.
     *
     * @return true if the summoner is a veteran, false otherwise
     */
    public boolean isVeteran() {
        return veteran;
    }

    /**
     * Returns whether the summoner is inactive.
     *
     * @return true if the summoner is inactive, false otherwise
     */
    public boolean isInactive() {
        return inactive;
    }

    /**
     * Returns whether the summoner is fresh blood.
     *
     * @return true if the summoner is fresh blood, false otherwise
     */
    public boolean isFreshBlood() {
        return freshBlood;
    }

    /**
     * Returns whether the summoner is on a hot streak.
     *
     * @return true if the summoner is on a hot streak, false otherwise
     */
    public boolean isHotStreak() {
        return hotStreak;
    }
}
package de.MCmoderSD.riot.core;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.InvalidParameterException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.MCmoderSD.riot.database.MySQL;
import de.MCmoderSD.riot.enums.Cluster;
import de.MCmoderSD.riot.enums.Region;
import de.MCmoderSD.riot.enums.Tier;
import de.MCmoderSD.riot.objects.Account;
import de.MCmoderSD.riot.objects.Entry;
import de.MCmoderSD.riot.objects.Summoner;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class RiotAPI {

    // Constants
    private static final String GET_ACCOUNT = "/riot/account/v1/accounts/by-riot-id/";
    private static final String GET_SUMMONER = "/lol/summoner/v4/summoners/by-puuid/";
    private static final String GET_ENTRIES = "/lol/league/v4/entries/by-summoner/";

    // Associations
    private final MySQL mySQL;

    // Attributes
    private final String apiKey;
    private final Cluster cluster;
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public RiotAPI(String apiKey, Cluster cluster, JsonNode databaseConfig) {

        // Init Associations
        mySQL = new MySQL(databaseConfig);

        // Init Attributes
        this.apiKey = apiKey;
        this.cluster = cluster;

        // Init Objects
        httpClient = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
    }

    public RiotAPI(String apiKey, Cluster cluster, String host, int port, String database, String username, String password) {

        // Init Associations
        mySQL = new MySQL(host, port, database, username, password);

        // Init Attributes
        this.apiKey = apiKey;
        this.cluster = cluster;

        // Init Objects
        httpClient = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
    }

    public static boolean checkParameters(String name, String tag, Region region) {
        if (name == null || tag == null) throw new InvalidParameterException("Name and Tag must not be null");
        if (name.isEmpty() || tag.isEmpty() || name.isBlank() || tag.isBlank()) throw new InvalidParameterException("Name and Tag must not be empty");
        if (name.length() > 16 || tag.length() > 5) throw new InvalidParameterException("Name must not be longer than 16 characters and Tag must not be longer than 5 characters");
        if (name.length() < 3 || tag.length() < 3) throw new InvalidParameterException("Name must not be shorter than 3 characters and Tag must not be shorter than 3 characters");
        if (region == null) throw new InvalidParameterException("Region must not be null");
        return true;
    }

    public Summoner getSummoner(String name, String tag, Region region) {
        try {

            // Check Parameters
            checkParameters(name, tag, region);

            // Try to get the summoner from the database
            Summoner summoner = mySQL.getSummoner(name, tag);

            // Check if the summoner is null
            if (summoner != null) return summoner;

            // Fetch the account and summoner
            return getSummoner(getAccount(name, tag), region);

        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Integer getProfileIconId(String name, String tag, Region region) {
        try {

            // Check Parameters
            checkParameters(name, tag, region);

            // Try to get the account from the database
            Account account = mySQL.getAccount(name, tag);
            if (account == null) account = getAccount(name, tag);

            // Check if the account is null
            if (account == null) return null;
            else return getSummoner(account, region).getProfileIconId();

        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Integer getSummonerLevel(String name, String tag, Region region) {
        try {

            // Check Parameters
            checkParameters(name, tag, region);

            // Try to get the account from the database
            Account account = mySQL.getAccount(name, tag);
            if (account == null) account = getAccount(name, tag);

            // Check if the account is null
            if (account == null) return null;
            else return getSummoner(account, region).getSummonerLevel();

        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Entry getEntry(String name, String tag, Region region) {
        try {

            // Fetch the entry
            return getEntry(getSummoner(name, tag, region), region);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Tier getTier(String name, String tag, Region region) {

        // Fetch the entry
        Entry entry = getEntry(name, tag, region);

        // Check if the entry is null
        if (entry == null) return null;
        else return Tier.getTier(entry.getTier());
    }

    public String getRank(String name, String tag, Region region) {

        // Fetch the entry
        Entry entry = getEntry(name, tag, region);

        // Check if the entry is null
        if (entry == null) return null;
        else return getTier(name, tag, region).getName() + " " + entry.getRank();
    }

    public int getLeaguePoints(String name, String tag, Region region) {

        // Fetch the entry
        Entry entry = getEntry(name, tag, region);

        // Check if the entry is null
        if (entry == null) return 0;
        else return entry.getLeaguePoints();
    }

    public int getWins(String name, String tag, Region region) {

        // Fetch the entry
        Entry entry = getEntry(name, tag, region);

        // Check if the entry is null
        if (entry == null) return 0;
        else return entry.getWins();
    }

    public int getLosses(String name, String tag, Region region) {

        // Fetch the entry
        Entry entry = getEntry(name, tag, region);

        // Check if the entry is null
        if (entry == null) return 0;
        else return entry.getLosses();
    }

    public boolean isVeteran(String name, String tag, Region region) {

        // Fetch the entry
        Entry entry = getEntry(name, tag, region);

        // Check if the entry is null
        if (entry == null) return false;
        else return entry.isVeteran();
    }

    public boolean isInactive(String name, String tag, Region region) {

        // Fetch the entry
        Entry entry = getEntry(name, tag, region);

        // Check if the entry is null
        if (entry == null) return false;
        else return entry.isInactive();
    }

    public boolean isFreshBlood(String name, String tag, Region region) {

        // Fetch the entry
        Entry entry = getEntry(name, tag, region);

        // Check if the entry is null
        if (entry == null) return false;
        else return entry.isFreshBlood();
    }

    public boolean isHotStreak(String name, String tag, Region region) {

        // Fetch the entry
        Entry entry = getEntry(name, tag, region);

        // Check if the entry is null
        if (entry == null) return false;
        else return entry.isHotStreak();
    }

    public Account getAccount(String gameName, String tagLine) throws IOException, InterruptedException {

        // Check Parameters
        if (gameName == null || tagLine == null)
            throw new InvalidParameterException("GameName and TagLine must not be null");
        if (gameName.isEmpty() || tagLine.isEmpty() || gameName.isBlank() || tagLine.isBlank())
            throw new InvalidParameterException("GameName and TagLine must not be empty");
        if (gameName.length() > 16 || tagLine.length() > 5)
            throw new InvalidParameterException("GameName must not be longer than 16 characters and TagLine must not be longer than 5 characters");
        if (gameName.length() < 3 || tagLine.length() < 3)
            throw new InvalidParameterException("GameName must not be shorter than 3 characters and TagLine must not be shorter than 3 characters");

        // Construct the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(cluster.getUrl() + GET_ACCOUNT + gameName + "/" + tagLine + "?api_key=" + apiKey))
                .GET()
                .build();

        // Send the request
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) throw new RuntimeException("Failed to get PUUID: " + response.body());

        // Parse and check JSON
        Account account = new Account(mapper.readTree(response.body()));
        mySQL.addAccount(account);
        return account;
    }

    public Summoner getSummoner(Account account, Region region) throws IOException, InterruptedException {

        // Check Parameters
        if (account == null) throw new InvalidParameterException("Account must not be null");
        if (region == null) throw new InvalidParameterException("Region must not be null");

        // Construct the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(region.getUrl() + GET_SUMMONER + account.getPUUID() + "?api_key=" + apiKey))
                .GET()
                .build();

        // Send the request
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) throw new RuntimeException("Failed to get Summoner ID: " + response.body());

        // Parse and check JSON
        Summoner summoner = new Summoner(mapper.readTree(response.body()));
        mySQL.addSummoner(summoner);
        return summoner;
    }

    public Entry getEntry(Summoner summoner, Region region) throws IOException, InterruptedException {

        // Check Parameters
        if (summoner == null) throw new InvalidParameterException("Summoner must not be null");
        if (region == null) throw new InvalidParameterException("Region must not be null");

        // Construct the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(region.getUrl() + GET_ENTRIES + summoner.getSummonerId() + "?api_key=" + apiKey))
                .GET()
                .build();

        // Send the request
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) throw new RuntimeException("Failed to get Tier Info: " + response.body());

        // Parse and check JSON
        JsonNode json = mapper.readTree(response.body());
        if (json.isEmpty()) return null;
        return new Entry(json.get(0));
    }
}
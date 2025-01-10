package de.MCmoderSD.riot.database;

import com.fasterxml.jackson.databind.JsonNode;
import de.MCmoderSD.mysql.Driver;
import de.MCmoderSD.riot.objects.Account;
import de.MCmoderSD.riot.objects.Summoner;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class handles the MySQL database operations for the Riot API.
 */
@SuppressWarnings("unused")
public class MySQL extends Driver {

    /**
     * Constructs a MySQL object with the given JSON configuration.
     *
     * @param jsonNode the JSON configuration
     */
    public MySQL(JsonNode jsonNode) {
        super(jsonNode);

        // Init tables
        initTables();
    }

    /**
     * Constructs a MySQL object with the given database connection parameters.
     *
     * @param host     the database host
     * @param port     the database port
     * @param database the database name
     * @param username the database username
     * @param password the database password
     */
    public MySQL(String host, int port, String database, String username, String password) {
        super(host, port, database, username, password);

        // Init tables
        initTables();
    }

    /**
     * Initializes the database tables if they do not already exist.
     */
    private void initTables() {
        try {
            if (!isConnected()) connect();

            // Condition for creating tables
            String condition = "CREATE TABLE IF NOT EXISTS ";

            // SQL statement for creating the Accounts table
            connection.prepareStatement(condition +
                    """
                            Accounts (
                            puuid VARCHAR(78) PRIMARY KEY,
                            gameName VARCHAR(16) NOT NULL,
                            TagLine VARCHAR(5) NOT NULL
                            )
                            """
            ).execute();

            // SQL statement for creating the Summoners table
            connection.prepareStatement(condition +
                    """
                            Summoners (
                            summonerId VARCHAR(63) PRIMARY KEY,
                            accountId VARCHAR(56) NOT NULL,
                            puuid VARCHAR(78) NOT NULL,
                            profileIconId INT NOT NULL,
                            summonerLevel INT NOT NULL,
                            FOREIGN KEY (puuid) REFERENCES Accounts(puuid)
                            )
                            """
            ).execute();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Adds an account to the database.
     *
     * @param account the account to add
     */
    public void addAccount(Account account) {
        addAccount(account.getPUUID(), account.getGameName(), account.getTagLine());
    }

    /**
     * Adds an account to the database with the given parameters.
     *
     * @param puuid    the PUUID of the account
     * @param gameName the game name of the account
     * @param tagLine  the tag line of the account
     */
    public void addAccount(String puuid, String gameName, String tagLine) {
        try {
            if (!isConnected()) connect();

            // Check if the account exists
            if (getAccount(gameName, tagLine) != null) return;

            // SQL statement for inserting a new account
            String query = "INSERT INTO Accounts (puuid, gameName, tagLine) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, puuid);
            preparedStatement.setString(2, gameName);
            preparedStatement.setString(3, tagLine);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Adds a summoner to the database.
     *
     * @param summoner the summoner to add
     */
    public void addSummoner(Summoner summoner) {
        addSummoner(summoner.getSummonerId(), summoner.getAccountId(), summoner.getPuuid(), summoner.getProfileIconId(), summoner.getSummonerLevel());
    }

    /**
     * Adds a summoner to the database with the given parameters.
     *
     * @param summonerId    the summoner ID
     * @param accountId     the account ID
     * @param puuid         the PUUID of the summoner
     * @param profileIconId the profile icon ID of the summoner
     * @param summonerLevel the summoner level
     */
    public void addSummoner(String summonerId, String accountId, String puuid, int profileIconId, int summonerLevel) {
        try {
            if (!isConnected()) connect();

            // Check if the account exists
            if (getSummoner(puuid) != null) return;

            // SQL statement for inserting a new summoner
            String query = "INSERT INTO Summoners (summonerId, accountId, puuid, profileIconId, summonerLevel) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, summonerId);
            preparedStatement.setString(2, accountId);
            preparedStatement.setString(3, puuid);
            preparedStatement.setInt(4, profileIconId);
            preparedStatement.setInt(5, summonerLevel);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Retrieves a summoner from the database by PUUID.
     *
     * @param puuid the PUUID of the summoner
     * @return the Summoner object, or null if not found
     */
    public Summoner getSummoner(String puuid) {
        try {
            if (!isConnected()) connect();

            String query = "SELECT * FROM Summoners WHERE puuid = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, puuid);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Summoner(
                        resultSet.getString("summonerId"),
                        resultSet.getString("accountId"),
                        resultSet.getString("puuid"),
                        resultSet.getInt("profileIconId"),
                        resultSet.getInt("summonerLevel")
                );
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    /**
     * Retrieves a summoner from the database by game name and tag line.
     *
     * @param gameName the game name of the summoner
     * @param tagLine  the tag line of the summoner
     * @return the Summoner object, or null if not found
     */
    public Summoner getSummoner(String gameName, String tagLine) {
        try {
            if (!isConnected()) connect();

            String query = "SELECT * FROM Accounts WHERE gameName = ? AND tagLine = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, gameName);
            preparedStatement.setString(2, tagLine);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) return getSummoner(resultSet.getString("puuid"));

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    /**
     * Retrieves an account from the database by game name and tag line.
     *
     * @param gameName the game name of the account
     * @param tagLine  the tag line of the account
     * @return the Account object, or null if not found
     */
    public Account getAccount(String gameName, String tagLine) {
        try {
            if (!isConnected()) connect();

            String query = "SELECT * FROM Accounts WHERE gameName = ? AND tagLine = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, gameName);
            preparedStatement.setString(2, tagLine);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Account(
                        resultSet.getString("puuid"),
                        resultSet.getString("gameName"),
                        resultSet.getString("tagLine")
                );
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
}
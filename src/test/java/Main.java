import de.MCmoderSD.riot.core.RiotAPI;
import de.MCmoderSD.riot.enums.Cluster;
import de.MCmoderSD.riot.enums.Region;
import de.MCmoderSD.sql.Driver;

public class Main {

    public static void main(String[] args) {

        // API
        RiotAPI api = new RiotAPI(
                "API_KEY",                  // API Key
                Cluster.EUROPE,             // Cluster
                Driver.DatabaseType.MYSQL,  // Database Type
                "localhost",                // Domain
                3306,                       // Port
                "RiotGames",                // Database
                "YourUser",                 // User
                "YourPassword");            // Password

        // Example
        String name = "NattyNatt";      // Summoner Name
        String tag = "2005";            // Tag
        Region region = Region.EUW1;    // Region

        // Output
        System.out.println("Profile Icon ID: " +    api.getProfileIconId(name, tag, region));
        System.out.println("Summoner Level: " +     api.getSummonerLevel(name, tag, region));
        System.out.println("Rank: " +               api.getRank(name, tag, region));
        System.out.println("League Points: " +      api.getLeaguePoints(name, tag, region));
        System.out.println("Wins: " +               api.getWins(name, tag, region));
        System.out.println("Losses: " +             api.getLosses(name, tag, region));
        System.out.println("Veteran: " +            api.isVeteran(name, tag, region));
        System.out.println("Inactive: " +           api.isInactive(name, tag, region));
        System.out.println("Fresh Blood: " +        api.isFreshBlood(name, tag, region));
        System.out.println("Hot Streak: " +         api.isHotStreak(name, tag, region));
    }
}
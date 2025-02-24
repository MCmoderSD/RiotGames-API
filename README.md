# RiotGames-API
[![](https://jitpack.io/v/MCmoderSD/RiotGames-API.svg)](https://jitpack.io/#MCmoderSD/RiotGames-API)

## Description
A simple API to retrieve information about a League of Legends player using the Riot Games API.

## Features
Currently, the following information can be retrieved:
- Profile Icon ID
- Summoner Level
- Rank
- League Points
- Wins
- Losses
- Veteran Status
- Inactive Status
- Fresh Blood Status
- Hot Streak Status

## Configuration
You need a MariaDB database to store the retrieved information. <br>
Create a JSON file with the following structure to configure the database connection:
```json
{
  "host": "localhost",
  "port": 3306,
  "database": "RiotGames",
  "user": "YourUser",
  "password": "YourPassword"
}
```

You also need a Riot Games API key to access the API. <br>
You can get one [here](https://developer.riotgames.com/).

## Usage

### Maven
Make sure you have the JitPack repository added to your `pom.xml` file:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Add the dependency to your `pom.xml` file:
```xml
<dependency>
    <groupId>com.github.MCmoderSD</groupId>
    <artifactId>RiotGames-API</artifactId>
    <version>1.1.5</version>
</dependency>
```

### Usage Example
```java
import de.MCmoderSD.riot.core.RiotAPI;
import de.MCmoderSD.riot.enums.Cluster;
import de.MCmoderSD.riot.enums.Region;
import de.MCmoderSD.sql.Driver;

public class Main {

    public static void main(String[] args) {

        // API
        RiotAPI api = new RiotAPI(
                "API_KEY",                      // API Key
                Cluster.EUROPE,                 // Cluster
                Driver.DatabaseType.MARIADB,    // Database Type
                "localhost",                    // Domain
                3306,                           // Port
                "RiotGames",                    // Database
                "YourUser",                     // User
                "YourPassword");                // Password

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
```
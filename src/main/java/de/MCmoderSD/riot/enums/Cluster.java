package de.MCmoderSD.riot.enums;

@SuppressWarnings("unused")
public enum Cluster {

    // Constants
    AMERICAS("americas"),
    ASIA("asia"),
    EUROPE("europe"),
    SEA("sea");

    // Attributes
    private final String cluster;

    // Constructor
    Cluster(String cluster) {
        this.cluster = cluster;
    }

    // Methods
    public String getCluster() {
        return cluster;
    }

    public String getUrl() {
        return String.format("https://%s.api.riotgames.com", cluster);
    }
}
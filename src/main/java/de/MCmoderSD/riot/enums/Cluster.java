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

    public static Cluster getCluster(String cluster) {
        if (cluster == null || cluster.isBlank()) throw new IllegalArgumentException("Cluster cannot be null or empty.");
        for (Cluster c : values()) if (c.getCluster().equalsIgnoreCase(cluster)) return c;
        return null;
    }

    public static String[] getClusters() {
        String clusters = "AMERICAS, ASIA, EUROPE, SEA";
        return clusters.split(", ");
    }
}
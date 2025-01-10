package de.MCmoderSD.riot.enums;

@SuppressWarnings("unused")
public enum Tier {

    // Constants
    UNRANKED(null, "Unranked"),
    IRON("IRON", "Iron"),
    BRONZE("BRONZE", "Bronze"),
    SILVER("SILVER", "Silver"),
    GOLD("GOLD", "Gold"),
    PLATINUM("PLATINUM", "Platinum"),
    DIAMOND("DIAMOND", "Diamond"),
    MASTER("MASTER", "Master"),
    GRANDMASTER("GRANDMASTER", "Grandmaster"),
    CHALLENGER("CHALLENGER", "Challenger");

    // Attributes
    private final String identifier;
    private final String name;

    // Constructor
    Tier(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    // Methods
    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public static Tier getTier(String identifier) {
        for (Tier tier : values()) if (tier.getIdentifier() != null && tier.getIdentifier().equalsIgnoreCase(identifier)) return tier;
        return UNRANKED;
    }
}
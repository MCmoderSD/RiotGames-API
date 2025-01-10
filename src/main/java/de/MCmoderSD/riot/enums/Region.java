package de.MCmoderSD.riot.enums;

@SuppressWarnings("unused")
public enum Region {

    // Constants
    BR1("br1"),
    EUN1("eun1"),
    EUW1("euw1"),
    JP1("jp1"),
    KR("kr"),
    LA1("la1"),
    LA2("la2"),
    NA1("na1"),
    OC1("oc1"),
    TR1("tr1"),
    RU("ru"),
    PH2("ph2"),
    SG2("sg2"),
    TH2("th2"),
    TW2("tw2"),
    VN2("vn2");

    // Attributes
    private final String region;

    // Constructor
    Region(String region) {
        this.region = region;
    }

    // Methods
    public String getRegion() {
        return region;
    }

    public String getUrl() {
        return String.format("https://%s.api.riotgames.com", region);
    }

    public static Region getCluster(String region) {
        if (region == null || region.isBlank()) throw new IllegalArgumentException("Region cannot be null or empty.");
        for (Region r : values()) if (r.getRegion().equalsIgnoreCase(region)) return r;
        return null;
    }

    public static String[] getRegions() {
        String regions = "BR1, EUN1, EUW1, JP1, KR, LA1, LA2, NA1, OC1, TR1, RU, PH2, SG2, TH2, TW2, VN2";
        return regions.split(", ");
    }
}

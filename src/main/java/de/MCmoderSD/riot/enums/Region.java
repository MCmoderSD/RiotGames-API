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
}

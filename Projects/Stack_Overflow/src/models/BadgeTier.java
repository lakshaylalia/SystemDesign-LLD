package models;

public enum BadgeTier {
    NEWBIE(0L, 99L),
    CONTRIBUTOR(100L, 499L),
    EXPERT(500L, 1999L),
    LEGEND(2000L, Long.MAX_VALUE);

    private final long minReputation;
    private final long maxReputation;

    BadgeTier(long minReputation, long maxReputation) {
        this.minReputation = minReputation;
        this.maxReputation = maxReputation;
    }

    public static BadgeTier fromReputation(long reputation) {
        for (BadgeTier tier : values()) {
            if (reputation >= tier.minReputation && reputation <= tier.maxReputation) {
                return tier;
            }
        }
        return NEWBIE;
    }
}

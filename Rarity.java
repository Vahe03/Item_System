public enum Rarity {
    Common,
    Great,
    Rare,
    Epic,
    Legendary;

    public boolean isEpic() {
        return this == Epic;
    }

    public boolean isLegendary() {
        return this == Legendary;
    }

    public Rarity getNextRarity() {
        return switch (this) {
            case Common -> Great;
            case Great -> Rare;
            case Rare -> Epic;
            case Epic -> Legendary;
            default -> null;
        };
    }
}

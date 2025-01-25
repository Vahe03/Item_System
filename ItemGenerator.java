import java.util.Random;

public class ItemGenerator {

    public static Item generateRandomItem() {
        Random random = new Random();
        int rand = random.nextInt(100) + 1;

        Rarity rarity;
        if (rand <= 50) {
            rarity = Rarity.Common;
        } else if (rand <= 75) {
            rarity = Rarity.Great;
        } else if (rand <= 90) {
            rarity = Rarity.Rare;
        } else if (rand <= 98) {
            rarity = Rarity.Epic;
        } else {
            rarity = Rarity.Legendary;
        }

        String[] names = {"Sword", "Shield", "Golden Bow"};
        String name = names[random.nextInt(names.length)];
        return new Item(name, rarity);
    }
}

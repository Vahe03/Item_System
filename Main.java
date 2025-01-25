public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        UpgradeSystem upgradeSystem = new UpgradeSystem(inventory);

        inventory.addItem(new Item("Sword", Rarity.Common));
        inventory.addItem(new Item("Sword", Rarity.Common));
        inventory.addItem(new Item("Sword", Rarity.Common));
        inventory.displayInventory();
        System.out.println();

        Item sword = inventory.getItemsByRarityAndName(Rarity.Common, "Sword").get(0);
        upgradeSystem.upgradeItem(sword);
        inventory.displayInventory();
        System.out.println();

        System.out.println("Generating random items:");
        for (int i = 0; i < 3; i++) {
            Item randomItem = ItemGenerator.generateRandomItem();
            inventory.addItem(randomItem);
            System.out.println("Generated: " + randomItem.getName() + " " + randomItem.getRarity());
        }
        System.out.println();

        System.out.println("Inventory after random item generation:");
        inventory.displayInventory();
    }
}
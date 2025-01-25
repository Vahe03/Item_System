import java.util.ArrayList;
import java.util.List;

public class UpgradeSystem {
    private Inventory inventory;

    public UpgradeSystem(Inventory inventory) {
        this.inventory = inventory;
    }

    public void upgradeItem(Item item) {
        Rarity itemRarity = item.getRarity();
        String itemName = item.getName();
        List<Item> sameRarityItems = inventory.getItemsByRarityAndName(itemRarity, itemName);

        if (itemRarity.isLegendary()) {
            System.out.println("Legendary items cannot be upgraded!");
            return;
        }

        if (!itemRarity.isEpic()) {
            if (sameRarityItems.size() < 3) {
                System.out.println("You don't have enough items to upgrade " + itemName + "!");
                return;
            }
            List<Item> ingredients = sameRarityItems.subList(1, 3);
            performUpgrade(item, ingredients);
            inventory.addItem(item);
        } else {
            handleEpicUpgrade(item, sameRarityItems);
        }
    }

    private void handleEpicUpgrade(Item item, List<Item> sameRarityItems) {
        if (item.getUpgradeCount() < 2) {
            if (sameRarityItems.size() < 2) {
                System.out.println("You don't have enough Epic items to upgrade!");
                return;
            }
            item.setUpgradeCount(item.getUpgradeCount() + 1);
            inventory.removeItem(sameRarityItems.get(1)); // Consume one Epic item
            System.out.println(item.getName() + " upgraded to Epic " + item.getUpgradeCount());
        } else {
            List<Item> epic2Items = sameRarityItems.stream()
                    .filter(i -> i.getUpgradeCount() == 2)
                    .toList();
            if (epic2Items.size() < 3) {
                System.out.println("You need 3 Epic 2 items to create a Legendary item!");
                return;
            }
            performLegendaryUpgrade(item, epic2Items.subList(1, 3));
            inventory.removeItem(item);
            inventory.addItem(item);
        }
    }

    private void performUpgrade(Item targetItem, List<Item> ingredients) {
        Rarity nextRarity = targetItem.getRarity().getNextRarity();
        targetItem.setRarity(nextRarity);
        for (Item ingredient : ingredients) {
            inventory.removeItem(ingredient);
        }
        System.out.println(targetItem.getName() + " upgraded to " + nextRarity);
    }

    private void performLegendaryUpgrade(Item targetItem, List<Item> ingredients) {
        targetItem.setRarity(Rarity.Legendary);
        for (Item ingredient : ingredients) {
            inventory.removeItem(ingredient);
        }

        System.out.println(targetItem.getName() + " upgraded to Legendary!");
    }
}

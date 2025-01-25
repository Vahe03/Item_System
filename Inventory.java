import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {

    private Map<Rarity, List<Item>> items;

    public Inventory() {
        items = new HashMap<>();
        for (Rarity rarity : Rarity.values()) {
            items.put(rarity, new ArrayList<>());
        }
    }

    public void addItem(Item item) {
        items.get(item.getRarity()).add(item);
    }

    public void removeItem(Item item) {
        items.get(item.getRarity()).remove(item);
    }

    public List<Item> getItemsByRarityAndName(Rarity rarity, String name) {
        List<Item> result = new ArrayList<>();
        for (Item item : items.get(rarity)) {
            if (item.getName().equals(name)) {
                result.add(item);
            }
        }
        return result;
    }

    public void displayInventory() {
        for (Rarity rarity : Rarity.values()) {
            System.out.println(rarity + ":");
            for (Item item : items.get(rarity)) {
                System.out.println(item.getName() + ", Upgrade Count: " + item.getUpgradeCount());
            }
        }
    }

}

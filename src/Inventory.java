import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void add(Item item){
        items.add(item);
    }

    @Override
    public String toString() {
        String resultString = "";
        for (int i = 0; i < items.size() - 1; i++) {
            resultString += items.get(i).getDescription() + ", ";
        }
        resultString += items.get(items.size()-1).getDescription() + ".";
        return resultString;
    }

    public int getSize() {
        return this.items.size();
    }

    public boolean isEmpty() {
        return  this.items.isEmpty();
    }

    public boolean hasItem(String itemName) {
        for (Item item: this.items) {
            if(item.getName().equals(itemName))
                return true;
        }
        return false;
    }

    public Item getItem(String itemName) {
        for (Item item: this.items) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void remove(Item item) {
        boolean isRemoved = false;
        for (int i = 0; i < items.size() && !isRemoved; i++) {
            if (items.get(i).equals(item)) {
                if (items.get(i).getMoveable() == Moveable.MOBILE) {
                    this.items.remove(i);
                    isRemoved = true;
                }
            }
        }
    }
}

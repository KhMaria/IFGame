import java.util.Objects;

public class Item {

    private String name;
    private String description;
    private Moveable moveable;

    public Item(String name, String description, Moveable moveable) {
        this.name = name;
        this.description = description;
        this.moveable = moveable;
    }

    public String getDescription() {
        return this.description;
    }

    public Object getName() {
        return name;
    }

    public Moveable getMoveable() {
        return this.moveable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(description, item.description) &&
                moveable == item.moveable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, moveable);
    }
}

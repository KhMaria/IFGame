import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Location {
    private String name;
    private String description;
    private Inventory inventory;
    private Map<Direction,Location> directions;

    public Location(String name, String description, Inventory inventory) {
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.directions = new HashMap<>();

        this.directions.put(Direction.UP, null);
        this.directions.put(Direction.DOWN, null);
        this.directions.put(Direction.EAST, null);
        this.directions.put(Direction.NORTH, null);
        this.directions.put(Direction.SOUTH, null);
        this.directions.put(Direction.WEST, null);
    }

    public void setDirection(Direction direction, Location location){
        this.directions.remove(direction);
        this.directions.put(direction,location);
    }

    @Override
    public String toString() {

        String directionsString = "";
        Set<Direction> setKey = this.directions.keySet();
        for (Direction direction : setKey) {
            switch (direction) {
                case UP:
                    if(directions.get(direction) != null)
                        directionsString += "Сверху " + directions.get(direction).getName() + ". ";
                    break;
                case DOWN:
                    if(directions.get(direction) != null)
                        directionsString += "Снизу " + directions.get(direction).getName() + ". ";
                    break;
                case EAST:
                    if(directions.get(direction) != null)
                        directionsString += "На востоке " + directions.get(direction).getName() + ". ";
                    break;
                case WEST:
                    if(directions.get(direction) != null)
                        directionsString += "На западе " + directions.get(direction).getName() + ". ";
                    break;
                case NORTH:
                    if(directions.get(direction) != null)
                        directionsString += "На севере " + directions.get(direction).getName() + ". ";
                    break;
                case SOUTH:
                    if(directions.get(direction) != null)
                        directionsString += "На юге " + directions.get(direction).getName() + ". ";
                    break;
            }
        }

        String inventoryString = "";
        if(this.inventory.getSize()!=0) {
            inventoryString += "Здесь находятся: "+this.inventory.toString();
        }

        return this.description + directionsString + inventoryString;

    }

    private String getName() {
        return this.name;
    }

    public boolean hasItem(String itemName) {
        if(this.inventory.hasItem(itemName)){
            return true;
        }
        else{
            return false;
        }
    }

    public Item getItem(String itemName) {
        return this.inventory.getItem(itemName);
    }

    public void pickUp(Item item) {
        this.inventory.remove(item);
    }

    public Location go(String direction) {
        Direction d = null;
        switch(direction){
            case "наверх":
                d = Direction.UP;
                break;
            case "вниз":
                d = Direction.DOWN;
                break;
            case "север":
                d = Direction.NORTH;
                break;
            case "юг":
                d = Direction.SOUTH;
                break;
            case "восток":
                d = Direction.EAST;
                break;
            case "запад":
                d = Direction.WEST;
                break;
        }
        if(d !=null){
            return this.directions.get(d);
        }
        else
        {
            System.out.println("Такого направления не существует");
            return null;
        }

    }
}

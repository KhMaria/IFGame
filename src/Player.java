public class Player {
    private Location location;
    private Inventory inventory;
    Combinations combinations;

    public Player(Location location) {
        this.location = location;
        this.inventory = new Inventory();
    }

    public void lookAround() {
        System.out.println(this.location.toString());
    }

    public void inventory() {
        if (this.inventory.isEmpty()) {
            System.out.println("У вас еще ничего нет :(");
        } else {
            System.out.println("У вас есть: " + this.inventory.toString());
        }
    }

    public void take(String itemName) {

        Item item = this.location.getItem(itemName);
        if (item != null) {
            if (item.getMoveable() == Moveable.MOBILE) {
                this.inventory.add(item);
                this.location.pickUp(item);
                System.out.println("У вас есть " + itemName);
            } else {
                System.out.println("Вы не можете взять этот предмет.");
            }
        } else {
            System.out.println("Такого предмета нет в этой локации.");
        }
    }

    public void setCombo(Combinations combinations) {
        this.combinations = combinations;
    }

    public void go(String direction) {

        Location nextLocation = this.location.go(direction);
        if (nextLocation != null) {
            this.location = nextLocation;
            System.out.println(this.location.toString());
        } else {
            System.out.println("Вы не можете туда пойти!");
        }
    }

    public boolean use(String object, String subject) {
        boolean isWin = false;
        Item itemObject = this.inventory.getItem(object);
        if (itemObject != null) {
            Item itemSubject = this.inventory.getItem(subject);
            if (itemSubject != null) {
                //оба инвентаря нах-ся у нас
                Combo resultCombo = this.combinations.searchCombo(itemObject, itemSubject);
                if (resultCombo != null) {
                    this.inventory.add(resultCombo.getResult());
                    this.inventory.remove(itemObject);
                    this.inventory.remove(itemSubject);
                    System.out.println(resultCombo.getMessage());
                } else {
                    System.out.println("Вы не можете их использовать.");
                }
            } else {
                //проверяем на локации
                itemSubject = this.location.getItem(subject);
                if (itemSubject != null) {
                    Combo resultCombo = this.combinations.searchCombo(itemObject, itemSubject);
                    if (resultCombo != null) {
                        this.inventory.add(resultCombo.getResult());
                        this.inventory.remove(itemObject);
                        this.location.pickUp(itemSubject);
                        System.out.println(resultCombo.getMessage());
                        //проверка на выигрыш
                        if (itemSubject.getDescription().equals("спящий волшебник") && itemObject.getDescription().equals("ведро с водой")) {
                            isWin = true;
                        }
                    } else {
                        System.out.println("Вы не можете их использовать.");
                    }
                } else {
                    System.out.println("Вы не можете их использовать.");
                }
            }
        } else {
            System.out.println("Вы не можете их использовать");
        }

        return isWin;
    }
}
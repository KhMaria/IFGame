import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Начальная расстановка игры
        Item burner = new Item("горелка", "гигантская горелка", Moveable.STATIONAL);
        Item wizard = new Item("волшебник", "спящий волшебник", Moveable.STATIONAL);
        Item whisky = new Item("бутылка виски", "бутылка с виски", Moveable.MOBILE);
        Item bucket = new Item("ведро", "пустое ведро", Moveable.MOBILE);
        Item frog = new Item("лягушка", "лягушка", Moveable.MOBILE);
        Item chain = new Item("цепь", "цепь", Moveable.MOBILE);
        Item well = new Item("колодец", "колодец", Moveable.STATIONAL);

        Inventory atticInventory = new Inventory();
        atticInventory.add(burner);

        Inventory livingRoomInventory = new Inventory();
        livingRoomInventory.add(wizard);
        livingRoomInventory.add(whisky);
        livingRoomInventory.add(bucket);

        Inventory gardenInventory = new Inventory();
        gardenInventory.add(frog);
        gardenInventory.add(chain);
        gardenInventory.add(well);

        Location attic = new Location("чердак", "Вы на чердаке старого дома. ", atticInventory);
        Location livingRoom = new Location("гостинная", "Вы находитесь в гостинной. ", livingRoomInventory);
        Location garden = new Location("прекрасный сад", "Вы находитесь в прекрасном саду. ", gardenInventory);

        attic.setDirection(Direction.DOWN, livingRoom);
        livingRoom.setDirection(Direction.WEST, garden);
        livingRoom.setDirection(Direction.UP, attic);
        garden.setDirection(Direction.EAST, livingRoom);

        Item bucketAndChain = new Item("ведро", "ведро с цепью", Moveable.MOBILE);
        Item bucketWithChain = new Item("ведро", "ведро на цепи", Moveable.MOBILE);
        Item bucketWithWater = new Item("ведро", "ведро с водой", Moveable.MOBILE);
        Item wakingUpWizard = new Item("волшебник", "проснувшийся волшебник", Moveable.STATIONAL);

        Combinations combinations = new Combinations();
        combinations.addCombo(new Combo(bucket, chain, bucketAndChain, "Теперь цепь находится в ведре."));
        combinations.addCombo(new Combo(bucketAndChain, burner, bucketWithChain, "Теперь цепь надежно приварена к ведру."));
        combinations.addCombo(new Combo(bucketWithChain, well, bucketWithWater, "Держа ведро за цепь, вы опускаете его в колодец и поднимаете полным до краев."));
        combinations.addCombo(new Combo(bucketWithWater, wizard, wakingUpWizard, "Волшебник вскакивает и начинает отряхиваться. Приведя себя в порядок, он благодарит вас за помощь и протягивает вам магический кристалл. Вы выиграли!"));

        Player player = new Player(livingRoom);
        player.setCombo(combinations);
        player.lookAround();

        Scanner scanner = new Scanner(System.in);
        String playerCommand;

        boolean isStop = false;
        while (!isStop) {
            playerCommand = scanner.nextLine();
            String[] splitCommand = playerCommand.split(" ");
            switch (splitCommand[0]) {
                case "осмотреться":
                    player.lookAround();
                    break;
                case "взять":
                    player.take(splitCommand[1]);
                    break;
                case "инвентарь":
                    player.inventory();
                    break;
                case "идти":
                    player.go(splitCommand[1]);
                    break;
                case "использовать":
                    isStop = player.use(splitCommand[1], splitCommand[2]);
                    break;
                case "выйти":
                    isStop = true;
                    break;
                    default:
                        System.out.println("Неизвестная команда");
                        break;
            }
        }
    }






}

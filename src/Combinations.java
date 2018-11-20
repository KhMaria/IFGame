import java.util.ArrayList;
import java.util.List;

public class Combinations {

    private List<Combo> combos;

    public Combinations(List<Combo> combos) {
        this.combos = combos;
    }

    public Combinations() {
        this.combos = new ArrayList<>();
    }

    public Combo searchCombo(Item itemObject, Item itemSubject) {
        for (Combo combo: combos) {
            if(combo.getObject().equals(itemObject) && combo.getSubject().equals(itemSubject)){
                return combo;
            }
        }
        return null;
    }

    public void addCombo(Combo combo){
        combos.add(combo);
    }
}

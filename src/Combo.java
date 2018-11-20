public class Combo {

    private Item object;
    private Item subject;
    private Item result;
    private String message;

    public Combo(Item object, Item subject, Item result, String message) {
        this.object = object;
        this.subject = subject;
        this.result = result;
        this.message = message;
    }


    public Item getObject() {
        return this.object;
    }

    public Item getSubject() {
        return this.subject;
    }

    public Item getResult() {
        return this.result;
    }

    public String getMessage() {
        return this.message;
    }
}

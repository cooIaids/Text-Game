public class Item {


    enum TypeOfItem{
        TABLE,
        BED,
        MORPHINE,
        AMPHETAMINE,
        DOCUMENTS,
        CABINET,

    }

    private final TypeOfItem typeOfItem;

    public Item(TypeOfItem typeOfItem) {
        this.typeOfItem = typeOfItem;
    }

    public TypeOfItem getTypeOfItem() {
        return typeOfItem;
    }

    @Override
    public String toString() {
        return typeOfItem.toString();
    }
}

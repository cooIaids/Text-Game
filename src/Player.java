import java.util.ArrayList;

public class Player {

    private final ArrayList<Item> inventory = new ArrayList<>();
    private int healthPoints = 5;
    private int strengthPoints = 2;



    public int getHealthPoints() {
        return healthPoints;
    }

    public int getStrengthPoints() {
        return strengthPoints;
    }

    public void addItemToInventory(Item i) {
        if(inventory.size() < 4){
                inventory.add(i);
        }else {
            System.out.println("[Full inventory]");
        }


    }
    public Item getItem(int index){
        return inventory.get(index);
    }


    public void useItems(int index){
       if(!(inventory.size() == 0)){
           Item i = inventory.get(index);
           if(i.getTypeOfItem().equals(Item.TypeOfItem.MORPHINE)){
               healthPoints += 5;
               inventory.remove(index);
           } else if (i.getTypeOfItem().equals(Item.TypeOfItem.AMPHETAMINE)) {
               strengthPoints += 3;
               inventory.remove(index);
           }else {
               System.out.println("[Can't use that item.]");
           }
       }else {
           System.out.println("[You have nothing in your inventory.]");
       }

    }

    public String lookIntoInventory(){
        StringBuilder item = new StringBuilder();
        for (Item value : inventory) {
            item.append(value).append(", ");
        }
        return item.toString();
    }




    public String checkYourStats(){
        return "Health: " + getHealthPoints() + "\n"
                + "Attack DMG: " + getStrengthPoints();
    }


}

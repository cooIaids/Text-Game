import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Room {


    private String nameOfRoom;
    private final ArrayList<Item> items = new ArrayList<>();
    private NPC npc;
    private final Random random = new Random();

    private final Scanner sc = new Scanner(System.in);


    public void setNameOfRoom(String nameOfRoom) {
        this.nameOfRoom = nameOfRoom;
    }

    public String getNameOfRoom() {
        return nameOfRoom;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public NPC getNpc() {
        return npc;
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public void removeItem(int index){
        items.remove(index);
    }

    public Item getItem(int index){
        return items.get(index);
    }

    public String getItemsFromList() {
        StringBuilder item = new StringBuilder();
        for (Item value : items) {
            item.append(value).append(", ");
        }
        return item.toString();
    }

    public String interactWithAnNPC() {
        String useItems;
        int useSpecificItem;
        String NPCStatus;
        Player p = new Player();
        int enemyHealth;
        int enemyDamage;
        int yourHealth;
        int yourDamage;
        if(getNpc() != null){
            if (!getNpc().isFriendly()) {
                System.out.println("""
                        [There's someone sitting in the corner of the room. It's another patient!.]\s
                        [They suddenly start acting aggressive and running towards you. They clearly don't want you here.]
                        [You need to defend yourself, you refuse to die in a hellhole like this.]""");
                System.out.println("[Use an item?]" + "\n" + "[yes/no]");
                useItems = sc.next();
                if (useItems.equalsIgnoreCase("yes")) {
                    System.out.println(p.lookIntoInventory());
                    useSpecificItem = sc.nextInt();
                    p.useItems(useSpecificItem);
                }
                enemyHealth = random.nextInt(5) + 5;
                enemyDamage = random.nextInt(3)+1;
                yourHealth = p.getHealthPoints();
                yourDamage = p.getStrengthPoints();
                getNpc().setHealthPoints(enemyHealth);
                getNpc().setStrengthPoints(enemyDamage);
                while(true){
                    System.out.println("[You attack]");
                    enemyHealth -= yourDamage;
                    System.out.println("Patient's health: " + enemyHealth);
                    if((enemyHealth -= yourDamage) < 0){
                        enemyHealth = 0;
                    }
                    System.out.println("Patient's attack DMG: " + enemyDamage);


                    if(enemyHealth == 0){
                        NPCStatus = "[You defeated the enemy!! You may pass through now]";
                        break;
                    }


                    System.out.println("[Patient attacks]");
                    yourHealth -= enemyDamage;
                    System.out.println("Your health: " + yourHealth);
                    if((yourHealth -= enemyDamage) < 0){
                        yourHealth = 0;
                    }
                    System.out.println("Your attack DMG: " + yourDamage);


                    if(yourHealth == 0){
                       NPCStatus = "[YOU DIED]";
                       System.exit(0);
                       break;

                    }

                }

                setNpc(null);


            }else{
                NPCStatus = "[There's someone sitting in the corner of the room. It's another patient! " + "\n" +
                        "[The patient greets you and tells you:" +
                        "'The only way to get out of this hell is through the Art Therapy room, " +
                        "go there and leave this wretched place.']";
            }


        }else{
            NPCStatus = "[There's no one else in this room but you..]";

        }

        return NPCStatus;
    }



}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class WorldCreator {


    private final LinkedHashMap<Room, ArrayList<String>> roomsAndDirections = new LinkedHashMap<>();
    private final ArrayList<String> map = new ArrayList<>();
    private final ArrayList<Item> items = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final Player p = new Player();


    public void readInputFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("mapa.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] divide = line.split("\n");
                map.addAll(Arrays.asList(divide));


            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public String commandsForUser() {
        return """
                north = [Go to the room north of the current room.]
                south = [Go to the room south of the current room.]
                west = [Go to the room west of the current room.]
                east = [Go to the room east of the current room.]
                search = [Search the current room.]
                inventory = [Check your inventory.]
                stats = [Check your health and attack DMG statistics.]
                """;
    }


    public void generatingTheRooms() {
        items.add(new Item(Item.TypeOfItem.BED));
        items.add(new Item(Item.TypeOfItem.AMPHETAMINE));
        items.add(new Item(Item.TypeOfItem.CABINET));
        items.add(new Item(Item.TypeOfItem.TABLE));
        items.add(new Item(Item.TypeOfItem.MORPHINE));
        items.add(new Item(Item.TypeOfItem.DOCUMENTS));
        for (String s : map) {
            ArrayList<String> directions = new ArrayList<>();
            String[] dividedList = s.split(";");
            for (int j = 1; j < 5; j++) {
                directions.add(dividedList[j]);
            }
            Room room = new Room();
            room.setNameOfRoom(dividedList[0]);
            Random r = new Random();
            int itemsInARoom = r.nextInt(2);
            if (itemsInARoom == 1) {
                int randomItem;
                for (int k = 0; k < 4; k++) {
                    randomItem = r.nextInt(6);
                    room.addItem(items.get(randomItem));
                }
            } else {
                room.addItem(null);

            }

            int NPCInARoom = r.nextInt(2);
            if (NPCInARoom == 1) {
                int friendlyOrNot = r.nextInt(2);
                if (friendlyOrNot == 1) {
                    room.setNpc(new NPC(true));
                } else {
                    room.setNpc(new NPC(false));
                }


            } else {
                room.setNpc(null);
            }

            roomsAndDirections.put(room, directions);
        }

    }

       public void traversingThroughRooms() {
           generatingTheRooms();
           String usersChoice;
           Room currentRoom = (Room) roomsAndDirections.keySet().toArray()[0];

           while (!(currentRoom.getNameOfRoom().equals("Art Therapy room"))) {
               System.out.println("[You seem to have found yourself in the " + currentRoom.getNameOfRoom() + "].");
               System.out.print(currentRoom.interactWithAnNPC() + "\n" + ">> ");
               usersChoice = sc.next();

               boolean isANumber = false;
               do {
                   try{
                       switch (usersChoice) {
                           case "north" -> {
                               if (roomsAndDirections.get(currentRoom).get(0).equals("null")) {
                                   System.out.println("Can't go that way");
                               } else {
                                   for (Map.Entry<Room, ArrayList<String>> entry : roomsAndDirections.entrySet()) {
                                       if (entry.getKey().getNameOfRoom().equals(roomsAndDirections.get(currentRoom).get(0))) {
                                           currentRoom = entry.getKey();
                                           break;
                                       }
                                   }
                               }
                           }
                           case "south" -> {
                               if (roomsAndDirections.get(currentRoom).get(1).equals("null")) {
                                   System.out.println("Can't go that way");
                               } else {
                                   for (Map.Entry<Room, ArrayList<String>> entry : roomsAndDirections.entrySet()) {
                                       if (entry.getKey().getNameOfRoom().equals(roomsAndDirections.get(currentRoom).get(1))) {
                                           currentRoom = entry.getKey();
                                           break;
                                       }
                                   }

                               }
                           }
                           case "west" -> {
                               if (roomsAndDirections.get(currentRoom).get(2).equals("null")) {
                                   System.out.println("Can't go that way");
                               } else {
                                   for (Map.Entry<Room, ArrayList<String>> entry : roomsAndDirections.entrySet()) {
                                       if (entry.getKey().getNameOfRoom().equals(roomsAndDirections.get(currentRoom).get(2))) {
                                           currentRoom = entry.getKey();
                                           break;
                                       }
                                   }

                               }
                           }
                           case "east" -> {
                               if (roomsAndDirections.get(currentRoom).get(3).equals("null")) {
                                   System.out.println("Can't go that way");
                               } else {
                                   for (Map.Entry<Room, ArrayList<String>> entry : roomsAndDirections.entrySet()) {
                                       if (entry.getKey().getNameOfRoom().equals(roomsAndDirections.get(currentRoom).get(3))) {
                                           currentRoom = entry.getKey();
                                           break;
                                       }
                                   }
                               }
                           }
                           case "search" -> {
                               String searchUsersChoice;
                               int usersItemChoice = 0;
                               System.out.println(currentRoom.getItemsFromList());
                               if ((currentRoom.getItem(usersItemChoice) == null)){
                                   System.out.println("[There's nothing in this room.]");
                               }else {
                                   System.out.println("[Take item from room?]" + "\n" + "[yes/no]");
                                   searchUsersChoice = sc.next();
                                   if (searchUsersChoice.equalsIgnoreCase("yes")) {

                                       System.out.println("[What item do you wanna take?]");
                                       usersItemChoice = sc.nextInt();
                                       if ((currentRoom.getItem(usersItemChoice).equals(items.get(0)))
                                               || currentRoom.getItem(usersItemChoice).equals(items.get(2)) ||
                                               currentRoom.getItem(usersItemChoice).equals(items.get(3))){
                                           System.out.println("[Can't take that item]");
                                       }else {
                                           p.addItemToInventory(currentRoom.getItem(usersItemChoice));
                                           currentRoom.removeItem(usersItemChoice);


                                       }

                                   }

                               }}
                           case "inventory" ->{
                               String usersInventoryChoice;
                               int itemInInventoryChoice;
                               System.out.println(p.lookIntoInventory());
                               if(p.lookIntoInventory().equals("")){
                                   System.out.println("[Nothing in your inventory]");
                               }else {
                                   System.out.println("[Use an item?]" + "\n" + "[yes/no]");
                                   usersInventoryChoice = sc.next();
                                   if(usersInventoryChoice.equalsIgnoreCase("yes")){
                                       System.out.println("[What item do you wanna use?]");
                                       itemInInventoryChoice = sc.nextInt();
                                       if(p.getItem(itemInInventoryChoice).equals(items.get(5))){
                                           System.out.println("[Can't use that item]");
                                       }else {
                                           p.useItems(itemInInventoryChoice);
                                       }

                                   }
                               }


                           }

                           case "stats" -> System.out.println(p.checkYourStats());
                           case "help" -> System.out.println(commandsForUser());

                       }
                       isANumber = true;
                   }catch (InputMismatchException ime){
                       System.out.println("[Input a number.]");
                       sc.next();
                   }catch (IndexOutOfBoundsException iobe){
                       System.out.println("[Invalid index.]");
                       sc.next();
                   }

               }while(!isANumber);

               if (currentRoom.getNameOfRoom().equals("room4")) {
                   System.out.println("""
                           [That looks like the Art Therapy room. You escape through the window of the room.]
                           [You're outside. It looks so... peaceful. It's been so long since you've seen nature.]
                           [You finally made it out. CONGRATULATIONS.]""");
               }
       }



    }
}

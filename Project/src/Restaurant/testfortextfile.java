//package Restaurant;
//import java.io.*;
//import java.util.ArrayList;
//
//public class testfortextfile {
//
//    BufferedWriter bw = new BufferedWriter(
//            new FileWriter("./textfiles/menuitems.txt", true)
//    );
//    Menu testmenu = new Menu();
//    for (MenuItem item: listofmenuitems){
//        bw.append(item.getItemName()+"\n" +String.valueOf(item.getItemID())+"\n"+String.valueOf(item.getPrice()) +"\n"+item.getDescription()+"\n");
//    }
//    bw.close();
//    BufferedReader br = new BufferedReader(
//            new FileReader("./textfiles/menuitems.txt")
//    );
//
//
//    // for reading items from text file
//    String itemname = null;
//    int itemid = 0;
//    Double itemprice = 0.0;
//    String description = null;
//
//    ArrayList<MenuItem> newmenuitemslist = new ArrayList<MenuItem>();
//
//    int x=0;
//    String s;
//    while ((s = br.readLine()) != null){
//        if (x%4 == 0){
//            itemname = s;
//        }else if (x%4 == 1) {
//            itemid = Integer.parseInt(s);
//        }else if(x%4==2){
//            itemprice = Double.parseDouble(s);
//        }else if (x%4 == 3){
//            description = s;
//            MenuItem newitem = new MenuItem(itemname,itemid,itemprice,description);
//            newmenuitemslist.add(newitem);
//        }
//        x++;
//    }
//
//    System.out.println("This is the list of update new menu items");
//    for (MenuItem item: newmenuitemslist){
//        System.out.println(item.getItemName()+" " +item.getItemID()+" "+item.getPrice() +" "+ item.getDescription());
//    }
//    br.close();
//
//}

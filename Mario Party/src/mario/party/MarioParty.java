//package mario.party;
//
//import java.util.Scanner;
//
///**
// *
// * @author Cole
// */
//public class MarioParty {
//
//    public static void main(String[] args) {
//        Scanner userIn = new Scanner(System.in);
//
//        //TODO: change settings to use JButtons
//        System.out.println("How many players?");
//        System.out.println("[1] [2] [3] [4]");
//        int players = userIn.nextInt();
//        System.out.println("What map?");
//        System.out.println("[BASIC]");
//        String m = userIn.nextLine();
//        Map map = null;
//        try {
//            map = Map.valueOf(m);
//        } catch (Exception e) {
//            System.out.println(e);
//            map = Map.BASIC;
//        }
//        Game g = new Game(players, map);
//
//        g.init();
//
//        while (true) {
//
//            g.periodic();
//
//        }
//    }
//
//}

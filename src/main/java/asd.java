//import java.util.Random;
//import java.util.Scanner;
//
//public class Ruletka {
//    public static void main(String[] args) {
//        byte wile=1;
//        System.out.print("Enter your balance for game: ");
//        Scanner balance1 = new Scanner(System.in);
//        double balance = balance1.nextDouble();
//
//
//        do {
//
//            System.out.println("Your balance is " + balance+'$');
//            Random rand = new Random();
//
//            int rand1 = rand.nextInt(2);
//
//            System.out.print("Make your bet-->");
//            Scanner bet1 = new Scanner(System.in);
//            int bet = bet1.nextInt();
//            if (bet>balance){
//                System.out.println("You have no money for this bet");
//            }
//            else {
//                balance = (balance - bet);
//                int win;
//
//                System.out.println("Type number 0 or 1");
//                Scanner sc1 = new Scanner(System.in);
//                int scanner = sc1.nextInt();
//
//                if (scanner == rand1) {
//                    win = bet + bet;
//                    System.out.println("Congratulation you win: " + win);
//                    balance = (balance + win);
//                    System.out.println("Your balance is " + balance+'$');
//
//                } else {
//                    System.out.println("You are lose =(");
//                    System.out.println("Number was " + rand1);
//                    System.out.println("Your balance is " + balance+'$');
//                }
//
//
//                if (balance > 0) {
//                    System.out.println("0|Play again");
//                    System.out.println("1|Windraw");
//                    Scanner type = new Scanner(System.in);
//                    int type1 = type.nextInt();
//
//                    if (type1 == 0) {  continue; }
//                    else {
//
//                        System.out.println("How many money do you want to windraw?");
//                        System.out.println("0|" + balance * 0.25 +
//                                "\n1|" + balance * 0.50 +
//                                "\n2|" + balance * 0.75 +
//                                "\n3|" + balance +
//                                "\n4|Your amounth");
//                        Scanner wind1 = new Scanner(System.in);
//                        int wind = wind1.nextInt();
//
//                        if (wind == 0){ balance = balance *0.75  ; }
//                        if (wind == 1){ balance = balance *0.50  ; }
//                        if (wind == 2){ balance = balance *0.25  ; }
//                        if (wind == 3){ balance = balance-balance; }
//
//                    }
//
//
//
//
//
//
//                    {   System.out.println("Choose your windraw method");
//
//                        System.out.println("0|Orange\n1|Moldcell");
//                        Scanner op = new Scanner(System.in);
//                        int oprt = op.nextInt();
//                        if (oprt == 0) {
//                            System.out.println("Scrie numarul Orange");
//                            Scanner opor = new Scanner(System.in);
//                            int oprtor = opor.nextInt();
//                            System.out.println("Verificati corectitudinea: " + oprtor);
//                            System.out.println("1-Yes|0-No");
//                            Scanner crt1 = new Scanner(System.in);
//                            int crt = op.nextInt();
//                            if (crt==1){
//                                System.out.println("Windraw was succesful");break;
//                            }
//                            else System.out.println("Type one more type number");
//                            oprtor = opor.nextInt();
//
//
//
//                        }
//                        else System.out.println("Scrie numarul Moldcell");
//                        Scanner opml = new Scanner(System.in);
//                        int oprtml = opml.nextInt();
//                        System.out.println("Verificati corectitudinea: " + oprtml);
//                        System.out.println("1-Yes|0-No");
//                        Scanner crt1 = new Scanner(System.in);
//                        int crt = op.nextInt();
//                        if (crt==1){
//                            System.out.println("Windraw was succesful");break;
//                        }
//                        else System.out.println("Type one more type number");
//                        oprtml = opml.nextInt();
//
//
//                    }
//
//                } else System.out.println("Sorry but you are lose all money for playing in this game =(");
//                break;
//            }
//        }while(wile==1);
//
//
//
//
//
//
//
//
//
//    }
//}
//

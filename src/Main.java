import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        int[][] bus_seats;
        int opt;
        String bus,seat;
        int chair_booking,checkID;
        int count_list_bus=0 ;
        String answer_to_booking,reset_seat;
        String redColorCode = "\u001B[31m";
        String resetColorCode = "\u001B[0m";
        String greenColorCode = "\u001B[32m";
        String resetColorGreen = "\u001B[0m";
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("---------- SETTING UP BUS ---------");
            System.out.print("-> Enter number of Bus : ");
            bus = sc.nextLine();
            if (!Pattern.matches("\\d+",bus)){
                System.out.println(redColorCode+"\n-------------------------------------------"+resetColorCode);
                System.out.println(redColorCode+"Bus can not less than or equal 0 !!"+resetColorCode);
                System.out.println(redColorCode+"Please enter the information again only number !!"+resetColorCode);
                System.out.println(redColorCode+"---------------------------------------------\n"+resetColorCode);
                continue;
            }
            if (Integer.parseInt(bus) <=0){
                System.out.println(redColorCode+"\n-------------------------------------------"+resetColorCode);
                System.out.println(redColorCode+"Bus can not less than or equal 0 !!"+resetColorCode);
                System.out.println(redColorCode+"---------------------------------------------\n"+resetColorCode);
                continue;
            }
            System.out.print("-> Enter number of Seat of Bus : ");
            seat = sc.nextLine();
            if (Integer.parseInt(seat)>=15 && Integer.parseInt(seat)<=45){
                System.out.println();
                break;
            }else {
                System.out.println(redColorCode+"\n-------------------------------------------"+resetColorCode);
                System.out.println(redColorCode+"Seat can not less than 15 and more than 45 !!"+resetColorCode);
                System.out.println(redColorCode+"Please enter the information again only number !!"+resetColorCode);
                System.out.println(redColorCode+"---------------------------------------------\n"+resetColorCode);

            }
        }while (true);


        int numberOfBus = Integer.parseInt(bus);
        int numberOfSeat = Integer.parseInt(seat);

        bus_seats = new int[numberOfBus][numberOfSeat];

        for(int i = 0;i<bus_seats.length;i++){
            for(int j=0;j<bus_seats[i].length;j++){
                bus_seats[i][j] = 1;
            }
        }

        while (true){
            System.out.println(redColorCode+"---------- BUS MANAGEMENT SYSTEM -----------"+resetColorCode);
            System.out.println("1- Check Bus.");
            System.out.println("--------------------------------------------");
            System.out.println("2- Booking Bus.");
            System.out.println("--------------------------------------------");
            System.out.println("3- Cancel Booking.");
            System.out.println("--------------------------------------------");
            System.out.println("4- Reset Bus.");
            System.out.println("--------------------------------------------");
            System.out.println("5- Exit.");
            System.out.println("--------------------------------------------");
            System.out.print(redColorCode+"-> Choose Option (1-5) : "+resetColorCode);
            opt = sc.nextInt();
            System.out.println();
            switch (opt){
                case 1 :
                    int id;
                    int[] seat_available,seat_unavailable;
                    seat_available = new int[numberOfBus];
                    seat_unavailable = new int[numberOfBus];
                    for(int i=0;i<bus_seats.length;i++){
                        for(int j=0;j<bus_seats[i].length;j++){
                            if(bus_seats[i][j]==1){
                                seat_available[i]+=1;
                            }else {
                                seat_unavailable[i]+=1;
                            }
                        }
                    }
                    System.out.println("---------- DISPLAY ALL BUS INFORMATION ----------\n");
                    System.out.println("---------------------------------------------");

                    System.out.println("ID\t\tSeat\t\tAvailable\tUnavailable");
                    System.out.println("---------------------------------------------");

                    for (int i = 0; i < numberOfBus; i++) {
                        System.out.printf(redColorCode+"%-5d\t%-10s\t%-15d\t%-15d\n"+resetColorCode, (i + 1), bus_seats[i].length, seat_available[i], seat_unavailable[i]);
                        System.out.println("---------------------------------------------");
                    }

                    System.out.println("\n---------- DISPLAY BUS INFORMATION ----------");
                    break_time:
                    do {
                        int cnt_available=0;
                        System.out.print("\n=> Enter 0 to Back or Bus ID to see Detail : ");
                        id = sc.nextInt();
                        System.out.println();
                        if(id==0)
                            break break_time;
                        if(id > bus_seats.length) {
                            System.out.println("We only have "+ seat +" !!");
                            break break_time;
                        }

                        for(int i=0;i<numberOfSeat;i++){
                            if (bus_seats[id-1][i]==1){
                                cnt_available+=1;
                            }
                            count_list_bus+=1;
                            String msg = bus_seats[id-1][i] == 0 ? redColorCode+"(-)"+resetColorCode : greenColorCode+"(+)"+resetColorGreen;
                            if(count_list_bus%5==0){
                                System.out.print(msg+" "+(i+1)+"\t\t" );
                                System.out.println();
                            }else {
                                System.out.print(msg+" "+(i+1)+"\t\t");
                            }
                        }

                        System.out.println("\n\n( - ) : Unavailable("+(bus_seats[id-1].length-cnt_available)+")" + "\t\t\t" + "( + ) : Available("+cnt_available+")");

                    }while (id!=0);
                    break;
                case 2 :
                    int cnt_available=0,cnt_bus=0;
                    System.out.print("=> Enter Bus ID : ");
                    checkID = sc.nextInt();
                    System.out.println();
                    if (checkID >numberOfBus) {
                        System.out.println("--------------------------");
                        System.out.println("We have only "+ bus + " buses !!");
                        System.out.println("--------------------------");
                        break;
                    }
                    for(int i=0;i<numberOfSeat;i++){
                        if (bus_seats[checkID-1][i]==1){
                            cnt_available+=1;
                        }
                        cnt_bus+=1;
                        String msg = bus_seats[checkID-1][i] == 0 ? redColorCode+"(-)"+resetColorCode : greenColorCode+"(+)"+resetColorGreen;
                        if(cnt_bus%5==0){
                            System.out.print(msg+" "+(i+1)+"\t\t" );
                            System.out.println();
                        }else {
                            System.out.print(msg+" "+(i+1)+"\t\t");
                        }
                    }

                    System.out.println("\n( - ) : Unavailable("+(bus_seats[checkID-1].length-cnt_available)+")" + "\t\t\t" + "( + ) : Available("+cnt_available+")");

                    System.out.print("\n-> Enter chair number to booking or 0 back to Menu : ");
                    chair_booking = sc.nextInt();
                    System.out.println();
                    if (chair_booking==0)
                        break ;
                    if (bus_seats[checkID-1][chair_booking-1]==0){
                        System.out.println(redColorCode+"----------------------------------"+resetColorCode);
                        System.out.println(redColorCode+"Chair is unavailable right now !!"+resetColorCode);
                        System.out.println(redColorCode+"----------------------------------"+resetColorCode);
                        break ;
                    }
                    if (chair_booking>bus_seats[checkID-1].length){
                        System.out.println(redColorCode+"\n----------------------------------"+resetColorCode);
                        System.out.println(redColorCode+"We have only "+ seat + " chairs !!"+resetColorCode);
                        System.out.println(redColorCode+"----------------------------------"+resetColorCode);
                        break ;
                    }

                    System.out.print(redColorCode+"=> Do you want to chair number "+chair_booking + "? (y/n) : "+resetColorCode);
                    answer_to_booking = sc.next();

                    if(answer_to_booking.equalsIgnoreCase("Y")){
                        bus_seats[checkID-1][chair_booking-1] = 0;
                        System.out.println(redColorCode+"------------------------------------------------"+resetColorCode);
                        System.out.println(redColorCode+"You have booked chair number : "+ chair_booking+" from bus ID "+ checkID +resetColorCode);
                        System.out.println(redColorCode+"------------------------------------------------\n"+resetColorCode);
                        break ;
                    }else {
                        System.out.println(redColorCode+"\n-------------------------------"+resetColorCode);
                        System.out.println(redColorCode+"You do not booking any seats !! "+resetColorCode);
                        System.out.println(redColorCode+"---------------------------------"+resetColorCode);
                        break ;
                    }
                case 3 :
                    int bus_id,count_seat_ava=0,cnt_bus_num=0,cancel_seat;
                    String ans;
                    System.out.print("-> Enter Bus ID : ");
                    bus_id = sc.nextInt();
                    if(bus_id > numberOfBus){
                        System.out.println(redColorCode+"\n----------------------"+resetColorCode);
                        System.out.println(redColorCode+"We have only "+ bus + " bus !!"+resetColorCode);
                        System.out.println(redColorCode+"----------------------\n"+resetColorCode);
                        break;
                    }

                    System.out.println("\n---------- DISPLAY BUS INFORMATION ----------");
                    for(int i=0;i<numberOfSeat;i++){
                        if (bus_seats[bus_id-1][i]==1){
                            count_seat_ava+=1;
                        }
                        cnt_bus_num+=1;
                        String msg = bus_seats[bus_id-1][i] == 0 ? redColorCode+"(-)"+resetColorCode : greenColorCode+"(+)"+resetColorGreen;
                        if(cnt_bus_num%5==0){
                            System.out.print(msg+" "+(i+1)+"\t\t" );
                            System.out.println();
                        }else {
                            System.out.print(msg+" "+(i+1)+"\t\t");
                        }
                    }

                    System.out.println("\n( - ) : Unavailable("+(bus_seats[bus_id-1].length-count_seat_ava)+")" + "\t\t\t" + "( + ) : Available("+count_seat_ava+")\n");

                    System.out.print("Enter seat number to cancel booking : ");
                    cancel_seat = sc.nextInt();
                    if(bus_seats[bus_id-1][cancel_seat-1]==0){
                        System.out.print("=> Do you want to cancel book chair number "+cancel_seat +" ? (y/n) :");
                        ans = sc.next();
                        if (ans.equalsIgnoreCase("Y")){
                            bus_seats[bus_id-1][cancel_seat-1] = 1;
                            System.out.println(redColorCode+"\n-----------------------------------------------------"+resetColorCode);
                            System.out.println(redColorCode+"Seat number "+ cancel_seat + " was cancel booking successfully !!"+resetColorCode);
                            System.out.println(redColorCode+"-------------------------------------------------------\n"+resetColorCode);
                            break;
                        }else {
                            System.out.println(redColorCode+"\n------------------------------------------------"+resetColorCode);
                            System.out.println(redColorCode+"Seat number "+ cancel_seat + " was not cancel booking !!"+resetColorCode);
                            System.out.println(redColorCode+"--------------------------------------------------\n"+redColorCode);
                            break;
                        }
                    }else {
                        System.out.println(redColorCode+"\n----------------------------------------"+resetColorCode);
                        System.out.println(redColorCode+"Seat number "+ cancel_seat + " Already Available !!"+resetColorCode);
                        System.out.println(redColorCode+"-------------------------------------------\n"+resetColorCode);
                    }
                    break;
                case 4 :
                    System.out.print("-> Enter ID of Bus you want to reset : ");
                    id = sc.nextInt();
                    if(id>numberOfBus){
                        System.out.println(redColorCode+"\n--------------------------"+resetColorCode);
                        System.out.println(redColorCode+"We have only "+ bus+" bus !!"+resetColorCode);
                        System.out.println(redColorCode+"----------------------------\n"+resetColorCode);
                        break;
                    }else {
                        System.out.print("Bus ID "+ id+ " was reset with all seats available? (y/n) :");
                        reset_seat = sc.next();
                        if(reset_seat.equalsIgnoreCase("Y")){
                            for (int i = 0;i<numberOfSeat;i++){
                                bus_seats[id-1][i] = 1;
                            }
                            System.out.println(redColorCode+"\n-----------------------------------------"+resetColorCode);
                            System.out.println(redColorCode+"Bus ID "+ id+ " was reset successfully !!"+resetColorCode);
                            System.out.println(redColorCode+"-----------------------------------------\n"+resetColorCode);
                        }
                    }
                    break;
                case 5 :
                    String answer;
                    System.out.print("Are you sure you want to quit ? (yes/no) : ");
                    answer = sc.next();
                    if(answer.equalsIgnoreCase("Y") ||answer.equalsIgnoreCase("yes") ){
                        System.out.println("\n------------------");
                        System.out.println("Good Bye !! See ya");
                        System.out.println("------------------");
                        return;
                    }
                default:
                    System.out.println(redColorCode+"--------------------------"+resetColorCode);
                    System.out.println(redColorCode+"We have only 5 options !!"+resetColorCode);
                    System.out.println(redColorCode+"--------------------------\n"+resetColorCode);
            }
        }
    }
}
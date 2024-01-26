import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int[][] bus_seats;
        int bus=0,seat=0;
        int[] unavailable;
        int[] available;
        int opt;
        int chair_booking,checkID;
        int count_list_bus=0 ;
        String answer_to_booking,reset_seat;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("---------- SETTING UP BUS ---------");
            System.out.print("-> Enter number of Bus : ");
            bus = sc.nextInt();
            System.out.print("-> Enter number of Seat of Bus : ");
            seat = sc.nextInt();
            if (seat>=15 && seat<=45){
                System.out.println();
                break;
            }else {
                System.out.println("\n-------------------------------------------");
                System.out.println("Seat can not less than 15 and more than 45 !!");
                System.out.println("Please enter the information again !!");
                System.out.println("---------------------------------------------\n");

            }
        }while (true);


        bus_seats = new int[bus][seat];
        available = new int[bus];
        unavailable = new int[bus];

        for(int i = 0;i<bus_seats.length;i++){
            for(int j=0;j<bus_seats[i].length;j++){
                bus_seats[i][j] = 1;
            }
        }

        for(int i=0;i<bus_seats.length;i++){
            for(int j=0;j<bus_seats[i].length;j++){
                if(bus_seats[i][j]==1){
                    available[i]+=1;
                }else {
                    unavailable[i]+=1;
                }
            }
        }

        while (true){
            System.out.println("---------- BUS MANAGEMENT SYSTEM -----------");
            System.out.println("1- Check Bus.");
            System.out.println("2- Booking Bus.");
            System.out.println("3- Cancel Booking.");
            System.out.println("4- Reset Bus.");
            System.out.println("5- Exit.");
            System.out.println("--------------------------------------------");
            System.out.print("-> Choose Option (1-5) : ");
            opt = sc.nextInt();
            System.out.println();
            switch (opt){
                case 1 :
                    int id;
                    int[] seat_available,seat_unavailable;
                    seat_available = new int[bus];
                    seat_unavailable = new int[bus];
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

                    for (int i = 0; i < bus; i++) {
                        System.out.printf("%-5d\t%-10s\t%-15d\t%-15d\n", (i + 1), bus_seats[i].length, seat_available[i], seat_unavailable[i]);
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

                        for(int i=0;i<seat;i++){
                            if (bus_seats[id-1][i]==1){
                                cnt_available+=1;
                            }
                            count_list_bus+=1;
                            String msg = bus_seats[id-1][i] == 0 ? "(-)" : "(+)";
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
                    if (checkID >bus) {
                        System.out.println("--------------------------");
                        System.out.println("We have only "+ bus + " !!");
                        System.out.println("--------------------------");
                        break;
                    }
                    for(int i=0;i<seat;i++){
                        if (bus_seats[checkID-1][i]==1){
                            cnt_available+=1;
                        }
                        cnt_bus+=1;
                        String msg = bus_seats[checkID-1][i] == 0 ? "(-)" : "(+)";
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
                        System.out.println("----------------------------------");
                        System.out.println("Chair is unavailable right now !!");
                        System.out.println("----------------------------------");
                        break ;
                    }
                    if (chair_booking>bus_seats[checkID-1].length){
                        System.out.println("\n----------------------------------");
                        System.out.println("We have only "+ seat + " chairs !!");
                        System.out.println("----------------------------------");
                        break ;
                    }

                    System.out.print("=> Do you want to chair number "+chair_booking + "? (y/n) : ");
                    answer_to_booking = sc.next();

                    if(answer_to_booking.equalsIgnoreCase("Y")){
                        bus_seats[checkID-1][chair_booking-1] = 0;
                        unavailable[checkID-1]+=1;
                        available[checkID-1]-=1;
                        System.out.println("----------------------------------");
                        System.out.println("You have booked chair number : "+ chair_booking);
                        System.out.println("----------------------------------\n");
                        break ;
                    }else {
                        System.out.println("\n-------------------------------");
                        System.out.println("You do not booking any seats !! ");
                        System.out.println("---------------------------------");
                        break ;
                    }
                case 3 :
                    int bus_id,count_seat_ava=0,cnt_bus_num=0,cancel_seat;
                    String ans;
                    System.out.print("-> Enter Bus ID : ");
                    bus_id = sc.nextInt();
                    if(bus_id > bus){
                        System.out.println("\n----------------------");
                        System.out.println("We have only "+ bus + " bus !!");
                        System.out.println("----------------------\n");
                        break;
                    }

                    System.out.println("\n---------- DISPLAY BUS INFORMATION ----------");
                    for(int i=0;i<seat;i++){
                        if (bus_seats[bus_id-1][i]==1){
                            count_seat_ava+=1;
                        }
                        cnt_bus_num+=1;
                        String msg = bus_seats[bus_id-1][i] == 0 ? "(-)" : "(+)";
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
                            System.out.println("\n-----------------------------------------------------");
                            System.out.println("Seat number "+ cancel_seat + " was cancel booking successfully !!");
                            System.out.println("-------------------------------------------------------\n");
                            break;
                        }else {
                            System.out.println("\n------------------------------------------------");
                            System.out.println("Seat number "+ cancel_seat + " was not cancel booking !!");
                            System.out.println("--------------------------------------------------\n");
                            break;
                        }
                    }else {
                        System.out.println("\n----------------------------------------");
                        System.out.println("Seat number "+ cancel_seat + " Already Available !!");
                        System.out.println("-------------------------------------------\n");
                    }
                    break;
                case 4 :
                    System.out.print("-> Enter ID of Bus you want to reset : ");
                    id = sc.nextInt();
                    if(id>bus){
                        System.out.println("\n--------------------------");
                        System.out.println("We have only "+ bus+" bus !!");
                        System.out.println("----------------------------\n");
                        break;
                    }else {
                        System.out.print("Bus ID "+ id+ " was reset with all seats available? (y/n) :");
                        reset_seat = sc.next();
                        if(reset_seat.equalsIgnoreCase("Y")){
                            for (int i = 0;i<seat;i++){
                                bus_seats[id-1][i] = 1;
                            }
                            System.out.println("\n-----------------------------------------");
                            System.out.println("Bus ID "+ id+ " was reset successfully !!");
                            System.out.println("-----------------------------------------\n");
                        }
                    }
                    break;
                case 5 :
                    String answer;
                    System.out.print("Are you sure you want to quit ? (yes/no) : ");
                    answer = sc.next();
                    if(answer.equalsIgnoreCase("Y") ||answer.equalsIgnoreCase("yes") ){
                        return;
                    }
            }
        }
    }
}
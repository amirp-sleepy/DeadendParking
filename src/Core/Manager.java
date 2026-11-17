package Core;

import java.util.Scanner;

import Interfaces.Factory;
import Interfaces.IParking;
import Interfaces.ITicket;

public class Manager {
    public void read() {
    try (Scanner scanner = new Scanner(System.in)) {
        scanner.useDelimiter("[,\\s]+");
        int parkingCapacity = scanner.nextInt();
        IParking parking = Factory.createParking(parkingCapacity);

        String input;
        while (!(input = scanner.next()).equals("E")) {   // exits when input ends
            String action = input;
            String plate = scanner.next();
            int time = scanner.nextInt();
            try {
                switch (action) {
                    case "A":
                        if (parking.add(plate, time))
                            System.out.println("PARK " + plate);
                        else
                            System.out.println("FULL " + plate + " rejected");
                        break;
                    case "D":
                        ITicket ticket = parking.leave(plate, time);
                        int stay = ticket.getExitTime() - ticket.getEntryTime();
                        int fee = Math.round(ticket.getCost());
                        System.out.println("LEAVE " + ticket.getPlate()
                                + " stay=" + stay
                                + " moves=" + ticket.getDisplacement()
                                + " fee=" + fee);
                        break;
                    default: System.err.println("Unknown parking action: " + action);
                }
            } catch (Exception e) {
                System.err.println("ERROR " + plate + " " + e.getMessage());
            }
        }
    }
}
    
}

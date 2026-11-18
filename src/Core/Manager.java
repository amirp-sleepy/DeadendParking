package Core;

import java.util.Scanner;

import Interfaces.Factory;
import Interfaces.ICar;
import Interfaces.IMainSt;
import Interfaces.IParking;
import Interfaces.ITicket;

public class Manager {
    public void read() {
    try (Scanner scanner = new Scanner(System.in)) {
        scanner.useDelimiter("[,\\s]+");
        int parkingCapacity = scanner.nextInt();
        IParking parking = Factory.createParking(parkingCapacity);

        String input;

        IMainSt mainSt = Factory.createMainSt();

        while (!(input = scanner.next()).equals("E")) {   // exits when input ends
            String action = input;
            String plate = scanner.next();
            int time = scanner.nextInt();
            try {
                switch (action) {
                    case "A":
                        ICar car = Factory.createCar(plate);
                        if (!parking.isFull()){
                            int slot = parking.add(car, time);
                            System.out.println("PARK " + plate + " slot=" + slot);
                        }
                            
                        else{
                            int line = mainSt.add(car, time);
                            System.out.println("WAIT " + plate + " line=" + line);
                        }
                            
                        break;
                    case "D":
                        try{
                        ITicket ticket = parking.leave(plate, time);
                        int stay = ticket.getExitTime() - ticket.getEntryTime();
                        int fee = Math.round(ticket.getCost());
                        System.out.println("LEAVE " + ticket.getPlate()
                                + " stay=" + stay
                                + " moves=" + ticket.getDisplacement()
                                + " fee=" + fee);
                        if(!mainSt.isEmpty()){
                            ICar carFromQueue = mainSt.remove();
                            int slot = parking.add(carFromQueue, time);
                            System.out.println("PARK " + carFromQueue.getPlate() + " slot=" + slot + " wait=" + (time - carFromQueue.getWaitingTime()));
                        }
                        }
                        catch(Exception e){
                            if(mainSt.leaveQueue(plate)){
                                System.out.println("GIVEUP " + plate + " fee=0");
                            }
                            else{
                                System.err.println("ERROR " + plate + " " + e.getMessage());
                            }
                        }
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

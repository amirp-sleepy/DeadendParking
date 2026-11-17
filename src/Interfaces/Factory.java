package Interfaces;

import Models.*;

public class Factory {

    public static ICar createCar(String plate){

        return new Car(plate);
    }

    public static ICar createCar(String plate, ITicket ticket){

        return new Car(plate, ticket);
    }

    public static ITicket createTicket(int tick, String plate){

        return new Ticket(tick, plate);
    }

    public static IParking createParking(int capacity){
        return new Parking(capacity);
    }
    
}

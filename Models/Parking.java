package Models;

import Interfaces.*;
import dataStructure.ArrayStack;
import Utilitieas.*;

public class Parking implements IParking {

    ArrayStack<ICar> m_cars;
    ArrayStack<ICar> m_tempPlace;
    int tick;

    public Parking(int capacity){
        m_cars = new ArrayStack<ICar>(capacity);
        m_tempPlace = new ArrayStack<ICar>(capacity);
        tick = 0;
    }

    @Override 
    public void increaseTick(){
        tick = tick + 1;
    }

    @Override 
    public void add(String plate) throws Exception{
        
        if(m_cars.isFull())
            return;

        if(searchCarInParking(plate) != -1)
            throw new Exception("parking already contains this car"); // or return 

        ITicket ticket = Factory.createTicket(tick, plate);
        ICar car = Factory.createCar(plate, ticket);

        m_cars.push(car);

    }

    @Override 
    public float leave(String plate) throws Exception{

        if(m_cars.isEmpty())
            throw new Exception("parking id empty"); // or return 

        int index = searchCarInParking(plate);

        if(index == -1)
            throw new Exception("parking doesn't contain this car"); // or return

        ICar car = null;

        for(int i = m_cars.size() - 1; i > index; i--){

            car = m_cars.pop();
            car.getTicket().increaseDisplacement();
            m_tempPlace.push(car);
        }

        car = m_cars.pop();
        car.getTicket().setExitTime(tick);

        while(!m_tempPlace.isEmpty()){
            m_cars.push(m_tempPlace.pop());
        }

        return calculateCost(car);

    }


    private float calculateCost(ICar car){

        ITicket ticket = car.getTicket();
        int T = ticket.getExitTime() - ticket.getEntryTime();
        return(float) Math.max(0, (Config.r * (Math.ceil(T / 60))) - Config.c * ticket.getDisplacement());

    }


    private int searchCarInParking(String plate){

        for(int i = 0; i < m_cars.size(); i++){
            if(m_cars.get(i).getPlate().equalsIgnoreCase(plate))
                return i;
        }

        return -1;
    }
}

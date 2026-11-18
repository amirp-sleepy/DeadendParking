package Models;

import Interfaces.*;
import dataStructure.ArrayStack;
import Utilitieas.*;
import dataStructure.CircularArray;
import dataStructure.IDequeue;

public class Parking implements IParking {

    IDequeue<ICar> m_cars;
    ArrayStack<ICar> m_tempPlace;

    public Parking(int capacity) {
        m_cars = new CircularArray<ICar>(capacity);
        m_tempPlace = new ArrayStack<ICar>(capacity);
    }

    @Override
    public int add(ICar car, int tick) throws Exception {

        if (searchCarInParking(car.getPlate()) != -1)
            throw new Exception("is already here");

        ITicket ticket = Factory.createTicket(tick, car.getPlate());
        //ICar car = Factory.createCar(plate, ticket);
        car.setTicket(ticket);
        m_cars.insertLast(car);
        return m_cars.size();
    }

@Override
    public ITicket leave(String plate, int tick) throws Exception {

        if (m_cars.isEmpty())
            throw new Exception("not found");

        int index = searchCarInParking(plate);

        if (index == -1)
            throw new Exception("not found");

        ICar car = null;
        ITicket ticket = null;

        if (index > m_cars.size() / 2) {
            for (int i = m_cars.size() - 1; i > index; i--) {
                car = m_cars.removeLast();
                car.getTicket().increaseDisplacement();
                m_tempPlace.push(car);
            }
            car = m_cars.removeLast();
            ticket = car.getTicket();
            ticket.setDoor("south");

            while (!m_tempPlace.isEmpty()) {
                m_cars.insertLast(m_tempPlace.pop());
            }
        }
        else {
            for(int i = 0; i < index; i++) {
                car = m_cars.removeFirst();
                car.getTicket().increaseDisplacement();
                m_tempPlace.push(car);
            }
            car = m_cars.removeFirst();
            ticket = car.getTicket();
            ticket.setDoor("north");
            while (!m_tempPlace.isEmpty()) {
                m_cars.insertFirst(m_tempPlace.pop());
            }
        }


        ticket.setExitTime(tick);
        float cost = calculateCost(car);
        ticket.setCost(cost);

        return ticket;

    }

    private float calculateCost(ICar car) {

        ITicket ticket = car.getTicket();
        int T = ticket.getExitTime() - ticket.getEntryTime();
        int billableHours = (int) Math.ceil(T / 60.0);
        return Math.max(0, Config.r * billableHours - Config.c * ticket.getDisplacement());

    }

    private int searchCarInParking(String plate) {

        for (int i = 0; i < m_cars.size(); i++) {
            if (m_cars.get(i).getPlate().equalsIgnoreCase(plate))
                return i;
        }

        return -1;
    }

    public boolean isFull(){
        return m_cars.isFull();
    }
}

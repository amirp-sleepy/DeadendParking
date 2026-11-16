package Models;

import Interfaces.*;
import dataStructure.ArrayStack;
import Utilitieas.*;

public class Parking implements IParking {

    ArrayStack<ICar> m_cars;
    ArrayStack<ICar> m_tempPlace;

    public Parking(int capacity) {
        m_cars = new ArrayStack<ICar>(capacity);
        m_tempPlace = new ArrayStack<ICar>(capacity);
    }

    @Override
    public boolean add(String plate, int tick) throws Exception {

        if (m_cars.isFull())
            return false;

        if (searchCarInParking(plate) != -1)
            throw new Exception("is already here");

        ITicket ticket = Factory.createTicket(tick, plate);
        ICar car = Factory.createCar(plate, ticket);

        m_cars.push(car);
        return true;
    }

    @Override
    public ITicket leave(String plate, int tick) throws Exception {

        if (m_cars.isEmpty())
            throw new Exception("not found");

        int index = searchCarInParking(plate);

        if (index == -1)
            throw new Exception("not found");

        ICar car = null;

        for (int i = m_cars.size() - 1; i > index; i--) {

            car = m_cars.pop();
            car.getTicket().increaseDisplacement();
            m_tempPlace.push(car);
        }

        car = m_cars.pop();
        ITicket ticket = car.getTicket();
        ticket.setExitTime(tick);
        float cost = calculateCost(car);
        ticket.setCost(cost);

        while (!m_tempPlace.isEmpty()) {
            m_cars.push(m_tempPlace.pop());
        }

    
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
}

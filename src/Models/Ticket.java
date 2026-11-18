package Models;

import Interfaces.ITicket;

public class Ticket implements ITicket {

    private int m_displacement;
    private int m_entryTime;
    private int m_exitTime;
    private float m_cost;
    private String m_plate;
    private String m_door;

    public Ticket(int entryTime, String plate) {
        m_entryTime = entryTime;
        m_displacement = 0;
        m_exitTime = 0;
        m_cost = 0;
        m_plate = plate;
        m_door = "";
    }

    @Override
    public int getDisplacement() {
        return m_displacement;
    }

    @Override
    public void increaseDisplacement() {
        m_displacement++;
    }

    @Override
    public int getEntryTime() {
        return m_entryTime;
    }

    @Override
    public int getExitTime() {
        return m_exitTime;
    }

    @Override
    public void setExitTime(int exitTime) {
        m_exitTime = exitTime;
    }

    @Override
    public float getCost() {
        return m_cost;
    }

    @Override
    public void setCost(float cost) {
        m_cost = cost;
    }

    @Override
    public String getPlate() {
        return m_plate;
    }

    @Override
    public String whichDoor() {
        return m_door;
    }

    @Override
    public void setDoor(String door) {
        m_door = door;
    }
}

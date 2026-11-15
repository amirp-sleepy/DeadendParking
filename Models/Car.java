package Models;
import Interfaces.*;

public class Car implements ICar {

    private String m_plate;
    private ITicket m_ticket;

    public Car(String plate) {

        m_plate = plate;
        m_ticket = null;

    }
    
    public Car(String plate, ITicket ticket) {

        m_plate = plate;
        m_ticket = ticket;

    }

    public String getPlate() { return m_plate; }
    public ITicket getTicket() { return m_ticket; }
    public void setTicket(ITicket ticket) { this.m_ticket = ticket; }

}
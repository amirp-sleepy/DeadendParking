package Models;
import Interfaces.ICar;

public class Car implements ICar {

    private String m_plate;
    private ITicket m_ticket;

    public Car(String m_plate, ITicket m_ticket) {

        this.m_plate = m_plate;
        this.m_ticket = m_ticket;

    }

    public String getPlate() { return m_plate; }
    public ITicket getTicket() { return m_ticket; }
    public void setTicket(ITicket ticket) { this.m_ticket = ticket; }

}
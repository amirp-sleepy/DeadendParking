package Models;
import Interfaces.*;

public class Car implements ICar {

    private String m_plate;
    private ITicket m_ticket;
    private int m_waitTime;

    public Car(String plate) {

        m_plate = plate;
        m_ticket = null;
        m_waitTime = -1;

    }
    
    // public Car(String plate, ITicket ticket) {

    //     m_plate = plate;
    //     m_ticket = ticket;
    //     m_waitTime = 0;

    // }

    public String getPlate() { return m_plate; }
    public ITicket getTicket() { return m_ticket; }
    public void setTicket(ITicket ticket) { this.m_ticket = ticket; }

    
    public int getWaitingTime() {
        return m_waitTime;
    }

    public void setWaitingTime(int waitingTime){
        m_waitTime = waitingTime;
    }
}
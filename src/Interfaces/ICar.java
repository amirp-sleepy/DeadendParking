package Interfaces;

public interface ICar {

    public String getPlate();

    public ITicket getTicket();

    public void setTicket(ITicket ticket);

    public int getWaitingTime();

    public void setWaitingTime(int waitingTime);

}
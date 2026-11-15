package Interfaces;

public interface ITicket {

    public int getDisplacement();
    public void increaseDisplacement();
    public int getEntryTime();
    public int getExitTime();
    public void setExitTime(int exitTime);
    public int getCost();
    public void setCost(int cost);
    public String getPlate();

}

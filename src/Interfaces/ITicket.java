package Interfaces;

public interface ITicket {

    public int getDisplacement();

    public void increaseDisplacement();

    public int getEntryTime();

    public int getExitTime();

    public void setExitTime(int exitTime);

    public float getCost();

    public void setCost(float cost);

    public String getPlate();

}

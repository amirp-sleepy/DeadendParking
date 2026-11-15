package Interfaces;

public interface ICar {

    public String getPlate();
    public int getWaitTime();
    public void setWaitTime(int waitTime);
    public int calculateWaitTime(int entryTimeToLine, int waitTime);

}
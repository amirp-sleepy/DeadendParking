package Interfaces;

public interface IParking {

    public ITicket leave(String plate, int tick) throws Exception;

    public int add(ICar car, int tick) throws Exception;

    public  boolean isFull();

}

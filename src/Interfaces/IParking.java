package Interfaces;

public interface IParking {

    public ITicket leave(String plate, int tick) throws Exception;

    public boolean add(String plate, int tick) throws Exception;

}

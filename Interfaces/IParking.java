package Interfaces;

public interface IParking {

    public float leave(String plate, int tick) throws Exception;

    public void add(String plate, int tick) throws Exception;

}

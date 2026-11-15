package Interfaces;

public interface IParking {
    
    public float leave(String plate) throws Exception;

    public void add(String plate) throws Exception;

    public void increaseTick();
}

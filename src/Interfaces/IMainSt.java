package Interfaces;

public interface IMainSt {
    public boolean leaveQueue(String plate);

    public ICar remove();

    public int add(ICar car, int tick);

    public boolean isEmpty();
}

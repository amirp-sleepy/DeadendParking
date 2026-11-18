package Models;

import Interfaces.ICar;
import Interfaces.IMainSt;
import dataStructure.LinkedQueue;

public class MainSt implements IMainSt {

    LinkedQueue<ICar> carsQueue;

    public MainSt() {
        carsQueue = new LinkedQueue<>();
    }

    @Override
    public boolean leaveQueue(String plate) {

        boolean leaved = false;
        ICar car;
        for (int i = 0; i < carsQueue.size(); i++) {
            car = carsQueue.dequeue();

            if (!car.getPlate().equalsIgnoreCase(plate)) {
                carsQueue.enqueue(car);
            } else
                leaved = true;
        }
        return leaved;
    }

    @Override
    public ICar remove() {
        return carsQueue.dequeue();
    }

    @Override
    public int add(ICar car, int tick) {
        carsQueue.enqueue(car);
        car.setWaitingTime(tick);
        return carsQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return carsQueue.isEmpty();
    }
}

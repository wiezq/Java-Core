package robot;

import java.util.concurrent.atomic.AtomicInteger;

class Leg implements Runnable {
    private final int id;
    private final AtomicInteger steps;


    Leg(AtomicInteger steps, int id) {
        this.id = id;
        this.steps = steps;
    }

    @Override
    public void run() {
        while (steps.get() > 0) {
            synchronized (steps) {
                if (steps.get() % 2 == id) {
                    System.out.println("Leg id = " + id + ", step = " + steps.decrementAndGet());
                    steps.notify();
                } else {
                    try {
                        steps.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
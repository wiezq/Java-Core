package robot;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class Robot {

    public static void main(String[] args) {
        Robot robot = new Robot();
        robot.walk(new AtomicInteger(10));


    }

    public void walk(AtomicInteger steps) {
        if (steps.get() < 1) throw new IllegalArgumentException();


        ExecutorService es = Executors.newFixedThreadPool(2);


        List<Leg> legs = List.of(
                new Leg(steps, 0),
                new Leg(steps, 1)
        );

        legs.forEach(es::execute);

        es.shutdown();
    }

}
import dispatch.Dispatch;
import dispatch.FeedBackDispatch;
import dispatch.PriorityDispatch;
import dispatch.RoundRobinDispatch;
import pojo.PCB;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        final int SIZE = 5;

        PCB[] processes = new PCB[SIZE];
        for (int i = 0; i < SIZE; i++) {
            processes[i] = new PCB(i, random.nextInt(10), random.nextInt(2, 10), random.nextInt(1, 16), 'W');
        }

        Dispatch fd = new PriorityDispatch(processes);
        System.out.println("采用方法: " + fd.getClass().getName());
        fd.run();
    }


}

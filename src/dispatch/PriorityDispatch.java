package dispatch;

import pojo.PCB;

import java.util.Collections;
import java.util.PriorityQueue;

public class PriorityDispatch extends Dispatch{
    private final PriorityQueue<PCB> readyQueue;

    public PriorityDispatch(PCB[] processes) {
        super(processes);
        readyQueue = new PriorityQueue<>((PCB p1, PCB p2) -> p2.priority - p1.priority);    //优先级降序堆
        Collections.addAll(readyQueue, processes);
    }

    @Override
    protected void changeQueue(PCB process, int queueId) {
        if (process.priority > 3)
            process.priority -= 3;
        else
            process.priority = 0;
        if (process.cpuTime > 1)
            process.cpuTime--;
        readyQueue.add(process);
        process.status = 'W';  //等待状态
    }

    @Override
    protected void dispatch(int queueId) {
        PCB process = readyQueue.remove();
        if (runProcess(process))
            process.status = 'F';
        else
            changeQueue(process, queueId);
    }

    @Override
    public void run() {
        while (!readyQueue.isEmpty()) {
            dispatch(0);
        }
        showLog();
    }

    @Override
    public void showLog() {
        super.showLog();
    }
}
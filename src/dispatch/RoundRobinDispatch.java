package dispatch;

import pojo.PCB;

import java.util.LinkedList;
import java.util.Queue;

public class RoundRobinDispatch extends Dispatch{
    private Queue<PCB> readyQueue;

    public RoundRobinDispatch(PCB[] processes) {
        super(processes);
        readyQueue = new LinkedList<>();
        for (PCB process : processes) {
            process.priority = -1;
            readyQueue.add(process);
        }
    }

    @Override
    protected void changeQueue(PCB process, int queueId) {
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
}

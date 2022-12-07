package dispatch;

import pojo.PCB;

import java.util.*;


public class FeedBackDispatch extends Dispatch{
    private final List<Queue<PCB>> readyQueues;    //多条就绪队列

    public final int QUEUE_NUM = 5;

    public FeedBackDispatch(PCB[] processes) {
        super(processes);
        readyQueues = new ArrayList<>();
        for (int i = 0; i < QUEUE_NUM; i++) {   //多条就绪队列
            readyQueues.add(new LinkedList<>());
        }
        for (PCB process : processes) {
            process.priority = QUEUE_NUM;
        }
        Collections.addAll(readyQueues.get(0), processes);
    }

    @Override
    protected void changeQueue(PCB process, int queueId) {    //将进程调换到另一就绪队列
        if (queueId < QUEUE_NUM - 1) {
            process.priority--;    //优先级降低
            process.cpuTime *= 2;  //CPU时间片加长
            readyQueues.get(queueId + 1).add(process);
        }
        else    //最后一条队列
            readyQueues.get(queueId).add(process);
        process.status = 'W';  //等待状态
    }

    @Override
    protected void dispatch(int queueId) {     //运行进程并调度队列
        PCB process = readyQueues.get(queueId).remove();
        if (runProcess(process))
            process.status = 'F';
        else
            changeQueue(process, queueId);
    }

    @Override
    public void run() {
        for (int i = 0; i < QUEUE_NUM; i++) {
            while (!readyQueues.get(i).isEmpty()) {
                dispatch(i);
            }
        }
        showLog();
    }
}

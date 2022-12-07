package dispatch;

import pojo.PCB;

import java.util.Arrays;

abstract public class Dispatch {
    protected PCB[] processes;

    public Dispatch(PCB[] processes) {
        this.processes = processes;
        System.out.println("HelloWorld");
    }

    protected boolean runProcess(PCB process) {    //运行直到时间片完 or 进程完成
        process.status = 'R';
        showLog();          //打印日志
        boolean ret = false;
        for (int i = 0; i < process.cpuTime; i++) {
            if (--process.allTime <= 0) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    abstract protected void changeQueue(PCB process, int queueId);     //更换进程所属的队列(不从队列中取PCB)
    abstract protected void dispatch(int queueId);     //调度一个进程 = 取出作业 + runProcess + dispatch
    abstract public void run();     //运行

    public void showLog() {         //打印日志
        showId();
        showPriority();
        showCPUTime();
        showAllTime();
        showStatus();
        showNextLog();
        System.out.println("=====================================");
    }

    private void showId() {
        System.out.print("ID:         ");
        for (PCB process : processes) {
            System.out.print(process.pid + "\t");
        }
        System.out.println();
    }

    private void showPriority() {
        System.out.print("PRIORITY:   ");
        for (PCB process : processes) {
            System.out.print(process.priority + "\t");
        }
        System.out.println();
    }

    private void showCPUTime() {
        System.out.print("CPUTIME:    ");
        for (PCB process : processes) {
            System.out.print(process.cpuTime + "\t");
        }
        System.out.println();
    }

    private void showAllTime() {
        System.out.print("ALLTIME:    ");
        for (PCB process : processes) {
            System.out.print(process.allTime + "\t");
        }
        System.out.println();
    }

    private void showStatus() {
        System.out.print("STATUS:     ");
        for (PCB process : processes) {
            System.out.print(process.status + "\t");
        }
        System.out.println();
    }

    private void showNextLog() {    //打印next关系
        System.out.print("NEXT:       ");
        PCB[] copy = Arrays.copyOf(processes, processes.length);
        Arrays.sort(copy, (PCB p1, PCB p2)-> p2.priority - p1.priority);
        for (int i = 0; i < processes.length; i++) {
            if (copy[i].status != 'F')
                System.out.print(copy[i].pid + " -> ");
        }
        System.out.println("null");
    }

}

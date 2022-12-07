package pojo;

public class PCB {
    public int pid;
    public int priority;
    public int cpuTime;
    public int allTime;
    public char status;

    public PCB(int pid, int priority, int cpuTime, int allTime, char status) {
        this.pid = pid;
        this.priority = priority;
        this.cpuTime = cpuTime;
        this.allTime = allTime;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PCB{" +
                "pid=" + pid +
                ", priority=" + priority +
                ", cpuTime=" + cpuTime +
                ", allTime=" + allTime +
                ", status=" + status +
                '}';
    }
}

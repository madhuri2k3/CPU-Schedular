// src/jobs/Job.java
package src.jobs;

public class Job {
    private int jobId;
    private int arrivalTime;
    private int burstTime;
    private int remainingTime;
    private int priority;
    private int startTime;
    private int endTime;
    private int waitingTime;
    private int turnaroundTime;

    // Constructor
    public Job(int jobId, int arrivalTime, int burstTime, int priority) {
        this.jobId = jobId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.startTime = -1; // Not yet started
    }

    // Getters and Setters
    public int getJobId() { return jobId; }
    public int getArrivalTime() { return arrivalTime; }
    public int getBurstTime() { return burstTime; }
    public int getRemainingTime() { return remainingTime; }
    public void setRemainingTime(int remainingTime) { this.remainingTime = remainingTime; }
    public int getPriority() { return priority; }
    public int getStartTime() { return startTime; }
    public void setStartTime(int startTime) { this.startTime = startTime; }
    public int getEndTime() { return endTime; }
    public void setEndTime(int endTime) { this.endTime = endTime; }
    public int getWaitingTime() { return waitingTime; }
    public int getTurnaroundTime() { return turnaroundTime; }

    // Calculate waiting time and turnaround time
    public void calculateWaitingTime() {
        this.waitingTime = this.startTime - this.arrivalTime;
    }

    public void calculateTurnaroundTime() {
        this.turnaroundTime = this.endTime - this.arrivalTime;
    }
}

package src.jobs.schedulers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import src.jobs.Job;

public class RoundRobin implements Scheduler {
    private int timeQuantum;

    public RoundRobin(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    @Override
    public void schedule(ArrayList<Job> jobs) {
        Queue<Job> jobQueue = new LinkedList<>(jobs);
        int currentTime = 0;

        while (!jobQueue.isEmpty()) {
            Job job = jobQueue.poll();
            if (currentTime < job.getArrivalTime()) {
                currentTime = job.getArrivalTime();
            }

            if (job.getStartTime() == -1) {
                job.setStartTime(currentTime);
            }

            if (job.getRemainingTime() > timeQuantum) {
                currentTime += timeQuantum;
                job.setRemainingTime(job.getRemainingTime() - timeQuantum);
                jobQueue.add(job);
            } else {
                currentTime += job.getRemainingTime();
                job.setRemainingTime(0);
                job.setEndTime(currentTime);

                job.calculateWaitingTime();
                job.calculateTurnaroundTime();

                System.out.println("Job ID: " + job.getJobId() + ", Start Time: " + job.getStartTime() +
                        ", End Time: " + job.getEndTime() + ", Waiting Time: " + job.getWaitingTime() +
                        ", Turnaround Time: " + job.getTurnaroundTime());
            }
        }
    }
}

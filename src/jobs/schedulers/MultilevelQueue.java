// src/schedulers/MultilevelQueue.java
package src.jobs.schedulers;

import src.jobs.Job;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MultilevelQueue implements Scheduler {
    private Queue<Job> systemQueue;     // Higher priority queue (for system jobs)
    private Queue<Job> userQueue;       // Lower priority queue (for user jobs)

    public MultilevelQueue() {
        systemQueue = new LinkedList<>();
        userQueue = new LinkedList<>();
    }

    @Override
    public void schedule(ArrayList<Job> jobs) {
        // Split jobs into system and user queues based on priority
        for (Job job : jobs) {
            if (job.getPriority() <= 3) {
                systemQueue.add(job);
            } else {
                userQueue.add(job);
            }
        }

        int currentTime = 0;

        // First schedule system jobs (higher priority)
        while (!systemQueue.isEmpty()) {
            Job job = systemQueue.poll();
            if (currentTime < job.getArrivalTime()) {
                currentTime = job.getArrivalTime();
            }
            job.setStartTime(currentTime);
            currentTime += job.getBurstTime();
            job.setEndTime(currentTime);

            job.calculateWaitingTime();
            job.calculateTurnaroundTime();

            System.out.println("System Job ID: " + job.getJobId() + ", Start Time: " + job.getStartTime() +
                    ", End Time: " + job.getEndTime() + ", Waiting Time: " + job.getWaitingTime() +
                    ", Turnaround Time: " + job.getTurnaroundTime());
        }

        // Then schedule user jobs (lower priority)
        while (!userQueue.isEmpty()) {
            Job job = userQueue.poll();
            if (currentTime < job.getArrivalTime()) {
                currentTime = job.getArrivalTime();
            }
            job.setStartTime(currentTime);
            currentTime += job.getBurstTime();
            job.setEndTime(currentTime);

            job.calculateWaitingTime();
            job.calculateTurnaroundTime();

            System.out.println("User Job ID: " + job.getJobId() + ", Start Time: " + job.getStartTime() +
                    ", End Time: " + job.getEndTime() + ", Waiting Time: " + job.getWaitingTime() +
                    ", Turnaround Time: " + job.getTurnaroundTime());
        }
    }
}

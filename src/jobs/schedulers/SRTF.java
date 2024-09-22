package src.jobs.schedulers;

import java.util.ArrayList;
import java.util.Comparator;

import src.jobs.Job;

public class SRTF implements Scheduler {

    @Override
    public void schedule(ArrayList<Job> jobs) {
        jobs.sort(Comparator.comparingInt(Job::getRemainingTime));
        int currentTime = 0;

        while (!jobs.isEmpty()) {
            Job job = jobs.remove(0);
            if (currentTime < job.getArrivalTime()) {
                currentTime = job.getArrivalTime();
            }
            job.setStartTime(currentTime);
            currentTime += job.getBurstTime();
            job.setEndTime(currentTime);

            job.calculateWaitingTime();
            job.calculateTurnaroundTime();

            System.out.println("Job ID: " + job.getJobId() + ", Start Time: " + job.getStartTime() +
                    ", End Time: " + job.getEndTime() + ", Waiting Time: " + job.getWaitingTime() +
                    ", Turnaround Time: " + job.getTurnaroundTime());
        }
    }
}

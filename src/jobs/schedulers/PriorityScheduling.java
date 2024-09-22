package src.jobs.schedulers;

import java.util.ArrayList;
import java.util.Comparator;

import src.jobs.Job;

public class PriorityScheduling implements Scheduler {

    @Override
    public void schedule(ArrayList<Job> jobs) {
        jobs.sort(Comparator.comparingInt(Job::getPriority));

        int currentTime = 0;
        for (Job job : jobs) {
            if (currentTime < job.getArrivalTime()) {
                currentTime = job.getArrivalTime();
            }
            job.setStartTime(currentTime);
            currentTime += job.getBurstTime();
            job.setEndTime(currentTime);

            job.calculateWaitingTime();
            job.calculateTurnaroundTime();

            System.out.println("Job ID: " + job.getJobId() + ", Priority: " + job.getPriority() +
                    ", Start Time: " + job.getStartTime() + ", End Time: " + job.getEndTime() +
                    ", Waiting Time: " + job.getWaitingTime() + ", Turnaround Time: " + job.getTurnaroundTime());
        }
    }
}

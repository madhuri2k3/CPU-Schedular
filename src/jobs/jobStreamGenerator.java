// src/jobs/JobStreamGenerator.java
package src.jobs;

import java.util.ArrayList;
import java.util.Random;

public class jobStreamGenerator {

    public ArrayList<Job> generateJobs(int numJobs, int minBurstTime, int maxBurstTime, int minArrivalTime, int maxArrivalTime, int maxPriority) {
        ArrayList<Job> jobs = new ArrayList<>();
        Random rand = new Random();

        for (int i = 1; i <= numJobs; i++) {
            int arrivalTime = rand.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            int burstTime = rand.nextInt(maxBurstTime - minBurstTime + 1) + minBurstTime;
            int priority = rand.nextInt(maxPriority) + 1;
            jobs.add(new Job(i, arrivalTime, burstTime, priority));
        }

        return jobs;
    }
}


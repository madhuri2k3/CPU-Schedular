import src.jobs.schedulers.*;

import java.util.ArrayList;

public class SchedulerApp {

    public static void main(String[] args) {
        // Generate job stream
        JobStreamGenerator generator = new JobStreamGenerator();
        ArrayList<Job> jobs = generator.generateJobs(5, 2, 10, 0, 10, 5); // 5 jobs, burst times between 2 and 10, arrival between 0 and 10, priorities from 1 to 5

        // Choose a scheduler and run it
        Scheduler scheduler = new MultilevelQueue(); // Multilevel Queue Scheduling
        // Scheduler scheduler = new MultilevelFeedbackQueue(4, 8); // Multilevel Feedback Queue with two time quanta
        scheduler.schedule(jobs);
    }
}

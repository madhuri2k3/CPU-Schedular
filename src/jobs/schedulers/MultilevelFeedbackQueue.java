package src.jobs.schedulers;
import src.jobs.Job;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MultilevelFeedbackQueue implements Scheduler {
    private Queue<Job> queue1; // Highest priority queue (short time quantum)
    private Queue<Job> queue2; // Medium priority queue (longer time quantum)
    private Queue<Job> queue3; // Lowest priority queue (FCFS)

    private int timeQuantum1;  // Time quantum for queue1
    private int timeQuantum2;  // Time quantum for queue2

    public MultilevelFeedbackQueue(int timeQuantum1, int timeQuantum2) {
        this.timeQuantum1 = timeQuantum1;
        this.timeQuantum2 = timeQuantum2;

        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
        queue3 = new LinkedList<>();
    }

    @Override
    public void schedule(ArrayList<Job> jobs) {
        // Initially, all jobs are in the highest priority queue
        queue1.addAll(jobs);
        int currentTime = 0;

        // Process jobs in queue1 (highest priority, shortest time quantum)
        while (!queue1.isEmpty()) {
            Job job = queue1.poll();
            if (currentTime < job.getArrivalTime()) {
                currentTime = job.getArrivalTime();
            }

            if (job.getRemainingTime() > timeQuantum1) {
                currentTime += timeQuantum1;
                job.setRemainingTime(job.getRemainingTime() - timeQuantum1);
                queue2.add(job);  // Move job to queue2 after its time quantum is used
            } else {
                currentTime += job.getRemainingTime();
                job.setRemainingTime(0);
                job.setEndTime(currentTime);
                job.calculateWaitingTime();
                job.calculateTurnaroundTime();

                System.out.println("Job ID: " + job.getJobId() + ", Finished in Queue 1, End Time: " +
                        job.getEndTime() + ", Waiting Time: " + job.getWaitingTime() + ", Turnaround Time: " + job.getTurnaroundTime());
            }
        }

        // Process jobs in queue2 (medium priority, longer time quantum)
        while (!queue2.isEmpty()) {
            Job job = queue2.poll();
            if (currentTime < job.getArrivalTime()) {
                currentTime = job.getArrivalTime();
            }

            if (job.getRemainingTime() > timeQuantum2) {
                currentTime += timeQuantum2;
                job.setRemainingTime(job.getRemainingTime() - timeQuantum2);
                queue3.add(job);  // Move job to queue3 after its time quantum is used
            } else {
                currentTime += job.getRemainingTime();
                job.setRemainingTime(0);
                job.setEndTime(currentTime);
                job.calculateWaitingTime();
                job.calculateTurnaroundTime();

                System.out.println("Job ID: " + job.getJobId() + ", Finished in Queue 2, End Time: " +
                        job.getEndTime() + ", Waiting Time: " + job.getWaitingTime() + ", Turnaround Time: " + job.getTurnaroundTime());
            }
        }

        // Process jobs in queue3 (lowest priority, FCFS)
        while (!queue3.isEmpty()) {
            Job job = queue3.poll();
            if (currentTime < job.getArrivalTime()) {
                currentTime = job.getArrivalTime();
            }
            job.setStartTime(currentTime);
            currentTime += job.getRemainingTime();
            job.setEndTime(currentTime);

            job.calculateWaitingTime();
            job.calculateTurnaroundTime();

            System.out.println("Job ID: " + job.getJobId() + ", Finished in Queue 3 (FCFS), End Time: " +
                    job.getEndTime() + ", Waiting Time: " + job.getWaitingTime() + ", Turnaround Time: " + job.getTurnaroundTime());
        }
    }
}

// src/schedulers/Scheduler.java
package src.jobs.schedulers;

import java.util.ArrayList;

import src.jobs.Job;

public interface Scheduler {
    void schedule(ArrayList<Job> jobs);
}

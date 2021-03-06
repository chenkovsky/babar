package com.criteo.babar.agent.worker;

import com.criteo.babar.agent.reporter.Reporter;

import java.util.Collection;

public class ShutdownHookWorker implements Runnable {

    private final Collection<? extends Schedulable> schedulables;
    private final Reporter reporter;

    public ShutdownHookWorker(Collection<? extends Schedulable> schedulables, Reporter reporter) {
        this.schedulables = schedulables;
        this.reporter = reporter;
    }

    @Override
    public void run() {

        // profile for all profilers
        for (Schedulable s: schedulables) {
            try {
                s.stop();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        // finally stop reporter correctly
        reporter.stop();
    }
}

package com.team3316.orchestra.time;

import edu.wpi.first.wpilibj2.command.Command;

/**
 * A wrapper that runs another command in a new thread with no timer.
 * <p>
 * The inner command's {@link Command#execute() execute} method is called
 * repeatedly, with no pause between calls.
 */
public class ContinuousCommand extends Command {
    private final Command inner;
    private Thread thread;

    public ContinuousCommand(Command inner) {
        this.inner = inner;
        addRequirements(inner.getRequirements());
    }

    @Override
    public void initialize() {
        inner.initialize();
        // While the thread is running, it should have exclusive access to inner!
        thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted() && !inner.isFinished())
                inner.execute();
        });
        thread.start();
    }

    @Override
    public boolean isFinished() {
        if (thread == null) return true;
        // We rely on short-circuiting
        // isFinished is called only if thread isn't alive
        return !thread.isAlive() && inner.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        if (thread == null) return;

        if (interrupted) thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread = null;
        // thread has ended, so we can use inner
        inner.end(interrupted);
    }
}

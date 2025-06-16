package app.threading;

public class ThreadedExecution implements Runnable {
    private MethodCallback callback;
    private Thread thread;
    private volatile boolean running;

    public ThreadedExecution() {
        callback = null;
        running = false;
    }
    public ThreadedExecution(MethodCallback callback) {
        this.callback = callback;
        running = false;
    }

    public void execute(MethodCallback callback) {
        this.callback = callback;
        start();
    }
    public void start() {
        if (callback == null) {
            throw new RuntimeException("Method callback was null!");
        }
        thread = new Thread(this);
        thread.start();
    }
    public void run() {
        running = true;
        callback.run();
        running = false;
    }

    public boolean isDone() {
        return !running;
    }
    public MethodCallback getCallback() {
        return callback;
    }
}

package app.threading;

public class ThreadedExecution implements Runnable {
    private MethodCallback callback;
    private Thread thread;
    private volatile boolean running;
    private String name;

    public ThreadedExecution() {
        callback = null;
        name = null;
        running = false;
    }
    public ThreadedExecution(String name) {
        callback = null;
        this.name = name;
        running = false;
    }
    public ThreadedExecution(MethodCallback callback) {
        this.callback = callback;
        name = null;
        running = false;
    }
    public ThreadedExecution(MethodCallback callback, String name) {
        this.callback = callback;
        this.name = name;
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
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public void run() {
        callback.run();
        running = false;
    }

    public boolean isDone() {
        return !running;
    }
    public MethodCallback getCallback() {
        return callback;
    }
    public String getName() {
        return name;
    }
}

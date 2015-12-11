import controller.Controller;

/**
 * Created by hanne on 07.12.2015.
 */

public class Main {
    private Controller controller;
    private Thread thread;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        this.controller = new Controller();

        this.thread = new Thread() {
            public void run() {
                while (true) {
                    controller.listen();
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        this.thread.start();
    }
}

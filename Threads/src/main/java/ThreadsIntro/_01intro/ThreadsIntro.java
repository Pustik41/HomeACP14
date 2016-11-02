package ThreadsIntro._01intro;

/**
 * Created by Котято on 21.09.2016.
 */
public class ThreadsIntro {

    public static void main(String[] args) {
        // get current thread
        Thread main = Thread.currentThread();

        System.out.println(main.getName());

        MyTread myTread = new MyTread("poor info thread");
        myTread.setDaemon(true);
        myTread.start();

        for (int i = 0; i < 10; i++) {
            System.out.printf("main working %s\n", i);

            if(i == 5){
                myTread.interrupt();
            }
            try {
                main.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyTread extends Thread{

    private String threadDescprint;

    public MyTread(String threadDescprint) {
        this.threadDescprint = threadDescprint;
    }

    @Override
    public void run() {

        while (!isInterrupted()){
            System.out.printf("My work %s, %s, %s\n", getId(), getName(), getState());
            try {
                sleep(1000); // interrupt  - разбудит
            } catch (InterruptedException e) {
                e.printStackTrace();
                interrupt(); // для того что бы поменять статус потока и он себа правильно завершит
            }
        }
    }
}

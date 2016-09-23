package homework.week3.math_counting;

import homework.week3.math_counting.utils.FileSearchThread;

import java.io.File;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newCachedThreadPool;

/**
 * Created by Anna on 14.09.2016.
 */
public class MatchCounter {

    private File directory;
    private String keyword;
    private int count;
    private ExecutorService service;

    public MatchCounter(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
        service = Executors.newCachedThreadPool();
    }

    public int find(){

        Future<Integer> future = service.submit(new FileSearchThread(directory, keyword,service));

        while (!future.isDone()){

        }

        service.shutdown();

        try {
            count = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return count;
    }
}

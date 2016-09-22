package homework.week3._02mathCounting;

import homework.week3._01mathCounting.Utils.FileSearchThread;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newCachedThreadPool;

/**
 * Created by Anna on 14.09.2016.
 */
public class MatchCounter {

    private File directory;
    private String keyword;
    private int count;

    public MatchCounter(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
    }

    public int find(){
        count = 0;
        File[] files = directory.listFiles();
        ExecutorService service = newCachedThreadPool();

        Arrays.stream(files).forEach(file -> {
            if (file.isDirectory()) {
               Future<Integer> future = service.submit(new FileSearchThread(file, keyword));

                try {
                    count+= future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            } else if(file.getName().contains(keyword)) {
                count++;
            }});

        return count;
    }
}

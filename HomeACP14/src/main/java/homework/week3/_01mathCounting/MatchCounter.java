package homework.week3._01mathCounting;

import homework.week3._01mathCounting.Utils.FileSearchThread;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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

        Arrays.stream(files).forEach(file -> {
            if (file.isDirectory()) {
                try {
                    try {
                        FutureTask<Integer> futureTask = new FutureTask<Integer>(new FileSearchThread(file, keyword));
                        futureTask.run();

                        count += futureTask.get();

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if(file.getName().contains(keyword)) {
                System.out.println(file.getName());
                count++;
            }});


        return count;
    }

}

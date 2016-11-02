package homework.week3.math_counting.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * Created by Котято on 22.09.2016.
 */
public class FileSearchThread implements Callable{

    private File root;
    private String keyWord;
    private  int count;
    private ExecutorService service;
    private List<FutureTask<Integer>> futureTasks;

    public FileSearchThread(File root, String keyWord, ExecutorService service) {
        this.root = root;
        this.keyWord = keyWord;
        this.service = service;
        futureTasks = new ArrayList<>();
    }

    @Override
    public Object call() throws Exception {
        return searshFile(root);
    }

    private int searshFile(File root){
        count = 0;

        File[] files = root.listFiles();

        Arrays.stream(files).forEach( file -> {
            if (file.isDirectory()) {
                FutureTask<Integer> futureTask = new FutureTask<Integer>(new FileSearchThread(file, keyWord,service));
                futureTasks.add(futureTask);
                service.submit(futureTask);
            } else {
                if(file.getName().contains(keyWord)) {
                    count++;
                }
            }
        });

        for (FutureTask<Integer> futureTask : futureTasks) {

            while (!futureTask.isDone()){

            }

            try {
                count += futureTask.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

        return count;
    }
}

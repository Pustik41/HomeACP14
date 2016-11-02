package ThreadsIntro._02join;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Котято on 21.09.2016.
 */
public class _02ThreadJoin {

    public static void main(String[] args) {

        File folder = new File("E:\\Java\\Projects\\HomeACP14");

        File[] files = folder.listFiles();

        List<Thread> threads = new ArrayList<>();
        List<AsynchFileSearchTask> tasks = new ArrayList<>();
        List<File> results = new ArrayList<>();

        for (File file : files) {
            if(file.isDirectory()){
                AsynchFileSearchTask task = new AsynchFileSearchTask(file, ".xml");
                Thread thread = new Thread(task);

                threads.add(thread);
                tasks.add(task);

                thread.start();
            }
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (AsynchFileSearchTask task : tasks) {
            List<File> result = task.getRes();
            results.addAll(result);
        }

        results.forEach(System.out::println);
    }
}


class AsynchFileSearchTask implements Runnable{

    private List<File> searchResult;
    private String keyWord;
    private File root;

    public AsynchFileSearchTask(File root, String keyWord) {
        searchResult = new ArrayList<>();
        this.root = root;
        this.keyWord = keyWord;
    }

    @Override
    public void run() {
        recSearch(root);
    }

    private void recSearch( File directory) {
        if(directory == null){
            return;
        }

        File[] files = directory.listFiles();
        if(files == null || files.length == 0){
            return;
        }

        Arrays.stream(files)
                .forEach((file -> {
                    if(file.isDirectory()){
                        recSearch(file);
                    } else  if(file.getName().contains(keyWord)){
                        searchResult.add(file);
                    }
                }));
    }

    public List<File> getRes(){
        return  searchResult;
    }
}
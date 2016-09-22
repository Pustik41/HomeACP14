package homework.week3._01mathCounting.Utils;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * Created by Котято on 22.09.2016.
 */
public class FileSearchThread implements Callable{

    private File root;
    private String keyWord;
    private  int count;

    public FileSearchThread(File root, String keyWord) {
        this.root = root;
        this.keyWord = keyWord;
        count = 0;
    }

    @Override
    public Object call() throws Exception {
        return searshFile(root);
    }

    private int searshFile(File root){

        File[] files = root.listFiles();

        Arrays.stream(files).forEach( file -> {
            if (file.isDirectory()) {
                searshFile(file);
            } else {
                if(file.getName().contains(keyWord)) {
                    count++;
                }
            }
        });

        return count;
    }
}

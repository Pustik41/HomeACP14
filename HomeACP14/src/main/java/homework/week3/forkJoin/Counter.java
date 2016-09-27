package homework.week3.forkJoin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Anna on 15.09.2016.
 */

public class Counter extends RecursiveTask<Integer> {

    public static final int THRESHOLD = 1000;
    private double[] values;
    private int from;
    private int to;
    private Filter filter;

    public Counter(double[] values, int from, int to, Filter filter) {
        this.values = values;
        this.filter = filter;
        this.to = to;
        this.from = from;
    }


    @Override
    protected Integer compute() {
        int count = 0;


        if (to - from < THRESHOLD) {
            for (int i = from; i < to; i++) {
                if (filter.accept(values[i])) {
                    count++;
                }
            }
            return count;
        } else {
            int mid = (from + to) / 2;
            Counter task1 = new Counter(values, from, mid, filter);
            task1.fork();
            Counter task2 = new Counter(values, mid, to, filter);

            return task2.compute()+ task1.join();
        }
    }
}

package homework.week3.bankExample;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Anna on 12.09.2016.
 */
public class Bank {

    private final double[] accounts;
    private final ReentrantReadWriteLock rwLock ;
    private Condition transferCondition ;

    public Bank(int n, double initialBalance) {

        rwLock = new ReentrantReadWriteLock();
        transferCondition = rwLock.writeLock().newCondition();
        accounts = new double[n];
        for (int i = 0; i < accounts.length; i++){
            accounts[i] = initialBalance;
        }
    }

    public void transfer(int from, int to, double amount) {
        try {
            rwLock.writeLock().lock();

            while (accounts[from] < amount) {
                try {
                    transferCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;

            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            transferCondition.signalAll();
        } finally {
            rwLock.writeLock().unlock();
        }

    }

    private double getTotalBalance() {
        try {

            rwLock.readLock().lock();

            double sum = 0;
            for (double a : accounts) {
                sum += a;
            }
            return sum;
        }finally {
            rwLock.readLock().unlock();
        }

    }

    public int size(){

        return accounts.length;
    }
}

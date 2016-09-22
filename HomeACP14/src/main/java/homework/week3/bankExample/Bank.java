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
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(); //new ReentrantLock(true);
    private Condition transferCondition  = rwLock.writeLock().newCondition();

    public Bank(int n, double initialBalance) {
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

            rwLock.readLock().lock();

            transferCondition.signalAll();
        } finally {
            rwLock.writeLock().unlock();
        }

        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
        rwLock.readLock().unlock();
    }

    private double getTotalBalance() {
        double sum = 0;
        for (double a: accounts){
            sum += a;
        }
        return sum;

    }

    public int size(){

        return accounts.length;
    }
}

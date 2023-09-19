package com.algorithm.bank2;

//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SummingUpTest {

    private static final int TRANSFER_AMOUNT = 1;
    private static final int INITIAL_BALANCE = 100000;
    private static final int ACCOUNTS = 1000;
    private static final int TRANSACTIONS = 10; //100000;

    private BankingSystem bs;
    private int initialSum, lastSum;
    private List<Account> accounts;
    private List<Thread> threads;

    private AtomicLong remainingTransactions;

    @BeforeClass
    public void setUp() throws Exception {
        int noOfCores = 10000;

        bs = new BankingSystem();
        accounts = new ArrayList<Account>();
        for (int i = 0; i < ACCOUNTS; i++) {
            Account a = new Account(i);
            a.setBalance(INITIAL_BALANCE);
            initialSum += INITIAL_BALANCE;
            accounts.add(a);
        }

        // Add the accounts shuffled.
        List<Account> shuffled = new ArrayList<Account>(accounts);
        Collections.shuffle(shuffled);
        for (Account a : shuffled) {
            bs.addAccount(a);
        }

        remainingTransactions = new AtomicLong(noOfCores * TRANSACTIONS*100);
        //System.out.println("1 remainingTransactions.get() = " + remainingTransactions.get());

        // one transfer thread for each core
        threads = new ArrayList<Thread>(noOfCores + 1);
        for (int i = 0; i < noOfCores; i++) {

            threads.add(new Thread() {
                @Override
                public void run() {
                    Random generator = new Random();
                    while (remainingTransactions.decrementAndGet() > 0) {
                        //System.out.printf(".");
                        Account from = accounts.get(generator.nextInt(ACCOUNTS));
                        Account to = accounts.get(generator.nextInt(ACCOUNTS));
                        bs.transferMoney(from, to, TRANSFER_AMOUNT);
                    }
                }
            });
        }

        // /*
        // additional revisor thread which sums stuff up
        threads.add(new Thread() {
            @Override
            public void run() {
                //System.out.printf("2. remainingTransactions.get() = " + remainingTransactions.get());
                //while (remainingTransactions.decrementAndGet() > 0) {
                do {
                    //System.out.printf("_");
                    lastSum = bs.totalMoneyInBank();
                    if (lastSum != initialSum) {
                        // mismatch found, finish early
                        remainingTransactions.set(0);
                    }
                } while (remainingTransactions.decrementAndGet() > 0);
            }
        });
        // */
    }

    @Test(timeOut = 5000) // Timeout in case of deadlock
    public void testSum() throws InterruptedException {
        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        Assert.assertEquals(initialSum, lastSum, "Concurrent summing up was incorrect");
        //assertThat("Concurrent summing up was incorrect", lastSum, is(initialSum));
        //assertEquals("Concurrent summing up was incorrect", initialSum, lastSum);
    }
}

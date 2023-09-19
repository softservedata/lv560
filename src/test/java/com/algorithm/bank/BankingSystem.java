package com.algorithm.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankingSystem {

    protected List<Account> accountList;
    private Lock lock = new ReentrantLock();
    AtomicInteger count = new AtomicInteger();
    //

    /**
     * Initializes the BankingSystem:
     * accountList is empty and totalMoneyInBank() should return 0.
     */
    public BankingSystem() {
        setAccountList(new ArrayList<Account>());
        count.set(0);
    }

    /**
     * Transfers Money from one account to another.
     *
     * @param from   Account to transfer money from.
     * @param to     Account to transfer money to.
     * @param amount Amount to transfer.
     * @return True if Money was transferred successfully.
     * False if there was not enough balance in from.
     */
    public boolean transferMoney(Account from, Account to, int amount) {

        if (from.getBalance() < amount) {
            return false;
        }
        ReentrantLock fromLock;
        ReentrantLock toLock;
        if (from.compareTo(to) < 0) {
            fromLock = from.getLock();
            toLock = to.getLock();
        } else {
            fromLock = to.getLock();
            toLock = from.getLock();
        }
//        //
//        if (count.get() == 0) {
//            System.out.printf("S");
//            lock.lock();
//            System.out.printf("L");
//        }
        while (count.get() > 0);
        //
        if (count.get() <=0) {
            count.decrementAndGet();
            //
            fromLock.lock();
            toLock.lock();
            //
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            //
            toLock.unlock();
            fromLock.unlock();
            //
            count.incrementAndGet();
        }
//        if (count.get() == 0) {
//            try {
//                lock.unlock();
//            } catch (Exception e) {
//            }
//        }
        //
        return true;

        /*
        //System.out.print("start transferMoney()");
        ReentrantLock fromLock;
        ReentrantLock toLock;

        if (from.compareTo(to) < 0) {
            fromLock = from.getLock();
            toLock = to.getLock();
        } else {
            fromLock = to.getLock();
            toLock = from.getLock();
        }

        //fromLock.lock();
        //toLock.lock();
            if (from.getBalance() < amount) {
                return false;
            } else {
                from.setBalance(from.getBalance() - amount);
                to.setBalance(to.getBalance() + amount);
            }
            //System.out.println(from.getId());
        try {
            //System.out.print("end transferMoney()");
            return true;
        } finally {
           // fromLock.unlock();
            //toLock.unlock();
        }
        */
    }

    /**
     * Returns the sum of a given list of accounts.
     *
     * @fixme Tends to return wrong results :-(
     */
    public int sumAccounts(List<Account> accounts) {
        int sum = 0;

        if (count.get() < 0) {
            lock.lock();
        }
//        while (count.get() < 0) {
//            System.out.printf("_");
//        };
        System.out.printf(" " + String.valueOf(count.get()));
        //
        if (count.get() >=0) {
            count.incrementAndGet();
            System.out.printf("+");

            for (Account a : accounts) {
                sum += a.getBalance();
            }
            count.decrementAndGet();
        }
        if (count.get() == 0) {
            lock.unlock();
        }
        return sum;
    }

    /**
     * Calculates the total amount of money in the bank at any point in time.
     *
     * @return The total amount of money.
     * @fixme Tends to return wrong results :-(
     */
    public int totalMoneyInBank() {
        return sumAccounts(getAccountList());
    }

    /**
     * Adds a new account to the bank.
     * The account needs to have a positive balance to be added to the system.
     *
     * @param a New account
     * @return True if account was added successfully.
     * False if account could not be added to the system
     * (ie., account did not have enough balance).
     */
    public boolean addAccount(Account a) {
        if (a.getBalance() >= 0) {
            getAccountList().add(a);
            return true;
        } else {
            return false;
        }

    }

    protected List<Account> getAccountList() {
        return accountList;
    }

    protected void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

}

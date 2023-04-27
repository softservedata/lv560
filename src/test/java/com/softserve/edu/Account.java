package com.softserve.edu;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account implements Comparable<Account> {

	private final int id;
	private int balance;
	private ReentrantLock lock = new ReentrantLock();

	/**
	 * @param id Id of the account. You should make sure to set this is unique over all accounts.
	 */
	public Account(int id) {
		this.id = id;
		this.balance = 0;
	}

	/**
	 * @return Balance of the account.
	 */
	public int getBalance() {
		return balance;
		/*
		lock.lock();
		try {
			return balance;
		} finally {
			lock.unlock();
		}
		*/
	}

	public ReentrantLock getLock() {
		return lock;
	}

	/**
	 * Set a new balance for this account.
	 *
	 * @param balance The new balance. Must not be negative.
	 */
	public void setBalance(int balance) {
		if (balance < 0) {
			throw new IllegalArgumentException("Negative balance.");
		}
		this.balance = balance;
	}

	/**
	 * @return Id of the account. You should make sure this is unique over all accounts.
	 */
	public int getId() {
//		lock.lock();
//		try {
			return id;
//		} finally {
//			lock.unlock();
//		}
	}

	/**
	 * Implementation of Comparable interface. This allows a list of Accounts
	 * to be sorted using Collections.sort()
	 *
	 * @return A negative integer, zero, or a positive integer if
	 * this account is less than, equal to, or greater than the other account.
	 */
	@Override
	public int compareTo(Account other) {
		//lock.lock();
		try {
			return (id < other.getId() ? -1 : (id == other.getId() ? 0 : 1));
		} finally {
			//lock.unlock();
		}

	}

}

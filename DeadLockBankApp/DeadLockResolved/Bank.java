/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banktransfer;

import java.util.*;
import java.util.concurrent.locks.*;


public class Bank
{
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;

    public Bank(int n)
    {
       accounts = new double[n];
       // Gives the inital balance of account 0 200 and account 1 300
       accounts[0] = 200;
       accounts[1] = 100;
       bankLock = new ReentrantLock();
       sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) throws InterruptedException
    {
        bankLock.lock();
        try
        {
            
            if (accounts[from] < amount)
                throw new InsufficientFundsException("Insufficient funds");
            else 
                System.out.print(Thread.currentThread());
                accounts[from] -= amount;
                System.out.printf(" %10.2f from %d to %d", amount, from, to);
                accounts[to] += amount;
                System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
                sufficientFunds.signalAll();
             
        } catch (InsufficientFundsException i) {
            
            System.out.println(i);
            
        } finally {     
            
          bankLock.unlock();
          
        }
    }

    public double getTotalBalance()
    {
        bankLock.lock();
        try
        {
            double sum = 0;

            for (double a : accounts)
                sum += a;

            return sum;
        }
        finally
        {
            bankLock.unlock();
        }
    }

    public int size()
    {
       return accounts.length;
    }
    
    public class InsufficientFundsException extends Exception {
    
        public InsufficientFundsException (String message) {
            super(message);
        }
    
    }
}


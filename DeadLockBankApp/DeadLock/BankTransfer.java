/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banktransfer;

/**
 *
 * @author kmarti
 */
public class BankTransfer 
{
    // sets the max number of accounts that can be created
    public static final int NACCOUNTS = 2;
    public static final int DELAY = 10;

    public static void main(String[] args)
    {
        // initializes the accounts
        Bank bank = new Bank(NACCOUNTS);
        for (int i = 0; i < NACCOUNTS; i++)
        {
            int acc = i;
            Runnable r = () -> {
                try
                {
                    while (true)
                    {
                        // tries to transfer 300 from account 0 to account 1 in thread 0
                        if (acc == 0)
                            bank.transfer(0, 1, 300);
                        // tries to transfer 400 from account 1 to account 0 in thread 1
                        else
                            bank.transfer(1, 0, 400);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                }
                catch (InterruptedException e)
                {
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
    
}

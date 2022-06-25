package codaImpl;

import java.util.concurrent.*;
import coda.*;

public class CodaWrapperSem extends CodaWrapper{
    
    private Semaphore freeSpace;
    private Semaphore itemsAvailable;

    public CodaWrapperSem(Coda c) {
        super(c);

        freeSpace = new Semaphore(coda.getSize());
        itemsAvailable = new Semaphore(0);
    }

    public void insert(int e) {
        try {
            freeSpace.acquire();

            try {
                synchronized(coda) {
                    coda.insert(e);
                }
            } finally {
                itemsAvailable.release();
            }
        } catch(InterruptedException err) {
            err.printStackTrace();
        }
    }

    public int withdraw() {
        int x = 0;
        try {
            itemsAvailable.acquire();

            try {
                synchronized(coda) {
                    x = coda.withdraw();
                }
            } finally {
                freeSpace.release();
            }
        } catch(InterruptedException err) {
            err.printStackTrace();
        }

        return x;
    }

}

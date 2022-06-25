package codaImpl;

import coda.*;

public class CodaWrapperSynchr extends CodaWrapper {
    
    public CodaWrapperSynchr(Coda c) {
        super(c);
    }

    public void insert(int e) {
        synchronized(coda) {
            while(coda.full()) {
                try {
                    coda.wait();
                } catch(InterruptedException err) {
                    err.printStackTrace();
                }
            }

            coda.insert(e);
            coda.notifyAll();
        }
    }

    public int withdraw() {
        int x = 0;

        synchronized(coda) {
            while(coda.empty()) {
                try {
                    coda.wait();
                } catch(InterruptedException err) {
                    err.printStackTrace();
                }
            }

            x = coda.withdraw();
            coda.notifyAll();
        }

        return x;
    }
}

package tester;

import coda.*;
import codaImpl.*;

public class TesterProgram {
    
    /**
	 * @param args
	 */

     public static void main(String[] args) {

        Coda c = new CodaCircolare(4);
        Coda wrapper = new CodaWrapperLock(c);

        int n_threads = 50;
        WorkerThread[] workers = new WorkerThread[n_threads];

        int i = 0;
        for (i = 0; i < n_threads; i++) {
            if (i % 2 == 0) {
                workers[i] = new WorkerThread(wrapper, true);   // insert
            } else {
                workers[i] = new WorkerThread(wrapper, false);     // withdraw
            }
            workers[i].start();
        }

        for (i = 0; i < n_threads; i++) {
            try {
                workers[i].join();
            } catch(InterruptedException err) {
                err.printStackTrace();
            }
        }
     }
}

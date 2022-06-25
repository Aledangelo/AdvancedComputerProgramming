package codaImpl;

import coda.*;

public class CodaCircolare implements Coda {
    
    private int data[]; // array per memorizzare gli elementi

    private int size;
    private int nelem;

    // Testa e coda della coda circolare
    private int head;
    private int tail;

    public CodaCircolare(int s) {
        size = s;
        nelem = 0;
        data = new int[size];
        head = 0;
        tail = 0;
    }

    public boolean full() {
        if (nelem != size) {
            return false;
        } else {
            return true;
        }
    }

    public boolean empty() {
        if (nelem != 0) {
            return false;
        } else {
            return true;
        }
    }

    public int getSize() {
        return size;
    }

    // Le altre due funzioni
    public void insert(int e) {
        // Metto l'elemento alla posizione tail a meno che non sia uguale alla size, in quel caso lo metto alla posizione 0
        data[tail % size] = e;

        try {
            Thread.sleep(101 + (int)(Math.random() * 100)); // Sleep a cazzo
        } catch(InterruptedException err) {
            err.printStackTrace();
        }

        nelem = nelem + 1;
        System.out.println("Inserito l'elemento " + e + ". Elementi totali: " + nelem);
        tail = tail + 1;
    }

    public int withdraw() {
        int x = data[head % size];

        try {
            Thread.sleep(101 + (int)(Math.random() * 100));
        } catch (InterruptedException err) {
            err.printStackTrace();
        }

        nelem = nelem - 1;
        System.out.println("Prelevato: " + x + ". Elementi totali: " + nelem);
        head = head + 1;
        
        return x;
    }
}

package computeunit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Compute {
	
	private Semaphore maxThread;
	private PrintWriter pw;
	private Random rnd;
	
	public Compute(int maxThread, String pathFile) {
		this.maxThread = new Semaphore(maxThread);
		this.rnd = new Random();
		
		try {
			this.pw = new PrintWriter(new BufferedWriter(new FileWriter(pathFile, true)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getRes(String type, int a, int b) {
		int res = 0;
		
		int wtime = 1000 + this.rnd.nextInt(4001);
		try {
			Thread.sleep(wtime);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		try {
			this.maxThread.acquire();
			
			switch(type) {
				case "sum":
					res = a + b;
					System.out.println("[Compute] " + a + "+" + b + "=" + res);
					this.pw.println(a + "+" + b + "=" + res);
					this.pw.flush();
					break;
				case "mul":
					res = a * b;
					System.out.println("[Compute] " + a + "*" + b + "=" + res);
					this.pw.println(a + "*" + b + "=" + res);
					this.pw.flush();
					break;
				default:
					res = -1;
					break;
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.maxThread.release();
		}
		
		return res;
	}
}

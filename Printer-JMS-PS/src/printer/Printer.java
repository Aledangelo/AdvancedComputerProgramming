package printer;

public class Printer {
	public static void main(String[] args) {
		int port = Integer.valueOf(args[0]).intValue();
		
		PrinterImpl printer = new PrinterImpl("log.txt", port);
		printer.runSkeleton();
		
		System.out.println("[Printer] Listening on port: " + port);
	}
}

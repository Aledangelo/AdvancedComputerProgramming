package manager;

public class Manager {
	public static void main(String[] args) {
		ManagerImpl manager = new ManagerImpl(9000);
		manager.runSkeleton();
	}
}

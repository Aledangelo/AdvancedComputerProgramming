package server;

public class CounterImpl extends CounterSkeleton {
    private int count;

    public void setCount(String id, int value) {
        System.out.println("[Counter Impl] Count set to: " + value + " by client: " + id);
        count = value;
    }

    public int sum(int value) {
        System.out.println("[Counter Impl] Sum with: " + value);
        count = count + value;
        return count;
    }

    public int inc() {
        System.out.println("[Counter Impl] Increment");
        count = count + 1;
        return count;
    }
}

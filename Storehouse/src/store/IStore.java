package store;

public interface IStore {
    public void deposit(String item, int id);
    public int withdraw(String item);
}
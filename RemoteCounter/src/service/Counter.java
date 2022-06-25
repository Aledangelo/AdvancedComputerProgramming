package service;

public interface Counter {
    int inc();
    int sum(int value);
    void setCount(String id, int value);
}
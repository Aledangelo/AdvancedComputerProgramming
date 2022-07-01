package client;

import whiteboard.Shape;

public class Triangle implements Shape {
    private static final long serialVersionUID = 1L;

    public void draw() {
        System.out.println(" /\\ ");
        System.out.println("/__\\ ");
    }
}

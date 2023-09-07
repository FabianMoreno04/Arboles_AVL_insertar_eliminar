package models;

public class AVLNode {

    int data;
    int height;
    AVLNode left;
    AVLNode right;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1;
    }

    public int height() {
        return height;
    }
}

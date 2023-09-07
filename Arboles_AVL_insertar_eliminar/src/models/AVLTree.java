package models;

import java.awt.Color;
import java.awt.Graphics2D;

class AVLTree {
    private AVLNode root;

    public void insert(int data) {
        root = insertRecursive(root, data);
    }

    public void delete(int data) {
        root = deleteRecursive(root, data);
    }

    private AVLNode rotateLeft(AVLNode y) {
        AVLNode x = y.right;
        AVLNode T2 = x.left;

        x.left = y;
        y.right = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode rotateRight(AVLNode x) {
        AVLNode y = x.left;
        AVLNode T2 = y.right;

        y.right = x;
        x.left = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    public AVLNode getRoot() {
        return root;
    }

    private int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private AVLNode insertRecursive(AVLNode current, int data) {
        if (current == null) {
            return new AVLNode(data);
        }

        if (data < current.data) {
            current.left = insertRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = insertRecursive(current.right, data);
        } else {
            return current;
        }

        current.height = 1 + Math.max(height(current.left), height(current.right));

        return balance(current);
    }

    private AVLNode deleteRecursive(AVLNode current, int data) {
        if (current == null) {
            return current;
        }

        if (data < current.data) {
            current.left = deleteRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = deleteRecursive(current.right, data);
        } else {
            if (current.left == null || current.right == null) {
                AVLNode temp = (current.left != null) ? current.left : current.right;

                if (temp == null) {
                    temp = current;
                    current = null;
                } else {
                    current = temp;
                }
            } else {
                AVLNode temp = minValueNode(current.right);
                current.data = temp.data;
                current.right = deleteRecursive(current.right, temp.data);
            }
        }

        if (current == null) {
            return current;
        }

        current.height = 1 + Math.max(height(current.left), height(current.right));

        return balance(current);
    }

    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private AVLNode balance(AVLNode node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) >= 0) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        if (balance < -1) {
            if (getBalance(node.right) <= 0) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }
    public void drawTree(Graphics2D g2d, int x, int y, AVLNode node) {
        if (node != null) {
            int dx = 60; // Espacio horizontal entre nodos
            int dy = 60; // Espacio vertical entre niveles

            // Dibuja el subárbol izquierdo
            drawTree(g2d, x - dx, y + dy, node.left);

            // Dibuja el nodo actual
            g2d.setColor(Color.BLACK);
            g2d.fillOval(x - 15, y - 15, 30, 30);
            g2d.setColor(Color.WHITE);
            g2d.drawString(Integer.toString(node.data), x - 5, y + 5);

            // Dibuja el subárbol derecho
            drawTree(g2d, x + dx, y + dy, node.right);
        }
    }
}

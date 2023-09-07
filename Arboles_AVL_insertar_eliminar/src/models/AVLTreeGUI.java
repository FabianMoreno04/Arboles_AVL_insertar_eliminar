package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AVLTreeGUI extends JFrame {
    private AVLTree avlTree;
    private JTextField insertTextField;
    private JTextField deleteTextField;
    private JPanel treePanel;

    public AVLTreeGUI() {
        avlTree = new AVLTree();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AVL Tree Visualization");
        setSize(800, 600);

        JPanel controlPanel = new JPanel();
        insertTextField = new JTextField(10);
        JButton insertButton = new JButton("Insert");
        deleteTextField = new JTextField(10);
        JButton deleteButton = new JButton("Delete");

        controlPanel.add(new JLabel("Insert Node:"));
        controlPanel.add(insertTextField);
        controlPanel.add(insertButton);
        controlPanel.add(new JLabel("Delete Node:"));
        controlPanel.add(deleteTextField);
        controlPanel.add(deleteButton);

        treePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                avlTree.drawTree(g2d, getWidth() / 2, 30, avlTree.getRoot());
            }
        };

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(insertTextField.getText());
                    avlTree.insert(value);
                    treePanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AVLTreeGUI.this, "Invalid input. Please enter an integer.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(deleteTextField.getText());
                    avlTree.delete(value);
                    treePanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AVLTreeGUI.this, "Invalid input. Please enter an integer.");
                }
            }
        });

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(treePanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AVLTreeGUI frame = new AVLTreeGUI();
            frame.setVisible(true);
        });
    }
}
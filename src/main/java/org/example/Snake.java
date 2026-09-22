package org.example;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;


public class Snake extends JPanel {
    private boolean showSquare = true;
    private Image appleImage;

    private int x = 100;
    private int y = 100;


    public Snake() {
        setBackground(Color.BLACK);

         appleImage = new ImageIcon(
                 Objects.requireNonNull(getClass().getResource("/apple.png"))
        ).getImage();

        // Правая стрелка
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");

        getActionMap().put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x += 20;
                repaint();
            }
        });

        // Левая стрелка
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");

        getActionMap().put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x -= 20;
                repaint();
            }
        });

        // Клик мыши
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();

                repaint();
            }
        });
    }

    // g.fillRect(...);       // прямоугольник
    //g.fillOval(...);       // круг / овал
    //g.drawRect(...);       // контур прямоугольника
    //g.drawOval(...);       // контур круга
    //g.drawLine(...);       // линия
    //g.drawString(...);     // текст
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(appleImage, 100, 100, 40, 30,this);

        if (showSquare) {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, 20, 20);
            g.setColor(Color.RED);
            g.fillRect(150, 100, 20, 20);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Snake", 100, 70);
        }
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Test");

        Snake panel = new Snake();

        window.add(panel);
        window.setSize(500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
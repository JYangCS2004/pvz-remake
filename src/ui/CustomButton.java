package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {
    private int width;
    private int height;
    public CustomButton(GamePanel g) {
        this.width = 48 * 2;
        this.height = 48;

        setPreferredSize(new Dimension(width, height));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (g.getPlantInterface().enoughCards()) {
                    g.remove(CustomButton.this);
                    g.startGame();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(205,133,63));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.setColor(Color.white);
        g2.setFont(new Font("Serif", Font.BOLD, 15));
        g2.drawString("Let's Rock!", 10, getHeight() / 2 + 5);
    }
}

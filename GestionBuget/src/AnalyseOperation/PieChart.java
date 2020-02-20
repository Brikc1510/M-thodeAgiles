package AnalyseOperation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class PieChart extends JComponent {

    Part[] slices = {
        new Part(15, Color.yellow), new Part(30, Color.white), new Part(25, Color.blue), new Part(30, Color.red)
    };

    String mTitle;

    public PieChart(String title) {
        mTitle = title;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void paint(Graphics g) {
        drawPie((Graphics2D) g, getBounds(), slices);
    }

    void drawPie(Graphics2D g, Rectangle area, Part[] slices) {
        double total = 0.0D;
        for (int i = 0; i < slices.length; i++) {
            total += slices[i].value;
        }
        double curValue = 0.0D;
        int startAngle = 0;
                    int radius = area.width > area.height ? area.height : area.width;
            int margin = (int)(radius * 0.1);
        for (int i = 0; i < slices.length; i++) {
            startAngle = (int) (curValue * 360 / total);
            int arcAngle = (int) (slices[i].value * 360 / total);

            g.setColor(slices[i].color);
  
            g.fillArc(area.x + (int)(area.width / 2.0) - (int)((radius-margin) / 2.0), area.y, radius - margin, radius - margin, startAngle, arcAngle);
            curValue += slices[i].value;
        }
        
        g.setColor(Color.BLACK);
        g.drawString(mTitle, area.x + (area.width / 2) - (int)((mTitle.length() / 2.0) * (g.getFont().getSize() / 2.0)), area.y + radius - (int)(g.getFont().getSize() / 2.0));
    }
}

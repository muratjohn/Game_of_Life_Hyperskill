package life;

import javax.swing.*;
import java.awt.*;

class MyPanel extends JPanel {

    private boolean[][] world;
    private int dimension;
    private Color filledCellColor = Color.BLACK;
    private Color gridColor = Color.BLACK;

    {
        world = Universe.getWorld();
    }

    public void setWorld(boolean[][] world) {
        this.world = world;
        repaint();
    }

    public void setFilledCellColor(Color filledCellColor) {
        this.filledCellColor = filledCellColor;
    }

    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    // TODO: 5.02.2020
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dimension = 800;
        int size = (int) Math.ceil(dimension / (double) Universe.getSize());

        for (int i = 0; i < dimension; i += size) {
            for (int j = 0; j < dimension; j += size) {
                if (world[i / size][j / size]) {
                    g.setColor(filledCellColor);
                    g.fillRect(j, i, size, size);

                } else {
                    g.setColor(gridColor);
                    g.drawRect(j, i, size, size);
                }
            }
        }
    }
}
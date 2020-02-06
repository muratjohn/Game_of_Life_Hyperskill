package life;

import javax.swing.*;
import java.awt.*;

public class Controller {

    private Universe m;
    private GameOfLife v;
    private volatile boolean play = true;
    private int speed;
    private boolean changeFillColor;

    public Controller(Universe m, GameOfLife v) {
        this.m = m;
        this.v = v;
        initController();
    }

    void init() {
        speed = GameOfLife.getInitSpeed();
        do {
            while (!play) {
                Thread.onSpinWait();
            }
            v.getField().setWorld(Universe.getWorld());
            v.getGenerationLabel().setText("Generation #" + m.getCurrentGeneration());
            v.getAliveLabel().setText("Alive: " + m.getCurrentlyAlive());
            try {
                Thread.sleep(speed);
            } catch (InterruptedException ignored) {
            }

            m.nextGeneration();

        } while (true);

    }

    void initController() {
        v.getPlayToggleButton().addActionListener(e -> play = !play);
        v.getResetButton().addActionListener(e -> {
            int size;
            try {
                size = Math.abs(Integer.parseInt(v.getTextField().getText()));
            } catch (NumberFormatException ex) {
                size = Universe.getSize();
            }
            Universe.setSize(size);
            m.reset();
        });
        v.getGridColorRadioBtn().addActionListener(actionEvent -> changeFillColor = true);
        v.getFilledCellsColorRadioBtn().addActionListener(actionEvent -> changeFillColor = false);
        v.getSpeed().addChangeListener(changeEvent -> {
            JSlider source = (JSlider) changeEvent.getSource();
            if (!source.getValueIsAdjusting()) {
                speed = (int) source.getValue();
            }
        });

        v.getColorChooser().getSelectionModel().addChangeListener(changeEvent -> {
            Color color = v.getColorChooser().getColor();
            if (!changeFillColor) {
                v.getField().setFilledCellColor(color);
            } else {
                v.getField().setGridColor(color);
            }
        });

    }


}
package life;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.util.Hashtable;

public class GameOfLife extends JFrame {
    //View
    private JLabel generationLabel;
    private JLabel aliveLabel;
    private MyPanel field;
    private JTextField textField;
    private JToggleButton playToggleButton;
    private JButton resetButton;
    private JSlider speed;
    private JRadioButton filledCellsColorRadioBtn;
    private JRadioButton gridColorRadioBtn;
    static final int MIN_SPEED = 0;
    static final int MAX_SPEED = 2000;
    static final int INIT_SPEED = 100;
    private JColorChooser colorChooser;

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel statsPanel = new JPanel();
        statsPanel.setSize(150, 600);
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));

        playToggleButton = new JToggleButton("pause/resume", false);
        playToggleButton.setName("PlayToggleButton");

        resetButton = new JButton("restart");
        resetButton.setName("ResetButton");

        JLabel textLabel = new JLabel("Input new size for reset");
        textField = new JTextField(String.valueOf(Universe.getSize()), 4);
        textField.setMaximumSize(new Dimension(35, 20));

        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        generationLabel.setText("Yooo 0");

        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        aliveLabel.setText("'Supppp 0");

        JLabel sliderLabel = new JLabel("Speed mode", JLabel.LEFT);
        speed = new JSlider(JSlider.HORIZONTAL, MIN_SPEED, MAX_SPEED, INIT_SPEED);
        speed.setMajorTickSpacing(500);
        speed.setMinorTickSpacing(100);
        speed.setPaintTicks(true);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("Fast"));
        labelTable.put(500, new JLabel(".5s"));
        labelTable.put(1000, new JLabel("1s"));
        labelTable.put(1500, new JLabel("1.5s"));
        labelTable.put(2000, new JLabel("2s"));
        speed.setLabelTable(labelTable);
        speed.setPaintLabels(true);

        filledCellsColorRadioBtn = new JRadioButton("Filled Cell Color");
        filledCellsColorRadioBtn.setSelected(true);
        gridColorRadioBtn = new JRadioButton("Grid color");

        ButtonGroup group = new ButtonGroup();
        group.add(filledCellsColorRadioBtn);
        group.add(gridColorRadioBtn);

        colorChooser = new JColorChooser();
        colorChooser.setPreviewPanel(new JPanel());
        AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            if (!accp.getDisplayName().equals("HSV")) {
                colorChooser.removeChooserPanel(accp);
            }
        }

        statsPanel.add(playToggleButton);
        statsPanel.add(resetButton);
        statsPanel.add(textLabel);
        statsPanel.add(textField);
        statsPanel.add(generationLabel);
        statsPanel.add(aliveLabel);
        statsPanel.add(sliderLabel);
        statsPanel.add(speed);
        statsPanel.add(colorChooser);
        statsPanel.add(filledCellsColorRadioBtn);
        statsPanel.add(gridColorRadioBtn);

        add(statsPanel, BorderLayout.LINE_START);

        field = new MyPanel();
        field.setPreferredSize(new Dimension(800, 800));
        field.setMaximumSize(new Dimension(900, 900));
        add(field, BorderLayout.CENTER);
        pack();
        revalidate();

        setVisible(true);
    }

    public JTextField getTextField() {
        return textField;
    }

    public JRadioButton getGridColorRadioBtn() {
        return gridColorRadioBtn;
    }

    public JRadioButton getFilledCellsColorRadioBtn() {
        return filledCellsColorRadioBtn;
    }

    public JColorChooser getColorChooser() {
        return colorChooser;
    }

    public JToggleButton getPlayToggleButton() {
        return playToggleButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JSlider getSpeed() {
        return speed;
    }

    public MyPanel getField() {
        return field;
    }

    public JLabel getGenerationLabel() {
        return generationLabel;
    }

    public JLabel getAliveLabel() {
        return aliveLabel;
    }

    public static int getInitSpeed() {
        return INIT_SPEED;
    }
}
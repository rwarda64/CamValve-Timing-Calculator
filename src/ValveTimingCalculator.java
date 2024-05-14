/**
 * Valve Timing Calculator
 *
 * This Java program calculates and displays valve timing events for internal combustion engines.
 * The program provides a graphical user interface for inputting various parameters like intake and exhaust duration,
 * lobe separation, and valve advance, and computes the opening and closing timings for both intake and exhaust valves.

 * Created by Ramsin Warda
 */

import javax.swing.*;
import java.awt.*;

public class ValveTimingCalculator extends JFrame {
    private JTextField intakeDurationField, exhaustDurationField;
    private JTextField lobeSeparationField, advanceField;
    private JTextField intakeCenterlineField, intakeOpenField, intakeCloseField;
    private JTextField exhaustCenterlineField, exhaustOpenField, exhaustCloseField;
    private JTextField overlapField;

    public ValveTimingCalculator() {
        setTitle("Valve Timing Calculator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 5, 5));

        add(new JLabel("Intake Duration:"));
        intakeDurationField = new JTextField("224", 5);
        add(intakeDurationField);

        add(new JLabel("Exhaust Duration:"));
        exhaustDurationField = new JTextField("234", 5);
        add(exhaustDurationField);

        add(new JLabel("Lobe Separation:"));
        lobeSeparationField = new JTextField("112", 5);
        add(lobeSeparationField);

        add(new JLabel("Advance:"));
        advanceField = new JTextField("4", 5);
        add(advanceField);

        add(new JLabel("Intake Centerline:"));
        intakeCenterlineField = new JTextField("108", 5);
        add(intakeCenterlineField);

        add(new JLabel("Intake Open (BTDC):"));
        intakeOpenField = new JTextField("44", 5);
        add(intakeOpenField);

        add(new JLabel("Intake Close (ABDC):"));
        intakeCloseField = new JTextField("40", 5);
        add(intakeCloseField);

        add(new JLabel("Exhaust Centerline:"));
        exhaustCenterlineField = new JTextField("116", 5);
        add(exhaustCenterlineField);

        add(new JLabel("Exhaust Open (BBDC):"));
        exhaustOpenField = new JTextField("93", 5);
        add(exhaustOpenField);

        add(new JLabel("Exhaust Close (ATDC):"));
        exhaustCloseField = new JTextField("27", 5);
        add(exhaustCloseField);

        add(new JLabel("Overlap:"));
        overlapField = new JTextField("83", 5);
        add(overlapField);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> updateValues());
        add(updateButton);
    }

    private void updateValues() {
        try {
            int advance = Integer.parseInt(advanceField.getText());

            // Calculate the intake and exhaust timings
            int intakeOpen = Integer.parseInt(intakeOpenField.getText()) - advance;
            int intakeClose = Integer.parseInt(intakeCloseField.getText()) + advance;
            int exhaustOpen = Integer.parseInt(exhaustOpenField.getText()) - advance;
            int exhaustClose = Integer.parseInt(exhaustCloseField.getText()) + advance;

            // Set the corrected values back to the text fields
            intakeOpenField.setText(String.valueOf(intakeOpen));
            intakeCloseField.setText(String.valueOf(intakeClose));
            exhaustOpenField.setText(String.valueOf(exhaustOpen));
            exhaustCloseField.setText(String.valueOf(exhaustClose));

            // Calculate and set overlap
            int overlap = calculateOverlap(intakeOpen, exhaustClose);
            overlapField.setText(String.valueOf(overlap));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int calculateOverlap(int intakeOpen, int exhaustClose) {
        // Assuming overlap calculation is based on these events
        return Math.abs(intakeOpen) + Math.abs(exhaustClose);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ValveTimingCalculator frame = new ValveTimingCalculator();
            frame.setVisible(true);
        });
    }
}

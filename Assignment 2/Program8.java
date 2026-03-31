// Develop GUI-based Investment Calculator using Swing 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Main Class
public class Program8 extends JFrame implements ActionListener {

    // Components
    JTextField amountField, rateField, yearsField, resultField;
    JButton calculateBtn;

    Problem8() {
        setTitle("Investment Calculator");

        // Labels
        JLabel amountLabel = new JLabel("Principal Amount:");
        JLabel rateLabel = new JLabel("Rate of Interest (%):");
        JLabel yearsLabel = new JLabel("Time (Years):");
        JLabel resultLabel = new JLabel("Total Amount:");

        // Text Fields
        amountField = new JTextField(10);
        rateField = new JTextField(10);
        yearsField = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        // Button
        calculateBtn = new JButton("Calculate");
        calculateBtn.addActionListener(this);

        // Layout
        setLayout(new GridLayout(5, 2, 10, 10));

        add(amountLabel);
        add(amountField);

        add(rateLabel);
        add(rateField);

        add(yearsLabel);
        add(yearsField);

        add(resultLabel);
        add(resultField);

        add(new JLabel()); // empty space
        add(calculateBtn);

        // Frame settings
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Action when button is clicked
    public void actionPerformed(ActionEvent e) {
        try {
            double principal = Double.parseDouble(amountField.getText());
            double rate = Double.parseDouble(rateField.getText());
            double time = Double.parseDouble(yearsField.getText());

            // Simple Interest Formula
            double total = principal + (principal * rate * time) / 100;

            resultField.setText(String.valueOf(total));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
        }
    }

    public static void main(String[] args) {
        new Problem8();
    }
}
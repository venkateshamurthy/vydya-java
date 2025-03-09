package vydya.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
       // setLayout(new GridLayout(2,2));
        this.add(getCalculationPanel("Addition"));
        this.add(getCalculationPanel("Subtraction"));
        this.add(getCalculationPanel("Multiplication"));
        this.add(getCalculationPanel("Division"));
        pack();
        setVisible(true);
    }

    public JPanel getCalculationPanel(String operation) {
        //1. Create Panel and set border and title as operation
        JPanel panel = new JPanel();
        //panel.setBorder(BorderFactory.createTitledBorder(operation));

        //2. Make it a grid layout of 4 rows and 2 cols.
        panel.setLayout(new GridLayout(4, 2));

        //3. Create/Keep it ready Text fields for 2 inputs and another 1 for result
        JTextField num1Field = new JTextField(10);
        JTextField num2Field = new JTextField(10);
        JTextField resultField = new JTextField(10);
        resultField.setEditable(false);

        //4. Create/Keep it ready Button and action listener
        JButton button = new JButton(operation);
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setBorderPainted(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int n1 = Integer.parseInt(num1Field.getText());
                    int n2 = Integer.parseInt(num2Field.getText());
                    try {
                        //5. Using operation name get the actual operation done
                        int result = operate(operation, n1, n2);
                        resultField.setText(""+result);
                    } catch(ArithmeticException ae) {
                        resultField.setText("Cannot divide by zero");
                    }
                } catch (NumberFormatException nfe) {
                    resultField.setText("Invalid input/number format");
                }
            }
        });

        panel.add(new JLabel("Number 1:")); panel.add(num1Field);
        panel.add(new JLabel("Number 2:")); panel.add(num2Field);
        panel.add(new JLabel("Click button-->:")); panel.add(button);
        panel.add(new JLabel("Result:"));   panel.add(resultField);
        return panel;
    }

    private int operate(String operationName, int n1, int n2) {
        //In case if operationName passed is null just mae it empty so you dont throw null pointer exception
        if (operationName==null) {
            operationName = "";
        }
        // Simple switch case based on operation name
        switch(operationName.toLowerCase()) {
            case "add": case "addition": case "sum":
            case "+": return n1 + n2;

            case "subtract": case "subtraction": case "minus":
            case "-": return n1 - n2;

            case "multiply": case "multiplication": case "product":
            case "*": return n1 * n2;

            case "division": case "divide":
            case "/": return n1 / n2;
        }

        // if none of the above then its not a known operation
        throw new IllegalArgumentException("Unknown operation: " + operationName);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }
}
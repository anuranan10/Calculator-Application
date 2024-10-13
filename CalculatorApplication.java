import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApplication extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton[] numberButtons;
    private JButton addButton, subButton, mulButton, divButton, equalsButton, clearButton;
    private double num1, num2;
    private String operator;

    public CalculatorApplication() {
        // Set up the frame
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input field
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setEditable(false);
        add(inputField, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        // Create number buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            buttonPanel.add(numberButtons[i]);
        }

        // Operation buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");

        // Add action listeners
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        equalsButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Add operation buttons to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(subButton);
        buttonPanel.add(mulButton);
        buttonPanel.add(divButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Number button pressed
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                inputField.setText(inputField.getText() + i);
                return;
            }
        }

        // Operator button pressed
        if (e.getSource() == addButton) { setOperator("+"); }
        else if (e.getSource() == subButton) { setOperator("-"); }
        else if (e.getSource() == mulButton) { setOperator("*"); }
        else if (e.getSource() == divButton) { setOperator("/"); }
        else if (e.getSource() == equalsButton) { calculateResult(); }
        else if (e.getSource() == clearButton) { inputField.setText(""); }
    }

    private void setOperator(String op) {
        operator = op;
        num1 = Double.parseDouble(inputField.getText());
        inputField.setText("");
    }

    private void calculateResult() {
        num2 = Double.parseDouble(inputField.getText());
        double result = 0;

        switch (operator) {
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "*" -> result = num1 * num2;
            case "/" -> {
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    inputField.setText("Error");
                    return;
                }
            }
        }
        inputField.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        CalculatorApplication calculator = new CalculatorApplication();
        calculator.setVisible(true);
    }
}
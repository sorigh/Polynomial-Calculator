package display;

import logic.Operation;
import dataModel.Polinom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JDialog implements ActionListener {

    //polynomials
    Polinom pp1 = new Polinom();
    Polinom pp2 = new Polinom();
    Polinom ppRez = new Polinom();
    Polinom ppRez2 = new Polinom();
    Polinom[] rez;
    //panel
    private final JPanel mainPanel;
    //text fields
    private JTextField inputTextField1;
    private JTextField inputTextField2;
    private JTextArea resultArea;

    //combo box
    private JComboBox<String> operationComboBox;

    //buttons
    private JButton calculateButton;
    private JButton resetButton;
    private JButton informationButton;

    public Display(Frame parent){
        super(parent);
        setTitle("Polynomial Calculator");
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(410, 450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        createContent();
        mainPanel.setBackground(new Color(87,108,133));
        mainPanel.setLayout(null);

        calculateButton.addActionListener(ae -> {

            pp1.clear();
            pp2.clear();
            ppRez.clear();
            ppRez2.clear();
            if (!pp1.processPolynomial(inputTextField1.getText()))
            {
                JOptionPane.showMessageDialog(null, "Wrong input!", "Something went wrong", JOptionPane.ERROR_MESSAGE);
            }
            pp2.processPolynomial(inputTextField2.getText());
            chooseOperation(operationComboBox.getSelectedIndex());
        });
        resetButton.addActionListener(ae -> reset());
        informationButton.addActionListener(ae -> JOptionPane.showMessageDialog(null, "accepted term with the format:\n [coefficient]x^[exponent] ", "Required format", JOptionPane.INFORMATION_MESSAGE));

        this.setVisible(true);
    }

    private void createContent() {

        //text fields
        inputTextField1 = new JTextField();
        inputTextField1.setFont(new Font("Cambria Math",Font.BOLD,15));
        inputTextField1.setBounds(20, 80, 200, 30);


        inputTextField2 = new JTextField();
        inputTextField2.setFont(new Font("Cambria Math",Font.BOLD,15));
        inputTextField2.setBounds(20, 160, 200, 30);

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Cambria Math",Font.BOLD,15));
        resultArea.setBounds(20, 240, 350, 100);
        resultArea.setEditable(false);
        resultArea.setWrapStyleWord(true);

        //labels
        JLabel titleLabel = new JLabel();
        titleLabel.setFont(new Font("Cambria Math",Font.BOLD,20));
        titleLabel.setText("Polynomial Calculator");
        titleLabel.setBounds(100, 10, 300, 30);
        titleLabel.setForeground(new Color(223, 225, 229));

        JLabel inputLabel1 = new JLabel();
        inputLabel1.setFont(new Font("Cambria Math",Font.BOLD,15));
        inputLabel1.setText("Polynomial 1:");
        inputLabel1.setBounds(20, 60, 100, 20);
        inputLabel1.setForeground(new Color(223, 225, 229));


        JLabel inputLabel2 = new JLabel();
        inputLabel2.setFont(new Font("Cambria Math",Font.BOLD,15));
        inputLabel2.setText("Polynomial 2:");
        inputLabel2.setBounds(20, 140, 100, 20);
        inputLabel2.setForeground(new Color(223, 225, 229));


        JLabel resultLabel = new JLabel();
        resultLabel.setFont(new Font("Cambria Math",Font.BOLD,15));
        resultLabel.setText("Result:");
        resultLabel.setBounds(20, 210, 100, 20);
        resultLabel.setForeground(new Color(223, 225, 229));

        //combo box
        String[] operations = {"Operation","Add", "Subtract", "Multiply", "Divide", "Derive", "Integrate"};
        operationComboBox = new JComboBox<>(operations);
        operationComboBox.setBackground(new Color(93,64,27));
        operationComboBox.setForeground(new Color(197,198,201));
        operationComboBox.setFont(new Font("Cambria Math",Font.BOLD,15));
        operationComboBox.setSelectedItem(1);
        operationComboBox.setBounds(270, 120, 100, 30);

        //button
        calculateButton = new JButton();
        calculateButton.setFont(new Font("Cambria Math",Font.BOLD,15));
        calculateButton.setText("Calculate");
        calculateButton.setBounds(270, 200, 100, 30);
        calculateButton.setBackground(new Color(18,38,63));
        calculateButton.setForeground(new Color(197,198,201));

        resetButton = new JButton();
        resetButton.setFont(new Font("Cambria Math",Font.BOLD,15));
        resetButton.setText("Reset");
        resetButton.setBounds(270, 350, 100, 30);
        resetButton.setBackground(new Color(18,38,63));
        resetButton.setForeground(new Color(197,198,201));

        informationButton = new JButton();
        informationButton.setFont(new Font("Cambria Math",Font.BOLD,15));
        informationButton.setText("Info");
        informationButton.setBounds(20, 350, 100, 30);
        informationButton.setBackground(new Color(18,38,63));
        informationButton.setForeground(new Color(197,198,201));


        //text fields
        mainPanel.add(inputTextField1);
        mainPanel.add(inputTextField2);
        mainPanel.add(resultArea);


        //labels
        mainPanel.add(titleLabel);
        mainPanel.add(inputLabel1);
        mainPanel.add(inputLabel2);
        mainPanel.add(resultLabel);

        //combo box
        mainPanel.add(operationComboBox);

        //button
        mainPanel.add(calculateButton);
        mainPanel.add(resetButton);
        mainPanel.add(informationButton);

    }

    public void reset(){
        pp1.clear();
        pp2.clear();
        ppRez.clear();
        ppRez2.clear();
        operationComboBox.setSelectedItem(1);
        inputTextField1.setText("");
        inputTextField2.setText("");
        resultArea.setText("");
    }
    public void chooseOperation(int selectedIndex){
        ppRez.clear();
        ppRez2.clear();
        switch (selectedIndex) {
            case 1: //"Add"
                ppRez = Operation.add(pp1,pp2);
                break;
            case 2: //"Subtract"
                ppRez = Operation.subtract(pp1,pp2);
                break;
            case 3: //"Multiply"
                ppRez = Operation.multiplication(pp1,pp2);
                break;
            case 4:// "Divide"
                try {
                    rez = Operation.division(pp1, pp2);
                }catch (ArithmeticException e) {
                JOptionPane.showMessageDialog(null, "No division by 0 ", "Division by 0", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 5:// "Derive"
                ppRez = Operation.derivative(pp1);
                ppRez2 = Operation.derivative(pp2);

                break;
            case 6: //"Integrate"
                ppRez = Operation.integral(pp1);
                ppRez2 = Operation.integral(pp2);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Select a valid operation!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (selectedIndex > 4)
            resultArea.setText("\n" + ppRez.display() +"\n" + ppRez2.display());
        else{
            if (selectedIndex == 4)
            {
                //[0] e quotient, [1] remainder
                resultArea.setText("\nQuotient: " + rez[0].display() +"\nRemainder: " + rez[1].display());
            }
            else {
                resultArea.setText("\n" + ppRez.display());
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

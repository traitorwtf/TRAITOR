package Simple_Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by traitorwtf on 20.03.2017.
 *
 */
public class Calculator {
    JFrame frame;
    JButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    JButton plusButton, minusButton, divisionButton, multiplicButton, equalButton, clearButton, dotButton;
    JTextField score;
    String value = "";
    Double value1, value2;
    Double result;
    int resultInt;
    boolean plus, minus, division, multiplication, fraction;


    public static void main(String[] args) {
        new Calculator().go();
    }

    public void go(){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(210,240);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();

        score = new JTextField(8);
        button0 = new JButton("0");
        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");

        plusButton = new JButton("+");
        minusButton = new JButton("-");
        divisionButton = new JButton("/");
        multiplicButton = new JButton("*");
        equalButton = new JButton("      =      ");
        clearButton = new JButton("C");
        dotButton = new JButton(".");

        Font font = new Font("Arial", Font.BOLD, 24);
        score.setFont(font);

        button0.addActionListener(new button0Listener());
        button1.addActionListener(new button1Listener());
        button2.addActionListener(new button2Listener());
        button3.addActionListener(new button3Listener());
        button4.addActionListener(new button4Listener());
        button5.addActionListener(new button5Listener());
        button6.addActionListener(new button6Listener());
        button7.addActionListener(new button7Listener());
        button8.addActionListener(new button8Listener());
        button9.addActionListener(new button9Listener());

        clearButton.addActionListener(new clearButtonListener());
        dotButton.addActionListener(new dotButtonListener());
        plusButton.addActionListener(new plusButtonListener());
        minusButton.addActionListener(new minusButtonListener());
        multiplicButton.addActionListener(new multiplButtonListener());
        divisionButton.addActionListener(new divisionButtonListener());
        equalButton.addActionListener(new equalButtonListener());

        panel.add(score);
        panel.add(button7);
        panel.add(button8);
        panel.add(button9);
        panel.add(divisionButton);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(multiplicButton);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(minusButton);
        panel.add(clearButton);
        panel.add(button0);
        panel.add(dotButton);
        panel.add(plusButton);
        panel.add(equalButton);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }
    // НАЧАЛО ЛИСТЕНЕРОВ С КНОПКАМИ 0-9 и Clear
    private class button0Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value = value+"0";
            score.setText(value);
        }
    }

    private class button1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value = value+"1";
            score.setText(value);
        }
    }

    private class button2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value = value+"2";
            score.setText(value);
        }
    }

    private class button3Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value = value+"3";
            score.setText(value);
        }
    }

    private class button4Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value = value+"4";
            score.setText(value);
        }
    }

    private class button5Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value = value+"5";
            score.setText(value);
        }
    }

    private class button6Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value = value+"6";
            score.setText(value);
        }
    }

    private class button7Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value = value+"7";
            score.setText(value);
        }
    }

    private class button8Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value = value+"8";
            score.setText(value);
        }
    }

    private class button9Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value = value+"9";
            score.setText(value);
        }
    }

    private class clearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value = "";
            score.setText(value);


            fraction = false;
            plus = false;
            minus = false;
            multiplication = false;
            division = false;
        }
    }

    // НАЧАЛО ЛИСТЕНЕРОВ С +, -, /, *, = И ТОЧКОЙ.

    private class dotButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value = value+".";
            fraction = true;
            score.setText(value);
        }
    }

    private class plusButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value1 = Double.parseDouble(value);
            value = "";
            score.setText(value);

            fraction = false;
            plus = true;
            minus = false;
            multiplication = false;
            division = false;
        }
    }

    private class minusButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value1 = Double.parseDouble(value);
            value = "";
            score.setText(value);

            fraction = false;
            plus = false;
            minus = true;
            multiplication = false;
            division = false;
        }
    }

    private class multiplButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value1 = Double.parseDouble(value);
            value = "";
            score.setText(value);

            fraction = false;
            plus = false;
            minus = false;
            multiplication = true;
            division = false;
        }
    }

    private class divisionButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value1 = Double.parseDouble(value);
            value = "";
            score.setText(value);

            fraction = false;
            plus = false;
            minus = false;
            multiplication = false;
            division = true;
        }
    }

    private class equalButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            value2 = Double.parseDouble(value);

            if (plus) result = value1+value2;
            if (minus) result = value1-value2;
            if (multiplication) result = value1*value2;
            if (division) result = value1/value2;
            result = round(result, 3);

            if (result%1 == 0) {
                resultInt = result.intValue();
                String resInt = "" + resultInt;
                score.setText(resInt);
            } else {
                score.setText(result.toString());
            }
            value = result.toString();

            fraction = false;
            plus = false;
            minus = false;
            multiplication = false;
            division = true;
        }
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

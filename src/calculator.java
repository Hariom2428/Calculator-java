import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;
import javax.swing.border.LineBorder;

public class Calculator {

    int height = 540;
    int width = 360;

    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGray = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(255, 149, 0);





    String[] buttonValues = {
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };

    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};


    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    //A-B,A*B,A+B,A/B
    String A="0";
    String operator=null;
    String B=null;



    Calculator() {

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Display
        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);

        // Button panel
        buttonPanel.setLayout(new GridLayout(5, 4));
        buttonPanel.setBackground(customBlack);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Create buttons
        for (int i = 0; i < buttonValues.length; i++) {

            JButton button = new JButton();
            String buttonValue = buttonValues[i];

            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusPainted(false);
            button.setBorder(new LineBorder(customBlack, 1));

            // Button colors
            if (Arrays.asList(topSymbols).contains(buttonValue)) {

                button.setBackground(customLightGray);
                button.setForeground(customBlack);

            } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {

                button.setBackground(customOrange);
                button.setForeground(Color.WHITE);

            } else {

                button.setBackground(customDarkGray);
                button.setForeground(Color.WHITE);
            }

            buttonPanel.add(button);

            // Button action
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    JButton sourceButton = (JButton) e.getSource();
                    String buttonValue = sourceButton.getText();

                    if (Arrays.asList(topSymbols).contains(buttonValue)) {

                        // AC, +/-, %
                        if(buttonValue=="AC"){
                            clearAll();
                            displayLabel.setText("0");
                        } else if (buttonValue=="+/-") {
                            double numdisplay=Double.parseDouble(displayLabel.getText());
                            numdisplay *=-1;
                            displayLabel.setText(removeZero(numdisplay));
                        } else if (buttonValue=="%") {
                            double numdisplay=Double.parseDouble(displayLabel.getText());
                            numdisplay /=100;
                            displayLabel.setText(removeZero(numdisplay));
                        }

                    } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {

                        // ÷, ×, -, +, =

                    } else {

                        if (buttonValue.equals(".")) {

                            if(!displayLabel.getText().contains(buttonValue)){
                                displayLabel.setText(
                                        displayLabel.getText() + buttonValue
                                );
                            }

                        } else if ("0123456789".contains(buttonValue)) {

                            if (displayLabel.getText().equals("0")) {

                                displayLabel.setText(buttonValue);

                            } else {

                                displayLabel.setText(
                                        displayLabel.getText() + buttonValue
                                );
                            }
                        }
                    }
                }
            });
        }

        // Make the frame visible AFTER adding all components
        frame.setVisible(true);
    }
    void clearAll(){
        A="0";
        operator=null;
        B=null;

    }

    String removeZero(double numDisplay){
        if(numDisplay%1==0){
            return Integer.toString((int) numDisplay );
        }
        return  Double.toString(numDisplay);
    }
}
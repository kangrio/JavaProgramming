package inputscanner;

import javax.swing.JOptionPane;

public class BMR {

    public static void Bmg() {
        String name, gender = "";
        double height, weight, BMR = 0;
        int age, sex;
        String[] genderOP = {"Male", "Female"};

        String input = JOptionPane.showInputDialog(null, "Enter your name", "Input", JOptionPane.INFORMATION_MESSAGE);
        name = input;
        sex = JOptionPane.showOptionDialog(null, "Select your gender", "Gender",JOptionPane.DEFAULT_OPTION , JOptionPane.INFORMATION_MESSAGE, null, genderOP,genderOP[0]);
        input = JOptionPane.showInputDialog(null, "Enter your weight (kg)", "Input", JOptionPane.INFORMATION_MESSAGE);
        weight = Double.parseDouble(input);
        input = JOptionPane.showInputDialog(null, "Enter your height(cm)", "Input", JOptionPane.INFORMATION_MESSAGE);
        height = Double.parseDouble(input);
        input = JOptionPane.showInputDialog(null, "Enter your age", "Input", JOptionPane.INFORMATION_MESSAGE);
        age = Integer.parseInt(input);

        switch (sex) {
            case 0:
                BMR = 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
                gender = "Male";
                break;
            case 1:
                BMR = 665 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
                gender = "Female";
                break;
        }

        JOptionPane.showMessageDialog(null, "Name = " + name + "\nSex = " + gender + "\nWeight = "
                + weight + "\nHeight = " + height + "\nAge = " + age + "\nBMR = " + BMR);

    }

}

package gradecal;

import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;

public class GradeCal {

    public static ArrayList<String> name = new ArrayList(), surname = new ArrayList(), grade = new ArrayList();
    public static ArrayList<Double> midtest = new ArrayList(), finaltest = new ArrayList(),
            quiz = new ArrayList(), totalscore = new ArrayList();

    public static File ScoreFile = new File("d:/myscore.txt");

    //ป้อนข้อมูล
    public static void inputData() {

        readFile();

        int loop = 1;

        while (loop == 1) {

            String data = "";
            int i = 1;
            do {
                boolean isNum = false;
                String title = (i == 1) ? "ชื่อ" : (i == 2) ? "นามสกุล" : (i == 3) ? "คะแนนมิดเทอม" : (i == 4) ? "คะแนนปลายภาค" : "คะแนนเก็บ";

                if (i == 1 || i == 2) {
                    //ป้อน ชื่อ นามสกุล
                    data = JOptionPane.showInputDialog(null, "ป้อน " + title + " : ", "ป้อนข้อมูล", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    while (!isNum) {
                        //ป้อนคะแนน
                        data = JOptionPane.showInputDialog(null, "ป้อน " + title + " : ", "ป้อนข้อมูล", JOptionPane.INFORMATION_MESSAGE);
                        //เช็ดว่าเป็นตัวเลขไหม
                        if (data.matches("\\d+")) {
                            isNum = !isNum;
                        } else {
                            JOptionPane.showMessageDialog(null, "ป้อน " + title + " ใหม่");
                        }
                    }

                }

                if (!(data.equals(""))) {   //ข้อมูลที่ป้อนว่างไหม
                    switch (i) {

                        case 1:
                            name.add(data);
                            break;

                        case 2:
                            surname.add(data);
                            break;

                        case 3:
                            midtest.add(Double.parseDouble(data));
                            break;

                        case 4:
                            finaltest.add(Double.parseDouble(data));
                            break;

                        case 5:
                            quiz.add(Double.parseDouble(data));
                            break;

                    }

                    i++;
                    data = "";
                } else {    //ถ้าว่างให้ป้อนไหม่
                    JOptionPane.showMessageDialog(null, "ป้อน " + title + " ใหม่");

                }

            } while (i != 6);

            loop = 2;
            data = JOptionPane.showInputDialog(null, "ป้อนข้อมูลอีกไหม ?\n"
                    + "กด 1 ป้อนข้อมูลคนต่อไป\n"
                    + "กด 2 ไม่");

            if (!(data.equals(""))) {
                loop = Integer.parseInt(data);
            }
        }

        writeFile(1);

    }

    //อ่านไฟล์
    public static void readFile() {

        name.clear();
        surname.clear();
        midtest.clear();
        finaltest.clear();
        quiz.clear();
        totalscore.clear();
        grade.clear();

        if (ScoreFile.canExecute()) {
            try {
                int i = 0;
                String G;
                Scanner sc = new Scanner(ScoreFile);

                while (sc.hasNext()) {
                    name.add(sc.next());
                    surname.add(sc.next());
                    midtest.add(Double.parseDouble(sc.next()));
                    finaltest.add(Double.parseDouble(sc.next()));
                    quiz.add(Double.parseDouble(sc.next()));
                    totalscore.add(midtest.get(i) + finaltest.get(i) + quiz.get(i));
                    G = (totalscore.get(i) > 80) ? "A" : (totalscore.get(i) > 70) ? "B" : (totalscore.get(i) > 60) ? "C" : (totalscore.get(i) > 50) ? "D" : "F";
                    grade.add(G);
                    i++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "ไม่พบไฟล์ข้อมูล");
        }

    }

    //เขียนไฟล์
    public static void writeFile(int op) {

        if (op == 1) {
            try {

                PrintStream ps = new PrintStream("d:/myscore.txt");

                for (int i = 0; i < name.size(); i++) {
                    ps.println(name.get(i) + " " + surname.get(i) + " " + midtest.get(i) + " " + finaltest.get(i) + " " + quiz.get(i));
                }
                ps.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            try {

                PrintStream ps = new PrintStream("d:/mygrade.txt");

                for (int i = 0; i < name.size(); i++) {
                    ps.println(name.get(i) + " " + surname.get(i) + " " + totalscore.get(i) + " " + grade.get(i));
                }
                ps.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    //คำนวณเกรด
    public static void calculateGrade() {
        readFile();
        String G = "unknow";
        for (int i = 0; i < name.size(); i++) {
            totalscore.add(midtest.get(i) + finaltest.get(i) + quiz.get(i));
            G = (totalscore.get(i) > 80) ? "A" : (totalscore.get(i) > 70) ? "B" : (totalscore.get(i) > 60) ? "C" : (totalscore.get(i) > 50) ? "D" : "F";
            grade.add(G);
        }

        writeFile(2);

    }

    //แสดงข้อมูล
    public static void displayGrade() {

        readFile();
        if (ScoreFile.canExecute()) {
            for (int i = 0; i < name.size(); i++) {
                System.out.println("ชื่อ : " + name.get(i) + "\n" + "นามสกุล : " + surname.get(i) + "\n" + "คะแนนมิดเทอม : " + midtest.get(i) + "\n"
                        + "คะแนนปลายภาค : " + finaltest.get(i) + "\n" + "คะแนนเก็บ : " + quiz.get(i) + "\n" + "คะแนนรวม : " + totalscore.get(i) + "\n" + "เกรด : " + grade.get(i) + "\n");
            }
            System.out.print("\n\n\n");
        }

    }

    /*โปรแกรมหลัก*/
    public static void main(String[] args) {
        String menu;

        do {

            menu = JOptionPane.showInputDialog(null, "เลือกเมนู\n1.ป้อนข้อมูล\n2.คำนวณเกรด\n3.แสดงข้อมูล\nกด \"Q\" เพื่อออกจากโปรแกรม", "Menu", JOptionPane.INFORMATION_MESSAGE);

            switch (menu.toLowerCase()) {

                case "1":
                    inputData();
                    break;

                case "2":
                    calculateGrade();
                    break;

                case "3":
                    displayGrade();
                    break;

                case "q":
                    JOptionPane.showMessageDialog(null, "Have a nice day (:)", "Bye By ...", JOptionPane.PLAIN_MESSAGE);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Try again!!!", "Alert", JOptionPane.WARNING_MESSAGE);
                    break;

            }

        } while (!(menu.equalsIgnoreCase("q")));

    }

}

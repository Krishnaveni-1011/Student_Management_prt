package com.gqt.std;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class StudentManagement extends JFrame implements ActionListener {

    JLabel l1, l2, l3;
    JTextField t1, t2;
    JTextArea area;
    JButton saveBtn, viewBtn;

    StudentManagement() {

        setTitle("Student Record Management");
        setSize(500, 500);
        setLayout(null);

        l1 = new JLabel("Student Name:");
        l1.setBounds(50, 30, 120, 30);
        add(l1);

        t1 = new JTextField();
        t1.setBounds(180, 30, 200, 30);
        add(t1);

        l2 = new JLabel("Marks:");
        l2.setBounds(50, 80, 120, 30);
        add(l2);

        t2 = new JTextField();
        t2.setBounds(180, 80, 200, 30);
        add(t2);

        saveBtn = new JButton("Save");
        saveBtn.setBounds(80, 140, 120, 40);
        add(saveBtn);

        viewBtn = new JButton("View Records");
        viewBtn.setBounds(240, 140, 140, 40);
        add(viewBtn);

        area = new JTextArea();
        JScrollPane sp = new JScrollPane(area);
        sp.setBounds(50, 220, 380, 180);
        add(sp);

        saveBtn.addActionListener(this);
        viewBtn.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == saveBtn) {

            String name = t1.getText();
            int marks = Integer.parseInt(t2.getText());

            String grade;

            // Conditional Statements
            if (marks >= 90)
                grade = "A";
            else if (marks >= 75)
                grade = "B";
            else if (marks >= 50)
                grade = "C";
            else
                grade = "FAIL";

            try {

                FileWriter fw =
                        new FileWriter("students.txt", true);

                fw.write(name + "," + marks +
                        "," + grade + "\n");

                fw.close();

                JOptionPane.showMessageDialog(this,
                        "Record Saved Successfully");

                t1.setText("");
                t2.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage());
            }
        }

        if (e.getSource() == viewBtn) {

            try {

                BufferedReader br =
                        new BufferedReader(
                                new FileReader("students.txt"));

                String line;
                String output = "";

                output += "NAME\tMARKS\tGRADE\n";
                output += "---------------------------------\n";

                // Loop
                while ((line = br.readLine()) != null) {

                    String data[] = line.split(",");

                    output += data[0] + "\t"
                            + data[1] + "\t"
                            + data[2] + "\n";
                }

                br.close();

                area.setText(output);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new StudentManagement();
    }
}

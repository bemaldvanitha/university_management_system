package com.company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.company.authentication.frame2;
import static com.company.authentication.frame;

public class authCreate {
    public JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPasswordField passwordField1;
    private JTextField textField4;
    private JButton cancelButton1;
    private JButton signupAsLectureButton;
    private JButton signupAsStudentButton;
    private JButton backButton;

    //private JFrame frame;

    public authCreate() {
        cancelButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        signupAsLectureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textField1.getText().toString().isEmpty() && !textField2.getText().toString().isEmpty() && !textField3.getText().toString().isEmpty() && !textField4.getText().toString().isEmpty() && !passwordField1.getText().toString().isEmpty() ) {
                    if(!textField2.getText().toString().trim().equals(passwordField1.getText().toString().trim()) && !textField4.getText().toString().trim().equals(passwordField1.getText().toString().trim())) {
                        if(passwordStrength()) {
                            String id = textField1.getText().toString().trim();
                            String name = textField2.getText().toString().trim();
                            String age1 = textField3.getText().toString().trim();
                            int age = 0;
                            try {
                                age = Integer.valueOf(age1);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame2, "age must be number");
                            }
                            String password = passwordField1.getText().toString().trim();
                            String userName = textField4.getText().toString().trim();
                            try {
                                createLectureAccount(id, name, age, password, userName);
                                JOptionPane.showMessageDialog(frame2,"account create susscfully we use md5 encrption for security");
                            } catch (NoSuchAlgorithmException ex) {
                                ex.printStackTrace();
                            }
                            clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(frame2,"password strength is low please add upper case lower case number");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frame2,"user name and password cannot be same");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(frame2,"all fields must be full");
                }
            }
        });
        signupAsStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textField1.getText().toString().isEmpty() && !textField2.getText().toString().isEmpty() && !textField3.getText().toString().isEmpty() && !textField4.getText().toString().isEmpty() && !passwordField1.getText().toString().isEmpty() ) {
                    if(!textField2.getText().toString().trim().equals(passwordField1.getText().toString().trim()) && !textField4.getText().toString().trim().equals(passwordField1.getText().toString().trim())) {
                        if(passwordStrength()) {
                            String id = textField1.getText().toString().trim();
                            String name = textField2.getText().toString().trim();
                            String age1 = textField3.getText().toString().trim();
                            int age = 0;
                            try {
                                age = Integer.valueOf(age1);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame2, "age must be number");
                            }
                            String password = passwordField1.getText().toString().trim();
                            String userName = textField4.getText().toString().trim();
                            try {
                                createStudentAccount(id, name, age, password, userName);
                                JOptionPane.showMessageDialog(frame2,"account create susscfully we use md5 seccurity for safe");
                            } catch (NoSuchAlgorithmException ex) {
                                ex.printStackTrace();
                            }
                            clear();
                        }
                        else{
                            JOptionPane.showMessageDialog(frame2,"password strength is low please add upper case lower case number");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frame2,"userName and password can not be same");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame2,"all fields must full");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.setVisible(false);

                frame = new JFrame("Student managment System");
                frame.setContentPane(new authentication().panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(1200,500);
                frame.setVisible(true);
            }
        });
    }

    public void clear(){
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        passwordField1.setText("");
    }

    public void createStudentAccount(String id,String name,int age,String password,String userName) throws NoSuchAlgorithmException {
        SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        String pass = encrepter(password);

        Student student = new Student(id,name,age,userName,pass);
        session.save(student);
        session.getTransaction().commit();
        //session.close();
        //sessionFactory.close();
    }

    public void createLectureAccount(String id,String name,int age,String password,String userName) throws NoSuchAlgorithmException {
        SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        String pass = encrepter(password);

        Lecture lecture = new Lecture(id,name,age,userName,pass);
        session.save(lecture);
        session.getTransaction().commit();
        //session.close();
        //sessionFactory.close();
    }

    public boolean passwordStrength(){
        String pass = passwordField1.getText().toString().trim();
        int s1= 0,s2=0,s4=0;
        if(pass.length()<6){
            return false;
        }
        for(int i=0;i<pass.length();i++){
            if(Character.isUpperCase(pass.charAt(i))){
                s1=1;
            }
            else if(Character.isLowerCase(pass.charAt(i))){
                s2=1;
            }
            else if(Character.isDigit(pass.charAt(i))){
                s4=1;
            }
        }
        if(s1!=0 && s2!=0 && s4!=0){
            return true;
        }
        return false;
    }

    public String encrepter(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        //System.out.println(sb.toString());
        return  sb.toString();
    }
}

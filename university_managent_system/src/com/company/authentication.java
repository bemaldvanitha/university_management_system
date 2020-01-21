package com.company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.company.Loged.logged_id;
import  static com.company.Loged.category;

public class authentication {
    public JPanel panel;
    private JButton studentButton;
    private JButton lectureButton;
    private JButton signInButton;
    private JButton signUp;
    private JButton signInButton1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel label1;
    private JLabel label2;
    private JButton signInLecture;
    private JButton signInStudent;
    private JButton cancel;

    public static JFrame frame;
    public static JFrame frame2,frame3;
    private static JPanel panel1;

    public authentication() {


        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame2 = new JFrame("Student managment System Sign Up");
                frame2.setContentPane(new authCreate().panel1);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.pack();
                frame2.setSize(1200,500);
                frame2.setVisible(true);
            }
        });
        signInStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textField1.getText().toString().trim().isEmpty() && !passwordField1.getText().toString().trim().isEmpty()) {
                    String username = textField1.getText().toString().trim();
                    String password = passwordField1.getText().toString().trim();
                    boolean au = false;
                    try {
                        au = SignInStudent(username, password);
                        clear();
                    } catch (NoSuchAlgorithmException ex) {
                        ex.printStackTrace();
                    }
                    if(au){
                        JOptionPane.showMessageDialog(frame,"log in sucefullly");
                        frame.setVisible(false);

                        frame3 = new JFrame("Logged");
                        frame3.setContentPane(new Loged().panel4);
                        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame3.pack();
                        frame3.setSize(1200,500);
                        frame3.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(frame,"Account not found please check user_name password");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame,"all fileds must filled");
                }
            }
        });
        signInLecture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textField1.getText().toString().trim().isEmpty() && !passwordField1.getText().toString().trim().isEmpty()) {
                    String username = textField1.getText().toString().trim();
                    String password = passwordField1.getText().toString().trim();
                    boolean au = false;
                    try {
                        au = SignInLecture(username, password);
                        clear();
                    } catch (NoSuchAlgorithmException ex) {
                        ex.printStackTrace();
                    }
                    if(au){
                        JOptionPane.showMessageDialog(frame,"log in sucefullly");
                        frame.setVisible(false);

                        frame3 = new JFrame("Logged");
                        frame3.setContentPane(new Loged().panel4);
                        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame3.pack();
                        frame3.setSize(1200,500);
                        frame3.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(frame,"Account not found please check user_name password");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame,"all fileds must filled");
                }
            }
        });
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        frame = new JFrame("Student managment System");
        frame.setContentPane(new authentication().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1200,500);
        frame.setVisible(true);


    }

    public boolean SignInStudent(String userName,String password) throws NoSuchAlgorithmException {
        SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        List <Student> studentList = session.createQuery("from Student").list();
        session.getTransaction().commit();
        //session.close();
        //sessionFactory.close();

        String pass = encrepter(password);
        for(int i = 0;i<studentList.size();i++) {
            if (studentList.get(i).getUsername().equals(userName) && studentList.get(i).getPassword().equals(pass)) {
                category = "a";
                logged_id = studentList.get(i).getId();
                return true;
            }
        }
        return false;
    }

    public boolean SignInLecture(String userName,String password) throws NoSuchAlgorithmException {
        SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        List <Lecture> lectureList = session.createQuery("from Lecture").list();
        session.getTransaction().commit();
        //session.close();
        //sessionFactory.close();

        //System.out.println(lectureList.size());
        String pass = encrepter(password);
        for(int i = 0;i<lectureList.size();i++){
            if(lectureList.get(i).getUsername().equals(userName) && lectureList.get(i).getPassword().equals(pass)){
                category = "b";
                logged_id = lectureList.get(i).getId();
                return true;
            }
        }
        return false;
    }

    public void clear(){
        textField1.setText("");
        passwordField1.setText("");
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

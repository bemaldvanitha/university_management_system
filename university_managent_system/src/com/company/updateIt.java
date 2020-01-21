package com.company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.company.Loged.*;
import static com.company.authentication.frame3;

public class updateIt {
    public JPanel panel5;
    private JButton cancelButton;
    private JButton backButton;
    private JTextField newNameText;
    private JTextField newUserNameText;
    private JPasswordField newPasswordText;
    private JButton updateProfile1Button;
    private JTextField newAgeText;

    public updateIt() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame5.setVisible(false);

                frame3 = new JFrame("Logged");
                frame3.setContentPane(new Loged().panel4);
                frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame3.pack();
                frame3.setSize(1200,500);
                frame3.setVisible(true);
            }
        });
        updateProfile1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!newNameText.getText().toString().trim().isEmpty() && !newAgeText.getText().toString().trim().isEmpty() && !newPasswordText.getText().toString().trim().isEmpty() && !newUserNameText.getText().toString().trim().isEmpty()){
                    if(!newUserNameText.getText().toString().trim().equals(newPasswordText.getText().toString().trim())){
                        if(passwordStrength()){
                            String name = newNameText.getText().toString().trim();
                            String age1 = newAgeText.getText().toString().trim();
                            String userName = newUserNameText.getText().toString().trim();
                            String password = newPasswordText.getText().toString().trim();
                            int age = 0;
                            try{
                                age=Integer.valueOf(age1);
                            }catch (Exception ex){
                                JOptionPane.showMessageDialog(frame5,"age must be number");
                            }
                            try {
                                String pass = encrepter(password);
                                updateAccount(name,age,userName,pass);
                                JOptionPane.showMessageDialog(frame5,"Profile Updated");
                                clear();
                            } catch (NoSuchAlgorithmException ex) {
                                ex.printStackTrace();
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(frame5,"password must least 6 char include capitial simple and numbers");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frame5,"user name and password is same");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame5,"all Fields must be fill");
                }
            }
        });
    }

    public void clear(){
        newAgeText.setText("");
        newNameText.setText("");
        newPasswordText.setText("");
        newUserNameText.setText("");
    }

    public void updateAccount(String name,int age,String username,String password){
        SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
        Session session =sessionFactory.openSession();
        session.getTransaction().begin();

        if(category.equals("a")){
            Student student = session.get(Student.class,logged_id);
            student.setName(name);
            student.setUsername(username);
            student.setAge(age);
            student.setPassword(password);
        }
        else if(category.equals("b")){
            Lecture lecture = session.get(Lecture.class,logged_id);
            lecture.setName(name);
            lecture.setAge(age);
            lecture.setUsername(username);
            lecture.setPassword(password);
        }
        session.getTransaction().commit();
    }
    public boolean passwordStrength(){
        String pass = newPasswordText.getText().toString().trim();
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

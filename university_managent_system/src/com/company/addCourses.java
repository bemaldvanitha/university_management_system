package com.company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.company.Loged.frame4;
import static com.company.Loged.logged_id;
import static com.company.authentication.frame3;

public class addCourses {
    private JButton clearButton;
    private JButton addSubjectButton;
    private JCheckBox selectCheckBox;
    private JCheckBox selectCheckBox1;
    private JCheckBox selectCheckBox2;
    private JButton backButton;
    public JPanel panel5;

    private String [] cour_id = {"SENG1111","SENG1113","SENG1113"};
    private String [] cour_name = {"Introduction_to_Programming","Fundamentals_of_Engineering","Data_Structures_and_Algorithms"};

    public addCourses() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame4.setVisible(false);

                frame3 = new JFrame("Logged");
                frame3.setContentPane(new Loged().panel4);
                frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame3.pack();
                frame3.setSize(1200,500);
                frame3.setVisible(true);
            }
        });
        addSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCourse1();
                clear();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
    }

    public void clear(){
        selectCheckBox.setSelected(false);
        selectCheckBox1.setSelected(false);
        selectCheckBox2.setSelected(false);
    }

    public void addCourse1(){

        if(selectCheckBox.isSelected()){
            SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();

            Course course =new Course(cour_id[0],cour_name[0],logged_id);
            session.save(course);
            session.getTransaction().commit();
        }
        if(selectCheckBox1.isSelected()){
            SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();

            Course course =new Course(cour_id[1],cour_name[1],logged_id);
            session.save(course);
            session.getTransaction().commit();
        }
        if(selectCheckBox2.isSelected()){
            SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();

            Course course =new Course(cour_id[2],cour_name[2],logged_id);
            session.save(course);
            session.getTransaction().commit();
        }
    }
}

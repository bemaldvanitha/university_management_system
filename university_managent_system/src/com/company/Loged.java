package com.company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static com.company.authentication.frame3;
import static com.company.authentication.frame;

public class Loged {
    public static String logged_id="";
    public static String category = "";
    public JPanel panel4;
    private JButton showDetailsButton;
    private JButton addCoursesButton;
    private JLabel labelId;
    private JLabel labelName;
    private JLabel labelAge;
    private JLabel labelUserName;
    private JLabel labelCourses;
    private JButton logOutButton;
    private JButton updateProfileButton;
    private JButton deleteProfileButton;

    public static JFrame frame4,frame5;

    public Loged() {
        showDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetails();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.setVisible(false);

                frame = new JFrame("Student managment System");
                frame.setContentPane(new authentication().panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(1200,500);
                frame.setVisible(true);
            }
        });
        addCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.setVisible(false);

                frame4 = new JFrame("Add Courses");
                frame4.setContentPane(new addCourses().panel5);
                frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame4.pack();
                frame4.setSize(1200,500);
                frame4.setVisible(true);
            }
        });
        updateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.setVisible(false);

                frame5 = new JFrame("Update Profile");
                frame5.setContentPane(new updateIt().panel5);
                frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame5.pack();
                frame5.setSize(1200,500);
                frame5.setVisible(true);
            }
        });
        deleteProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAccount();
                frame3.setVisible(false);

                frame = new JFrame("Student managment System");
                frame.setContentPane(new authentication().panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(1200,500);
                frame.setVisible(true);
            }
        });
    }

    public void showDetails(){
        SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        if(category.equals("a")){
            Student student = session.get(Student.class,logged_id);

            labelId.setText(student.getId());
            labelName.setText(student.getName());
            labelAge.setText(Integer.toString(student.getAge()));
            labelUserName.setText(student.getUsername());
            labelCourses.setText(getCourses());
        }
        else if(category.equals("b")){
            Lecture lecture = session.get(Lecture.class,logged_id);

            labelId.setText(lecture.getId());
            labelName.setText(lecture.getName());
            labelAge.setText(Integer.toString(lecture.getAge()));
            labelUserName.setText(lecture.getUsername());
            labelCourses.setText(getCourses());
        }
        session.getTransaction().commit();
    }

    public String getCourses(){
        String allCourses = "*";
        SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        List<Course> courseList = session.createQuery("from Course").list();
        for(int i = 0;i<courseList.size();i++){
            if(courseList.get(i).getEnroll_id().equals(logged_id)){
                allCourses = allCourses + "  "+courseList.get(i).getCour_code()+"  "+courseList.get(i).getName() +" * \n";
            }
        }
        session.getTransaction().commit();
        return  allCourses;
    }

    public void deleteAccount(){
        SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        if(category.equals("a")){
            Student student = session.get(Student.class,logged_id);
            session.delete(student);
        }
        else if(category.equals("b")){
            Lecture lecture = session.get(Lecture.class,logged_id);
            session.delete(lecture);
        }
        session.getTransaction().commit();
    }
}

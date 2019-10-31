import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String args[]){
        Scanner scan=new Scanner(System.in);
        ArrayList <student> studentArrayList=new ArrayList<>();
        ArrayList <lecture> lectureArrayList=new ArrayList<>();

        int a=1,b,c,d,e;
        String k,l,m,n,o,p;
        while(a==1){
            System.out.println("Welcome to univercity managemtn system");
            System.out.println("plese select an opption");
            System.out.println("enter 1 to register as student enter 2 to register as lecturer enter 3 to exit");
            a=scan.nextInt();
            if(a== 1){
                System.out.println("enter name");
                k=scan.next();
                System.out.println("enter age");
                l=scan.next();
                student stu=new student(k,l);
                stu.showCourses();
                System.out.println("enter course id ");
                m=scan.next();
                stu.selectCouse(m);

                stu.show();
            }
            else if(a==2){
                System.out.println("enter name");
                k=scan.next();
                System.out.println("enter age");
                l=scan.next();
                lecture lec=new lecture(k,l);
                lec.show();
            }
            else if(a==3){
                a=2;
            }
            else{
                System.out.println("wrong command");
            }
        }

    }
}

class student extends couses{
    private String name;
    private int age;
    private ArrayList <String> selectCourses=new ArrayList<String>();

    public student(name,age){
        this.name=name;
        this.age=age;
    }

    public void selectCouse(String id){
        selectCourses.add(id);
    }

    public void show(){
        System.out.println("Name : "+name);
        System.out.println("Age : "+age);
        for(int a=0;a<selectCourses.size();a++){
            System.out.println("Subject : "+selectCourses.get(a)" ");
        }
    }

}

class couses{
    protected ArrayList <String> courseId =new ArrayList<String>(); //{"SENG 11111","SENG 11112","SENG 11113"
    protected ArrayList <String> courseName=new ArrayList<String>(); // {"INTRDUCTION TO PROGRAMMING","FUNDUMENTALS OF ENGINNERING", "DATA STUCTURES AND ALGORITHEMS"}

    public couses(){
        courseId.add("SENG 11111");
        courseId.add("SENG 11112");
        courseId.add("SENG 11113");
        courseName.add("INTRDUCTION TO PROGRAMMING");
        courseName.add("FUNDUMENTALS OF ENGINNERING");
        courseName.add("DATA STUCTURES AND ALGORITHEMS");
    }



    public void showCourses(){
        for (int a=0;a<courseId.size();a++){
            System.out.print(a+" "+courseId.get(a));
            System.out.println(" - "+courseName.get(a));
        }
    }
}

class lecture extends couses{
    private String name;
    private int age;

    public lecture(name,age){
        this.name=name;
        this.age=age;
    }

    public void addCourses(String id,String name){
        courseId.add(id);
        courseName.add(name);
    }

    public void show(){
        System.out.println("Name : "+name);
        System.out.println("Age : "+age);

    }
}


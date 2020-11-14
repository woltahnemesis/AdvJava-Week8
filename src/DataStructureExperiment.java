import Models.Course;
import Models.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class DataStructureExperiment {
    public static void main (String[] args){
        String[] names = {"Fred", "Barney", "Wilma"};

        for(int i = 0; i < names.length; i++)
            System.out.println(names[i]);

        ArrayList namesAL = new ArrayList();
        namesAL.add("Fred");
        namesAL.add(new Student(1001, "Fred", "Flintstone"));
        namesAL.add(new Course("COMP 1011", "Adv Java"));

        ArrayList<Student> studentAL = new ArrayList<>();
        Student st1 = new Student(10001, "Fred", "Flintstone");
        Student st2 = new Student(10002, "Barney", "Rubble");
        Student st3 = new Student(10003, "Wilma", "FLintstone");
        Student st4 = new Student(10004, "Betty", "Rubble");

        studentAL.add(st1);
        studentAL.addAll(Arrays.asList(st2, st3, st4));

        // This is not recommended
        ArrayList<String> stringAL = new ArrayList<>();
        stringAL.add(st1.toString());
        stringAL.add(st2.toString());
        stringAL.add(st3.toString());
        stringAL.add(st4.toString());

        System.out.println("This is the stringAL list");
        for(String string:stringAL)
            System.out.println(string);

        //studentAL.remove(st4);
        studentAL.add(st1);

        System.out.println("\nThis is the studentAL list");
        for(Student student:studentAL)
            System.out.println(student + "hashcode: " + student.hashCode());

        HashSet<Student> studentHS = new HashSet<>();
        studentHS.add(st1);
        studentHS.addAll(studentAL);

        // This is a new object but with same values
        Student st5 = new Student(10001, "Fred", "Flintstone");
        studentHS.add(st5);

        System.out.println("\nPrinting out the HashSet");
        for(Student student: studentHS)
            System.out.println(student);

        // Must implement comparable
        TreeSet<Student> studentTS = new TreeSet<>();
        studentTS.add(st3);
        studentTS.add(st4);
        studentTS.add(st1);
        studentTS.add(st2);

        System.out.println("\nPrinting out the TreeSet");
        for(Student student: studentTS)
            System.out.println(student);

        st1.addCourse(new Course("COMP 1011", "Adv Java"), 99);


    }
}

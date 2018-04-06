package OOP.FifthEx;

import java.util.*;

public class Group {
    private static HashSet<Group> groups = new HashSet<>();
    private Discipline discipline;
    private String nGroup;
    private ArrayList<Student> students = new ArrayList<>();

    public Group(Discipline discipline, String nGroup) {
        this.discipline = discipline;
        this.nGroup = nGroup;
        groups.add(this);
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public String getnGroup() {
        return nGroup;
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            student.addDiscipline(discipline);
        } else
            System.out.println("This student has already been in group");
    }

    private boolean find(Student student) {
        int index = students.indexOf(student);
        return index >= 0;
    }

    public static void getInfoAboutStudent(Student student) {
        System.out.printf("For student %s:\n", student.getName());
        for (Group group : groups) {
            if (group.find(student)) {
                System.out.printf("In group %s:\n", group.nGroup);
                System.out.println(group.discipline + ": "
                        + student.getMark(group.discipline));
            }
        }
    }



    public static void main(String[] args) {
        Group f = new Group(Discipline.MATH, "P3101");
        Group s = new Group(Discipline.MATH, "P3103");
        Group t = new Group(Discipline.ART, "P3102");

        Student Ritt = new Student("Ritt");
        Student Ivan = new Student("Ivan");

        f.addStudent(Ritt);
        f.addStudent(Ritt);
        t.addStudent(Ritt);
        f.addStudent(Ivan);

        t.addStudent(Ivan);
        Ritt.changeMark(Discipline.MATH, 4.2);
        Ritt.changeMark(Discipline.ART, 5);

        getInfoAboutStudent(Ivan);

        System.out.println(Ritt);
        System.out.println(Ivan);
    }



}

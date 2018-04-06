package OOP.FifthEx;

import java.util.EnumMap;
import java.util.Map;

public class Student {
    private String name;
    private static int id;
    private Map<Discipline, Number> marks = new EnumMap<>(Discipline.class);

    public Student(String name) {
        this.name = name;
        id++;
    }

    public String getName() {
        return name;
    }

    public void addDiscipline(Discipline discipline) {
        marks.put(discipline, 0);
    }

    public Number getMark(Discipline discipline) {
        return marks.get(discipline);
    }

    public void changeMark(Discipline discipline, Number mark) {
        if (mark instanceof Double && !discipline.isInteger()
                || mark instanceof Integer && discipline.isInteger()) {

            if (marks.containsKey(discipline))
                marks.put(discipline, mark);
            else System.out.println("Wrong discipline");
            return;
        }
        System.out.println("Incorrect type of mark");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}

import java.util.*;

public class Main {
    static String getLetterGrade(double grade) {
        if (grade >= 85) return "A";
        else if (grade >= 75) return "B";
        else if (grade >= 65) return "C";
        else if (grade >= 50) return "D";
        else return "F";
    }

    static void HighGradeSubject(double[][] grades, int nStudents, int indexofcolum, String[] Students) {
        double highGrade = 0;
        int index = 0;
        for (int i = 0; i < nStudents; i++) {
            if (highGrade < grades[i][indexofcolum]) {
                highGrade = grades[i][indexofcolum];
                index = i;
            }
        }
        System.out.println("Student " + Students[index] + " has the highest grade in subject " + (indexofcolum + 1) + ": " + highGrade);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of students: ");
        int NumberStudents = in.nextInt();
        String[] Students = new String[NumberStudents];
        double[][] grades = new double[NumberStudents][4];
        int countNotPass = 0;

        for (int i = 0; i < NumberStudents; i++) {
            System.out.println("Enter the name of student " + (i + 1) + ": ");
            Students[i] = in.next();
            double sum = 0;
            boolean failed = false;

            for (int j = 0; j < 3; j++) {
                System.out.println("Enter grade for subject " + (j + 1) + ": ");
                grades[i][j] = in.nextDouble();
                while (grades[i][j] < 0 || grades[i][j] > 100) {
                    System.out.print("Please enter a valid grade between 0 and 100: ");
                    grades[i][j] = in.nextDouble();
                }
                sum += grades[i][j];
                if (grades[i][j] < 50) failed = true;
            }

            if (failed) countNotPass++;

            grades[i][3] = sum / 3;
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println("****************** Main MENU *********************");
            System.out.println("1. Show all students names.");
            System.out.println("2. Show all Students grades in each subject.");
            System.out.println("3. Search students by name.");
            System.out.println("4. Count passed/failed Students.");
            System.out.println("0. Exit.");
            System.out.println("**************************************************");
            choice = in.nextInt();

            switch (choice) {
                case 1 -> {
                    for (int i = 0; i < NumberStudents; i++)
                        System.out.println(Students[i]);
                }
                case 2 -> {
                    for (int i = 0; i < NumberStudents; i++) {
                        System.out.print("Grades of " + Students[i] + ": ");
                        for (int j = 0; j < 3; j++) {
                            String l = getLetterGrade(grades[i][j]);
                            System.out.print(grades[i][j] + " (" + l + ") ");
                        }
                        System.out.println(" Average: " + grades[i][3]);
                    }
                    for (int i = 0; i < 3; i++)
                        HighGradeSubject(grades, NumberStudents, i, Students);
                }
                case 3 -> {
                    System.out.println("Enter student name to search: ");
                    String name = in.next();
                    boolean found = false;
                    for (int i = 0; i < NumberStudents; i++) {
                        if (Students[i].equals(name)) {
                            System.out.print("Student " + Students[i] + ": ");
                            for (int j = 0; j < 3; j++) {
                                String l = getLetterGrade(grades[i][j]);
                                System.out.print(grades[i][j] + " (" + l + ") ");
                            }
                            System.out.println(" Average: " + grades[i][3]);
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Student not found.");
                }
                case 4 -> {
                    System.out.println("Failed students: " + countNotPass);
                    System.out.println("Passed students: " + (NumberStudents - countNotPass));
                }
                case 0 -> System.out.println("Thank you for using our application.");
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
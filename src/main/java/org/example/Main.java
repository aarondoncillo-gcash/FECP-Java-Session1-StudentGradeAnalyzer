package org.example;

import java.util.*;

public class Main{

    public static char calculateLetterGrade(int score, int[] gradeCounts) {
        char letterGrade;
        // give the corresponding letter grade and count the number of students per grade
        if (score >= 90 && score <= 100) {
            letterGrade = 'A';
            gradeCounts[0] += 1;
            return letterGrade;
        }  else if (score >= 80 && score <= 89) {
            letterGrade = 'B';
            gradeCounts[1] += 1;
            return letterGrade;
        }  else if (score >= 70 && score <= 79) {
            letterGrade = 'C';
            gradeCounts[2] += 1;
            return letterGrade;
        }  else if (score >= 60 && score <= 69) {
            letterGrade = 'D';
            gradeCounts[3] += 1;
            return letterGrade;
        } else {
            letterGrade = 'F';
            gradeCounts[4] += 1;
            return letterGrade;
        }

    }

    public static int calculateTopStudentScore(int score, int maxScore, String name, ArrayList<String> students) {
        // get top student with highest score and add it to list
        if (score > maxScore) {
            students.clear();
            students.add(name);
        } else if (score == maxScore) {
            // if there are more than students who got the highest score
            students.add(name);
        }
        // Update highest score
        maxScore = Math.max(score, maxScore);
        return maxScore;
    }

    public static void displayTopStudents(ArrayList<String> topStudents, int highestScore) {
        String studentGrade="";
        for(String student : topStudents){
            studentGrade += (studentGrade.length() > 1) ? ", " + student + " (" + highestScore + ")" : student + " (" + highestScore + ")";
        }
        System.out.printf("\nTop Student(s): %s", studentGrade);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // store the number of students per grade; index 0=A, 1=B, 2=C, 3=D, 4=F
        int [] gradeCounts = {0, 0, 0, 0, 0};
        // store the top students
        ArrayList<String> topStudents = new ArrayList<>();

        // get no. of students
        System.out.print("Enter number of students: ");
        int numberOfStudents = sc.nextInt();
        // prevent skipping the next input
        sc.nextLine();

        String studentName;
        int highestScore = 0;
        int studentScore = 0;
        double averageScore = 0;
        for (int i = 0; i < numberOfStudents; ++i) {
            System.out.println();
            // get student name
            System.out.printf("Enter name of student %d: ", i+1);
            studentName = sc.nextLine();
            // get student score
            System.out.printf("Enter score for %s: ", studentName);
            studentScore = sc.nextInt();
            // prevent skipping the next input
            sc.nextLine();

            // add all student grades for computation of average later
            averageScore += studentScore;
            // calculate top students scores
            highestScore = calculateTopStudentScore(studentScore, highestScore, studentName, topStudents);

            System.out.printf("%s got grade: %c \n", studentName, calculateLetterGrade(studentScore, gradeCounts));
        }

        System.out.println();
        System.out.println("---- Class Summary ----");
        System.out.printf("Average Score: %.2f", averageScore/numberOfStudents); // get average score
        System.out.printf("\nGrade Counts: A:%d B:%d C:%d D:%d F:%d", gradeCounts[0], gradeCounts[1], gradeCounts[2], gradeCounts[3], gradeCounts[4]);
        displayTopStudents(topStudents, highestScore);

    }
}

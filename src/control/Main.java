/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package control;

import adt.SetInterface;
import control.StudentController;
import dao.TutorInitializer;
import entity.Tutor;
import java.util.Scanner;

/**
 *
 * @author fongj
 */
public class Main {

    private static final int MAX_INVALID_ATTEMPTS = 3;
    private static StudentController studentPage = new StudentController();

    public static void main(String[] args) {
        homepage();
    }

    public static void homepage() {
        TutorInitializer t = new TutorInitializer();
        SetInterface<Tutor> tutorList = t.initializeTutor();

        int countInvalidInput;
        int option;

        Scanner scanner = new Scanner(System.in);

        printLine(1, 40);
        System.out.println("Welcome to TARUC Management System");
        printLine(1, 40);

        do {
            countInvalidInput = 0;

            System.out.printf("%2s %-10s\n", "1)", "Student Registration Management");
            System.out.printf("%2s %-10s\n", "2)", "Course Management");
            System.out.printf("%2s %-10s\n", "3)", "Progremme Management");
            System.out.printf("%2s %-10s\n", "4)", "Tutorial Group Management");
            System.out.printf("%2s %-5s\n", "5)", "Exit System");
            printLine(1, 40);

            do {
                System.out.printf("%-10s", "Enter Number(1-5): ");
                option = scanner.nextInt();

                if (option < 1 || option > 5) {
                    System.out.println("Invalid option! Please enter a number between 1 and 5.");
                    ++countInvalidInput;
                }

                if (countInvalidInput >= MAX_INVALID_ATTEMPTS) {
                    break;
                }
            } while (option < 1 || option > 5);

            switch (option) {
                case 1: //Student Management
                    studentPage.studentManagement();
                    break;
                case 2: //Course Management
                    break;
                case 3: // Progremme Management
                    break;
                case 4: // Tutorial Group Management
                    break;
                default:
                    break;
            }

        } while (countInvalidInput >= MAX_INVALID_ATTEMPTS);
    }

    private static void printLine(int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("=");
            }
        }
        System.out.println();
    }

}

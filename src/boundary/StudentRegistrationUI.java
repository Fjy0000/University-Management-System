package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Fong Jun Yi
 */
public class StudentRegistrationUI {

    Scanner input = new Scanner(System.in);

    public void titleUI(String header) {
        System.out.println();
        System.out.println(header);
        printLine(1, 35);
    }

    // Student Menu UI----------------------------------------------------------------------------------------------------------------------------
    public int studentManageMenu() {
        int option;

        System.out.println();
        printLine(1, 35);
        System.out.printf("%-10s\n", "Student Management");
        printLine(1, 35);
        System.out.printf("%2s %-10s\n", "1)", "Add Student");
        System.out.printf("%2s %-10s\n", "2)", "View Student List");
        System.out.printf("%2s %-10s\n", "3)", "Update Student Details");
        System.out.printf("%2s %-10s\n", "4)", "Manage Student Course");
        System.out.printf("%2s %-10s\n", "5)", "Remove Student");
        System.out.printf("%2s %-10s\n", "6)", "Calculate Total Cost of Registed Course");
        System.out.printf("%2s %-10s\n", "7)", "Generate Report");
        System.out.printf("%2s %-5s\n", "8)", "Exit");
        printLine(1, 35);

        System.out.print("Enter Number(1-8): ");
        option = input.nextInt();

        while (option < 1 || option > 8) {
            System.out.print("Invalid option! Please select a number between 1 and 8 : ");
            option = input.nextInt();
        }

        return option;
    }

    // Student Registration UI------------------------------------------------------------------------------------------------------------------
    public String inputStudentName() {
        String name;

        do {
            input.nextLine();
            System.out.print("Enter Student Name : ");
            name = input.nextLine();
            if (name.length() < 4) {
                System.out.println("Student Name must more than 4 letter...");
            }
        } while (name.length() < 4);
        return name;
    }

    public String inputStudentContactNo() {
        String contactNo;

        do {
            System.out.print("Enter Student Contact No : ");
            contactNo = input.nextLine();
            if (contactNo.length() < 10) {
                System.out.println("Please enter correct and real contact no...");
            }
        } while (contactNo.length() < 10);
        return contactNo;
    }

    public String inputStudentGender() {
        int gender;

        do {
            System.out.print("Enter Student Gender (1 = Male/2 = Female) : ");
            gender = input.nextInt();
            if (gender < 1 || gender > 2) {
                System.out.println("Please select the option 1 or 2...");
            }
        } while (gender < 1 || gender > 2);
        if (gender == 1) {
            return "Male";
        } else {
            return "Female";
        }
    }

    public String inputStudentProgremme() {
        String progremme;

        do {
            input.nextLine();
            System.out.print("Enter Student Progremme (exp: RSD): ");
            progremme = input.nextLine();
            if (progremme.isEmpty()) {
                System.out.println("Student Progremme cannot leave empty...");
            }
        } while (progremme.isEmpty());
        return progremme;
    }

    // Update Student UI -------------------------------------------------------------------------------------------------------------------------
    public int updateMenu() {
        int option;
        System.out.println();
        System.out.println("1) Student Name");
        System.out.println("2) Student Contact No");
        System.out.println("3) Student Gender");
        System.out.println("4) Student Progremme");
        System.out.print("Choose one to update (1-4): ");
        option = input.nextInt();

        while (option < 1 || option > 8) {
            System.out.print("Invalid option! Please select a number between 1 and 4 : ");
            option = input.nextInt();
        }

        return option;
    }

    public String inputStuNewName() {
        String name;

        do {
            input.nextLine();
            System.out.print("Enter New Student Name : ");
            name = input.nextLine();
            if (name.length() < 4) {
                System.out.println("Student Name must more than 4 letter...");
            }
        } while (name.length() < 4);
        return name;
    }

    public String inputStuNewContactNo() {
        String contactNo;

        do {
            System.out.print("Enter New Student Contact No : ");
            contactNo = input.nextLine();
            if (contactNo.length() < 10) {
                System.out.println("Please enter correct and real contact no...");
            }
        } while (contactNo.length() < 10);
        return contactNo;
    }

    public String inputStuNewGender() {
        int gender;

        do {
            System.out.print("Enter New Student Gender (1 = Male/2 = Female) : ");
            gender = input.nextInt();
            if (gender < 1 || gender > 2) {
                System.out.println("Invalid ! Please select 1 = Male or 2 = Female");
            }
        } while (gender < 1 || gender > 2);
        if (gender == 1) {
            return "Male";
        } else {
            return "Female";
        }
    }

    public String inputStuNewProgremme() {
        String progremme;

        do {
            System.out.print("Enter New Student Progremme : ");
            progremme = input.nextLine();
            if (progremme.isEmpty()) {
                System.out.println("Student Progremme cannot leave empty...");
            }
        } while (progremme.isEmpty());
        return progremme;
    }

    // Student Course UI -------------------------------------------------------------------------------------------------------------------------
    public String inputStudentId() {
        input.nextLine();
        System.out.print("Enter the Student ID : ");
        String id = input.nextLine();
        return id;
    }

    public String inputStudentCourse() {
        System.out.print("Enter the Course : ");
        String course = input.nextLine();
        return course;
    }

    public String inputCourseStatus() {
        int select;

        System.out.print("Enter the number of Course Status (Main = 1/Resit = 2/Repeat = 3) : ");
        select = input.nextInt();
        while (select < 0 || select > 3) {
            System.out.print("Invalid Enter! Please select a number between 1 and 3 (Main = 1/Resit = 2/Repeat = 3) : ");
            select = input.nextInt();
        }
        if (select == 1) {
            return "Main";
        } else if (select == 2) {
            return "Resit";
        } else {
            return "Repeat";
        }
    }

    public int addOrRemoveCourse() {
        int select;

        System.out.printf("%-20s", "Do you want to Register or Remove a student's course? (Register = 1/Remove = 2) : ");
        select = input.nextInt();
        while (select < 0 || select > 2) {
            System.out.print("Invalid Enter! Please select a number between 1 and 2 (Register = 1/Remove = 2) : ");
            select = input.nextInt();
        }
        return select;
    }

    // Display Student List UI ---------------------------------------------------------------------------------------------------------------------
    public void studentListHeader() {
        printLine(1, 150);
        System.out.printf("%-5s \t %-15s \t %-15s \t %-15s \t %-15s \t %-15s \t %-15s\n",
                "NO", "Student ID", "Student Name", "Contact No", "Gender", "Programme", "Courses");
        printLine(1, 150);
    }

    // Display Total Cost of Registered Course UI ---------------------------------------------------------------------------------------------------------------------
    public void totalCostListHeader() {
        printLine(1, 100);
        System.out.printf("%-5s \t %-15s \t %-15s \t %-15s \t %-15s \n",
                "NO", "Student ID", "Student Name", "Courses", "Total Fees");
        printLine(1, 100);
    }

    // Display Summary Report Header & Footer UI ---------------------------------------------------------------------------------------------------------------------
    public void summaryReportHeader() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);

        System.out.printf("\t \t \t \t \t \t \t \t \t \t    %30s %1s%-4d%1s \n", "Student Registration Annual Report", "(", date.getYear(), ")");
        System.out.printf("\t \t \t \t \t \t \t \t \t \t \t %10s %-15s \n", "As on: ", formattedDate);

        printLine(1, 205);
        System.out.printf("%-15s \t %-15s \t %-15s \t %-15s \t %-15s \t %-15s \t %-15s \t %-15s \t %-15s\n",
                "Student ID", "Student Name", "Contact No", "Gender",
                "Programme", "Courses", "Total Main", "Total Resit", "Total Repeat");
        printLine(1, 205);
    }

    public void summaryReportFooter(int totalStudent, int totalPaidFees) {
        LocalDate date = LocalDate.now();

        printLine(1, 205);
        System.out.println("This Year(" + date.getYear() + ") Total Student Registered : " + totalStudent);
        System.out.println("This Year(" + date.getYear() + ") Total Student Registered Paid Course Fees : " + totalPaidFees);
        printLine(1, 205);
    }

    // Display List Exit UI ---------------------------------------------------------------------------------------------------------------------
    public int studentListExit() {
        int exit;

        System.out.println();
        do {
            System.out.print("Enter 1 to exit this page : ");
            exit = input.nextInt();
        } while (exit != 1);
        return exit;
    }

    // Confirmation Page UI ------------------------------------------------------------------------------------------------------------------------------
    public boolean inputConfirmation(String str) {
        System.out.println();
        System.out.printf("%-20s", "Confirm to " + str + "? (Y/N): ");
        String confirm = input.nextLine();
        while (!confirm.toLowerCase().equals("y") && !confirm.toUpperCase().equals("Y")
                && !confirm.toLowerCase().equals("n") && !confirm.toUpperCase().equals("N")) {
            System.out.printf("%-20s", "Invalid Input !!! Please try agian do you confirm to " + str + "? (Y/N): ");
            confirm = input.nextLine();
        }
        System.out.println();
        if (confirm.toLowerCase().equals("y") || confirm.toUpperCase().equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    // Exit Page UI ------------------------------------------------------------------------------------------------------------------------------
    public int inputExitPage() {
        int exit;

        System.out.println();
        System.out.printf("%-30s", "Do you want to EXIT this page? (Yes=1/No=0) : ");
        exit = input.nextInt();
        while (exit < 0 || exit > 1) {
            System.out.printf("%-30s", "Invalid Input! Please enter 0 or 1 (Yes=1/No=0) : ");
            exit = input.nextInt();
        }
        System.out.println();
        return exit;
    }

    // Print Line UI ------------------------------------------------------------------------------------------------------------------------------
    public void printLine(int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("=");
            }
        }
        System.out.println();
    }
}

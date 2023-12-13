package control;

import adt.Set;
import adt.SetInterface;
import boundary.StudentRegistrationUI;
import static control.Main.homepage;
import static control.Main.stu;
import static control.Main.student;
import dao.StudentInitializer;
import entity.Student;
import entity.StudentCourse;
import java.util.Iterator;

/**
 *
 * @author Fong Jun Yi
 */
public class StudentController {

    private StudentRegistrationUI studentUI = new StudentRegistrationUI();

    public void studentManagement() {
        int result, exit;

        do {
            exit = 0;
            result = studentUI.studentManageMenu();

            switch (result) {
                case 1: {
                    exit = registration(student);
                    break;
                }
                case 2: {
                    exit = displayStudentList(student);
                    break;
                }
                case 3: {
                    exit = updateStudent(student);
                    break;
                }
                case 4: {
                    exit = manageStudentCourse(student);
                    break;
                }
                case 5: {
                    exit = removeStudent(student);
                    break;
                }
                case 6: {
                    exit = displayTotalCost(student);
                    break;
                }
                case 7: {
                    exit = displayReport(student);
                    break;
                }
                case 8:
                    homepage();
                    break;
            }
        } while (exit == 1);
    }

    private int registration(SetInterface<Student> student) {
        int exit;
        studentUI.titleUI("Add New Student");
        do {
            String name = studentUI.inputStudentName();
            String contactNo = studentUI.inputStudentContactNo();
            String ic = studentUI.inputStudentIc();
            String progremme = studentUI.inputStudentProgremme();

            String id = name.substring(0, 3) + "1";
            if (studentUI.inputConfirmation("add new student") == true) {
                student.add(new Student(id, name, contactNo, ic, progremme));
                System.out.println("Successful Registered New Student !!!!");
            } else {
                System.out.println("Cancelled Registration !!!!");
            }
            exit = studentUI.inputExitPage();
        } while (exit == 0);
        return exit;
    }

    private int manageStudentCourse(SetInterface<Student> student) {
        int exit;
        String id, course, status;
        boolean isSuccess = false;

        studentUI.titleUI("Manage Student Course");
        do {
            int select = studentUI.addOrRemoveCourse();

            switch (select) {
                case 1: {
                    id = studentUI.inputStudentId();
                    course = studentUI.inputStudentCourse();
                    status = studentUI.inputCourseStatus();
                    if (studentUI.inputConfirmation("add this Course") == true) {
                        Iterator<Student> getStudent = student.getIterator();
                        while (getStudent.hasNext()) {
                            Student object = getStudent.next();
                            if (object.getStudentId().equals(id)) {
                                object.addStudentCourse(new StudentCourse("courseid", course, status));
                                isSuccess = true;
                                break;
                            }
                        }

                        if (isSuccess == true) {
                            System.out.println("Added Course to this Student Successful........");

                        } else {
                            System.out.println("The Student ID no inside the list...");
                        }
                    } else {
                        System.out.println("Cancelled Adding the Course !!!!");
                    }
                    break;
                }
                case 2: {
                    id = studentUI.inputStudentId();
                    course = studentUI.inputStudentCourse();
                    if (studentUI.inputConfirmation("remove this Course") == true) {
                        Iterator<Student> getStudent = student.getIterator();
                        while (getStudent.hasNext()) {
                            Student object1 = getStudent.next();
                            if (object1.getStudentId().equals(id)) {
                                Iterator<StudentCourse> getStudentCourse = object1.getStudentCourse().getIterator();
                                while (getStudentCourse.hasNext()) {
                                    StudentCourse object2 = getStudentCourse.next();
                                    if (object2.getCourse().equals(course)) {
                                        object1.removeStudentCourse(object2);
                                        isSuccess = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if (isSuccess == true) {
                            System.out.println("Removed Course from this Student Successful........");
                        } else {
                            System.out.println("The Student ID no inside the list...");
                        }
                    } else {
                        System.out.println("Cancelled Removing the Course !!!!");
                    }
                    break;
                }
            }
            exit = studentUI.inputExitPage();
        } while (exit == 0);
        return exit;
    }

    private int updateStudent(SetInterface<Student> student) {
        int exit, option, count;
        String id, name = "", ic = "", contactNo = "", progremme = "";
        boolean isSuccess = false;

        studentUI.titleUI("Update Student Details");
        do {
            id = studentUI.inputStudentId();
            option = studentUI.updateMenu();
            switch (option) {
                case 1: {
                    name = studentUI.inputStuNewName();
                    break;
                }
                case 2: {
                    contactNo = studentUI.inputStuNewContactNo();
                    break;
                }
                case 3: {
                    ic = studentUI.inputStuNewIC();
                    break;
                }
                case 4: {
                    progremme = studentUI.inputStuNewProgremme();
                    break;
                }
            }
            if (studentUI.inputConfirmation("update the student detail") == true) {
                Iterator<Student> getStudent = student.getIterator();
                count = 0;
                while (getStudent.hasNext()) {
                    count++;
                    Student object = getStudent.next();
                    if (object.getStudentId().equals(id)) {
                        if (option == 1) {
                            isSuccess = student.update(new Student(object.getStudentId(), name,
                                    object.getContactNo(), object.getStudentIc(),
                                    object.getStudentProgremme(), object.getStudentCourse()),
                                    count);
                            break;
                        }
                        if (option == 2) {
                            isSuccess = student.update(new Student(object.getStudentId(), object.getStudentName(),
                                    contactNo, object.getStudentIc(),
                                    object.getStudentProgremme(), object.getStudentCourse()),
                                    count);
                            break;
                        }
                        if (option == 3) {
                            isSuccess = student.update(new Student(object.getStudentId(), object.getStudentName(),
                                    object.getContactNo(), ic,
                                    object.getStudentProgremme(), object.getStudentCourse()),
                                    count);
                            break;
                        }
                        if (option == 4) {
                            isSuccess = student.update(new Student(object.getStudentId(), object.getStudentName(),
                                    object.getContactNo(), object.getStudentIc(),
                                    progremme, object.getStudentCourse()),
                                    count);
                            break;
                        }
                    }
                }
                if (isSuccess == true) {
                    System.out.println("Successful Updated Student Detail.....");
                } else {
                    System.out.println("The Student ID no inside the list...");
                }
            } else {
                System.out.println("Cancelled Updating Student Detail !!!!");
            }
            exit = studentUI.inputExitPage();
        } while (exit == 0);
        return exit;
    }

    private int removeStudent(SetInterface<Student> student) {
        int exit;
        String id;
        boolean isSuccess = false;

        studentUI.titleUI("Remove Student");
        do {
            id = studentUI.inputStudentId();

            if (studentUI.inputConfirmation("remove this Student") == true) {
                Iterator<Student> getStudent = student.getIterator();
                while (getStudent.hasNext()) {
                    Student object = getStudent.next();
                    if (object.getStudentId().equals(id)) {
                        student.remove(object);
                        isSuccess = true;
                        break;
                    }
                }
                if (isSuccess == true) {
                    System.out.println("Removed the Student Successful........");
                } else {
                    System.out.println("The Student ID no inside the list...");
                }
            } else {
                System.out.println("Cancelled Removing the Student !!!!");
            }

            exit = studentUI.inputExitPage();
        } while (exit == 0);
        return exit;
    }

    private int displayStudentList(SetInterface<Student> student) {
        int exit;
        int count1 = 0, count2;

        studentUI.titleUI("View Student List");
        studentUI.studentListHeader();
        if (student.isEmpty()) {
            System.out.println("Oops !!! Student List is Empty............");
        } else {
            Iterator<Student> getStudent = student.getIterator();
            while (getStudent.hasNext()) {
                ++count1;
                Student studentObject = getStudent.next();
                if (studentObject.getStudentCourseSize() != 0) {
                    Iterator<StudentCourse> getStudentCourse = studentObject.getStudentCourse().getIterator();
                    count2 = 0;
                    while (getStudentCourse.hasNext()) {
                        StudentCourse courseObject = getStudentCourse.next();
                        if (count2 == 0) {
                            System.out.printf("%-5s \t %-15s \t %-15s \t %-15s \t %-15s \t %-15s \t %-15s\n",
                                    count1, studentObject.getStudentId(), studentObject.getStudentName(),
                                    studentObject.getContactNo(), studentObject.getStudentIc(),
                                    studentObject.getStudentProgremme(), courseObject.getCourse());
                            count2++;
                        } else {
                            System.out.printf("%-5s \t %-15s \t %-15s \t %-15s \t %-15s \t %-15s \t %-15s\n",
                                    "", "", "", "", "", "", courseObject.getCourse());
                            count2++;
                        }
                    }
                } else {
                    System.out.printf("%-5s \t %-15s \t %-15s \t %-15s \t %-15s \t %-15s \t \n",
                            count1, studentObject.getStudentId(), studentObject.getStudentName(),
                            studentObject.getContactNo(), studentObject.getStudentIc(),
                            studentObject.getStudentProgremme());
                }
            }
        }
        exit = studentUI.studentListExit();
        return exit;
    }

    private int displayTotalCost(SetInterface<Student> student) {
        int exit;

        studentUI.titleUI("Calculate Total Cost of Registed Course");
        studentUI.totalCostListHeader();
        exit = studentUI.studentListExit();
        return exit;
    }

    private int displayReport(SetInterface<Student> student) {
        int exit;

        studentUI.titleUI("Generate Report");
        studentUI.summaryReportHeader();
        exit = studentUI.studentListExit();
        return exit;
    }
}

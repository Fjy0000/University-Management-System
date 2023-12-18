package control;

import adt.Set;
import adt.SetInterface;
import adt.SortedIterator;
import boundary.CourseUI;
import static control.Main.courseSet;
import static control.Main.facultySet;
import static control.Main.homepage;
import entity.Course;
import entity.Faculty;
import entity.Programme;
import java.util.Scanner;
import static control.Main.programmeSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author User
 */
public class CourseControl {

    private CourseUI courseUI = new CourseUI();
    private static final String TITLE = "Course Management System Summary Report";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    private Set<String> actionHistory = new Set<>();

    public void CourseManagement() {

        // Create an instance of Course_Faculty
        CourseControl course = new CourseControl();
        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();
        do {
            choice = courseUI.CourseManagementMenu();
            switch (choice) {
                case 1:
                    course.addCourseByUserInput();
                    break;
                case 2:
                    course.removeCourseByUserInput();
                    break;
                case 3:
                    course.editCourseDetail();
                    break;
                case 4:
                    System.out.print("Enter Faculty ID: ");
                    String facultyId = scanner.next();
                    System.out.println("Courses taken by Faculty " + facultyId + ":");
                    break;
                case 5:
                    System.out.print("Enter Programme ID: ");
                    String programmeId = scanner.next();
                    course.findCourseById(programmeId);
                    break;
                case 6:
                    course.displayAllCourses(courseSet);
//                    CourseManagement();
                case 7:
                    course.searchCourseByUserInput();
//                    CourseManagement();
                    break;
//                    
                case 8:
                    course.addProgramToCourse();
//                    CourseManagement();
                    break;
                case 9:
                    course.removeProgramFromCourse();
//                    CourseManagement();
                    break;
                case 10:
                    break;
                case 0:
                    homepage();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);

    }

    public void addCourseByUserInput() {
        Scanner scanner = new Scanner(System.in);
        int continueAdd = 0;

        System.out.println("Enter Course ID:");
        String courseId = scanner.next();

        // Check if the course already exists before adding
        if (findCourseById(courseId) == null) {
            System.out.println("Enter Course Name:");
            String courseName = scanner.next();

            Course newCourse = new Course(courseId, courseName);
            courseSet.add(newCourse);

            System.out.println("ID \t Faculty Name");
            Iterator<Faculty> getFaculty = facultySet.getIterator();
            while (getFaculty.hasNext()) {
                Faculty facultyOj = getFaculty.next();
                System.out.println(facultyOj.getfacultyId() + "\t" + facultyOj.getfacultyName());
            }

            do {
                scanner.nextLine();
                System.out.print("Enter Select the Faculty : ");
                String facultyID = scanner.nextLine();

                Iterator<Faculty> check1 = facultySet.getIterator();
                while (check1.hasNext()) {
                    Faculty result1 = check1.next();
                    if (result1.getfacultyId().equals(facultyID)) {
                        Iterator<Course> getCourse = courseSet.getIterator();
                        while (getCourse.hasNext()) {
                            Course result2 = getCourse.next();
                            if (result2.getCourseId().equals(courseId)) {
                                result2.addFaculty(new Faculty(result1.getfacultyId(), result1.getfacultyName()));
                                System.out.println("Course added successfully: " + result2.getFacultyId().toString());
                                break;
                            }
                        }
                    }
                }
                System.out.print("Do you want to add another more faculty to this course (yes=1/no=2) :");
                continueAdd = scanner.nextInt();

            } while (continueAdd == 1);

        } else {
            System.out.println("Course with ID " + courseId + " already exists.");
        }
    }

    public void removeCourseByUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Course ID to remove:");
        String courseId = scanner.next();

        Course courseToRemove = findCourseById(courseId);

        if (courseToRemove != null) {
            courseSet.remove(courseToRemove);
            System.out.println("Course removed successfully: " + courseToRemove);
        } else {
            System.out.println("Course with ID " + courseId + " not found.");
        }
    }

    public void searchCourseByUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Course ID to search:");
        String courseId = scanner.next();

        Course foundCourse = findCourseById(courseId);

        if (foundCourse != null) {
            System.out.println("Found Course: " + foundCourse);
        } else {
            System.out.println("Course not found.");
        }
    }

    public void editCourseDetail() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Course ID to edit:");
        String courseId = scanner.next();

        Course courseToEdit = findCourseById(courseId);

        if (courseToEdit != null) {
            System.out.println("Current Course Details:");
            System.out.println(courseToEdit);

            System.out.println("Enter new Course Name (or press Enter to keep the current name):");
            String newCourseName = scanner.next();
            scanner.nextLine();
            if (!newCourseName.isEmpty()) {
                courseToEdit.setCourseName(newCourseName);
            }
            System.out.println("Course details updated successfully: " + courseToEdit);
        } else {
            System.out.println("Course with ID " + courseId + " not found.");
        }
    }

    public void addProgramToCourse() {
        Scanner scanner = new Scanner(System.in);
        int continueAdd = 0;

        System.out.println("Enter Course ID to add a program:");
        String courseId = scanner.next();

        Course courseToAddProgram = findCourseById(courseId);

        if (courseToAddProgram != null) {
            System.out.println("Available Programs:");
            displayAllPrograms(programmeSet);

            do {
                System.out.print("Enter Program ID to add to the course: ");
                String programId = scanner.next();

                Programme programToAdd = findProgramById(programId);

                if (programToAdd != null) {
                    courseToAddProgram.addProgramme(programToAdd);
                    System.out.println("Program added to the course successfully.");
                    System.out.println("Updated Course Details: " + courseToAddProgram);
                } else {
                    System.out.println("Program with ID " + programId + " not found.");
                }

                System.out.print("Do you want to add another program to this course (yes=1/no=2): ");
                continueAdd = scanner.nextInt();

            } while (continueAdd == 1);

        } else {
            System.out.println("Course with ID " + courseId + " not found.");
        }
    }

    private Programme findProgramById(String programId) {
        Iterator<Programme> iterator = programmeSet.getIterator();

        while (iterator.hasNext()) {
            Programme program = iterator.next();
            if (program.getProgrammeCode().equals(programId)) {
                return program;
            }
        }

        return null;
    }

    public void removeProgramFromCourse() {
        Scanner scanner = new Scanner(System.in);

        // Display all courses and programs for user reference
        displayAllCourses(courseSet);
        displayAllPrograms(programmeSet);

        System.out.println("Enter Course ID to remove a program:");
        String courseId = scanner.next();

        Course courseToRemoveProgram = findCourseById(courseId);

        if (courseToRemoveProgram != null) {
            System.out.println("Enter Program ID to remove:");
            String programId = scanner.next();

            // Find the Programme object based on the programId
            Programme programToRemove = findProgramById(programId);

            if (programToRemove != null) {
                if (courseToRemoveProgram.removeProgramme(programToRemove)) {
                    System.out.println("Program removed successfully from Course " + courseId + ": " + programId);
                } else {
                    System.out.println("Program with ID " + programId + " not found in Course " + courseId + ".");
                }
            } else {
                System.out.println("Program with ID " + programId + " not found.");
            }
        } else {
            System.out.println("Course with ID " + courseId + " not found.");
        }
    }

    private void displayAllPrograms(SetInterface<Programme> programmeSet) {
        Iterator<Programme> iterator = programmeSet.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public SetInterface<Course> getCoursesForProgramme(String programmeId) {
        SetInterface<Course> coursesForProgramme = new Set<>();

        // Assuming you have a method to find the programme by ID
        Programme programme = findProgrammeById(programmeId);

        if (programme != null) {
            // Iterate through all courses and check if they are associated with the given programme
            Iterator<Course> iterator = courseSet.getIterator();
            while (iterator.hasNext()) {
                Course course = iterator.next();
                if (course.containsProgram(programme)) {
                    coursesForProgramme.add(course);
                }
            }
        } else {
            System.out.println("Programme with ID " + programmeId + " not found.");
        }
        return coursesForProgramme;
    }

//      Assuming you have a method to find the programme by ID
    private Programme findProgrammeById(String programmeId) {
        Iterator<Programme> iterator = programmeSet.getIterator();

        while (iterator.hasNext()) {
            Programme programme = iterator.next();
            if (programme.getProgrammeCode().equals(programmeId)) {
                return programme;
            }
        }

        return null;
    }

    private void displayAllCourses(SetInterface<Course> courseSet) {
        Iterator<Course> iterator = courseSet.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private Faculty findFacultyById(String facultyId) {
        Iterator<Faculty> iterator = facultySet.getIterator();

        while (iterator.hasNext()) {
            Faculty faculty = iterator.next();
            if (faculty.getfacultyId().equals(facultyId)) {
                return faculty;
            }
        }

        return null;
    }

    private Course findCourseById(String courseId) {
        Iterator<Course> iterator = courseSet.getIterator();

        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }

        return null;
    }
 // Helper method to display added faculties
    private static void displayAddedFaculties(SetInterface<Faculty> addedFaculties) {
        Iterator<Faculty> iterator = addedFaculties.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    // Helper method to display programs added to specified courses
    private static void displayProgramsAddedToCourses(Map<Course, SetInterface<Programme>> programsAddedToCourses) {
        for (Map.Entry<Course, SetInterface<Programme>> entry : programsAddedToCourses.entrySet()) {
            Course course = entry.getKey();
            SetInterface<Programme> programs = entry.getValue();

            System.out.println("\nCourse ID: " + course.getCourseId());
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Programs Added:");

            Iterator<Programme> programIterator = programs.getIterator();
            while (programIterator.hasNext()) {
                System.out.println(programIterator.next());
            }
        }
    }
    

    public void generateSummaryReport() {
        // Get current date and time
        String currentDateAndTime = DATE_FORMATTER.format(new Date());

        // Display the summary report
        System.out.println("================================================");
        System.out.println("Course Management System");
        System.out.println("================================================");
        System.out.println("Date and Time: " + currentDateAndTime);
        System.out.println("\nSummary Report:");

        // Display all courses
        System.out.println("\nAll Courses:");
        displayAllCourses(courseSet);

//        // Display added faculties
//        System.out.println("\nAdded Faculties:");
//        displayAddedFaculties(addedFaculties);
//
//        // Display programs added to specified courses
//        System.out.println("\nPrograms Added to Specified Courses:");
//        displayProgramsAddedToCourses(programsAddedToCourses);

    }

   
}

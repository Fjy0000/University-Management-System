/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.Set;
import adt.SetInterface;
import java.util.Objects;

/**
 *
 * @author Fong Jun Yi
 */
public class Student extends StudentCourse {

    private String studentId;
    private String contactNo;
    private String studentName;
    private String studentIc;
    private String studentProgremme;
    private SetInterface<StudentCourse> studentCourse = new Set<>();

    // Student ------------------------------------------------------------------------------
    public Student() {
    }

    public Student(String id, String name, String contactNo, String ic, String progremme) {
        this.studentId = id;
        this.studentName = name;
        this.contactNo = contactNo;
        this.studentIc = ic;
        this.studentProgremme = progremme;
    }

    public Student(String id, String name, String contactNo, String ic, String progremme, SetInterface<StudentCourse> courses) {
        this.studentId = id;
        this.studentName = name;
        this.contactNo = contactNo;
        this.studentIc = ic;
        this.studentProgremme = progremme;
        this.studentCourse = courses;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentIc() {
        return studentIc;
    }

    public void setStudentIc(String studentIc) {
        this.studentIc = studentIc;
    }

    public String getStudentProgremme() {
        return studentProgremme;
    }

    public void setStudentProgremme(String studentProgremme) {
        this.studentProgremme = studentProgremme;
    }

    // Student Course ------------------------------------------------------------------------------
    public SetInterface<StudentCourse> getStudentCourse() {
        return studentCourse;
    }

    public int getStudentCourseSize() {
        return this.studentCourse.getSize();
    }

    public void addStudentCourse(StudentCourse courses) {
        this.studentCourse.add(courses);
    }

    public void removeStudentCourse(StudentCourse courses) {
        this.studentCourse.remove(courses);
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + 
                "\nContactNo: " + contactNo + 
                "\nName: " + studentName + 
                "\nIC: " + studentIc + 
                "\nProgremme: " + studentProgremme + "\n";
    }

    // add by hongli  -----------------------------------------------------------------------------------   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student student = (Student) obj;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}

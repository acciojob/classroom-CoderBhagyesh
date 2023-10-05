package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentHashMap = new HashMap<>();
    HashMap<String, Teacher> teacherHashMap = new HashMap<>();
    HashMap<String, List<String>> studentsCorrespondsTeacher = new HashMap<String, List<String>>();


    public void addStudent(Student student) {
        studentHashMap.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherHashMap.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        List<String> students = studentsCorrespondsTeacher.getOrDefault(teacher, new ArrayList<>());
        students.add(student);
        studentsCorrespondsTeacher.put(teacher, students);
    }

    public Student getStudentByName(String name) {
        return studentHashMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherHashMap.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return studentsCorrespondsTeacher.get(teacher);
    }

    public List<String> getAllStudents() {
        List<String> students = new ArrayList<>();
        for(String student: studentHashMap.keySet()) {
            students.add(student);
        }
        return students;
    }

    public void deleteTeacherByName(String teacher) {
        teacherHashMap.remove(teacher);
    }

    public void deleteAllTeachers() {
        for(String teacher:teacherHashMap.keySet()){
            if(mapDB.containsKey(teacher)) {
                List<String> stringList = mapDB.get(teacher);
                for(String student:stringList) {
                    if (studentHashMap.containsKey(student)){
                        studentHashMap.remove(student);
                    }
                }
                mapDB.remove(teacher);
            }
            teacherHashMap.remove(teacher);
        }
    }
}

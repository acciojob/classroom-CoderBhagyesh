package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {


    HashMap<String,Student> studentHashMap=new HashMap<>();
    HashMap<String,Teacher> teacherHashMap=new HashMap<>();
    HashMap<String,List<String>> mapDB=new HashMap<>();

    public void addStudent(Student student){
        studentHashMap.put(student.getName(),student);
    }
    public  void addTeacher(Teacher teacher){
        teacherHashMap.put(teacher.getName(),teacher);
    }
    public void addStudentTeacherPair(String student ,String teacher){
        List<String> slist=mapDB.containsKey(teacher)==true ?mapDB.get(teacher):new ArrayList<>();
        slist.add(student);
        mapDB.put(teacher,slist);
    }
    public Student getStudentByName(String name){
        return studentHashMap.get(name);
    }
    public Teacher getTeacherByName(String name){
        return teacherHashMap.get(name);
    }
    public List<String> getStudentsByTeacherName(String name){
        return mapDB.get(name);
    }
    public List<String> getAllStudents(){
        List<String> ansList=new ArrayList<>();
        for(String student:studentHashMap.keySet()){
            ansList.add(student);
        }
        return ansList;
    }
    public void deleteTeacherByName(String teacher){
        List<String> slist=mapDB.get(teacher);
        for(String name:slist){
            studentHashMap.remove(name);
        }
        teacherHashMap.remove(teacher);
        mapDB.remove(teacher);
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
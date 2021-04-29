package com.hbu.backend.model.entity.course;

import com.hbu.backend.model.entity.Instructor;
import com.hbu.backend.model.entity.Student;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Data
public class CourseModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(targetEntity = Course.class)
    @JoinColumn(name = "course_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

//    @ManyToOne(targetEntity = Instructor.class)
    @JoinColumn(name = "instructor_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Instructor instructor;

    @OneToMany(mappedBy = "courseModule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Chapter> chapters;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "courseModules")
    private Set<Student> students;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "courseModule")
    private Set<Grade> grades;

    public boolean addChapter(Chapter chapter){
        return chapters.add(chapter);
    }

    public boolean updateChapter(Chapter chapter){
        int i = 0;
        for(; i < chapters.size(); i++){
            if(chapters.get(i).getId().equals(chapter.getId())){
                break;
            }
        }
        if(i == chapters.size()){
            return false;
        }
        chapters.set(i, chapter);
        return true;
    }

    public boolean deleteChapter(Chapter chapter){
        return chapters.remove(chapter);
    }

    public Student findStudent(Long studentId){
        for(Student s : students){
            if(s.getId().equals(studentId)){
                return s;
            }
        }
        return null;
    }
    public boolean addStudent(Student student){
        return students.add(student);
    }

    public boolean updateStudent(Student student){
        for(Student s : students){
            if(s.getId().equals(student.getId())){
                students.remove(s);
                students.add(student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(Student student){
        return students.remove(student);
    }

    public Grade findGrade(Long studentId){
        for(Grade g : grades){
            if(g.getStudent().getId().equals(studentId)){
                return g;
            }
        }
        return null;
    }

    public boolean addGrade(Grade grade){
        return grades.add(grade);
    }

    /**
     * Update the Grade referred by a student
     * @param student
     * @param newGradeValue
     * @return updated successful return true, otherwise return false
     */
    public boolean updateGrade(Student student, Double newGradeValue){
        for(Grade g : grades){
            if(g.getStudent().getId().equals(student.getId())){
                grades.remove(g);
                Grade newGrade = new Grade();
                newGrade.setStudent(student);
                newGrade.setGradeValue(newGradeValue);
                grades.add(newGrade);
                return true;
            }
        }
        return false;
    }

    public boolean updateGrade(Grade grade){
        for(Grade g : grades){
            if(g.getId().equals(grade.getId())){
                grades.remove(g);
                grades.add(grade);
                return true;
            }
        }
        return false;
    }

    /**
     * Delete a grade referred by a student, when the student drop from the course module
     * @param student
     * @return delete the grade successfully, return true. Otherwise, return false;
     */
    public boolean deleteGrade(Student student){
        Grade foundGrade = null;
        for(Grade g : grades){
            if(g.getStudent().getId().equals(student.getId())){
                foundGrade = g;
                break;
            }
        }
        if(foundGrade == null){
            return false;
        }
        return grades.remove(foundGrade);
    }



    //    @ElementCollection
//    @ManyToMany(cascade = CascadeType.ALL)
////    @JoinColumn(name = "student_Id")
//    @JoinTable(name = "coursemodule_student_mapping",
//            joinColumns = {@JoinColumn(name = "coursemodule_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "student_Id", referencedColumnName = "id")})
//    @MapKeyJoinColumn(name = "student_id")
//    private Map<Student, Double> studentToScore;


//    public boolean addStudentAndScore(Student student){
//        if(studentToScore.containsKey(student)){
//           return false;
//        }
//        studentToScore.put(student, 0.0);
//        return true;
//    }
//
//    public boolean updateStudentGrade(Student student, Double grade){
//        if(!studentToScore.containsKey(student)){
//            return false;
//        }
//        studentToScore.put(student, grade);
//        return true;
//    }

//    public boolean deleteStudent(Student student){
//        if(!studentToScore.containsKey(student)){
//            return false;
//        }
//        studentToScore.remove(student);
//        return true;
//    }
}

package com.github.ratomidev.service;

import com.github.ratomidev.entity.Course;
import com.github.ratomidev.entity.Lesson;
import com.github.ratomidev.entity.StudentProgress;
import com.github.ratomidev.repository.CourseRepository;
import com.github.ratomidev.repository.LessonRepository;
import com.github.ratomidev.repository.StudentProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProgressService {
    @Autowired
    private StudentProgressRepository studentProgressRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public List<StudentProgress> getAllProgress() {
        return studentProgressRepository.findAll();
    }

    public Optional<StudentProgress> getProgressById(Long id) {
        return studentProgressRepository.findById(id);
    }

    public List<StudentProgress> getProgressByCourseId(Long courseId) {
        return studentProgressRepository.findByCourse_Id(courseId);
    }

    public List<StudentProgress> getProgressByLessonId(Long lessonId) {
        return studentProgressRepository.findByLesson_Id(lessonId);
    }

    public List<StudentProgress> getProgressByStudentId(Long studentId) {
        return studentProgressRepository.findByStudentId(studentId);
    }

    public List<StudentProgress> getProgressByCourseIdAndLessonId(Long courseId, Long lessonId) {
        return studentProgressRepository.findByCourse_IdAndLesson_Id(courseId, lessonId);
    }

    public StudentProgress createProgress(Long courseId, Long lessonId, StudentProgress progress) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        progress.setCourse(course);
        progress.setLesson(lesson);
        return studentProgressRepository.save(progress);
    }

    /**
     * Create a progress entry directly from a populated StudentProgress object
     * 
     * @param progress The fully populated StudentProgress object
     * @return The saved StudentProgress
     */
    public StudentProgress createProgress(StudentProgress progress) {
        return studentProgressRepository.save(progress);
    }

    public StudentProgress updateProgress(StudentProgress progress) {
        return studentProgressRepository.save(progress);
    }

    public void deleteProgress(Long id) {
        studentProgressRepository.deleteById(id);
    }
}
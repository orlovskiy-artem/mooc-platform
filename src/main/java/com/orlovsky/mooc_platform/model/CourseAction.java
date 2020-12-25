package com.orlovsky.mooc_platform.model;

import java.time.ZonedDateTime;
import java.util.UUID;

public class CourseAction {
    private final Long courseId;
    private final Long studentId;
    private final ActionType actionType;
    private final ZonedDateTime actionTime;
    private final Integer score;

    public CourseAction(Long courseId,
                        Long studentId,
                        ActionType actionType,
                        ZonedDateTime actionTime,
                        Integer score) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.actionType = actionType;
        this.actionTime = actionTime;
        this.score = score;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Integer getScore() {
        return score;
    }

    public Long getStudentId() {
        return studentId;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public ZonedDateTime getActionTime() {
        return actionTime;
    }
}

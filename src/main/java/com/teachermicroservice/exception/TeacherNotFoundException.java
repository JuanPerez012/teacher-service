package com.teachermicroservice.exception;

import static com.teachermicroservice.utils.MessageConstants.TEACHER_NOT_FOUND;

public class TeacherNotFoundException extends RuntimeException{

    public TeacherNotFoundException() {
        super(TEACHER_NOT_FOUND);
    }
}
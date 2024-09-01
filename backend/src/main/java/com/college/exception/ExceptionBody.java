package com.college.exception;

import java.time.ZonedDateTime;
import java.util.List;

public record ExceptionBody (
        String path,
        String message,
        int statusCode,
        ZonedDateTime zonedDateTime,
        List<String> errors
)
{ }

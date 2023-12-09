package com.library.bookpad.common;

import com.library.bookpad.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

@AllArgsConstructor
@Getter
@Setter
public class BookpadException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(BookpadException.class);
    private ExceptionEnum exceptionEnum;

    public BookpadException(Class<?> className, ExceptionEnum exceptionEnum, Throwable cause, String message) {
        super(MessageFormatter.arrayFormat(exceptionEnum.name(), null).getMessage(), cause);
        logger.error("BookpadException: ExceptionEnum: {}, Throwable cause: {}, message: {}, className: {}, methodName: {}", exceptionEnum, cause, message, className.getSimpleName(), getMethodName());
    }

    public BookpadException(Class<?> className, ExceptionEnum exceptionEnum, String message) {
        super(MessageFormatter.arrayFormat(exceptionEnum.name(), null).getMessage());
        logger.error("BookpadException: ExceptionEnum: {}, message: {}, className: {}, methodName: {}", exceptionEnum, message, className.getSimpleName(), getMethodName());
    }

    public BookpadException(Class<?> className, ExceptionEnum exceptionEnum) {
        super(MessageFormatter.arrayFormat(exceptionEnum.name(), null).getMessage());
        logger.error("BookpadException: ExceptionEnum: {}, className: {}, methodName: {}", exceptionEnum, className.getSimpleName(), getMethodName());
    }

    public String getMethodName() {
        return StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .walk(stream -> stream.skip(2).findFirst().get())
                .getMethodName();
    }
}
package ru.javawebinar.topjava.web;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.util.exception.ErrorInfo;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.util.exception.UnprocessableEntityException;

import javax.servlet.http.HttpServletRequest;

/**
 * User: gkislin
 * Date: 23.09.2014
 */
public interface ExceptionInfoHandler {
    LoggerWrapper LOG = LoggerWrapper.get(ExceptionInfoHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)     //404
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE)
    default ErrorInfo handleError(HttpServletRequest req, NotFoundException e) {
        return LOG.getErrorInfo(req.getRequestURL(), e);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT/*, reason = "User with this email already present in application."*/)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    default ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        return LOG.getErrorInfo(req.getRequestURL(), e);
//        throw new DataIntegrityViolationException("User with this email already present in application.");
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 2)
    default ErrorInfo handleError(HttpServletRequest req, UnprocessableEntityException e) {
        return LOG.getErrorInfo(req.getRequestURL(), e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  //500
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @Order(Ordered.LOWEST_PRECEDENCE)
    default ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return LOG.getErrorInfo(req.getRequestURL(), e);
    }
}

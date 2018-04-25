package com.psk.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.psk.bank.model.User;

@RestController
public class ExampleExceptionsController {
    
    public static class CustomException extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CustomException(String msg) {
            super(msg);
        }
    }

    @GetMapping(value = "/throw-1")
    public ModelAndView customException1() throws Exception {
        throw new CustomException("test except");
    }
    
    
    @GetMapping(value = "/throw-2")
    public User exception2() throws Exception {
        throw new Exception("test except");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomException.class)
    public @ResponseBody String handler(Exception ex) {
        return "EXC " + ex.getMessage();
    }
}

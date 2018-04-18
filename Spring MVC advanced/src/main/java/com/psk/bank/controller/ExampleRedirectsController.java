package com.psk.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExampleRedirectsController {

    @GetMapping(value = "/do-and-redirect-1")
    public ModelAndView doAndRedirect1() {
        return new ModelAndView("redirect:/destination");
    }

    @GetMapping(value = "/do-and-redirect-2")
    public String doAndRedirect2() {
        return "redirect:/destination";
    }

    
    
    @GetMapping(value = "/do-and-forward")
    public String doAndForward() {
        return "forward:/destination";
    }

    @GetMapping(value = "/destination")
    public @ResponseBody String redirectDestination() {
        return "redirect destination";
    }
    
    
    ////////////
    
    

    @PostMapping(value = "/consume-produce-example", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String consumeTextProduceTextExample(@RequestBody String input) {
        return input + " consume Text";
    }
    
    
    @PostMapping(value = "/consume-produce-example2", produces = MediaType.APPLICATION_PDF_VALUE, consumes = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody String consumeProduceExample2(@RequestBody String input) {
        return input + " and output";
    }

    @PostMapping(value = "/consume-produce-entity-example", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> consumeProduceExampleWithEntites(RequestEntity<String> input) {

        return ResponseEntity.ok(input.getBody() + " and output");
    }

    /////////Response Status
    
    
    
    @ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
    @PostMapping(value = "/response-status-example", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String responseStatusExample(@RequestBody String input) {
        return input + " and output";
    }

    @PostMapping(value = "/response-status-entity-example", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> responseStatusExampleWithEntites(RequestEntity<String> input) {

        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(input.getBody() + " and output");
    	
    }
    
    //@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
    @PostMapping(value = "/response-status-with-exception", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String responseStatusException(@RequestBody String input) throws Exception {
        throw new CustomException("nothing");
    }

    
    /////
    
    
    @ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
    public static class CustomException extends Exception {

        private static final long serialVersionUID = -2835204380228475592L;

        public CustomException(final String msg) {
            super(msg);
        }
    }

}

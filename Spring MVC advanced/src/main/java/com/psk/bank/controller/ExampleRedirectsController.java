package com.psk.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.psk.bank.model.User;
import com.psk.bank.repository.UserRepository;

@Controller
public class ExampleRedirectsController {

	
	@Autowired
	UserRepository userRepository;
	
	
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
    
    
    @PutMapping(value = "/updateResource", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User updateResource(@RequestBody User input) {
        return input;
    }
    
    

    @PostMapping(value = "/consume-produce-entity-example", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> consumeProduceExampleWithEntites(RequestEntity<String> input) {

        return ResponseEntity.ok("produce xml");
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
    
    
	@RequestMapping(value = "/response-status-exception-example", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> responseStatusExampleWithException() throws CustomException {
		throw new CustomException("exception");
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

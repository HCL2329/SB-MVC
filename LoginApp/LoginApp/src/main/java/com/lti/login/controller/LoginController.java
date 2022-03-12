package com.lti.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.lti.login.model.User;
import com.lti.login.repo.UserRepository;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    
    
   @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/loginForm")

    public String loginPage(){
        return "login";
    }


    
    @RequestMapping("/login")

    public String loginHomePage(@RequestParam("userName")String userName, @RequestParam("password") String password, Model model){

        User u=null;
        try{
            u=userRepository.findByName(userName);
        }catch (Exception e){
            System.out.println("User Not Found...");

        }
        if(u!=null) {
            model.addAttribute("UserName", userName);
            return "home";
        }
        model.addAttribute("error","User Not Found");
        return "login";
    }
    
    //http://localhost:8080/register
    @RequestMapping("/register")
    public String goToRegisterPage() {
    	return "register";
    }
    
    
    //http://localhost:8080/register
    @RequestMapping("/set-user")
    public String goToRegisterMicroservice(@RequestParam("userName")String userName,@RequestParam("password1")String password1,
    		@RequestParam("password2")String password2,Model model) {
    	if(password1.equals(password2)) {
    		
    		restTemplate.getForObject("http://localhost:8090/register-user/"+userName+"/"+password1,String.class);
    		//	restTemplate.getForObject("http://localhost:8090/register-user/"+userName+"/"+password1,String.class);
    		model.addAttribute("success","sucessfully register,kindly login to continue");
    	}else {
    		model.addAttribute("registrationError","Password not same");
    	}
    	return "login";
    }
}

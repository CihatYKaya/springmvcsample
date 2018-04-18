package bootsample.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bootsample.model.CustomUserDetails;
import bootsample.model.User;
import bootsample.model.UserCommand;
import bootsample.model.Users;
import bootsample.service.UserService;

@Controller
public class MyController {

	private Log log = LogFactory.getLog(MyController.class);

	@Autowired
	private UserService userService;

	@GetMapping
	public String login(Model model, HttpServletRequest request) {
		model.addAttribute("user", new UserCommand());
		SecurityContext context = SecurityContextHolder.getContext();
		Object obj = context.getAuthentication().getAuthorities().toArray()[0];
		request.setAttribute("role", obj.toString());
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Model model, @ModelAttribute("user") UserCommand cud, String error, String logout, HttpServletRequest request) {
		SecurityContext context = SecurityContextHolder.getContext();
		Object obj = context.getAuthentication().getAuthorities().toArray()[0];
		request.setAttribute("role", obj.toString());
		ModelAndView mav = new ModelAndView();
		model.addAttribute("message", "You are logged in as " + context.getAuthentication().getName());
		mav.setViewName("login");
		return mav;
	}
}

package bootsample.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bootsample.model.CustomUserDetails;
import bootsample.model.Task;
import bootsample.model.UserCommand;
import bootsample.model.Users;
import bootsample.service.TaskService;
import bootsample.service.UserService;

@Controller
@RequestMapping("/task")
public class MainController {

	private Log log = LogFactory.getLog(MainController.class);

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String home(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		SecurityContext context = SecurityContextHolder.getContext();
		Object obj = context.getAuthentication().getAuthorities().toArray()[0];
		request.setAttribute("role", obj.toString());
		if (request.getAttribute("url") == null)
			request.setAttribute("url", request.getRequestURL());
		log.info("url  " + request.getRequestURL());
		return "index";
	}

	public void setRole() {

	}

	public String login(Model model, @ModelAttribute("user") UserCommand cud, String error, String logout,
			HttpServletRequest request) {
		SecurityContext context = SecurityContextHolder.getContext();
		Object obj = context.getAuthentication().getAuthorities().toArray()[0];
		log.info("obj  " + obj);
		if (context.getAuthentication().isAuthenticated()) {
			Optional<Users> optionalUsers = userService.findByName(cud.getName());
			if (!optionalUsers.isPresent()) {
				request.setAttribute("error", "Kullanıcı bulunamadı!");
				model.addAttribute("error", "Kullanıcı bulunamadı!");
				log.info("Kullanıcı bulunamadı!");
				return "login";
			}
			CustomUserDetails cudDao = optionalUsers.map(CustomUserDetails::new).get();
			log.info(cudDao.getEmail() + " - " + cudDao.getPassword());
			if (!cud.getName().equals(cudDao.getEmail()) || !cud.getPassword().equals(cudDao.getPassword())) {
				request.setAttribute("error", "Kullanıcı adı veya şifre yanlış");
				model.addAttribute("error", "Kullanıcı adı veya şifre yanlış");
				log.info("Kullanıcı adı veya şifre yanlış");
				return "login";
			}
		}
		request.setAttribute("role", obj.toString());
		return "index";
	}

	@PostMapping
	public String homePost(Model model, @ModelAttribute("user") UserCommand cud, String error, String logout,
			HttpServletRequest request) {
		String s = login(model, cud, error, logout, request);
		request.setAttribute("mode", "MODE_HOME");
		return s;
	}

	@GetMapping("/all-tasks")
	public String allTasks(HttpServletRequest request) {
		request.setAttribute("tasks", taskService.findAll());
		request.setAttribute("mode", "MODE_TASKS");
		SecurityContext context = SecurityContextHolder.getContext();
		Object obj = context.getAuthentication().getAuthorities().toArray()[0];
		request.setAttribute("role", obj.toString());
		if (request.getAttribute("url") == null)
			request.setAttribute("url", request.getRequestURL());
		log.info("url  " + request.getRequestURL());
		return "index";
	}

	@PostMapping("/all-tasks")
	public String allTaskPost(Model model, @ModelAttribute("user") UserCommand cud, String error, String logout,
			HttpServletRequest request, HttpServletResponse response) {
		String s = login(model, cud, error, logout, request);
		request.setAttribute("tasks", taskService.findAll());
		request.setAttribute("mode", "MODE_TASKS");
		return s;
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/new-task")
	public String newTask(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_NEW");
		return "index";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/save-task")
	public String saveTask(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request) {
		task.setDateCreated(new Date());
		taskService.save(task);
		request.setAttribute("tasks", taskService.findAll());
		request.setAttribute("mode", "MODE_TASKS");
		return "index";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/update-task")
	public String updateTask(@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("task", taskService.findTask(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "index";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/delete-task")
	public String deleteTask(@RequestParam int id, HttpServletRequest request) {
		taskService.delete(id);
		request.setAttribute("tasks", taskService.findAll());
		request.setAttribute("mode", "MODE_TASKS");
		return "index";
	}
}

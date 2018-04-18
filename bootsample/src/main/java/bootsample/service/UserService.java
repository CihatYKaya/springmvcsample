package bootsample.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import bootsample.dao.UsersRepository;
import bootsample.model.Users;

@Service
@Transactional
public class UserService {

	private final UsersRepository userRepository;

	public UserService(UsersRepository taskRepository) {
		this.userRepository = taskRepository;
	}
	
	public Optional<Users> findByName(String name) {
		return userRepository.findByName(name);	
	}
	
/*	
	public List<User> findAll(){
		List<User> tasks = new ArrayList<>();
		for(User task : userRepository.findAll()){
			tasks.add(task);
		}
		return tasks;
	}
	
	public User findTask(int id){
		return userRepository.findOne(id);
	}
	
	public void save(User task){
		userRepository.save(task);
	}
	
	public void delete(int id){
		userRepository.delete(id);
	}*/
}

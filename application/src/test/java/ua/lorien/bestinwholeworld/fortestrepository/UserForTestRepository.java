package ua.lorien.bestinwholeworld.fortestrepository;

import ua.lorien.bestinwholeworld.model.User;

public class UserForTestRepository {
	public static User johnDoe(){
		User johnDoe = new User();
		johnDoe.setEmail("johnDoe@gmail.com");
		johnDoe.setUserName("JohnDoe");
		johnDoe.setDisplayUserName("John Doe");
		johnDoe.setPassword("123");
		
		return johnDoe;
	}
	
	public static User samantaFox(){
		User samFox = new User();
		samFox.setEmail("smantaFox@gmail.com");
		samFox.setUserName("Sam");
		samFox.setDisplayUserName("Samanta");
		samFox.setPassword("123");
		
		return samFox;
	}
	
	public static User billKid(){
		User billyKid = new User();
		billyKid.setEmail("billyKid@gmail.com");
		billyKid.setUserName("Kid");
		billyKid.setDisplayUserName("Billy");
		billyKid.setPassword("123");
		
		return billyKid;
	}
	
	public static User userWithId(Long id){
		User user = johnDoe();
		user.setId(id);
		return user;
	}
}

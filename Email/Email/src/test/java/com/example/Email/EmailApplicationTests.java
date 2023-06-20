package com.example.Email;

import com.example.Email.data.models.Message;
import com.example.Email.data.models.User;
import com.example.Email.data.repositories.MessageRepository;
import com.example.Email.data.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageRepository messageRepository;

	@Test
	public void testSaveUser() {
		User user = new User();
		userRepository.save(user);
		long size = userRepository.count();
		Assertions.assertEquals(1,size);
	}

	@Test
	public void testSaveMessage() {
		Message message = new Message();
		messageRepository.save(message);
		long size = messageRepository.count();
		Assertions.assertEquals(1,size);
	}

}

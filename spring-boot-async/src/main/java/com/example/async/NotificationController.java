package com.example.async;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

	@Autowired
	private EmailSenderTask asyncTask;
	
	@GetMapping({"/", "/{count}"})
	public String sendEmail(@PathVariable("count") Optional<Integer> count) {
		System.out.println("Start sendEmail - "+Thread.currentThread().getName());
		asyncTask.sendEmail(prepareEmailList(count.isPresent() ? count.get() : 1));
		System.out.println("End sendEmail - "+Thread.currentThread().getName());
		return "Email sending process starts";
	}
	
	private List<String> prepareEmailList(int n) {
		List<String> emails = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			emails.add("test" + i + "@gmail.com");
		}
		
		return emails;
	}
	
}

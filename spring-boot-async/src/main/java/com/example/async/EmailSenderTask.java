package com.example.async;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderTask {

	@Async
	void sendEmail(List<String> emailList) {
		emailList.forEach(email -> {
			try {
				Thread.sleep(1000);
				System.out.println("Sending email to : " + 
						email + " by thread - " + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
}
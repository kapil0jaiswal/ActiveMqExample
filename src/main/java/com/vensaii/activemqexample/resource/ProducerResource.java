/**
 * 
 */
package com.vensaii.activemqexample.resource;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kapil
 *
 */
@RestController
@RequestMapping("/api")
public class ProducerResource {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	Queue queue ;
	
	@GetMapping("/produce/{message}")
	public String send(@PathVariable("message")String message) {
		jmsTemplate.convertAndSend(queue,message);
		return "published Successfully";
	}

	@GetMapping("/consume")
	public String recieve() {
		return "Recieved message : "+jmsTemplate.receiveAndConvert("standalone.queue");
	}
}

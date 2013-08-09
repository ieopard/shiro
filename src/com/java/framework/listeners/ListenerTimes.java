package com.java.framework.listeners;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;


@Component
public class ListenerTimes implements ApplicationListener<ApplicationEvent>{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
		if(event instanceof RequestHandledEvent){
			
			RequestHandledEvent rhe = (RequestHandledEvent) event;
			
			System.out.println(rhe.toString());
		}
		
	}

}

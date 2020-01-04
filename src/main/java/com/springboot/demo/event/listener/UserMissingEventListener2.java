package com.springboot.demo.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.springboot.demo.event.UserMissingEvent;

@Component
@Order(2)
public class UserMissingEventListener2 {

	@EventListener
	@Order(30)
	@Async // 异步执行
	public void onApplicationEvent3(UserMissingEvent event) {
		String id = event.getUserId();
		Object source = event.getSource();
		System.out.println("333 --"+ Thread.currentThread().getId() +"--> USER 【" + id + "】 is missing in cache" + " ==> " + source.getClass().getCanonicalName());
	}
	
	@EventListener
	@Order(20)
	@Async // 异步执行
	public void onApplicationEvent2(UserMissingEvent event) {
		String id = event.getUserId();
		Object source = event.getSource();
		System.out.println("222 --"+ Thread.currentThread().getId() +"--> USER 【" + id + "】 is missing in cache" + " ==> " + source.getClass().getCanonicalName());
	}
}

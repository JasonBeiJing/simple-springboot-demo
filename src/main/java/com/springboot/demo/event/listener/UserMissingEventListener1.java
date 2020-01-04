package com.springboot.demo.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.springboot.demo.event.UserMissingEvent;

@Component
@Order(1)
public class UserMissingEventListener1 implements ApplicationListener<UserMissingEvent> {

	// 同步执行【产生事件的主线程】
	
	@Override
	public void onApplicationEvent(UserMissingEvent event) {
		String id = event.getUserId();
		Object source = event.getSource();
		System.out.println("111 --"+ Thread.currentThread().getId() +"--> USER 【" + id + "】 is missing in cache" + " ==> " + source.getClass().getCanonicalName());
	}
}

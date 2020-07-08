package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtx;
import spring.Client;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("before ctx create!!");
		AbstractApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class);
		System.out.println("after ctx create");

		Client client = ctx.getBean(Client.class);
		System.out.println("after getBean");
		client.send();
		System.out.println("after send");

		ctx.close();
		System.out.println("after close");
	}

}
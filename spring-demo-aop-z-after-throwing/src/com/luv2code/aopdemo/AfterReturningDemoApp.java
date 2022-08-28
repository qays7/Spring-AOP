package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class AfterReturningDemoApp {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

		List<Account> theAccounts = null;

		try {
			theAccounts = theAccountDAO.findAccounts(false);

		} catch (Exception e) {
			System.err.println("\n\nMain Program ... caught exception: " + e);
		}

		System.out.println("\n\nMain program: AfterThrowingDemoApp");
		System.out.println("----");
		System.out.println(theAccounts);
		System.out.println("\n");

		context.close();
	}

}

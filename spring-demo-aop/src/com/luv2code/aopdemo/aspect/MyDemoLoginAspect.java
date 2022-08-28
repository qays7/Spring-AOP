package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoginAspect {

//	@Before("execution(public void addAccount())")
//	@Before("execution(void add*())")
//	@Before("execution(* add*())")
//	@Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
//	@Before("execution(* add*(..))") // for all methods with args or none args
//	@Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
//	public void beforeAddAccountAdvice() {
//		System.out.println("\n=====> Executing @Before advice on method()");
//	}

//	@Before("execution(public void add*())")
//	public void beforeAddAccountAdvice() {
//		System.out.println("\n=====> Executing @Before advice on method()");
//	}

//	@Before("execution(public void addAccount())")
//	public void beforeAddAccountAdvice() {
//		System.out.println("\n=====> Executing @Before advice on method()");
//	}

//	@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
//	public void beforeAddAccountAdvice() {
//		System.out.println("\n=====> Executing @Before advice on method()");
//	}
	
	public void beforeAddAccountAdvice() {
		System.out.println("\n=====> Executing @Before advice on method()");
	}
}

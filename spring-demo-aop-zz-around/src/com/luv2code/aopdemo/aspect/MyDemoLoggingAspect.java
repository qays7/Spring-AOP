package com.luv2code.aopdemo.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

		String method = theProceedingJoinPoint.getSignature().toShortString();

		System.out.println("\n=====>>> Executing @Around  on method: " + method);

		long begin = System.currentTimeMillis();

		Object result = theProceedingJoinPoint.proceed();

		long end = System.currentTimeMillis();

		long duration = end - begin;

		System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

		return result;
	}

	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {

		String method = theJoinPoint.getSignature().toShortString();

		System.out.println("\n=====>>> Executing @After (finally) on method: " + method);

	}

	@AfterThrowing(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {

		String method = theJoinPoint.getSignature().toShortString();

		System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

		System.out.println("\n=====>>> The Exception is:  " + theExc);
	}

	@AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

		String method = theJoinPoint.getSignature().toShortString();

		System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

		System.out.println("\n=====>>> result is:  " + result);

		convertAccountNamesToUpperCase(result);

		System.out.println("\n=====>>> result is:  " + result);

	}

	private void convertAccountNamesToUpperCase(List<Account> result) {
		result.forEach(account -> account.setName(account.getName().toUpperCase()));
	}

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {

		System.out.println("\n=====>>> Executing @Before advice on method()");

		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

		System.out.println("Method: " + methodSig);

		Object[] args = theJoinPoint.getArgs();

		Arrays.asList(args).forEach(arg -> {

			System.out.println(arg);

			if (arg instanceof Account) {

				Account theAccount = (Account) arg;

				System.out.println("account name: " + theAccount.getName());
				System.out.println("account level: " + theAccount.getLevel());

			}
		});
	}

}

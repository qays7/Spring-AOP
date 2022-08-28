package com.luv2code.aopdemo.aspect;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

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

	private Logger myLogger = Logger.getLogger(MyDemoLoggingAspect.class.getName());

	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

		String method = theProceedingJoinPoint.getSignature().toShortString();

		myLogger.info("\n=====>>> Executing @Around  on method: " + method);

		long begin = System.currentTimeMillis();

		Object result = theProceedingJoinPoint.proceed();

		long end = System.currentTimeMillis();

		long duration = end - begin;

		myLogger.info("\n=====> Duration: " + duration / 1000.0 + " seconds");

		return result;
	}

	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {

		String method = theJoinPoint.getSignature().toShortString();

		myLogger.info("\n=====>>> Executing @After (finally) on method: " + method);

	}

	@AfterThrowing(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {

		String method = theJoinPoint.getSignature().toShortString();

		myLogger.info("\n=====>>> Executing @AfterThrowing on method: " + method);

		myLogger.info("\n=====>>> The Exception is:  " + theExc);
	}

	@AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

		String method = theJoinPoint.getSignature().toShortString();

		myLogger.info("\n=====>>> Executing @AfterReturning on method: " + method);

		myLogger.info("\n=====>>> result is:  " + result);

		convertAccountNamesToUpperCase(result);

		myLogger.info("\n=====>>> result is:  " + result);

	}

	private void convertAccountNamesToUpperCase(List<Account> result) {
		result.forEach(account -> account.setName(account.getName().toUpperCase()));
	}

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {

		myLogger.info("\n=====>>> Executing @Before advice on method()");

		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

		myLogger.info("Method: " + methodSig);

		Object[] args = theJoinPoint.getArgs();

		Arrays.asList(args).forEach(arg -> {

			myLogger.info(arg.toString());

			if (arg instanceof Account) {

				Account theAccount = (Account) arg;

				myLogger.info("account name: " + theAccount.getName());
				myLogger.info("account level: " + theAccount.getLevel());

			}
		});
	}

}

package com.luv2code.aopdemo.dao;

import java.util.Arrays;
import java.util.List;


import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {

	private String name;

	private String serviceCode;
	
	public List<Account> findAccounts(boolean tripWire) {
		
		if(tripWire)
			throw new RuntimeException("No soup for you!!!");
		
		List<Account> myAccount = Arrays.asList(new Account("Qays", "Silver"), new Account("Morad", "Platinum"),
				new Account("Thaer", "Gold"));
		return myAccount;
	}

	public void addAccount(Account account, boolean vipFlag) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
	}

	public boolean doWork() {
		System.out.println(getClass() + ": doWork()");
		return false;
	}

	public String getName() {
		System.out.println(getClass() + ": in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": in setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + ": getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + ": setServiceCode()");
		this.serviceCode = serviceCode;
	}

}

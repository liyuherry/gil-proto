package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import common.data.Student.AddressBook;
import common.data.Student.Person;

public class ListStudent {
	static void print(AddressBook addressBook){
		for(Person person : addressBook.getPersonList()){
			System.out.println("Student ID=" + person.getId());
			System.out.println("Student name=" + person.getName());
			System.out.println("Student Email=" + person.getEmail());
			for(Person.PhoneNumber phoneNumber : person.getPhoneList()){
				switch (phoneNumber.getType()) {
				case HOME:
					System.out.println(" HOME phone!");
					break;
					
				case MOBILE:
					System.out.println(" MOBILE phone!");
					break;

				case WORK:
					System.out.println(" WORK phone!");
					break;	
				}
				
				System.out.println(" phoneNumber.getNumble=" + phoneNumber.getNumber());
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		if(args.length != 1){
			System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE");
			System.exit(-1);
		}
		
		AddressBook addressBook = AddressBook.parseFrom(new FileInputStream(args[0]));
		print(addressBook);
	}
}

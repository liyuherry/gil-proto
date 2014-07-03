package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import common.data.Student.AddressBook;
import common.data.Student.Person;

public class AddPerson {
	static Person PromptForAddress(BufferedReader stdin,
            PrintStream stdout) throws IOException{
		Person.Builder person = Person.newBuilder();
		
		stdout.print(" Student ID=");
		person.setId(Integer.valueOf(stdin.readLine()));
		
		stdout.print(" Student name=");
		person.setName(stdin.readLine());
		
		while (true) {
			stdout.print("Enter a phone number (or leave blank to finish): ");
		      String number = stdin.readLine();
		      if (number.length() == 0) {
		        break;
		      }

		      Person.PhoneNumber.Builder phoneNumber =
		        Person.PhoneNumber.newBuilder().setNumber(number);

		      stdout.print("Is this a mobile, home, or work phone? ");
		      String type = stdin.readLine();
		      if (type.equals("mobile")) {
		        phoneNumber.setType(Person.PhoneType.MOBILE);
		      } else if (type.equals("home")) {
		        phoneNumber.setType(Person.PhoneType.HOME);
		      } else if (type.equals("work")) {
		        phoneNumber.setType(Person.PhoneType.WORK);
		      } else {
		        stdout.println("Unknown phone type.  Using default.");
		      }

		      person.addPhone(phoneNumber);
		    }

		    return person.build();
	}
	
	public static void main(String[] args) throws IOException {
//		if(args.length != 1){
//			System.err.println("Usage:  AddPerson ADDRESS_BOOK_FILE");
//			System.exit(-1);
//		}
		
		AddressBook.Builder addressBook = AddressBook.newBuilder();
		
//		try{
//			addressBook.mergeFrom(new FileInputStream(args[0]));
//		}catch (FileNotFoundException  e) {
//			 System.out.println(args[0] + ": File not found.  Creating a new file.");
//		}
		
		addressBook.addPerson(PromptForAddress(
				new BufferedReader(new InputStreamReader(System.in)), System.out));
		
//		FileOutputStream out = new FileOutputStream(args[0]);
//		
//		addressBook.build().writeTo(out);
		
		byte[] addressBytes = addressBook.build().toByteArray();
		
		AddressBook.Builder newAddressBook = AddressBook.newBuilder();
		
		newAddressBook.mergeFrom(addressBytes);
		
		print(newAddressBook.build());
//		out.close();
	}
	
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
}

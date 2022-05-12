/*
 * Name: Prem Patel
 * Date: 4/17/2022
 * CSCI 3341: Multi-Threading Assignment
 */
import java.util.*;
import java.io.*;

public class main {
	static char alphabet[] = {
			'A','B','C','D', 'E','F','G','H',
			'I','J','K','L',
			'M','N','O','P', 'Q','R','S','T',
			'U','V','W','X',
			'Y','Z'
			};
	static String substring1, substring2, substring3, substring4;
	public static void main(String[] args) {
		String input;
		try (
			Scanner reader = new Scanner(new File("src/records"));
			Scanner userI = new Scanner(System.in);
		){
			// Create ArrayList & Fill it with input retrieved from the file
			ArrayList<String> string = new ArrayList<>();
			
			while(reader.hasNextLine()) {
				string.add(reader.nextLine());
			}
			
			// Send every input in file to their respective threads
			for (int i = 0; i <= string.size() - 1; i++) {
				input = string.get(i);	
				
				
				// Code for hard-coded input
			//	input = "** PQLG 4 3 2 7*** ABCDEF 1 2 3 5 2 1 0 2 4*M FSSO ****17 -4 18 9 6 0";
				
				/*	Code for User-Input
				 	System.out.print("Enter a String input: ");
					input = userI.nextLine();
				*/
				
				// Divider
				System.out.printf("%n---------------------------------------------------------------------------------------------------------%n");

				// Checks to see whether the string is valid (has enough *s)
				int astCount =0;
				for (int x = 0; x < input.length(); x++) {
					if (input.charAt(x) == '*') {
						astCount++;
					}
				}
				// If String is valid, run through threads-- else, print invalid and move to next string
				if(astCount == 10) {
					//System.out.printf("Valid Input Received: %s%nRunning Thread: %d%n", input, 0);

					System.out.printf("Valid Input Recieved: %s%nRunning Thread: %d%n", input, i);
				
				// Obtain parsed Strings 
				obtainSubString(input);

				// Print Substitute Input & Run Substitute Thread
				System.out.printf("%nSubstitute Input:\t%s%nSubstitue Translation:\t\t", substring1);
				Substitute subs = new Substitute(substring1, alphabet, i);
				
					// For hard-code or user input testing (comment when not in use)
				//Substitute subs = new Substitute(substring1, alphabet, 0);
				
				
				subs.start();
				subs.join();
				
				// Print Hill Input & Run Hill Thread
				System.out.printf("%nHill Input:\t\t%s%n ", substring2);
				 Hill hill = new Hill(substring1, substring2, alphabet, i);
				
				// For hard-code or user input testing (comment when not in use)
				//Hill hill = new Hill(substring1, substring2, alphabet, 0);
				
				hill.start();
				hill.join();
				
				// Print Summit Input & Run Substitute Thread
				System.out.printf("%nSummit Input:\t\t%s%n ", substring3);
				Summit sum = new Summit(substring3, alphabet, i);
				
				//// For hard-code or user input testing (comment when not in use)
				//Summit sum = new Summit(substring3, alphabet, 0);
				
				sum.start();
				sum.join();
				
				// If all three threads are finished, then run the fourth (Replace) thread.
				if(!subs.isAlive() && !hill.isAlive() && !sum.isAlive()) {
					System.out.printf("%nReplace Input:\t\t%s%nMessage:\t\t", substring4);
					Replace rep = new Replace(substring4, alphabet, i);
					
					// For hard-code or user input testing (comment when not in use)
					//Replace rep = new Replace(substring4, alphabet, 0);
					
					rep.start();
					rep.join();
				}
			
				} else {
					System.out.printf("%nThe following String is invalid: %s%n", input);
				}
				
				// Uncomment curly brace when using hard-code or user-input
				
				}
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.printf("%s%nPlease verify the file exists and is in the correct pathway listed above."
								, e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	public static void obtainSubString(String input) {
		char seq[] = input.toCharArray();
		int store = 0, temp = 0;
		String substrings[] = new String[4];
		String encode = "";
		boolean cons = false;
		String check = input.concat(input).concat(" ");
		char re [] = check.toCharArray();
		for(int i = 0; i < input.length(); i++) {
			if(seq[i] == '*') {
				store++;
				cons = true;
			} else {
				if(cons) {
					encode += input.charAt(i);
					if (store >= 1) {
						temp = store;
						store = 0;
					}
					substrings[temp - 1] = encode;
					if(i != input.length()) {
						if (re[i+1] == '*') {
							encode = "";
						}
					}
				} 
			}
		}
		substring1 = substrings[0];
		substring2 = substrings[1];
		substring3 = substrings[2];
		substring4 = substrings[3];
	}
}
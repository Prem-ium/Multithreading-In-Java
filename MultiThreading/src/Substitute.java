
public class Substitute extends Thread {
	
	String substring1;
	char alphabet[];
	int threadNum;
	
	public Substitute(String substring1, char[] alphabet, int threadNum){
		this.substring1 = substring1;
		this.alphabet = alphabet;
		this.threadNum = threadNum;
	}
	
	@Override
	public void run() {
		// UpperCase Substring & store First Index
		substring1 = substring1.toUpperCase().stripLeading();
	
		char firstIndex = substring1.charAt(0);
		
		// Error Checking
		if(!Character.isLetter(firstIndex)) {
			System.out.printf("Substitue #%d Thread ERROR: Substring 1 first token constains non-alphabet letter(%c). EXITING. %n", threadNum, firstIndex);
			return;
		}
		
		
		int firstPos = 0;
		for (int i = 0; i < alphabet.length; i++) {
			if (firstIndex == alphabet[i]) {
				firstPos = i;
			}
		}
		int N = (firstPos % 10) + 2;
		int circle = 26;
		substring1 = substring1.substring(1);
		for (int i = 0; i < substring1.length(); i++) {
			
			// Error Checking, non-alpha letters
			if (!Character.isLetter(substring1.charAt(i)) && substring1.charAt(i) != ' ') {
				System.out.printf("Substitue #%d Thread ERROR: Substring 1 constains non-alphabet letters (%c). EXITING. %n", threadNum, substring1.charAt(i));
				return;
			}
			
			for (int j = 0; j < alphabet.length; j++) {
				if (substring1.charAt(i) == alphabet[j]) {
					int translation = substring1.charAt(i) - N;
				
					if (translation < 65) {
						translation = translation + circle;
					}
					System.out.print((char)translation);
				}
			}
		}
		System.out.println();
	}
}

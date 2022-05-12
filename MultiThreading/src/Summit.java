
public class Summit extends Thread {
	private String substring3;
	private char alphabet[];
	private int threadNum;
	
	public Summit(String substring3, char[] alphabet, int threadNum) {
		this.substring3 = substring3;
		this.alphabet = alphabet;
		this.threadNum = threadNum;
	}
	@Override
	public void run() {
		String s1 = "", s2 = "";
		int alphaCount = 1;
		
		for(int i = 0; i < substring3.length(); i++) {
			if(Character.isAlphabetic(substring3.charAt(i))) {
				alphaCount++;
			}
		}
		
		s1 = substring3.substring(0, alphaCount).trim();
		s2 = substring3.substring(alphaCount, substring3.length()).trim();
		
		
		
		for(int i = 0; i < s1.length(); i++) {
			if((!Character.isLetter(s1.charAt(i)))) {
				System.out.printf("Summit #%d Thread ERROR: Section 1 contains non-alphabet characters(%c). EXITING. %n", threadNum, s1.charAt(i));
				return;
			} else if (s1.length() % 3 != 0) {
				System.out.printf("Summit #%d Thread ERROR: Section 1 contains number of characters (%d) not divisible by 3. EXITING. %n", threadNum, s1.length());
				return;
			} 
		}
		int digitCount = 0;
		for(int i = 0; i < s2.length(); i++) {
			if(Character.isDigit(s2.charAt(i))) {
				digitCount++;
			} else if (s2.charAt(i) != ' ' && !Character.isDigit(s2.charAt(i))) {
				System.out.printf("Summit #%d Thread ERROR: Section 2 contains non-digital characters (%c). EXITING. %n", threadNum, s2.charAt(i));
				return;
			}
		}
		String re[] = s2.stripLeading().split(" ");
		
		
		if (re.length != 9 && re.length < 9) {
			System.out.printf("Summit #%d Thread ERROR: Section 2 does not contain 9 digits (%d). EXITING. %n", threadNum, digitCount);
			return;
		}
		String sa = "";
	
		for (int i = 0; i < s2.length(); i++) {
			if (!(s2.charAt(i) == ' ')) {
				sa += s2.charAt(i);
			}
		}
		s2 = sa;
		System.out.printf("%nSummit:\tSection 1:\t%s\tSection 2:\t", s1);
		
		int sc2[] = new int[re.length];
		for(int i = 0; i < re.length; i++) {
			System.out.print(re[i]);
			sc2[i] = Integer.parseInt(re[i]);
			
			if(i != re.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
		int S[][] = new int[3][3];
		
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				
		//for(int row = 0; row < s1.length(); row++) {
		//	for(int col = 0; col < s1.length(); col++) {
				if (row == 0 && col == 0) {
					//S[row][col] = s2.charAt(0) - '0';
					S[row][col] = sc2[0] - 0;
				}
				if (row == 0 && col == 1) {
					//S[row][col] = s2.charAt(1) - '0';
					S[row][col] = sc2[1] - 0;
				}
				if (row == 0 && col == 2) {
					//S[row][col] = s2.charAt(2) - '0';
					S[row][col] = sc2[2] - 0;
				}
				if (row == 1 && col == 0) {
					//S[row][col] = s2.charAt(3) - '0';
					S[row][col] = sc2[3] - 0;
				}
				if (row == 1 && col == 1) {
					//S[row][col] = s2.charAt(4) - '0';
					S[row][col] = sc2[4] - 0;
				}
				if (row == 1 && col == 2) {
					//S[row][col] = s2.charAt(5) - '0';
					S[row][col] = sc2[5] - 0;
				}
				if (row == 2 && col == 0) {
					//S[row][col] = s2.charAt(6) - '0';
					S[row][col] = sc2[6] - 0;
				}
				if (row == 2 && col == 1) {
					//S[row][col] = s2.charAt(7) - '0';
					S[row][col] = sc2[7] - 0;
				}
				if (row == 2 && col == 2) {
					//S[row][col] = s2.charAt(8) - '0';
					S[row][col] = sc2[8] - 0;
				}
			}
		}
			char indexOne = 0;
			char indexTwo = 0;
			char indexThree;
			int pos1 = 0;
			int pos2 = 0;
			int pos3 = 0;
			
			indexOne = s1.charAt(0);
			indexTwo = s1.charAt(1); 
			indexThree = s1.charAt(2);
			
			int p1 = 0, p2 = 0, p3 = 0, modOne = 0, modTwo = 0, modthree = 0;
			System.out.printf("Summit Encryption:\t");
			
			int tim = s1.length() / 3;
			
			for(int i = 0; i < tim; i++) {
				if(i >= 1){
					for(int j = 0; j < s1.length() - 2; j +=3) { 
						indexOne = s1.charAt(j); 
						indexTwo = s1.charAt(j + 1); 
						indexThree = s1.charAt(j + 2);
					}
				}
				for(int j = 0; j < alphabet.length; j++) {
					if(alphabet[j] == indexOne) {
						pos1 = j;
					}
					if(alphabet[j] == indexTwo) {
						pos2 = j;
					}
					if(alphabet[j] == indexThree) {
						pos3 = j;
					}
				}
				if(i < 1){
					int C[][] = {
							{pos1}, {pos2}, {pos3}
					};
					p1 = (S[0][0] * C[0][0]) + (S[0][1] * C[1][0]) + (S[0][2] * C[2][0]); 
					p2 = (S[1][0] * C[0][0]) + (S[1][1] * C[1][0]) + (S[1][2] * C[2][0]); 
					p3 = (S[2][0] * C[0][0]) + (S[2][1] * C[1][0]) + (S[2][2] * C[2][0]); 
					modOne = p1 % 26; 
					modTwo = p2 % 26; 
					modthree = p3 % 26;
					
					System.out.printf("%c%c%c", alphabet[modOne], alphabet[modTwo], alphabet[modthree]);
				} else {
					for(int j = 0; j < s1.length()-2; j +=3) { 
						indexOne = s1.charAt(j); 
						indexTwo = s1.charAt(j + 1); 
						indexThree = s1.charAt(j + 2);
					}
					int C[][] = {
							{pos1}, {pos2}, {pos3}
					};
					p1 = (S[0][0] * C[0][0]) + (S[0][1] * C[1][0]) + (S[0][2] * C[2][0]); 
					p2 = (S[1][0] * C[0][0]) + (S[1][1] * C[1][0]) + (S[1][2] * C[2][0]); 
					p3 = (S[2][0] * C[0][0]) + (S[2][1] * C[1][0]) + (S[2][2] * C[2][0]); 
					modOne = p1 % 26; 
					modTwo = p2 % 26; 
					modthree = p3 % 26;
					
					System.out.printf("%c%c%c%n", alphabet[modOne], alphabet[modTwo], alphabet[modthree]);
				}	
		}
		System.out.println();
	}

}

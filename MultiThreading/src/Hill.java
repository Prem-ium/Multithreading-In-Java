public class Hill extends Thread {
	
	private String substring1, substring2;
	private char[] alphabet;
	private int threadNum;
	
	public Hill(String substring1, String substring2, char[] alphabet, int threadNum) {
		this.substring1 = substring1;
		this.substring2 = substring2;
		this.alphabet = alphabet;
		this.threadNum = threadNum;
	}
	@Override
	public void run() {
		String s1, s2;
		int alphaCount = 1;
		
		for(int i = 0; i < substring2.length(); i++) {
			if(Character.isAlphabetic(substring2.charAt(i))) {
				alphaCount++;
			}
		}
		s1 = substring2.substring(0, alphaCount).trim();
		s2 = substring2.substring(alphaCount, substring2.length()).trim();
		
		
		
		// Stop thread if error. 
		for(int i = 0; i < s1.length(); i++) {
			if((!Character.isLetter(s1.charAt(i))) && s1.charAt(i) != ' ') {
				System.out.printf("Hill #%d Thread ERROR: Section 1 contains non-alphabet characters (%c). EXITING. %n", threadNum, s1.charAt(i));
				return;
			} else if (s1.length() % 2 != 0) {
				System.out.printf("Hill #%d Thread ERROR: Section 1 contains uneven number characters (%d). EXITING. %n", threadNum, s1.length());
				return;
			} 
		}
		
		String re[] = s2.stripLeading().split(" ");
		
		
		for(int i = 0; i < s2.length(); i++) {
			if (s2.charAt(i) != ' ' && Character.isLetter(s2.charAt(i))) {
				System.out.printf("Hill #%d Thread ERROR: Section 2 contains non-digital characters (%c). EXITING. %n", threadNum, s2.charAt(i));
				return;
			}
		}
		
		
		if (re.length != 4) {
			System.out.printf("Hill #%d Thread ERROR: Section 2 does not contain 4 digits(%d). EXITING. %n", threadNum, re.length);
			return;
		}
		
		substring1.toUpperCase();
		String sa = "";
		for (int i = 0; i < s2.length(); i++) {
			if (!(s2.charAt(i) == ' ')) {
				sa += s2.charAt(i);
			}
		}
		s2 = sa;
		System.out.printf("%nHill:\tSection 1:\t%s\tSection 2:\t", s1, s2);
		
		int sc2[] = new int[re.length];
		for(int i = 0; i < re.length; i+=2) {
			System.out.print(re[i] + ", " + re[i + 1]);
			sc2[i] = Integer.parseInt(re[i]);
			sc2[i + 1] = Integer.parseInt(re[i + 1]);
			
			if(i + 2!= re.length) {
				System.out.print(", ");
			}
		}
		
		System.out.println();
		int S[][] = new int[2][2];
		
		for(int row = 0; row < 2; row++) {
			for(int col = 0; col < 2; col++) {
				if (row == 0 && col == 0) {
					S[row][col] = sc2[0] - 0;
				}
				if (row == 0 && col == 1) {
					S[row][col] = sc2[1] - 0;
				}
				if (row == 1 && col == 0) {
					S[row][col] = sc2[2] - 0;
				}
				if (row == 1 && col == 1) {
					S[row][col] = sc2[3] - 0;
				}
			}
		}
		char indexOne = 0;
		char indexTwo;
		int posOne = 0;
		int posTwo = 0;
		
		indexOne = s1.charAt(0);
		indexTwo = s1.charAt(1); 
		
		System.out.printf("Hill Decryption:\t");
		
		for(int i = 0; i < s1.stripLeading().length() / 2; i++) {
			if(i >= 1){
				for(int j = 0; j < s1.length(); j +=2) { 
					indexOne = s1.charAt(j); 
					indexTwo = s1.charAt(j + 1); 
				}
			}
			for(int j = 0; j < alphabet.length; j++) {
				if(alphabet[j] == indexOne) {
					posOne = j;
				}
				if(alphabet[j] == indexTwo) {
					posTwo = j;
				}
			}
			
			int C[][] = {
					{posOne}, {posTwo},
			};
			int p1 = (S[0][0] * C[0][0]) + (S[0][1] * C[1][0]); 
			int p2 = (S[1][0] * C[0][0]) + (S[1][1] * C[1][0]); 
			int firstMod = p1 % 26; 
			int secMod = p2 % 26; 
			
			//System.out.println((S[0][0] * C[0][0]) + (S[0][1] * C[1][0]) + " " + (S[0][0] * C[0][0]) + (S[0][1] * C[1][0]));
			
			//System.out.println();
			
			System.out.printf("%c%c", alphabet[firstMod], alphabet[secMod]);
		}
		System.out.println();
	}
}

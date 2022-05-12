
public class Replace extends Thread {
	private String substring4;
	private char alphabet[];
	int threadNum;
	
	public Replace(String substring4, char[] alphabet, int threadNum) {
		this.substring4 = substring4;
		this.alphabet = alphabet;
		this.threadNum = threadNum;
	}
	
	@Override
	public void run() {
		substring4 = substring4.stripLeading();
		String ip[] = substring4.split(" ");
		int values[] = new int[ip.length];
		int m = 0;
		for(int i = 0; i < ip.length; i++) {
			values[i] = Integer.valueOf(ip[i]);
			m += values[i];
		}
		
		if(m < 0) {
			System.out.printf("Replace #%d Thread ERROR: Sum of digits (%d) is negative. EXITING. %n", threadNum, m);
			return;
		}
		
		m = m % 26;
		
		int[] store = new int[values.length];
		char seq[] = new char[substring4.length()];
		seq[0] = 'N';
		int temp;
		for(int i = 0; i < values.length; i++) {
			temp = Math.abs(m- values[i]);
			store[i] = temp % 26;
			//store[i] = (m- values[i]) % 26;
			if(store[i] < 0) {
				System.out.printf("Replace #%d Thread ERROR: cannot mod negative number (%d - %d = %d). EXITING. %n", threadNum, m, values[i], store[i]);
				return;
			} else {
				seq[i] = alphabet[store[i]];
			}
		}
		String id = String.valueOf(seq);
		id = id.substring(2) + id.substring(0, 2);
		
		
		for(int i = 0; i < id.length(); i++) {
			if (Character.isLetter(id.charAt(i))) {
				System.out.print(id.charAt(i));
			}
		}
	
		
	}
}

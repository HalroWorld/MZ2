
package mz;

import java.awt.EventQueue;

public class MZ_main {
	public static String code;
	public static String code2;
	public static String code3;
	
	public MZ_main() {
		new MZ_login();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MZ_login();
			}
		});
	}
}
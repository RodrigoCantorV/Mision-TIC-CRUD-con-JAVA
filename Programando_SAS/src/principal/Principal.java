package principal;

import java.awt.EventQueue;

import gui.GUIPrincipal;

public class Principal {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
		
			public void run() {
		
				try {
			
					GUIPrincipal frame = new GUIPrincipal();
			
					frame.setVisible(true);
			
				} catch (Exception e) {
				
					e.printStackTrace();
				
				}
			}
		});
	}

}

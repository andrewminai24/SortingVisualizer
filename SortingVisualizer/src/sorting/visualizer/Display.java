package sorting.visualizer;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Display extends Canvas implements Runnable {
	
	
	private JFrame frame;
	private Thread thread;
	private static String title = "sorting visualizer";
	private int width = 1000;
	private int height = 600;
	private boolean running = false;
	
	public Display() {
		frame = new JFrame();
		
		
		
		Dimension size = new Dimension(width,height);
		this.setPreferredSize(size);
		
	}
	public synchronized void start() {
		running = true;
		thread = new Thread(this,"Display");
		thread.start();
	}
	
	public static void main(String[] args) {
		Display display = new Display();
		display.frame.setTitle(title);
		display.frame.add(display);
		display.frame.pack();
		display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.frame.setLocationRelativeTo(null);
		display.frame.setVisible(true);
		
		display.start();
	}
	
	
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		//At the start we record what time it is .
		final double ns = 1000000000.0 / 400;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		
		
		//This is the time in nanoseconds
		
		
		while(running) {
			//We will be comparing now to last time
			//To how many nanoseconds have passed.
			/*
			 Once the number of nanoseconds have passed.
			 It is time to go through the update method.
			 To go through the whole list.
			 */
			
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			/*
			 * Once delta is equal to 1 then we 
			 * know we need to update
			 */
			
			lastTime = now;
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			//This frames will contain our frames per second.
			
			if(System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + frames + "fps");
				
				frames = 0;
				updates = 0;
				//Here
			}
		}
	}
	
	//This method will be drawing visuals to 
	//the screen
	private void render() {
		
	}
	//Logic of the code
	private void update() {
		
	}
	

}

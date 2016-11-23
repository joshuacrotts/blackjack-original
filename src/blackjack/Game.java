package blackjack;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable{

	private Window window;
	
	private Thread thread;
	private boolean running = false;
	
	private int frames;
	private int currentFPS;
	
	private BufferStrategy bs;
	
	public boolean show = false;
	public boolean goAgain = false;
	public boolean isReady = false;
	
	private Deck d;
	
	private AI comp;
	private Player p;
	private GUI gui;
	
	private BufferedImage bg;
	
	public Game(){
		window = new Window(800,600, "BlackJack", this);
		

		
		try {
			bg = ImageIO.read(new File("img/poker-table-background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		d = new Deck();
		
		comp = new AI(this, d, p);
		p = new Player(this, d, comp);
		gui = new GUI(this,d,p,comp);
		
		addMouseListener(p);
		addMouseMotionListener(p);
		addMouseListener(gui);
		addMouseMotionListener(gui);
		
		d.printCards();
		
		d.shuffle();
		
		comp.getHand().add(d.deal());
		comp.getHand().add(d.deal());
		
		p.getHand().add(d.deal());
		p.getHand().add(d.deal());
		
		start();
	}
	
	public synchronized void start(){
		if(running) return;
	
		else{
			thread = new Thread(this);
			thread.start();
			running = true;
		}
	}
	
	public synchronized void stop(){
		if(!running) return;
		
		else{
			try{
				thread.join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			running = false;
		}
	}
	
	public void run(){
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		this.frames = 0;

		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >=1)
			{
				tick();
				delta--;
			}
			if(running)
				render();
			this.frames++;

			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				//System.out.println("FPS: "+ this.frames);
				currentFPS = this.frames;
				this.frames = 0;
			}
		}
		stop();
	}
	
	public void restart(){
		System.out.println("RESTARTING");
		p.getHand().clear();
		comp.getHand().clear();
		d.repopulate();
		show = false;
		
		comp.setLose(false);
		comp.setWin(false);
		p.setLose(false);
		p.setWin(false);
		
		comp.getHand().add(d.deal());
		comp.getHand().add(d.deal());
		
		p.getHand().add(d.deal());
		p.getHand().add(d.deal());
	}
	
	public void tick(){
		
		if(d.isEmpty()){
			restart();
		}
		
		p.tick();
		comp.tick();
		d.tick();
		gui.tick();
		
		if(p.isStay() == true){
			if(comp.isStay() == true){
				if(p.isLose()){
					p.setLose(true);
					p.setStay(false);
					comp.setStay(false);
				}
				else if(comp.isLose()){
					p.setWin(true);
					p.setStay(false);
					comp.setStay(false);
				}
				else if(p.getSum() <= comp.getSum()){
					p.setLose(true);
					p.setStay(false);
					comp.setStay(false);
				}
				else if(p.getSum() > comp.getSum()){
					p.setWin(true);
					p.setStay(false);
					comp.setStay(false);
				}
			}
		}
	}
	
	
	public void render(){
		bs = getBufferStrategy();
		
		if(bs == null){
			this.createBufferStrategy(3);
			return; //make detrimental sure this is here or your program will not work for some odd reason. 
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//DRAW HERE
		g.drawImage(bg,0,0,null);
		
		p.render(g);
		comp.render(g);
		d.render(g);
		gui.render(g);
		
		//END DRAWING
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args){
		new Game();
	}
}

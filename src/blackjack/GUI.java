package blackjack;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class GUI extends MouseAdapter{

	private static Font customFont;
	
	private Game game;
	public boolean goAgain = false;

	public boolean hit = false;
	public boolean stay = false;

	private Deck d;
	private Player p;
	private AI ai;

	public GUI(Game game, Deck d, Player p, AI ai){
		this.game = game;
		this.d = d;
		this.p = p;
		this.ai = ai;
		setFont();
	}

	public void tick(){
		
	}

	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		


		if(mouseOver(mx,my,600,350,100,60) && !p.isLose() && !p.isWin()){
			stay = true;
			p.setStay(true);
			ai.act();
			//do other stuff for AI ONCE THIS HAPPENS
		}
		
		if(mouseOver(mx,my,600,120,100,60) && !p.isLose() && !p.isWin()){
			System.out.println("PLEASE TELL ME THIS IS HITTING?!?!?");
			hit = true;
			p.addCard(d.deal());
			hit = false;//????????????????
		}
		
		if(mouseOver(mx,my,650,350,100,60) && (p.isLose() || p.isWin())){
			game.restart();
			
		}
		

	}


	public void render(Graphics g){
		
		Font defaultFont = g.getFont();
		g.setFont(customFont);
		g.drawString("BlackJack", 570, 530);
		g.drawString("By Joshua Crotts", 400, 560);
		
		g.setFont(defaultFont);
		
		if(p.isLose() || p.isWin()){
			//CONTINUE BUTTON
			g.setColor(Color.BLACK);
			g.fillRect(650, 350, 100, 60);
			g.setColor(Color.WHITE);
			g.drawString("Continue",677,381);
		}
		
		if(!p.isLose() && !p.isWin()){
			g.setColor(Color.WHITE);
			g.drawString("What is your move?", 600,100);
			
			//HIT BUTTON
			g.setColor(Color.BLACK);
			g.fillRect(600, 120, 100, 60);
			g.setColor(Color.WHITE);
			g.drawString("HIT",640,155);

			//STAY BUTTON
			g.setColor(Color.BLACK);
			g.fillRect(600, 350, 100, 60);
			g.setColor(Color.WHITE);
			g.drawString("STAY",635,385);
		}

	}
	
	private static void setFont(){
		customFont = null;
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/oldschool.ttf")).deriveFont(24f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/oldschool.ttf")));
		} catch (IOException e) {
			e.printStackTrace();
		} catch(FontFormatException e) {
			e.printStackTrace();
		}
	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}return false;
		}return false;
	}


}

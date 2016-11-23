package blackjack;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends MouseAdapter{

	private ArrayList<Card> hand = new ArrayList<Card>();

	private Scanner keyboard = new Scanner(System.in);

	private int sum = 0;

	private Game game;

	private Deck d;

	private AI ai;

	private boolean win = false;
	private boolean lose = false;

	private boolean stay = false;
	
	public Player(Game game, Deck d, AI ai){
		hand = new ArrayList<Card>();
		this.game = game;
		this.d = d;
		this.ai = ai;

	}

	public void addCard(Card c){
		hand.add(c);
	}

	public void removeCard(Card c){
		hand.remove(c);
	}

	public void removeCard(int cardNum){
		hand.remove(cardNum);
	}

	public ArrayList<Card> getHand(){
		return hand;
	}

	public int getSum(){
		int sum = 0;

		for(int i = 0; i<hand.size(); i++){
			sum+=hand.get(i).getValue();
		}

		return sum;
	}

	//love this method
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}return false;
		}return false;
	}

	public void tick(){
		if(hand.size() != 0){
			hand.get(0).setX(200);
			hand.get(0).setY(30);
			hand.get(1).setX(280);
			hand.get(1).setY(30);
		}

		if(getSum() > 21){
			setLose(true);
		}

	}

	public void render(Graphics g){

		g.setColor(Color.RED);
		g.drawString("Player Cards:",50, 80);

		for(int i = 0; i<hand.size(); i++){
			if(!(hand.size() > 2)){
				g.drawImage(hand.get(i).getImage(), hand.get(i).getX(), hand.get(i).getY(), null);
			}else{
				hand.get(hand.size()-1).setX(hand.get(hand.size()-2).getX()+80);
				hand.get(hand.size()-1).setY(hand.get(hand.size()-2).getY());
				g.drawImage(hand.get(i).getImage(), hand.get(i).getX(), hand.get(i).getY(), null);
			}
		}


		g.drawString("Player Card Sum is: "+getSum(), 50, 100);

		if((ai.isStay() == true && stay) || lose || win){
			if(lose){
				ai.setStay(false);
				setStay(false);
				g.drawString("You lose", 678, 340);

			}
			else if(win){
				ai.setStay(false);
				setStay(false);
				g.drawString("You win",678,340);
			}
		}
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Deck getD() {
		return d;
	}

	public void setD(Deck d) {
		this.d = d;
	}

	public AI getAi() {
		return ai;
	}

	public void setAi(AI ai) {
		this.ai = ai;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}
	
	public boolean isStay() {
		return stay;
	}

	public void setStay(boolean stay) {
		this.stay = stay;
	}

	public String toString(){
		return "Player";
	}
}

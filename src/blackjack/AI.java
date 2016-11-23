package blackjack;


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class AI {

	private ArrayList<Card> hand = new ArrayList<Card>();

	private int sum = 0;

	private Game game;

	private Deck d;

	private Player p;

	//conditions for the ai itself
	private boolean lose = false;
	private boolean win = false;
	
	//conditional for the ai decision
	private boolean stay = false;
	
	//for the user interface to continue after a card
	private boolean isActing = false;

	public AI(Game game, Deck d, Player p){

		hand = new ArrayList<Card>();
		this.game = game;
		this.d = d;
		this.p = p;
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

	public int getSum(){
		int sum = 0;

		for(int i = 0; i<hand.size(); i++){
			sum+=hand.get(i).getValue();
		}

		return sum;
	}

	public void tick(){
		if(hand.size() != 0){
			hand.get(0).setX(200);
			hand.get(0).setY(400);
			hand.get(1).setX(280);
			hand.get(1).setY(400);}


	}

	public void act(){
		isActing = true;
		while(getSum() <= 21 && stay == false){
			if(getSum() > 21){
				lose = true;
				stay = true;
				game.show = true;
				break;
			}
			if(getSum() <= 16){
				hand.add(d.deal());
			}
			else{
				Random randomInt = new Random();
				int chooser = 1+randomInt.nextInt(7);
				int secondChooser = 1+randomInt.nextInt(7);

				if(chooser == secondChooser){
					hand.add(d.deal());
				}
				else
					stay = true;
					game.show = true;
			}
		}
		
	}

	public void render(Graphics g){

		g.setColor(Color.RED);
		g.drawString("AI Cards:",50, 480);

		if(!game.show){
			for(int i = 0; i<hand.size(); i++){
				if(!(hand.size() > 2)){
					g.drawImage(Deck.blankCard.getImage(), hand.get(i).getX(), hand.get(i).getY(), null);
				}else{
					hand.get(hand.size()-1).setX(hand.get(hand.size()-2).getX()+80);
					hand.get(hand.size()-1).setY(hand.get(hand.size()-2).getY());
					g.drawImage(Deck.blankCard.getImage(), hand.get(i).getX(), hand.get(i).getY(), null);
				}
			}
		}
		else{
			for(int i = 0; i<hand.size(); i++){
				if(!(hand.size() > 2)){
					g.drawImage(hand.get(i).getImage(), hand.get(i).getX(), hand.get(i).getY(), null);
				}else{
					hand.get(hand.size()-1).setX(hand.get(hand.size()-2).getX()+80);
					hand.get(hand.size()-1).setY(hand.get(hand.size()-2).getY());
					g.drawImage(hand.get(i).getImage(), hand.get(i).getX(), hand.get(i).getY(), null);
				}
			}
			g.drawString("AI Card Sum is: "+getSum(), 50, 500);
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

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
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

	public boolean isActing() {
		return isActing;
	}

	public void setActing(boolean isActing) {
		this.isActing = isActing;
	}

	public ArrayList<Card> getHand() {
		// TODO Auto-generated method stub
		return hand;
	}
}

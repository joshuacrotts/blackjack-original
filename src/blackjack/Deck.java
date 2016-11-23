package blackjack;

import java.awt.Graphics;
import java.util.ArrayList;

public class Deck {

	private ArrayList<Card> cards = new ArrayList<Card>();
	private ArrayList<Card> selectedCards = new ArrayList<Card>();
	
	private String[] suits = {"clubs","spades","hearts","diamonds"};
	private String[] specials = {"jack","queen","king"};

	public static Card blankCard;
	
	private int size = 0;
	private int selectedSize = 0;
	
	public Deck(){
		cards = new ArrayList<Card>();
		
		for(int i = 0; i<4; i++){
			int x = 0; 
			for(int j = 1; j<=13; j++){
				if(j > 10){
					cards.add(new Card(10, suits[i], specials[x], "img/cards/"+j+suits[i]+".GIF"));
					x++;
				}
				else{
					cards.add(new Card(j, suits[i], ""+j, "img/cards/"+j+suits[i]+".GIF"));
				} 
			}
		}
		size = cards.size();
		blankCard = new Card(0, null, "Back Card", "img/cards/back1.GIF");
		
		//BLANK CARDS
		selectedCards = new ArrayList<Card>();
		
		for(int i = 0; i<4; i++){
			int x = 0; 
			for(int j = 1; j<=13; j++){
				if(j > 10){
					selectedCards.add(new Card(10, suits[i], specials[x], "img/selectedcards/"+j+suits[i]+"S.GIF"));
					x++;
				}
				else{
					selectedCards.add(new Card(j, suits[i], ""+j, "img/selectedcards/"+j+suits[i]+"S.GIF"));
				}
			}
		}
		setSelectedSize(selectedCards.size());
	}
	
	public void repopulate(){
		for(int i = 0; i<4; i++){
			int x = 0; 
			for(int j = 1; j<=13; j++){
				if(j > 10){
					cards.add(new Card(10, suits[i], specials[x], "img/cards/"+j+suits[i]+".GIF"));
					x++;
				}
				else{
					cards.add(new Card(j, suits[i], ""+j, "img/cards/"+j+suits[i]+".GIF"));
				} 
			}
		}
		size = cards.size();
		blankCard = new Card(0, null, "Back Card", "img/cards/back1.GIF");
		
		//BLANK CARDS
		selectedCards = new ArrayList<Card>();
		
		for(int i = 0; i<4; i++){
			int x = 0; 
			for(int j = 1; j<=13; j++){
				if(j > 10){
					selectedCards.add(new Card(10, suits[i], specials[x], "img/selectedcards/"+j+suits[i]+"S.GIF"));
					x++;
				}
				else{
					selectedCards.add(new Card(j, suits[i], ""+j, "img/selectedcards/"+j+suits[i]+"S.GIF"));
				}
			}
		}
		setSelectedSize(selectedCards.size());
		shuffle();
	}
	
	public void shuffle() {
		for (int k = cards.size() - 1; k > 0; k--) {
			int howMany = k + 1;
			int start = 0;
			int randPos = (int) (Math.random() * howMany) + start;
			Card temp = cards.get(k);
			cards.set(k, cards.get(randPos));
			cards.set(randPos, temp);
		}
		size = cards.size();
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public Card deal() {
		if (isEmpty()) {
			return null;
		}
		size--;
		Card c = cards.get(size);
		return c;
	}
	
	public void printCards(){
		for(int i = 0; i<cards.size(); i++){
			System.out.println("Card "+(i+1)+": "+cards.get(i));
		}
	}

	public int getSize(){
		return size;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public ArrayList<Card> getSelectedCards() {
		return selectedCards;
	}



	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public void tick(){
		
	}

	public int getSelectedSize() {
		return selectedSize;
	}

	public void setSelectedSize(int selectedSize) {
		this.selectedSize = selectedSize;
	}
	
	public void render(Graphics g){
		
		g.drawString("Deck",200, 270);
		

		
		for(int i = 0; i<size; i++){
			g.drawImage(this.getCards().get(i).getImage(), 240, 220, null);
		}
		
		g.drawImage(Deck.blankCard.getImage(), 240, 220, null);
	}


}

package blackjack;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Card extends MouseAdapter{

	private BufferedImage image;
	private String suit;
	private String cardName;
	private int value;
	
	private int x;
	private int y;
	private int width = 73;
	private int height = 97;

	private boolean selected = false;
	
	public Card(int x, int y,int value, String suit, String cardName, String fileName){
		this.x = x;
		this.y = y;
		this.value = value;
		this.suit = suit;
		this.cardName = cardName;
		
		try{
			this.image = ImageIO.read(new File("img/cards/"+fileName+".GIF"));
		}catch(IOException e){
			System.err.println("Card Not Found");
		}
		
		this.width = image.getWidth();
		this.height = image.getHeight();
	}
	
	public Card(int value, String suit, String cardName, String fileName){
		this.x = 0;
		this.y = 0;
		this.value = value;
		this.suit = suit;
		this.cardName = cardName;
		
		try{
			this.setImage(ImageIO.read(new File(fileName)));
		}catch(IOException e){
			System.err.println("Card Not Found");
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y, image.getWidth(), image.getHeight());
	}
	


	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public boolean equals(Card card){
		
		if(this.getValue() == card.getValue() &&
		   this.getSuit().equals(card.getSuit()) &&
		   this.getCardName().equals(card.getCardName()))
			return true;
		return false;
	}
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void render(Graphics g){
		g.drawImage(this.getImage(),0,0,null);
	}
	
	public String toString(){
		return "Card Value: "+getValue()+"\tCard Suit: "+getSuit()+"\tCard Name: "+getCardName();
	}
	
	
	
	
}

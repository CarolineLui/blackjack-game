//main method here

import java.util.ArrayList;
import java.util.List;

public class BlackJack
{
  private int counter;
  private Counter system;
  private DeckOfCards deck;
  private ArrayList<Card> dealer;
  private ArrayList<Card> cheater;
  private ArrayList<Card> player3;
  private ArrayList<Card> player4;
  private int numDecks;
  private int cheaterMoney;
  private int bet;
  private boolean won;
  private boolean lost;
  private boolean tied;
  
  public BlackJack(int n, Counter s, int m)
  {
    won = false;
    lost = false;
    tied = false;
    cheaterMoney = m;
    bet = 0;
    numDecks = n;
    counter = 0;
    system = s;
    deck = new DeckOfCards(n);
    
    dealer = new ArrayList<Card>();
    cheater = new ArrayList<Card>();
    player3 = new ArrayList<Card>();
    player4 = new ArrayList<Card>();
  }
  
  //accesor methods
  public int getNumDecks() {return numDecks;}
  public int getCount() {return counter;}
  public ArrayList<Card> getDealer() {return dealer;}
  public ArrayList<Card> getCheater() {return cheater;}
  public ArrayList<Card> getPlayer3() {return player3;}
  public ArrayList<Card> getPlayer4() {return player4;}
  public int getCheaterMoney() {return cheaterMoney;}
  public int getBet() {return bet;}
  
  
  private boolean hasAce(ArrayList<Card> player)
  {
   for( int c = 0; c < player.size(); c++)
    {
      if(player.get(c).getRank().equals("Ace"))
      return true;
    }
   return false;
  }
  
  public int getScoreAce11(ArrayList<Card> player)
  {
   int score = 0;
   for(int c = 0; c < player.size(); c++)
   {
     score += player.get(c).getPoints();
   }
   return score;
  }
  
  public int getScore(ArrayList<Card> player)
  {
   int score = 0;
   for(int c = 0; c < player.size(); c++)
   {
     score += player.get(c).getPoints();
   }
   
   if (score > 21 && hasAce(player))
   {
     score -= 10;
   }
   return score;
  }
  
  public int bet()
  {
    double trueCount = (double)counter/numDecks;
    bet = (int) Math.max(5, (trueCount-1)*10);
    return bet;
  }
  
  ////will they hit?
  
  private boolean playerHit(ArrayList<Card> player)
  {
    if (getScoreAce11(player) < 17 || (getScoreAce11(player) == 17 && hasAce(player)))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  //keeps track of count
  
  private void count(Card c)
  {
    counter += system.getCount(c.getRank());
  }
  
  
  private void outcome()
  {
    if(getScore(cheater) > 21)
      lost = true;
    else if(getScore(dealer) > 21)
      won = true;
    else if(getScore(cheater) == getScore(dealer))
      tied = true;
    else if(getScore(cheater) > getScore(dealer))
      won = true;
    else //cheater score < dealer score 
      lost = true;
  }
  
  
  //deals a card to any hand, updates count
  private Card addCard(ArrayList<Card> player)
  {
    Card c = deck.deal();
    count(c);
    player.add(c);
    return c;
  }
  
  //reshuffles deck 
  private void reshuffle()
  {
    deck.shuffle();
    counter = 0;
  }
  
  
  //plays blackjack
  public boolean game()
  {
    bet();
    addCard(dealer);
    addCard(dealer);
    
    addCard(player3);
    addCard(player3);
    
    addCard(player4);
    addCard(player4);
    
    addCard(cheater);
    addCard(cheater);
    
    while(playerHit(cheater))
      addCard(cheater);
    while(playerHit(player3))
      addCard(player3);
    while(playerHit(player4))
      addCard(player4);
    while(playerHit(dealer))
      addCard(dealer);
    
    if(deck.getSize() < 50)
      reshuffle();
    
    outcome();
    
    dealer = new ArrayList<Card>();
    player3 = new ArrayList<Card>();
    player4 = new ArrayList<Card>();
    cheater = new ArrayList<Card>();
    
    if(lost)
    {
      cheaterMoney -= bet;
      bet = 0;
      lost = false;
      return false;
    }
    else if (won)
    {
      cheaterMoney += (int)bet*(1.5);
      bet = 0;
      won = false;
      return true;
    }
    else if (tied)
    {
      bet = 0;
      tied = false;
      return false;
    }
    else
      return false;
    
  }
  
  public static void main(String[] args)
  {
    System.out.print("Control final money: ");
    BlackJack game1 = new BlackJack(4, new Control(), 10000);
    for (int c = 0; c < 1000; c++)
    {
      game1.game();
    }
    System.out.println(game1.getCheaterMoney());

    System.out.print("Zen Count final money: ");
    BlackJack game2 = new BlackJack(4, new ZenCount(), 10000);
    for (int c = 0; c < 1000; c++)
    {
      game2.game();
    }
    System.out.println(game2.getCheaterMoney());
                     
    System.out.print("Hi-Low Count final money: ");
    BlackJack game3 = new BlackJack(4, new HiLowCount(), 10000);
    for (int c = 0; c < 1000; c++)
    {
      game3.game();
    }
    System.out.println(game3.getCheaterMoney());
                     
    System.out.print("High-Opt-II Count final money: ");
    BlackJack game4 = new BlackJack(4, new HighOptIICount(), 10000);
    for (int c = 0; c < 1000; c++)
    {
      game4.game();
    }
    System.out.println(game4.getCheaterMoney());
  }
}
package assig1;
import java.util.*;

public class Blackjack{
    
    public static void main(String [] args){
        
        Scanner inScan = new Scanner(System.in);

        Card c;
        RandIndexQueue rd;
        Card currentCard;
        Card deleteCard;
        Card addCard;
        boolean dealerWon = false;
        boolean playerWon = false;
        boolean roundGreater = false;

        int roundCounter=1, rounds =0, shoeNum =0 , currRound =0;
        int playerValue=0, dealerValue = 0, aceCheck =0;
        int playerWins = 0, dealerWins=0, pushes = 0;
        RandIndexQueue<Card> dealer = new RandIndexQueue<Card>(10);
        RandIndexQueue<Card> player =  new RandIndexQueue<Card>(10);
        RandIndexQueue<Card> discardPile = new RandIndexQueue<Card>(10);
        
        
        if (args.length > 0)
        {
           rounds = Integer.parseInt(args[0]);
           shoeNum = Integer.parseInt(args[1]);
        }
       
        if (rounds > 10)
        {
            roundGreater = true;
        }
        
        RandIndexQueue<Card> deck = new RandIndexQueue<Card>(52*shoeNum);
        
        for (int i = 0; i < shoeNum; i++)
        {
            for (Card.Suits s: Card.Suits.values())
            {
                for (Card.Ranks r: Card.Ranks.values())
                {
                    c = new Card(s,r);
                    deck.offer(c);
                    deck.shuffle();
                }
            }
        }
        
        //adds two cards into both the dealer and players hands
        while (roundCounter < rounds+1)
        {         
            System.out.println("\n");
            if (roundGreater == false)
            {
            System.out.println("Starting round " + roundCounter);
            }
            
            currentCard = deck.poll();
            dealer.offer(currentCard);
            currentCard= deck.poll();
            player.offer(currentCard);
            currentCard = deck.poll();
            dealer.offer(currentCard);
            currentCard = deck.poll();
            player.offer(currentCard);
            
            //prints the players hand and the value
            if (roundGreater==false) 
            {
            System.out.print("Player: " + player.toString());
            }
            
            for (int i = 0; i < player.size(); i++)
            {             
                playerValue+= player.get(i).value();        

            }
            if (roundGreater == false)
            {
            System.out.println(":" + playerValue);
            
            //prints the dealers hand and the value
            System.out.print("Dealer: " + dealer.toString());
            }
            
            for (int i = 0; i< dealer.size(); i++)
            {   
                 dealerValue += dealer.get(i).value();
                
            }
            if (roundGreater == false)
            {
            System.out.println(":" + dealerValue);
            }
            
            //checks if players hand is = 21 otherwise hits
            if (playerValue == 21 || dealerValue > 21)
            {
                if (roundGreater == false)
                {
                System.out.println("Result: Player Wins!");
                }
                playerWon = true;
                playerWins++;          
                //break;
            }
            
            //hits while hand is less than 17
            while (playerValue < 17 && playerWon == false)
            {
                if (roundGreater == false)
                {
                System.out.print("Player hits- ");
                }
                
                currentCard = deck.poll();
                player.offer(currentCard);
                
                if (roundGreater == false)
                {
                System.out.print(currentCard);
                }
                
                playerValue = 0;
                for (int i =0; i < player.size(); i++)
                {                   
                    playerValue += player.get(i).value();
                }
                checkValue(player, playerValue);
                
                if (roundGreater == false)
                {
                System.out.println(": " + playerValue);
                }
            }
            
            //stands while hand is between 17 and 21
            if (playerValue > 17 && playerValue < 21)
            {
                if (roundGreater == false)
                {
                System.out.println("Player STANDS- " + player.toString() + ": "+ playerValue);
                }
            }
            
            if (playerValue > 21)
            {
                if (roundGreater == false)
                {
                System.out.print("Player Busts- ");               
                System.out.println(player.toString() + ": " + playerValue);
                }
                dealerWon = true;
            }
            
            //if dealers hand is equal to 21 dealer wins
            if (dealerValue == 21 || playerValue > 21)
            {   
                if (roundGreater == false)
                {
                System.out.println("Result: Dealer wins!");
                }
                dealerWon = true;
                dealerWins++;
                //break;
            }

            //hits while the value is less than 19
            while (dealerValue< 17 && playerWon == false && dealerWon == false)
            {
                if (roundGreater == false)
                {
                System.out.print("Dealer hits- ");
                }
                currentCard = deck.poll();
                dealer.offer(currentCard);
                
                if (roundGreater == false)
                {
                System.out.print(currentCard);
                }
                dealerValue = 0;
                                
                for (int i=0; i<dealer.size(); i++)
                {                 
                    dealerValue += dealer.get(i).value();       
                }
                
                //check aces
                checkValue(dealer, dealerValue);
                
                if (roundGreater == false)
                {
                System.out.println(": " + dealerValue);
                }
            }
            
            //stands while it is in between 17-21
            if (dealerValue > 17 && dealerValue < 21 && playerWon)
            {
                if (roundGreater == false)
                {
                System.out.println("Dealer STANDS- " + dealer.toString() + ": "+ dealerValue);
                }
            }   
            
            if (dealerValue > 21)
            {
                if (roundGreater == false)
                {
                System.out.print("Dealer Busts- ");
                System.out.println(dealer.toString() + ": " + dealerValue);
                System.out.println("Result: Player wins!");
                }
                playerWon= true;
                playerWins++;
            }
            
            //if player hand and dealer hand are equal its a tie
            if (playerValue == dealerValue)
            {
                if (roundGreater == false)
                {
                System.out.println("Result: Push!");
                }
                pushes++;
            }
            
            //if playerValue is greater than dealerValue -- player wins
            if (playerValue > dealerValue && playerValue <= 21 && dealerValue <= 21)
            {
                if (roundGreater == false)
                {
                System.out.print("Result: Player Wins!");  
                }
                playerWon = true;
                playerWins++;
            }
            
            //if dealerValue is greater than playerValue -- dealer wins
            //changed here to not equal -- check that 
            if (dealerValue > playerValue && playerValue < 21 && dealerValue < 21)
            {
                if (roundGreater == false)
                {
                System.out.println("Result: Dealer Wins!");
                }
                dealerWon = true;
                dealerWins++;
            }
                  
            //adds the cards from player and dealer hands to discard pile
            for (int i = 0; i < player.size(); i++)
            {               
                deleteCard = player.get(i);
                discardPile.offer(deleteCard);
            }
            
            for (int i=0; i < dealer.size(); i++)
            {
                deleteCard = dealer.get(i);
                discardPile.offer(deleteCard);
            }
            //resets everything for the next round
            roundCounter++;    
            player.clear();
            dealer.clear();
            playerValue = 0;
            dealerValue = 0;
            playerWon=false;
            dealerWon = false;
            
            //checks size of the discard pile

            if (discardPile.size() >= ((3/4)*deck.size()))
            {
                for (int i = 0; i < discardPile.size(); i++)
                {
                    addCard = discardPile.get(i);
                    deck.offer(addCard);
                }
                deck.shuffle();
                
                if (rounds>10)
                {
                System.out.println("Reshuffling the shoe in round " + roundCounter);
                }
  
            }
        }
   
            System.out.println("\n");
            System.out.println("Results after " + rounds + " rounds");
            System.out.println("Dealer Wins: " + dealerWins);
            System.out.println("Player Wins: " + playerWins);
            System.out.println("Pushes: " + pushes);
        
    }
    
    public static int checkValue(RandIndexQueue<Card> user, int value)
    {
       int numAces=0;
       
       for (int i = 0; i< user.size(); i++)
       {
           if (user.get(i).value()==11)
           {
               numAces++;
           }
       }
       while (numAces > 0 && value > 21)
       {
           value = value-10;
           numAces--;
       }
       return value;
    }
    

    
}



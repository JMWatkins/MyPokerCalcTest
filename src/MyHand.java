
public class MyHand{

//------------------------- CONSTRUCTORS ---------------------------

    Card[] hand;

    public MyHand(String[] in){
        hand = new Card[5];
        if (hand.length!=5){
            System.out.println("Wrong size hand!");
            System.err.println();
        }
        for (int i =0; i < 5;i++)
            hand[i] = (new Card(in[i]));
    }

//--------------------------- TOOLS ---------------------------------

    // searches for the value of the nth highest card
    public int NthHighestCard(int n){
        int[] highestIndex = {-1,-1,-1,-1,-1};
        int highest = -1;
        int highestVal = 0;
        for(int i = 0; i<n; i++){
            highest = -1;
            highestVal = 0;
            for(int j = 0; j<hand.length; j++){
                if (hand[j].number > highestVal && !arContains(highestIndex, j)){
                	highest = j;
                	//System.out.println(">>"+hand[j].number + " at index: " +j);
                    highestVal = hand[j].number;
                }
            }
            highestIndex = arAdd(highestIndex, highest);
        }
        return highestVal;   
    }

    // checks if an  int list contains an int
    private static boolean arContains(int[] ar, int num){
        for (int integer: ar){
            if (integer == num)
                return true;
        }
        return false;
    }

    private static int[] arAdd(int[] ar, int num){
        for (int i = 0; i < ar.length;i++){
            if (ar[i] == -1){
                ar[i] = num;
                //System.out.println(ar[i]);
            	return ar;
            } 
        }
        System.out.println("no space to add to ar");
        return null;
    }

    public int highestCard(){
        return NthHighestCard(1);
    }


 /*       private boolean containsSuit(char c){
        for(int i = 0; i < hand.length; i++){
            if (hand[i].suit == c)
                return true;
        }
        return false;
    }*/

    private boolean containsNumber(int in){
        for(int i = 0; i < hand.length; i++){
            if (hand[i].number == in)
                return true;
        }
        return false;
    }
    
    private int matchingVals(int val){
    	int ret = 0;
    	for (Card c: hand){
    		if (c.number == val)
    			ret ++;
    	}
    	return ret;
    }

// ----------------------- USEFULL STUFF ------------------------------


    
    /*  returns total value of a hand (rank of hand * 20)
        plus the value of the constituants of the hand
    */
    public float getHandVal(){
        //check for straight
    	float ret = 100+(float)highestCard();
    	//System.out.println("Highest Card: " + ret);
    	
        float duplicates = checkDuplicates();
        float straight = checkStraight();
        float flush = checkFlush();
        
        float max1 = Math.max(duplicates, straight);
        ret = Math.max(flush, ret );
        ret = Math.max(ret, max1);
        
        return ret;
    }

    private float checkDuplicates(){
    	int fours =  0;
    	int threes = 0;
    	int [] twos = {-1,-1};
    	int [] visited = {-1,-1,-1,-1,-1};
    	
    	for (Card c: hand){
    		int duplicateVals = matchingVals(c.number);
    		if (duplicateVals>1 && !arContains(visited,c.number) ){
    			arAdd(visited, c.number);
    			if(duplicateVals == 4)
    				fours = c.number;
    			else if(duplicateVals == 3){
    				threes = c.number;
    			}
    			else{
    				if(twos[0] == -1){
    					twos[0] = c.number;
    				}
    				else if(twos[1] == -1){
    					twos[1] = c.number;
    				}
    			}
    		}
    		
    	}
    	// Decide scoring
    	if (fours > 0){
    		return 800 + ((float)fours);
    	}
    	else if (threes > 0){
    		if(twos[0] > 0){
    			return 700 + ((float)threes ) + ((float)twos[0]/10f);
    		}
    		else{
    			return 400 + ((float)threes );
    		}
    		
    	}
    	else if (twos[0] > 0 && twos[1] >0){
    		return 300 + ((float)twos[0] )+((float)twos[1] ); 
    	}
    	else if (twos[0] > 0){
    		return 200+((float)twos[0] );
    	}
    	
        return 0;
    }

    private float checkStraight(){
    	int hi = highestCard();
    	if (containsNumber(hi-1) && containsNumber(hi-2) && containsNumber(hi-3) && containsNumber(hi-4)){
    		boolean isFlush = true;
    		char s = hand[0].suit;
    		for(Card c: hand){
    			if(c.suit != s)
    				isFlush = false;
    		}
    		if (isFlush){
    			if(hi == 14){
    				return 1000;
    			}
    			else 
    				return 900 + (float)hi ;
    		}
    		else
    			return 500 +(float)hi ;
    	}
        return 0;
    }

    private float checkFlush(){
    	boolean isFlush = true;
		char s = hand[0].suit;
		for(Card c: hand){
			if(c.suit != s)
				isFlush = false;
		}
		if (isFlush)
			return 600 + (float)highestCard() ;
		return 0;
    }






// ----------------------- CARD CLASS ---------------------------------

    private class Card{
        public int number;
        public char suit;

        /*public Card(char char1, char char2){
            number=getVal(char1);
            number=char2;
        }*/

        public Card(String card){
            card = card.trim();
            if(card.length()<2){
                System.out.println("Card too complicated");
                System.err.println();
            }else{
                number=getVal(card.charAt(0));
                suit=card.charAt(1);
            }
        }
        private int getVal(char number){
            if((int)number > 48 && (int)number < 58){
            	//System.out.println(Character.getNumericValue(number));
                return Character.getNumericValue(number);
            }
            else if (number == 'T')
                return 10;
            else if (number == 'J')
                return 11;
            else if (number == 'Q')
                return 12;
            else if (number == 'K')
                return 13;
            else if (number == 'A')
                return 14;
            else
                System.out.println("Bad Card Val");
            return 0;
        }
    }
}
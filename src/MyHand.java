
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
                    highestVal = hand[j].number;
                    arAdd(highestIndex, j);
                }
            }
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

    private static boolean arAdd(int[] ar, int num){
        for (int integer: ar){
            if (integer == -1)
                integer = num;
        }
        return false;
    }

    public int highestCard(){
        return NthHighestCard(1);
    }


        private boolean containsSuit(char c){
        for(int i = 0; i < hand.length; i++){
            if (hand[i].suit == c)
                return true;
        }
        return false;
    }

    private boolean containsNumber(int in){
        for(int i = 0; i < hand.length; i++){
            if (hand[i].number == in)
                return true;
        }
        return false;
    }

// ----------------------- USEFULL STUFF ------------------------------


    
    /*  returns total value of a hand (rank of hand * 20)
        plus the value of the constituants of the hand
    */
    public int getHandVal(){
        //check for straight
        int ret = 0;

        int duplicates = checkDuplicates();
        if (duplicates > 0)
            ret = duplicates;

        int straight = checkStraight();
        if (straight > 0 && straight > ret)
            ret = straight;

        int flush = checkFlush();
        if (flush > 0 && flush > ret)
            ret = flush;

        return ret;
    }

    private int checkDuplicates(){
        return 0;
    }

    private int checkStraight(){
        return 0;
    }

    private int checkFlush(){
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
            if((int) number > 48 || (int) number < 58)
                return number;
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
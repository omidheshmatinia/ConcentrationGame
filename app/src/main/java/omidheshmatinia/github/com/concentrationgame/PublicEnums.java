package omidheshmatinia.github.com.concentrationgame;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

public class PublicEnums {

    public enum Difficulty {
        Easy(8,4),
        Normal(18,6),
        Hard(32,8);

        private int cardNumber ;
        private int columnSize ;

        Difficulty(int cardNumber,int columnSize) {
            this.cardNumber = cardNumber;
            this.columnSize = columnSize;
        }

        public int getCardNumber(){
            return cardNumber;
        }

        public int getColumnSize() {
            return columnSize;
        }
    }
}

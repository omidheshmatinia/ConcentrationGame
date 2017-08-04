package omidheshmatinia.github.com.concentrationgame;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

public class PublicEnums {

    public enum Difficulty {
        Easy(8,4,1),
        Normal(18,6,2),
        Hard(32,8,3);

        private int cardNumber ;
        private int columnSize ;
        private int type ;

        Difficulty(int cardNumber,int columnSize,int type) {
            this.cardNumber = cardNumber;
            this.columnSize = columnSize;
            this.type= type;
        }

        public int getCardNumber(){
            return cardNumber;
        }

        public int getColumnSize() {
            return columnSize;
        }
        public int getType() {
            return type;
        }
    }
}

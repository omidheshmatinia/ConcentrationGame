package omidheshmatinia.github.com.concentrationgame;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

public class PublicEnums {

    public enum Difficulty {
        Easy(4,2,1,"Easy"),
        Normal(9,4,2,"Normall"),
        Hard(16,6,3,"Hard");

        private int cardNumber ;
        private int columnSize ;
        private int type ;
        private String name;

        Difficulty(int cardNumber,int columnSize,int type,String name) {
            this.cardNumber = cardNumber;
            this.columnSize = columnSize;
            this.type= type;
            this.name = name;
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

        public String getName() {
            return name;
        }

        public static Difficulty getDifficultyByType(int type){
            switch (type){
                default:
                case 1:
                    return Easy;
                case 2:
                    return Normal;
                case 3:
                    return Hard;
            }
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

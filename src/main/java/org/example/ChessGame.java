package org.example;

import java.util.ArrayList;
import java.util.Collections;

abstract class AbstractGame {
    abstract void setPlayer(Player p1, Player p2);
    abstract boolean gameOver();
    abstract boolean move(int location);
}

public class ChessGame extends AbstractGame {

    int tmp=0, first=0;
    Chess currentChess;
    String playerSide="";
//    String[] loc=new String[32];
    private ArrayList<Integer> loc=new ArrayList<>();
    public Chess[][] chessBoard=new Chess[4][8];
//    ArrayList<ArrayList<Chess>> chessBoard=new ArrayList<>();

    void setPlayer(Player p1, Player p2){
        p1.setSide(currentChess.getSide());
        playerSide=p1.getName()+": "+currentChess.getSide()+", "+p2.getName()+": ";
        if(currentChess.getSide().equals("Red")){
            p2.setSide("Black");
            playerSide+="Black";
        }
        else{
            p2.setSide("Red");
            playerSide+="Red";
        }

    }

    boolean gameOver(){

        return true;
    }
    boolean move(int location){

        return true;
    }
    void showAllChess(){
        System.out.print("   ");
        String s="１２３４５６７８";
        for(int i=0;i<8;i++)
            System.out.printf("  %c  ", s.charAt(i));
        System.out.println();
        for(int i=0;i<4;i++) {
            System.out.printf("%c  ", 'A'+i);
            for(int j=0;j<8;j++){
                System.out.printf(" %s ", chessBoard[i][j].toString());
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------");
        if(!playerSide.isEmpty())System.out.println(playerSide);
    }
    void generateChess(){
        for(int i=0;i<4;i++){
            for(int j=0;j<8;j++) {
                loc.add(i*10+j);
            }
        }
        Collections.shuffle(loc); //隨機位置
        chessBoard[loc.get(tmp)/10][loc.get(tmp)%10]=new Chess("將", 7, "Black", loc.get(tmp++));
        chessBoard[loc.get(tmp)/10][loc.get(tmp)%10]=new Chess("帥", 7, "Red", loc.get(tmp++));
        for(int i=0;i<2;i++){
            chessBoard[loc.get(tmp)/10][loc.get(tmp)%10]=new Chess("士", 6, "Black", loc.get(tmp++));
            chessBoard[loc.get(tmp)/10][loc.get(tmp)%10]=new Chess("仕", 6, "Red", loc.get(tmp++));
        }
        for(int i=0;i<2;i++){
            chessBoard[loc.get(tmp)/10][loc.get(tmp)%10] = new Chess("象", 5, "Black", loc.get(tmp++));
            chessBoard[loc.get(tmp)/10][loc.get(tmp)%10] = new Chess("相", 5, "Red", loc.get(tmp++));
        }
        for(int i=0;i<2;i++){
            chessBoard[loc.get(tmp)/10][loc.get(tmp)%10] = new Chess("車", 4, "Black", loc.get(tmp++));
            chessBoard[loc.get(tmp)/10][loc.get(tmp)%10] = new Chess("俥", 4, "Red", loc.get(tmp++));
        }
        for(int i=0;i<2;i++){
            chessBoard[loc.get(tmp)/10][loc.get(tmp)%10]=new Chess("馬", 3, "Black", loc.get(tmp++));
            chessBoard[loc.get(tmp)/10][loc.get(tmp)%10]=new Chess("傌", 3, "Red", loc.get(tmp++));
        }
        for(int i=0;i<2;i++){
            chessBoard[loc.get(tmp)/10][loc.get(tmp)%10]=new Chess("砲", 2, "Black", loc.get(tmp++));
            chessBoard[loc.get(tmp)/10][loc.get(tmp)%10]=new Chess("炮", 2, "Red", loc.get(tmp++));
        }
        for(int i=0;i<5;i++){
            chessBoard[loc.get(tmp)/10][loc.get(tmp)%10]=new Chess("卒", 1, "Black", loc.get(tmp++));
            chessBoard[loc.get(tmp)/10][loc.get(tmp)%10]=new Chess("兵", 1, "Red", loc.get(tmp++));
        }
//        System.out.println(tmp);
    }

    boolean chessShow(int location){
        currentChess=chessBoard[location/10][location%10];

        if(chessBoard[location/10][location%10].show){
            return true;
        }
        else{
            chessBoard[location/10][location%10].show=true;
            return false;
        }
    }
}

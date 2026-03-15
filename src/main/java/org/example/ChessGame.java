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
    Chess currentChess, target;
    String playerSide="";
    Player[] p=new Player[2];
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
        p[0]=p1;
        p[1]=p2;
    }

    boolean gameOver(){
        int redChess=0, blackChess=0, moveSpace, redCannotMove=0, blackCannotMove=0;
        int[] num={-1, 1, -10, 10};
        ArrayList<Chess> redLast=new ArrayList<>(); // 剩下的紅棋
        ArrayList<Chess> blackLast=new ArrayList<>(); // 剩下的黑棋

        for(int i=0;i<4;i++){
            for(int j=0;j<8;j++){
                if(chessBoard[i][j].live){
                    if(chessBoard[i][j].getSide().equals("Red")){
                        redChess++;
                        redLast.add(chessBoard[i][j]);
                    }
                    else {
                        blackChess++;
                        blackLast.add(chessBoard[i][j]);
                    }
                }
            }
        }
        if(redChess==0||blackChess==0){
            System.out.println("Game Over!");
            if(redChess==0){
                if(p[0].getSide().equals("Black"))System.out.println(p[0].getName()+" 勝利!");
                else System.out.println(p[1].getName()+" 勝利!");
            }
            else{
                if(p[0].getSide().equals("Red"))System.out.println(p[0].getName()+" 勝利!");
                else System.out.println(p[1].getName()+" 勝利!");
            }
            return true;
        }
        else{
            for(Chess r:redLast){
                moveSpace=0;
                for(int n:num){
                    if(move(r.getLoc()+n))moveSpace++;
                }
                if(moveSpace==0)redCannotMove++;
            }
            if(redCannotMove==redLast.size()){
                System.out.println("Game Over!");
                if(p[0].getSide().equals("Black"))System.out.println(p[0].getName()+" 勝利!");
                else System.out.println(p[1].getName()+" 勝利!");
                return true;
            }

            for(Chess b:blackLast){
                moveSpace=0;
                for(int n:num){
                    if(move(b.getLoc()+n))moveSpace++;
                }
                if(moveSpace==0)blackCannotMove++;
            }
            if(blackCannotMove==blackLast.size()){
                System.out.println("Game Over!");
                if(p[0].getSide().equals("Red"))System.out.println(p[0].getName()+" 勝利!");
                else System.out.println(p[1].getName()+" 勝利!");
                return true;
            }
        }
        return false;
    }

    void swap(int iBefore, int jBefore, int iAfter, int jAfter){
        int tmp=currentChess.getLoc();
        currentChess.loc=target.loc;
        target.loc=tmp;
        chessBoard[iBefore][jBefore]=target;
        chessBoard[iAfter][jAfter]=currentChess;
    }
    boolean move(int location){
        int iBefore=currentChess.getLoc()/10, jBefore= currentChess.getLoc()%10;
        int iAfter=location/10, jAfter=location%10, dx, dy;
        if(iAfter>3||jAfter>7||iAfter<0||jAfter<0)return false;
        target=chessBoard[iAfter][jAfter];
        if(currentChess.show==target.show){
            if(currentChess.getWeight()!=2){
                dx=Math.abs(iAfter-iBefore);
                dy=Math.abs(jAfter-jBefore);
                if(dx+dy==1){
                    if(!target.live) {
                        swap(iBefore, jBefore, iAfter, jAfter);
                        return true;
                    }
                    else{
                        if(!currentChess.getSide().equals(target.getSide())){
                            boolean boss=currentChess.getWeight()==7&&target.getWeight()!=1,
                                    highWeight=currentChess.getWeight()>=target.getWeight(),
                                    eatBoss=currentChess.getWeight()==1&&target.getWeight()==7,
                                    bossCannot=currentChess.getWeight()==7&&target.getWeight()==1;
//                            System.out.print(boss+" ");
//                            System.out.print(highWeight+" ");
//                            System.out.print(eatBoss+" ");
//                            System.out.println(bossCannot);
                            if((boss||highWeight||eatBoss)&&!bossCannot){
                                target.live=false;
                                swap(iBefore, jBefore, iAfter, jAfter);
                                return true;
                            }

                        }
                    }

                }
            }
            else{ // 炮的規定
                int obstacle=0;
                dx=iBefore-iAfter;
                dy=jBefore-jAfter;
                if(dx==0&&(dy>1||dy<-1)){
                    if(dy<-1){
                        for(int i=jBefore+1;i<jAfter;i++){
                            if(chessBoard[iBefore][i].live)obstacle++;
                        }
                    }
                    else{
                        for(int i=jBefore-1;i>jAfter;i--){
                            if(chessBoard[iBefore][i].live)obstacle++;
                        }
                    }
                }
                else if((dx>1||dx<-1)&&dy==0){
                    if(dx<-1){
                        for(int i=iBefore+1;i<iAfter;i++){
                            if(chessBoard[i][jBefore].live)obstacle++;
                        }
                    }
                    else{
                        for(int i=iBefore-1;i>iAfter;i--){
                            if(chessBoard[i][jBefore].live)obstacle++;
                        }
                    }
                }
                if(obstacle==1&&target.live){
                    target.live=false;
                    swap(iBefore, jBefore, iAfter, jAfter);
                    return true;
                }
                if(Math.abs(dx)+Math.abs(dy)==1&&!target.live){
                    swap(iBefore, jBefore, iAfter, jAfter);
                    return true;
                } // 上下左右移動
            }
        }
        return false;
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

        if(currentChess.show){
            return true; // 移動
        }
        else{
            currentChess.show=true;
            if(first==0)first++;
            return false; // 翻開
        }
    }

    boolean checkInputCurrentChess(String input, Player player){
        if(input.length()!=2)return true;
        int i=input.charAt(0)-'A', j=input.charAt(1)-'1';
        if(i>3||j>7)return true;
        if(first!=0&&chessBoard[i][j].show){
            if(!chessBoard[i][j].getSide().equals(player.getSide()))return true;
        }
        if(!chessBoard[i][j].live)return true; // 死掉的不能選
        return false;
    }

    boolean checkInputTarget(String input, Player player){
        if(input.length()!=2)return true;
        int i=input.charAt(0)-'A', j=input.charAt(1)-'1';
        if(i>3||j>7)return true;

        return false;
    }
}

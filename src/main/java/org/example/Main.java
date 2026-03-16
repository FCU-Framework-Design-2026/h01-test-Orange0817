package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("暗棋遊戲");

        Player[] players=new Player[2];
        int p=0;
        System.out.print("請輸入一號玩家名稱: ");
        Scanner sc=new Scanner(System.in);
        players[0]=new Player(sc.next());
        System.out.print("請輸入二號玩家名稱: ");
        players[1]=new Player(sc.next());

        System.out.println("\n================== 遊戲開始 ====================");
        ChessGame game=new ChessGame();
        game.generateChess();

        // 第一次選棋子決定玩家紅黑方
        game.showAllChess();
        System.out.printf("\n%s 請輸入所選棋子的位置: ", players[p++].getName());
        String chessLoc=sc.next();
        int i, j;
        while(game.checkInputCurrentChess(chessLoc, players[0])){
            System.out.print("\n錯誤，請重新輸入所選棋子的位置: ");
            chessLoc=sc.next();
        }
        i=chessLoc.charAt(0)-'A';
        j=chessLoc.charAt(1)-'1';
        game.chessShow(i*10+j); // 翻棋
        game.setPlayer(players[0], players[1]);

        while(!game.gameOver()){
            game.showAllChess();
            System.out.printf("\n%s 請輸入所選棋子的位置: ", players[p].getName());
            chessLoc=sc.next();
            while(game.checkInputCurrentChess(chessLoc, players[p])){
                System.out.print("\n錯誤，請重新輸入所選棋子的位置: ");
                chessLoc=sc.next();
            }
            i=chessLoc.charAt(0)-'A';
            j=chessLoc.charAt(1)-'1';
            if(game.chessShow(i*10+j)){
                System.out.printf("\n%s 請輸入目的位置: ", players[p].getName());
                chessLoc=sc.next();
                while(game.checkInputTarget(chessLoc, players[p])){
                    System.out.print("\n錯誤，請重新輸入目的位置: ");
                    chessLoc=sc.next();
                }
                i=chessLoc.charAt(0)-'A';
                j=chessLoc.charAt(1)-'1';
                while(!game.move(i*10+j)){
                    System.out.print("\n無法移動到該位置，請重新輸入目的位置: ");
                    chessLoc=sc.next();
                    if(chessLoc.equals("esc")){
                        p=1-p;
                        break;
                    }
                    while(game.checkInputTarget(chessLoc, players[p])){
                        System.out.print("\n錯誤，請重新輸入目的位置: ");
                        chessLoc=sc.next();
                    }
                    i=chessLoc.charAt(0)-'A';
                    j=chessLoc.charAt(1)-'1';
                }
            }

            p=1-p; // 換另一個人下棋
        }
    }

}
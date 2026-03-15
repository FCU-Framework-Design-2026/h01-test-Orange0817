# H1 Report

* Name: 洪采萱
* ID: D1149474

---

## 題目：象棋翻棋遊戲 (OOP)
* 考慮一個象棋翻棋遊戲，32 個棋子會隨機的落在 4*8的棋盤上。透過 Chess 的建構子產生這些棋子並隨機編排位置，再印出這些棋子的名字、位置
* ChessGame
    * void showAllChess(); 
    * void generateChess();
* Chess: 
    * Chess(name, weight, side, loc); 
    * String toString();	
* 同上， 
    * ChessGame 繼承一個抽象的 AbstractGame; AbstractGame 宣告若干抽象的方法：
        * setPlayers(Player, Player)
        * boolean gameOver()
        * boolean move(int location)
* 撰寫一個簡單版、非 GUI 介面的 Chess 系統。使用者可以在 console 介面輸入所要選擇的棋子的位置 (例如 A2, B3)，若該位置的棋子未翻開則翻開，若以翻開則系統要求輸入目的的位置進行移動或吃子，如果不成功則系統提示錯誤回到原來狀態。每個動作都會重新顯示棋盤狀態。
* 規則：請參考 [這裏](https://zh.wikipedia.org/wiki/%E6%9A%97%E6%A3%8B#%E5%8F%B0%E7%81%A3%E6%9A%97%E6%A3%8B)

```
    1   2   3  4   5  6   7   8
 A  ＿  兵  ＿  車  Ｘ  ＿  象  Ｘ
 B  Ｘ  ＿  包  Ｘ  士  ＿  馬  Ｘ   
 C  象  兵  Ｘ  車  馬  ＿  ＿  將 
 D  Ｘ  包  ＿  士  兵  Ｘ  ＿  Ｘ  
```

## 設計方法概述
1. 先建立類別 Chess、Player、AbstractGame、ChessGame
2. Chess 除了有名稱 (name)、階級 (weight)、陣營 (side)、位置 (loc)，我還加上了是否翻開 (show)，以及是否被吃掉 (live)，使用函式 toString 時，紅方會使用 ```[ ]``` 來標記，黑方則是以 ```{ }``` 表示，若尚未被翻開，就會顯示 ```Ｘ```，被吃掉會回傳 ```－```。
3. Player 包含玩家名稱 (name) 與陣營 (side)
4. ChessGame 繼承 AbstractGame 後: 
    * setPlayer: 等一號玩家翻開第一個棋子後開始分陣營
    * gameOver: 如果一方的棋子全都被吃光或是沒路可走就會結束遊戲
    * move: 負責檢查棋子能不能移動到目的
    * generateChess: 先創建一個 list 放置棋子的座標 "00" ~ "37" ，然後使用隨機函式打亂，再創一個 4 * 8 的陣列 (chessBoard) 依照剛剛隨機打亂的座標位置來存放棋子
    * showAllChess: 顯示每回合的棋盤畫面
    * swap: 當棋子要 move 時，其實是把棋盤上的兩個棋子交換，在交換前把目的地的棋子 (target) 的 live 改成 false，這樣  時，就只會顯示 ```－``` 在畫面上
    * chessShow: 檢查該棋子是否為翻開狀態，若是就詢問使用者要移動到哪個地方，否則將該棋子翻開
    * checkInputCurrentChess, checkInputTarget: 防呆機制，用來確認使用者輸入的棋子座標是否正確
5. 

## 程式、執行畫面及其說明
迴圈的內容如下：

```java
for (int i = 1; i <= 5; i++) {
    System.out.println("i = " + i);
}
```

每一次，i 的值會變化。執行的畫面如下：

![](img/image.png)

# AI 使用狀況與心得

這個展示比較容易，所以沒有用到 AI

## 心得
我學到的迴圈的使用。

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.net.*;
import java.util.LinkedList;
import java.util.TreeSet;
import javax.swing.*;

public class Board{
    
    private  Piece [][] pieces;
    private boolean moved = false;
    private boolean selected = false;
    private Piece selection = null;
    private int selectedX;
    private int selectedY;
    private boolean has_Fire_left = false;
    private boolean has_Water_left = false;
    private boolean is_fire_turn = true;


    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    private   boolean shouldBeEmpty;





    public Board(boolean shouldBeEmpty){
        pieces = new Piece[8][8];
        for (int i = 0; i < 7; i+=2){
                int j = 0;
               pieces [i][j]  = new Piece(true, this, i, j, "pawn");
            }
            for (int i = 1; i < 7; i+=2){
                int j = 1;
                pieces [i][j]  = new Piece(true, this, i, j, "shield");
            }
            for (int i = 0; i < 7; i+=2){
                int j = 2;
                pieces [i][j]  = new Piece(true, this, i, j, "bomb");
            }
            for (int i = 1; i < 7; i+=2){
                int j = 5;
                 pieces [i][j] = new Piece(false, this, i, j, "bomb");
            }
            for (int i = 0; i < 7; i+=2){
                int j = 6;
                 pieces [i][j]  = new Piece(false, this, i, j, "shield");
            }
            for (int i = 1; i < 7; i+=2){
                int j = 7;
                pieces [i][j]   = new Piece(false, this, i, j, "pawn");

}
    }
    private  void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(pieces[i][j] != null) {
                    drawPiece(i, j, pieces[i][j]);


            }
        }
    
        
    }

    }


    private void drawPiece(int x, int y, Piece p) {
        String img = "img/";

        if (p.isBomb()) {
            img += "bomb";
        }
        else if (p.isShield()) {
            img += "shield";
        }
        else {
            img += "pawn";
        }
        if (p.isFire()) {
            img += "-fire";
        }
        else {
            img += "-water";
        }
        if (p.isKing()) {
            img += "-crowned";
        }
        img += ".png";
    StdDrawPlus.picture(x + .5, y + .5, img, 1, 1);}

    public static void main(String[] args) {
        Board b = new Board(true);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        while(true) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                }
            }
            else if(StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
                b.endTurn();
            }
            b.drawBoard(N);
        


            StdDrawPlus.show(10);
        
    }

}


    private boolean OutofBound(int x, int y) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return true;
        }
        else {
            return false;
        }
    }

        public Piece pieceAt(int x, int y) {
        if (OutofBound(x, y) || pieces[x][y] == null) {
            return null;
        }
        else {
            return pieces[x][y];
        }
    }
        public void place(Piece aPiece, int x, int y) {
        if (!OutofBound(x, y)) {
            pieces[x][y] = aPiece;
        }
    }


    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece p = pieceAt(xi, yi);
        if (OutofBound(xi, yi) || OutofBound(xf, yf) || pieces[xf][yf] != null || p == null) {
            return false;
        }
        if (!p.isKing()) {
            if ((p.isFire() && yf < yi) || (!p.isFire() && yf > yi)) {
                return false;
            }
        }
        if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)){
            return true;
        }
        if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)){
            Piece p1 = pieceAt((xi + xf) / 2, (yi + yf) / 2);
            if (p1 != null && p1.isFire() != p.isFire()) {
                return true;
            }
        }
        return false;



}
public boolean canSelect(int x, int y) {
        if (OutofBound(x, y)) {
            return false;
        }
        else if (pieces[x][y] != null && is_fire_turn == pieces[x][y].isFire() && (!selected || !moved)) {
            return true;
        }
        else if (pieces[x][y] == null && selected) {
            if (!moved && validMove(selectedX, selectedY, x, y)) {
                return true;
            }
            else if (selection.hasCaptured() && validMove(selectedX, selectedY, x, y) && Math.abs(selectedX - x) == 2) {
                return true;
            }
        }
        return false;
    }
public void select(int x, int y) {
        if (pieceAt(x, y) != null) {
            selectedX = x;
            selectedY = y;
            selected = true;
            selection = pieces[x][y];
        }
        else {
            pieceAt(selectedX, selectedY).move(x, y);
            moved = true;
            selectedX = x;
            selectedY = y;
        }
    }

    public Piece remove(int x, int y) {
        Piece removed = pieces[x][y];
        if (OutofBound(x, y) || pieceAt(x, y) == null) {
            return null;
        }
        else {
            pieces[x][y] = null;
            return removed;
        }
    }

    public boolean canEndTurn() {
        if (moved == true) {
            return moved;
        }
        else {
            return false;
        }
    }

    public void endTurn() {
        selected = false;
        if (selection != null) {
            selection.doneCapturing();
        }
        selection = null;
        moved = false;
        is_fire_turn = !is_fire_turn;
    }

    public String winner() {
        has_Fire_left = has_Water_left = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        has_Fire_left = true;
                    }
                    else {
                        has_Water_left = true;
                    }
                }
            }
        }
        if (has_Water_left == true && has_Fire_left == true) {
            return null;
        }
        if (has_Fire_left == true && has_Water_left == false) {
            return "Fire";
        }
        if (has_Fire_left == false && has_Water_left == true) {
            return "Water";
        }
        else {
            return "No one";
        }
    }
}
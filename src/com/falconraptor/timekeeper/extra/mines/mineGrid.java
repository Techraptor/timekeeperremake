package com.falconraptor.timekeeper.extra.mines;

import java.util.Scanner;

public class mineGrid {
    public Scanner in;
    public int width;
    public int height;
    public int numMines;
    public int[][] mine;
    public int[][] nei;
    public boolean[][] reveal;
    public boolean haveLost = false;
    public boolean win;
    public int flagged;

    public mineGrid(int w, int h, int mines) {
        in = new Scanner(System.in);
        mine = new int[w][h];
        nei = new int[w][h];
        reveal = new boolean[w][h];
        width = w;
        height = h;
        numMines = mines;
        flagged = 0;
        addMines();
        recalcNei();
    }

    public boolean inBound(int a, int b) {
        return ((a >= 0 && a < width) && (b >= 0 && b < height));
    }

    public boolean isWin() {
        if (flagged == numMines) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    //unflagged mine or falsly flagged mine.
                    if (mine[x][y] == 1 || mine[x][y] == 2) return false;
                }
            }
            return true;
        }
        return false;
    }

    private void addMines() {
        int rx = 0;
        int ry = 0;
        for (int i = 0; i < numMines; i++) {
            rx = (int) (width * Math.random());
            ry = (int) (height * Math.random());
            if (mine[rx][ry] == 0) mine[rx][ry] = 1;
            else i--;
        }
    }

    public void recalcNei() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                nei[x][y] = 0;
                if (!(x == 0 || y == 0)) nei[x][y] += mine[x - 1][y - 1];
                if (!(y == 0)) nei[x][y] += mine[x][y - 1];
                if (!(x == width - 1 || y == 0)) nei[x][y] += mine[x + 1][y - 1];
                if (!(x == 0)) nei[x][y] += mine[x - 1][y];
                if (!(x == width - 1)) nei[x][y] += mine[x + 1][y];
                if (!(x == 0 || y == height - 1)) nei[x][y] += mine[x - 1][y + 1];
                if (!(y == height - 1)) nei[x][y] += mine[x][y + 1];
                if (!(x == width - 1 || y == height - 1)) nei[x][y] += mine[x + 1][y + 1];
                if (mine[x][y] == 1) nei[x][y] = 9;
            }
        }
    }

    public void reveal(int x, int y) {
        if (mine[x][y] == 0) reveal[x][y] = true;
        if (mine[x][y] == 1) haveLost = true;
        if (nei[x][y] == 0) revealAround(x, y);
    }

    public void revealAround(int x, int y) {
        for (int a = -1; a < 2; a++) {
            for (int b = -1; b < 2; b++) {
                if (inBound(x + a, y + b) && !reveal[x + a][y + b]) reveal(x + a, y + b);
            }
        }
    }

    public void flag(int x, int y) {
        reveal[x][y] = !reveal[x][y];
        if (reveal[x][y]) flagged++;
        else flagged--;
        mine[x][y] = (mine[x][y] + 2) % 4;
    }

    //Beyond this point is just for printing things to the console, so not really needed.
    public void run() {
        String c;
        int x;
        int y;
        while (!win) {
            try {
                c = in.next();
                if (c.equals("p")) print();
                else if (!(c.equals("r") || c.equals("f"))) {
                    win = true;
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                x = in.nextInt();
                y = in.nextInt();
                if (c.equals("r")) {
                    reveal(x, y);
                    print();
                } else if (c.equals("f")) {
                    flag(x, y);
                    print();
                }
            } catch (Exception e) {
            }
        }
    }

    public void print() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (reveal[x][y]) {
                    if (mine[x][y] == 2 || mine[x][y] == 3) System.out.print("@ ");
                    else if (nei[x][y] != 9) System.out.print(nei[x][y] + " ");
                    else System.out.print("* ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.print("\n");
        }
    }
}
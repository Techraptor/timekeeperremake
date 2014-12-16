package com.falconraptor.timekeeperold.extra.mines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class minesweeper extends JFrame {
	JLabel[][] minegrid;
	JPanel p = new JPanel(), b = new JPanel();
	JLabel win = new JLabel("", (int) JLabel.CENTER_ALIGNMENT);
	JButton reset = new JButton("New Game?");
	mineGrid g;
	GridLayout grid, bs, main;
	boolean helped = false;

	public minesweeper () {
		super("Minesweeper");
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setSize(500, 500);
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(false);
		main = new GridLayout(0, 1, 0, 0);
		super.setLayout(main);
	}

	public static void main (String[] args) {
		new minesweeper().fix();
	}

	public void appear () {
		super.setVisible(true);
	}

	private void fix () {
		if (!helped) {
			int w = Integer.parseInt(JOptionPane.showInputDialog("Width of MineField?"));
			int h = Integer.parseInt(JOptionPane.showInputDialog("Height of MineField?"));
			int m = Integer.parseInt(JOptionPane.showInputDialog("Mines in MineField?"));
			if (m >= w * h) {
				JOptionPane.showMessageDialog(null, "ERROR", "Too Many Mines", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (w < 3 || h < 3) {
				JOptionPane.showMessageDialog(null, "ERROR", "Not Enough Spaces", JOptionPane.ERROR_MESSAGE);
				return;
			}
			helped = true;
			intgrid(w, h, m);
		}
	}

	private void addpanels () {
		win.setText("");
		b.add(win);
		b.add(reset);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				fix();
			}
		});
		Box box = Box.createVerticalBox();
		super.setContentPane(box);
		super.getContentPane().add(p, BorderLayout.NORTH);
		super.getContentPane().add(new JSeparator(), BorderLayout.CENTER);
		super.getContentPane().add(b, BorderLayout.SOUTH);
		super.pack();
		super.validate();
		super.repaint();
	}

	public void intgrid (int h, int w, int mines) {
		p = new JPanel();
		b = new JPanel();
		grid = new GridLayout(w, h, 0, 0);
		p.setLayout(grid);
		bs = new GridLayout(1, 2, 0, 0);
		b.setLayout(bs);
		g = new mineGrid(w, h, mines);
		minegrid = new JLabel[g.width][g.height];
		for (int i = 0; g.width > i; i++) {
			for (int j = 0; j < g.height; j++) {
				minegrid[i][j] = new JLabel(" ");
				p.add(minegrid[i][j]);
				minegrid[i][j].addMouseListener(e(i, j));
				Font f = new Font("Times New Roman", Font.PLAIN, 12);
				minegrid[i][j].setFont(f);
			}
		}
		addpanels();
		helped = false;
		update();
		appear();
	}

	private MouseAdapter e (final int i, final int j) {
		return new MouseAdapter() {
			public void mouseClicked (MouseEvent e) {
				if (!g.haveLost) {
					if (e.getButton() >= 2) g.flag(i, j);
					else {
						if (!g.reveal[i][j]) g.reveal(i, j);
					}
					update();
				}
			}
		};
	}

	private void update () {
		for (int i = 0; i < g.width; i++) {
			for (int j = 0; j < g.height; j++) {
				if (g.reveal[i][j]) {
					if (g.mine[i][j] >= 2) minegrid[i][j].setText("@");
					else if (g.nei[i][j] != 9) minegrid[i][j].setText(g.nei[i][j] + "");
					else minegrid[i][j].setText("*");
				} else minegrid[i][j].setText("■");
			}
		}
		if (g.haveLost) win.setText("You Lost");
		else if (g.isWin()) win.setText("You Win!");
		super.pack();
	}
}
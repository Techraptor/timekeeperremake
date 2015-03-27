package com.darkleach7.extra.Calender;

import com.falconraptor.timekeeper.other.*;
import com.falconraptor.utilities.logger.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.*;

public class Calender extends JFrame {
	private final static String log = References.log + ".extra.utilities.Calender.Calender.";
	private final ArrayList<JPanel> p = new ArrayList<>(0);
	private final ArrayList<JButton> b = new ArrayList<>(0);
	private final ArrayList<JButton> d = new ArrayList<>(0);
	private final HashMap<JButton, ActionListener> da = new HashMap<>();
    //public ArrayList<JLabel> l = new ArrayList<>(0);
    private final ArrayList<JMenu> m = new ArrayList<>(0);
    //public ArrayList<ActionListener> a = new ArrayList<>(0);
    private final JMenuBar menu = new JMenuBar();
	private final JLabel[] ld = new JLabel[7];
    //public editdays ed = new editdays();
    private final JButton left = new JButton("");
	private final JButton right = new JButton("");
	private final LocalDate currentDate = LocalDate.now();
	public ArrayList<JMenuItem> mi = new ArrayList<>(0);
	public boolean[] nm = new boolean[43];
	public JButton selectDate = new JButton("Go to");
	int lastDayOfMonth;
	int lastDayOfLastMonth;
	private int firstDayOfMonth;
	private int lengthOfMonth;
	private LocalDate workingDate = currentDate.withDayOfMonth(1);
	private LocalDate selectedDate = LocalDate.now().withYear(1900).withDayOfMonth(1).withMonth(1);
	private Font font = new Font("Tahoma", Font.PLAIN, 12);

    //int int = new int int = new int;
    public Calender() {
        super("Calender");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(false);
        setContentPane(setgui());
        pack();
    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }

    private JPanel setgui() {
        Logger.logINFO("Starting Calendar...");
        setJMenuBar(menu); // adding the menuBar
        setFileMenu();
        setUtilitiesMenu();
        setCustomizeMenu();
        for (int i = 0; i <= 2; i++) {
            menu.add(m.get(i));
        }
        p.add(new JPanel(new GridLayout(8, 7, 0, 0))); //creating the layout of calendar, currently blank
        d.add(new JButton(currentDate.getMonth() + " " + currentDate.getYear())); //getting the name of the month to add to the calendar
        p.get(0).add(left); //adding the left arrow
        for (int i = 0; i < 2; i++) p.get(0).add(new JLabel("", 0));// adding blank spaces
        p.get(0).add(d.get(0));//adding the month name
        //p.get(0).add(selectDate);//adding the goto button
        for (int i = 0; i < 2; i++) p.get(0).add(new JLabel("", 0));//adding blank spaces
        p.get(0).add(right);//adding the right arrow
        //adding the names of the days to the calendar
        setDateLabels();
        //adding the editable labels for the dates
        for (int i = 0; i < 42; i++) {
            p.add(new JPanel(new GridLayout(2, 1, 0, 0)));
            d.add(new JButton(""));
            d.get(i).setBorderPainted(false);
            d.get(i).setContentAreaFilled(false);

            b.add(new JButton("Edit"));
            p.get(i + 1).add(d.get(i + 1));
            p.get(0).add(p.get(i + 1));
        }

        setDates();
        setCalender();

        //adding action for the button to go back a month
        left.addActionListener(e -> {
            //removeActionListeners();
            if (workingDate.getMonthValue() == 1) {
                workingDate = workingDate.withYear(workingDate.getYear() - 1);
                workingDate = workingDate.withMonth(12);
            } else {
                workingDate = workingDate.withMonth(workingDate.getMonthValue() - 1);
            }
            clearMap();
            setDates();
            setCalender();

            //this is a comment
        });
        right.addActionListener(e -> {
            //removeActionListeners();
            if (workingDate.getMonthValue() + 1 == 13) {
                workingDate = workingDate.withMonth(1);
                workingDate = workingDate.withYear(workingDate.getYear() + 1);
            } else {
                workingDate = workingDate.withMonth(workingDate.getMonthValue() + 1);
            }
            clearMap();
            setDates();
            setCalender();
        });

        return p.get(0);
    }

    private void setDateLabels() {
        for (int i = 1; i < 8; i++) {
            ld[i - 1] = new JLabel(getDateLabel(i), 0);
            ld[i - 1].setFont(getFont());
            p.get(0).add(ld[i - 1]);
        }
    }

    private void setDateLabelFont() {
        for (int i = 1; i < 8; i++) {
            //ld[i - 1] = new JLabel(getDateLabel(i), 0);
            ld[i - 1].setFont(getFont());
            //p.get(0).add(ld[i - 1]);
        }
    }

    private void setCalender() {
        clearMap();
        d.get(0).setFont(getFont());
        d.get(0).setText(workingDate.getMonth() + " " + workingDate.getYear());
        ///a.add(clicked(0, 0, 0));
        //d.get(0).addActionListener(a.get(0));
        //adding blank (soon days of prev month) entries if the month doesn't start on 1 (Monday)
        int k = 0;
        for (int i = firstDayOfMonth; i >= 1; i--) {
            d.get(i).setForeground(Color.GRAY);
            LocalDate a;
            if (workingDate.getMonthValue() == 1) {
                a = workingDate.withMonth(12);
            } else {
                a = workingDate.withMonth(workingDate.getMonthValue() - 1);
            }
            a = a.withDayOfMonth(a.lengthOfMonth());
            d.get(i).setFont(getFont());
            d.get(i).setText(" " + (a.lengthOfMonth() + 1 - k));
            k++;
            //a.add(clicked(0, 0, 0));
            //d.get(i).addActionListener(a.get(i));
        }
        //adding the days of th month
        for (int i = firstDayOfMonth; i <= lengthOfMonth + firstDayOfMonth; i++) {
            if (i - firstDayOfMonth + 1 == selectedDate.getDayOfMonth() && workingDate.getMonthValue() == selectedDate.getMonthValue() && workingDate.getYear() == selectedDate.getYear()) {
                d.get(i).setForeground(Color.RED);
                d.get(i).setFont(getFont());
                d.get(i).setText("" + (i - firstDayOfMonth + 1));
                da.put(d.get(i), clicked(i - firstDayOfMonth + 1, workingDate.getMonthValue(), workingDate.getYear()));
                d.get(i).addActionListener(da.get(d.get(i)));
            } else if (i - firstDayOfMonth + 1 == currentDate.getDayOfMonth() && workingDate.getMonthValue() == currentDate.getMonthValue() && workingDate.getYear() == currentDate.getYear()) {
                d.get(i).setForeground(Color.CYAN);
                d.get(i).setFont(getFont());
                d.get(i).setText("" + (i - firstDayOfMonth + 1));
                //a.add(clicked(i - firstDayOfMonth + 1, workingDate.getMonthValue(), workingDate.getYear()));
                da.put(d.get(i), clicked(i - firstDayOfMonth + 1, workingDate.getMonthValue(), workingDate.getYear()));
                d.get(i).addActionListener(da.get(d.get(i)));
                //setMap(d.get(i),d.get(i).getActionListeners()[0]);
            } else {
                d.get(i).setForeground(Color.BLACK);
                d.get(i).setFont(getFont());
                d.get(i).setText("" + (i - firstDayOfMonth + 1));
                //a.add(clicked(i - firstDayOfMonth + 1, workingDate.getMonthValue(), workingDate.getYear()));
                da.put(d.get(i), clicked(i - firstDayOfMonth + 1, workingDate.getMonthValue(), workingDate.getYear()));
                d.get(i).addActionListener(da.get(d.get(i)));
                //setMap(d.get(i),d.get(i).getActionListeners()[0]);
            }
        }
        //adding the blank entries after the month (will be next months days)
        int j = 1;
        for (int i = lengthOfMonth + firstDayOfMonth; i < 42; i++) {
            d.get(i).setForeground(Color.GRAY);
            d.get(i).setFont(getFont());
            d.get(i).setText(" " + (j));
            j++;
        }
        pack();

    }

    private void clearMap() {
	    for (JButton aD : d) {
		    aD.removeActionListener(da.get(aD));
	    }
	    da.clear();
    }

    private int fixday(int day) {
        day++;
        if (day == 8) day = 1;
        return day;
    }

    private void setDates() {
        firstDayOfMonth = fixday(workingDate.getDayOfWeek().getValue());
        lengthOfMonth = workingDate.lengthOfMonth();
        //workingDate = workingDate.withDayOfMonth(lengthOfMonth);
        //lastDayOfMonth = workingDate.getDayOfWeek().getValue();
        ///lastDayOfLastMonth = firstDayOfMonth-1;
    }

    private void setFileMenu() {
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        //addActionListener
        JMenuItem save = new JMenuItem("Save");
        //addActionListener
        JMenuItem reset = new JMenuItem("Reset");
        //addActionListener
        JMenuItem exit = new JMenuItem("Exit");
        exit = setMenuItem(exit, KeyEvent.VK_W, "Exit Calendar", KeyEvent.VK_W, ActionEvent.CTRL_MASK);
        exit.addActionListener(e -> {
            Logger.logINFO("Exiting Calendar");
            dispose();
        });
        //file.add(open);
        //file.add(save);
        //file.addSeparator();
        //file.add(reset);
        //file.addSeparator();
        //file.add(exit);
        m.add(file);
    }

    private void setUtilitiesMenu() {
        JMenu utils = new JMenu("Utilities");
        JMenuItem goTo = new JMenuItem("Go To Date");
        goTo = setMenuItem(goTo, KeyEvent.VK_G, "Go To Date", KeyEvent.VK_G, ActionEvent.CTRL_MASK);
        goTo.addActionListener(e -> {
            JFrame gtd = new JFrame("Go to Date");
            gtd.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            gtd.setLocationRelativeTo(null);
            gtd.setVisible(true);
            gtd.setContentPane(dateGUI());
            gtd.pack();
        });
        //addActionListener
        utils.add(goTo);
        m.add(utils);
    }

    private void setCustomizeMenu() {
        JMenu custom = new JMenu("Customize");
        JMenuItem bgColor = new JMenuItem("Set Calendar Color");
        bgColor = setMenuItem(bgColor, KeyEvent.VK_1, "Set Color", KeyEvent.VK_1, ActionEvent.CTRL_MASK);
        bgColor.addActionListener(e -> {
            JFrame guiFrame = new JFrame();
            Color selectedColor = JColorChooser.showDialog(guiFrame, "Pick a Color"
                    , Color.GREEN);
            this.getContentPane().setBackground(selectedColor);
		   for (JButton aD : d) {
			   aD.setBackground(selectedColor);
			   //b.get(i).setBackground(selectedColor);
		   }
	   });

        JMenuItem bgImage = new JMenuItem("Set Custom Calender Image");
        //addActionListener
        JMenuItem fontStyle = new JMenuItem("Set Font");
        fontStyle = setMenuItem(fontStyle, KeyEvent.VK_3, "Set Font", KeyEvent.VK_3, ActionEvent.CTRL_MASK);
        fontStyle.addActionListener(e -> {
            JFrame sf = new JFrame("Set Font...");
            sf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            sf.setLocationRelativeTo(null);
            sf.setVisible(true);
            sf.setContentPane(fontGUI());
            sf.pack();
        });
        //addActionListener
        JMenuItem fontColor = new JMenuItem("Set Font Color");
        //addActionListener
        JMenuItem fontSize = new JMenuItem("Set Font Size");
        //addActionListener
        //custom.add(bgColor);
        //custom.add(bgImage);
        //custom.addSeparator();
        custom.add(fontStyle);
        //custom.add(fontColor);
        //custom.add(fontSize);
        m.add(custom);
    }

    public void appear() {
        setVisible(true);
    }

    private ActionListener clicked(final int date, final int month, final int year) {
        try {
            return e -> Logger.logINFO("" + month + "/" + date + "/" + year);
        } catch (Exception e) {
            return e1 -> Logger.logERROR(log + "clicked]" + e1);
        }
    }

    private String getDateLabel(int day) {
        LocalDate date = currentDate.withYear(2014).withMonth(9).withDayOfMonth(7);
        date = date.withDayOfMonth(date.getDayOfMonth() + day - 1);
        return "" + date.getDayOfWeek();
    }

    private JMenuItem setMenuItem(JMenuItem j, int mn, String tt, int mn2, int ae) {
	    j.setMnemonic(mn);
	    j.setToolTipText(tt);
	    j.setAccelerator(KeyStroke.getKeyStroke(mn2, ae));
	    return j;
    }

    private JPanel dateGUI() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 0, 0));
        Integer[] months = new Integer[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        };
        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) {
		   days[i] = i + 1;
	   }
        Integer[] years = new Integer[201];
        for (int i = 0; i < 201; i++) {
		   years[i] = i + 1900;
	   }
        JComboBox month = new JComboBox(months);
        JComboBox day = new JComboBox(days);
        JComboBox year = new JComboBox(years);
        month.setEditable(true);
        day.setEditable(true);
        year.setEditable(true);
        panel.add(new JLabel("Month"));
        panel.add(month);
        panel.add(new JLabel("Day"));
        panel.add(day);
        panel.add(new JLabel("Year"));
        panel.add(year);
        JButton go = new JButton("Go");
        go.addActionListener(e1 -> {
            try {
                selectedDate = selectedDate.withMonth((int) month.getSelectedItem());
                selectedDate = selectedDate.withDayOfMonth((int) day.getSelectedItem());
                selectedDate = selectedDate.withYear((int) year.getSelectedItem());
                Logger.logINFO("Selected Date: " + selectedDate.getMonthValue() + " " + selectedDate.getDayOfMonth() + " " + selectedDate.getYear());
                workingDate = selectedDate;
                clearMap();
                setDates();
                setCalender();
            } catch (Exception e) {
                Logger.logINFO("Year: " + (year.getSelectedIndex() + 1900));
                Logger.logINFO("Month: " + (month.getSelectedIndex() + 1));
                Logger.logINFO("Day: " + (day.getSelectedIndex() + 1));
                Logger.logERROR(e);
            }
        });
        panel.add(go);
        return panel;
    }

    private JPanel fontGUI() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 0, 0));
        String[] fonts = {
                "Arial", "Times New Roman", "Tahoma", "Comic Sans MS", "Calibri", "Rockwell Extra Bold",
        };
        String[] styles = new String[]{
                "PLAIN", "ITALIC", "BOLD"
        };
        Integer[] sizes = new Integer[]{
                8, 9, 10, 11, 12, 13, 14, 16, 18, 20, 22, 24, 26, 28, 32, 36, 40, 44, 48, 54, 60, 66, 72, 80, 88, 96, 1000
        };
        JComboBox font = new JComboBox(fonts);
        JComboBox style = new JComboBox(styles);
        JComboBox size = new JComboBox(sizes);
        font.setEditable(false);
        style.setEditable(false);
        size.setEditable(true);
        panel.add(new JLabel("Font"));
        panel.add(font);
        panel.add(new JLabel("Style"));
        panel.add(style);
        panel.add(new JLabel("Size"));
        panel.add(size);
        JButton go = new JButton("Set");
        go.addActionListener(e -> {
            if (style.getSelectedIndex() == 0) {
                setFont(new Font((String) font.getItemAt(font.getSelectedIndex()), Font.PLAIN, (int) size.getSelectedItem()));
            } else if (style.getSelectedIndex() == 1) {
                setFont(new Font((String) font.getItemAt(font.getSelectedIndex()), Font.ITALIC, (int) size.getSelectedItem()));
            } else if (style.getSelectedIndex() == 2) {
                setFont(new Font((String) font.getItemAt(font.getSelectedIndex()), Font.BOLD, (int) size.getSelectedItem()));
            }
            setDateLabelFont();
            setDates();
            setCalender();
        });
        panel.add(go);
        return panel;
    }

    private void removeActionListeners() {
        for (int i = 0; i < 42; i++) {
            Logger.logINFO(d.get(i).getActionListeners()[0].toString());
            // d.get(i).removeActionListener();
        }
    }

	public final Font getFont () {
		return font;
    }

    public void setFont(Font f) {
        font = f;
    }
}
package com.falconraptor.timekeeperold.extra.utilities;

import com.falconraptor.utilities.logger.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class util {
    public static String log = "[com.falconraptor.timekeeperold.extra.utilities.util.";

    public static void windowsr(Robot r) {
        r.keyPress(KeyEvent.VK_WINDOWS);
        r.keyPress('R');
        r.keyRelease(KeyEvent.VK_WINDOWS);
        r.keyRelease('R');
        r.delay(50);
    }

    public static void enter(Robot r) {
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void sendtext(String s, Robot r) {
        int[] command = getcodes(s);
        for (int c : command) {
            if (c < 0) {
                c *= -1;
                r.keyPress(KeyEvent.VK_SHIFT);
                r.keyPress(c);
                r.keyRelease(KeyEvent.VK_SHIFT);
                r.keyRelease(c);
            } else {
                r.keyPress(c);
                r.keyRelease(c);
            }
        }
    }

    public static int[] getcodes(String s) {
        s = s.toLowerCase();
        int[] l = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a') l[i] = KeyEvent.VK_A;
            else if (c == 'b') l[i] = KeyEvent.VK_B;
            else if (c == 'c') l[i] = KeyEvent.VK_C;
            else if (c == 'd') l[i] = KeyEvent.VK_D;
            else if (c == 'e') l[i] = KeyEvent.VK_E;
            else if (c == 'f') l[i] = KeyEvent.VK_F;
            else if (c == 'g') l[i] = KeyEvent.VK_G;
            else if (c == 'h') l[i] = KeyEvent.VK_H;
            else if (c == 'i') l[i] = KeyEvent.VK_I;
            else if (c == 'j') l[i] = KeyEvent.VK_J;
            else if (c == 'k') l[i] = KeyEvent.VK_K;
            else if (c == 'l') l[i] = KeyEvent.VK_L;
            else if (c == 'm') l[i] = KeyEvent.VK_M;
            else if (c == 'n') l[i] = KeyEvent.VK_N;
            else if (c == 'o') l[i] = KeyEvent.VK_O;
            else if (c == 'p') l[i] = KeyEvent.VK_P;
            else if (c == 'q') l[i] = KeyEvent.VK_Q;
            else if (c == 'r') l[i] = KeyEvent.VK_R;
            else if (c == 's') l[i] = KeyEvent.VK_S;
            else if (c == 't') l[i] = KeyEvent.VK_T;
            else if (c == 'u') l[i] = KeyEvent.VK_U;
            else if (c == 'v') l[i] = KeyEvent.VK_V;
            else if (c == 'w') l[i] = KeyEvent.VK_W;
            else if (c == 'x') l[i] = KeyEvent.VK_X;
            else if (c == 'y') l[i] = KeyEvent.VK_Y;
            else if (c == 'z') l[i] = KeyEvent.VK_Z;
            else if (c == ' ') l[i] = KeyEvent.VK_SPACE;
            else if (c == '0') l[i] = KeyEvent.VK_0;
            else if (c == '1') l[i] = KeyEvent.VK_1;
            else if (c == '2') l[i] = KeyEvent.VK_2;
            else if (c == '3') l[i] = KeyEvent.VK_3;
            else if (c == '4') l[i] = KeyEvent.VK_4;
            else if (c == '5') l[i] = KeyEvent.VK_5;
            else if (c == '6') l[i] = KeyEvent.VK_6;
            else if (c == '7') l[i] = KeyEvent.VK_7;
            else if (c == '8') l[i] = KeyEvent.VK_8;
            else if (c == '9') l[i] = KeyEvent.VK_9;
            else if (c == '.') l[i] = KeyEvent.VK_PERIOD;
            else if (c == '/') l[i] = KeyEvent.VK_SLASH;
            else if (c == ':') l[i] = -1 * KeyEvent.VK_SEMICOLON;
            else if (c == ';') l[i] = KeyEvent.VK_SEMICOLON;
            else if (c == '\\') l[i] = KeyEvent.VK_BACK_SLASH;
        }
        return l;
    }

    public static int[] checkerrors(String test) {
        return new int[]{findnum(test, "r="), findnum(test, "g="), findnum(test, "b=")};
    }

    public static int findnum(String abc, String find) {
        int a = 5, t;
        for (int i = 0; i < 4; i++) {
            try {
                t = Integer.parseInt(abc.substring(abc.indexOf(find) + 2, abc.indexOf(find) + a));
                break;
            } catch (Exception e) {
                if (Logger.level <= 5) Logger.logERROR(log + "findnum] " + e);
                a--;
            }
        }
        return a;
    }
}

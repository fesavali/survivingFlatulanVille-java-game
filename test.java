/* ***************************************************************************
 *
 * Project:          Project Part One
 * Project Name:     Flatulan
 * Main Class File:  survivingFlatulanVille
 * File:             test.java
 *
 * Username:         mmccullough
 * @author           Malcolm McCullough
 * @version          0.9
 * @since            2021-01-81
 * Instructors:      McCullough
 * Section:          01
 *
 *
 * Description: Has Flatulan, City, Player, and Games classes
 * Simple game
 *  You have arrived in the walled city of Flatula, whose residents eat beans,
 *  cabbage, and hard-boiled eggs for every meal. Although they are used to
 *  the resulting effects, the odor that emanates from the Flatulans makes you
 *  woozy. Your only hope is to convert all the citizens to a diet of lean
 *  meat, lettuce, and rice.
 *
 *
 *  Run with
 *   java -enableassertions:test test
 *
 * ***************************************************************************/

import java.io.*;

/* ******************************************************************/
/*                                                                  */
/*          testing  driver Class                                   */
/*                                                                  */
/* ******************************************************************/
class test {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int NUMDIRS = 4; // number of directions play can move
    public static final int MAXFLATULANS = 512;

    static int countFlatulans(int size, String grid) {
        int count = 0;
        int stop = grid.indexOf("There");
        for (int i = 0; i < stop; i++) {
            if (grid.charAt(i) != '.'
                    && grid.charAt(i) != '@'
                    && grid.charAt(i) != ' '
                    && grid.charAt(i) != '\n') {
                count++;
            }
        }
        return count;
    }

    static boolean testone(int testnumber) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;

        switch (testnumber) {
            default: {
                    System.out.println("Bad argument ");
                    return false;
                }

            case 1: {
                try {
                    City c = new City(10, 18);
                    c.addPlayer(2, 2);
                    Flatulan f = new Flatulan(c, 5, 17);
                    assert (f.row() == 5 && f.col() == 17);
                 } catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                }
                break;

            case 2: {
                try{
                    boolean movedr = false;
                    boolean movedl = false;
                    boolean stayed = false;
                    for (int k = 0; k < 150; k++) {  
                        City c = new City(1, 30);
                        c.addPlayer(1, 1);
                        Flatulan f = new Flatulan(c, 1, 29);
                        assert ((f.row() == 1) && (f.col() == 29));
                        for (int m = 0; m < 3; m++) {
                            //System.out.println("" + f.col() );
                            int oldc = f.col();
                            f.move();
                            assert (f.row() == 1 && f.col() <= 30);
                            int d = f.col() - oldc;
                            //System.out.println("" + f.col() + " - " + oldc + " = " + d);
                            switch (d) {
                                case 0:
                                    stayed = true;
                                    break;
                                case 1:
                                    movedr = true;
                                    break;
                                case -1:
                                    movedl = true;
                                    break;
                                default:
                                    assert (false);
                            }
                        }
                    }
    
                    assert (movedr && movedl && stayed) : movedr + " " + movedl + " " + stayed;
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                

                }
                break;

            case 3: {
                    
                    try{
                    boolean movedd = false;
                    boolean movedu = false;
                    boolean stayed = false;

                        for (int k = 0; k < 150; k++) {
                            City c = new City(20, 1);
                            c.addPlayer(1, 1);
                            Flatulan f = new Flatulan(c, 19, 1);
                            assert (f.row() == 19 && f.col() == 1);
                            for (int m = 0; m < 2; m++) {
                                int oldr = f.row();
                                f.move();
                                assert (f.col() == 1 && f.row() <= 20);
                                int d = f.row() - oldr;
                                switch (d) {
                                    case 0:
                                        stayed = true;
                                        break;
                                    case 1:
                                        movedd = true;
                                        break;
                                    case -1:
                                        movedu = true;
                                        break;
                                    default:
                                        assert (false);
                                }
                            }
                        }
                        assert (movedd && movedu && stayed);
                    }catch (InterruptedException e) {
                           System.err.println("Got interrupted...");
                           System.exit(0);
                        } 
                    
                }
                break;

            case 4: {
                try{
                    City c = new City(20, 20);
                    c.addPlayer(1, 1);
                    Flatulan f = new Flatulan(c, 12, 12);
                    for (int k = 0; k < 8; k++) {
                        int oldr = f.row();
                        int oldc = f.col();
                        f.move();
                        assert ((f.row() == oldr && Math.abs(f.col() - oldc) == 1)
                                || (f.col() == oldc && Math.abs(f.row() - oldr) == 1));
                    }
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 } 
                  
                }
                break;

            case 5: {
                        try{
                             int dr[] = {-1, 0, 1, 0, 0, 0};
                    int dc[] = {0, 1, 0, -1, 0, 0};
                    for (int k = 0; k < NUMDIRS; k++) {
                        int nvisits[] = {0, 0, 0, 0, 0};
                            for (int m = 0; m < 100; m++) {
                                City c = new City(20, 20);
                                c.addPlayer(10, 10);
                                int oldr = 10 - dr[k];
                                int oldc = 10 - dc[k];
                                Flatulan f = new Flatulan(c, oldr, oldc);
                                f.move();
                                int rdiff = f.row() - oldr;
                                int cdiff = f.col() - oldc;
                                int i;
                                for (i = 0; i < NUMDIRS + 1; i++) {
                                    if (rdiff == dr[i] && cdiff == dc[i]) {
                                        nvisits[i]++;
                                        break;
                                    }
                                }
                                assert (i < NUMDIRS + 1);
                            }
                            for (int i = 0; i < NUMDIRS + 1; i++)
                                assert (k == i ? nvisits[i] == 0 : nvisits[i] > 1);
                            }
                        }catch (InterruptedException e) {
                           System.err.println("Got interrupted...");
                           System.exit(0);
                        } 
                       
                }
                break;

            case 6: {
                    
                    try{
                        int m = 0;
                        for (int k = 0; k < 600; k++) {
                            City c = new City(10, 20);
                            c.addPlayer(1, 1);
                            Flatulan f = new Flatulan(c, 1, 2);
                            if (f.possiblyGetConverted()) m++;
                        }
                        assert (m >= 360 && m <= 440);
                    }catch (InterruptedException e) {
                           System.err.println("Got interrupted...");
                           System.exit(0);
                        } 
                   
                }
                break;

            case 7: {
                try{
                    City c = new City(10, 20);
                    Player p = new Player(c, 2, 3);
                    assert (p.row() == 2 && p.col() == 3 && !p.isPassedOut());  
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 } 
                }
                break;

            case 8: {
                try{
                    City c = new City(10, 20);
                    Player p = new Player(c, 2, 3);
                    for (int k = 0; k < 12; k++) {
                        assert (!p.isPassedOut());
                        p.getGassed();
                    }
                    assert (p.isPassedOut());
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 } 
                }
                break;

            case 9: {
                try{
                    City c = new City(10, 20);
                    Player p = new Player(c, 2, 3);
                    for (int k = 0; k < 12; k++) {
                        assert (p.health() == 12 - k);
                        p.getGassed();
                    }
                    assert (p.isPassedOut());
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 } 
                  
                }
                break;

            case 10: {
                try{
                    City c = new City(10, 18);
                    c.addPlayer(10, 17);
                    Player pp = c.player();
                    pp.move(RIGHT);
                    assert (pp.row() == 10 && pp.col() == 18 && !pp.isPassedOut());
                    pp.move(DOWN);
                    assert (pp.row() == 10 && pp.col() == 18 && !pp.isPassedOut());
                    pp.move(RIGHT);
                    assert (pp.row() == 10 && pp.col() == 18 && !pp.isPassedOut());
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 } 
                   
                }
                break;

            case 11: {
                try{
                    City c = new City(10, 18);
                    c.addPlayer(2, 1);
                    Player pp = c.player();
                    pp.move(UP);
                    assert (pp.row() == 1 && pp.col() == 1 && !pp.isPassedOut());
                    pp.move(LEFT);
                    assert (pp.row() == 1 && pp.col() == 1 && !pp.isPassedOut());
                    pp.move(UP);
                    assert (pp.row() == 1 && pp.col() == 1 && !pp.isPassedOut());
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 } 
                    
                }
                break;

            case 12: {
                try{
                    City c = new City(10, 20);
                    c.addPlayer(8, 12);
                    Player pp = c.player();
                    pp.move(UP);
                    assert (pp.row() == 7 && pp.col() == 12);
                    pp.move(DOWN);
                    assert (pp.row() == 8 && pp.col() == 12);
                    pp.move(LEFT);
                    assert (pp.row() == 8 && pp.col() == 11);
                    pp.move(RIGHT);
                    assert (pp.row() == 8 && pp.col() == 12);
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 } 
                }
                break;

            case 13: {
                try{
                    City c = new City(10, 18);
                    c.addPlayer(5, 7);
                    c.addFlatulan(3, 7);
                    c.addFlatulan(4, 9);
                    c.addFlatulan(6, 8);
                    c.addFlatulan(5, 6);
                    Player pp = c.player();
                    pp.move(UP);
                    assert (pp.row() == 4 && pp.col() == 7 && !pp.isPassedOut());
                    pp.move(UP);
                    assert (pp.row() == 4 && pp.col() == 7 && !pp.isPassedOut());
                    pp.move(RIGHT);
                    assert (pp.row() == 4 && pp.col() == 8 && !pp.isPassedOut());
                    pp.move(RIGHT);
                    assert (pp.row() == 4 && pp.col() == 8 && !pp.isPassedOut());
                    pp.move(DOWN);
                    assert (pp.row() == 5 && pp.col() == 8 && !pp.isPassedOut());
                    pp.move(DOWN);
                    assert (pp.row() == 5 && pp.col() == 8 && !pp.isPassedOut());
                    pp.move(LEFT);
                    assert (pp.row() == 5 && pp.col() == 7 && !pp.isPassedOut());
                    pp.move(LEFT);
                    assert (pp.row() == 5 && pp.col() == 7 && !pp.isPassedOut());
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                   
                }
                break;

            case 14: {
                try{
                    City c = new City(10, 18);
                    c.addPlayer(3, 9);
                    c.addFlatulan(4, 9);
                    assert (c.flatulanCount() == 1);
                    for (int k = 0; k < 100 && c.flatulanCount() == 1; k++) c.player().preach();
                    assert (c.flatulanCount() == 0);
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                    
                }
                break;

            case 15: {
                try{
                    City c = new City(10, 18);
                    c.addPlayer(3, 9);
                    assert (c.flatulanCount() == 0);
                    for (int k = 1; k <= 20; k++) {
                        c.addFlatulan(3, 8);
                        c.addFlatulan(3, 10);
                        assert (c.flatulanCount() == 2 * k);
                    }
                    int prevct = c.flatulanCount();
                    for (int k = 0; k < 100 && prevct > 0; k++) {
                        c.player().preach();
                        int ct = c.flatulanCount();
                        assert (ct <= prevct);
                        prevct = ct;
                    }
                    assert (prevct == 0);
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                   
                }
                break;

            case 16: {
                try{
                    City c = new City(10, 18);
                    c.addPlayer(3, 9);
                    c.addFlatulan(2, 9);
                    assert (c.nFlatulansAt(2, 9) == 1);
                    for (int k = 0; k < 100 && c.nFlatulansAt(2, 9) == 1; k++) c.player().preach();
                    assert (c.nFlatulansAt(2, 9) == 0);
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                    
                }
                break;

            case 17: { 
                try{
                    // Tell Java to use your special stream
                    System.setOut(ps);
                    City c = new City(2, 3);
                    c.addPlayer(1, 2);
                    c.addFlatulan(1, 3);
                    c.addFlatulan(2, 2);
                    // System.out has been redirected to a stream called baos (see above)
                    c.display();
                    // create astring with output
                    String s = baos.toString();
                    // Put System.out back
                    System.out.flush();
                    System.setOut(old);
                    assert (s.indexOf(". @ F") != -1 && s.indexOf(". F .") != -1);
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }

                }
                break;

            case 18: { 
                try{
                    // Tell Java to use your special stream
                    System.setOut(ps);
                    City c = new City(2, 3);
                    c.addPlayer(1, 2);
                    for (int k = 1; k <= 8; k++) c.addFlatulan(1, 3);
                    c.addFlatulan(2, 2);
                    // System.out has been redirected to a stream called baos (see above)
                    c.display();
                    // create astring with output
                    String s = baos.toString();
                    // Put System.out back
                    System.out.flush();
                    System.setOut(old);
                    assert (s.indexOf(". @ 8") != -1) : s;
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                    
                }
                break;

            case 19: { 
                try{
                    // Tell Java to use your special stream
                    System.setOut(ps);
                    City c = new City(2, 3);
                    c.addPlayer(1, 2);
                    for (int k = 1; k <= 10; k++) c.addFlatulan(1, 3);
                    c.addFlatulan(2, 2);
                    c.display();
                    String s = baos.toString();
                    // Put System.out back
                    System.out.flush();
                    System.setOut(old);
                    // assert(s.indexOf(". @ A") != -1 ) : s;
                    assert (s.indexOf(". @ #") != -1) : s;
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }

                }
                break;

            case 20: { 
                try{
                    // Tell Java to use your special stream
                    System.setOut(ps);
                    City c = new City(10, 18);
                    c.addPlayer(3, 9);
                    int prevct[] = {0, 0, 0};
                    for (int dr = -1; dr <= 1; dr++) {
                        if (dr == 0) continue;
                        for (int k = 0; k < City.MAXFLATULANS / 2; k++)
                            assert (c.addFlatulan(3 + dr, 9));
                        prevct[1 + dr] = c.nFlatulansAt(3 + dr, 9);
                        assert (prevct[1 + dr] == City.MAXFLATULANS / 2);
                    }
                    boolean anyleft = true;
                    for (int k = 0; k < 100 && anyleft; k++) {
                        anyleft = false;
                        c.player().preach();
                        for (int dr = -1; dr <= 1; dr++) {
                            if (dr == 0) continue;
                            int ct = c.nFlatulansAt(3 + dr, 9);
                            assert (ct <= prevct[1 + dr]);
                            prevct[1 + dr] = ct;
                            if (ct > 0) anyleft = true;
                        }
                    }
                    assert (!anyleft);
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                    
                }
                break;

            case 21: {
                try{
                    City c = new City(10, 18);
                    c.addPlayer(3, 9);
                    int prevct[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                    for (int dr = -1; dr <= 1; dr++)
                        for (int dc = -1; dc <= 1; dc++) {
                            if (dr == 0 && dc == 0) continue;
                            for (int k = 0; k < City.MAXFLATULANS / 8; k++)
                                assert (c.addFlatulan(3 + dr, 9 + dc));
                            prevct[1 + dr][1 + dc] = c.nFlatulansAt(3 + dr, 9 + dc);
                            assert (prevct[1 + dr][1 + dc] == City.MAXFLATULANS / 8);
                        }
                    boolean anyleft = true;
                    for (int k = 0; k < 100 && anyleft; k++) {
                        anyleft = false;
                        c.player().preach();
                        for (int dr = -1; dr <= 1; dr++)
                            for (int dc = -1; dc <= 1; dc++) {
                                if (dr == 0 && dc == 0) continue;
                                int ct = c.nFlatulansAt(3 + dr, 9 + dc);
                                assert (ct <= prevct[1 + dr][1 + dc]);
                                prevct[1 + dr][1 + dc] = ct;
                                if (ct > 0) anyleft = true;
                            }
                    }
                    assert (!anyleft);
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                   
                }
                break;

            case 22: {
                try{
                    int m = 0;
                    for (int k = 0; k < 600; k++) {
                        City c = new City(10, 18);
                        c.addPlayer(3, 9);
                        assert (c.flatulanCount() == 0);
                        c.addFlatulan(3, 10);
                        assert (c.flatulanCount() == 1);
                        c.player().preach();
                        if (c.flatulanCount() == 0) m++;
                    }
                    assert (m >= 360 && m <= 440) : " " + m;
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                   
                }
                break;

            case 23: {
                try{
                    City c = new City(10, 18);
                    c.addPlayer(3, 9);
                    for (int k = 0; k < City.MAXFLATULANS; k++) assert (c.addFlatulan(2, 9));
                    assert (!c.addFlatulan(2, 9));
                    assert (c.flatulanCount() == City.MAXFLATULANS);
                    for (int m = 64; m > 0; m /= 2) {
                        for (int k = 0; k < 100 && c.flatulanCount() >= m; k++)
                            c.preachToFlatulansAroundPlayer();
                        assert (c.flatulanCount() < m) : " " + c.flatulanCount() + " < " + m;
                        for (int k = 0;
                                k < City.MAXFLATULANS && c.flatulanCount() < City.MAXFLATULANS;
                                k++) assert (c.addFlatulan(2, 9));
                        assert (c.flatulanCount() == City.MAXFLATULANS);
                    }
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                    
                }
                break;

            case 24: {
                try{
                    int dr[] = {-1, 0, 1, 0};
                    int dc[] = {0, 1, 0, -1};
                    City c = new City(12, 18);
                    c.addPlayer(1, 1);
                    int r[] = {3, 6, 9};
                    int v[] = {4, 7, 10};
                    for (int k = 0; k < 3; k++) c.addFlatulan(r[k], v[k]);
                    c.moveFlatulans();
                    for (int k = 0; k < 3; k++) {
                        int ct[] = {0, 0};
                        for (int p = 0; p < Flatulan.NUMDIRS; p++) {
                            int m = c.nFlatulansAt(r[k] + dr[p], v[k] + dc[p]);
                            assert (m == 0 || m == 1);
                            ct[m]++;
                        }
                        assert (ct[0] == 3 && ct[1] == 1);
                    }
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                    
                }
                break;

            case 25: {
                try{
                    boolean gassed = false;
                    for (int m = 0; m < 20; m++) {
                        City c = new City(10, 20);
                        c.addPlayer(5, 9);
                        for (int k = 0; k < 11; k++) c.addFlatulan(5, 11);
                        assert (c.player().health() == 12);
                        c.moveFlatulans();
                        int h = c.player().health();
                        if (h < 12) gassed = true;
                        assert (c.nFlatulansAt(5, 10) == 12 - h);
                    }
                    assert (gassed);
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                    
                }
                break;

            case 26: {
                try{
                    boolean gassed = false;
                    for (int m = 0; m < 20; m++) {
                        City c = new City(10, 20);
                        c.addPlayer(5, 9);
                        for (int k = 0; k < 9; k++) c.addFlatulan(5, 10);
                        assert (c.player().health() == 12);
                        c.moveFlatulans();
                        int h = c.player().health();
                        if (h < 12) gassed = true;
                        assert (c.nFlatulansAt(5, 10) == 12 - h);
                    }
                    assert (gassed);
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }
                    
                }
                break;

            case 27: {
                        try{
                            for (int m = 0; m < 20; m++) {
                            City c = new City(1, 20);
                            c.addPlayer(1, 2);
                            for (int k = 0; k < 11; k++) c.addFlatulan(1, 1);
                            assert (c.player().health() == 12);
                            c.moveFlatulans();
                            assert (c.player().health() == 12 - 11);
                            assert (c.nFlatulansAt(1, 1) == 11);
                        }
                        }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                 }

                }
                break;

            case 28: {
                try{
                    int n;
                    for (n = 0; n < 2; n++) {
                        City c = new City(10, 20);
                        c.addPlayer(5, 9);
                        for (int k = 0; k < MAXFLATULANS; k++) c.addFlatulan(5, 11);
                        assert (c.player().health() == 12 && !c.player().isPassedOut());
                        c.moveFlatulans();
                        if (c.player().health() <= 0 && c.player().isPassedOut()) break;
                    }
                    assert (n != 2);
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                    
                }
            }
                break;

            case 29: {
                try{
                    System.setOut(ps);
                    City c = new City(10, 20);
                    c.display();
                    String s = baos.toString();
                    int a = countFlatulans(10 * 20, s);
                    baos = new ByteArrayOutputStream();
                    ps = new PrintStream(baos);
                    System.setOut(ps);
                    c.addPlayer(4, 3);
                    c.addFlatulan(5, 6);
                    c.addFlatulan(5, 7);
                    c.addFlatulan(5, 8);
                    c.display();
                    s = baos.toString();
                    System.setOut(old);
                    assert (countFlatulans(10 * 20, s) == (a + 3)); 
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                    
                }
                
                }
                break;

            case 30: {
                try{
                    City c = new City(10, 20);
                    System.setOut(ps);
                    c.display();
                    String s = baos.toString();
                    int a = countFlatulans(10 * 20, s);
                    baos = new ByteArrayOutputStream();
                    ps = new PrintStream(baos);
                    System.setOut(ps);
                    c.addPlayer(4, 3);
                    c.addFlatulan(5, 6);
                    c.addFlatulan(5, 7);
                    c.addFlatulan(5, 8);
                    c.addFlatulan(5, 6);
                    c.addFlatulan(5, 7);
                    c.addFlatulan(5, 8);
                    c.display();
                    s = baos.toString();
                    System.out.flush();
                    System.setOut(old);
                    assert (countFlatulans(10 * 20, s) == (a + 3))
                            : countFlatulans(10 * 20, s) + " == " + (a + 3);
                }catch (InterruptedException e) {
                    System.err.println("Got interrupted...");
                    System.exit(0);
                    
                }
                   
                }
                break;
        } // switch
        System.out.flush();
        System.setOut(old);
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////
    //  main()
    ///////////////////////////////////////////////////////////////////////////
    public static void main(String argv[]) {
       /*
         System.out.print ("Enter test number (1-30): ");
         int n;
         Scanner keyboard = new Scanner(System.in);
         n = keyboard.nextInt();
         if( testone(n) )
           System.out.println( "Passed!" ) ;
       */

        for (int i = 1; i <= 30; i++) {
            if (testone(i)) System.out.println("Passed!");
        }

        System.out.println("Done");

    }
}


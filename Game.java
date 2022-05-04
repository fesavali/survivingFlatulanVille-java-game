import java.util.Scanner;

/* ******************************************************************/
/*                      GAME Class                                  */
/* ******************************************************************/

class Game {

    // Game constants

    // private final int MAXROWS = 80;               // max number of rows in a city
    // private final int MAXCOLS = 80;               // max number of columns in a city
    private final int MAXFLATULANS = 512; // max number of Flatulans allowed

    private City m_city;

    ////////////////////////////////////////////////////////////
    //   Constructor
    //////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public Game(int rows, int cols, int nFlatulans, int playsHealth) throws InterruptedException {
        if (nFlatulans < 0) {
            System.err.print("***** Cannot create Game with negative number of Flatulans!\n");
            System.exit(1);
        }
        if (nFlatulans > MAXFLATULANS) {
            System.err.print(
                    "***** Trying to create Game with "
                            + nFlatulans
                            + " Flatulans; only "
                            + MAXFLATULANS
                            + " are allowed!\n");
            System.exit(1);
        }
        if (rows == 1 && cols == 1 && nFlatulans > 0) {
            System.err.print("***** Cannot create Game with nowhere to place the Flatulans!\n");
            System.exit(1);
        }

        // Create city
        m_city = new City(rows, cols, MAXFLATULANS); // max number of Flatulans allowed);

        // Add player
        int rPlayer = randInt(1, rows);
        int cPlayer = randInt(1, cols);
        m_city.addPlayer(rPlayer, cPlayer, playsHealth);
        if (City.Verbose) System.out.println("Player " + m_city.m_player + "\n");

        int i = 0;
        // Populate with Flatulans
        while (nFlatulans > 0) {
            int r = randInt(1, rows);
            int c = randInt(1, cols);
            // Don't put a Flatulan where the player is
            if (r == rPlayer && c == cPlayer) continue;
            m_city.addFlatulan(r, c);
            i++;
            nFlatulans--;
        }

        if (City.Verbose) {
            System.out.println("------------------------");
            System.out.println(m_city);
            System.out.println("------------------------");
            for (i = 0; i < nFlatulans; i++)
                System.out.println(
                        "Flatulan at["
                                + m_city.m_flatulans[i].row()
                                + "]["
                                + m_city.m_flatulans[i].col()
                                + "]");
            System.out.println("-------------------------");
        }
    } // game constructor

    public Game(int rows, int cols, int nFlatulans) throws InterruptedException {
        this(rows, cols, nFlatulans, 12); // default health is 12
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public void play() throws InterruptedException {
        Scanner keybaord = new Scanner(System.in);
        m_city.display();
        Player p = m_city.player();
        if (p == null) return;

        while (!p.isPassedOut() && m_city.flatulanCount() > 0) {
            System.out.print("Move (u/d/l/r/h/q): ");
            String action = keybaord.nextLine();
            if (action.isEmpty()) // user enter 'Return' so player preaches
            p.preach();
            else {
                switch (action.charAt(0)) {
                    default: // if bad move, nobody moves
                        System.out.print("\0007"); // '\a' );  // ANSI beep
                        continue;
                    case 'q':
                        return;
                    case 'u':
                    case 'd':
                    case 'l':
                    case 'r':
                        p.move(decodeDirection(action.charAt(0)));
                        break;
                    case 'h':
                        m_city.clearScreen();
                        m_city.m_hist.display();
                        continue;

                }
            }
            m_city.moveFlatulans();
            m_city.display();
        }
        if (p.isPassedOut()) {
            System.out.print("You lose.\n");
        } else {
            System.out.print("You win.\n");
        }
    } // play

    ////////////////////////////////////////////////////////////
    //  Auxiliary function declarations
    //////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    private int decodeDirection(char dir) {
        switch (dir) {
            case 'u':
                return m_city.UP;
            case 'd':
                return m_city.DOWN;
            case 'l':
                return m_city.LEFT;
            case 'r':
                return m_city.RIGHT;
        }
        return -1; // bad argument passed in!
    }

    ////////////////////////////////////////////////////////////
    // Description: Return a uniformly distributed random int from min to max, inclusive
    // Receives:
    // Returns:
    // Requires:
    public static int randInt(int min, int max) {
        if (max < min) {
            int t = max; // swap
            max = min;
            min = t;
        }
        // Generate random number
        int range = max - min;
        int rand = (int) (Math.random() * range) + min;
        return rand;
   }
}

/* ******************************************************************/
/*                      CITY Class                                  */
/* ******************************************************************/

class City {
    // city constants
    public static int MAXFLATULANS; // max number of Flatulans allowed - set in constructor
    public static final int MAXROWS = 80; // max number of rows in a city
    public static final int MAXCOLS = 80; // max number of columns in a city
    

    public final int UP = 0;
    public final int DOWN = 1;
    public final int LEFT = 2;
    public final int RIGHT = 3;

    public static boolean Verbose = false;

    private final int m_rows;
    private final int m_cols;
    public Player m_player;
    public Flatulan m_flatulans[];
    private int m_nFlatulans;
    public  History m_hist;

    ////////////////////////////////////////////////////////////
    // Constructor
    //////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public City(int nRows, int nCols, int maxFlatulans) throws InterruptedException {

        m_rows = nRows;
        m_cols = nCols;
        m_player = null;
        m_nFlatulans = 0;

        MAXFLATULANS = maxFlatulans;
        m_flatulans = new Flatulan[MAXFLATULANS];

        m_hist = new History(nRows, nCols);
        

        if (nRows <= 0 || nCols <= 0 || nRows > MAXROWS || nCols > MAXCOLS) {
            System.err.print(
                    "***** City created with invalid size " + nRows + " by " + nCols + "!\n");
            System.exit(1);
        }
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public City(int nRows, int nCols) throws InterruptedException {
        this(nRows, nCols, 120);
    }

    ////////////////////////////////////////////////////////////
    // Accessors
    //////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public int rows() {
        return m_rows;
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public int cols() {
        return m_cols;
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public Player player() {
        return m_player;
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public boolean isPlayerAt(int r, int c) {
        return m_player != null && m_player.row() == r && m_player.col() == c;
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public int flatulanCount() {
        return m_nFlatulans;
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public int nFlatulansAt(int r, int c) {
        int count = 0;
        for (int k = 0; k < m_nFlatulans; k++) {
            Flatulan fp = m_flatulans[k];
            if (fp.row() == r && fp.col() == c) count++;
        }
        return count;
    }

    ////////////////////////////////////////////////////////////
    // Determine the New Position
    // Description: Figures out new position and checks it for validity
    //              if new position is valid then row and column are updated (array)
    // Receives:    1) An array of size two with first element for row second for column
    //                 (using an array here so we pass by reference and not by value)
    //              2) The proposed direction for the move
    // Returns:     True if new position is valid, false if it is not
    //              (note, array with row and column is update only if new position is valid)
    // Requires:    row/column array has two elements, value of those elements are between
    //              zero and MAXROWS/MAXCOLS, direction parameter is UP,DOWN,RIGHT,LEFT (0-3)
    //
    public boolean determineNewPosition(int at[], int dir) {
        if (at.length < 2) {
            System.err.print(
                    " City: array passed in is too small " + at.length + " should be 2!\n");
            System.exit(1);
        }
        int r = at[0], c = at[1];
        if (r <= 0 || c <= 0 || r > MAXROWS || c > MAXCOLS) {
            System.err.print(
                    " City: indics into array have invalid size " + r + " by " + c + "!\n");
            System.exit(1);
        }
        if (dir < UP || dir > RIGHT) { // dir is equal to 0, 1, 2, 3
            System.err.print(
                    " City: proposed direction is invalid greater than 3 or less than 0 "
                            + dir
                            + "!\n");
            System.exit(1);
        }
        switch (dir) {
            case UP:
                if (r <= 1) return false;
                else r--;
                break;
            case DOWN:
                if (r >= rows()) return false;
                else r++;
                break;
            case LEFT:
                if (c <= 1) return false;
                else c--;
                break;
            case RIGHT:
                if (c >= cols()) return false;
                else c++;
                break;
            default:
                return false;
        }
        // proposed position is valid so update array
        at[0] = r;
        at[1] = c;
        return true;
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public void display() {
        // Position (row,col) in the city coordinate system is represented in
        // the array element grid[row-1][col-1]
        char grid[][] = new char[MAXROWS][MAXCOLS];
        int r, c;

        if (m_player != null) {
            if (m_player.converted() > 0) {
                if (Verbose)
                    System.out.print(
                            " Yea, you converted convert "
                                    + m_player.converted()
                                    + " Flatulan(s) to stop pollute the air \n");
                m_player.resetConverted();
            }
            if (m_player.gassed() > 0) {
                if (Verbose)
                    System.out.print(
                            " Ouch, you have been exposed to noxious gas "
                                    + m_player.gassed()
                                    + " times \n");
                m_player.resetGassed();
            }
        }
        if (Verbose) System.out.println("");

        // Fill the grid with dots
        for (r = 0; r < rows(); r++) {
            for (c = 0; c < cols(); c++) {
                grid[r][c] = '.';
            }
        }

        // Indicate each Flatulan's position
        for (int k = 0; k < m_nFlatulans; k++) {
            // Flatulan fp = m_flatulans[k];
            char gridChar = grid[m_flatulans[k].row() - 1][m_flatulans[k].col() - 1];
            switch (gridChar) {
                case '.':
                    gridChar = 'F';
                    grid[m_flatulans[k].row() - 1][m_flatulans[k].col() - 1] = gridChar;
                    break; // if empty the mark as occupied
                case 'F':
                    gridChar = '2';
                    grid[m_flatulans[k].row() - 1][m_flatulans[k].col() - 1] = gridChar;
                    break; // 'F' if only 1  '2' for two
                case '9':
                    gridChar = '#';
                    grid[m_flatulans[k].row() - 1][m_flatulans[k].col() - 1] = gridChar;
                    break; // more than 9 use '#'
                case '#':
                    grid[m_flatulans[k].row() - 1][m_flatulans[k].col() - 1] = gridChar;
                    break; // more than 9 use '#'
                default:
                    gridChar++;
                    grid[m_flatulans[k].row() - 1][m_flatulans[k].col() - 1] = gridChar;
                    break; // '2' through '8'
            }
        }

        // Indicate player's position
        if (m_player != null) {
            // Set the char to '@', unless there's also a Flatulan there
            // (which should never happen), in which case set it to '*'.
            char gridChar = grid[m_player.row() - 1][m_player.col() - 1];
            if (gridChar == '.') gridChar = '@';
            else gridChar = '*';
            grid[m_player.row() - 1][m_player.col() - 1] = gridChar;
        }

        // Draw the grid
        clearScreen();
        for (r = 0; r < rows(); r++) {
            for (c = 0; c < cols(); c++) System.out.print(grid[r][c] + " ");
            System.out.println("");
        }
        System.out.println("");

        // Write message, Flatulan, and player info
        System.out.print("There are " + m_nFlatulans + " unconverted Flatulans remaining.\n");
        if (m_player == null) System.out.print("There is no player.\n");
        else {
            if (m_player.age() > 0)
                System.out.print("The player has lasted " + m_player.age() + " steps.\n");
            if (m_player.isPassedOut()) System.out.print("The player has passed out.\n");
            else System.out.print("The player's health level is " + m_player.health() + "\n");
        }
    }

    ////////////////////////////////////////////////////////////
    // Mutators
    //////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public boolean addFlatulan(int r, int c) {
        if (!isInBounds(r, c)) return false;

        // Don't add a Flatulan on a spot with a player
        if (m_player != null && m_player.row() == r && m_player.col() == c) return false;

        // Dynamically allocate a new Flatulan and add it to the city
        if (m_nFlatulans == MAXFLATULANS) return false;
        try{
            m_flatulans[m_nFlatulans] = new Flatulan(this, r, c);
        }catch (InterruptedException e) {
            System.err.println("Got interrupted...");
            System.exit(0);            
        }
        

        if (Verbose) System.out.println("In City - " + m_flatulans[m_nFlatulans]);

        m_nFlatulans++;

        return true;
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public boolean addPlayer(int r, int c, int health) {
        if (!isInBounds(r, c)) return false;

        // Don't add a player if one already exists
        if (m_player != null) return false;

        // Don't add a player on a spot with a Flatulan
        if (nFlatulansAt(r, c) > 0) return false;

        // Dynamically allocate a new Player and add it to the city
        m_player = new Player(this, r, c, health);
        if (Verbose) System.out.println("In City  - " + m_player);
        return true;
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public boolean addPlayer(int r, int c) {
        return addPlayer(r, c, 12); // default health is 12
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public void preachToFlatulansAroundPlayer() {
        // Preach to Flatulans orthogonally or diagonally adjacent to player.  If a
        // Flatulan is converted, then since the order of the Flatulans in the array
        // doesn't matter, we can replace the converted Flatulan we remove from the
        // game by the last one in the array.
        if (m_player == null) return;

        for (int k = 0; k < m_nFlatulans; ) {
            Flatulan fp = m_flatulans[k];
            int rowdiff = fp.row() - m_player.row();
            int coldiff = fp.col() - m_player.col();

            // if orthogonally or diagonally adjacent and conversion succeeds
            if (rowdiff >= -1
                    && rowdiff <= 1
                    && coldiff >= -1
                    && coldiff <= 1
                    && fp.possiblyGetConverted()) {
                m_flatulans[k] = m_flatulans[m_nFlatulans - 1];
                m_nFlatulans--;
                m_player.incConverted();
            } else k++;
        }
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public void moveFlatulans() {
        for (int k = 0; k < m_nFlatulans; k++) {
            Flatulan fp = m_flatulans[k];
            fp.move();
            if (m_player == null) continue;
            int rowdiff = fp.row() - m_player.row();
            int coldiff = fp.col() - m_player.col();
            // if orthogonally adjacent
            if ((rowdiff == 0 && (coldiff == 1 || coldiff == -1))
                    || (coldiff == 0 && (rowdiff == 1 || rowdiff == -1))) m_player.getGassed();
        }
    }

    ////////////////////////////////////////////////////////////
    // Helper functions
    //////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    private boolean isInBounds(int r, int c) {
        return (r >= 1 && r <= m_rows && c >= 1 && c <= m_cols);
    }

    ///////////////////////////////////////////////////////////////////////////
    //  clearScreen implementation
    ///////////////////////////////////////////////////////////////////////////
    // DO NOT MODIFY ANY OF THE clearscreen CODE
    //////////////////////////////////////////////////////////
    // Description:  clear the console - using either Windows command ANSI escape sequence
    // Receives:	nothing
    // Returns:		nothing but clears the screen
    // Requires:	the OS to be Windows Linux or Darwin
    public final void clearScreen() {
        try {
            final String os =
                    System.getProperty("os.name"); // get os's Name (Window, Mac OS, Linux, ... )
            // System.err.println(os);
            if (os.contains("Windows")) { // if has "Windows assumed to be Windows OS "
                // System.err.print( "clearing screen Win" );
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else { // assumed to be UNIX like terminal (ANSI aware)
                // System.err.print( "clearing screen a Unix terminal " );
                String ESC_SEQ = "\033["; // ANSI Terminal esc seq:  ESC [
                System.out.print(
                        ESC_SEQ + "2J" + ESC_SEQ
                                + "H"); // clear screen on Terminal Darwin and Linux
                System.out.flush(); // wrte output buffer now
            }
        } catch (final Exception e) { // getProperty() could throwexecption
            e.printStackTrace(); // dump stack
        }
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public String toString() {
        int i;
        String str = new String(" City with FlatuiansL:");
        for (i = 0; i < m_nFlatulans; i++) str += " " + m_flatulans[i].At();
        str += "; PLAYER " + m_player;
        return str;
    }
} // City Class


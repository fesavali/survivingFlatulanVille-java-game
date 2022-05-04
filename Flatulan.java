/* ***************************************************************************
/*                      FLATULAN Class                                      */
/* **************************************************************************/

class Flatulan extends Citizen{


    ////////////////////////////////////////////////////////////
    // constructor
    ////////////////////////////////////////////////////////////
    public Flatulan(City city, int rows, int cols) throws InterruptedException {
        m_city = city;
        setRow(rows); // m_row = rows;
        setCol(cols); // m_col = cols;
        if (city == null) {
            System.out.print("***** A Flatulan must be created in some City! \n");
            System.exit(1);
        }
    }

    ////////////////////////////////////////////////////////////
    // Accessors
    ///////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////
    // Description: This method returns the Row vlaue of the called instance
    // Receives:    none
    // Returns:     returns instance variable m_row.
    // Requires:    n/a

    //fun row was here

    ////////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    
    //fun col was here

    ////////////////////////////////////////////////////////////
    // Mutators
    ///////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public void setRow(int r) {
        if (r < 1 || r > m_city.rows()) {
            System.out.print("***** Flatulan created with invalid row coordinate (" + r + ")!\n");
            System.exit(1);
        }
        m_row = r;
    }

    //////////////////////////////////////////////////////////
    // Description: This method sets the Column vlaue of the called instance
    //              exit if Column is out of range (invalid)
    // Receives:    int - The new column value
    // Returns:     int This returns instance variable m_row.
    // Requires:    n/a
    public void setCol(int c) {
        if (c < 1 || c > m_city.cols()) {
            System.out.print(
                    "***** Flatulan created with invalid column coordinate ("
                            + c
                            + " <> "
                            + m_city.rows()
                            + ")!\n");
            System.exit(1);
        }
        m_col = c;
    }

    // Description:
    // Receives:
    // Returns:
    // Requires:
    ///////////////////////////////////////////////////////////////
    public void move() {
        // Attempt to move in a random direction; if it can't move, don't move.
        // If the player is there, don't move.
        int dir = Game.randInt(0, NUMDIRS); // dir (0-3) is now UP, DOWN, LEFT, or RIGHT
        int r = m_row;
        int c = m_col;
        int at[] = new int[2];
        at[0] = m_row;
        at[1] = m_col;
        if (m_city.determineNewPosition(at, dir)) {
            r = at[0];
            c = at[1];
        } // up date locaton if new position
        if (!m_city.isPlayerAt(r, c)) {
            m_row = r;
            m_col = c;
        }
    }

    // Description:
    // Receives:
    // Returns:
    // Requires:
    ////////////////////////////////////////////////////////////
    public boolean possiblyGetConverted() { // return true if converted
        // Be converted with 2/3 probability
        boolean ret = Game.randInt(0, 3) < 2;
        return ret;
    }

    // Description:
    // Receives:
    // Returns:
    // Requires:
    ///////////////////////////////////////////////////////////////
    public String At() {
        String str = new String("[" + m_row + "][" + m_col + "] ");
        // System.out.err( "\tFlatulan currently at ["+m_row+"]["+m_col+"] \n");
        return str;
    }

    // Description:
    // Receives:
    // Returns:
    // Requires:
    ///////////////////////////////////////////////////////////////
    public String toString() {
        String str = new String("Flatulan currently at [" + m_row + "][" + m_col + "] ");
        // System.out.err( "\tFlatulan currently at ["+m_row+"]["+m_col+"] \n");
        return str;
    }
} // class Flatulan

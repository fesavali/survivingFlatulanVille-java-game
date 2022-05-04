/* ******************************************************************/
/*                      PLAYER Class                                */
/* ******************************************************************/

class Player extends Citizen {


    ////////////////////////////////////////////////////////////
    // Constructor
    ///////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public Player(City city, int rows, int cols) throws InterruptedException {
        this(city, rows, cols, 12);
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public Player(City city, int rows, int cols, int InitialPlayHealth) {
        m_city = city;
        m_row = rows;
        m_col = cols;

        setHealth(InitialPlayHealth);

        m_age = 0;

        if (city == null) {
            System.out.print("***** The player must be created in some City!\n");
            System.exit(1);
        }
        if (rows < 1 || rows > city.rows() || cols < 1 || cols > city.cols()) {
            System.out.print(
                    "***** Flatulan created with invalid coordinates ("
                            + rows
                            + ","
                            + cols
                            + ")!\n");
            System.exit(1);
        }
    }

    ////////////////////////////////////////////////////////////
    // Accessors
    ///////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
   
 //fun row was here

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
   
   

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public int age() {
        return m_age;
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public int health() {
        return m_health;
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public void setHealth(int health) {
        if (health <= 0 || health > MAX_PLAYER_HEALTH) {
            System.out.print("***** Player created with invalid health (" + health + ")!\n");
            System.exit(1);
        }
        m_health = health;
    }

    // Description:
    // Receives:
    // Returns:
    // Requires:
    ///////////////////////////////////////////////////////////////
    public void resetConverted() {
        m_converted = 0;
    }

    // Description:
    // Receives:
    // Returns:
    // Requires:
    ///////////////////////////////////////////////////////////////
    public int converted() {
        return m_converted;
    }

    // Description:
    // Receives:
    // Returns:
    // Requires:
    ///////////////////////////////////////////////////////////////
    public void incConverted() {
        m_converted++;
    }

    // Description:
    // Receives:
    // Returns:
    // Requires:
    ///////////////////////////////////////////////////////////////
    public void resetGassed() {
        m_gassed = 0;
    }

    // Description:
    // Receives:
    // Returns:
    // Requires:
    ///////////////////////////////////////////////////////////////
    public int gassed() {
        return m_gassed;
    }

    // Description:
    // Receives:
    // Returns:
    // Requires:
    ///////////////////////////////////////////////////////////////
    public boolean isPassedOut() {
        return m_health <= 0;
    }

    //////////////////////////////////////////////////////////
    // Mutators
    //////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public void preach() {
        m_age++;
        m_city.preachToFlatulansAroundPlayer();
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public void move(int dir) {
        // dir (0-3) is now UP, DOWN, LEFT, or RIGHT
        int r = m_row;
        int c = m_col;
        int at[] = new int[2];
        at[0] = m_row;
        at[1] = m_col;
        if (m_city.determineNewPosition(at, dir)) {
            r = at[0];
            c = at[1];
            // If there are no Flatulans at the new position
            if (m_city.nFlatulansAt(r, c) == 0) {
                m_row = r;
                m_col = c;
            }
        }
    }

    //////////////////////////////////////////////////////////
    // Description:
    // Receives:
    // Returns:
    // Requires:
    public void getGassed() {
        m_health--;
        m_gassed++;
    }

    public String toString() {
        String str =
                new String(
                        "Player currently at ["
                                + m_row
                                + "]["
                                + m_col
                                + "], age:"
                                + m_age
                                + " health:"
                                + m_health
                                + " converted:"
                                + m_converted
                                + " gassed:"
                                + m_gassed);
        return str;
    }
} // Player Class


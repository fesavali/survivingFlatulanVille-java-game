public abstract class Citizen {
    // city constants
    public static final int NUMDIRS = 4; // number of directions play can move
    public static final int MAXROWS = 80; // max number of rows in a city
    public static final int MAXCOLS = 80; // max number of columns in a city

    public static int MAXFLATULANS;
    public final static int LEFT = 2;

    public static boolean Verbose = false;

   
        
    // player constants
    protected final int MAX_PLAYER_HEALTH = 512;

    protected City m_city;
    protected int m_row;
    protected int m_col;
    protected int m_health;
    protected int m_age;
    protected int m_converted;
    protected int m_gassed;

    public Player m_player;
    public Flatulan m_flatulans[];
    protected int m_nFlatulans;

    public int row() {
        return m_row;
    }
    public int col() {
        return m_col;
    }
}

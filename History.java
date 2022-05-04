public class History extends Citizen {
  
    private final int m_rows;
    private final int m_cols;

    
    public History(int nRows, int nCols) {
        m_rows = nRows;
        m_cols = nCols;
    }
    public Boolean record(int r, int c) {
        return null;
    }

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

    public void display() throws InterruptedException {
        City cls = new City(m_cols, m_cols);
        cls.display();
    }
    public static void h(int i, int j) {
    }         
 
}

/* ***************************************************************************
 *
 * Project:          Project 01
 * Project Name:     Flatulan
 * Main Class File:  survivingFlatulanVille
 * File:             survivingFlatulanVille.java
 * Semester:         CSC300 Spring 2021
 *
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
 *
 * TODO
 * rewrite this code some it uses good programming practices
 * for example (not a complete list - see writeup for full list of tasks):
 *    -->separate into multiple fiels (by class),
 *    documentation for all files / classes and methods
 *           (see examples such as determineNewPosition)
 *    add exceptions: for all range value checks
 *    add failed convertion history (see writeup)
 *
 * ***************************************************************************/



/* ******************************************************************/
/*                                                                  */
/*          survivingFlatulanVille the driver Class                            */
/*                                                                  */
/* ******************************************************************/
class survivingFlatulanVille extends Citizen {
    
    ///////////////////////////////////////////////////////////////////////////
    //  main()
    ///////////////////////////////////////////////////////////////////////////
    public static void main(String argv[]) throws InterruptedException {

        String verbose = System.getProperty("Verbose");
        if (verbose != null) {
            System.err.println(": " + verbose);
            City.Verbose = true;
        }
        // Create a game
        // Use this instead to create a mini-game:   Game(7, 8, 25);
        // (int rows, int cols, int nFlatulans)

        try {
            Game g = new Game(7, 8, 25);
               // Play the game
            g.play();
         } catch (InterruptedException e) {
            System.err.println("Got interrupted...");
            System.exit(0);
         }

        //test 1
        // History.h(2, 2);
	    // History h = new History(2, 2);
        // h.record(1, 1);
	    // h.display();

        // test 2
        // City m_city = new City(5, 5, 32);
	    // Flatulan f = new Flatulan(null, 1, 1);

        // test 3
        // Player p = new Player(null, 1, 1);

        // test 4
        // City c = new City(10, 18);
	    // c.addPlayer(2, 2);

        // test 5
        // City c = new City(10, 20);
	    // Player p = new Player(c, 2, 3);

        //  test 6
        //  City c = new City (10, 20);
	    // Player p = new Player (c, 2, 3);

        // test 7
        // City c = new City(10, 20);
	    // Player p = new Player (c, 2, 3);

        //  test 8
        // City ct = new City (3, 4);
	    // ct.addPlayer(2, 3);
	    // for (;;)
	    // {
		// ct.preachToFlatulansAroundPlayer();
		// if (ct.nFlatulansAt(1, 4) != 0)
		//     break;
		// ct.addFlatulan(1, 4);
	    // }
	    // ct.player().move(LEFT);
	    // for (int r = 1; r <= 3; r++)
	    // {
		// for (int c = 1; c <= 3; c++)
		// {
		//     if (r == 2  &&  c == 2)
		// 	continue;
		//     for (int k = 0; k < MAXFLATULANS/8; k++)
		// 	ct.addFlatulan(r, c);
		// }
	    // }
	    // ct.preachToFlatulansAroundPlayer();
	    // ct.preachToFlatulansAroundPlayer();     
    }

}

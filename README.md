# survivingFlatulanVille-java-game
At each turn the player may take one of these actions: 
1. Move one step up, down, left, or right to an empty position (i.e., one not occupied by a Flatulan). If the 
player attempts to move into the wall of the city (e.g., down, when on the bottom row) or to a position 
occupied by a Flatulan, the player does not move. 
2. Preach to the Flatulans adjacent to the player orthogonally or diagonally (i.e., to any Flatulans next to 
the player in the eight directions). For each of those Flatulans, there is a 2/3 probability that the player 
will convert that Flatulan to no longer pollute the air. A Flatulan who has been converted should be 
removed from the game, since they pose no further threat to the player and are thus irrelevant. 
The game allows the user to select the player's action by typing u/d/l/r for moving (up, down, left or right) or 
just hitting enter for preaching. The user may also type q to prematurely quit the game. 
When it's the Flatulans' turn, each Flatulan picks a random direction (up, down, left, or right) with equal 
probability. The Flatulan moves one step in that direction if it can; however, if doing so would cause the 
Flatulan to move into a wall of the city (e.g., down, when on the bottom row) or to the position occupied by the 
player, it does not move. More than one Flatulan may occupy the same position; in that case, instead of F, the 
display will show a digit character indicating the number of Flatulans at that position (where 9 indicates 9 or 
more). After each Flatulan attempts to move (even if it doesn't actually move), if it is now orthogonally adjacent 
to the player (i.e., next to the player directly above, below, to the left, or to the right, but not diagonally), the 
player suffers one blast of gas from that Flatulan. If this is the twelfth gas blast the player has suffered during 
the game, the player passes out and the game is over. 

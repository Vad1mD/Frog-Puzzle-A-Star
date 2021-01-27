# Frog-Puzzle-A-Star
A* algorithm for the leaping frog puzzle

The leaping frog puzzle https://sonnyradio.com/leapfrog.htm

Exercise in our AI course required solution for the leaping frog puzzle using the A* algorithm 
implemented in java.

The format is Black and white pieces separated by zeros.
Initial state is where there is at least 1 black piece to the left of all white, and the opposite like so:

BBB0WWW | B0BBWWW | BW0WWBB

The given heuristic is the number of W's that yet passed to the left of all B's


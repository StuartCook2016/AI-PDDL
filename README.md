# F29AI Coursework
#### Christian Gregg (cg23) & Stuart Cook (sc89)

## Part I
### Packages (`/src`)

`aiRobot`, `aiRobotEditor`, `aiRobotSearch` contains all code related to the GUI.

`cg23` contains all code related to the search.

### How to
Run (From project root) `java -cp bin/ aiRobot.AISearch` to start the program.

In Editor mode:
- use `F1` to save current map to file.
- scroll to change block type (wall, trap)
- left click to place block
- right click to remove block

In Search mode:
- enter file to load as map
- place 1 or 2 robot(s) and their goal(s)
- select search type (A\* Manhattan, A\* Euclidean, UCS (A\* no heuristic))
- run the search

The blocks on the grid should begin to move, with 1.5 seconds between each step.

You should see relevant output on the terminal regarding the search. Output should be in the form of:

```
Run Search type: <manhattan/euclidean/none>
Running <Cooperative/single> Algorithm
<Number of iterations>
<path of robot 2 (r2) (if cooperative algorithm)>
<path of robot 1 (r1)>

=== STEP n ===
Move Robot 1 to (x,y)
Move Robot 2 to (x,y)
=== STEP n+1 ===
Move ...
...
...
Animation Done.
```

### Heuristic Functions

The GUI gives you the option of running three different variations of the A* Algorithm. These variations simply implement a different heuristic functions.

The different heuristic functions available are Manhattan Distance, Euclidean Distance, and none. The none shows up in the GUI as 'Uniform Cost Search', this is because A* Search without a heuristic (or a heuristic of 0.0 all the time) is equivalent to Uniform Cost Search.

## Part II

All PDDL files are held in `/PDDL`

README
______________________

This is a java program that reads in the bingo.txt file and randomly selects from them to fill in a bingo sheet.

You can save an image called "Bingo.png" by pressing ctrl+s while the window is up.

The format for the bingo.txt is that line one is an integer that says how many lines are below it (incorrect number causes the program to not launch).

Every line below the number is taken as their own element.

E.g.
--------
2
Line1
Line 2
--------
Would be a valid bingo.txt file

When defining the number of rows and columns, take care that there are enough unique lines in the bingo.txt to fill the sheet, or the program will not launch. For example, setting the rows and columns to 5x5 and having less than 25 lines would not work.

If there are more elements than space on the sheet, elements are chosen at random to fill the sheet.

For example if bingo.txt is:
-----
5
line1
line 2
LiNe3
line4
LineFive
-----

A 2x2 could be
---------------
 line4  | line1|
----------------
lineFive|line 2|
----------------

Where liNe3 is not included, but the next time the program is run liNe3 might be included.

Enjoy!
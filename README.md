# perfect-gaussian

Inspired by Ed Pegg Jr's [Neglected Gaussian Integers](http://www.mathpuzzle.com/Gaussians.html) page, perfect-gaussian is a Java math library with goals of discovering:
* a perfect Gaussian integer
* amicable Gaussian integers
* a Gaussian aliquot cycle

### Current Status

Factoring Gaussian Integers using the brute force approach is incredibly slow, so a faster algorithm would be a massive improvement. For example, when factoring integers an improvement would be to only test numbers up to and including the square root of the integer. If there is a similar concept available to find the positive divisors of a Gaussian Integer, that would be incredibly helpful.

###### The brute force approach:
    For Gaussian Integer (A + Bi) the code is testing every Gaussian integer a + bi for 
    0 <= a <= max(A, B)
    0 <= b <= max(A, B)

For Current Progress see the [Wiki home page](https://github.com/eli-mcgowan/perfect-gaussian/wiki).

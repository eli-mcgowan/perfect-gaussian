# perfect-gaussian

Inspired by Ed Pegg Jr's [Neglected Gaussian Integers](http://www.mathpuzzle.com/Gaussians.html) page, perfect-gaussian is a Java math library with goals of discovering:
* a perfect Gaussian integer
* amicable Gaussian integers
* a Gaussian aliquot cycle

### Current Status

##### The square root approach:
A new Factorer that tests a much smaller subset of factors has been created. It is fast, but needs to be proven to be correct.

    For Gaussian Integer (A + Bi) the code is testing every Gaussian integer a + bi for 
    0 <= a <= sqrt(A + B)
    0 <= b <= sqrt(A + B)
    
Originally, I tried "0 <= a <= (sqrt(max(A, B)) + 1)" and "0 <= b <= (sqrt(max(A, B)) + 1)" but this doesn't work for 528 + 519i, where (sqrt(max(A, B)) + 1)=23.9. This would miss 3 + 24i and 19 + 24i.


###### The brute force approach:

Factoring Gaussian Integers using the brute force approach is incredibly slow, so a faster algorithm would be a massive improvement. For example, when factoring integers an improvement would be to only test numbers up to and including the square root of the integer. If there is a similar concept available to find the positive divisors of a Gaussian Integer, that would be incredibly helpful.

    For Gaussian Integer (A + Bi) the code is testing every Gaussian integer a + bi for 
    0 <= a <= max(A, B)
    0 <= b <= max(A, B)

For Current Progress see the [Wiki home page](https://github.com/eli-mcgowan/perfect-gaussian/wiki).

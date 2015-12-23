# perfect-gaussian

Inspired by Ed Pegg Jr's [Neglected Gaussian Integers](http://www.mathpuzzle.com/Gaussians.html) page, perfect-gaussian is a Java math library with goals of discovering:
* a perfect Gaussian integer
* amicable Gaussian integers
* a Gaussian aliquot cycle

Some free Amazon Servers have been set up (t2.micro, Windows Server 2012 R2) and are running through various set of the numbers. Assuming that the code is correct, the current progress is in the table below. (a range of 800 to 899 means every number a + bi has been tested where a is in the range {800 ... 899} and b is in the range {0 ... 899}; and every number a + bi has been tested where a is in the range {0 ... 899} and b is in the range {800 ... 899}). When the free 750 monthly hours for Windows servers has been used, Linux servers will be used.

Low Range | High Range | Completed | Time | Instance
----------|------------|-----------|------|---------
2 | 699 | Yes | 14h 14m 38s | 1 - PerfectGaussian
700 | 799 | NO | | 
800 | 899 | Yes | 20h 23m 44s | 1 - PerfectGaussian
900 | 999 | Yes | 21h 49m 35s | 2 - PerfectGaussian
1000 | 1099 | Yes | 28h 54m 40s | 3 - PerfectGaussian
1100 | 1199 | Yes | 32h 49m 39s | 4 - PerfectGaussian
1200 | 1299 | In Progress (1279) |  41h 42m 30s | 5 - PerfectGaussian
1300 | 1399 | In Progress (1329) | 19h 11m 6s | 1 - PerfectGaussian
1400 | 1499 | In Progress (1423) | 19h 35m 36s  | 2 - PerfectGaussian
1500 | 1599 | In Progress (1519) | 14h 58m 6s  | 6 - PerfectGaussian
1600 | 1699 | In Progress (1613) | 5h 33m 35s  | 3 - PerfectGaussian
1700 | 1799 | In Progress (1705) | 5h 26m 39s  | 4 - PerfectGaussian

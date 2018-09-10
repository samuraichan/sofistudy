package com.sofi.java.interview.Arrays;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayRotationTest {

    /*
        Given the array 3 x 5 array (foo):
        [1]  [2]  [3]  [4]  [5]
        [6]  [7]  [8]  [9]  [10]
        [11] [12] [13] [14] [15]

        rotate it 45 degrees, so it should yield the following 5 x 3 array (bar):
        [11]  [6]   [1]
        [12]  [7]   [2]
        [13]  [8]   [3]
        [14]  [9]   [4]
        [15]  [10]  [5]

        solution:
        1. bar [0][0] = foo [2][0]
        2. bar [0][1] = foo [1][0]
        3. bar [0][2] = foo [0][0]

        4. bar [1][0] = foo [2][1]

        The trick is the flip the foo[row][col] to bar[col][row]; writing out the before and after helped me 'see' what the values
        should be

     */

    @Test
    public void testMe() {
        int[][] foo = new int[][]{
            { 1, 2, 3, 4, 5 },
            { 6, 7, 8, 9, 10},
            { 11, 12, 13, 14, 15}
        };

        // rotate 45 degrees
        foo = rotate(foo);
        assertThat(foo.length).isEqualTo(5); // 5 is the number of rows
        assertThat(foo[0].length).isEqualTo(3); // 3 is the number of rows

        assertThat(foo[0][0]).isEqualTo(11);
        assertThat(foo[4][2]).isEqualTo(5);

        // rotate 45 degrees again   (total of 90 degrees from original)
        foo = rotate(foo);
        assertThat(foo.length).isEqualTo(3); // 5 is the number of rows
        assertThat(foo[0].length).isEqualTo(5); // 3 is the number of rows
        assertThat(foo[0][0]).isEqualTo(15);
        assertThat(foo[2][4]).isEqualTo(1);
    }

    private int[][] rotate(int[][] foo) {
        int row = foo.length;
        int col = foo[0].length;

        int [][] bar = new int [col][row];

        for (int new_row = 0; new_row < col; new_row++) {
            int count = row - 1;  // this offset is the trick

            for (int new_col = 0; new_col < row; new_col++) {
                //System.out.println(String.format("Bar[%s][%s] = Foo[%s][%s]", new_row, new_col, count-new_col, new_row));
                bar[new_row][new_col] = foo[count-new_col][new_row];
            }
        }

        return bar;
    }
}

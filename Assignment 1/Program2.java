class Matrix {
    int rows, cols;
    int[][] mat;

    Matrix(int r, int c) {
        rows = r;
        cols = c;
        mat = new int[r][c];
    }

    void setValues(int[][] values) {
        mat = values;
    }

    void display() {
        for (int[] row : mat) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }

    Matrix transpose() {
        Matrix t = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                t.mat[j][i] = mat[i][j];
        return t;
    }

    Matrix multiply(Matrix m) {
        Matrix result = new Matrix(rows, m.cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < m.cols; j++)
                for (int k = 0; k < cols; k++)
                    result.mat[i][j] += mat[i][k] * m.mat[k][j];
        return result;
    }
}

class Main2 {
    public static void main(String[] args) {
        Matrix m1 = new Matrix(2, 2);
        Matrix m2 = new Matrix(2, 2);

        m1.setValues(new int[][]{{1, 2}, {3, 4}});
        m2.setValues(new int[][]{{5, 6}, {7, 8}});

        System.out.println("Transpose:");
        m1.transpose().display();

        System.out.println("Multiplication:");
        m1.multiply(m2).display();
    }
}
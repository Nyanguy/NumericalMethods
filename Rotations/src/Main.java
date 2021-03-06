public class Main {

    public static void main(String[] args) {
        System.out.println("This program will solve matricies with rotations method.");

        double[][] matrix=new double[][] {
                new double[]{900,3,2,1,5,-3,5,9,7,3,        5},
                new double[]{-3,122,1,5,7,4,-2,3,12,-2,     -3},
                new double[]{-6,4,840,10,11,8,7,7,10,7,     -12},

                new double[]{-4,3,15,400,9,10,4,5,17,8,     15},
                new double[]{-3,5,-8,3,-540,5,4,7,8,-9,     7},
                new double[]{-2,4,16,7,8,101,2,8,7,5,       8},

                new double[]{2,32,-4,-3,2,-8,505,16,7,9,    19},
                new double[]{-3,-5,4,1,3,5,2,302,5,15,       3},
                new double[]{-5,8,9,-8,3,4,5,6,301,3,        1},

                new double[]{-3,2,1,14,9,-2,14,7,18,503,    52},

        };
        print(matrix,false);
        rotations(matrix);
    }


    private static void print(double[][] m, boolean solution) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if(j==m.length)
                    System.out.print("| ");
                if(solution) {
                    System.out.print("x" + (i+1) + ": ");
                    System.out.print(m[i][j] + " ");
                }
                else
                    System.out.print(m[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    /**
     * @param m matrix of LSE
     * @return if matrix can be solved
     */
    private static boolean solvable(double[][] m) {
        boolean state=false;
        for(int i=0; i<m.length-1; i++)
            if(m[i][i]!=0.0)
                state=true;
        return state;
    }

    private static double[][] mTranspose(double[][] m) {
        double[][] m_transposed=new double[m[0].length][m.length];
        for(int i=0; i<m.length; i++)
            for(int j=0; j<m[0].length; j++)
                m_transposed[j][i]=m[i][j];
        return m_transposed;
    }

    /**
     * Solve matrix with rotations method
     * @param matrix matrix of LSE
     */
    private static void rotations(double[][] matrix) {

        double[][] sol=new double[matrix.length][1];
        double[] temp_first=new double[matrix[0].length];
        double[] temp_second=new double[matrix[0].length];

        if(check(matrix)) {

            for (int i = 0; i < matrix.length - 1; i++) {
                for (int j = i + 1; j < matrix.length; j++) {
                    double divisor = Math.sqrt(Math.pow(matrix[i][i], 2) + Math.pow(matrix[j][i], 2));
                    double C = matrix[i][i] / divisor;
                    double S = matrix[j][i] / divisor;

                    for (int el = 0; el < matrix[i].length; el++) {
                        temp_first[el] = matrix[i][el] * C + matrix[j][el] * S;
                        temp_second[el] = matrix[i][el] * (-S) + matrix[j][el] * C;
                    }

                    for (int el = 0; el < matrix[i].length; el++) {
                        matrix[i][el] = temp_first[el];
                        matrix[j][el] = temp_second[el];
                    }
                }
            }

            double det = 1;

            for (int i = 0; i < matrix.length; i++)
                det *= matrix[i][i];

            System.out.println("DET = " + det);
        }

        //This peace will solve matrix with a simple backtrace.
        /*print(matrix,false);

        if(!solvable(matrix)) {
            System.out.print("I don't think we can really solve this system. Too many solutions.");
            return;
        }

        for(int i=0; i<matrix.length; i++)
            sol[i][0]=0.0;


        for(int i=matrix.length-1; i>-1; i--) {
            double previous=0;
            for (int th = i; th < matrix[i].length - 1; th++)
                previous+=matrix[i][th] * sol[th][0];
            sol[i][0] = (matrix[i][matrix[i].length - 1] - previous) / matrix[i][i];
        }
        System.out.print("END:\n");
        print(sol,true);
    }*/
    }

    /**
     * Checks if method can be applied to a matrix
     * @param matrix matrix of LSE
     * @return if it can be solved
     */
    private static boolean check(double[][] matrix) {
        double sum=0.0;
        for(int i=0; i<matrix.length; i++) {
            sum=0.0;
            for (int j = 0; j < matrix.length; j++)
                if (i != j)
                    sum+= matrix[i][j];
            if (Math.abs(matrix[i][i])-Math.abs(sum)<0){
                System.out.println(Math.abs(matrix[i][i]));
                return false;
            }
        }
        return true;
    }
}
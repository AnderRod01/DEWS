package beans;

import java.util.ArrayList;

public final class AlmacenMatrices {
    
    private final static ArrayList<int[][]> matrices = new ArrayList<>();
    
    /**
     * Add a matrix to the static list
     * @param matrix The matrix to add
     */
    public static void aniadirMatriz(int[][] matrix){
        matrices.add(matrix);
    }
    
    /**
     * Get the static list of matrix
     * @return The list
     */
    public static ArrayList<int[][]> getMatrices(){
        return matrices;
    }
    
}

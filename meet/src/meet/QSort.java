package meet;

public class QSort {
	private int array[][];
    private int length;
    //input array must be a matrix with 4 columns
    public void sort(int[][] inputArr) {
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        this.length = inputArr.length;
        quickSort(0, length-1);
    }
 
    private void quickSort(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot[]=new int[4];
        pivot[0]=array[lowerIndex+(higherIndex-lowerIndex)/2][0];
        pivot[1]=array[lowerIndex+(higherIndex-lowerIndex)/2][1];
        pivot[2]=array[lowerIndex+(higherIndex-lowerIndex)/2][2];
        pivot[3]=array[lowerIndex+(higherIndex-lowerIndex)/2][3];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i][1] < pivot[1]||(array[i][1]==pivot[1]&&array[i][0]<pivot[0])) {
                i++;
            }
            while (array[j][1] > pivot[1]||(array[j][1]==pivot[1]&&array[j][0]>pivot[0])) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }
 
    private void exchangeNumbers(int i, int j) {
        int[] temp = new int[4];
        temp[0]=array[i][0];temp[1]=array[i][1];temp[2]=array[i][2];temp[3]=array[i][3];
        array[i][0] = array[j][0];array[i][1] = array[j][1];array[i][2] = array[j][2];array[i][3] = array[j][3];
        array[j][0] = temp[0];array[j][1] = temp[1];array[j][2] = temp[2];array[j][3] = temp[3];
    }
}

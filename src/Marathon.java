import java.util.Arrays;

class Marathon {
    public static void main(String args[]) {
        int[] startPos = new int[] {1,2,4,3,5};
        int[] endPos = new int[] {1,2,3,4,5};
        int runners = startPos.length;
        endPos = substitute(runners, startPos, endPos);
        System.out.println(Arrays.toString(endPos));
    }

    public static int[] substitute(int runners, int[] startPos, int[] endPos) {
        int[] newEndPos = new int[runners];
        for(int i = 0 ; i < runners ; i++) {
            int num = startPos[i];
            for(int j = 0; j < runners; j++) {
                if(endPos[j] == num) {
                    newEndPos[j] = i;
                    break;
                }
            }
        }
        return newEndPos;
    }

    public static int recCalcInversions(int[] permutation, int lo, int hi) {
        int inversions = 0;
        if(lo >= hi) {
            return 0;
        }
        int mid = (lo + hi) /2;
        inversions += recCalcInversions(permutation, lo, mid);
        inversions +=  recCalcInversions(permutation, mid + 1, hi);
        int j = 0;
        int k = 0;

        while(j + k < hi - lo) {
            if()
        }

    }
}

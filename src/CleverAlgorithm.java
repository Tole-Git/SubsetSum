import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CleverAlgorithm {
    private static ArrayList<Long> cleverSolution = new ArrayList<>();

    public static boolean cleverAlgorithm(long[] a, long target) {
        cleverSolution.clear();
        int n = a.length;
        int lSize = 1 << (n / 2);
        int rSize = 1 << (n - n / 2);
        long[] lSums = new long[lSize];
        long[] rSums = new long[rSize];

        for (int i = 0; i < lSize; ++i) {
            for (int j = 0; j < n / 2; ++j) {
                if (a[j] == target) {
                    cleverSolution.add(a[j]);
                    return true;
                } else if ((i & (1 << j)) > 0 && a[j] < target) {
                    long temp = lSums[i];
                    lSums[i] += a[j];
                    if (lSums[i] == target) {
                        cleverSolution.add(temp);
                        cleverSolution.add(a[j + n / 2]);
                        return true;
                    }
                }
            }
        }

        for (int i = 0; i < rSize; ++i) {
            for (int j = 0; j < n - n / 2; ++j) {
                if (a[j + n / 2] == target) {
                    cleverSolution.add(a[j + n / 2]);
                    return true;
                } else if ((i & (1 << j)) > 0 && a[j + n / 2] < target) {
                    long temp = rSums[i];
                    rSums[i] += a[j + n / 2];
                    if (rSums[i] == target) {
                        cleverSolution.add(temp);
                        cleverSolution.add(a[j + n / 2]);
                        return true;
                    }
                }
            }
        }

        Arrays.sort(lSums);
        Arrays.sort(rSums);

        int left = 0;
        int right = rSize - 1;

        while (left < lSize && right >= 0) {
            if (lSums[left] + rSums[right] <= target) {
                if (lSums[left] + rSums[right] == target) {
                    cleverSolution.add(lSums[left]);
                    cleverSolution.add(rSums[right]);
                    return true;
                }
                ++left;
            } else {
                --right;
            }
        }
        return false;
    }

    public static void driver(int n, int r, boolean v) {
        long s = 0;
        Random random = new Random();
        long[] a = new long[n];

        for (int i = 0; i < a.length; i++){
            long longR = random.nextInt((r)) + 1;
            a[i] = longR;
        }

        if(v) {
            int nElements = n;
            ArrayList<Integer> visitedPositions = new ArrayList<>();
            System.out.println("n=" + nElements);
            for (int i = 0; i < nElements; i++) {
                int position = random.nextInt(nElements);
                while (visitedPositions.contains(position)) {
                    visitedPositions.add(position);
                    position = random.nextInt(nElements);
                }
                s += a[position];
                visitedPositions.add(position);
            }
        } else if (!v){
            for (long lnum : a)
                s += lnum;
            s += random.nextInt(10) + 1;
        }

        System.out.println(Arrays.toString(a) + " " + s);

        long start = System.currentTimeMillis();
        System.out.println(cleverAlgorithm(a, s) + " " +cleverSolution.toString());

        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println(elapsedTime/1000);

        long total = 0;
        for (long lnum : a) {
            total += lnum;
        }
        System.out.println(total);
    }


    public static void main(String[] args) {
        driver(56, 1000, true);
    }
}


package sort;

/**
 * 排序工具类
 */
public class SortUtil {
    /**
     * 插入排序
     *
     * @param sortList
     */
    public static void insertionSorting(int[] sortList) {
        for (int i = 0; i < sortList.length; i++) {
            int j = i - 1;
            int insertVal = sortList[i];
            while (j >= 0 && sortList[j] > insertVal) {
                sortList[j + 1] = sortList[j];
                j--;
            }
            sortList[j + 1] = insertVal;
        }
    }

    /**
     * 二分插入排序
     *
     * @param sortList
     */
    public static void binary_insertionSorting(int[] sortList) {
        for (int i = 0; i < sortList.length; i++) {
            int j = i - 1;
            int insertVal = sortList[i];
            int idx = binarySearch(sortList, 0, j, insertVal);
            for (int k = j; k >= idx; k--) {
                sortList[k + 1] = sortList[k];
            }
            sortList[idx] = insertVal;
        }
    }

    private static int binarySearch(int[] sortList, int begin, int end, int val) {
        if (begin > end) {
            return begin;
        }
        int mid = (begin + end) / 2;
        if (sortList[mid] > val) {
            return binarySearch(sortList, begin, mid - 1, val);
        } else {
            return binarySearch(sortList, mid + 1, end, val);
        }
    }

    /**
     * 希尔排序
     *
     * @param sortList
     */
    public static void shellSorting(int[] sortList) {
        int groupNums = sortList.length / 2;
        while (groupNums > 0) {
            for (int i = 0; i < groupNums; i++) {
                int gmNums = sortList.length / groupNums;
                for (int j = i, k = 0; j < sortList.length && k < gmNums; j += groupNums, k++) {
                    int before = j - groupNums;
                    int insertVal = sortList[j];
                    while (before >= i && sortList[before] > insertVal) {
                        sortList[before + groupNums] = sortList[before];
                        before = before - groupNums;
                    }
                    sortList[before + groupNums] = insertVal;
                }
            }
            groupNums = groupNums / 2;
        }
    }

    /**
     * 冒泡排序
     *
     * @param sortList
     */
    public static void bubbleSorting(int[] sortList) {
        for (int i = 0; i < sortList.length; i++) {
            for (int j = 0; j < sortList.length - i - 1; j++) {
                if (sortList[j] > sortList[j + 1]) {
                    int temp = sortList[j];
                    sortList[j] = sortList[j + 1];
                    sortList[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     *
     * @param sortList
     */
    public static void selectSorting(int[] sortList) {
        for (int i = 0; i < sortList.length; i++) {
            for (int j = i + 1; j < sortList.length; j++) {
                if (sortList[i] > sortList[j]) {
                    int temp = sortList[j];
                    sortList[j] = sortList[i];
                    sortList[i] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     *
     * @param sortList
     */
    public static void quickSorting(int[] sortList) {
        qSorting(sortList, 0, sortList.length - 1);
    }

    private static void qSorting(int[] sortList, int left, int right) {
        if (left >= right) {
            return;
        }
        //int idx = oneTimeQS(sortList, left, right);
        //int idx = oneTimeQS2(sortList, left, right);
        int idx = oneTimeQS3(sortList, left, right);

        qSorting(sortList, left, idx - 1);
        qSorting(sortList, idx + 1, right);
    }

    /**
     * 一趟快速排序(
     *
     * @param sortList
     * @param left
     * @param right
     */
    private static int oneTimeQS(int[] sortList, int left, int right) {
        int compareVal = sortList[right];
        int pre = left - 1;
        int cur = left;
        while (cur < right) {
            if (sortList[cur] < compareVal && ++pre != cur) {
                swap(sortList, cur, pre);
            }
            cur++;
        }
        swap(sortList, ++pre, right);
        return pre;

    }

    /**
     * 一趟快速排序(左右指针法)
     *
     * @param sortList
     * @param left
     * @param right
     */
    private static int oneTimeQS2(int[] sortList, int left, int right) {
        int compareVal = sortList[right];
        while (left < right) {
            while (sortList[left] < compareVal && left < right) {
                left++;
            }
            while (sortList[right] >= compareVal && left < right) {
                right--;
            }
            swap(sortList, left, right);
        }
        swap(sortList, left, right);
        return left;
    }

    /**
     * 一趟快速排序(挖坑法)
     *
     * @param sortList
     * @param left
     * @param right
     */
    private static int oneTimeQS3(int[] sortList, int left, int right) {
        int compareVal = sortList[right];
        int pos = right;
        while (left < right) {
            while (sortList[left] < compareVal && left < right) {
                left++;
            }
            sortList[pos] = sortList[left];
            pos = left;
            while (sortList[right] >= compareVal && left < right) {
                right--;
            }
            sortList[pos] = sortList[right];
            pos = right;
        }
        sortList[pos] = compareVal;
        return pos;

    }


    /**
     * 归并排序
     *
     * @param sortList
     */
    public static void mergeSorting(int[] sortList) {
        mergeSorting(sortList, 0, sortList.length - 1);
    }

    private static void mergeSorting(int[] sortList, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSorting(sortList, left, mid);
        mergeSorting(sortList, mid + 1, right);
        mergeTwoList(sortList, left, mid, right);
    }

    private static void mergeTwoList(int[] sortList, int left, int mid, int right) {
        int start = left;
        for (int i = mid + 1; i <= right; i++) {
            int insertVal = sortList[i];
            for (int j = start; j < i; j++) {
                if (sortList[j] > sortList[i]) {
                    move(sortList, j, i - 1);
                    sortList[j] = insertVal;
                }
            }
        }
    }

    /**
     * 堆排序
     *
     * @param sortList
     */
    public static void heapSorting(int[] sortList) {
        buildHeap(sortList);
        for (int i = 0; i < sortList.length; i++) {
            swap(sortList, 0, sortList.length - 1 - i);
            adjustHeap(sortList, 0, sortList.length - 2 - i);
        }
    }

    private static void adjustHeap(int[] sortList, int adjustIdx, int max) {
        int left = adjustIdx * 2 + 1;
        int right = adjustIdx * 2 + 2;
        if (left <= max && sortList[left] >= sortList[adjustIdx]) {
            swap(sortList, left, adjustIdx);
            adjustHeap(sortList, left, max);
        }
        if (right <= max && sortList[right] >= sortList[adjustIdx]) {
            swap(sortList, right, adjustIdx);
            adjustHeap(sortList, right, max);
        }
    }

    private static void buildHeap(int[] sortList) {
        for (int i = sortList.length / 2; i >= 0; i--) {
            adjustHeap(sortList, i, sortList.length - 1);
        }

    }

    private static void move(int[] sortList, int i, int j) {
        if (i > j || j >= sortList.length) {
            return;
        }
        for (int k = j; k >= i; k--) {
            sortList[k + 1] = sortList[k];
        }
    }

    private static void swap(int[] sortList, int f, int s) {
        int temp = sortList[f];
        sortList[f] = sortList[s];
        sortList[s] = temp;
    }

    private static void radixSorting(int[] sortList, int maxWs) {
        int cs = 1;
        for (int i = 0; i < maxWs; i++) {
            int[][] bucket = new int[10][sortList.length];
            int[] addPos = new int[10];
            for (int j = 0; j < sortList.length; j++) {
                int ws = (sortList[j] / cs) % 10;
                bucket[ws][addPos[ws]] = sortList[j];
                addPos[ws]++;
            }
            int cnt = 0;
            for (int k = 0; k < 10; k++) {
                for (int l = 0; l < addPos[k]; l++) {
                    sortList[cnt] = bucket[k][l];
                    cnt++;
                }
            }
            cs *= 10;
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 5, 7, 2, 1, 45, 23, 19};
        radixSorting(a, 2);
        System.out.print(a.toString());
    }

}

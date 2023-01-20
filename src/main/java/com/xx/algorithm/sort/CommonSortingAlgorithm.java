package com.xx.algorithm.sort;

/**
 * @author XuanXiao
 * @CreateDate 2022/6/30
 * 常见的排序算法
 */
public class CommonSortingAlgorithm {

    public static void main(String[] args) {

    }

    /**
     * 冒泡排序
     * O(N2)   最好O(N)  最坏O(N2)
     * 稳定
     */
    public static void Bsort(int[] arr) {
        //定义temp变量，用于之后的变量交换
        int temp;
        //进行n-1趟冒泡排序
        for (int i = 0; i < arr.length - 1; i++) {
            //将每两个元素进行比较。后i个已经有序了，所以j<arr.length-i-1
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前一个元素大于后一个元素
                if (arr[j] > arr[j + 1]) {
                    //交换a[j]和a[j+1])(交换前一个元素和后一个元素)
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     * O(N2)   最好O(N2)  最坏O(N2)
     * 不稳定
     */
    public static void chooseSort(int[] arry) {
        for (int i = 0; i < arry.length; i++) {
            for (int j = i + 1; j < arry.length; j++) {
                //判断，将较小的值放到数组前面
                //ij交换
                if (arry[i] > arry[j]) {
                    int temp;
                    temp = arry[i];
                    arry[i] = arry[j];
                    arry[j] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     * O(N2)   最好O(N)  最坏O(N2)
     * 稳定
     */
    public void insertSort(int[] array) {
        //外层向右的index，即作为比较对象的数据的index
        for (int index = 1; index < array.length; index++) {
            //用作比较的数据
            int temp = array[index];
            int leftindex = index - 1;
            //当比到最左边或者遇到比temp小的数据时，结束循环
            while (leftindex >= 0 && array[leftindex] > temp) {
                array[leftindex + 1] = array[leftindex];
                leftindex--;
            }
            //把temp放到空位上
            array[leftindex + 1] = temp;
        }
    }


    /**
     * 快速排序
     * O(N LOG N)   最好O(LOG N)  最坏O(N2) 空间O(LOG N)
     * 不稳定
     */
    public static void quickSort(int[] array, int low, int high) {
        /**
         * 分析：
         * 1.选定一个基准值，array[low]
         * 2.右指针从右向左遍历high--，查找比基准值小的数据，左指针从左向右low++，查找比基准值大的数据
         * 3.如果指针未相遇，交换左右两值位置，如果指针相遇，则替换基准值的位置
         * 4.左递归，右递归
         */
        // 方法退出条件,指针相遇或错过
        if (low >= high) {
            return;
        }
        // 1. 指定基准值和左右指针记录位置
        int pivot = array[low];
        int left = low;
        int right = high;
        int temp = 0;
        // 2. 遍历条件，左右指针位置
        while (left < right) {
            // 2.1 右侧遍历
            while (left < right && array[right] >= pivot) {
                right--;
            }
            // 2.2 左侧遍历
            while (left < right && array[left] <= pivot) {
                left++;
            }
            // 2.3 l指针还在r指针右侧，尚未相遇
            // 交换值
            if (left < right) {
                temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
        // 3. 当左右指针相遇，交换基准值位置
        array[low] = array[left];
        array[left] = pivot;
        // 4. 根据条件左侧递归遍历
        if (low < left) {
            quickSort(array, low, left - 1);
        }
        // 5. 根据条件右侧递归遍历
        if (right < high) {
            quickSort(array, right + 1, high);
        }
    }

    /**
     * 归并排序
     * O(N LOG N)   最好O(N LOG N)  最坏O(N LOG N) 空间O(N)
     * 不稳定
     */
    public static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //对first--last的前一半的元素进行排序
            mergeSort(array, left, mid, temp);
            //对first--last的后一半的元素进行排序
            mergeSort(array, mid + 1, right, temp);
            //对first--last左右两边拍好的两个序列进行归并
            mergeArray(array, left, mid, right, temp);
        }
    }

    public static void mergeArray(int[] arrays, int first, int mid, int last, int[] temp) {
        int firstP = first;
        int midP = mid;
        int mid1P = mid + 1;
        int lastP = last;
        int k = 0;

        //将一个有序序列归并完成
        while (firstP <= midP && mid1P <= lastP) {
            if (arrays[firstP] < arrays[mid1P]) {
                temp[k++] = arrays[firstP++];
            } else {
                temp[k++] = arrays[mid1P++];
            }
        }
        //如果剩下左边的序列，则将左边的序列依次添加到temp后面
        while (firstP <= midP) {
            temp[k++] = arrays[firstP++];
        }
        //如果剩下的是右边的序列，则将右边的序列添加到temp后面
        while (mid1P <= last) {
            temp[k++] = arrays[mid1P++];
        }
        //将temp中的有序序列依次拷贝到arrs序列相应的位置(first到last之间的这段序列)
        for (int i = 0; i < k; i++) {
            arrays[first + i] = temp[i];
        }
    }


    /**
     * 计数排序是一种线性时间的整数排序算法。如果数组的长度为n，
     * 整数范围（数组中最大整数与最小整数的差值）为k，适用于k远小于n的场景。
     * <p>
     * 计数排序的基本思想是先统计数组中每个整数在数组中出现的次数，然后按照从小到大的顺序将每个整数按照它出现的次数填到数组
     * 中。
     */
    public void CountSort(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : array) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] counts = new int[max - min + 1];
        for (int num : array) {
            counts[num - min] = counts[num] + 1;
        }

        int i = 0;
        for (int j = min; j <= max; j++) {
            while (counts[j - min] > 0) {
                array[i] = j;
                counts[j - min]--;
            }
        }

    }

}
package io.github.openpark.array;

import java.util.Arrays;

/**
 * @author anthony
 * @version 2025/6/10
 * @since 2025/6/10 22:19
 */
public class ArrayApplication {
	public static void main(String[] args) {
		declareAndInitArray();
		multidimensionalArray();
		arrayOperations();
		arraysOperations();
	}

	/**
	 * 声明、初始化数组
	 */
	public static void declareAndInitArray() {
		System.out.println("------------ 声明、初始化数组 ---------------");
		// 声明数组的两种方式
		// 方式1：数据类型[] 数组名
		int[] var1 = null;
		// 方式2（C语言语法）：数据类型 数组名[]
		int var2[] = null;

		// 静态初始化：声明时直接赋值
		int[] arr1 = {1, 2, 3, 4, 5};
		String[] names = {"Alice", "Bob", "Charlie"};

		// 动态初始化：先声明大小，后赋值
		int[] arr2 = new int[5]; // 创建长度为 5 的 int 数组，默认值 0
		arr2[0] = 10;
		arr2[1] = 20;

		System.out.println("arr2.length = " + arr2.length);
	}

	/**
	 * 多维数组
	 */
	public static void multidimensionalArray() {
		System.out.println("------------ 多维数组 ---------------");
		// 二维数组声明和初始化
		int[][] matrix = new int[3][3]; // 3行3列
		matrix[0][0] = 1;
		System.out.println("matrix[0][0] = " + matrix[0][0]);

		// 静态初始化二维数组
		int[][] matrix2 = {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};
		System.out.println("matrix2[0][0] = " + matrix2[0][0]);
	}

	/**
	 * 数组操作
	 */
	public static void arrayOperations() {
		System.out.println("------------ 数组的操作 ---------------");
		// 获取数组长度
		int[] originalArray = {1, 2, 3, 4, 5};
		System.out.println("arr.length = " + originalArray.length);

		// 复制数组
		// System.arraycopy
		// Object src,  int  srcPos, Object dest, int destPos, int length
		int[] newArray1 = new int[originalArray.length];
		System.arraycopy(originalArray, 0, newArray1, 0, Math.min(originalArray.length, newArray1.length));
		System.out.println("copy[0] = " + newArray1[0]);
		// Arrays.copyOf
		int[] newArray2 = Arrays.copyOf(originalArray, originalArray.length);
		System.out.println("copy2[0] = " + newArray2[0]);
	}

	/**
	 * 数组操作
	 * {@link java.util.Arrays}
	 */
	public static void arraysOperations() {
		System.out.println("------------ 使用 java.util.Arrays 数组操作 ---------------");
		int[] array = {5, 2, 4, 1, 3};
		System.out.println("初始化后 Arrays.toString(arr) = " + Arrays.toString(array));

		// 排序
		// 对基本数据类型数组排序，默认升序
		// 对对象数组排序（要求对象实现 Comparable 接口）
		Arrays.sort(array);
		System.out.println("排序后 Arrays.toString(arr) = " + Arrays.toString(array));

		// 二分查找（数组必须先排序）
		int index = Arrays.binarySearch(array, 3);
		System.out.println("二分查找 index = " + index);

		// 填充
		int[] arr2 = new int[5];
		// 所有元素设为100
		Arrays.fill(arr2, 100);
		System.out.println("填充后 Arrays.toString(arr2) = " + Arrays.toString(arr2));

		// 转换为字符串
		String str = Arrays.toString(arr2);  // [100, 100, 100, 100, 100]
		System.out.println("str = " + str);
	}
}
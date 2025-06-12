package io.github.openpark.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anthony
 * @version 2025/6/12
 * @since 2025/6/12 10:37
 */
public class ArrayListApplication {
	public static void main(String[] args) {
		bug62606525();
	}

	/**
	 * <a href="https://bugs.java.com/bugdatabase/view_bug.do?bug_id=6260652"/>
	 */
	public static void bug62606525() {
		Integer[] array = {1, 2};
		List<Integer> integerList = Arrays.asList(array);
		Object[] objectArray = integerList.toArray();
		// jdk  8 [Ljava.lang.Integer;
		// jdk 17 [Ljava.lang.Object;
		System.out.println(objectArray.getClass().getName());
		// jdk  8 true
		// jdk 17 false
		System.out.println(objectArray.getClass() == array.getClass());

		List<Integer> list = new ArrayList<>();
		// jdk  8 [Ljava.lang.Object;
		// jdk 17 [Ljava.lang.Object;
		System.out.println(list.toArray().getClass().getName());
		// jdk  8 true
		// jdk 17 true
		System.out.println(list.toArray().getClass() == Object[].class);
	}
}
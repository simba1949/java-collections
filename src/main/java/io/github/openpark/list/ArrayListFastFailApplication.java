package io.github.openpark.list;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ArrayList fail-fast 机制
 *
 * @author anthony
 * @version 2025/6/13
 * @since 2025/6/13 9:36
 */
@Slf4j
public class ArrayListFastFailApplication {
	public static void main(String[] args) {
		List<Integer> dataList = new ArrayList<>();
		new Thread4ListAdd(dataList).start();
		new Thread4ListRemove<>(dataList).start();
	}
}

@Slf4j
@AllArgsConstructor
class Thread4ListAdd extends Thread {
	private List<Integer> list;

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			log.info("循环执行添加元素{}", i);
			list.add(i);

			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				log.error("{}", e.getMessage(), e);
			}
		}
	}
}

@Slf4j
@AllArgsConstructor
class Thread4ListRemove<E> extends Thread {
	private List<E> list;

	@Override
	public void run() {
		while (true) {
			// 迭代器方式
			for (Iterator<E> iterator = list.iterator(); iterator.hasNext(); ) {
				E next = iterator.next();
				log.info("{}", next);
				try {
					TimeUnit.MILLISECONDS.sleep(5);
				} catch (InterruptedException e) {
					log.error("{}", e.getMessage(), e);
				}
			}
		}
	}
}
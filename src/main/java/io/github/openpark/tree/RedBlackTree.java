package io.github.openpark.tree;

import lombok.Data;

/**
 * 红黑树
 *
 * @author anthony
 * @version 2025/6/18
 * @since 2025/6/18 20:32
 */
public class RedBlackTree<E extends Comparable<E>> {
	public static final boolean RED = true;
	public static final boolean BLACK = false;

	private Node root;
	private int size;

	public void add(E e) {
		root = add(root, e);
		root.color = BLACK;
	}

	/**
	 * 添加节点
	 * 添加第一个节点时：该节点为根节点，颜色为黑色
	 * 添加第二个节点时：
	 * 1. 第一种情况是在根节点左侧添加节点时，该节点为左子节点，颜色为红色：无后续操作
	 * 2. 第一种情况是在根节点右侧添加节点时，该节点为右子节点，颜色为红色：需要左旋
	 * 添加第三个节点时：
	 * 1. 第一种情况是在根节点的左子树的左侧添加节点时，颜色为红色：需要右旋，需要颜色翻转
	 * 2. 第二种情况是在根节点的左子树的右侧添加节点时，颜色为红色：需要左旋，需要右旋，需要颜色翻转
	 * 3. 第三种情况是在根节点的右侧添加节点时，颜色为红色：无后续操作
	 *
	 * @param node 节点
	 * @param e    节点值
	 * @return 添加后的节点
	 */
	public Node add(Node node, E e) {
		if (node == null) {
			size++;
			return new Node(e);
		}

		if (e.compareTo(node.e) < 0) {
			node.left = add(node.left, e);
		} else if (e.compareTo(node.e) > 0) {
			node.right = add(node.right, e);
		}

		// 左旋：当右子节点为红色
		if (!isRed(node.left) && isRed(node.right)) {
			node = leftRotate(node);
		}

		// 右旋：当左子节点和左左子节点都为红色
		if (isRed(node.left) && isRed(node.left.left)) {
			node = rightRotate(node);
		}

		// 颜色翻转：当左右子节点都为红色
		if (isRed(node.left) && isRed(node.right)) {
			flipColor(node);
		}

		return node;
	}


	/**
	 * 左旋（Left Rotation）
	 * node                      x
	 * /   \                   /  \
	 * T1  x  ---------->   node T3
	 * /  \              /  \
	 * T2 T3            T1  T2
	 */
	private Node leftRotate(Node node) {
		Node x = node.right;

		node.right = x.left;
		x.left = node;

		x.color = node.color;
		node.color = RED;
		return x;
	}

	/**
	 * 右旋（Right Rotation）
	 * node                      x
	 * /   \                   /  \
	 * x  T2  ---------->    T1 node
	 * /  \                      /  \
	 * T1 T2                   T3  T4
	 */
	private Node rightRotate(Node node) {
		Node x = node.left;

		node.left = x.right;
		x.right = node;

		x.color = node.color;
		node.color = RED;
		return x;
	}

	/**
	 * 颜色翻转
	 * node                     node
	 * /   \                   /  \
	 * T1  T2  ---------->   T1  T2
	 * /  \                 /  \
	 * R  R                 B  B
	 */
	private void flipColor(Node node) {
		node.color = RED;
		if (node.left != null) node.left.color = BLACK;
		if (node.right != null) node.right.color = BLACK;
	}

	private boolean isRed(Node node) {
		return node != null && node.color == RED;
	}

	@Data
	public class Node {
		private E e;
		private Node left;
		private Node right;
		private boolean color;

		public Node(E e) {
			this.e = e;
			this.left = null;
			this.right = null;
			this.color = RED;
		}
	}
}
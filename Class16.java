import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Class16 {
	static int preOrIndex = 0;
	static int ans = Integer.MIN_VALUE;
	static Map hm = new HashMap<Integer, ArrayList<Integer>>();
	static int minLevel = Integer.MAX_VALUE;
	static int maxLevel = Integer.MIN_VALUE;

	public static void main(String[] args) {
		int ar1[] = { 1, 10, 32, 3, 6, 43, 12, 4, 7 };
		TreeNode root = Class14.createTree(ar1);

		root = deleteNode(root, 32);
		System.out.println(isFullBST(root));
		System.out.println(isCBT(root));
		System.out.println(isBalanced(root));
		System.out.println(isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}

	static int search(int[] inOr, int startIn, int endIn, int ele) {
		int i = 0;
		for (i = startIn; i < endIn; i++) {
			if (inOr[i] == ele)
				return i;
		}
		return i;
	}

	static void printPostOrder(int[] inOr, int[] preOr, int startIn, int endIn) {
		if (startIn > endIn)
			return;
		int index = search(inOr, startIn, endIn, preOr[preOrIndex++]);
		printPostOrder(inOr, preOr, startIn, index - 1);
		printPostOrder(inOr, preOr, index + 1, endIn);
		System.out.print(inOr[index] + " ");
	}

	private static TreeNode deleteNode(TreeNode root, int data) {
		if (root == null)
			return null;
		if (root.data < data)
			root.right = deleteNode(root.right, data);
		else if (root.data > data)
			root.left = deleteNode(root.left, data);
		else {
			if (root.left == null && root.right == null) {
				return null;
			} else if (root.right == null) {
				return root.left;
			} else if (root.left == null) {
				return root.right;
			} else {
				int maxEle = findMax(root.left);
				root.data = maxEle;
				deleteNode(root.left, maxEle);
			}
		}
		return root;
	}

	private static int findMax(TreeNode root) {
		if (root == null)
			return 0;
		return (int) Math.max(root.data, Math.max(findMax(root.left), findMax(root.right)));
	}

	static boolean isFullBST(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return true;
		}
		if (root.left != null && root.right != null) {
			return isFullBST(root.left) && isFullBST(root.right);
		}
		return false;
	}

	static boolean isCBT(TreeNode root) {
		if (root == null) {
			return true;
		}
		Queue<TreeNode> qu = new LinkedList<TreeNode>();
		qu.add(root);
		boolean flag = false;
		while (!qu.isEmpty()) {
			TreeNode node = qu.remove();
			if (node.left != null) {
				if (flag == true) {
					return false;
				}
				qu.add(node.left);
			} else {
				flag = true;
			}
			if (node.right != null) {
				if (flag == true) {
					return false;
				}
				qu.add(node.right);
			} else {
				flag = true;
			}
		}
		return true;
	}

	static boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;
		if (Math.abs(Class15.height(root.left) - Class15.height(root.right)) <= 1 && isBalanced(root.left)
				&& isBalanced(root.right)) {
			return true;
		}
		return false;
	}

	/*
	 * Returns true if the given tree is a BST and its values are >= min and <= max.
	 */
	public static boolean isBSTUtil(TreeNode node, int min, int max) {
		/* an empty tree is BST */
		if (node == null)
			return true;

		/* false if this node violates the min/max constraints */
		if (node.data < min || node.data > max)
			return false;

		/*
		 * otherwise check the subtrees recursively tightening the min/max constraints
		 */
		// Allow only distinct values
		return (isBSTUtil(node.left, min, node.data - 1) && isBSTUtil(node.right, node.data + 1, max));
	}

	public static int maxSumPath(TreeNode root) {
		int l = maxSumPath(root.left);
		int r = maxSumPath(root.right);
		int currentPath = l + r + root.data;

		int returnPath = Math.max(Math.max(root.data, root.data + l), root.data + r);
		ans = Math.max(ans, Math.max(currentPath, returnPath));
		return ans;
	}

	static void findLCA(TreeNode root, int u, int v) {
		if (u > root.data && v > root.data) {
			findLCA(root.right, u, v);
		} else if (u < root.data && v < root.data) {
			findLCA(root.left, u, v);
		} else {
			System.out.println(root.data);
			return;
		}
	}

	static int height(TreeNode root) {
		if (root == null)
			return -1;
		return Math.max(height(root.left), height(root.right)) + 1;
	}

	static int diameterOfTheTree(TreeNode root) {
		if (root == null)
			return 0;
		int leftLength = height(root.left);
		int rightLength = height(root.right);

		int leftDiameter = diameterOfTheTree(root.left);
		int rightDiameter = diameterOfTheTree(root.right);

		return Math.max(leftLength + rightLength + 1, Math.max(leftDiameter, rightDiameter));
	}

	static void verticalOrder(TreeNode root, int level) {
		if (root == null)
			return;
		if (maxLevel < level)
			maxLevel = level;
		if (minLevel > level)
			minLevel = level;
		if (hm.containsKey(level)) {
			ArrayList<Integer> ar = (ArrayList) hm.get(level);
			ar.add(root.data);
			hm.put(level, ar);
		} else {
			ArrayList<Integer> ar = new ArrayList<Integer>();
			ar.add(root.data);
			hm.put(level, ar);
		}
		verticalOrder(root.left, level - 1);
		verticalOrder(root.right, level + 1);
	}
}

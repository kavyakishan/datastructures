import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Class15 {
	static Map hm = new HashMap<Integer, ArrayList<Integer>>();
	static int M = 1000000007;

	public static void main(String[] args) {
		int ar1[] = { 1, 10, 32, 3, 6, 43, 12, 4, 7 };
		TreeNode root = Class14.createTree(ar1);

		System.out.println(countNodes(root));
		System.out.println(height(root));
		System.out.println(sumOfNodes(root));
		levelOrder(root);
		levelOrder2(root, 1);

		// printing level order

		for (int i = 1; hm.containsKey(i); i++) {
			ArrayList<Integer> list = (ArrayList) hm.get(i);
			for (int j = 0; j < list.size(); j++) {
				System.out.print(list.get(j) + " ");
			}
			System.out.println();
		}

		mirror(root);
		fillDepth(root, 0);
		System.out.println("Root to leaf sum: " + rootToLeaf(root, 0));
	}

	static int numOfDigits(int data) {
		int digits = 0;
		if (data == 0)
			return 1;
		while (data != 0) {
			digits++;
			data = data / 10;
		}
		return digits;
	}

	static long rootToLeaf(TreeNode root, long val) {
		if (root == null)
			return 0;
		val = (long) ((long) (val * Math.pow(10, numOfDigits(root.data))) % M + root.data) % M;

		if (root.left == null && root.right == null) {
			return val;
		}
		return (rootToLeaf(root.left, val) + rootToLeaf(root.right, val)) % M;

	}

	static void levelOrder2(TreeNode root, int level) {
		if (root == null)
			return;
		if (hm.containsKey(level)) {
			ArrayList<Integer> ar = (ArrayList) hm.get(level);
			ar.add(root.data);
			hm.put(level, ar);
		} else {
			ArrayList<Integer> ar = new ArrayList<Integer>();
			ar.add(root.data);
			hm.put(level, ar);
		}
		levelOrder2(root.left, level + 1);
		levelOrder2(root.right, level + 1);
	}

	private static void levelOrder(TreeNode root) {
		Queue<TreeNode> qu = new LinkedList<TreeNode>();
		qu.add(root);
		while(!qu.isEmpty()) {
			TreeNode node = qu.peek();
			System.out.print(node.data +"-");
			if(node.left != null) qu.add(node.left);
			if(node.right != null) qu.add(node.right);
			qu.remove();
		}
		System.out.println();
	}

	private static void fillDepth(TreeNode root, int depth) {
		if(root == null) return;
		root.depth = depth;
		fillDepth(root.left,depth+1);
		fillDepth(root.right, depth+1);
		return;
	}

	private static void mirror(TreeNode root) {
		
		// LR swap or swap LR (post or pre order)
		if(root == null ) return;
		mirror(root.left);
		mirror(root.right);
		
		TreeNode temp = root.right;
		root.right  = root.left;
		root.left = temp;
	}

	private static int sumOfNodes(TreeNode root) {
		if (root == null)
			return 0;
		return root.data + sumOfNodes(root.left) + sumOfNodes(root.right);
	}

	private static int countNodes(TreeNode root) {
		if (root == null)
			return 0;
		return 1 + countNodes(root.left) + countNodes(root.right);
	}

	public static int height(TreeNode root) {
		if(root == null) return -1;
		return 1 + Math.max(height(root.left), height(root.right));
	}

}

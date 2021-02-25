import java.util.Stack;

public class Class17 {

	public static void main(String[] args) {
		int ar1[] = {1,10,32,3,6,43,12,4,7};
		TreeNode root = Class14.createTree(ar1);
		
		preOrderItr(root); //DLR
		
		int ar2[] = {1,10,32,3,6,43,12,4,7};
		TreeNode root2 = Class14.createTree(ar2);
		inOrderItr(root2); //LDR
	}

	private static void inOrderItr(TreeNode root) {
		Stack<TreeNode> st = new Stack<TreeNode>();
		while(!st.isEmpty() || root!= null) {
			while(root!=null) {
				st.push(root);
				root = root.left;
			}
			TreeNode node = st.peek();
			st.pop();
			System.out.print(node.data + " ");
			root = node.right;
		}
	}

	private static void preOrderItr(TreeNode root) {
		Stack<TreeNode> st = new Stack<TreeNode>();
		st.push(root);
		while(!st.isEmpty()) {
			TreeNode curr = st.peek();
			st.pop();
			if(curr.right != null )st.push(curr.right);
			if(curr.left != null )st.push(curr.left);
			System.out.print(curr.data + " ");
		}
		System.out.println();
	}
}

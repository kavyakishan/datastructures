class TreeNode {
	int data;
	TreeNode left;
	TreeNode right;
	int depth;
	
	TreeNode(int data){
		this.data = data;
		this.left = null;
		this.right = null;
	}
	
}

public class Class14 {

	public static void main(String[] args) {
		int ar1[] = {1,10,32,3,6,43,12,4,7};
		TreeNode root = createTree(ar1);
		
		preOrder(root);
		System.out.println();
		
		inOrder(root);
		System.out.println();

		postOrder(root);
		System.out.println();

		
	}

	private static void postOrder(TreeNode root) {
		if(root == null) return;
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data + " ");
	}

	private static void preOrder(TreeNode root) {
		if(root == null) return;
		System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}

	private static void inOrder(TreeNode root) {
		if(root == null) return;
		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}

	public static TreeNode createTree(int[] ar1) {
		TreeNode root = null;
		for(int i = 0 ; i<ar1.length; i++) {
			root = insert(root,ar1[i]);
		}
		return root;
	}

	public static TreeNode insert(TreeNode root, int ele) {
        if(root == null) return new TreeNode(ele);
        if(root.data < ele) {
            root.right = insert(root.right,ele);
        } else {
            root.left = insert(root.left,ele);
        }
        return root;
    }
}

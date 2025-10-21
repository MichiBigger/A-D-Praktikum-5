package ch.zhaw.ads;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {
    private final TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    public void inorder(Visitor<T> vis) {
        // TODO Implement
    }

    public void preorder(Visitor<T> vis) {
        // TODO Implement
    }

    public void postorder(Visitor<T> vis) {
        // TODO Implement
    }
}

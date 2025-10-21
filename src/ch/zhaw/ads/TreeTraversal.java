package ch.zhaw.ads;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of Traversal<T> for binary trees.
 */
public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private final TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    // ===== PREORDER =====
    @Override
    public void preorder(Visitor<T> visitor) {
        preorder(root, visitor);
    }

    private void preorder(TreeNode<T> node, Visitor<T> v) {
        if (node == null) return;
        v.visit(node.values.get(0));
        preorder(node.left, v);
        preorder(node.right, v);
    }

    // ===== INORDER =====
    @Override
    public void inorder(Visitor<T> visitor) {
        inorder(root, visitor);
    }

    private void inorder(TreeNode<T> node, Visitor<T> v) {
        if (node == null) return;
        inorder(node.left, v);
        v.visit(node.values.get(0));
        inorder(node.right, v);
    }

    // ===== POSTORDER =====
    @Override
    public void postorder(Visitor<T> visitor) {
        postorder(root, visitor);
    }

    private void postorder(TreeNode<T> node, Visitor<T> v) {
        if (node == null) return;
        postorder(node.left, v);
        postorder(node.right, v);
        v.visit(node.values.get(0));
    }

    // ===== LEVELORDER =====
    @Override
    public void levelorder(Visitor<T> visitor) {
        if (root == null) return;
        Queue<TreeNode<T>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode<T> n = q.poll();
            visitor.visit(n.values.get(0));
            if (n.left != null) q.add(n.left);
            if (n.right != null) q.add(n.right);
        }
    }

    // ===== INTERVAL =====
    @Override
    public void interval(T min, T max, Visitor<T> visitor) {
        interval(root, min, max, visitor);
    }

    private void interval(TreeNode<T> node, T min, T max, Visitor<T> v) {
        if (node == null) return;
        // Left subtree may contain >= min
        if (node.values.get(0).compareTo(min) >= 0) {
            interval(node.left, min, max, v);
        }
        // Current node in interval?
        if (node.values.get(0).compareTo(min) >= 0 && node.values.get(0).compareTo(max) <= 0) {
            v.visit(node.values.get(0));
        }
        // Right subtree may contain <= max
        if (node.values.get(0).compareTo(max) < 0) {
            interval(node.right, min, max, v);
        }
    }
}
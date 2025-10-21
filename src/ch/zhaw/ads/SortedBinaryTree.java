package ch.zhaw.ads;

import java.util.LinkedList;
import java.util.List;

public class SortedBinaryTree<T extends Comparable<T>> implements Tree<T> {
    protected TreeNode<T> root;

    private TreeNode<T> insertAt(TreeNode<T> node, T x) {
        if (node == null) {
            return new TreeNode<>(x);
        } else {
            if (x.compareTo(node.getValue()) <= 0) {
                node.left = insertAt(node.left, x);
            } else {
                node.right = insertAt(node.right, x);
            }
            return node;
        }
    }

    @Override
    public void add(T x) {
        root = insertAt(root, x);
    }

    private TreeNode<T> findRepAt(TreeNode<T> node, TreeNode<T> rep) {
        if (node.right != null) {
            node.right = findRepAt(node.right, rep);
        } else {
            rep.values = node.values;
            node = node.left;
        }
        return node;
    }

    private TreeNode<T> removeAt(TreeNode<T> node, T x, TreeNode<T> removed) {
        if (node == null) {
            return null;
        } else {
            int cmp = x.compareTo(node.getValue());
            if (cmp < 0) {
                node.left = removeAt(node.left, x, removed);
            } else if (cmp > 0) {
                node.right = removeAt(node.right, x, removed);
            } else {
                removed.values = node.values;
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                } else {
                    TreeNode<T> rep = new TreeNode<>(null);
                    node.left = findRepAt(node.left, rep);
                    node.values = rep.values;
                }
            }
            return node;
        }
    }

    @Override
    public T remove(T x) {
        TreeNode<T> removed = new TreeNode<>(null);
        root = removeAt(root, x, removed);
        return removed.getValue();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Traversal<T> traversal() {
        return new TreeTraversal<>(root);
    }

    // Aufgabe 4: Höhe berechnen
    protected int calcHeight(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftH = calcHeight(node.left);
        int rightH = calcHeight(node.right);
        return 1 + Math.max(leftH, rightH);
    }

    @Override
    public int height() {
        return calcHeight(root);
    }

    // Aufgabe 4: Größe berechnen
    protected int calcSize(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + calcSize(node.left) + calcSize(node.right);
    }

    @Override
    public int size() {
        return calcSize(root);
    }

    @Override
    public boolean balanced() {
        return false;
    }

    public String printTree() {
        StringBuilder out = new StringBuilder();
        if (root == null) {
            return out.toString();
        }
        if (root.right != null) {
            printTree(root.right, out, true, "");
        }
        out.append(root.values).append("\n");
        if (root.left != null) {
            printTree(root.left, out, false, "");
        }
        return out.toString();
    }

    private void printTree(TreeNode<T> node, StringBuilder out, boolean isRight, String indent) {
        if (node.right != null) {
            printTree(node.right, out, true,
                    indent + (isRight ? "        " : " |      "));
        }
        out.append(indent);
        if (isRight) {
            out.append(" /");
        } else {
            out.append(" \\");
        }
        out.append("----- ");
        out.append(node.values).append("\n");
        if (node.left != null) {
            printTree(node.left, out, false,
                    indent + (isRight ? " |      " : "        "));
        }
    }
}

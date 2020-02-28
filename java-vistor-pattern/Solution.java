import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.Scanner;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private List<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }

    public String toString() {
        return String.format("[value=%d, color=%s, depth=%d, children=\n\t%s]", getValue(), getColor(), getDepth(),
                children.toString());
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }

    public String toString() {
        return String.format("[value=%d, color=%s, depth=%d]", getValue(), getColor(), getDepth());
    }
}

abstract class TreeVis {
    public abstract int getResult();

    public abstract void visitNode(TreeNode node);

    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int sum = 0;

    public int getResult() {
        return sum;
    }

    public void visitNode(TreeNode node) {
        // do nothing
    }

    public void visitLeaf(TreeLeaf leaf) {
        sum += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private static final int MOD = 1000000007;
    private long prod = 1;

    public int getResult() {
        return (int) prod;
    }

    public void visitNode(TreeNode node) {
        calcProduct(node);
    }

    public void visitLeaf(TreeLeaf leaf) {
        calcProduct(leaf);
    }

    private void calcProduct(Tree tree) {
        if (Color.RED == tree.getColor()) {
            prod = (prod * tree.getValue()) % MOD;
        }
    }

}

class FancyVisitor extends TreeVis {
    private int sumGreenLeafs = 0;
    private int sumEventNonLeafs = 0;

    public int getResult() {
        return Math.abs(sumEventNonLeafs - sumGreenLeafs);
    }

    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0) {
            sumEventNonLeafs += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (Color.GREEN == leaf.getColor()) {
            sumGreenLeafs += leaf.getValue();
        }
    }
}

public class Solution {

    public static Tree solve() {
        final Scanner scan = new Scanner(System.in);
        final int nodeCount = scan.nextInt();
        final int[] values = parseValues(scan, nodeCount);
        final Color[] colors = parseColors(scan, nodeCount);
        final Map<Integer, Set<Integer>> adjacencies = parseAdjacencies(scan, nodeCount);
        scan.close();
        return buildTree(0, 0, adjacencies, values, colors);
    }

    static int[] parseValues(final Scanner scan, final int nodeCount) {
        final int[] values = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            values[i] = scan.nextInt();
        }
        return values;
    }

    static Color[] parseColors(final Scanner scan, final int nodeCount) {
        final Color[] colors = new Color[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            colors[i] = (scan.nextInt() == 0) ? Color.RED : Color.GREEN;
        }
        return colors;
    }

    static Map<Integer, Set<Integer>> parseAdjacencies(final Scanner scan, final int nodeCount) {
        final Map<Integer, Set<Integer>> adjacency = new HashMap<Integer, Set<Integer>>();
        for (int i = 0; i < nodeCount - 1; i++) {
            final int u = scan.nextInt() - 1;
            final int v = scan.nextInt() - 1;
            Set<Integer> uAdj = adjacency.get(u);
            if (uAdj == null) {
                uAdj = new HashSet<>();
                adjacency.put(u, uAdj);
            }
            uAdj.add(v);
            Set<Integer> vAdj = adjacency.get(v);
            if (vAdj == null) {
                vAdj = new HashSet<>();
                adjacency.put(v, vAdj);
            }
            vAdj.add(u);
        }
        return adjacency;
    }

    static Tree buildTree(int nodeNr, int depth, Map<Integer, Set<Integer>> adjacencies, int[] values, Color[] colors) {
        final Set<Integer> neighbors = adjacencies.get(nodeNr);
        if (neighbors.isEmpty()) {
            return new TreeLeaf(values[nodeNr], colors[nodeNr], depth);
        } else {
            final TreeNode node = new TreeNode(values[nodeNr], colors[nodeNr], depth);
            for (int neighbor : neighbors) {
                final Set<Integer> neighboursOfNeighbour = adjacencies.get(neighbor);
                neighboursOfNeighbour.remove(nodeNr);
                node.addChild(buildTree(neighbor, depth + 1, adjacencies, values, colors));
            }
            return node;
        }
    }

    public static void main(String[] args) {
        Tree root = solve();

        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);

    }

}
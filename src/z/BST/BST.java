package z.BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    private enum Operation { pass, visit }

    private class Command {
        Node node;
        Operation op;

        Command (Node node, Operation op) {
            this.node = node;
            this.op = op;
        }
    }

    private class Node {
        E data;
        Node left;
        Node right;

        Node (E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private int size;
    private Node root;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /****************** add ******************************/

    public void addt(E value) {
        if (root == null) {
            root = new Node(value, null, null);
            size++;
            return;
        }
        Node cur = root;
        while (cur != null) {
            if (cur.data.compareTo(value) > 0  && cur.left == null) {
                cur.left = new Node(value, null, null);
                size++;
            } else if (cur.data.compareTo(value) < 0  && cur.right == null) {
                cur.right = new Node(value, null, null);
                size++;
            } else  {
                if (cur.data.compareTo(value) > 0) {
                    cur = cur.left;
                } else  {
                    cur = cur.right;
                }
            }
        }
    }

    public void addr(E data) {
        root = add(root, data);
    }

    private Node add(Node node, E data) {
        if (node == null) {
            size++;
            return new Node(data, null ,null);
        }

        if (node.data.compareTo(data) > 0) {
            node.left = add(node.left, data);
        } else  {
            node.right = add(node.right, data);
        }
        return node;
    }

    /****************** add ******************************/

    /****************** contains ******************************/

    public boolean contains(E value) {
        return contains(root, value);
    }

    private boolean contains (Node node, E value) {
        if (node == null) return false;
        if (node.data == value) return true;
        if (node.data.compareTo(value) > 0) {
            return contains(node.left, value);
        } else {
            return contains(node.right, value);
        }
    }

    /****************** contains ******************************/


    /****************** traverse ******************************/
    // 前序遍历 / 深度优先
    public void preOrder (Node node) {
        if (node == null) return;
         System.out.print(node.data+"  ");
         preOrder(node.left);
         preOrder(node.right);
    }

    // 前序遍历非递归实现
    public void preOrderNR (Node node) {

        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node tmp = stack.pop();
            System.out.print(tmp.data + "  ");
            if (tmp.right != null)
            stack.push(tmp.right);
            if (tmp.left != null)
            stack.push(tmp.left);
        }
    }

    public void midOrder (Node node) {
        if (node == null) return;
        midOrder(node.left);
        System.out.print(node.data+"  ");
        midOrder(node.right);
    }

    // 非递归中序遍历
    public void midOrderNR (Node node) {

        Stack<Command> stack = new Stack<>();
        stack.push(new Command(node, Operation.pass));

        while (!stack.isEmpty()) {

            Command cmd = stack.pop();

            if (cmd.op.equals(Operation.visit)) {
                System.out.print(cmd.node.data + "  ");
            } else {

                if (cmd.node.right != null) {
                    stack.push(new Command(cmd.node.right, Operation.pass));
                }

                stack.push(new Command(cmd.node, Operation.visit));

                if (cmd.node.left != null) {
                    stack.push(new Command(cmd.node.left, Operation.pass));
                }
            }
        }
    }

    // 后续遍历用于释放内存
    void postOrder (Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data+"  ");
    }

    // 非递归后序遍历
    public void  postOrderNR (Node node) {
        Stack<Command> stack = new Stack<>();
        stack.push(new Command(node, Operation.pass));
        while (!stack.isEmpty()) {
            Command cmd = stack.pop();

            if (cmd.op.equals(Operation.visit)) {
                System.out.print(cmd.node.data + "  ");
            } else {
                stack.push(new Command(cmd.node, Operation.visit));
                if (cmd.node.right != null) {
                    stack.push(new Command(cmd.node.right, Operation.pass));
                }
                if (cmd.node.left != null) {
                    stack.push(new Command(cmd.node.left, Operation.pass));
                }
            }
        }
    }

    // 层序遍历 / 广度优先遍历 - 队列实现
    public void levelOrder () {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.data + "  ");
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }

    /****************** traverse ******************************/


    /****************** min ******************************/

    public Node getMin() {
        return minimum(root);
    }

    private Node minimum (Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    /****************** max ******************************/

    public Node getMax() {
        return maximum(root);
    }

    private Node maximum(Node node) {
        if (node.right == null)
            return node;
        return maximum(node.right);
    }

    /****************** remove ******************************/

    public Node removeMin() {
        Node min = getMin();
        root = removeMin(root);
        return min;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node tmp = node.right;
            node.right = null;
            return tmp;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public Node removeMax () {
        Node max = getMax();
        root = removeMax(root);
        return max;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node tmp = node.left;
            node.right = null;
            return tmp;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) return null;

        if (node.data.compareTo(e) > 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (node.data.compareTo(e) < 0) {
            node.right = remove(node.right, e);
            return node;
        } else { // node to remove
            if (node.left == null) {
                Node tmp = node.right;
                node.right = null;
                size--;
                return tmp;
            }
            if (node.right == null) {
                Node tmp = node.left;
                node.left = null;
                size--;
                return tmp;
            }

            // have both left & right
            // or maxLeft
            Node rightMinNode = minimum(node.right);
            rightMinNode.left = node.left;
            rightMinNode.right = removeMin(rightMinNode); // 😡😡😡😡😡！！
            node.left = node.right = null;
            size--;
            return rightMinNode;

//            Node leftMax = maximum(node.left);
//            leftMax.left = removeMax(node.left);
//            leftMax.right = node.right;
//            node.left = node.right = null;
//            size--;
//            return leftMax;
        }
    }

    /******** 输出格式 **********/
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }
    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.data +"\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }
    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums)
            bst.addt(num);
        System.out.println(bst);

        bst.removeMin();
        System.out.println(bst);

        bst.removeMax();
        System.out.println(bst);
    }

    private void test () {
//        bst.preOrder(bst.root);
//        System.out.println();
//        bst.preOrderNR(bst.root);
//
//        System.out.println();
//        System.out.println();
//
//        bst.midOrder(bst.root);
//        System.out.println();
//        bst.midOrderNR(bst.root);
//
//        System.out.println();
//        System.out.println();
//
//        bst.postOrder(bst.root);
//        System.out.println();
//        bst.postOrderNR(bst.root);
//
//        System.out.println();
//        System.out.println();
//        bst.levelOrder();
    }

}

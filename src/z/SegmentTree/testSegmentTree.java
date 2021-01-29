package z.SegmentTree;

public class testSegmentTree {

    public static void main(String[] args) {
        Integer [] arr = {-2, 0, 3, -5, 2, -1};;
        SegmentTree<Integer> tree = new SegmentTree<>(arr, (a, b) -> a + b);
        System.out.println(tree);
    }

}

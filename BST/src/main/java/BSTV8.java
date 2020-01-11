import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BSTV8<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left,right;
        public Node(E e){
            this.e=e;
            left=null;
            right=null;
        }
    }
    private Node root;
    private int size;

    public BSTV8(){
        root=null;
        size=0;
    }
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(E e){
        root=add(root,e);
    }

    //返回插入新节点后二分搜索树的根
    private Node add(Node node,E e){

        //如果当前的节点为空，则当前人就是节点
        if(node==null){
            size++;
            return new Node(e);
        }

        if(e.compareTo(node.e)<0){

            //这里犹如转包一样，把这个锅甩给再下面的人的，就OK了
            node.left=add(node.left,e);
        }else if(e.compareTo(node.e)>0){
            node.right=add(node.right,e);
        }

        return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }

    private boolean contains(Node node,E e){
        if(node==null) return false;
        if(e.compareTo(node.e)<0){
            return contains(node.left,e);
        }else if(e.compareTo(node.e)>0){
            return contains(node.right,e);
        }
        return true;
    }

    public void preOrder(){
        preOrder(root);
    }

    //前序遍历
    private void preOrder(Node node){
        if(node==null)return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }


    public void inOrder(){
        inOrder(root);
    }

    /**
     * 中序遍历
     * 一个很神奇的现象，顺序是从小到大排序的
     *
     * 先遍历小的，再遍历大的
     * @param node
     */
    private void inOrder(Node node){
        if(node==null)return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }





    public void afterOrder(){
        afterOrder(root);
    }

    //后序遍历

    /**
     * 应用场景：每一个节点，必须释放完子树信息，才能释放自己
     * @param node
     */
    private void afterOrder(Node node){
        if(node==null)return;

        afterOrder(node.left);
        afterOrder(node.right);
        System.out.println(node.e);
    }



    /*
    在做事之前，先要想好，这里想要的效果是什么然后才动手
    * */

    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();

          res= generateTostring(root,0,res);

          return res.toString();
    }

    private StringBuilder generateTostring(Node node, int detpth, StringBuilder res){

        if(node==null){
            res.append(generateDetpthString(detpth)+"null\n");
            return res;
        }


        res.append(generateDetpthString(detpth)+node.e.toString()+"\n");

        generateTostring(node.left,detpth+1,res);

        generateTostring(node.right,detpth+1,res);

        return res;
    }

    //这个方法就是生成深度的前缀字符串
    private StringBuilder generateDetpthString(int detpth){
        StringBuilder res =new StringBuilder();
        for (int i = 0; i < detpth; i++) {
            res.append("-");
        }
        return res;
    }

    //非递归的前序遍历
    public void preOrderNR(){
        Stack<Node> stack=new Stack();
        stack.push(root);

        while (!stack.empty()){
            Node currentNode = stack.pop();
            System.out.println(currentNode.e);

            if(currentNode.right!=null){
                stack.push(currentNode.right);
            }

            if(currentNode.left!=null){
                stack.push(currentNode.left);
            }
        }
    }



    /**
     * 广度优先遍历，也叫 二叉树的层序遍历
     *
     * 广度优先遍历的使用场景在于搜索最短路径
     *
     * 广度优先的遍历 是使用 队列来做的
     */
    public void leverOrder(){

        Queue<Node> q=new LinkedList<Node>();
        q.add(root);
        while(!q.isEmpty()){
            Node currentNode = q.remove();
            System.out.println(currentNode.e);
            if(currentNode.left!=null){
                q.add(currentNode.left);
            }
            if(currentNode.right!=null){
                q.add(currentNode.right);
            }
        }

    }


    public static void main(String[] args) {
        BSTV8<Integer> bst=new BSTV8<Integer>();
        int[] nums={5,3,6,8,4,2};
        for (int num: nums) {
            bst.add(num);
        }
/*
*
*     5
*   3   6
*  2 4    8
*
*
* */


        System.out.println(bst.toString());

        System.out.println("----------------");

        bst.leverOrder();

        /*
        前序-中序-后序遍历，本质上都是深度优先的遍历
        * */



    }

}

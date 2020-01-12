import java.util.*;

public class BSTV9<E extends Comparable<E>> {
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

    public BSTV9(){
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


    /**
     * 寻找二分搜索树的最小元素
     * @return
     */
    private E miniMum(){
        if(size==0)
            throw new IllegalArgumentException("BST is Empty");
        return miniMun(root).e;
    }

    private Node miniMun(Node node){

        //做好预防措施
        if(node==null){
            throw new IllegalArgumentException();
        }

        if(node.left==null){
            return node;
        }

        return miniMun(node.left);
    }

    /**
     * 移除最小的值，并且返回这个最小的值
     * @return
     */
    public E removeMin(){
        E miniNum= miniMum();
        root= removeMin(root);
        return miniNum;
    }

    /**
     * 返回移除最小值后返回的节点
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        /*
        * 如何做到移除节点的？
        * 我们不需要明显的去删除某一个节点
        * 在二叉树中，我们只返回需要的节点即可，不需要的，则不返回了
        *
        * */
        //当前的左子树为空，则当前节点是需要被移除的，但是得保留这个节点的右子树，
        //所以我们只需要返回右子树就好了，我们不用去管理从某一个节点去移除什么
        if(node.left==null){

            //保留当前的右子树
            Node right=node.right;

            size--;

            //既然需要删除当前节点，则需要删除当前节点的右子树
            node.right=null;
            return right;
        }

        //如果当前的节点不是最小节点，那么需要继续往下找最小的节点
        //把这个任务再一次进行，然后用当前的左子树进行接收
        node.left= removeMin(node.left);
        return node;
    }


    /**
     * 获取最大值
     * @return
     */
    public E maxiMun(){
        return maxiMun(root).e;
    }

    private Node maxiMun(Node node){
        if(node==null){
            throw new IllegalArgumentException();
        }

        if(node.right==null){
            return node;
        }

        return maxiMun(node.right);
    }


    /**
     * 删除最大值
     * @return
     */
    public E removeMax(){
        E maxiMun=maxiMun();

        //递归的从根节点处理这个树  --> 小弟，我把删除最大值的这个事情交个你了，你自己去做，返回一个结果即可
        root= removeMax(root);
        return maxiMun;
    }

    private Node removeMax(Node node){

        //当前右子树为空
        if(node.right==null){
            //返回左子树
            Node left=node.left;

            size--;

            //把当前节点的左子树清空
            node.left=null;

            //返回左子树
            return left;
        }

        node.right = removeMax(node.right);
        return node;
    }




    public static void main(String[] args) {
        BSTV9<Integer> bst = new BSTV9<Integer>();
        Random random = new Random();
        int num=1000;
        for (int i = 0; i < num; i++) {
            bst.add(random.nextInt(10000));
        }

        List<Integer> arrayList = new ArrayList<Integer>();
        while (!bst.isEmpty()){
            arrayList.add( bst.removeMin());
        }

        System.out.println(arrayList);

        for (int i = 1; i < arrayList.size(); i++) {
            if(arrayList.get(i-1)>arrayList.get(i)){
                throw new IllegalArgumentException("Error");
            }

        }

        System.out.println("Ok ");

    }

}


/**
 * 实现找到最小值
 * 实现找到最大值
 * 实现remove最小值
 * 实现remove最大值
 * 测试removeMin
 * 测试removeMax
 *
 */

/**
 * 教训：
 * 1. 分析哪些方法是public的，哪些方法是private的
 * 2. 返回Node的方法，或者是中间的方法，其实都是private方法
 * 3. Node是内部的概念，是private的，是不能作为public去访问的
 * 4. 每次都需要记得对于size的维护，++ 或者 --
 * 5. 对于递归 root=removeMin(root) 不是用记代码的形式去写出来，而是确切的理解他的含义
 *    含义就是：执行移除最小值的操作，并且返回根节点
 *    只有确切理解如何执行递归，才能够实现递归
 *
 */


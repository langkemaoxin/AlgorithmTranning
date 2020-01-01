public class BSTV2<E extends Comparable<E>> {
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

    public BSTV2(){
        root=null;
        size=0;
    }
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    /**
     * 往这颗树添加一个元素
     * @param e
     */
    public void add(E e){

        //第二种方式就是使用递归
        //用自己的话来概括就是：老大吩咐小弟做事一样

        //在版本一中，作为一个调用方法，也就是老大，竟然要知道下面人做事的细节
        //*********************************************
        // if(root==null) root=new Node(e);
        //*********************************************

        //这肯定是不能忍的，应该要这么做，我让你去做事，你接到事情了，做好后再返回给我
        //基于这个思路，我们可以这样做，我才不管你们的逻辑是什么，反正我就要这个东西

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


}

public class BSTV1_Self<E extends Comparable<E>> {
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

    public BSTV1_Self(){
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

        //算法，先要考虑最简单的情况,然后针对每一个步骤都进行处理

        //1. 节点为空的情况
        if(root==null){
            root=new Node(e);
        }

        //2. 节点不为空的情况下，应该往根节点上插入数据，因为这里不好处理，所以要新建一个
        //方法进行处理
        add(root,e);
    }

    private void add(Node root,E e){

        //根据二叉树的算法原理，一个新插入的数，都是从根节点进行比较的
        //1. 如果当前节点的数字和要插入的数字一样大，我们就直接返回了【在我们这里的逻辑就是这样，后期可以扩展并存多个】
        //2. 如果要插入的数字比当前节点的数字要小，则往当前节点的左子树进行插入
        //3. 如果要插入的数字比当前节点的数字要大，则往当前节点的右子树进行插入

        //这里的root节点不可能为空，否则这算法无法进行


        //节点值相等
        if(e.compareTo(root.e)==0) return;

        //搜索左子树
        if(e.compareTo(root.e)<0){
            if(root.left==null){
                root.left=new Node(e);
            }else{
                add(root.left,e);
            }
        }

        //搜索右子树
        if(e.compareTo(root.e)>0){
            if(root.right==null){
                root.right=new Node(e);
            }else{
                add(root.right,e);
            }
        }


    }


}

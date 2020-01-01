public class BSTV1_Fix<E extends Comparable<E>> {
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

    public BSTV1_Fix(){
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


        //节点值相等
        if(e.compareTo(root.e)==0) return;

        //搜索左子树
        if(e.compareTo(root.e)<0){
            if(root.left==null){
                root.left=new Node(e);

                //***********************************
                //  代码遗漏点1：忘记返回，忘记自增计数器
                size++;
                return;
                //***********************************
            }else{
                add(root.left,e);
            }
        }

        //搜索右子树
        if(e.compareTo(root.e)>0){
            if(root.right==null){
                root.right=new Node(e);
                //***********************************
                //  代码遗漏点2：忘记返回，忘记自增计数器
                size++;
                return;
                //***********************************
            }else{
                add(root.right,e);
            }
        }


    }


}

public class BSTV4<E extends Comparable<E>> {
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

    public BSTV4(){
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

    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();

          res= toString(root,0,res);

          return res.toString();
    }

    private StringBuilder toString(Node node,int detpth,StringBuilder res){

        res=buildDetpth(detpth);

        if(node==null){
            res.append("null\n");
            return res;
        }


        res.append(node.e.toString()+"\n");

        toString(node.left,detpth+1,res);

        toString(node.right,detpth+1,res);

        return res;
    }

    private StringBuilder buildDetpth(int detpth){
        StringBuilder res =new StringBuilder();
        for (int i = 0; i < detpth; i++) {
            res.append("-");
        }
        return res;
    }



    public static void main(String[] args) {
        BSTV4<Integer> bst=new BSTV4<Integer>();
        int[] nums={5,3,6,8,4,2};
        for (int num: nums) {
            bst.add(num);
        }
        //bst.preOrder();


        System.out.printf(bst.toString());
    }

}

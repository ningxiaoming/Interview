package com.datastructure.tree;



/*
        手写红黑树
 */
public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    //树根的引用
    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    //获取当前节点的父节点
    private RBNode parentOf(RBNode node){
        if (node!=null){
            return node.parent;
        }
        return null;
    }
    //节点是否为红色
    private boolean isRed(RBNode node){
        if (node!=null){
            return node.color == RED;
        }
        return false;
    }
    //节点是否为黑色
    private boolean isBlack(RBNode node){
        if (node!=null){
            return node.color == BLACK;
        }
        return false;
    }
    //设置节点为红色
    private void setRed(RBNode node){
        if (node!=null){
            node.color = RED;
        }
    }
    //设置节点为黑色
    private void setBlack(RBNode node){
        if (node!=null){
            node.color = BLACK;
        }
    }
    //二叉树中序打印方法
    public void inOrderPrint(){
        inOrderPrint(this.root);
    }
    private void inOrderPrint(RBNode node){
        if (node != null){
            inOrderPrint(node.left);
            System.out.println("key:"+node.key + ",value:"+node.value);
            inOrderPrint(node.right);
        }
    }
    /**
     * 左旋方法
     * 左旋示意图：左旋x节点
     *    p                   p
     *    |                   |
     *    x                   y
     *   / \         ---->   / \
     *  lx  y               x   ry
     *     / \             / \
     *    ly  ry          lx  ly
     *
     * 左旋做了几件事？
     * 1.将y的左子节点赋值给x的右边，并且把x设置为y的左子节点的父节点
     * 2.将x的父节点（非空时）指向y，更新y的父节点为x的父节点
     * 3.将y的左子节点指向x，更新x的父节点为y
     */
    private void leftRotate(RBNode x){
        RBNode y = x.right;
        //1.将y的左子节点赋值给x的右边，并且把x设置为y的左子节点的父节点
        x.right = y.left;
        if (y.left !=null){
            y.left.parent = x;
        }
        //2.将x的父节点（非空时）指向y，更新y的父节点为x的父节点
        if (x.parent != null){
            y.parent = x.parent;
            if (x == x.parent.left){
                x.parent.left = y;
            }else {
                x.parent.right = y;
            }
        }else {//说明x为根节点，此时需要更新y节点为根节点
            this.root = y;
            this.root.parent = null;
        }
        //3.将y的左子节点指向x，更新x的父节点为y
        x.parent = y;
        y.left = x;
    }
    /**
     * 右旋方法
     * 右旋示意图：右旋y节点
     *
     *    p                       p
     *    |                       |
     *    y                       x
     *   / \          ---->      / \
     *  x   ry                  lx  y
     * / \                         / \
     *lx  ly                      ly  ry
     *
     * 右旋都做了几件事？
     * 1.将x的右子节点 赋值 给了 y 的左子节点，并且更新x的右子节点的父节点为 y
     * 2.将y的父节点（不为空时）指向x，更新x的父节点为y的父节点
     * 3.将x的右子节点指向y，更新y的父节点为x
     */
    private void rightRotate(RBNode y){
        //1.将x的右子节点 赋值 给了 y 的左子节点，并且更新x的右子节点的父节点为 y
        RBNode x = y.left;
        y.left = x.right;
        if (x.right != null){
            x.right.parent = y;
        }
        //2.将y的父节点（不为空时）指向x，更新x的父节点为y的父节点
        if (y.parent != null){
            x.parent = y.parent;

            if (y == y.parent.left){
                y.parent.left = x;
            }else {
                y.parent.right = x;
            }
        }else {
            this.root = x;
            this.root.parent = null;
        }
        //3.将x的右子节点指向y，更新y的父节点为x
        y.parent = x;
        x.right = y;
    }

    /**
     * 公开的插入接口
     * @param key 键
     * @param value 值
     */
    public void insert(K key,V value){
        RBNode node = new RBNode();
        node.setKey(key);
        node.setValue(value);
        //新节点一定是红色
        node.setColor(RED);
        insert(node);
    }
    private void insert(RBNode node){
        //第一步：查找当前node的父节点
        RBNode parent = null;
        RBNode x = this.root;
        while (x != null){
            parent = x;
            //cmp > 0 说明node.key 大于 x.key 需要到右子树去比较
            //cmp == 0 ， 说明 node.key == x.key 需要做替换操作
            int cmp = node.key.compareTo(x.key);
            if (cmp > 0){
                x = x.right;
            }else if (cmp == 0){
                x.setValue(node.getValue());
                return;
            }else {
                x = x.left;
            }
        }
        node.parent = parent;
        if (parent != null) {
            //判断node与parent的key谁大
            int cmp = node.key.compareTo(parent.key);
            //当前node的key比parent的key 大， 需要把node放入parent的右子节点
            if (cmp > 0){
                parent.right = node;
            }else {//当前node的key比parent的key小， 需要把node放入parent的左子节点
                parent.left = node;
            }
        }else {
            this.root = node;
        }
        //需要调用修复红黑树平衡的方法
        insertFixUp(node);
    }
    /**
     * 插入后修复红黑树平衡的方法
     *     |---情景1：红黑树为空树 -->将节点染成黑色。
     *     |---情景2：插入节点的key已经存在 -->不需要处理。
     *     |---情景3：插入节点的父节点为黑色  -->因为所插入的路径，黑色节点没有变化，所以红黑树依然平衡，所以不用处理。
     *
     *     情景4 需要咱们去处理
     *     |---情景4：插入节点的父节点为红色
     *          |---情景4.1：叔叔节点存在，并且为红色（父-叔 双红） -->将爸爸和叔叔节点染为黑色，将爷爷染为红色，并且再以爷爷节点为当前节点，进行下一轮处理。
     *          |---情景4.2：叔叔节点不存在，或者为黑色，父节点为爷爷节点的左子树
     *               |---情景4.2.1：插入节点为其父节点的左子节点（LL情况） -->将爸爸节点染成黑色，将爷爷节点染成红色，然后以爷爷节点进行右旋，完成。
     *               |---情景4.2.2：插入节点为其父节点的右子节点（LR情况） -->以爸爸节点进行一次左旋，得到LL双红情形(4.2.1),然后指定爸爸节点为当前节点进行下一轮处理。
     *          |---情景4.3：叔叔节点不存在，或者为黑色，父节点为爷爷节点的右子树
     *               |---情景4.3.1：插入节点为其父节点的右子节点（RR情况） -->将爸爸节点染成黑色，将爷爷节点染成红色，然后以爷爷节点进行左旋，完成。
     *               |---情景4.3.2：插入节点为其父节点的左子节点（RL情况） -->以爸爸节点进行一次右旋，得到RR双红情形(4.3.2),然后指定爸爸节点为当前节点进行下一轮处理。
     */
    private void insertFixUp(RBNode node){
        this.root.setColor(BLACK);

        RBNode parent = parentOf(node);
        RBNode gparent = parentOf(parent);//node的爷爷
        //情景4：插入节点的父节点为红色
        if (parent != null && isRed(parent)){
            //如果父节点是红色，那么一定存在爷爷节点，因为根节点不可能是红色。
            RBNode uncle = null;
            if (parent == gparent.left){//父节点为爷爷节点的左子节点
                uncle = gparent.right;
                //父-叔 双红
                if (uncle != null && isRed(uncle)){
                    //将爸爸和叔叔节点染为黑色，将爷爷染为红色，并且再以爷爷节点为当前节点，进行下一轮处理。
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixUp(gparent);
                    return;
                }
                //叔叔节点不存在，或者为黑色，
                if (uncle ==null || isBlack(uncle)){
                    //情景4.2.1：插入节点为其父节点的左子节点（LL情况）
                    if (node == parent.left){
                        setBlack(parent);
                        setRed(gparent);
                        rightRotate(gparent);
                        return;
                    }
                    //情景4.2.2：插入节点为其父节点的右子节点（LR情况）-->以爸爸节点进行一次左旋，得到LL双红情形(4.2.1),然后指定爸爸节点为当前节点进行下一轮处理。
                    if (node == parent.right){
                        leftRotate(parent);
                        insertFixUp(parent);
                        return;
                    }
                }
            }else {//父节点为爷爷节点的右子节点
                uncle = gparent.left;
                //情景4.1：父-叔 双红
                if (uncle != null && isRed(uncle)){
                    //将爸爸和叔叔节点染为黑色，将爷爷染为红色，并且再以爷爷节点为当前节点，进行下一轮处理。
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixUp(gparent);
                    return;
                }
                //情景4.3：叔叔节点不存在，或者为黑色
                if (uncle ==null || isBlack(uncle)){
                    //情景4.3.1：插入节点为其父节点的右子节点（RR情况） -->将爸爸节点染成黑色，将爷爷节点染成红色，然后以爷爷节点进行左旋，完成。
                    if (node == parent.right){
                        setBlack(parent);
                        setRed(gparent);
                        leftRotate(gparent);
                        return;
                    }
                    //情景4.3.2：插入节点为其父节点的左子节点（RL情况）
                    // -->以爸爸节点进行一次右旋，得到RR双红情形(4.3.2),然后指定爸爸节点为当前节点进行下一轮处理。
                    if (node == parent.left){
                       rightRotate(parent);
                       insertFixUp(parent);
                       return;
                    }
                }
            }
        }
    }




    static class RBNode <K extends Comparable<K>,V> {
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private K key;
        private V value;

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode() {
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public RBNode getRight() {
            return right;
        }

        public boolean isColor() {
            return color;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}

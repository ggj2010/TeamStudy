package com.team.gaoguangjin.acm.node;

/**
 * @author:gaoguangjin
 * @date:2018/7/13
 */
public class TwoTree {
    Node root=null;

    public static void main(String[] args) {
        TwoTree twoTree=new TwoTree();
    }
    // 任何节点的键值一定大于其左子树中的每一个节点的键值，并小于其右子树中的每一个节点的键值。
    public void insert(int data){
        Node newNode = new Node(data);
        if(root==null){
            root=newNode;
        }else{
            Node current=root;
            Node parent=null;
            while(true){
                parent=current;
                if(data<(int)current.data){
                    current= current.left;
                    if(current==null){
                        parent.left=newNode;
                        break;
                    }
                }else{
                    current=current.right;
                    if(current==null){
                        parent.right=newNode;
                        break;
                    }
                }
            }
        }
    }
}

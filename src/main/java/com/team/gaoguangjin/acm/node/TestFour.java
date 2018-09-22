package com.team.gaoguangjin.acm.node;

/**
 * @author:gaoguangjin
 * @date:2018/8/17
 */
public class TestFour {

    Node root=null;
    public static void main(String[] args) {
        TestFour testFour=new TestFour();
        testFour.add(4);
        testFour.add(6);
        testFour.add(7);
        testFour.add(1);
        testFour.add(5);
        testFour.add(2);
        testFour.add(3);
        System.out.println(testFour);
    }
    //二叉树排序特点的就是，节点值大于左侧树所有节点值，小于右侧树所有节点值。
    private void add(int i) {
        Node newNode=new Node(i);
        if(root==null){
            root=newNode;
        }else{
            Node parentNode=root;
         //因为要和所有节点都比较，所以要循环
            while (true){
                //节点大于，那就是需要比较右子树
                if((int)newNode.data>(int)parentNode.data){
                    //右子树为空直接赋值
                    if(parentNode.right==null){
                        parentNode.right=newNode;
                        break;
                    }else{
                        //右子树不为空，那就需要继续判断
                        parentNode=parentNode.right;
                    }
                }else{
                 //节点小于，就是比较左子树
                    if(parentNode.left==null){
                        parentNode.left=newNode;
                        break;
                    }else{
                        //右子树不为空，那就需要继续判断
                        parentNode=parentNode.left;
                    }

                }
            }
        }
    }
}

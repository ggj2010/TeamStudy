package com.team.gaoguangjin.acm.node;

/**
 * @author:gaoguangjin
 * @date:2018/7/13
 */
public class Test {
    Node root;

    public static void main(String[] args) {
        Test test=new Test();
        test.insert(1);
        test.insert(3);
        test.insert(5);
        test.insert(6);
        test.insert(2);
        test.insert(7);
        test.insert(4);

        System.out.println(test);
    }
    //二叉排序树，任何节点的值大于左子树每个节点的值，小于右子树每个节点的值
    public void insert(int iData ){	//插入新的节点
        Node newNode = new Node(iData);
        if(root == null){		//树为空，把第一个节点置为根节点
            root = newNode;
        }else{ //不为空
            Node<Integer> current = root;	//声明啷个指向root的引用
            Node<Integer> parent = root;
            while (true){
                parent = current;
                if(iData < current.data){   //待插入的数值小于当前节点的值
                    current = current.left;  //把current指向当前节点的左孩子
                    if (current == null){
                        parent.left = newNode;
                        return;
                    }
                }else {
                    current = current.right;   //待插入的数值大于当前节点的值
                    if (current == null){
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }


    public void preOrder(Node node){
        if(node!=null){
            node.displsyndoe();
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void middleOrder(Node node){
        if(node!=null){
            middleOrder(node.left);
            node.displsyndoe();
            middleOrder(node.right);
        }
    }

    public void postOrder(Node node){
        if(node!=null){
            postOrder(node.left);
            postOrder(node.right);
            node.displsyndoe();
        }
    }


}

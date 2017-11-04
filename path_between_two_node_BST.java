package whatfixinterview;
import java.util.*;
class BSTNode{
    BSTNode left, right;
    int data;
    public BSTNode(int data){
        left = null;
        right = null;
        this.data = data;
    }
    
    public int getData(){
        return data;
    }
}
class BST{
    private BSTNode root;
    public void insert(int data){
        root = insertNode(root, data);
    }
    private BSTNode insertNode(BSTNode root, int data){
        if(root == null)
            root = new BSTNode(data);
        else{
            if(data<=root.getData())
                root.left = insertNode(root.left, data);
            else
                root.right = insertNode(root.right, data);
        }
        return root;
    }
    
    public static void inorder(BSTNode r){
        if(r == null)
            return;
        inorder(r.left);
        System.out.print(r.data + " ");
        inorder(r.right);
    }
    public void inorder(){
        inorder(root);
    }
    static ArrayList<Integer> path = new ArrayList<Integer>();
    public static void printLeft(BSTNode r, int n1){
        if(r==null) return;
        if(r.data == n1) return;
        if(n1 < r.data){
            printLeft(r.left, n1);
            path.add(r.data);
        }
        if(n1 > r.data){
            printLeft(r.right, n1);
            path.add(r.data);
        }         
    }
    public static void printarr(){
        //Collections.sort(path);
        for(int k : path){
            System.out.print(k + " ");
        }
    }

    
    public static void printRight(BSTNode r, int n2){
        if(r==null) return;
        while(true){
            if(r.data == n2) break;
            if(n2 > r.data)
                r = r.right;
            
            else
                r = r.left;
            System.out.print(r.data +  " ");
        }
    }
    public static BSTNode lca(BSTNode root, int n1, int n2)
    {
        if (root == null) return null;
        if (root.data > n1 && root.data > n2)
            return lca(root.left, n1, n2);
        if (root.data < n1 && root.data < n2)
            return lca(root.right, n1, n2);

        return root;
    }
    static void printPath(BSTNode root, int n1, int n2){
        BSTNode r1 = lca(root, n1, n2);
        path.add(n1);
        printLeft(r1, n1);
        printarr();
        printRight(r1, n2);
    }
    public void printPathUtil(int n1, int n2){
        printPath(root, n1, n2);
    }
    
}


public class WhatfixInterview {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        //int arr[] = {30, 16, 12,  19, 17, 3, 45, 35, 40, 50};
        int arr[] = {30, 20, 19, 50, 45, 17, 1, 35, 40};
        int length = arr.length;
        
        BST bst = new BST();
        for(int i=0; i<length; i++){
            bst.insert(arr[i]);
        }
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        if(n1<n2)
            bst.printPathUtil(n1, n2);
        else
            bst.printPathUtil(n2, n1);
        //bst.inorder();
    }
}

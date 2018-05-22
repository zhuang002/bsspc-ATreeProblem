/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atreeproblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class ATreeProblem {


    static Scanner sc = new Scanner(System.in);
    static TreeNode root = new TreeNode(1);
    static Map<Integer,TreeNode> allNodes=new HashMap<>();

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO code application logic here
        allNodes.put(1, root);
        inputData();
        System.out.println(root.numOfSubtrees());
    }

    private static void inputData() {
        int n=sc.nextInt();
        for (int i=1;i<n;i++) {
            int v=sc.nextInt();
            TreeNode parent;
            TreeNode child;
            if (allNodes.containsKey(v)) {
                parent=allNodes.get(v);
            } 
            else {
                parent=new TreeNode(v);
                allNodes.put(v,parent);
            }
            if (allNodes.containsKey(i+1)) {
                child=allNodes.get(i+1);
            }
            else {
                child=new TreeNode(i+1);
                allNodes.put(i+1,child);
            }
            parent.addChild(child);
        }
    }
    
}

class TreeNode {
    int id;
    ArrayList<TreeNode> children =new ArrayList<>();
    int numOfSubtreesWithMeBackup = -1;
    int numOfSubtreesWithoutMeBackup = -1;
    
    public TreeNode(int v) {
        this.id=v;
    }

    public int numOfSubtrees() {
        return numOfSubtreesWithMe()+numOfSubtreesWithoutMe();
    }

    void addChild(TreeNode child) {
        this.children.add(child);
    }

    private int numOfSubtreesWithMe() {
        if (this.numOfSubtreesWithMeBackup!=-1) return this.numOfSubtreesWithMeBackup;
        if (this.children.isEmpty()) {
            this.numOfSubtreesWithMeBackup=1;
            return 1;
        }
        int num=1;
        for (TreeNode subtree:this.children) {
            num*=subtree.numOfSubtreesWithMe()+1;
        }
        this.numOfSubtreesWithMeBackup=num;
        return num;
    }

    private int numOfSubtreesWithoutMe() {
        if (this.numOfSubtreesWithoutMeBackup!=-1)
            return this.numOfSubtreesWithoutMeBackup;
        if (this.children.isEmpty()) {
            this.numOfSubtreesWithoutMeBackup=0;
            return 0;
        }
        int num=0;
        for (TreeNode subtree:this.children) {
            num+=subtree.numOfSubtrees();
        }
        this.numOfSubtreesWithoutMeBackup=num;
        return num;
    }
}

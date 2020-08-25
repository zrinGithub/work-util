package leetcode;

import java.util.*;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/24 11:37
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * [5,4,8,11,null,13,4,7,2,null,null,5,1]
 * 22
 */
class Solution {
    //看了答案，使用广度优先来迭代
    //因为后续遍历是左、右、根，所以每一层从右至左向栈里面压数据
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<TreeNode> track = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;

        track.addLast(root);
        while (!track.isEmpty()) {
            TreeNode node = track.pollLast();
            res.addFirst(node.val);
            if (node.left != null) {
                track.addLast(node.left);
            }
            if (node.right != null) {
                track.addLast(node.right);
            }
        }
        return res;
    }
}


public class SumTwo {
    public static void main(String[] args) {
//        new Solution().pathSum(new TreeNode(5), 5);
    }
}

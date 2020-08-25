package leetcode;

import java.util.*;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/24 11:37
 */
//class ListNode {
//    int val;
//    ListNode next;
//
//    ListNode(int x) {
//        val = x;
//    }
//}

class SolutionA {
    //保存结果
    List<List<Integer>> result = new ArrayList<>();

    //获取权排列
    public List<List<Integer>> permutation(int[] nums) {
        //轨迹
        LinkedList<Integer> track = new LinkedList<>();

        //回溯
        backtrack(nums, track);
        return result;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track) {
        //结束条件：到达决策树尾
        if (track.size() == nums.length) {
            //这里需要复制，因为track是一个引用，不能直接使用引用
            result.add(new LinkedList<>(track));
            return;
        }

        //遍历选择列表
        for (int num : nums) {
            if (!track.contains(num)) {
                //做选择
                track.add(num);
                //回溯
                backtrack(nums, track);
                //撤销选择
                track.removeLast();
            }
        }
    }

}

public class SumResult {
    public static void main(String[] args) {
        List<List<Integer>> lists = new SolutionA().permutation(new int[]{1, 2, 3});
        System.out.println(lists);
    }
}

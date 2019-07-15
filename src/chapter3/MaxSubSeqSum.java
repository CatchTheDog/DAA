package chapter3;

/**
 * @author majq
 * @since 2019/07/02 21:03
 * 最大子序列和问题
 */
public class MaxSubSeqSum {
	/**
	 * Cubic maximum contiguous subsequence sum algrithom
	 * complexity O(N^3)
	 *
	 * @param a sequence
	 * @return maximum subsequence sum
	 */
	public static int maxSubSum1(int[] a) {
		int maxSum = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j < a.length; j++) {
				int thisSum = 0;
				for (int k = i; k <= j; k++) {
					thisSum += a[k];
				}
				if (thisSum > maxSum) maxSum = thisSum;
			}
		}
		return maxSum;
	}

	/**
	 * Quadratic maximum contiguous subsequences sum algorithm
	 * complexity O(N^2)
	 *
	 * @param a sequence
	 * @return maximum subsequence sum
	 */
	public static int maxSubSum2(int[] a) {
		int maxSum = 0;
		for (int i = 0; i < a.length; i++) {
			int sum = 0;
			for (int j = i; j < a.length; j++) {
				sum += a[j];
			}
			if (sum > maxSum) maxSum = sum;
		}
		return maxSum;
	}

	/**
	 * Recursive maximum contiguous subsequence sum algorithm.
	 * Finds maximum sum in subarray spanning a[left ... right].
	 * Does not attempt to maintain actual best sequence.
	 * complexity O(NlogN)
	 *
	 * @param a sequence
	 * @return maximum subsequence sum
	 */
	public static int maxSumRec(int[] a, int left, int right) {
		if (left == right)
			if (a[left] > 0)
				return a[left];
			else
				return 0;

		int center = (left + right) / 2;
		int maxLeftSum = maxSumRec(a, left, center);
		int maxRightSum = maxSumRec(a, center + 1, right);

		int maxLeftBorderSum = 0, leftBorderSum = 0;
		for (int i = center; i > left; i--) {
			leftBorderSum += a[i];
			if (leftBorderSum > maxLeftBorderSum)
				maxLeftBorderSum = leftBorderSum;
		}

		int maxRightBorderSum = 0, rightBorderSum = 0;
		for (int i = center; i < right; i++) {
			rightBorderSum += a[i];
			if (rightBorderSum > maxRightBorderSum)
				maxRightBorderSum = rightBorderSum;
		}

		return max(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
	}

	/**
	 * 求输入最大值
	 *
	 * @param args 输入序列，此方法仅处理输入元素为int类型的序列
	 * @return 序列元素最大值
	 */
	public static int max(int... args) {
		if (null != args && args.length > 0) {
			if (args.length == 1) return args[0];
			else {
				int max = 0;
				for (int x : args) {
					if (x > max) max = x;
				}
				return max;
			}
		}
		return Integer.MIN_VALUE;
	}

	/**
	 * Driver for divide-and-conquer maximum contiguous
	 * subsequence sum algorithm.
	 *
	 * @param a 输入数组序列
	 * @return 最大子序列和
	 */
	public static int maxSubSum3(int[] a) {
		return maxSumRec(a, 0, a.length - 1);
	}

	/**
	 * Linear-time maximum contigous subsequence sum algorithm.
	 * time complexity: O(N)
	 *
	 * @param a input sequence as an array
	 * @return maximum subsequence sum.
	 */
	public static int maxSubSum4(int[] a) {
		int maxSum = 0, thisSum = 0;
		for (int j = 0; j < a.length; j++) {
			thisSum += a[j];
			if (thisSum > maxSum) {
				maxSum = thisSum;
			} else if (thisSum < 0) {
				thisSum = 0;
			}
		}
		return maxSum;
	}
}


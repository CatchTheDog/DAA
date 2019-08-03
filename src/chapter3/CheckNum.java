package chapter3;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目：
 * 一个七位正整数，各位数字均不重复。
 * 此整数分别乘以2、乘以3、乘以4、乘以5、乘以6后所得数都是七位整数且该均是该整数各个位上数字的排列组合中一种；
 * 请找出这样的七位数。
 */
public class CheckNum {
	public static void main(String[] args) {
		for (int i = 1000000; i <= 1666667; i++) {
			int a1 = i * 2;
			int a2 = i * 3;
			int a3 = i * 4;
			int a4 = i * 5;
			int a5 = i * 6;
			if (check(i, a1, a2, a3, a4, a5))
				System.out.println(i);
		}
	}

	/**
	 * 获取正整数各个位上的数字集合
	 *
	 * @param num 正整数
	 * @param set 正整数各个位上数字的集合
	 */
	public static void get(int num, Set<Integer> set) {
		if (num < 10) {
			set.add(num);
			return;
		} else {
			int x = num % 10;
			set.add(x);
			int y = num / 10;
			get(y, set);
		}
	}

	/**
	 * 检测当前数字是否满足题目所述条件
	 *
	 * @param i    该整数
	 * @param numa 乘以2所得数
	 * @param numb 乘以3所得数
	 * @param numc 乘以4所得数
	 * @param numd 乘以5所得数
	 * @param nume 乘以6所得数
	 * @return true:该数满足条件 false:不满足条件
	 */
	public static boolean check(int i, int numa, int numb, int numc, int numd, int nume) {
		Set<Integer> a = new HashSet<>(),
				b = new HashSet<>(),
				c = new HashSet<>(),
				d = new HashSet<>(),
				e = new HashSet<>(),
				f = new HashSet<>();
		get(i, a);
		get(numa, b);
		get(numb, c);
		get(numc, d);
		get(numd, e);
		get(nume, f);
		if (b.size() == 7 && c.size() == 7 && d.size() == 7 && e.size() == 7 && f.size() == 7) {
			b.removeAll(a);
			c.removeAll(a);
			d.removeAll(a);
			e.removeAll(a);
			f.removeAll(a);
			return b.size() == 0 && c.size() == 0 && d.size() == 0 && e.size() == 0 && f.size() == 0;
		}
		return false;
	}

}

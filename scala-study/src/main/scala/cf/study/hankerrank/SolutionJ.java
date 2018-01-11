package cf.study.hankerrank;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by fan on 2016/9/23.
 */
public class SolutionJ {

	static long sum = 0;

	static long distSumToRoot(long lv) {
		long sum = 0;
		for (long i = 0; i <= lv; i++) sum += i;
		return sum;
	}

	static class TN {
		static TN root = null;

		int v;
		int lv;
		TN left;
		TN right;
		TN parent;

		int numOfLeft = 0;
		long distSumOfLeft = 0;
		int numOfRight = 0;
		long distSumOfRight = 0;

		TN(int _v, int _lv) {
			this.v = _v;
			this.lv = _lv;

			calculate();
		}

		TN(int _v, TN _parent) {
			this(_v, _parent.lv + 1);
			this.parent = _parent;
		}

		private void updateParents() {
			for (TN node = this; node.parent != null; node = node.parent) {
				if (node.v > node.parent.v) {
					node.parent.numOfRight += 1;
				} else {
					node.parent.numOfLeft += 1;
				}

				if (this.v > node.parent.v) {
					node.parent.distSumOfRight += this.lv - node.parent.lv;
				} else {
					node.parent.distSumOfLeft += this.lv - node.parent.lv;
				}
			}
		}

		private void calculate() {
			if (root != null) {
				for (TN node = this; node.parent != null; node = node.parent) {
					if (v > node.parent.v) {
						sum += node.parent.distSumOfLeft + node.parent.numOfLeft * (this.lv - node.parent.lv);
					} else if (v < node.parent.v) {
						sum += node.parent.distSumOfRight + node.parent.numOfRight * (this.lv - node.parent.lv);
					}
				}
				sum += distSumToRoot(this.lv);
				updateParents();
			}
		}

		void addNode(int _v) {
			TN node = this;

			do {
			if (_v > node.v) {
				if (node.right != null) {
					node = node.right;
				} else {
					node.right = new TN(_v, node);
					return;
				}
			} else {
				if (node.left != null) {
					node = node.left;
				} else {
					node.left = new TN(_v, node);
					return;
				}
			}} while (true);
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
//		Scanner scan = new Scanner(new FileInputStream(new File("input12.txt")));
		int n = scan.nextInt();

		TN.root = new TN(scan.nextInt(), 0);
		System.out.println(sum);

		int[] arr = new int[n];
		for (int i = 1; i < n; i++) {
			arr[i] = scan.nextInt();
		}

		for (int i = 1; i < n; i++) {
			TN.root.addNode(arr[i]);
			System.out.println(sum);
		}
	}

}

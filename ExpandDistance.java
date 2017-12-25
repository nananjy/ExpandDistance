package 包名;

import java.util.Scanner;

/**
 * 给定字符串A和B，计算字符串A和B的最小扩展距离。
 * 扩展：在字符串A中插入若干空格字符所产生的字符串。
 * 距离：对于长度相同的两个字符串A和B，其距离定义为相应位置字符距离之和。
 * （两个非空格字符的距离是它们的ASCII码之差的绝对值；空格与空格的距离为0，空格与其他字符的距离为一定值k(k>0)。）
 * 扩展距离：在字符串A和B的所有长度相同的扩展中，有一对距离最小的扩展，该距离称为字符串A和B的扩展距离。
 * @author 作者名
 */
public class ExpandDistance {

	private static String A;//字符串A
	private static String B;//字符串B
	private static Integer k;//空格与其他字符的距离k(k>0)
	private int minExpandDistance, expDistance, aPlace, bPlace;//最小扩展距离，扩展距离以及字符串A, B中匹配空格的字符个数
	
	/**
	 * 输入字符串A, B和距离定值k的构造函数
	 * @param A 待赋值的字符串A
	 * @param B 待赋值的字符串B
	 * @param k 待赋值的距离定值k
	 */
	public ExpandDistance(String A, String B, Integer k) {
		ExpandDistance.A = A;
		ExpandDistance.B = B;
		ExpandDistance.k = k;
		this.aPlace = A.length();//初始化字符串A中字符匹配空格的个数
		this.bPlace = B.length();//初始化字符串B中字符匹配空格的个数
		this.minExpandDistance = k * (aPlace + bPlace);//初始化最小扩展距离
		this.expDistance = 0;//初始化扩展距离
	}

	/**
	 * 获取字符串A和B的扩展距离
	 * 递归，注意判断字符串中的空格
	 */
	public void getExpandDistance(int index_A, int index_B) {
		//字符串A和B分别从index_A和index_B的位置开始，进行字符的ASCII码比较
		for (int i = index_A; i < A.length(); i++) {
			for (int j = index_B; j < B.length(); j++) {
				//判断待比较的两字符是否为空格，1 若都不是空格，计算两字符之差的绝对值，注意：不用比较两字符之差的绝对值与定值k, (A=" w ", B="as")
				if (A.charAt(i) != ' ' && B.charAt(j) != ' ') {
					int distance = Math.abs(A.charAt(i) - B.charAt(j));//保存两字符的距离
					expDistance += distance;//将字符之差绝对值加到扩展距离
					this.aPlace--; this.bPlace--;//将字符串A, B中匹配空格的字符个数减一
					getExpandDistance(i +1, j +1);//递归
					expDistance -= distance;//当前两字符之差绝对值对应的扩展距离已经计算完成，将其从扩展距离中减去
					this.aPlace++; this.bPlace++;//将字符串A, B中匹配空格的字符个数加一
				//2 若都是空格，则两字符之差为0
				} else if (A.charAt(i) == ' ' && B.charAt(j) == ' ') {
					this.aPlace--; this.bPlace--;
					getExpandDistance(i +1, j +1);//递归
					this.aPlace++; this.bPlace++;
				//3 若A在index_A处或B在index_B处有一个为空格，则字符之差为k(k>0)
				} else {
					expDistance += k;//将定值k加到扩展距离
					this.aPlace--; this.bPlace--;
					getExpandDistance(i +1, j +1);//递归
					expDistance -= k;//包含当前两字符距离k对应的扩展距离已经计算完成，将其从扩展距离中减去
					this.aPlace++; this.bPlace++;
				}
			}
			//4.1 若字符串B的长度小于字符串A的长度，判断A中剩余字符中是否有空格
			if (index_A < A.length() && A.charAt(index_A) == ' ') {
				this.aPlace--;//空格与空格间距离为0
				getExpandDistance(index_A +1, index_B);//递归
				this.aPlace++;//包含两空格的扩展距离已计算完成，将字符串A中匹配空格的字符个数加一
			}
		}
		//4.2 若字符串B的长度大于字符串A的长度，判断B中剩余字符中是否有空格
		if (index_B < B.length() && B.charAt(index_B) == ' ') {
			this.bPlace--;//空格与空格间距离为0
			getExpandDistance(index_A, index_B +1);//递归
			this.bPlace++;//包含两空格的扩展距离已计算完成，将字符串B中匹配空格的字符个数加一
		}
		//5 比较当前扩展距离与最小距离，若扩展距离小，则更新最小距离
		if (this.aPlace < A.length() || this.bPlace < B.length()) {
			expDistance += k * (aPlace + bPlace);
			System.out.println("A匹配空格个数：" + this.aPlace + "，B匹配空格个数：" + this.bPlace + ",扩展距离为：" + expDistance);
			if (expDistance < minExpandDistance) {
				this.minExpandDistance = expDistance;
			}
			expDistance -= k * (aPlace + bPlace);
		}
	}
	
	/**
	 * 获取控制台输入的字符串A和字符串B
	 * @param args
	 */
	public static void main(String[] args) {
		String A, B;//待赋值的两字符串
		int k;//待赋值的定值
		//1 获取控制台输入的字符串A
		Scanner scanner = new Scanner(System.in);
		System.out.println("Merry Christmas~");
		System.out.println("请输入字符串A，然后按回车键结束：");
		String string = scanner.nextLine();
		while (string == null || string.equals("")) {
			System.out.println("没有输入任何字符，请重输字符串A：");
			string = scanner.nextLine();
		}
		A = string;//将输入的字符串赋值给A
		//2 获取控制台输入的字符串B
		System.out.println("请输入字符串B，然后按回车键结束：");
		string = scanner.nextLine();
		while (string == null || string.equals("")) {
			System.out.println("没有输入任何字符，请重输字符串B：");
			string = scanner.nextLine();
		}
		B = string;//将输入的字符串赋值给B
		//3 获取空格与其他字符的距离k(k>0)
		System.out.println("请输入空格与其他字符的距离k（正整数），然后按回车键结束");
		string = scanner.nextLine();
		while (!string.matches("^[0-9]*[1-9][0-9]*$")) {//0*[1-9]\\d*
			System.out.println("输入错误，输入为一个正整数，请重输k：");
			string = scanner.nextLine();
		}
		k = Integer.parseInt(string);//将输入的正整数赋值给k
		System.out.println("字符串A为" + A + "\n字符串B为" + B + "\n空格与其他字符的距离为：" + k);
		scanner.close();//关闭输入扫描器
		//4 调用构造函数，初始化变量
		ExpandDistance expandDistance = new ExpandDistance(A, B, k);
		//5 调用计算扩展距离的方法，从字符串A和B的起始位置开始计算
		expandDistance.getExpandDistance(0, 0);
		//6 输出长度相同的字符扩展中，最小的扩展距离
		System.out.println("字符串A和B，在长度相同的字符扩展中，最小的扩展距离为：" + expandDistance.minExpandDistance);
	}
}

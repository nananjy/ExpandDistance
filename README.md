# ExpandDistance

问题描述：
 * 给定字符串A和B，计算字符串A和B的最小扩展距离。
 * 扩展：在字符串A中插入若干空格字符所产生的字符串。
 * 距离：对于长度相同的两个字符串A和B，其距离定义为相应位置字符距离之和。
 * （两个非空格字符的距离是它们的ASCII码之差的绝对值；空格与空格的距离为0，空格与其他字符的距离为一定值k(k>0)。）
 * 扩展距离：在字符串A和B的所有长度相同的扩展中，有一对距离最小的扩展，该距离称为字符串A和B的扩展距离。

算法流程（伪代码）：
 1. 从控制台获取字符串A, 字符串B, 以及空格与其他字符的距离k。
 2. 输入：字符串A, B, k;
 3. 输出：最小扩展距离minExpandDistance.
 4. 初始值：minExpandDistance = k * (A.length + B.length);
 5. Func getExpandDistance(index_A, index_B) {
 6. &emsp;for(int i = index_A; i < A.length(); i++) {
 7. &emsp;&emsp;for(int j = index_B; j < B.length(); j++) {
 8. &emsp;&emsp;&emsp;判断字符串A和B在i和j位置处是否为空格，若都不是空格，计算两字符ASCII码之差的绝对值
 9. &emsp;&emsp;&emsp;&emsp;expDistance += Math.abs(A(i) - B(j));
 10. &emsp;&emsp;&emsp;&emsp;Func getExpandDistance(i +1, j +1);//递归
 11. &emsp;&emsp;&emsp;&emsp;expDistance -= Math.abs(A(i) - B(j));
 12. &emsp;&emsp;&emsp;若都是空格，空格间距离为0
 13. &emsp;&emsp;&emsp;&emsp;Func getExpandDistance(i +1, j +1);//递归
 14. &emsp;&emsp;&emsp;若有一个是空格，距离为k
 15. &emsp;&emsp;&emsp;&emsp;expDistance += k;
 16. &emsp;&emsp;&emsp;&emsp;Func getExpandDistance(i +1, j +1);//递归
 17. &emsp;&emsp;&emsp;&emsp;expDistance -= k;
 18. &emsp;&emsp;}
 19. &emsp;&emsp;比较字符串A和B的长度，若B的长度小于A的长度，判断A中剩余字符中是否有空格，若有空格，空格间距离为0
 20. &emsp;&emsp;&emsp;Func getExpandDistance(index_A +1, index_B);//递归
 21. &emsp;}
 22. &emsp;若字符串B的长度大于A的长度，判断B中剩余字符中是否有空格，若有空格，空格间距离为0
 23. &emsp;&emsp;Func getExpandDistance(index_A, index_B +1);//递归
 24. &emsp;若minExpandDistance > expDistance
 25. &emsp;&emsp;minExpandDistance = expDistance;
 26. }

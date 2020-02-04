/* POJ 1661 Help Jimmy */
/*	"Help Jimmy" 是在下图所示的场景上完成的游戏。（图略）
*	场景中包括多个长度和高度各不相同的平台。地面是最低的平台，高度为零，长度无限。
*	Jimmy老鼠在时刻0从高于所有平台的某处开始下落，它的下落速度始终为1米/秒。当Jimmy落到某个平台上时，游戏者选择让它向左还是向右跑，它跑动的速度也是1米/秒。
*	当Jimmy跑到平台的边缘时，开始继续下落。Jimmy每次下落的高度不能超过MAX米，不然就会摔死，游戏也会结束。
*	设计一个程序，计算Jimmy到底地面时可能的最早时间。
*/
//Input:
//第一行是测试数据的组数t（0 <= t <= 20）。每组测试数据的第一行是四个整数N，X，Y，MAX，用空格分隔。
//N是平台的数目（不包括地面），X和Y是Jimmy开始下落的位置的横竖坐标，MAX是一次下落的最大高度。
//接下来的N行每行描述一个平台，包括三个整数，X1[i]，X2[i]和H[i]。H[i]表示平台的高度，X1[i]和X2[i]表示平台左右端点的横坐标。
//1 <= N <= 1000，-20000 <= X, X1[i], X2[i] <= 20000，0 < H[i] < Y <= 20000（i = 1..N）。所有坐标的单位都是米。

#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

struct board {
	int x1;
	int x2;
	int h;
	board():
		x1(0),x2(0),h(0){ }
};

bool comp(const board &b1, const board &b2);		//自定义比较函数
int boardBelow(int curr,vector<board> v,int x,int max);		//判断当前位置下方是否有板子

int main() {
	int t = 0;
	const int INF = 0x3f3f3f3f;	//用于表示∞
	int n, x, y, max;
	cin >> t;					//t是测试数据的组数
	for (int t1 = 0; t1 < t; t1++) {
		cin >> n >> x >> y >> max;
		vector<int> leftMinTime(n + 1, 0);
		vector<int> rightMinTime(n + 1, 0);
		vector<board> boards(n + 1, board());	
		for (int i = 1; i <= n; i++) {
			cin >> boards[i].x1;
			cin >> boards[i].x2;
			cin >> boards[i].h;
		}
		boards[0].x1 = x;
		boards[0].x2 = x;
		boards[0].h = y;								//0号板子的高度即为Jimmy的初始高度
		sort(boards.begin() + 1, boards.end(), comp);		//将板子按照高度进行降序排序
		for (int i = n; i >= 0; i--) {
			//计算leftMinTime
			int below = boardBelow(i, boards, boards[i].x1, max);
			if (below == -1) {								//左端正下方没有板子
				if (boards[i].h > max) {
					leftMinTime[i] = INF;
				}
				else {
					leftMinTime[i] = boards[i].h;
				}
			}
			else {
				leftMinTime[i] = boards[i].h - boards[below].h +
					min(leftMinTime[below] + boards[i].x1 - boards[below].x1,
						rightMinTime[below] + boards[below].x2 - boards[i].x1);
			}
			//计算rightMinTime
			below = boardBelow(i, boards, boards[i].x2, max);
			if (below == -1) {
				if (boards[i].h > max) {
					rightMinTime[i] = INF;
				}
				else {
					rightMinTime[i] = boards[i].h;
				}
			}
			else {
				rightMinTime[i] = boards[i].h - boards[below].h +
					min(leftMinTime[below] + boards[i].x2 - boards[below].x1,
						rightMinTime[below] + boards[below].x2 - boards[i].x2);
			}
		}	//i-loop
		cout << leftMinTime[0] << endl;
	}	//t1-loop
	return 0;
}

bool comp(const board &b1, const board &b2) {
	return b1.h > b2.h;							//降序排序
}

int boardBelow(int curr, vector<board> v, int x,int max) {
	for (int i = 1; i < v.size(); i++) {
		if (i == curr) { continue; }
		if (x >= v[i].x1&&x <= v[i].x2&&v[i].h < v[curr].h) {
			if (abs(v[i].h - v[curr].h) <= max) {
				return i;
			}
		}
	}
	return -1;
}

//解题思路：动态规划(DP),求出从每个板子的左端和右端到达地面的最短时间
//假设一开始Jimmy处在长度为0的0号板子上，最终结果即为leftMinTime[0]

/*		伪代码：
if (板子k左端正下方没有其他板子) {
	if (板子k的高度h(k) > MAX) {
		leftMinTime(k) = ∞;
	}
	else {
		leftMinTime(k) = h(k);
	}
else if (板子k左端正下方的板子编号为m) {
	leftMinTime(k) = h(k) - h(m) +
		min(leftMinTime(m) + x1(k) - x1(m), rightMinTime(m) + x2(m) - x1(k));
}
*/

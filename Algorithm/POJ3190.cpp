/* POJ 3190 Stall Reservations */
/*	Description:
Oh those picky N (1 <= N <= 50,000) cows! They are so picky that each one will only be milked over some precise
time interval A..B (1 <= A <= B <= 1,000,000), which includes both times A and B. Obviously, FJ must create a reservation
system to determine which stall each cow can be assigned for her milking time. 
Of course, no cow will share such a private moment with other cows.
Help FJ by determining:
· The minimum number of stalls required in the barn so that each cow can have her private milking period
· An assignment of cows to these stalls over time
Many answers are correct for each test dataset; a program will grade your answer.
*/

/* Input:
Line 1: A single integer, N
Lines 2..N+1: Line i+1 describes cow i's milking interval with two space-separated integers.
*/

/* Output:
Line 1: The minimum number of stalls the barn must have.
Lines 2..N+1: Line i+1 describes the stall to which cow i will be assigned for her milking period.
*/

//解题思路：贪心算法

#define _CRT_SECURE_NO_WARNINGS 
#include <cstdio>
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

struct Cow {
	int begin, end;
	int num;
	bool operator< (const Cow & c) const {
		return begin < c.begin;
	}
}cows[50100];

struct Stall {
	int end;
	int num;
	bool operator< (const Stall & s) const {		//优先队列默认大顶堆，故将 < 重载为 >
		return end > s.end;
	}
	Stall(int e, int n) :
		end(e), num(n) { }
};

int main() {
	int n;
	int total = 0;
	int pos[50010] = { 0 };
	priority_queue<Stall> stalls;
	scanf("%d", &n);
	for (int i = 0; i < n; ++i) {
		scanf("%d%d", &cows[i].begin, &cows[i].end);
		cows[i].num = i;
	}
	sort(cows, cows + n);
	for (int i = 0; i < n; ++i) {
		if (stalls.empty()) {
			++total;
			stalls.push(Stall(cows[i].end, total));
			pos[cows[i].num] = total;
		}
		else {
			Stall st = stalls.top();
			if (st.end < cows[i].begin) {
				stalls.pop();
				pos[cows[i].num] = st.num;
				stalls.push(Stall(cows[i].end, st.num));
			}
			else {
				total++;
				stalls.push(Stall(cows[i].end, total));
				pos[cows[i].num] = total;
			}
		}
	}
	printf("%d\n", total);
	for (int i = 0; i < n; ++i) {
		printf("%d\n", pos[i]);
	}
	return 0;
}

//这题时间卡的太死了，TLE了无数次- -
//首先是改良了算法，用优先队列来存储Stall，使得最先完成的畜栏位于队头
//然后还进行了很多细节的修改，如i++改为++i，cin/cout换为scanf/printf
//最后终于AC了
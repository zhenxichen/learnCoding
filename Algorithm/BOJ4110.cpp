/* 百练OJ 4110 圣诞老人的礼物-Santa Clau’s Gifts */
/*圣诞节来临了，在城市A中圣诞老人准备分发糖果，现在有多箱不同的糖果，每箱糖果有自己的价值和重量，
* 每箱糖果都可以拆分成任意散装组合带走。圣诞老人的驯鹿最多只能承受一定重量的糖果，请问圣诞老人最多能带走多大价值的糖果。
*/
/* 输入：
第一行由两个部分组成，分别为糖果箱数正整数n(1 <= n <= 100)，驯鹿能承受的最大重量正整数w（0 < w < 10000），两个数用空格隔开。
其余n行每行对应一箱糖果，由两部分组成，分别为一箱糖果的价值正整数v和重量正整数w，中间用空格隔开。
*/
//输出：输出圣诞老人能带走的糖果的最大总价值，保留1位小数。输出为一行，以换行符结束。

#define _CRT_SECURE_NO_WARNINGS 
#include <cstdio>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Candy {
	int value;
	int weight;
	Candy(int v,int w):
		value(v),weight(w){ }
};

bool comp(const Candy &c1, const Candy &c2);

int main() {
	int n, w;
	int value, weight;
	int i = 0;
	double total = 0.0;
	cin >> n >> w;
	vector<Candy> candies;
	for (i = 0; i < n; i++) {
		cin >> value;
		cin >> weight;
		candies.push_back(Candy(value, weight));
	}
	sort(candies.begin(), candies.end(), comp);		//将糖果箱按价值/重量比从高到低排序
	weight = 0;										//用以计算总重量
	i = 0;
	while (weight < w && i < n) {
		if (w - weight >= candies[i].weight) {
			total += candies[i].value;
			weight += candies[i++].weight;
		}
		else {
			total += ((w - weight)*1.0 / candies[i].weight) * candies[i].value;
			weight = w;
		}
	}
	printf("%.1f\n", total);
	return 0;
}

bool comp(const Candy &c1, const Candy &c2) {
	double r1 = c1.value / c1.weight;
	double r2 = c2.value / c2.weight;
	return r1 > r2;					//降序排序
}
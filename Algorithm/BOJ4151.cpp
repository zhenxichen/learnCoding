/* 百练OJ 4151 电影节 */
/*大学生电影节在北大举办! 这天，在北大各地放了多部电影，给定每部电影的放映时间区间，
区间重叠的电影不可能同时看（端点可以重合），问李雷最多可以看多少部电影。*/

//思路：贪心算法，优先选择结束时间早的电影

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Movie {
	int begin;
	int end;
	Movie(int a, int b) :
		begin(a), end(b){ }
};

bool comp(const Movie &m1, const Movie &m2) { return m1.end < m2.end; }

int main() {
	int n;
	int i;
	int begin, end;
	vector<Movie> movies;
	while (cin >> n) {
		if (n == 0) { break; }
		int count = 0;
		for (i = 0; i < n; i++) {
			cin >> begin >> end;
			movies.push_back(Movie(begin, end));
		}
		sort(movies.begin(), movies.end(), comp);		//按结束时间排序
		end = 0;
		for (int i = 0; i < n; i++) {
			if (movies[i].begin >= end) {
				end = movies[i].end;
				count++;
			}
		}
		cout << count << endl;
		movies.clear();
	}
	return 0;
}
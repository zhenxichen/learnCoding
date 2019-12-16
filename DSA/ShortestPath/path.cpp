#include "path.h"
#include "graph.cpp"
#include <stack>

void setMap(GraphMatrix<string, string> & map) {
	string place[10] =
	{ "西园七舍","西南门","南门","青春广场","一教","二基楼B座",
		"一号运动场","工训中心","灾后重建学院","一基楼" };		//地点名称
	string pathName[] = { "川大路二段","环形大道西段(1)","长桥",
		"明远大道(1)","环形大道东段(2)","环形大道东段(1)","明远大道(2)",
		"景行路","启明路","启智路","商业街","不知名小路","环形大道西段(2)",
		"筑景路","川大路一段" };												//道路名
	int pathlen[] = { 538,452,702,439,362,588,761,397,
		251,257,419,621,806,633,1400 };											//道路长度
	int pathV1[] = { 1,1,3,4,5,5,5,4,2,9,0,0,3,4,2 };							//道路节点1										
	int pathV2[] = { 2,3,4,5,6,7,8,2,9,6,3,1,7,7,8 };							//道路节点2
	//毕竟题目要求的是自己选定地点，既然如此那就不用文件读取来给自己添加难度了- -
	/*读取地点，以点的形式存入图中*/
	for (int i = 0; i < 10; i++) {
		map.insert(place[i]);
	}
	/*读取道路，以边的方式存入图中*/
	for (int i = 0; i < 15; i++) {						//添加两个方向的道路
		map.insert(pathName[i], pathlen[i], pathV1[i], pathV2[i]);
		map.insert(pathName[i], pathlen[i], pathV2[i], pathV1[i]);
	}
}

void findPath(GraphMatrix<string, string> map, int s, int t) {
	/*标记*/
	map.reset();
	map.priority(s) = 0;
	for (int i = 0; i < map.n; i++) {
		map.status(s) = VISITED;
		if (map.parent(s) != -1) {
			map.type(map.parent(s), s) = TREE;
		}
		for (int j = map.firstNbr(s); j > -1; j = map.nextNbr(s, j)) {						//遍历所有邻居
			if (map.status(j) == UNDISCOVERED && 
				(map.priority(j) > map.priority(s) + map.weight(s, j))) {					//计算优先级
				map.priority(j) = map.priority(s) + map.weight(s, j);
				map.parent(j) = s;
			}
		}
		for (int shortest = INT_MAX, j = 0; j < map.n; j++) {								//选出下一节点
			if ((map.status(j) == UNDISCOVERED) && (map.priority(j) < shortest)) {
				shortest = map.priority(j);
				s = j;
			}
		}
	}
	/*记录路径*/
	int totalLength = 0;								//总长
	stack<int> S;										//记录路径的栈
	int p1 = 0, p2 = 0;									//用以暂存两个点
	S.push(t);
	while (map.parent(t) != -1) {						//仍可追溯到上一节点
		S.push(map.parent(t));
		t = map.parent(t);
	}
	/*输出路径和长度*/
	p1 = S.top();
	S.pop();
	cout << map.vertex(p1) << "->";
	while (!S.empty()) {
		p2 = S.top();
		cout << map.edge(p1, p2) << "->";
		totalLength += map.weight(p1, p2);
		cout << map.vertex(p2);
		if (S.size() > 1) {
			cout << "->";
		}
		else {
			cout << endl;
		}
		p1 = p2;
		S.pop();
	}
	cout << "总长为：" << totalLength << endl;
}

void printPlace() {
	string place[10] =
	{ "西园七舍","西南门","南门","青春广场","一教","二基楼B座",
		"一号运动场","工训中心","灾后重建学院","一基楼" };
	cout << "以下为校内各个地点的编号" << endl;
	for (int i = 0; i < 10; i++) {
		cout << "（" << i + 1 << "） " << place[i] << endl;
	}
}

bool checkInput(int begin, int end, int res) {
	if (res == -1) { return false; }
	if (begin < 1 || begin>10) {
		cout << "输入的起点编号超出范围" << endl;
		return false;
	}
	if (end < 1 || end>10) {
		cout << "输入的终点编号超出范围" << endl;
		return false;
	}
	if (begin == end) {
		cout << "站在原地不动就行啦~" << endl;
		return false;
	}
	return true;
}

int inputNum(int & begin, int & end) {
	cout << "请输入起点编号：";
	cin >> begin;
	if (cin.fail()) {
		cout << "输入了非法内容" << endl;
		return -1;
	}
	cin.clear();
	cin.ignore(INT_MAX, '\n');
	cout << "请输入终点编号：";
	cin >> end;
	if (cin.fail()) {
		cout << "输入了非法内容" << endl;
		return -1;
	}
	return 0;
}

/*目前查错方面只支持以下几种情况*/
//（1）	要求输入数字时输入了字符串
//（2）	输入的数字超出了1-10的范围
//（3）	如果输入的是浮点数会被自动认为是整数继续执行程序
//（4）	如果起点与终点相同会有特定回应

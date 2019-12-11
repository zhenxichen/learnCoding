#ifndef _PATH_H_
#define _PATH_H_

#include "graph.h"
#include <string>
#include <iostream>

GraphMatrix<string, string> setMap();							//将信息录入图中
void findPath(GraphMatrix<string, string> map, int s, int t);	//利用dijkstra算法求最短路径
void printPlace();												//显示地点信息

#endif // !_PATH_H_
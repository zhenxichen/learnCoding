#include "graph.h"

template <typename Tv, typename Te> void GraphMatrix<Tv,Te>::reset() {
	for (int i = 0; i < n; i++) {
		status(i) = UNDISCOVERED;
		dTime(i) = fTime(i) = -1;
		parent(i) = -1;
		priority(i) = INT_MAX;
		for (int j = 0; j < e; j++) {
			if (exists(i, j)) {
				type(i, j) = UNDETERMINED;
			}
		}
	}
}

template <typename Tv, typename Te> int GraphMatrix<Tv, Te>::nextNbr(int i, int j) {
	while (j > -1 && !exists(i, --j));
	return j;
}

template <typename Tv, typename Te> bool GraphMatrix<Tv, Te>::exists(int i, int j) {
	return ((i >= 0) && (i < n) && (j >= 0) && (j < n) && E[i][j] != NULL);
}

template <typename Tv, typename Te> 
void GraphMatrix<Tv, Te>::insert(Te const& edge, int w, int i, int j) {
	if (exists(i, j)) { return; }
	E[i][j] = new Edge<Te>(edge, w);
	e++;
	V[i].outDegree++;
	V[j].inDegree++;
}

template <typename Tv, typename Te> Te GraphMatrix<Tv, Te>::remove(int i, int j) {
	Te eBak = edge(i, j);
	delete E[i][j];
	E[i][j] = NULL;
	e--;
	V[i].outDegree--;
	V[j].inDegree--;
	return eBak;
}

template <typename Tv, typename Te> int GraphMatrix<Tv, Te>::insert(Tv& const v) {
	for (int j = 0; j < n; j++) {
		E[j].push_back(NULL);
	}
	n++;
	vector<Edge<Te>*> v1(n, (Edge<Te>*)NULL);
	E.push_back(v1);
	Vertex<Tv> vertex(v);
	V.push_back(vertex);
	return n;
}

template <typename Tv, typename Te> Tv GraphMatrix<Tv, Te>::remove(int i) {
	for (int j = 0; j < n; j++) {		//所有入边
		if (exists(i, j)) {
			delete E[i][j];
			V[j].inDegree--;
			e--;
		}
	}
	E.erase(E.begin() + i);
	n--;
	Tv v = vertex(i);
	V.erase(V.begin() + i);
	for (int j = 0; j < n; j++) {		//所有出边
		if (Edge<Te>* x = E[j][i]) {					
			E[j].erase(E[j].begin() + i);			//删除代表i的一行
			delete x;
			V[j].outDegree--;
			e--;
		}
	}
	return v;
}

template <typename Tv, typename Te> void GraphMatrix<Tv, Te>::dijkstra(int s) {
	reset();
	priority(s) = 0;
	for (int i = 0; i < n; i++) {
		status(s) = VISITED;
		if (parent(s) != -1) {
			type(parent(s), s) = TREE;
		}
		for (int j = firstNbr(s); j > -1; j = nextNbr(s, j)) {						//遍历所有邻居
			if (status(j) == UNDISCOVERED && (priority(j) > priority(s) + weight(s, j))) {		//计算优先级
				priority(j) = priority(s) + weight(s, j);
				parent(j) = s;
			}
		}
		int temp = 0;
		for (int shortest = INT_MAX, j = 0; j < n; j++) {							//选出下一节点
			if ((status(j) == UNDISCOVERED) && (priority(j) < shortest)) {
				shortest = priority(j);
				temp = j;
			}
		}
		s = temp;
	}
}

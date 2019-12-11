#ifndef _GRAPH_H_
#define _GRAPH_H_

#include <limits.h>
#include <vector>

using namespace std;

typedef enum { UNDISCOVERED, DISCOVERED, VISITED } VStatus;			
typedef enum { UNDETERMINED, TREE, CROSS, FORWARD, BACKWARD } EType;

template <typename Tv, typename Te> class Graph {
public:
	virtual void reset() = 0;
	//顶点
	int n;				//顶点总数
	virtual int insert(Tv& const) = 0;		//插入顶点，返回编号
	virtual Tv remove(int) = 0;		//删除顶点及其关联边，返回该顶点数据
	virtual Tv& vertex(int) = 0;	//获取顶点的数据
	virtual int inDegree(int) = 0;
	virtual int outDegree(int) = 0;
	virtual int& dTime(int) = 0;
	virtual int& fTime(int) = 0;
	virtual int& parent(int) = 0;
	virtual int& priority(int) = 0;
	//边
	int e;				//边总数
	virtual bool exists(int, int) = 0;
	virtual void insert(Te const&, int, int, int) = 0;
	virtual Te remove(int, int) = 0;
	virtual EType& type(int, int) = 0;
	virtual Te& edge(int, int) = 0;		//获取边的数据
	virtual int& weight(int, int) = 0;
	//算法
	virtual void dijkstra(int) = 0;					//最短路径Dijkstra算法
};

template <typename Tv> struct Vertex {
	Tv data;
	int inDegree, outDegree;
	VStatus status;
	int dTime, fTime;
	int parent;
	int priority;
	Vertex(Tv & const d):
		data(d),inDegree(0),outDegree(0),status(UNDISCOVERED),
		dTime(-1),fTime(-1),priority(INT_MAX){ }

};

template <typename Te> struct Edge {
	Te data;
	int weight;
	EType type;
	Edge(Te const & d, int w):
		data(d),weight(w),type(UNDETERMINED){ }
};

template <typename Tv, typename Te> class GraphMatrix : public Graph<Tv, Te> {
private:
	vector<Vertex<Tv>> V;		//顶点集
	vector<vector<Edge<Te>*>> E;//边集
public:
	int n;
	int e;
	GraphMatrix() { n = e = 0; }
	~GraphMatrix() {
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < n; k++) {
				delete E[j][k];
			}
		}
	}
	void reset();
	//顶点操作
	Tv & vertex(int i) { return V[i].data; }
	int inDegree(int i) { return V[i].inDegree; }
	int outDegree(int i) { return V[i].outDegree; }
	VStatus & status(int i) { return V[i].status; }
	int & dTime(int i) { return V[i].dTime; }
	int & fTime(int i) { return V[i].dTime; }
	int & parent(int i) { return V[i].parent; }
	int & priority(int i) { return V[i].priority; }
	int nextNbr(int i, int j);				//已寻找到邻居j，返回下一个邻居
	int firstNbr(int i) { return nextNbr(i, n); }
	int insert(Tv& const);
	Tv remove(int i);
	//边操作
	bool exists(int i, int j);
	Te & edge(int i, int j) { return E[i][j]->data; }
	EType & type(int i, int j) { return E[i][j]->type; }
	int & weight(int i, int j) { return E[i][j]->weight; }
	void insert(Te const& edge, int w, int i, int j);
	Te remove(int i, int j);
	//算法
	void dijkstra(int);
};

#endif
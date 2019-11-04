#ifndef _ARI_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <Windows.h>
#include <math.h>
#include <conio.h>


typedef struct Record {
	int numberOfQuestions;			//总做题量
	int numberOfSolvedQuestions;	//总对题数
	int numberOfAddQuestions;		//加法总题数
	int solvedAdd;					//加法对题数
	int numberOfSub;				//减法总题数
	int solvedSub;					//减法对题数
	int numberOfMul;				//乘法总题数
	int solvedMul;					//乘法对题数
	int numberOfDiv;				//除法总题数
	int solvedDiv;					//除法对题数
	char evaluation[1001];			//教师评价
	int level;						//闯到的关卡数
}Record;

typedef struct User {				//用以存储用户信息的链表
	char account[20];				//账号
	char password[20];				//密码
	char name[10];					//姓名
	Record record;					//答题记录
	int isTeacher;					//标记是否为老师
}User;

typedef struct WrongBank {
	char name[10];
	int num1;
	int num2;
	int kind;
}WrongBank;

typedef struct Node {
	User user;
	struct Node *pNext;
}Node;

#define  CLEARBUF \
{\
	int ch;\
		while((ch=getchar())!=EOF&&ch!='\n')\
		{\
		;\
	}\
 } 

extern User user_read;				//全局变量保存读入的用户信息

int loginInterface(User* user);		//登录界面（返回值为登录结果）
int login(User* p);					//登录(返回值为登录结果)
void enrol();						//注册
int teacherInterface(User *p);		//教师登录后显示界面(返回值表示是否继续运行)
int studentInterface(User* p);		//学生登录后显示界面(返回值表示是否继续运行)
void announce();					//教师发布公告
Node* studentList();				//创建学生链表
Node* insertList(Node* pEnd, User user);	//链表插入函数
void commentStudent();				//评价学生
void commentSystem();				//评价系统
void viewRank();					//查看学生做题量排名
void limitTimeExercise();			//“激情60s”功能
void timeExercise();				//计时模式
void breakThrough();				//闯关模式
void watchBank();					//查看错题本
void exerciseYourself();			//自测练习
void checkInfo();					//查看信息
void checkEvaluation();				//查看教师评价
void checkAnnouncement();			//查看公告
int setDifficulty();				//设置题目难度
int recording(int isRight, int kind,int num1, int num2);
void enterBank(char name[], int kind, int num1, int num2);			//将错题加入错题库
void save();						//将对全局变量做的修改进行保存
int continueOrNot();				//判断用户是否继续做题
void checkRecord();					//学生查看自己的答题记录
Node* sort(Node* pHead);			//将学生信息链表按答题量排序
int checkRepeat(User newUser);		//检查新用户信息是否重复（0无重复，1用户名重复，2姓名重复）
void printErrorSituation(int situation);	//输出错误情况
void displayTimeExercise();			//显示计时模式界面
void timeExerciseProcess();			//计时模式过程
void printDifficulty(int n);		//计时模式中打印难度
void quit();						//用户推出前显示界面

#endif // !_ARI_H_



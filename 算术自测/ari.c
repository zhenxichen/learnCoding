#include "ari.h"

User user_read;

int loginInterface(User* user) {								//chen
	int option = 1;													//选项：1为登录，2为注册
	int ret = 0;													//返回值：0为未登录，1为登录
	HANDLE hInput = GetStdHandle(STD_INPUT_HANDLE);					//获取标准输入设备句柄
	COORD crd = { 0,0 };
	INPUT_RECORD inRec;												//记录输入信息
	DWORD res,mode;
	CONSOLE_CURSOR_INFO cinfo;										//光标
	cinfo.bVisible = 0;												//光标设为不可见
	
	printf("--------------------------------------\n");
	printf("欢迎来到小学算术自测系统\n");
	printf("--------------------------------------\n");
	printf("请用鼠标点击选项：\n");
	printf("<1>登录\n\n");
	printf("<2>注册\n\n");

	
	for (;;) {
		GetConsoleMode(hInput, &mode);										//重设控制台模式
		mode |= ENABLE_MOUSE_INPUT;											//允许鼠标输入
		SetConsoleMode(hInput, mode);
		ReadConsoleInput(hInput, &inRec, 1, &res);							//读取控制台输入信息
		if (inRec.EventType == MOUSE_EVENT && inRec.Event.MouseEvent.dwButtonState
			== FROM_LEFT_1ST_BUTTON_PRESSED) {								//按键为左键
			if (inRec.Event.MouseEvent.dwMousePosition.Y == 4 &&			//按键位置在登录按键附近
				inRec.Event.MouseEvent.dwMousePosition.X < 10) {
				option = 1;
				break;
			}
			else if (inRec.Event.MouseEvent.dwMousePosition.Y ==6 &&		//按键位置在注册按键附近
				inRec.Event.MouseEvent.dwMousePosition.X < 10) {
				option = 2;
				break;
			}
		}
	}

	

	system("cls");
	switch (option) {
		case 2:enrol(); return 0;											//用return 0代替break（注册后需重新登录）
		case 1:return login(user);											//返回login()函数的返回值
	};
}

int login(User* p) {								//chen
	char account[20];
	char password[20];
	FILE *fp = fopen("data.txt", "r");					//打开存储数据的文件
	User user;

	printf("----------------------\n");
	printf("         登录         \n");
	printf("----------------------\n");
	printf("请输入您的账号：");
	scanf("%s", account);
	for (;;) {
		if (fread(&user, sizeof(user), 1, fp)) {		//遍历文件类型
			if (!strcmp(account, user.account)) {		//strcmp返回值为0表示输入账号与该账号相同
				printf("请输入您的密码：");
				scanf("%s", password);					//输入密码
				if (strcmp(password, user.password)) {
					printf("密码输入错误，请重试\n");
					system("pause");
					fclose(fp);
					return 0;
				}
				user_read = user;
				p = &user_read;
				fclose(fp);
				return 1;
			}
		}
		else {											//fread返回值为0，即已经全部读取
			printf("该账号不存在，请重试\n");
			system("pause");
			fclose(fp);
			return 0;
		}
	}
}

void enrol() {										//chen
	FILE* fp = NULL;
	User newUser;
	int isRepeat = 0;
	char input[50];
	
	for (;;) {
		printf("请设置您的账号：");
		fflush(stdin);								//清空缓冲区
		scanf("%s", &input);
		if (strlen(input) > 19) {					//若字符串长度超过19个字符
			printErrorSituation(4);					//要求用户重新设置
			system("pause");
			system("cls");
		}
		else {
			strcpy(newUser.account, input);
			break;
		}
	}
	system("cls");
	for (;;) {
		printf("请设置您的密码：");
		fflush(stdin);
		scanf("%s", &input);
		if (strlen(input) > 19) {
			printErrorSituation(5);
			system("pause");
			system("cls");
		}
		else {
			strcpy(newUser.password, input);
			break;
		}
	}
	system("cls");
	printf("请输入您的姓名：");
	scanf("%s", &newUser.name);
	system("cls");
	if (IDYES == MessageBox(NULL, "您是老师吗", "身份确认", MB_YESNO)) {
		newUser.isTeacher = 1;
	}
	else {
		newUser.isTeacher = 0;
	}
	system("cls");
	
	newUser.record.numberOfAddQuestions = 0;		//初始化做题记录数据
	newUser.record.numberOfDiv = 0;
	newUser.record.numberOfMul = 0;
	newUser.record.numberOfQuestions = 0;
	newUser.record.numberOfSolvedQuestions = 0;
	newUser.record.numberOfSub = 0;
	newUser.record.solvedAdd = 0;
	newUser.record.solvedDiv = 0;
	newUser.record.solvedMul = 0;
	newUser.record.solvedSub = 0;
	newUser.record.level = 1;
	newUser.record.evaluation[0] = '\0';
	
	isRepeat = checkRepeat(newUser);					//防止用户名或姓名重复
	if (!isRepeat) {
		fp = fopen("data.txt", "a");					//打开存储数据的文件
		fwrite(&newUser, sizeof(User), 1, fp);
		printf("注册完成，请重新登录\n");
		fclose(fp);
	}
	else {
		printErrorSituation(isRepeat);
	}
	
	system("pause");
}

int teacherInterface(User *p) {						//chen
	HANDLE hStdIn = GetStdHandle(STD_INPUT_HANDLE);			//获取标准输入句柄
	INPUT_RECORD inRec;										//存储输入记录
	DWORD mode;												//存储控制台模式
	int option = 0;											//记录用户选择

	printf("%s老师，您好\n",user_read.name);
	printf("请鼠标点击您要使用的功能\n");
	printf("<1>发布公告\n\n");
	printf("<2>对学生进行评价\n\n");
	printf("<3>对系统进行评价\n\n");
	printf("<4>查看学生排行\n\n");
	printf("<5>退出\n");

	for (;;) {
		GetConsoleMode(hStdIn, &mode);						//获取控制台模式
		mode |= ENABLE_MOUSE_INPUT;							//允许鼠标输入
		SetConsoleMode(hStdIn, mode);						//重设控制台模式
		ReadConsoleInput(hStdIn, &inRec, 1, &mode);			//读取控制台输入到inRec
		if (inRec.EventType == MOUSE_EVENT &&				//判断鼠标点击位置
			inRec.Event.MouseEvent.dwButtonState == FROM_LEFT_1ST_BUTTON_PRESSED) {
			if (inRec.Event.MouseEvent.dwMousePosition.Y == 2 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 10) {
				option = 1;
				break;
			}
			else if (inRec.Event.MouseEvent.dwMousePosition.Y == 4 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 15) {
				option = 2;
				break;
			}
			else if (inRec.Event.MouseEvent.dwMousePosition.Y == 6 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 15) {
				option = 3;
				break;
			}
			else if (inRec.Event.MouseEvent.dwMousePosition.Y == 8 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 15) {
				option = 4;
				break;
			}
			else if (inRec.Event.MouseEvent.dwMousePosition.Y == 10 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 10) {
				option = 5;
				break;
			}
		}

	}
	system("cls");

	switch (option) {
		case 5: return 0;						//<5>退出系统
		case 4: viewRank(); return 1;			//<4>查看学生排行
		case 3: commentSystem(); return 1;		//<3>对系统进行评价
		case 2: commentStudent(); return 1;		//<2>对学生进行评价
		case 1: announce(); return 1;			//<1>发布公告
		default: break;
	}
	
	return 1;
}

int studentInterface(User* p) {					//chen
	HANDLE hStdIn = GetStdHandle(STD_INPUT_HANDLE);			//获取标准输入句柄
	INPUT_RECORD inRec;										//用以记录鼠标输入
	DWORD mode;												//用以存储控制台模式
	int option = 0;											//标记用户选择

	printf("%s同学，你好\n", user_read.name);
	printf("请鼠标点击您要使用的功能\n");
	printf("<1>查看信息\n\n");
	printf("<2>自测练习\n\n");
	printf("<3>查看错题本\n\n");
	printf("<4>闯关模式\n\n");
	printf("<5>计时模式\n\n");
	printf("<6>激情60s\n\n");
	printf("<7>退出\n\n");

	for (;;) {
		GetConsoleMode(hStdIn, &mode);						//将控制台模式存储到mode中
		mode |= ENABLE_MOUSE_INPUT;							//控制台模式设定为允许鼠标输入
		SetConsoleMode(hStdIn, mode);						//设置控制台模式
		ReadConsoleInput(hStdIn, &inRec, 1, &mode);			//读取控制台输入
		if (inRec.EventType == MOUSE_EVENT &&				//事件类型为鼠标事件
			inRec.Event.MouseEvent.dwButtonState == FROM_LEFT_1ST_BUTTON_PRESSED) {		//事件为鼠标左键点击
			if (inRec.Event.MouseEvent.dwMousePosition.Y == 2 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 10) {
				option = 1;
				break;
			}
			else if (inRec.Event.MouseEvent.dwMousePosition.Y == 4 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 10) {
				option = 2;
				break;
			}
			else if (inRec.Event.MouseEvent.dwMousePosition.Y == 6 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 10) {
				option = 3;
				break;
			}
			else if (inRec.Event.MouseEvent.dwMousePosition.Y == 8 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 15) {
				option = 4;
				break;
			}
			else if (inRec.Event.MouseEvent.dwMousePosition.Y == 10 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 10) {
				option = 5;
				break;
			}
			else if (inRec.Event.MouseEvent.dwMousePosition.Y == 12 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 10) {
				option = 6;
				break;
			}
			else if (inRec.Event.MouseEvent.dwMousePosition.Y == 14 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 10) {
				option = 7;
				break;
			}
		}
	}
	system("cls");
	switch (option) {
		case 7: return 0;						//<7>退出（返回0表示不再维持系统运行）
		case 6: limitTimeExercise(); return 1;	//<6>激情60s
		case 5: timeExercise();	return 1;		//<5>计时模式
		case 4: breakThrough();	return 1;		//<4>闯关模式
		case 3: watchBank(); return 1;			//<3>查看错题本
		case 2: exerciseYourself();	return 1;	//<2>自测练习
		case 1: checkInfo(); return 1;			//<1>查看信息
	}
	
	return 1;	
}

void announce() {								//chen
	FILE *fp = fopen("announce.txt", "a");
	char announceText[1001];
	printf("请输入公告内容(最多1000字)：\n");
	scanf("%s", announceText);
	fprintf(fp, "%s\n", announceText);
	fclose(fp);
	system("cls");
}

Node* studentList() {							//chen
	User user;
	Node *pHead = (Node*)malloc(sizeof(Node));
	Node *pEnd = pHead;
	FILE* fp = fopen("data.txt", "r");
	pHead->pNext = NULL;
	while (fread(&user, sizeof(user), 1, fp) == 1) {
		if (user.isTeacher == 0) {					//若该用户不是老师，则加入链表
			pEnd = insertList(pEnd, user);
		}
	}
	fclose(fp);
	return pHead;
}

Node* insertList(Node* pEnd, User user) {		//chen
	Node* p = (Node*)malloc(sizeof(Node));
	p->user = user;
	p->pNext = NULL;
	pEnd->pNext = p;
	return p;
}

void commentStudent() {							//chen
	char name[10];
	int find = 0;								//标记是否找到对应学生
	FILE *fp = fopen("data.txt","rt+");			//打开文本（允许读写）
	User user;
	char comment[1001];							//评语内容

	printf("请输入要评价学生的姓名：");
	scanf("%s", name);

	while (fread(&user, sizeof(user), 1, fp)) {
		if (!strcmp(user.name, name)) {			//strcmp()返回值为0，即名字对应
			find = 1;
			break;
		}
	}
	
	if (find) {
		fseek(fp, -(long)sizeof(User), SEEK_CUR);
		printf("请输入评语：\n");
		scanf("%s",user.record.evaluation);
		fwrite(&user, sizeof(user), 1, fp);
		fclose(fp);
		printf("评价完成\n");
	}
	else {
		printf("未找到该学生\n");
	}
	system("pause");
	system("cls");

}

void commentSystem() {					//chen
	FILE* fp = fopen("comments.txt","a");
	char commentSystemText[1001];
	printf("请留下您对这个软件的意见和建议：\n");
	scanf("%s", commentSystemText);
	fprintf(fp, commentSystemText);
	fclose(fp);
	system("cls");
}

void viewRank() {						//chen
	Node* pHead = studentList();		//创建学生链表并填入数据
	Node* p = NULL;
	int rank = 0;
	
	pHead = sort(pHead);				//对链表进行冒泡排序
	printf("名次\t姓名\t做题量\n");
	for (p = pHead->pNext; p; p = p->pNext) {	//遍历链表
		rank++;
		printf("第%d名\t%s\t%d\n", rank, p->user.name, p->user.record.numberOfQuestions);
	}
	system("pause");
	system("cls");

}

void limitTimeExercise() {				//feng
	int difficulty;//难度
	int num1, num2;// 算式的两个数 
	int answer;//答案 
	int kind = 1; //算式种类 
	int grades = 0;//游戏得分 
	int temp;
	int rest;
	time_t start, end; //计时 
	srand((unsigned)time(NULL));
	//打印提示 
	printf("欢迎来到“激情60秒”\n");
	printf("你将有60秒的时间进行答题，你可以选择1，2，3三个难度，如果在你回答题目的时候到时间了");

	printf("，系统将给你回答完之后结算分数，但是不计入成绩。加油！\n");
	system("pause");
	system("cls");
	difficulty = setDifficulty();


	start = time(NULL);   //开始计时 
	end = time(NULL);
	for (; (end - start) < 60; end = time(NULL), kind++) {
		if (kind == 5) kind = 1;
		if (difficulty == 1) {    //难度1 
			num1 = rand() % 10 + 1;
			num2 = rand() % 10 + 1;//生成一位简单的数字
			printf("小朋友，你还剩下%d秒。\n", 60 - (end - start));

			if (num1 < num2) { temp = num1; num1 = num2; num2 = temp; }
			switch (kind) {    //根据种类来生成算式 并记下答题记录 
			case 1:printf("%d + %d = ？\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1 + num2) == answer, kind, num1, num2); if ((num1 + num2) == answer) { printf("正确！\n"); grades++;
				}
				break;
			case 2:printf("%d - %d = ？\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1 - num2) == answer, kind, num1, num2);  if ((num1 - num2) == answer) { printf("正确！\n"); grades++;
				}
				break;
			case 3:printf("%d * %d = ？\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1*num2) == answer, kind, num1, num2);  if ((num1*num2) == answer) { printf("正确！\n"); grades++;
				}
				break;
			case 4:printf("%d / %d = ？\n", num1, num2); fflush(stdin); scanf("%d%d", &answer, &rest);
				recording((num1 / num2) == answer && (num1%num2) == rest, kind, num1, num2);
				if ((num1 / num2) == answer && (num1%num2) == rest) { printf("正确！\n"); grades++;
				} break;
			}
		}
		else if (difficulty == 2) {   //难度2 
			num1 = rand() % 101 + 1;
			num2 = rand() % 101 + 1;
			switch (kind) {
			case 1:printf("%d + %d = ?\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1 + num2) == answer, kind, num1, num2); 
				if ((num1 + num2) == answer) { printf("正确！\n"); grades++;
				}
				break;
			case 2:printf("%d - %d = ?\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1 - num2) == answer, kind, num1, num2);  
				if ((num1 - num2) == answer) { printf("正确！\n"); grades++;
				}
				break;
			case 3:printf("%d * %d = ?\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1*num2) == answer, kind, num1, num2);  
				if ((num1*num2) == answer) { printf("正确！\n"); grades++;
				}
				break;
			case 4:printf("%d / %d = ？\n", num1, num2); fflush(stdin); scanf("%d%d", &answer, &rest);
				recording((num1 / num2) == answer && (num1%num2) == rest, kind, num1, num2);
				if ((num1 / num2) == answer && (num1%num2) == rest) { printf("正确！\n"); grades++;
				}
				break;
			}
		}
		else if (difficulty == 3) {   //难度3 
			num1 = rand() % 1001 + 1;
			num2 = rand() % 1001 + 1;
			switch (kind) {
			case 1:printf("%d + %d = ?\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1 + num2) == answer, kind, num1, num2); 
				if ((num1 + num2) == answer) { printf("正确！\n"); grades++;
				}
				break;
			case 2:printf("%d - %d = ?\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1 - num2) == answer, kind, num1, num2);
				if ((num1 - num2) == answer) { printf("正确！\n"); grades++;
				}
				break;
			case 3:printf("%d * %d = ?\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1*num2) == answer, kind, num1, num2);  
				if ((num1*num2) == answer) { printf("正确！\n"); grades++;
				}
				break;
			case 4:printf("%d / %d = ？\n", num1, num2); fflush(stdin); scanf("%d%d", &answer, &rest);
				recording((num1 / num2) == answer && (num1%num2) == rest, kind, num1, num2);
				if ((num1 / num2) == answer && (num1%num2) == rest) { printf("正确！\n"); grades++;
				} break;
			}
		}
		system("pause");
		system("cls");
	}
	printf("游戏结束！小朋友，你一共答对了%d题\n", grades);
	system("pause");
	system("cls");
}

void timeExercise() {					//yu
	clock_t start;
	clock_t end;
	displayTimeExercise();
	start = clock();						//开始计时 
	timeExerciseProcess();
	end = (clock() - start);				//计时结束 
	printf("用时为%d秒\n", end / 1000);

	Sleep(4000);								//暂停4秒 
	system("cls");
}

void displayTimeExercise() {			//yu
	int difficulity;
	printf("\n			**************************欢迎进入计时练习模式************************\n");
	printf("				（系统将会为你出10道计算题，完成后会告知你所用时间）\n");
}

void printDifficulty(int n) {
	switch (n) {
	case 1:printf("\n						     当前为简单模式\n\n\n"); break;
	case 2:printf("\n						     当前为一般模式\n\n\n"); break;
	case 3:printf("\n						     当前为困难模式\n\n\n"); break;
	}
}

void timeExerciseProcess() {			//yu
	int num1, num2;					//num1为算数题的第一个数，num2为第二个数 
	int kindrandom;				//随机数控制算数题的符号 
	int answer;						//用户的答案 
	char kind;						//算数符号 
	int shang;						//除法的商 
	int yushu;						//除法的余数 
	int rightcount = 0;			   	//做对题数 
	int wrongcount = 0;				//做错题数 
	int difficulty = 0;				    //难度 
	int i = 0;
	HANDLE hStdIn = GetStdHandle(STD_INPUT_HANDLE);
	INPUT_RECORD inRec;
	DWORD mode;


	printf("\n						    *请选择难易程度*\n");
	printf("\n              1------简单     2------一般     3------困难\n\n\n");


begin:																			//goto 语句跳转 
	while (!difficulty) {
		GetConsoleMode(hStdIn, &mode);
		mode |= ENABLE_MOUSE_INPUT;
		SetConsoleMode(hStdIn, mode);
		ReadConsoleInput(hStdIn, &inRec, 1, &mode);
		if (inRec.EventType == MOUSE_EVENT &&
			inRec.Event.MouseEvent.dwButtonState == FROM_LEFT_1ST_BUTTON_PRESSED) {
			if (inRec.Event.MouseEvent.dwMousePosition.Y == 6) {
				if (inRec.Event.MouseEvent.dwMousePosition.X >= 13 &&
					inRec.Event.MouseEvent.dwMousePosition.X < 23) {
					difficulty = 1;
				}
				else if (inRec.Event.MouseEvent.dwMousePosition.X >= 26 &&
					inRec.Event.MouseEvent.dwMousePosition.X < 36) {
					difficulty = 2;
				}
				else if (inRec.Event.MouseEvent.dwMousePosition.X >= 41 &&
					inRec.Event.MouseEvent.dwMousePosition.X < 51) {
					difficulty = 3;
				}
			}
		}
	}
	CLEARBUF											//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!添加 

	printf("将在两秒后开始");
	Sleep(2000); 														//输入难度选择后暂停2秒 
	system("cls");														//清除部分内容 
	displayTimeExercise();												//重新打印窗口信息 
	printDifficulty(difficulty);




	for (i = 0; i < 10; i++) {															//10次循环做题 


		srand(time(NULL));				//产生随机数机制 
		switch (difficulty) {															//根据用户输入难度，出题随机数的范围改变 
		case 1: num1 = rand() % 10, num2 = rand() % 9 + 1; break;
		case 2: num1 = rand() % 90 + 10, num2 = rand() % 100 + 1; break;							//防止除数（num2）为0 
		case 3: num1 = rand() % 900 + 100, num2 = rand() % 1000 + 1; break;

		default:

			printf("						*请选择难易程度*\n");
			printf("\n				1------简单		2------一般		3------困难\n\n\n");
			printf("输入无效命令！！！请重新输入\n");
			goto  begin; 															//输入无效指令，跳转难度选择界面重新输入 

		}

		kindrandom = rand() % 4 + 1;														//随机产生题目类型（加减乘除） 

		switch (kindrandom) {								//kindrandom变量：1表加法；2表减法；3表乘法；4 表除法

		case 1: kind = '+'; break;
		case 2: kind = '-'; break;
		case 3: kind = 'x'; break;
		case 4: kind = '/'; break;

		}


		printf("\n\n第%d题：\n\n", i + 1);
		if (kindrandom == 2) {
			if (num1 >= num2)printf("%d - %d =", num1, num2);
			else if (num1 < num2)printf("%d - %d =", num2, num1);						//减法时防止答案为负数 
		}
		else printf("%d %c %d =", num1, kind, num2);

		if (kindrandom == 1 || kindrandom == 2 || kindrandom == 3) {
			scanf("%d", &answer);

			CLEARBUF									//添加!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!清空缓存区 

				if (kindrandom == 1) {															//加法 
					if (num1 + num2 == answer) {
						rightcount++;															//对题数加一 
						printf("恭喜你，答对了！\n");
					}

					else {
						wrongcount++;
						printf("不对哦！\n");
					}

					Sleep(2000);
					system("cls");															//清除上一题 
					displayTimeExercise();
					printDifficulty(difficulty);

				}
				else if (kindrandom == 2) {    											//减法 
					if (num1 >= num2) {

						if (num1 - num2 == answer) {
							rightcount++;
							printf("恭喜你，答对了！\n");
						}

						else {
							wrongcount++;
							printf("不对哦！\n");
						}
						Sleep(2000);
						system("cls");
						displayTimeExercise();
						printDifficulty(difficulty);
					}
					else {
						if (num2 - num1 == answer) {
							rightcount++;
							printf("恭喜你，答对了！\n");
						}

						else {
							wrongcount++;
							printf("不对哦！\n");
						}
						Sleep(2000);
						system("cls");
						displayTimeExercise();
						printDifficulty(difficulty);
					}
				}
				else if (kindrandom == 3) {												//乘法 
					if (num1*num2 == answer) {
						rightcount++;
						printf("恭喜你，答对了！\n");
					}

					else {
						wrongcount++;
						printf("不对哦！\n");
					}

					Sleep(2000);
					system("cls");
					displayTimeExercise();
					printDifficulty(difficulty);

				}
		}
		else if (kindrandom == 4) {												//除法 


			printf("（商）：");
			scanf("%d", &shang);
			if (num1 / num2 == shang)printf("恭喜你，答对了！\n");					//算商 


			else printf("不对哦！\n");

			CLEARBUF									//！！！！！！！！！！！！！！！！！！！！！清空缓存区 
				printf(" ......(余数)：");
			scanf("%d", &yushu);

			if (num1%num2 == yushu)printf("恭喜你，答对了！\n");				//算余数 

			else printf("不对哦！\n");
			CLEARBUF														//清空缓存区 

				if (num1 / num2 != shang || num1 % num2 != yushu) wrongcount++;
				else rightcount++;
			Sleep(2000);
			system("cls");
			displayTimeExercise();
			printDifficulty(difficulty);

		}
		printf("\n\n");
	}
	printf("你已完成此次计时练习，共答对%d道，答错%d道\n再接再厉！\n", rightcount, wrongcount);
}

void breakThrough() {					//feng
	int i;
	int kind;
	int answer;
	int rest;
	int right;
	int option = 0;
	int num1, num2;
	int temp;
	HANDLE hStdIn = GetStdHandle(STD_INPUT_HANDLE);
	INPUT_RECORD inRec;
	DWORD mode;

	srand((unsigned)time(NULL));
	printf("小朋友，欢迎来到闯关模式，");
	printf("上一次你闯到的关卡数是第%d关\n", user_read.record.level);
	printf("准备好了，游戏三秒钟后开始！\n");
	Sleep(3000);
	system("cls");
	if (user_read.record.level == 1) {  //第一关 
		printf("\n欢迎来到第一关！\n");
		for (i = 0; i < 7; i++) {
			right = 0;
			num1 = rand() % 10 + 1;    //生成随机数 
			num2 = rand() % 10 + 1;
			if (num1 < num2) { temp = num1; num1 = num2; num2 = temp; }
			srand((unsigned)time(NULL)); //生成随机数--》构成计算题种类 
			kind = rand() % 4 + 1;
			switch (kind) {                                                         //存贮题目历史                      //如果错误 right变为1 
			case 1:printf("%d + %d = ?\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1 + num2) == answer, kind, num1, num2); 
				if ((num1 + num2) != answer) right = 1;  break;
			case 2:printf("%d - %d = ?\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1 - num2) == answer, kind, num1, num2);  if ((num1 - num2) != answer) right = 1; break;
			case 3:printf("%d * %d = ?\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1*num2) == answer, kind, num1, num2);  if ((num1*num2) != answer) right = 1; break;
			case 4:printf("%d / %d = ?\n", num1, num2); fflush(stdin); scanf("%d%d", &answer, &rest);
				recording((num1 / num2) == answer && num1%num2 == rest, kind, num1, num2);
				if ((num1 / num2) != answer || num1 % num2 != rest) right = 1; break;
			}
			if (right == 1) {  //错误的处理办法 
				system("cls");
				printf("闯关失败！小朋友，你要从这关开始重新挑战吗？\n\n");
				printf("<是>                                   <否>\n");

				for (;;) {
					GetConsoleMode(hStdIn, &mode);
					mode |= ENABLE_MOUSE_INPUT;
					SetConsoleMode(hStdIn, mode);
					ReadConsoleInput(hStdIn, &inRec, 1, &mode);
					if (inRec.EventType == MOUSE_EVENT &&
						inRec.Event.MouseEvent.dwButtonState == FROM_LEFT_1ST_BUTTON_PRESSED) {
						if (inRec.Event.MouseEvent.dwMousePosition.Y == 2) {
							if (inRec.Event.MouseEvent.dwMousePosition.X >= 0 &&
								inRec.Event.MouseEvent.dwMousePosition.X < 5) {
								option = 1;
								break;
							}
							else if (inRec.Event.MouseEvent.dwMousePosition.X >= 37 &&
								inRec.Event.MouseEvent.dwMousePosition.X < 42) {
								option = 0;
								break;
							}
						}
					}
				}

				if (option == 0) { system("cls"); break; }   // 如果选择0；break 
				else if (option == 1) { i = 0; system("pause"); system("cls"); } //如果选择1；重置i 和 right； 
			}
			else {
				printf("正确！");
				system("pause");
				system("cls");
			}

		}
		if (right == 0) user_read.record.level++;
	}
	if (user_read.record.level == 2) {  //第2关 
		printf("欢迎来到第二关！\n");
		for (i = 0; i < 7; i++) {
			right = 0;
			num1 = rand() % 101 + 1;    //生成随机数 
			num2 = rand() % 10 + 1;
			if (num1 < num2) { temp = num1; num1 = num2; num2 = temp; }
			srand((unsigned)time(NULL)); //生成随机数--》构成计算题种类 
			kind = rand() % 4 + 1;
			switch (kind) {                                                         //存贮题目历史   //如果错误 right变为1 
			case 1:printf("%d + %d = ?\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1 + num2) == answer, kind, num1, num2); if ((num1 + num2) != answer) right = 1;  break;
			case 2:printf("%d - %d = ?\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1 - num2) == answer, kind, num1, num2);  if ((num1 - num2) != answer) right = 1; break;
			case 3:printf("%d * %d = ?\n", num1, num2); fflush(stdin); scanf("%d", &answer);
				recording((num1*num2) == answer, kind, num1, num2);  if ((num1*num2) != answer) right = 1; break;
			case 4:printf("%d / %d = ?\n", num1, num2); fflush(stdin); scanf("%d%d", &answer, &rest);
				recording((num1 / num2) == answer && num1%num2 == rest, kind, num1, num2);
				if ((num1 / num2) != answer || num1 % num2 != rest) right = 1; break;
			}
			if (right == 1) {  //错误的处理办法 
				system("cls");
				printf("闯关失败！小朋友，你要从这关开始重新挑战吗？\n\n");
				printf("<是>                                   <否>\n");
				for (;;) {
					GetConsoleMode(hStdIn, &mode);
					mode |= ENABLE_MOUSE_INPUT;
					SetConsoleMode(hStdIn, mode);
					ReadConsoleInput(hStdIn, &inRec, 1, &mode);
					if (inRec.EventType == MOUSE_EVENT &&
						inRec.Event.MouseEvent.dwButtonState == FROM_LEFT_1ST_BUTTON_PRESSED) {
						if (inRec.Event.MouseEvent.dwMousePosition.Y == 2) {
							if (inRec.Event.MouseEvent.dwMousePosition.X >= 0 &&
								inRec.Event.MouseEvent.dwMousePosition.X < 5) {
								option = 1;
								break;
							}
							else if (inRec.Event.MouseEvent.dwMousePosition.X >= 37 &&
								inRec.Event.MouseEvent.dwMousePosition.X < 42) {
								option = 0;
								break;
							}
						}
					}
				}
				if (option == 0) { system("cls"); break; }  // 如果选择0；break 
				else if (option == 1) { i = 0; system("pause"); system("cls"); } //如果选择1；重置i 和 right； 

			}
			else {
				printf("正确！");
				system("pause");
				system("cls");
			}

		}
		if (right == 0) user_read.record.level++;
	}
	if (user_read.record.level == 3) {  //第3关 
		printf("欢迎来到第三关！\n");
		for (i = 0; i < 7; i++) {
			right = 0;
			num1 = rand() % 101 + 1;    //生成随机数 
			num2 = rand() % 101 + 1;
			if (num1 < num2) { temp = num1; num1 = num2; num2 = temp; }
			srand((unsigned)time(NULL)); //生成随机数--》构成计算题种类 
			kind = rand() % 4 + 1;
			switch (kind) {                                                         //存贮题目历史         //如果错误 right变为1 
			case 1:printf("%d + %d = ?\n", num1, num2); scanf("%d", &answer);
				recording((num1 + num2) == answer, kind, num1, num2); 
				if ((num1 + num2) != answer) right = 1;  break;
			case 2:printf("%d - %d = ?\n", num1, num2); scanf("%d", &answer);
				recording((num1 - num2) == answer, kind, num1, num2);
				if ((num1 - num2) != answer) right = 1; break;
			case 3:printf("%d * %d = ?\n", num1, num2); scanf("%d", &answer);
				recording((num1*num2) == answer, kind, num1, num2);
				if ((num1*num2) != answer) right = 1; break;
			case 4:printf("%d / %d = ?\n", num1, num2); scanf("%d%d", &answer, &rest);
				recording((num1 / num2) == answer && num1%num2 == rest, kind, num1, num2);
				if ((num1 / num2) != answer || num1 % num2 != rest) right = 1; break;
			}
			if (right == 1) {  //错误的处理办法 
				system("cls");
				printf("闯关失败！小朋友，你要从这关开始重新挑战吗？\n\n");
				printf("<是>                                   <否>\n");

				for (;;) {
					GetConsoleMode(hStdIn, &mode);
					mode |= ENABLE_MOUSE_INPUT;
					SetConsoleMode(hStdIn, mode);
					ReadConsoleInput(hStdIn, &inRec, 1, &mode);
					if (inRec.EventType == MOUSE_EVENT &&
						inRec.Event.MouseEvent.dwButtonState == FROM_LEFT_1ST_BUTTON_PRESSED) {
						if (inRec.Event.MouseEvent.dwMousePosition.Y == 2) {
							if (inRec.Event.MouseEvent.dwMousePosition.X >= 0 &&
								inRec.Event.MouseEvent.dwMousePosition.X < 5) {
								option = 1;
								break;
							}
							else if (inRec.Event.MouseEvent.dwMousePosition.X >= 37 &&
								inRec.Event.MouseEvent.dwMousePosition.X < 42) {
								option = 0;
								break;
							}
						}
					}
				}

				if (option == 0) { system("cls"); break; }   // 如果选择0；break 
				else if (option == 1) { i = 0; system("pause"); system("cls"); } //如果选择1；重置i 和 right；
			}
			else {
				printf("正确！");
				system("pause");
				system("cls");
			}

		}
		if (right == 0) {
			printf("小朋友，你的挑战已经完成。");
			system("pause");
			system("cls");
		}
	}
}

void watchBank() {						//feng
	FILE *fp;
	WrongBank wbank[100] = { NULL,0,0,0 };//声明读出错题存放的结构体 
	int i;//遍历变量 
	fp = fopen("错题库.txt", "r"); // 打开文件 
	for (i = 0; !feof(fp); i++) {
		fread(&wbank[i], sizeof(WrongBank), 1, fp);   //一个一个读出错题记录进结构体中，直到读完 
	}
	fclose(fp);//关闭文件 
	printf("以下是你曾做错的题：\n");
	for (i = 0; i < 50; i++) {
		if (strcmp(user_read.name, wbank[i].name) == 0) {   //如果遍历到全局变量姓名与错题记录姓名一致   
			switch (wbank[i].kind) {                          //打印 
			case 1:printf("%d + %d=%d\n", wbank[i].num1, wbank[i].num2, wbank[i].num1 + wbank[i].num2); break;
			case 2:printf("%d - %d=%d\n", wbank[i].num1, wbank[i].num2, wbank[i].num1 - wbank[i].num2); break;
			case 3:printf("%d x %d=%d\n", wbank[i].num1, wbank[i].num2, wbank[i].num1*wbank[i].num2); break;
			case 4:printf("%d / %d=%d···%d\n", wbank[i].num1, wbank[i].num2,
				wbank[i].num1 / wbank[i].num2, wbank[i].num1%wbank[i].num2); break;
			}
		}
	}
	system("pause");
	system("cls");
}

void exerciseYourself() {				//chen
	int difficulty = 0;					//难度值
	int num1 = 0, num2 = 0;
	int kind = 0;						//题型，1/2/3/4分别代表加/减/乘/除
	int continueExercising = 1;			//标记是否继续做题
	int answer = 0;						//存储学生输入的答案
	int standardAnswer = 0;				//标准答案
	int reminder = 0;					//如为除法，需记录学生给出的余数
	char sign;							//显示运算符
	int isRight = 0;					//标记题目是否做对
	int temp = 0;						//临时存储数字
	int isNumber = 0;					//标记用户输入的是否为数字
	
	difficulty = setDifficulty();
	while (continueExercising) {
		isNumber = 0;						//将是否数字重新标记为否，使能进入循环
		isRight = 0;						//将是否正确重新标记为错误
		srand(time(NULL));					//生成随机数
		num1 = rand() % (int)pow(10, difficulty) + 1;	//取随机数（难度1为10以内，难度2为100以内，难度3为1000以内）				
		num2 = rand() % (int)pow(10, difficulty) + 1;
		if (kind == 2 && num1 < num2) {					//避免出现负数结果
			temp = num1;
			num1 = num2;
			num2 = temp;
		}
		kind = (rand() % 4) + 1;				//取1-4之间的随机数
		switch (kind) {							//设置运算符
			case 4: sign = '/'; standardAnswer = num1 / num2; break;
			case 3: sign = '*'; standardAnswer = num1 * num2; break;
			case 2: sign = '-'; standardAnswer = num1 - num2; break;
			case 1: sign = '+'; standardAnswer = num1 + num2; break;
		}
		while (!isNumber) {
			printf("题目：   %d  %c  %d  =  ?\n", num1, sign, num2);
			if (kind == 4) {					//如果题目为除法
				printf("商：");
				fflush(stdin);					//清空缓冲区
				isNumber = scanf("%d", &answer);
				getchar();						//清除用户输入的回车
				if (!isNumber) {
					printErrorSituation(3);		//输出错误信息（输入非数字）
					system("pause");
					system("cls");
					continue;
				}
				printf("余数：");	
				fflush(stdin);
				isNumber = scanf("%d", &reminder);
				getchar();
				if (answer == standardAnswer && reminder == num1 % num2) {	//若商和余数均正确
					isRight = 1;				//则标记为正确
				}
			}
			else {
				printf("答案：");
				fflush(stdin);
				isNumber = scanf("%d", &answer);	
				getchar();
				if (answer == standardAnswer) {			//若结果与答案相同
					isRight = 1;						//则标记为做对
				}
			}
			if (!isNumber) {				//判断是否为数字
				printErrorSituation(3);		//输出错误信息（输入非数字）
				system("pause");
				system("cls");
			}
		}
		if (isRight) {
			printf("回答正确！\n");
			system("pause");
		}
		else {
			printf("很遗憾，回答错误，正确答案是：");
			if (kind == 4) {
				printf("商：%d  余数：%d\n", standardAnswer, (num1%num2));
			}
			else {
				printf("%d\n", standardAnswer);
			}
			system("pause");
		}
		recording(isRight, kind, num1, num2);
		continueExercising = continueOrNot();
	}
}

void checkInfo() {						//chen
	HANDLE hStdIn = GetStdHandle(STD_INPUT_HANDLE);
	INPUT_RECORD inRec;
	DWORD mode;
	int option = 0;
	
	printf("请选择您要查看的内容\n\n");
	printf("<1>公告\n\n");
	printf("<2>教师评价\n\n");
	printf("<3>查看个人记录\n\n");

	while (!option) {
		GetConsoleMode(hStdIn, &mode);						
		mode |= ENABLE_MOUSE_INPUT;							
		SetConsoleMode(hStdIn, mode);						
		ReadConsoleInput(hStdIn, &inRec, 1, &mode);
		if (inRec.EventType == MOUSE_EVENT &&
			inRec.Event.MouseEvent.dwButtonState == FROM_LEFT_1ST_BUTTON_PRESSED) {
			if (inRec.Event.MouseEvent.dwMousePosition.Y == 2 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 5) {
				option = 1;
			}
			else if (inRec.Event.MouseEvent.dwMousePosition.Y == 4 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 5) {
				option = 2;
			}
			else if (inRec.Event.MouseEvent.dwMousePosition.Y == 6 &&
				inRec.Event.MouseEvent.dwMousePosition.X < 5) {
				option = 3;
			}
		}
	}

	system("cls");

	switch (option) {
		case 3: checkRecord(); break;
		case 2: checkEvaluation(); break;
		case 1: checkAnnouncement(); break;
	}
}

void checkEvaluation() {					//chen
	printf("%s同学，老师对你的评价是:\n",user_read.name);
	printf("%s\n", user_read.record.evaluation);
	system("pause");
	system("cls");
}

void checkAnnouncement() {					//chen
	FILE* fp = fopen("announce.txt", "r");
	int find = 0;
	char announce[1001];

	while (fscanf(fp,"%s",announce)!=EOF){
		find++;
		printf("%s", announce);
		printf("\n");
	}
	if (find == 0) {
		printf("当前没有公告。\n");
	}
	system("pause");
	system("cls");
}

int setDifficulty() {						//chen
	HANDLE hStdIn = GetStdHandle(STD_INPUT_HANDLE);
	INPUT_RECORD inRec;
	DWORD mode;
	int option = 0;
	
	printf("请选择你所想要的题目难度：\n\n");
	printf("<1>         <2>          <3>\n");

	while (!option) {
		GetConsoleMode(hStdIn, &mode);
		mode |= ENABLE_MOUSE_INPUT;
		SetConsoleMode(hStdIn, mode);
		ReadConsoleInput(hStdIn, &inRec, 1, &mode);
		if (inRec.EventType == MOUSE_EVENT &&
			inRec.Event.MouseEvent.dwButtonState == FROM_LEFT_1ST_BUTTON_PRESSED) {
			if (inRec.Event.MouseEvent.dwMousePosition.Y == 2) {
				if (inRec.Event.MouseEvent.dwMousePosition.X >= 0 &&
					inRec.Event.MouseEvent.dwMousePosition.X < 3) {
					option = 1;
				}
				else if (inRec.Event.MouseEvent.dwMousePosition.X >= 11 &&
					inRec.Event.MouseEvent.dwMousePosition.X < 17) {
					option = 2;
				}
				else if (inRec.Event.MouseEvent.dwMousePosition.X >= 25 &&
					inRec.Event.MouseEvent.dwMousePosition.X < 29) {
					option = 3;
				}
			}
		}
	}
	printf("你选择了第%d级的难度\n", option);
	system("pause");
	system("cls");
	return option;
}

int recording(int isRight, int kind,int num1,int num2) {	//chen
	HANDLE hStdIn = GetStdHandle(STD_INPUT_HANDLE);
	INPUT_RECORD inRec;
	DWORD mode;
	int option = 0;
	
	user_read.record.numberOfQuestions++;				//做题量+1
	switch (kind) {
		case 4:user_read.record.numberOfDiv++; break;
		case 3:user_read.record.numberOfMul++; break;
		case 2:user_read.record.numberOfSub++; break;
		case 1:user_read.record.numberOfAddQuestions++; break;
	}
	if (isRight) {
		user_read.record.numberOfSolvedQuestions++;		//对题量+1
		switch (kind) {
			case 4:user_read.record.solvedDiv++; break;
			case 3:user_read.record.solvedMul++; break;
			case 2:user_read.record.solvedSub++; break;
			case 1:user_read.record.solvedAdd++; break;
		}
		return 0;
	}
	else {
		system("cls");
		printf("要将这道错题存入错题库吗？\n\n");
		printf("<是>                    <否>");
		for (;;) {
			GetConsoleMode(hStdIn, &mode);
			mode |= ENABLE_MOUSE_INPUT;
			SetConsoleMode(hStdIn, mode);
			ReadConsoleInput(hStdIn, &inRec, 1, &mode);
			if (inRec.EventType == MOUSE_EVENT &&
				inRec.Event.MouseEvent.dwButtonState == FROM_LEFT_1ST_BUTTON_PRESSED) {
				if (inRec.Event.MouseEvent.dwMousePosition.Y == 2) {
					if (inRec.Event.MouseEvent.dwMousePosition.X >= 0 &&
						inRec.Event.MouseEvent.dwMousePosition.X < 3) {
						option = 1;
						break;
					}
					else if (inRec.Event.MouseEvent.dwMousePosition.X >= 21 &&
						inRec.Event.MouseEvent.dwMousePosition.X < 30) {
						option = 0;
						break;
					}
				}
			}
		}
		if (option) {
			enterBank(user_read.name, kind, num1, num2);
		}
		return option;
	}
}

void enterBank(char name[], int kind, int num1, int num2) {		//feng
	FILE *fp;
	WrongBank wbank;   //将参数存入错题结构体当中 
	wbank.kind = kind;
	wbank.num1 = num1;
	wbank.num2 = num2;
	strcpy(wbank.name, name);
	fp = fopen("错题库.txt", "a");    //打开文件 
	fwrite(&wbank, sizeof(WrongBank), 1, fp);   //添加记录 
	fclose(fp);   //end 
}

void save() {					//chen
	FILE *fp = fopen("data.txt", "rt+");			//打开存储数据的文本（允许读写）
	User user;										//新建一个局部变量user，用以遍历数据文件
	while (fread(&user, sizeof(user), 1, fp)) {
		if (!strcmp(user.name, user_read.name)) {	//strcmp()返回值为0，即名字对应
			break;
		}
	}
	fseek(fp, -(long)sizeof(User), SEEK_CUR);		//将指针fp的位置设置到当前位置的前一页（即对应用户之前）
	fwrite(&user_read, sizeof(User), 1, fp);
	fclose(fp);
}

int continueOrNot() {											//chen
	HANDLE hStdIn = GetStdHandle(STD_INPUT_HANDLE);
	INPUT_RECORD inRec;
	DWORD mode;

	system("cls");
	printf("还想要继续做题吗？\n\n");
	printf("<是>               <否>\n");
	for (;;) {
		GetConsoleMode(hStdIn, &mode);
		mode |= ENABLE_MOUSE_INPUT;
		SetConsoleMode(hStdIn, mode);
		ReadConsoleInput(hStdIn, &inRec, 1, &mode);
		if (inRec.EventType == MOUSE_EVENT &&
			inRec.Event.MouseEvent.dwButtonState == FROM_LEFT_1ST_BUTTON_PRESSED) {
			if (inRec.Event.MouseEvent.dwMousePosition.Y == 2) {
				if (inRec.Event.MouseEvent.dwMousePosition.X >= 0 &&
					inRec.Event.MouseEvent.dwMousePosition.X < 6) {
					system("cls");
					return 1;
				}
				else if (inRec.Event.MouseEvent.dwMousePosition.X >= 17 &&
					inRec.Event.MouseEvent.dwMousePosition.X < 22) {
					system("cls");
					return 0;
				}
			}
		}
	}
}

void checkRecord() {										//chen
	printf("你的做题记录如下：\n");
	printf("总做题数：%d\n",user_read.record.numberOfQuestions);
	printf("总正确数：%d\n", user_read.record.numberOfSolvedQuestions);
	if (user_read.record.numberOfSolvedQuestions == 0) {
		printf("总正确率0.00%%\n");
	}
	else {
		printf("总正确率：%.2lf%%\n",
			user_read.record.numberOfSolvedQuestions *100.0 / user_read.record.numberOfQuestions);
	}
	printf("加法做题量：%d\t\t", user_read.record.numberOfAddQuestions);
	printf("加法正确题数：%d\n", user_read.record.solvedAdd);
	printf("减法做题量：%d\t\t", user_read.record.numberOfSub);
	printf("减法正确题数：%d\n", user_read.record.solvedSub);
	printf("乘法做题量：%d\t\t", user_read.record.numberOfMul);
	printf("乘法正确题数：%d\n", user_read.record.solvedMul);
	printf("除法做题量：%d\t\t", user_read.record.numberOfDiv);
	printf("除法正确题数：%d\n", user_read.record.solvedDiv);
	system("pause");
	system("cls");
}

Node* sort(Node* pHead) {						//chen
	Node* p = pHead->pNext;
	Node* q = pHead->pNext;
	User temp;
	for (p; p->pNext; p = p->pNext) {
		for (q = pHead->pNext; q->pNext; q = q->pNext) {
			if (q->user.record.numberOfQuestions < q->pNext->user.record.numberOfQuestions) {
				temp = q->pNext->user;
				q->pNext->user = q->user;
				q->user = temp;
			}
		}
	}
	return pHead;
}

int checkRepeat(User newUser) {
	FILE *fp = fopen("data.txt", "r");								//打开存储数据的文件
	User user;

	for (;;) {
		if (fread(&user, sizeof(user), 1, fp)) {
			if (!strcmp(newUser.account, user.account)) {			//若已存在相同用户名
				return 1;											//返回1
			}
			else if (!strcmp(newUser.name, user.name)) {			//若已存在相同姓名
				return 2;											//返回2
			}
		}
		else {
			return 0;												//返回0（表示无重复）
		}
	}
}

void printErrorSituation(int situation) {						//chen
	switch (situation) {
		case 5:printf("设定的密码长度超过20个字符，请重试\n"); break;
		case 4:printf("设定的账号长度超过20个字符，请重试\n"); break;
		case 3:printf("你输入的并非数字，请重新输入\n"); break;
		case 2:printf("你已经拥有一个账号了，请不要重复注册\n"); break;
		case 1:printf("该用户名已存在，请不要重复注册\n"); break;
		default: break;
	}
}

void quit() {
	printf("　　　　　　　　　　　       %%%%%%%%%%%\n");
	printf("　　　　　　　　　　　　   %%%%/\\%%%%/\\%%%%,\n");
	printf("　　　　　　　　　　　   %%%%%%%%/c ''J/ %%%%%%%,\n");
	printf("　 %.　　　　　　　　     %%%%%%%%/ d　b \\%%%%%%%%% \n");
	printf("　 `%%.　　　　 _　   %%%%%%%%%%%%%%|　　_　|%%%%%%%%%%%%% \n");
	printf("　　`%%　　　.-’　`'~-'`%%%%%%%%%%(=_Y_=)%%%%%%%%%%%%%%     ~~~  再见\n");
	printf("　　 //　　.’　　 `.　 `%%%%%%%%%%%% \\7/%%%%%%%%%%%____\n");
	printf("　　 //　　.’　　 `.　　  %%%%%%%%\\7/%%%%%%%%____ \n");
	printf("　　((　　/　　,　 ;　　　`  %%%%%%%%%%____)))\n");
	printf("　　`.`--’　,　, ,’　_,`-._%%%%%%%_`-,\n");
	printf("\n程序将在3秒后退出。");
	Sleep(3000);
}


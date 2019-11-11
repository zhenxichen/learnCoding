#ifndef _CALCULATE_H_
#define _CALCULATE_H_

#define IsOpt(x) ((x=='+')||(x=='-')||(x=='*')||(x=='/'))

bool paren(const char* string,int len);			//判断括号是否匹配
bool checkChar(const char* string, int len);	//检查是否有非法符号
bool checkInput(const char* string, int len);	//检查输入的表达式是否合法
bool checkOpt(const char* string, int len);		//检查输入表达式是否存在连续的运算符
void toRPN(const char* exp, int len,string& rpn);		
bool isDigit(char c);							//判断字符是否为数字
int orderBetween(const char top, const char c);	//比较两运算符优先级大小
int getPri(char c);								//获取运算符优先级大小数值
float calcu(float opnd1, char op, float opnd2);	//二元计算
float rpnCal(string rpn);						//计算逆波兰式
float readNumber(string str, Stack<float> &opnd);	//读取数字

#endif

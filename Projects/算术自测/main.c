#include "ari.h";

/*本程序需要使用VS2017进行编译，
并且由于VS2017解决方案设置的原因，在其他计算机运行时需要新建解决方案，
同时需要修改控制台设置，关闭快速编辑模式和插入模式才可正常运行*/

int main() {								//chen
	int loginResult = 0;					//记录用户登录结果（0失败，1成功）
	User user;
	int systemContinue = 1;					//标记程序是否继续运行（0为停止，1为继续）

	SetConsoleTitle("小学算术自测系统");

	while (!loginResult) {
		loginResult = loginInterface(&user);
		system("cls");
	}

	
	
	while (systemContinue) {
		if (user_read.isTeacher) {
			systemContinue = teacherInterface(&user);
		}
		else {
			systemContinue = studentInterface(&user);
		}
	}

	save();
	quit();

	return 0;
}

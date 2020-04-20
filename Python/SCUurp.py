#尝试通过requests的post请求登录四川大学教务系统

import requests
from PIL import Image

loginURL = 'http://zhjw.scu.edu.cn/login'
captchaURL = 'http://zhjw.scu.edu.cn/img/captcha.jpg'   #登录界面验证码的地址
checkURL = 'http://zhjw.scu.edu.cn/j_spring_security_check'  #校验地址
curriculumURL = 'http://zhjw.scu.edu.cn/student/courseSelect/thisSemesterCurriculum/index' #课程表页面

header = {
  'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.113 Safari/537.36',
  'Host': 'zhjw.scu.edu.cn',
  'Referer': 'http://zhjw.scu.edu.cn/login'
}

data = {
  'j_username': '2018141463009',
  'j_password': 'bf9fea71ace5516c954cb7be943302c3'   #密码为md5转码
}

# 服务器本身并不知道我们获得了哪张验证码图片，而只是将图片的识别码作为一个cookie发送给客户端
# 因此，我们在提交验证码时需要将cookie同时提交

#获取验证码
session = requests.Session()
session.get(loginURL)
captchaRes = session.get(captchaURL)
fp = open("captcha.jpg", 'wb')
fp.write(captchaRes.content)
fp.close()
image = Image.open('captcha.jpg')
image.show()
data['j_captcha'] = input('输入验证码：')

#发送POST请求
postRes = session.post(url= checkURL, headers= header, data= data)
print(postRes)
res = session.get(curriculumURL)
res = session.get('http://zhjw.scu.edu.cn/student/courseSelect/thisSemesterCurriculum/ajaxStudentSchedule/curr/callback')
print(res.text)
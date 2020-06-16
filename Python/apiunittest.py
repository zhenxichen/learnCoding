#	apiunittest.py
# 用以测试图书管理系统后端所提供的API

import unittest
import requests

class LoginTestCase(unittest.TestCase):

	def test_login_password_error(self):
		form = {
			'username': 'fyl666', #existed username
			'password': '111'	  #wrong password
		}
		apiaddress = 'http://49.232.26.148:7000/api/login/'
		res = requests.post(apiaddress, data= form)
		self.assertEqual(res.text, "Wrong Password.")
	
	def test_login_username_not_exist(self):
		form = {
			'username': '111', #not existed username
			'password': '111'	  
		}
		apiaddress = 'http://49.232.26.148:7000/api/login/'
		res = requests.post(apiaddress, data= form)
		self.assertEqual(res.text, "Username Not Exist")
	
	def test_login_success(self):
		form = {
			'username': 'fyl666', #existed username
			'password': '123'	  #wrong password
		}
		apiaddress = 'http://49.232.26.148:7000/api/login/'
		res = requests.post(apiaddress, data= form)
		expected_res = "{'username': 'fyl666', 'name': 'fyl'}"
		self.assertEqual(res.text, expected_res)

class CustomerTestCase(unittest.TestCase):

	def test_ID_existed(self):
		form = {
			'CustomerID': 0,
			'name': '路人',
			'address': ''
		}
		apiaddress = 'http://49.232.26.148:7000/api/newCustomer/'
		res = requests.post(apiaddress, data= form)
		self.assertEqual(res.text, "ID existed")
	
	def test_customer_success(self):
		form = {
			'CustomerID': 233,
			'name': '测试顾客',
			'address': ''
		}
		apiaddress = 'http://49.232.26.148:7000/api/newCustomer/'
		res = requests.post(apiaddress, data= form)
		self.assertEqual(res.text, "success")

if __name__ == "__main__":
	unittest.main()
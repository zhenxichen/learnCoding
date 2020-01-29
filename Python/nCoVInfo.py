import requests
import re


#找了个MOOC稍微学习了一下python爬虫，通过学习尝试做一个爬取2019-nCoV最新疫情的爬虫
#其实本来是想做一个爬取教授信息的爬虫（方便一个个问过去找实验室实习- -）
#但是学校网站的编码方式属实看不懂，怎么都整不了，只好换个题目了

def getHTMLText(url):
    try:
        r = requests.get(url,timeout=30)
        r.raise_for_status()            #如果状态码不是200，则引发HTTP Error异常
        r.encoding = r.apparent_encoding
        return r.text
    except:
        return "Error"

def fillList(infoList,demo):
    try:
        #cityList = re.findall(r'"cityName":".+?"',demo)
        #confirmedList = re.findall(r'"confirmedCount":\d+',demo)
        cityList = re.findall(r'"cityName":".+?","confirmedCount":\d+',demo)
        for i in range(len(cityList)):
            nameStr = cityList[i].split(',')[0]
            name = eval(nameStr.split(':')[1])          #eval用于去除‘’，split[1]实际就是取：后面的部分
            numberStr = cityList[i].split(',')[1]
            number = eval(numberStr.split(':')[1])
            infoList.append([name,number])
    except:
        print("Error")



def printList(infoList):
    tplt = "{:8}\t{:8}"
    print(tplt.format("地名","确诊数"))
    for g in infoList:
        print(tplt.format(g[0],g[1]))


def main():
    url = 'https://3g.dxy.cn/newh5/view/pneumonia_peopleapp?from=timeline&isappinstalled=0'
    infoList = []
    demo = getHTMLText(url)
    fillList(infoList,demo)
    printList(infoList)

main()



/**尝试用js写一个计算SCU教务处GPA的脚本
 * 计算方法采用的不是川大算法，而是WES算法
*/

var tbody = document.getElementById("scoreintbody");
var trs = tbody.childNodes;
var totalGP = 0.0;
var totalCredit = 0.0;
var gpa = 0.0;
var getGPA = function(score){
    if(score > 84){
        return 4.0;
    }
    else if(score > 74){
        return 3.0;
    }
    else if(score >= 60){
        return 2.0;
    }
    else{
        return 0.0;
    }
}

trs.forEach(item => {
    var tds = item.childNodes;
    var credit = tds[5].innerHTML;
    //console.log(credit);
    var score = tds[9].innerHTML;
    var currGPA = getGPA(score);
    totalGP += currGPA*credit;
    totalCredit += credit*1.0;
    //console.log(totalCredit);
})

gpa = totalGP / totalCredit;

//console.log("totalGP:",totalGP);
//console.log("totalCredit:",totalCredit);
console.log("GPA:",gpa);

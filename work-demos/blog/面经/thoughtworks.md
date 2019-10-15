来自thoughtworks的homework
请使用底部的链接提交你的代码,并将homework以附件的形式发送给你的招聘者

注意:
1.要求使用 Java, Ruby, C#, Python, Clojure, Scala or JavaScript.
2. 输入必须支持文本文件输入(There must be a way to supply the application with the input data via text file)
3. 程序必须可运行
4. 通过测试
5. 将结果附件给职位招聘人员
6. 以如下方式命名你的作业名: {姓名}--{trains/conference/galaxy}--{编程语言}

规则:
1. 代码中不能有额外依赖
2. 测试和编译可以借助额外依赖
比如, 你可以使用单元测试库或编译工具(如junit)

不能包含任何可执行文件,如.exe或.lib
我们会需要编译你的代码, 所以请提交一个源码和supporting files的zip包, 不要包含任何编译后的文件
3. 请包含一个简要的设计和假设说明, 以及运行应用程序的详细说明

4. 我们评估: 设计模式,面向对象编程思想, 我们希望你能提交生产质量级别的代码, 可执行,可维护,可扩展
You don’t need to gold plate your solution, however we are looking for something more than a bare-bones algorithm.

5. 请不要将homework外传

6. 请在上传之前用zip打包你的代码,保证没有可执行文件,

7. 可执行文件包含:asp, bat, class, cmd, com, cpl, dll, exe, fon, hta, ini, ins, iw, jar, jsp, js, jse, pif, scr, shs, sh, vb, vbe, vbs, ws, wsc, wsf, wsh & msi

 
问题1: Trains
 
The local commuter railroad services a number of towns in Kiwiland.  Because of monetary concerns, all of the tracks are 'one-way.'  That is, a route from Kaitaia to Invercargill does not imply the existence of a route from Invercargill to Kaitaia.  In fact, even if both of these routes do happen to exist, they are distinct and are not necessarily the same distance!
 
The purpose of this problem is to help the railroad provide its customers with information about the routes.  In particular, you will compute the distance along a certain route, the number of different routes between two towns, and the shortest route between two towns.
 
Input:  A directed graph where a node represents a town and an edge represents a route between two towns.  The weighting of the edge represents the distance between the two towns.  A given route will never appear more than once, and for a given route, the starting and ending town will not be the same town.
 
Output: For test input 1 through 5, if no such route exists, output 'NO SUCH ROUTE'.  Otherwise, follow the route as given; do not make any extra stops!  For example, the first problem means to start at city A, then travel directly to city B (a distance of 5), then directly to city C (a distance of 4).
The distance of the route A-B-C.
The distance of the route A-D.
The distance of the route A-D-C.
The distance of the route A-E-B-C-D.
The distance of the route A-E-D.
The number of trips starting at C and ending at C with a maximum of 3 stops.  In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
The length of the shortest route (in terms of distance to travel) from A to C.
The length of the shortest route (in terms of distance to travel) from B to B.
The number of different routes from C to C with a distance of less than 30.  In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
 
Test Input:
For the test input, the towns are named using the first few letters of the alphabet from A to D.  A route between two towns (A to B) with a distance of 5 is represented as AB5.
Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
Expected Output:
Output #1: 9
Output #2: 5
Output #3: 13
Output #4: 22
Output #5: NO SUCH ROUTE
Output #6: 2
Output #7: 3
Output #8: 9
Output #9: 9
Output #10: 7
 
问题2: 会议追踪管理
 
You are planning a big programming conference and have received many proposals which have passed the initial screen process but you're having trouble fitting them into the time constraints of the day -- there are so many possibilities! So you write a program to do it for you.
The conference has multiple tracks each of which has a morning and afternoon session.
Each session contains multiple talks.
Morning sessions begin at 9am and must finish before 12 noon, for lunch.
Afternoon sessions begin at 1pm and must finish in time for the networking event.
The networking event can start no earlier than 4:00 and no later than 5:00.
No talk title has numbers in it.
All talk lengths are either in minutes (not hours) or lightning (5 minutes).
Presenters will be very punctual; there needs to be no gap between sessions.
 
Note that depending on how you choose to complete this problem, your solution may give a different ordering or combination of talks into tracks. This is acceptable; you don’t need to exactly duplicate the sample output given here.
 
Test input:
Writing Fast Tests Against Enterprise Rails 60min
Overdoing it in Python 45min
Lua for the Masses 30min
Ruby Errors from Mismatched Gem Versions 45min
Common Ruby Errors 45min
Rails for Python Developers lightning
Communicating Over Distance 60min
Accounting-Driven Development 45min
Woah 30min
Sit Down and Write 30min
Pair Programming vs Noise 45min
Rails Magic 60min
Ruby on Rails: Why We Should Move On 60min
Clojure Ate Scala (on my project) 45min
Programming in the Boondocks of Seattle 30min
Ruby vs. Clojure for Back-End Development 30min
Ruby on Rails Legacy App Maintenance 60min
A World Without HackerNews 30min
User Interface CSS in Rails Apps 30min
 
Test output:
Track 1:
09:00AM Writing Fast Tests Against Enterprise Rails 60min
10:00AM Overdoing it in Python 45min
10:45AM Lua for the Masses 30min
11:15AM Ruby Errors from Mismatched Gem Versions 45min
12:00PM Lunch
01:00PM Ruby on Rails: Why We Should Move On 60min
02:00PM Common Ruby Errors 45min
02:45PM Pair Programming vs Noise 45min
03:30PM Programming in the Boondocks of Seattle 30min
04:00PM Ruby vs. Clojure for Back-End Development 30min
04:30PM User Interface CSS in Rails Apps 30min
05:00PM Networking Event
 
Track 2:
09:00AM Communicating Over Distance 60min
10:00AM Rails Magic 60min
11:00AM Woah 30min
11:30AM Sit Down and Write 30min
12:00PM Lunch
01:00PM Accounting-Driven Development 45min
01:45PM Clojure Ate Scala (on my project) 45min
02:30PM A World Without HackerNews 30min
03:00PM Ruby on Rails Legacy App Maintenance 60min
04:00PM Rails for Python Developers lightning
05:00PM Networking Event
 
问题3: Merchant's Guide to the Galaxy(银河系商人指南)
 
在银河系中买卖需要你能够转换数字和单位, 你决定自己写个程序解决

星际间使用的数字遵循着类似罗马数字的规律. 并且你已经收集了他们之间的转换方法
The numbers used for intergalactic transactions follows similar convention to the roman numerals and you have painstakingly collected the appropriate translation between them
 
罗马数字规律如下:
I  1

V  5

X  10

L  50

C  100

D  500

M  1,000

 
例如 MMVI = 1000 + 1000 + 5 + 1 = 2006. 通常情况下,符号按照值得大小放置顺序,开始是最大的值. 
但是,当较小值在最大值前面时,较小值会被较大值减去, 结果添加到所有值里面. 例如: MCMXLIV = 1000 + (1000 − 100) + (50 − 10) + (5 − 1) = 1944.
 
 符号"I", "X", "C", and "M" 可以连续重复3次, 但是不能重复更多(他们可以重复四次,只要第三个值和第四个值之间有个较小值打断,例如XXXIX)
 "D", "L", 和 "V" 永远不能被重复
"I" 只能被 "V" 和 "X" 减去. "X" 只能被 "L" 和 "C" 减去. "C" 只能被 "D" 和 "M" 减去. "V", "L", "D" 永远不能被减.
只有一个小值得符号可以被任何大值得符号减去(Only one small-value symbol may be subtracted from any large-value symbol).
一个罗马数字可以打散成数字位, 每个非0数字位应该被单独对待, 比如: 1,000 = M, 900 = CM, and 3 = III. Therefore, 1903 = MCMIII.
 
 你的程序的输入包含你记录的星级单位和罗马数字之间的转换关系, 你可能需要适当的处理无效查询
 (Input to your program consists of lines of text detailing your notes on the conversion between intergalactic units and roman numerals.
  You are expected to handle invalid queries appropriately.)
 
Test input:
glob is I
prok is V
pish is X
tegj is L
glob glob Silver is 34 Credits
glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
how much is pish tegj glob glob ?
how many Credits is glob prok Silver ?
how many Credits is glob prok Gold ?
how many Credits is glob prok Iron ?
how much wood could a woodchuck chuck if a woodchuck could chuck wood ?
 
Test Output:
pish tegj glob glob is 42
glob prok Silver is 68 Credits
glob prok Gold is 57800 Credits
glob prok Iron is 782 Credits
I have no idea what you are talking about
 
Copyright 2012 ThoughtWorks, Inc


作业一定要注意：
运用面向对象的方法进行代码结构设计（层次、分类等），
注重代码规范（cleancode、可读性、可扩展性）
工程实践（readme文档、目录结构、构建工具、测试）等


请帮忙检视下项目,是否达到要求的: 生产环境级别
3. pom.xml中哪些模块写上更规范? 编译, 打包插件用哪个合适?
 
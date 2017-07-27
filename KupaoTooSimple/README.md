### 介绍
暑假java补课的第一周作业，一个类似天天酷跑的简单图形界面程序．
参考了github 上一个使用JAVA图形界面做的flappy bird, 在读懂代码的基础上
自己重写了一个不需要操纵键盘，可以自动跳跃避免障碍物的简单程序
### 结构介绍
#### App.java
主函数所在的类，启动Jframe, 在Jframe中加载KupaoPanel(继承自Jpanel,负责全部
绘图，可以说是程序最终展示的地方）
#### KupaoPanel.java
从Game中获取界面Render(一个组合图片和图片位置的类)的集合，并将他们全部绘制出来
#### Game.java
控制界面中图片的位置变化，并用一个getRenders() 方法将他们全部返回，而
这个class的render 又是从Dot,Barrier类中获取到的
#### Dot.java
控制点的运动
#### Barrier.java 
控制障碍物的移动，具体表现为障碍物向左移动，给人感觉dot在移动
#### Loader.java
获取文件中的矢量图，返回一个Image对象
#### Render.java
可以简单理解为包含位置和图片（加载到内存中的Image）的类

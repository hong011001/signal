#使用说明#
1.需要安装JDK
2.将Message.java,Signal.java,signal_info.txt 3个文件放在同一路径下
3.signal_info代表的是无人机记录的消息，Message是消息的对象模型，Signal是主程序
4.在3个文件的路径下，打开cmd窗口
5.执行javac Message.java
6.执行javac Signal.java
7.执行java Signal + 消息序号(支持多个消息Id)，如java Signal 1 2 3 4 5 6 7

若指定的消息不存在，则输出Cannot find{消息序号}
若指定的消息不存在，且无人要正常，则输出｛无人机ID｝｛消息序号｝｛X｝｛Y｝｛Z｝
若指定的消息存在，但无人机故障，则输出 Error：｛消息序号｝
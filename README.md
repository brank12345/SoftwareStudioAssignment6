# Software Studio Assignment 6

## Notes
+ You will have to import the libraries on your own. All the libraries are provided on iLMS.
+ Even you followed the design TA shown in the video, you still have to explain the control of the program in the next section.

## Explanation of the Design
Explain your design in this section.  
Example:
### Operation
+ Clicking on the button "Add All": users can add all the characters into network to be analyzed.
+ Clicking on the button "Clear": users can remove all the characters from network.
+ Hovering on the character: the name of the character will be revealed.
+ By dragging the little circle and drop it in the big circle, the character will be added to the network.
+ By pressing key 1~7 on the keyboard, users can switch between episodes.
+ ...etc.

### Visualization
+ The width of each link is visualized based on the value of the link.
+ ...etc.

在這次assignment中，講解以下功能：
按鍵ADD，當按鍵按下時，記住哪些求是否在園內的參數都會設為1，而且圓內球的數量會增加為characters的總數，並且把每個點
平均的放在圓上，計算公式如下：x座標：大圓的x座標 ＋ 半徑 ＊ cos(360度/總數＊第幾個放入)、y座標：大圓的y座標 ＋ 半
徑 ＊ sin(360度/總數＊第幾個放入)

按鍵CLEAR：做法與ADD相似，記住哪些求是否在園內的參數都會設為0，而且圓內球的數量歸0，把characters的x、y座標設為一開始位置，方式跟set up的時候一樣。

拖曳characters：用mouseDragged()，把mouseX、mouseY無時無刻都輸入進去characters的x、y，當characters的x、y的座標距離小於半徑時會讓ballinside＋＋，也會該數字編號的inside[]的值變為1

在連線方面，每次在draw的時候，都會搜尋inside[]值是1的，找到時，會再搜尋JSON裡面和index相同的source，並且inside[]的值是1，就把2個點連起來，而且會根據value來決定粗細，strokeWeight(value)

更換file：用keyPressed，用getKeyCode()，1~7代表49~55，每次和檔案，都要做初始化，像：inside[]裡面要歸0、ballinside歸0、characters全部清空。

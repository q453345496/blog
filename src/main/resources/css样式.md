#样式居中
设置一定宽度，然后设置外间距
```
max-width:600px;
margin:0 auto;
```

#块级元素和行内元素
块级元素对应于`display:block`
行内元素对应于`display:inline`

块级元素会独占一行，默认情况下，其宽度自动填满其父元素宽度
行内元素不会独占一行，相邻的行内元素会排列在同一行里，直到一行排不下，才会换行，其**宽度随元素的内容而变化**

块级元素可以设置width,height属性，**即使设置了宽度,仍然是独占一行**
行内元素设置width，height属性无效，它的长度高度主要**根据内容决定**

块级元素可以设置margin和padding属性.
行内元素的margin和padding属性,水平方向的\*-left,\*-right都产生边距效果,但竖直方向的\*-top,\*-bottom却不 会产生边距效果

#display:fixed
相对于窗口固定布局，即不随滚动条变化

#盒子模型
盒子模型
```实际宽度 = width + padding + border```

现代解决方法:
```
-webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
```
        
#关于position
**static**
是默认值。元素不会被特殊的定位，元素出现在正常的流中（忽略 top, bottom, left, right 或者 z-index 声明）

**relative**
在一个相对定位（position属性的值为relative）的元素上设置 top 、 right 、 bottom 和 left 属性会使其偏离其正常位置。**其他的元素的位置不会受该元素的影响发生位置改变来弥补它偏离后剩下的空隙**。
例如：
css样式
```
.a {
  height: 100px;
  background-color:blue;
}
.b {
  height: 100px;
  background-color:red;
  position:relative;
  top:20px;
}
.c {
  height: 100px;
  background-color:yellow;
}
```
html片段
```
<div class="a">aaa</div>
<div class="b">bbb</div>
<div class="c">ccc</div>
```
b会往下移动20px，并覆盖在c上面，但是c元素不会往下移动偏离其原来位置

#关于border-radius
父子元素必须设置一样的`border-radius`值，否则在父子元素颜色不一样或者子元素有其他边框颜色时，会出现空白填充

#关于a:hover等伪元素
a下的元素如果没有指定自己的`color`，则默认会被继承`a:hover`时的`color`
例如：
css样式
```
a{
	color:yellow;
}
a:hover{
	color:red;
}
.a{
	display: block;
	color: blue;
}
.b{
	color:green;
}
```
html片段
```
	<a class="a">
		<span>aaa</span>
		<span class="b">bbb</span>
	</a>
```
此时aaa显示蓝色，bbb显示绿色，当鼠标悬停时在a标签的任意位置，aaa显示为红色，而bbb依旧是绿色
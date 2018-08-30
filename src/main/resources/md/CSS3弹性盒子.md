#CSS3 弹性盒子(Flex Box)
###弹性盒子是 CSS3 的一种新的布局模式
CSS3 弹性盒（ Flexible Box 或 flexbox），是一种当页面需要适应不同的屏幕大小以及设备类型时确保元素拥有恰当的行为的布局方式。

引入弹性盒布局模型的目的是**提供一种更加有效的方式来对一个容器中的子元素进行排列、对齐和分配空白空间**

###CSS3 弹性盒子内容
弹性盒子由**弹性容器(Flex container)**和**弹性子元素(Flex item)**组成。

弹性容器通过设置 `display`属性的值为 `flex` 或 `inline-flex`将其定义为弹性容器。

弹性容器内包含了一个或多个弹性子元素。

*注意： 弹性容器外及弹性子元素内是正常渲染的。弹性盒子只定义了弹性子元素如何在弹性容器内布局*

弹性子元素通常在弹性盒子内一行显示。**默认情况每个容器只有一行**。

###flex-direction属性
`flex-direction` 属性指定了弹性子元素在父容器中的位置。

- `row`：横向从左到右排列（左对齐），默认的排列方式
- `row-reverse`：反转横向排列（右对齐，从后往前排，最后一项排在最前面
- `column`：纵向排列。
- `column-reverse`：反转纵向排列，从后往前排，最后一项排在最上面

###justify-content属性
内容对齐（justify-content）属性应用在弹性容器上，把弹性项沿着弹性容器的主轴线（main axis）对齐

- `flex-start`：弹性项目向行头紧挨着填充
- `flex-end`： 弹性项目向行尾紧挨着填充
- `center`： 弹性项目居中紧挨着填充
- `space-between`：弹性项目平均分布在该行上
- `space-around`：弹性项目平均分布在该行上，两边留有一半的间隔空间

###align-items属性
align-items 设置或检索弹性盒子元素在侧轴（纵轴）方向上的对齐方式

###align-self属性
align-self 属性用于设置弹性元素自身在侧轴（纵轴）方向上的对齐方式,**align-self会覆盖容器的align-items属性**
- `flex-start`：弹性盒子元素的侧轴（纵轴）起始位置的边界紧靠住该行的侧轴起始边界
- `flex-end`：弹性盒子元素的侧轴（纵轴）起始位置的边界紧靠住该行的侧轴结束边界
- `center`：弹性盒子元素在该行的侧轴（纵轴）上居中放置
- `baseline`：该值将参与基线对齐
- `stretch`：**默认值**,如果指定侧轴大小的属性值为'auto'，则其值会使项目的边距盒的尺寸尽可能接近所在行的尺寸，但同时会遵照'min/max-width/height'属性的限制。

###flex-wrap属性
flex-wrap 属性用于指定弹性盒子的子元素换行方式
- `nowrap` - **默认**， 弹性容器为单行。该情况下弹性子项可能会溢出容器
- `wrap` - 弹性容器为多行。该情况下弹性子项溢出的部分会被放置到新行，子项内部会发生断行
- `wrap-reverse` -反转 wrap 排列

###align-content属性
- `align-content`：属性用于修改 flex-wrap 属性的行为。类似于 align-items, 但**它不是设置弹性子元素的对齐，而是设置各个行的对齐**
- `stretch`：默认。各行将会伸展以占用剩余的空间。
- `flex-start`：各行向弹性盒容器的起始位置堆叠。
- `flex-end`：各行向弹性盒容器的结束位置堆叠。
- `center`：各行向弹性盒容器的中间位置堆叠。
- `space-between`：各行在弹性盒容器中平均分布。
- `space-around`：各行在弹性盒容器中平均分布，两端保留子元素与子元素之间间距大小的一半。

###flex属性
flex 属性用于指定弹性子元素如何分配空间

```
.item1 {
    -webkit-flex: 2;
    flex: 2;
}
 
.item2 {
    -webkit-flex: 1;
    flex: 1;
}
 
.item3 {
    -webkit-flex: 1;
    flex: 1;
}
```
上面的实例中，第一个弹性子元素占用了 2/4 的空间，其他两个各占 1/4 的空间
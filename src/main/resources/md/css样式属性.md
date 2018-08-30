#字体属性：(font)

大小 {font-size: x-large;}(特大) xx-small;(极小) 一般中文用不到，只要用数值就可以，单位：PX、PD

样式 {font-style: oblique;}(偏斜体) italic;(斜体) normal;(正常)

行高 {line-height: normal;}(正常) 单位：PX、PD、EM

粗细 {font-weight: bold;}(粗体) lighter;(细体) normal;(正常)

变体 {font-variant: small-caps;}(小型大写字母) normal;(正常)

大小写 {text-transform: capitalize;}(首字母大写) uppercase;(大写) lowercase;(小写) none;(无)

修饰 {text-decoration: underline;}(下划线) overline;(上划线) line-through;(删除线) blink;(闪烁)

常用字体： (font-family)

"Courier New", Courier, monospace, "Times New Roman", Times, serif, Arial, Helvetica, sans-serif, Verdana

#背景属性： (background)

色彩 {background-color: #FFFFFF;}

图片 {background-image: url();}

重复 {background-repeat: no-repeat;}

滚动 {background-attachment: fixed;}(固定) scroll;(滚动)

位置 {background-position: left;}(水平) top(垂直);

简写方法 {background:#000 url(..) repeat fixed left top;}

#区块属性： (Block)

字间距 {letter-spacing: normal;} 数值

对齐 {text-align: justify;}(两端对齐) left;(左对齐) right;(右对齐) center;(居中)

缩进 {text-indent: 数值px;}

垂直对齐 {vertical-align: baseline;}(基线) sub;(下标) super;(下标) top; text-top; middle; bottom; text-bottom;

词间距{word-spacing: normal;} 数值

空格{white-space: pre;}(保留) nowrap;(不换行)

显示 {display:block;}(块) inline;(内嵌) list-item;(列表项) run-in;(追加部分) compact;(紧凑) marker;(标记) table; inline-table; table-raw-group; table-header-group; table-footer-group; table-raw; table-column-group; table-column; table-cell; table-caption;(表格标题)

#方框属性： (Box)

width:; height:; float:; clear:both; margin:; padding:; 顺序：上右下左

#边框属性： (Border)

border-style: dotted;(点线) dashed;(虚线) solid; double;(双线) groove;(槽线) ridge;(脊状) inset;(凹陷) outset;

border-width:; 边框宽度

border-color:#;

简写方法border：width style color; 

#列表属性： (List-style)

类型list-style-type: disc;(圆点) circle;(圆圈) square;(方块) decimal;(数字) lower-roman;(小罗码数字) upper-roman; lower-alpha; upper-alpha;

位置list-style-position: outside;(外) inside;

图像list-style-image: url(..);

#定位属性： (Position)

Position: absolute; relative; static;

visibility: inherit; visible; hidden;

overflow: visible; hidden; scroll; auto;

clip: rect(12px,auto,12px,auto) (裁切)
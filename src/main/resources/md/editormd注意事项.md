#集成editormd.js
1. 数学公式开启需要修改js中的 
```
editormd.katexURL  = {
    css : "//cdn.bootcss.com/KaTeX/0.9.0/katex.min",
    js  : "//cdn.bootcss.com/KaTeX/0.9.0/katex.min"
};
目前已经下载katex到plugins目录下,并且修改了editormd的源码,注意展示页面要显式的设置path值
```
2. 显示html需要引入一下js,否则会报错
```
<script src="<%=path%>/resources/editor/lib/marked.min.js"></script>
<script src="<%=path%>/resources/editor/lib/prettify.min.js"></script>
<script src="<%=path%>/resources/editor/lib/flowchart.min.js"></script>
<script src="<%=path%>/resources/editor/lib/raphael.min.js"></script>
<script src="<%=path%>/resources/editor/lib/underscore.min.js"></script>
<script src="<%=path%>/resources/editor/lib/sequence-diagram.min.js"></script>
<script src="<%=path%>/resources/editor/lib/flowchart.min.js"></script>
<script src="<%=path%>/resources/editor/lib/jquery.flowchart.min.js"></script>
```
3. 显示html时在div下需要有一个textarea

4. 显示html时
`htmlDecode : "style,script,sub,sup|on*"`,//表示这些标签不进行解析,|后为属性
`htmlDecode : true`,则不过滤
注意，优酷视频复制出来的连接有问题,allowfullscreen带有引号导致报错
```
<iframe height=498 width=510 src='http://player.youku.com/embed/XMzY3NTIxMjgwOA==' frameborder=0 'allowfullscreen'></iframe>
```
5. 图片上传表单参数为`editormd-image-file`

6. 在edirotmd中使用公式的问题
- 行内公式，Latex使用$equation$来表示行内公式，不过在Katex中使用\(equation\)来表示。（在editor.md中使用$$）

- 因为Markdown中\是转义字符，所以在Markdown中输入行内公式使用\\(equation\\)。其他地方如 \{, \\ 需要使用 \\{, \\\\来表示。

- **由于在markdown中 _ 表示 em 标签，故在Latex使用下标时需要使用 \_**。

7. 关于竖杠
一般情况下，竖杠可以使用`&#124;`来表示
但在html页面中，内容文本中的转义符已经正常显示，因此再使用jquery获取该文本内容时，是不带有转义符的，因此显示插件解析该文本会出现错误
解决方法是：在页面显示前，通过jsp的fn标签，将`&#124;`写成`&amp;#124;`，即将`&`符号转义后处理

8.注意代码块中最后一个\`\`\`之前不允许换行,否则下面的内容都有排版异常
<%@ page language="java" pageEncoding="UTF-8"%>
<style>
*{
	-webkit-box-sizing : border-box;
	-moz-box-sizing : border-box;
	box-sizing : border-box;
}
body { 
	background-color: #f6f6f6; 
	color: #555; 
	font: 14px/1.8 Helvetica Neue,Helvetica,PingFang SC,Tahoma,Arial,sans-serif;
} 
.pagination{
	text-align: center;
}
.layui-col-space20{
	margin: 0px;
}
.pull-right{
	float: right !important;
}

.pull-left{
	float: left !important;
}
.thumb img{
	border: 1px solid #ddd;
	padding: 1px;
	border-radius: 2px;
}
/*====================start head.jsp===========*/

/* start header */
.header{
	background-color: #FFF;
	position:relative;
	margin-bottom: 15px;
	border-bottom: 1px solid #EAEAEA;
}

.header-wrap{
	display: flex;
	align-items: center;
	justify-content: flex-start;
}

.header-nav{
	margin-right: auto;
}

.header-nav li.layui-this:after,
.header-nav .layui-nav-bar{
	background-color: #C1E4FF;
}
.header-nav .layui-nav-more{
	border-top-color: #000;
}

.header-nav .layui-nav-mored{
	border-color: transparent transparent #000;
}
.header-nav ul{
	background:transparent;
}
.header-nav .layui-nav a {
	font-size: 16px;
	color: #000;
}
.header-nav .layui-nav a:hover {
	color: #000;
}
/* end header */

/* start search-form */
.search-form{
	display: flex;
}

.search-form input{
    margin-right: .5rem!important;
}

.search-form input:focus{
	color: #495057;
	background-color: #fff;
	border-color: #80bdff;
	outline: 0;
	box-shadow: 0 0 0 0.2rem rgba(0,123,255,.25);
}
/* end search-form */

/*====================end head.jsp===========*/

/*=======================轮播图 =================*/
#carousel{
	margin-bottom: 20px;
}

.layui-carousel img {
	width: 100%;
	height: 280px;
	padding: 0 10px;
}
/*=======================end 轮播图=================*/

/*================index.jsp============*/
.index{
	background-color: #fff;
	box-shadow: 0px 0px 3px #ccc;
	margin-bottom: 10px;
}
.index-recommend>*{
	padding: 15px;
}
.index .cat-title{
	display: flex;
	justify-content: space-between;
	color: #0099cc;
	font-size: 16px;
	padding: 10px 15px 5px;
	border-bottom: 1px solid #eee; 
}
.index .cat-title a{
 	color: #888;
}
.index .cat-title a i{
	color: #ff0000;
}
.index .cat-title a span{
	margin-right: 6px;
}
.index .cat-title a:hover span{
	text-decoration: underline;
}
.index-recommend .title h2{
	font-size: 1.1rem;
	font-weight: 300;
}
.index-recommend .title h2 a{
	color: #555;
	white-space:nowrap;
}
.index-recommend .title h2 a:hover{
	color: #45B6F7;
}
.index-recommend .meta{
	font-size:12px;
	color: #aaa;
}
.index-recommend .meta span{
	margin-right: 20px;
}
.index-recommend .meta i{
	margin-right: 4px;
}

.today-first .thumb{
	margin-bottom: 10px;
}
.today-first .thumb img{
	width: 100%;
	height: 200px;
}
.today-first .summary{
	margin-bottom: 10px;
	font-size:12px;
	text-align: justify;
	height: 60px;
	overflow: hidden;
}
.today-other{
	display: flex;
	margin-bottom: 10px;
}
.today-other .thumb{
	margin-right: 12px;
}
.today-other .thumb a {
	margin-right: 12px;
}
.today-other .thumb img{
	width: 86px;
	height: 86px;
}
/*================end index.jsp============*/

/*================blog.jsp============*/
.post {
	background-color: #fff;
	padding: 1.5rem!important;
}
.post-content ol li{
	list-style-type: decimal;
}
.post-content ul li{
	list-style-type: circle;
}
.post-header {
	padding: 0 0 25px 0;
	border-bottom: 1px solid #eee;
	text-align: center;
}
.post-title {
	margin-bottom: 0.5rem;
	font-size: 26px;
	line-height: 36px;
}
.post-meta {
	font-size: 12px;
	color: #999;
}
.post-meta .item {
	display: inline-block;
	margin-right: 20px;
}
.post-meta .item i{
	margin-right: 4px;
}
.post-meta a{
	text-decoration: underline;
}
.post-copyright {
	background-color: #45B6F7;
	color: #fff;
	margin-top: 15px;
	margin-bottom: 30px;
	padding: 5px;
	font-size: 12px;
	text-align: center;
}
.post-copyright a{
	color: #fff;
}

/* start nav */
.post-nav{
	margin-bottom:30px;
	display: flex;
	justify-content: space-between;
	overflow: hidden;
}
.post-nav a{
	display:block;
	position: relative;
	width: 50%;
	color: #aaa;
}
.post-nav a:hover{
	color: #45B6F7;
}
.post-nav-pre{
	text-align: left;
}
.post-nav-next{
	text-align: right;
}
.post-nav-next:before{
	content: '';
	position: absolute;
	height: 100%;
	display: block;
	width: 1px;
	background-color: #eee;
}
/* end nav */

/* start recommend */
.post-recommend .title{
	border-bottom: 1px solid #eaeaea;
}
.post-recommend .title h3{
	margin-bottom: .5rem;
	font-size: 1.3rem;
}
.post-recommend ul{
	padding: 12px 20px;
	color: #bbb;
}
.post-recommend ul li{
	list-style-type: disc;
}
.post-recommend ul a{
	color: #666;
}
.post-recommend ul a:hover{
	color: #45B6F7;
}
/* end recommend */

/*================blog.jsp end============*/

/**===============list.jsp================*/
.post-list{
	background-color: #fff;
	box-shadow: 0px 0px 3px #ccc;
	margin-bottom: 10px;
}
.post-item{
	background-color: #fff;
	display: flex;
	padding: 20px;
	color: #999;
 	border-bottom: 1px solid #eaeaea;
}
.post-item .thumb{
	margin-right: 12px;
}
.post-item .thumb img{
	width: 220px;
	min-width: 220px;
	height: 150px;
}
.post-item .title h2{
	font-size: 1.2rem;
    font-weight: 600;
}
.post-item .title h2 a{
	color: #555;
}
.post-item .title h2 a:hover{
	color: #45B6F7;
}
.post-item .meta{
	margin-bottom: 10px;
	font-size:12px;
}
.post-item .meta span{
	margin-right: 20px;
}
.post-item .meta i{
	margin-right: 4px;
}
.post-item .summary{
	margin-bottom: 10px;
	font-size:12px;
	text-align: justify;
}
.readmore{
	float: right;
	background: #00a4ff;
	color: #fff;
	padding: 5px 10px;
}
.readmore a{
	color: #fff;
}

/*=================list.jsp  end =====================*/

/*=================search.jsp ========================*/
.result-status{
	background-color: #fff;
	padding: 10px 20px 4px;
	font-size: 12px;
	border-bottom: 1px solid #E5E5E5;
}
.result-error{
	background-color: #fff;
	padding: 20px;
	text-align: center;
}
.result-item{
	background-color: #fff;
	padding: 10px 20px 10px;
	color: #999;
}
.result-item h3 a{
	font-size: 20px;
	text-decoration: none;
	font-weight: normal;
	color: #00C;
	vertical-align: middle;
}
.result-item h3 a:hover{
	color: #dd4b39;
}
.result-item .url{
	color: #40AA53;
	font-size: 12px;
}
.result-item .summary{
	color: #555;
}
.result-item .date{
	font-size: 12px;
}

.result-item .highlight{
	color: #dd4b39;
}
.result-item p{
	margin: 0;
}

/*===================search.jsp end==================*/
</style>
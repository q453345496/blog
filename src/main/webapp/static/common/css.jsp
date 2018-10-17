<%@ page language="java" pageEncoding="UTF-8"%>
<style>
body { 
 	background-color: #f6f6f6; 
    color: #555; 
    font: 14px/1.8 Helvetica Neue,Helvetica,PingFang SC,Tahoma,Arial,sans-serif;
} 
.pagination{
	text-align: center;
}

/*================blog.jsp============*/
.post {
	background-color: #fff;
	padding: 1.5rem!important;
}
.post-header {
    margin-bottom: 20px;
    padding: 25px 0;
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
.post-item{
	background-color: #fff;
	display: flex;
	padding: 20px;
	color: #999;
	border: 1px solid #eaeaea;
	margin-bottom: -1px;
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
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="标示"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="名称"%>
<%@ attribute name="content" type="java.lang.String" required="true" description="内容"%>
<%@ attribute name="functions" type="java.lang.String" required="true" description="可以定义方法"%>


<div>
	<ul>
		<li>我们可以把通用的内容写到tag里面</li>
		<li>比如页面的导航，底部，或者头部，或者分页插件，或者其他的</li>
		<li>只要可以公用的，都可以写到这里面</li>
		<li>%@ attribute  这些参数我们可以自定义，也可以自定义回调引入页面的方法</li>
	</ul>

</div>

<div>
	<h1>${name}</h1>
	<h1>${content}</h1>
</div>


<script>
	$(function(){
		${functions};
	})
</script>
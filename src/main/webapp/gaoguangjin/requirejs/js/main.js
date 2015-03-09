

//如果jsp页面配置了 data-main="js/main.js"  那么 所有引用的就是架包的名字，没有必要配置path了
require.config({
	//BaseUrl也可以通过设置进行手动配置（通过RequireJS 的 config进行配置），若是没在config中进行配置，并且script标签没有指定data-main的话，那么默认目录为引入requireJS的HTML页面目录。
	
	//如果require.js和jsp在一个目录就不用配置了
	//baseUrl: 'js',
    paths: {
        jquery: 'jquery',
        test:'test'
    }
});
 /**
require(['jquery'], function($) {
    alert($().jquery);
});
**/
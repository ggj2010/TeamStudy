

//如果jsp页面配置了 data-main="js/main.js"  那么 所有引用的就是架包的名字，没有必要配置path了dd
require.config({
	//baseUrl: '../youdao/js/lib',
    paths: {
        jquery: '/TeamStudy/youdao/js/lib/jquery1.10.2',
        bootstrap: '/TeamStudy/youdao/js/lib/bootstrap'
    }
});

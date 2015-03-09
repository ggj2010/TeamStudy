

//如果jsp页面配置了 data-main="js/main.js"  那么 所有引用的就是架包的名字，没有必要配置path了
require.config({
	baseUrl: 'youdao/js/lib',
    paths: {
        jquery: 'jquery1.10.2',
        test:'test'
    }
});

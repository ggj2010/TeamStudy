<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %> 
<%
	String path = request.getContextPath();
request.setAttribute("path",path);
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html lang="en-US">
	<head>
		<base href="<%=basePath%>/gaoguangjin/zjy/">
		<title>老婆生日快乐</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="张静月,高广金,ggjlovezjy">
		<meta http-equiv="description" content="生日快乐">
		
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="css/bootstrap.css" />
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		<link rel="stylesheet" href="css/linea-icon.css" />
		<link rel="stylesheet" href="css/fancy-buttons.css" />
		
		<!--=== Google Fonts ===-->
		<link href='http://fonts.googleapis.com/css?family=Bangers'
			rel='stylesheet' type='text/css'>
		<link
			href='http://fonts.googleapis.com/css?family=Roboto+Slab:300,700,400'
			rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Raleway:600,400,300'
			rel='stylesheet' type='text/css'>
		<link
			href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'
			rel='stylesheet' type='text/css'>
		<!--=== Other CSS files ===-->
		<link rel="stylesheet" href="css/animate.css" />
		<link rel="stylesheet" href="css/jquery.vegas.css" />
		<link rel="stylesheet" href="css/baraja.css" />
		<link rel="stylesheet" href="css/jquery.bxslider.css" />
		
		<!--=== Main Stylesheets ===-->
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/responsive.css" />
		
		<!--=== Color Scheme, three colors are available red.css, orange.css and gray.css ===-->
		<link rel="stylesheet" id="scheme-source" href="css/schemes/gray.css" />
		
		
	</head>
<body>

	<!--=== Preloader section starts ===-->
	<section id="preloader">
		<div class="loading-circle fa-spin"></div>
	</section>
	<!--=== Preloader section Ends ===-->

	<!--=== Header section Starts ===-->
	<div id="header" class="header-section">
		<!-- sticky-bar Starts-->
		<div class="sticky-bar-wrap">
			<div class="sticky-section">
				<div id="topbar-hold" class="nav-hold container">
					<nav class="navbar" role="navigation">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse" data-target=".navbar-responsive-collapse">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<!--=== Site Name ===-->
							<a class="site-name navbar-brand" href="#"><span>I</span>❤张静月</a>
						</div>

						<!-- Main Navigation menu Starts -->
						<div class="collapse navbar-collapse navbar-responsive-collapse">
							<ul class="nav navbar-nav navbar-right">
								<li class="current"><a href="#header">相遇</a></li>
								<li><a href="#section-feature">热恋</a></li>
								<li><a href="#section-services">依赖</a></li>
								<li><a href="#step-1">独立</a></li>
								<li><a href="#section-fendou">奋斗</a></li>
								<li><a href="#section-wish">愿景</a></li>
								<li><a href="#section-screenshots" id="love">520</a></li>
								<li><a href="#section-video" id="closemusic">你猜</a></li>
								<li><a href="#section-liwu">生日礼物</a></li>
								<li><a href="#section-contact">留言</a></li>
							</ul>
						</div>
						<!-- Main Navigation menu ends-->
					</nav>
				</div>
			</div>
		</div>
		<!-- sticky-bar Ends-->
		<!--=== Header section Ends ===-->

		<!--=== Home Section Starts ===-->
		<div id="section-home" class="home-section-wrap center">
			<div class="section-overlay"></div>
			<div class="container home">
				<div class="row">
					<h1 class="well-come">相遇</h1>

					<div class="col-md-8 col-md-offset-2">
						<p class="intro-message">走在一起是缘分，一起在走是幸福</p>
					</div>
				</div>
			</div>
		</div>
		<!--=== Home Section Ends ===-->
	</div>


	<!--=== Features section Starts ===-->
	<section id="section-feature" class="feature-wrap">
		<div class="container features center">
			<div class="row">
				<div class="col-lg-12">
					<!--Features container Starts -->
					<ul id="card-ul" class="features-hold baraja-container">

						<!-- Single Feature Starts -->
						<li class="single-feature" title="Card style"><img
							src="http://bcs.duapp.com/ggjlovezjy/images/1.jpg" alt="" class="feature-image" /> <!-- Feature Icon -->

							<p class="feature-text">你在偷亲我！</p></li>
						<!-- Single Feature Ends -->

						<!-- Single Feature Starts -->
						<li class="single-feature" title="50+ SVG Icon included"><img
							src="http://bcs.duapp.com/ggjlovezjy/images/2.jpg" alt="" class="feature-image" /> <!-- Feature Icon -->

							<p class="feature-text">原来你也清纯过噢！</p></li>
						<!-- Single Feature Ends -->

						<!-- Single Feature Starts -->
						<li class="single-feature" title="MailChimp Ready"><img
							src="http://bcs.duapp.com/ggjlovezjy/images/3.jpg" alt="" class="feature-image" /> <!-- Feature Icon -->

							<p class="feature-text">暑假在KTV兼职制服照！</p></li>
						<!-- Single Feature Ends -->

						<!-- Single Feature Starts -->
						<li class="single-feature" title="4 home style"><img
							src="http://bcs.duapp.com/ggjlovezjy/images/4.jpg" alt="" class="feature-image" /> <!-- Feature Icon -->

							<p class="feature-text">那年减肥瘦成狗啊！！！</p></li>
						<!-- Single Feature Ends -->

						<!-- Single Feature Starts -->
						<li class="single-feature" title="Parallax Backgrounds"><img
							src="http://bcs.duapp.com/ggjlovezjy/images/5.jpg" alt="" class="feature-image" /> <!-- Feature Icon -->

							<p class="feature-text">第一次吃到必胜客的开心劲！</p></li>
						<!-- Single Feature Ends -->

						<!-- Single Feature Starts -->
						<li class="single-feature" title="Ajax contact form"><img
							src="http://bcs.duapp.com/ggjlovezjy/images/6.jpg" alt="" class="feature-image" /> <!-- Feature Icon -->

							<p class="feature-text">两个都是你的，看你开心的</p></li>
						<!-- Single Feature Ends -->

						<!-- Single Feature Starts -->
						<li class="single-feature" title="unlimited Google fonts"><img
							src="http://bcs.duapp.com/ggjlovezjy/images/7.jpg" alt="" class="feature-image" /> <!-- Feature Icon -->

							<p class="feature-text">在我宿舍玩自拍的二货</p></li>
						<!-- Single Feature Ends -->

						<!-- Single Feature Starts -->
						<li class="single-feature" title="Feature heading"><img
							src="http://bcs.duapp.com/ggjlovezjy/images/8.jpg" alt="" class="feature-image" /> <!-- Feature Icon -->

							<p class="feature-text">为了美丽的双眼皮动刀子之后的你，确实有那么几分人摸狗样</p></li>
						<!-- Single Feature Ends -->
					</ul>
					<!--Features container Ends -->

					<!-- Features Controls Starts -->
					<div class="features-control relative">
						<button
							class="control-icon fancy-button button-line no-text btn-col bell"
							id="feature-prev" title="上一张">
							<span class="icon"> <i class="fa fa-arrow-left"></i>
							</span>
						</button>
						<button
							class="control-icon fancy-button button-line no-text btn-col zoom"
							id="feature-expand" title="扩展">
							<span class="icon"> <i class="fa fa-expand"></i>
							</span>
						</button>
						<button
							class="control-icon fancy-button button-line no-text btn-col zoom"
							id="feature-close" title="紧缩">
							<span class="icon"> <i class="fa fa-compress"></i>
							</span>
						</button>
						<button
							class="control-icon fancy-button button-line no-text btn-col bell"
							id="feature-next" title="下一张">
							<span class="icon"> <i class="fa fa-arrow-right"></i>
							</span>
						</button>
					</div>
					<!-- Features Controls Ends -->
				</div>
			</div>
		</div>
	</section>
	<!--=== Features section Ends ===-->

	<!--=== Services section Starts ===-->
	<section id="section-services" class="services-wrap">
		<div class="container services">
			<div class="row">

				<div class="col-md-10 col-md-offset-1 center section-title">
					<h3>我是那么依赖你</h3>
				</div>

				<!-- Single Service Starts -->
				<div class="col-md-6 col-sm-6 service animated"
					data-animation="fadeInLeft" data-animation-delay="700">
					<span class="service-icon center"><i
						class="icon icon-basic-book-pencil fa-3x"></i></span>
					<div class="service-desc">
						<h4 class="service-title color-scheme">事件一</h4>
						<p class="service-description justify">
							热恋期的时候碰到暑假，我就直接带你一起去苏州了，这样你就可以陪我一个暑假了噢！</p>
					</div>
				</div>
				<!-- Single Service Ends -->

				<!-- Single Service Starts -->
				<div class="col-md-6 col-sm-6 service animated"
					data-animation="fadeInUp" data-animation-delay="700">
					<span class="service-icon center"><i
						class="icon icon-basic-paperplane fa-3x"></i></span>
					<div class="service-desc">
						<h4 class="service-title color-scheme">事件二</h4>
						<p class="service-description justify">
							骑车30公里去你学校看你，有时候还是热恋贴冷屁股哎，寒风吹冻的不是我的脸，是俺的❤！</p>
					</div>
				</div>
				<!-- Single Service ends -->

				<!-- Single Service Starts -->
				<div class="col-md-6 col-sm-6 service animated"
					data-animation="fadeInRight" data-animation-delay="700">
					<span class="service-icon center"><i
						class="icon icon-basic-accelerator fa-3x"></i></span>
					<div class="service-desc">
						<h4 class="service-title color-scheme">事件三</h4>
						<p class="service-description justify">
							放寒假的时候，我趁着离过年还有几天时间，投投从合肥跑到新乡找你。</p>
					</div>
				</div>
				<!-- Single Service Ends -->

				<!-- Single Service Starts -->
				<div class="col-md-6 col-sm-6 service animated"
					data-animation="fadeInUp" data-animation-delay="700">
					<span class="service-icon center"><i
						class="icon icon-basic-lightbulb fa-3x"></i></span>
					<div class="service-desc">
						<h4 class="service-title color-scheme">事件四</h4>
						<p class="service-description justify">
							独自一人闯荡上海，一直催着你过来陪我，早早给你买过车票，这样你就不得不过来了！</p>
					</div>
				</div>
				<!-- Single Service Ends -->
			</div>
		</div>
	</section>
	<!--=== Services section Ends ===-->

	<!--=== Step-1 section Starts ===-->
	<section id="step-1" class="section-step step-wrap">
		<div class="container step animated" data-animation="bounceInLeft"
			data-animation-delay="700">
			<div class="row">
				<!-- Step Description Starts -->
				<div class="col-md-8 step-desc">
					<div class="col-md-2 center">
						<div class="step-no">
							<span class="no-inner">1</span>
						</div>
					</div>

					<div class="col-md-10 step-details">
						<h3 class="step-title color-scheme">我在郑州培训的那段时光</h3>
						<p class="step-description">
							每天都自己回去敲代码到 <strong>凌晨</strong>,
							自己督促自己，因为我知道，毕业的时候如果我不找到工作，你是不会和我来合肥的 ，因为你就是我的动力
						</p>

					</div>
					<!-- End step-details -->
				</div>
				<!-- Step Description Ends -->
				<div class="col-md-4 step-img">
					<img src="http://bcs.duapp.com/ggjlovezjy/images/note.png" alt="" />
					<!-- Step Photo Here -->
				</div>
			</div>
		</div>
	</section>
	<!--=== Step-1 section Ends ===-->

	<!--=== Step-2 section Starts ===-->
	<section id="step-2" class="section-step step-even step-wrap">
		<div class="container step animated" data-animation="bounceInRight"
			data-animation-delay="700">
			<div class="row">
				<!-- Step Description Starts -->
				<div class="col-md-8 step-desc">
					<div class="col-md-2 center">
						<div class="step-no">
							<span class="no-inner">2</span>
						</div>
					</div>

					<div class="col-md-10 step-details">
						<h3 class="step-title color-scheme">工作那段时光</h3>
						<p class="step-description">
							每天下班和你打电话就是我最开心的 <strong>时间</strong>,
							有时候把工作上的脾气全都洒在你身上，让你无缘无故受委屈，是我的不对 现在我努力工作，赚多多的钱！
						</p>
					</div>
					<!-- End step-details -->
				</div>
				<!-- Step Description Ends -->
				<div class="col-md-4 step-img">
					<img src="http://bcs.duapp.com/ggjlovezjy/images/desk.png" alt="" />
					<!-- Step Photo Here -->
				</div>
			</div>
		</div>
	</section>
	<!--=== Step-2 section Ends ===-->




	<!--=== Testimonials section Starts ===-->
	<section id="section-fendou" class="testimonials-wrap">
		<div class="section-overlay"></div>
		<div class="container testimonials center animated"
			data-animation="rollIn" data-animation-delay="500">
			<div class="row">
				<div class="col-md-8 col-md-offset-2">
					<div class="testimonial-slider">
						<!-- Single Testimonial Starts -->
						<div class="testimonial">
							<p class="comment">将毕生岁月奉献给一门手艺，一项事业，一种信仰，这个世上又有多少人能够做到呢？</p>

							<h5 class="happy-client">小野二郎</h5>
							<span class="client-info">职业向导</span>
						</div>
						<!-- Single Testimonial Ends -->

						<!-- Single Testimonial Starts -->
						<div class="testimonial">
							<p class="comment">我会一步一步往上爬，从组长到项目经理到创业，等着我！</p>

							<h5 class="happy-client">高广金</h5>
							<span class="client-info">我的目标</span>
						</div>
						<!-- Single Testimonial Ends -->

						<!-- Single Testimonial Starts -->
						<div class="testimonial">
							<p class="comment">你的目标就是天天陪在我身边就好了！</p>

							<h5 class="happy-client">老婆</h5>
							<span class="client-info">╮(╯▽╰)╭</span>
						</div>
						<!-- Single Testimonial Ends -->
					</div>
					<div id="bx-pager" class="client-photos">
						<a data-slide-index="0" href="" class="photo-hold"> <span
							class="photo-bg"> <img src="http://bcs.duapp.com/ggjlovezjy/images/job_1.jpg" alt="" /> <!-- Client photo 1 -->
						</span>
						</a> <a data-slide-index="1" href="" class="photo-hold"> <span
							class="photo-bg"> <img src="http://bcs.duapp.com/ggjlovezjy/images/job_2.jpg" alt="" /> <!-- Client photo 2 -->
						</span>
						</a> <a data-slide-index="2" href="" class="photo-hold"> <span
							class="photo-bg"> <img src="http://bcs.duapp.com/ggjlovezjy/images/job_3.jpg" alt="" /> <!-- Client photo 3 -->
						</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--=== Testimonials section Ends ===-->

	<!--=== 愿景section Starts ===-->
	<section id="section-wish" class="pricing-wrap">
		<div class="container pricing">
			<div class="row">
				<div class="col-md-10 col-md-offset-1 center section-title">
					<h3>计划 & 生活</h3>
				</div>
				<!-- Single Price Starts -->
				<div class="col-md-3 col-sm-6 single-pricing-wrap center animated"
					data-animation="bounceInLeft" data-animation-delay="500">
					<div class="single-pricing">

						<div class="pricing-head">
							<h4 class="pricing-heading color-scheme">目前阶段</h4>
							<div class="price">
								<h3>
									<span class="dollar">K</span> 8.2 <span class="month">/月</span>
								</h3>
							</div>
						</div>

						<ul class="package-features">
							<li><span class="color-scheme fa fa-check"></span>是否每天都在进步</li>
							<li><span class="color-scheme fa fa-check"></span>是否努力赚钱给老婆</li>
							<li><span class="color-scheme fa fa-close"></span>是否看游戏视频了</li>
						</ul>

					</div>
				</div>
				<!-- Single Price Ends -->


				<!-- Single Price Starts -->
				<div class="col-md-3 col-sm-6 single-pricing-wrap center animated"
					data-animation="bounceInLeft" data-animation-delay="700">
					<div class="single-pricing best-pricing">
						<!-- this is best-pricing -->

						<div class="pricing-head">
							<h4 class="pricing-heading color-scheme">下个阶段</h4>
							<div class="price">
								<h3>
									<span class="dollar">K</span> 10 <span class="month">/month</span>
								</h3>
							</div>
						</div>

						<ul class="package-features">

							<li><span class="color-scheme fa fa-check"></span>是否在扩大知识面,作为加薪资本</li>
							<li><span class="color-scheme fa fa-close"></span>是否见到老丈人了</li>
							<li><span class="color-scheme fa fa-close"></span>老婆是否减肥成功了</li>
						</ul>

					</div>
				</div>
				<!-- Single Price Ends -->

				<!-- Single Price Starts -->
				<div class="col-md-3 col-sm-6 single-pricing-wrap center animated"
					data-animation="bounceInLeft" data-animation-delay="900">
					<div class="single-pricing">

						<div class="pricing-head">
							<h4 class="pricing-heading color-scheme">明年</h4>
							<div class="price">
								<h3>
									<span class="dollar">K</span> 15 <span class="month">/月</span>
								</h3>
							</div>
						</div>

						<ul class="package-features">
							<li><span class="color-scheme fa fa-check"></span>我得学会驾驶</li>
							<li><span class="color-scheme fa fa-check"></span>我得有一个狗狗</li>
							<li><span class="color-scheme fa fa-close"></span>是否结婚了</li>
						</ul>

					</div>
				</div>
				<!-- Single Price Ends -->

				<!-- Single Price Starts -->
				<div class="col-md-3 col-sm-6 single-pricing-wrap center animated"
					data-animation="bounceInLeft" data-animation-delay="1100">
					<div class="single-pricing">

						<div class="pricing-head">
							<h4 class="pricing-heading color-scheme">永久</h4>
							<div class="price">
								<h3>
									<span class="dollar">K</span> ？ <span class="month">/月</span>
								</h3>
							</div>
						</div>

						<ul class="package-features">
							<li><span class="color-scheme fa fa-check"></span>是否我和老婆每天都开开心心</li>
							<li><span class="color-scheme fa fa-check"></span>是否老婆瘦到110</li>
							<li><span class="color-scheme fa fa-check"></span>我们是否结婚了噢</li>
						</ul>

					</div>
				</div>
				<!-- Single Price Ends -->

			</div>
		</div>
	</section>
	<!--=== Pricing section Ends ===-->


	<!--=== ScreenShots section Starts ===-->
	<section id="section-screenshots" class="screenshots-wrap">
		<div class="container screenshots animated" data-animation="fadeInUp"
			data-animation-delay="1000">
			<div class="row porfolio-container"
				style="height: 1000px; overflow-y: scroll; SCROLLBAR-FACE-COLOR: #000024;">
				<div class="col-md-10 col-md-offset-1 center section-title">
					<h3>5 2 0</h3>
				</div>
				<div id="loveyou"></div>
			</div>
			<div id="portfolio-loader" class="center">
				<div class="loading-circle fa-spin"></div>
			</div>
			<!--=== Portfolio loader ===-->

			<div id="portfolio-load"></div>
			<!--=== ajax content will be loaded here ===-->

			<div class="col-md-12 center back-button">
				<a class="backToProject fancy-button button-line bell btn-col"
					href="#"> Back <span class="icon"> <i
						class="fa fa-arrow-left"></i>
				</span>
				</a>
			</div>
			<!--=== Single portfolio back button ===-->
		</div>
	</section>
	<!--=== ScreenShots section Ends ===-->



	<!--=== Video section Starts ===-->
	<section id="section-video" class="section-video-wrap">
		<div class="section-overlay"></div>
		<div class="container big-video center animated"
			data-animation="fadeInLeft" data-animation-delay="700">
			<div class="row">
				<div class="col-md-12 section-title">
					<h3>很喜欢这首歌，是不是我想结婚了，哈哈。</h3>
				</div>
				<div class="col-md-10 col-md-offset-1 video-content">

					<embed align="middle" allowfullscreen="true"
						allowscriptaccess="always" height="130" quality="high"
						src="http://www.iqiyi.com/common/flashplayer/20150311/SharePlayer_4_1_12_c3_2_4.swf?vid=ccc6b307bf50c0c8a92a16c6c5e4d5c8&pageURL=w_19rrlc50g1.swf&albumId=446326509&tvId=446326509&isPurchase=0&cnId=5&share_sTime=0&share_eTime=225&source="
						type="application/x-shockwave-flash" width="300">
				
				</div>
			</div>
		</div>
	</section>
	<!--=== Video section Ends ===-->

	


	<!--=== Download section Starts ===-->
	<section id="section-liwu" class="download-wrap">
		<div class="container download center">
			<div class="row">
				<div class="col-lg-12">
					<div class="col-md-10 col-md-offset-1 center section-title">
						<h3>Iphone6 plus</h3>
					</div>
					<div class="download-buttons clearfix">
						<!-- Download Buttons -->
						<a class="fancy-button button-line no-text btn-col large zoom"
							href="#" title="下个月工资又要没了，/(ㄒoㄒ)/~~"> <span class="icon">
								正在路上...明天快递小哥就送到家../(ㄒoㄒ)/~~
						</span>
						</a> 
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--=== Download section Ends ===-->


	<!--=== Contact section Starts ===-->
	<section id="section-contact" class="contact-wrap">
		<div class="section-overlay"></div>
		<div class="container contact center animated"
			data-animation="flipInY" data-animation-delay="1000">
			<div class="row">
				<div class="col-md-8 col-md-offset-2">
					<div class="col-md-10 col-md-offset-1 center section-title">
						<h3>留言给我</h3>
					</div>

					<div class="confirmation">
						<p>
							<span class="fa fa-check"></span>
						</p>
					</div>

					<form class="contact-form support-form">

						<div class="col-lg-12">
							<input id="name" class="input-field form-item field-name"
								type="text" required="required" name="name"
								placeholder="姓名" /> <input id="email"
								class="input-field form-item field-email" type="email"
								required="required" name="email" placeholder="邮箱" />

							<input id="subject" class="input-field form-item field-subject"
								type="text" required="required" name="subject"
								placeholder="主题" />
							<textarea id="message"
								class="textarea-field form-item field-message" rows="10"
								name="message" placeholder="内容"></textarea>
						</div>
						<button
							class=" button-line button-white large zoom" id="sendmessage">
							发送<span class="icon"> <i
								class="fa fa-paper-plane-o"></i>
							</span>
						</button>
					</form>
				</div>
			</div>
		</div>
	</section>
	<!--=== Contact section Ends ===-->

	<!--=== Footer section Starts ===-->

	<!--=== Footer section Ends ===-->

	<!--==== Js files ====-->
	<!--==== Essential files ====-->
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="js/modernizr.js"></script>
	<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>

	<!--==== Slider and Card style plugin ====-->
	<script type="text/javascript" src="js/jquery.baraja.js"></script>
	<script type="text/javascript" src="js/jquery.vegas.min.js"></script>
	<script type="text/javascript" src="js/jquery.bxslider.min.js"></script>

	<!--==== MailChimp Widget plugin ====-->
	<script type="text/javascript" src="js/jquery.ajaxchimp.min.js"></script>

	<!--==== Scroll and navigation plugins ====-->
	<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
	<script type="text/javascript" src="js/jquery.nav.js"></script>
	<script type="text/javascript" src="js/jquery.appear.js"></script>
	<script type="text/javascript" src="js/jquery.fitvids.js"></script>

	<!--==== Custom Script files ====-->
	<script type="text/javascript" src="js/custom.js"></script>

	<script type="text/javascript" defer async="async">
		$(function() {

			appendMusic();

			appendLove(4);

			//关闭音乐事件
			$("#closemusic").on("click", function() {
				$("#music").remove();
			})
			
			//查询事件
			$("#sendmessage").on("click", function() {
				
				sendmessage();
			})
			//love
			$("#love").on("click", function() {
				appendLove(400);
			})

		})
		
		function appendLove(sum){
			$("#loveyou").empty();
			var html = "";
			var head = "<div class=\"col-md-4 col-sm-4 col-xs-6\">";
			head += "<div class=\"screenshot\">";
			head += "<div class=\"photo-box\">";
			var end = "</div></div></div>";
			for ( var i = 1; i < sum; i++) {
				var temp = "";
				temp = head + "<img src=\"http://bcs.duapp.com/ggjlovezjy/images/520/"+i+".jpg\"  />"
				html += temp + end;
				temp = "";
			}
			$("#loveyou").append(html);
			
		}

		function appendMusic() {
			var ua = navigator.userAgent.toLowerCase();
			if (ua.match(/msie ([\d.]+)/)) {
				$('body')
						.append(
								'<object id="music" classid="clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95"><param name="AutoStart" value="1" /><param name="Src" value="http://bcs.duapp.com/ggjlovezjy/love.mp3" /></object>');
			} else if (ua.match(/firefox\/([\d.]+)/)) {
				$('body')
						.append(
								'<embed id="music" src="http://bcs.duapp.com/ggjlovezjy/love.mp3" type="audio/mp3" hidden="true" loop="false" mastersound></embed>');
			} else if (ua.match(/chrome\/([\d.]+)/)) {
				$('body')
						.append(
								'<audio  id="music" src="http://bcs.duapp.com/ggjlovezjy/love.mp3" type="audio/mp3" autoplay=”autoplay” hidden="true"></audio>');
			} else if (ua.match(/opera.([\d.]+)/)) {
				$('body')
						.append(
								'<embed id="music" src="http://bcs.duapp.com/ggjlovezjy/love.mp3" hidden="true" loop="false"><noembed><bgsounds src="/sounds/alert/1.mp3"></noembed>');
			} else if (ua.match(/version\/([\d.]+).*safari/)) {
				$('body')
						.append(
								'<audio id="music" src="http://bcs.duapp.com/ggjlovezjy/love.mp3" type="audio/mp3" autoplay="autoplay" hidden="true"></audio>');
			} else {
				$('body')
						.append(
								'<embed music src="http://bcs.duapp.com/ggjlovezjy/love.mp3" type="audio/mp3" hidden="true" loop="false" mastersound></embed>');
			}
		}
		
		
		function sendmessage(){
			var dataParams="name="+$("#name").val()+"&"+"email="+$("#email").val()+"&"+"subject="+$("#subject").val()+"&"+"message="+$("#message").val();
			$.ajax({
				type: "post",//使用get方法访问后台
				url: "../../sendmessage.do",//要访问的后台地址
				data: dataParams,//要发送的数据
				success: function(data){//msg为返回的数据，在这里做数据绑定
					$('.confirmation p').empty();
					$('.confirmation p').append("留言发送成功！").parent('.confirmation').show();
					$('.form-item').val('');
					setTimeout(function() {
						$('.confirmation').hide();
					}, 8000);
				},
				complete: function(){//AJAX请求完成时隐藏loading提示
				}
			})
			
		}
	</script>


</body>
</html>
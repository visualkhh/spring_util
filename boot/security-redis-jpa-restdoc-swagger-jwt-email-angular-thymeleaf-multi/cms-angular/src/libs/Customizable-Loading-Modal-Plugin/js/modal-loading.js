/**
 * Modal Loading JavaScript Library
 * @author 						c
 * @date    					2017-11-06
 * @param  {window} 	global  
 * @param  {jQuery} 	$       
 * @param  {function} 	factory 
 * @return {void}         		
 * @version 1.0.0
 */
(function(window, $, factory) {

	window.Loading = $.Loading = factory(window, $);

})(window, jQuery, function(window, $) {

	var windowWidth;
	var windowHeight;

	/**
	 * 构造Loading
	 * @author  				c
	 * @date 					2017-11-06
	 * @param {Object} options	构造Loading的具体参数
	 * @return {Loading} 		Loading对象
	 */
	function Loading(options) {
		return new Loading.prototype._init($('body'), options);
	}

	/**
	 * 初始化函数
	 * @author  				c
	 * @date 					2017-11-06
	 * @param {Object} $this	jQuery对象
	 * @param {Object} options	构造Loading的具体参数
	 * @return {Loading} 		Loading对象
	 */
	const init = Loading.prototype._init = function($target, options) {
		
		this.version = '1.0.0';

		this.$target = $target;

		this.set = $.extend(true, {}, this.set, options);

		this._build();

		return this;

	};

	/**
	 * 构建Loading
	 * @return {void} 
	 */
	Loading.prototype._build = function() {

		this.$modalMask = $('<div class="modal-mask"></div>');

		this.$modalLoading = $('<div class="modal-loading"></div>');

		this.$loadingTitle = $('<p class="loading-title"></p>');

		this.$loadingAnimation = $('<div class="loading-animate"></div>');

		this.$animationOrigin = $('<div class="animate-origin"><span></span></div>');

		this.$animationImage = $('<img/>');

		this.$loadingDiscription = $('<p class="loading-discription"></p>');

		// zIndex
		if(this.set.zIndex <= 0) {
			this.set.zIndex = (this.$target.siblings().length-1 || this.$target.children().siblings().length) + 10001;
		}

		// var attr, value;
		// for(attr in this.set) {
		// 	if(attr !== 'zIndex' && attr !== 'animationDuration') {
		// 		value = this.set[attr];
		// 		if(typeof value === 'number') {
		// 			if(value <= 0) {
		// 				this.set[attr] = 'auto';
		// 			} else {
		// 				this.set[attr] = (value + this.set.unit);
		// 			}
		// 		}
		// 	}
		// }

		// 构建Loading
		this._buildMask();

		this._buildLoading();

		this._buildTitle();
		
		this._buildLoadingAnimation();
		
		this._buildDiscription();

		// 是否初始化过
		this._init = false;

		if(this.set.defaultApply) {
			this.apply();
		}

	}

	/**
	 * 构建Mask
	 * @return {void} 
	 */
	Loading.prototype._buildMask = function() {

		// 如果不适用遮罩层
		if(!this.set.mask) {
			this.$modalMask.css({
				position: 	'absolute',
				top: 		'-200%',
			});
			return ;
		}

		// 遮罩层样式
		this.$modalMask.css({
			backgroundColor: 	this.set.maskBgColor,
			zIndex: 			this.set.zIndex,
		});

		// 添加额外的class
		this.$modalMask.addClass(this.set.maskClassName);

	}

	/**
	 * 构建Loading
	 * @return {void} 
	 */
	Loading.prototype._buildLoading = function() {

		this.$modalLoading.css({
			width: 				this.set.loadingWidth,
			height: 			this.set.loadingHeight,
			padding: 			this.set.loadingPadding,
			backgroundColor: 	this.set.loadingBgColor,
			borderRadius: 		this.set.loadingBorderRadius,
		});

		// 布局方式
		if(this.set.direction === 'hor') {
			this.$modalLoading.addClass('modal-hor-layout');
		}

		// 将loading添加到mask中
		this.$modalMask.append(this.$modalLoading);

	}

	/**
	 * 构建Title
	 * @return {void} 
	 */
	Loading.prototype._buildTitle = function() {

		if(!this.set.title) {
			return ;
		}


		this.$loadingTitle.css({
			color: 		this.set.titleColor,
			fontSize: 	this.set.titleFontSize,
		});

		this.$loadingTitle.addClass(this.set.titleClassName);

		this.$loadingTitle.text(this.set.title);

		// 将title添加到loading中
		this.$modalLoading.append(this.$loadingTitle);

	}

	/**
	 * 构建LoadingAnimation
	 * @return {void} 
	 */
	Loading.prototype._buildLoadingAnimation = function() {

		// loadingAnimation
		this.$loadingAnimation.css({
			width: this.set.animationWidth,
			height: this.set.animationHeight,
		});

		if(this.set.loadingAnimation === 'origin') { // origin动画
			this.$animationOrigin.children().css({
				width: this.set.animationOriginWidth,
				height: this.set.animationOriginHeight,
				backgroundColor: this.set.animationOriginColor,
			});
			for(var i = 0; i < 5; i++) {
				this.$loadingAnimation.append(this.$animationOrigin.clone());
			}
		} else if(this.set.loadingAnimation === 'image') { // 图片加载动画
			this.$animationImage.attr('src', this.set.animationSrc);
			this.$loadingAnimation.append(this.$animationImage);
		} //else {
		// 	throw new Error("[loadingAnimation] 参数错误. 参数值只能为['origin', 'image']");
		// }

		this.$loadingAnimation.addClass(this.set.animationClassName);

		// 将loadingAnimation添加到loading中
		this.$modalLoading.append(this.$loadingAnimation);

	}

	/**
	 * 构建Discription
	 * @return {void} 
	 */
	Loading.prototype._buildDiscription = function() {

		if(!this.set.discription) {
			return ;
		}

		this.$loadingDiscription.css({
			color: 		this.set.discriptionColor,
			fontSize: 	this.set.discriptionFontSize,
		});

		this.$loadingDiscription.addClass(this.set.discriptionClassName);

		this.$loadingDiscription.text(this.set.discription);

		// 将title添加到loading中
		this.$modalLoading.append(this.$loadingDiscription);

	}

	/**
	 * 定位
	 * @return {void} 
	 */
	Loading.prototype._position = function() {

		windowWidth = $(window).width();
		windowHeight = $(window).height(); 

		var loadingWidth = this.$modalLoading.outerWidth();
		var loadingHeight = this.$modalLoading.outerHeight();

		var x1 = windowWidth >>> 1;
		var x2 = loadingWidth >>> 1;
		var left = x1 - x2;

		var y1 = windowHeight >>> 1;
		var y2 = loadingHeight >>> 1;
		var top = y1 - y2;

		this.$modalLoading.css({ top, left });

	}

	/**
	 * 入屏过度动画
	 * @return {void} 
	 */
	Loading.prototype._transitionAnimationIn = function() {

		if(!this.set.animationIn) {
			this.$modalMask.css({ display: 'block' });
		} else {
			// this.$modalMask.removeClass(this.set.animationOut).addClass(this.set.animationIn);
			this.$modalMask.addClass(this.set.animationIn);
		}
		
	}

	/**
	 * 出屏过度动画
	 * @return {void} 
	 */
	Loading.prototype._transitionAnimationOut = function() {

		
		if(!this.set.animationOut) {
			
			// this.$modalMask.css({ display: 'none' });
			this.$modalMask.remove();

		} else {
			
			this._timer && this._timer.clearTimeout(this._timer);

			this.$modalMask.removeClass(this.set.animationIn).addClass(this.set.animationOut);

			// this._timer = setTimeout(() => {
			// 	this.$modalMask.remove();
			// }, this.set.animationDuration);

			var self = this;

			this._timer = setTimeout(function() {
				self.$modalMask.remove();
			}, this.set.animationDuration);

		}
	}

	/**
	 * 显示Loading
	 * @return {void} 
	 */
	Loading.prototype.apply = function() {
		this._transitionAnimationIn();

		// 这样按理说可以增加性能, 因为不需要从内存中寻找_initLoading方法.
		if(!this._init) {
			// 初始化Loading
			this._initLoading();
		}

	}

	/**
	 * 隐藏Loading
	 * @return {void} 
	 */
	Loading.prototype.out = function() {
		this._transitionAnimationOut();
	}

	/**
	 * 初始化Loading
	 * @return {void} 
	 */
	Loading.prototype._initLoading = function() {

		// 已经初始过 无需再次初始化
		if(this._init) {
			return ;
		}

		// 添加到页面中
		this.$target.append(this.$modalMask);

		// 定位
		this._position();

		// $(window).resize(() => {
		// 	windowWidth = $(window).width();
		// 	windowHeight = $(window).height();
		// 	this._position();
		// });

		var self = this;
		
		$(window).resize(function() {
			windowWidth = $(window).width();
			windowHeight = $(window).height();
			self._position();
		});

		this._init = true;
	}

	/**
	 * Loading参数属性
	 * 可以简单的设置一些css样式, 复杂的css样式可以通过增加class来更改样式.
	 *
	 * 像素单位: 如果是字符串, 则原文设置. 如果是数字类型, 默认单位为{unit}. zIndex除外.
	 *
	 * 如果字体样式为undefined(例如: titleFontFamily), 那么将会适用全局的字体样式(fontFamily)
	 * 
	 * @author  c
	 * @date 	2017-11-06
	 * @version 1.0.0
	 */
	Loading.prototype.set = {
		direction: 				'ver',	 					// 方向. ver: 垂直, hor: 水平.

		title: 					undefined, 					// 标题内容.
		titleColor: 			'#FFF', 					// 标题文字颜色.
		titleFontSize: 			14, 						// 标题文字字体大小. 
		titleClassName: 		undefined,					// 标题额外的class值.
		// titleFontFamily: 	undefined,					// 标题字体样式
		
		discription: 			undefined, 					// 描述内容.
		discriptionColor: 		'#FFF',						// 描述文字颜色.
		discriptionFontSize: 	14,							// 描述文字字体大小. 
		discriptionClassName: 	undefined,					// 描述额外的class值.
		// directionFontFamily: undefined,					// 描述字体样式.

		loadingWidth: 			'auto',						// Loading宽度.
		loadingHeight: 			'auto',						// Loading高度.
		loadingPadding: 		20,							// Loading内边距.
		loadingBgColor: 		'#252525',					// Loading背景颜色.
		loadingBorderRadius: 	12,							// Loading的borderRadius.
		// loadingPosition: 		'fixed',					// Loading的position

		mask: 					true, 						// 遮罩层. true: 显示遮罩层, false: 不显示. 
		maskBgColor: 			'rgba(0, 0, 0, .6)',		// 遮罩层背景颜色.
		maskClassName: 			undefined,					// 为遮罩层添加.
		// maskPosition: 			'fixed',					// 遮罩层position

		loadingAnimation: 		'origin',					// 加载动画. origin: 表示使用默认的原点动画, image: 表示使用自定义图片作为加载动画.
		animationSrc: 			undefined,					// 图片加载动画的地址. (前提: loadingAnimation=origin, 以下简称origin或者image)
		animationWidth: 		40, 						// 动画宽度. 为image时表示图片的宽度.
		animationHeight: 		40,							// 动画高度. 为image时表示图片的高度.
		animationOriginWidth:   4,							// 原点动画宽度.    (前提: origin)
		animationOriginHeight:  4,							// 原点动画高度.    (前提: origin)
		animationOriginColor:   '#FFF',						// 原点动画的颜色.  (前提: origin)
		animationClassName: 	undefined,					// 为动画添加一个额外的class值.
		
		defaultApply: 			true,						// 默认自动显示.
		animationIn: 			'animated fadeIn', 			// 入屏动画. 
		animationOut: 			'animated fadeOut',			// 出屏动画.
		animationDuration: 		1000,						// 动画持续时间(单位:ms)
		// fontFamily: 			'sans-serif',				// 文字字体样式.
		// position: 				'fixed',				// 定位. mask和loading的定位.
		// unit: 				'px', 						// 设置默认单位.
		zIndex: 				0,							// 最外围层级(mask). 如果是0或者负数, 则为{$this.siblings() + 10001}.

	};

	init.prototype = Loading.prototype;

	return Loading;
});
$.Loading.allOut = function () {
	$(".modal-mask").remove();
}

/*!
  * Bootstrap Toaster v0.0.1 (https://iqbalfn.github.io/bootstrap-toaster/)
  * Copyright 2019 Iqbal Fauzi
  * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
  */
(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports, require('jquery')) :
  typeof define === 'function' && define.amd ? define(['exports', 'jquery'], factory) :
  (global = global || self, factory(global['bootstrap-toaster'] = {}, global.jQuery));
}(this, function (exports, $) { 'use strict';

  $ = $ && $.hasOwnProperty('default') ? $['default'] : $;

  function _defineProperties(target, props) {
    for (var i = 0; i < props.length; i++) {
      var descriptor = props[i];
      descriptor.enumerable = descriptor.enumerable || false;
      descriptor.configurable = true;
      if ("value" in descriptor) descriptor.writable = true;
      Object.defineProperty(target, descriptor.key, descriptor);
    }
  }

  function _createClass(Constructor, protoProps, staticProps) {
    if (protoProps) _defineProperties(Constructor.prototype, protoProps);
    if (staticProps) _defineProperties(Constructor, staticProps);
    return Constructor;
  }

  function _defineProperty(obj, key, value) {
    if (key in obj) {
      Object.defineProperty(obj, key, {
        value: value,
        enumerable: true,
        configurable: true,
        writable: true
      });
    } else {
      obj[key] = value;
    }

    return obj;
  }

  function _objectSpread(target) {
    for (var i = 1; i < arguments.length; i++) {
      var source = arguments[i] != null ? arguments[i] : {};
      var ownKeys = Object.keys(source);

      if (typeof Object.getOwnPropertySymbols === 'function') {
        ownKeys = ownKeys.concat(Object.getOwnPropertySymbols(source).filter(function (sym) {
          return Object.getOwnPropertyDescriptor(source, sym).enumerable;
        }));
      }

      ownKeys.forEach(function (key) {
        _defineProperty(target, key, source[key]);
      });
    }

    return target;
  }

  /**
   * ------------------------------------------------------------------------
   * Constants
   * ------------------------------------------------------------------------
   */

  var NAME = 'toaster';
  var VERSION = '0.0.1';
  var Default = {
    title: false,
    content: '<em>No content</em>',
    delay: 3000,
    position: 'top right'
  };
  var DefaultTitle = {
    text: '',
    icon: null,
    image: null,
    close: true,
    info: false
  };
  var ToasterObject;
  var ToasterContainer = {};
  /**
   * ------------------------------------------------------------------------
   * Class Definition
   * ------------------------------------------------------------------------
   */

  var Toaster =
  /*#__PURE__*/
  function () {
    function Toaster(opt, title) {
      if (null === opt) return;
      if (!ToasterObject) ToasterObject = new Toaster(null);

      ToasterObject._open(opt, title);
    } // Privates


    var _proto = Toaster.prototype;

    _proto._getContainer = function _getContainer(config) {
      var position = (config.position || 'top right').split(' ');
      var ver = position[0] || 'top';
      var hor = position[1] || 'right';
      if (!['top', 'bottom'].includes(ver)) ver = 'top';
      if (!['left', 'center', 'right'].includes(hor)) hor = 'right';
      position = ver + " " + hor;
      if (ToasterContainer[position]) return ToasterContainer[position];

      var html = this._makeContainer(ver, hor);

      ToasterContainer[position] = $(html).appendTo('body');
      return ToasterContainer[position];
    };

    _proto._makeBody = function _makeBody(config) {
      return "<div class=\"toast-body\">" + config.content + "</div>";
    };

    _proto._makeContainer = function _makeContainer(ver, hor) {
      var css = "position:fixed;width:320px;" + ver + ":20px;z-index:1060;";
      if (hor === 'center') css += 'left:50%;margin-left:-160px';else css += hor + ":20px";
      return "<div aria-live=\"polite\" aria-atomic=\"true\" style=\"" + css + "\"></div>";
    };

    _proto._makeHeader = function _makeHeader(config) {
      if (!config.title) return '';
      if (typeof config.title === 'string') config.title = {
        text: config.title
      };

      var title = _objectSpread({}, DefaultTitle, config.title);

      var eImage = '';
      if (title.image) eImage = "<img src=\"" + title.image + "\" class=\"rounded mr-2\" alt=\"#\">";else if (title.icon) eImage = "<i class=\"" + title.icon + " mr-2\"></i>";
      var eTitle = !title.text ? '' : "<strong class=\"mr-auto\">" + title.text + "</strong>";
      var eInfo = !title.info ? '' : "<small>" + title.info + "</small>";
      var eClose = !title.close ? '' : " <button type=\"button\" class=\"ml-2 mb-1 close\" data-dismiss=\"toast\" aria-label=\"Close\">\n                    <span aria-hidden=\"true\">&times;</span>\n                </button>";
      return "\n            <div class=\"toast-header\">\n                " + eImage + "\n                " + eTitle + "\n                " + eInfo + "\n                " + eClose + "\n            </div>\n        ";
    };

    _proto._makeHtml = function _makeHtml(config) {
      var header = this._makeHeader(config);

      var body = this._makeBody(config);

      return "\n            <div class=\"toast\" role=\"alert\" aria-live=\"assertive\" aria-atomic=\"true\">\n                " + header + " " + body + "\n            </div>";
    };

    _proto._open = function _open(opt, title) {
      if (typeof opt !== 'object') {
        opt = {
          content: opt
        };
        if (undefined !== title) opt.title = title;
      }

      var config = _objectSpread({}, Default, opt);

      var html = this._makeHtml(config);

      $(html).appendTo(this._getContainer(config)).toast({
        animation: true,
        autohide: true,
        delay: config.delay
      }).toast('show').on('hidden.bs.toast', function () {
        $(this).remove();
      });
    } // Getters
    ;

    // Static
    Toaster.setDefault = function setDefault(opts) {
      for (var k in opts) {
        Default[k] = opts[k];
      }
    };

    _createClass(Toaster, null, [{
      key: "VERSION",
      get: function get() {
        return VERSION;
      }
    }, {
      key: "Default",
      get: function get() {
        return Default;
      }
    }]);

    return Toaster;
  }();
  /**
   * ------------------------------------------------------------------------
   * jQuery
   * ------------------------------------------------------------------------
   */


  $[NAME] = Toaster;

  exports.Toaster = Toaster;

  Object.defineProperty(exports, '__esModule', { value: true });

}));
//# sourceMappingURL=bootstrap-toaster.js.map

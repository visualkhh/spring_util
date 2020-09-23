/**
 * --------------------------------------------------------------------------
 * Bootstrap-Toaster (v0.0.1): toaster.js
 * --------------------------------------------------------------------------
 */

import $ from 'jquery'

/**
 * ------------------------------------------------------------------------
 * Constants
 * ------------------------------------------------------------------------
 */

 const NAME               = 'toaster'
 const VERSION            = '0.0.1'

const Default = {
    title   : false,
    content : '<em>No content</em>',
    delay   : 3000,
    position: 'top right',
    containerOption: {},
    buttonOption: {},
    headerOption: {},
    bodyOption: {}
}
const DefaultTitle = {
    text    : '',
    icon    : null,
    image   : null,
    close   : true,
    info    : false
}

let ToasterObject;
let ToasterContainer = {};

/**
 * ------------------------------------------------------------------------
 * Class Definition
 * ------------------------------------------------------------------------
 */

class Toaster {
    constructor(opt, title) {
        if(null === opt)
            return
        if(!ToasterObject)
            ToasterObject = new Toaster(null);
        ToasterObject._open(opt, title);
    }

    // Privates

    _getContainer(config){
        let position = (config.position || 'top right').split(' ');
        let ver = position[0] || 'top'
        let hor = position[1] || 'right'

        if(!['top','bottom'].includes(ver))
            ver = 'top'
        if(!['left','center','right'].includes(hor))
            hor = 'right'

        position = `${ver} ${hor}`;

        if(ToasterContainer[position])
            return ToasterContainer[position];

        let html = this._makeContainer(ver, hor);
        ToasterContainer[position] = $(html).appendTo('body');

        return ToasterContainer[position];
    }

    _makeBody(config){

        return `<div class="toast-body  ${config.headerOption.class?config.bodyOption.class:''}" ${config.bodyOption.style?'style="'+config.bodyOption.style+'"':''}>${config.content}</div>`;
    }

    _makeContainer(ver, hor){
        let css = `position:fixed;width:320px;${ver}:20px;z-index:1060;`

        if(hor === 'center')
            css+= 'left:50%;margin-left:-160px'
        else
            css+= `${hor}:20px`;

        return `<div aria-live="polite" aria-atomic="true" style="${css}"></div>`;
    }

    _makeHeader(config){
        if(!config.title)
            return '';

        if(typeof config.title === 'string')
            config.title = { text : config.title }
        
        let title = { ...DefaultTitle, ...config.title };

        let eImage = '';
        if(title.image)
            eImage = `<img src="${title.image}" class="rounded mr-2" alt="#">`;
        else if(title.icon)
            eImage = `<i class="${title.icon} mr-2"></i>`;

        let eTitle = !title.text ? ''
            : `<strong class="mr-auto">${title.text}</strong>`

        let eInfo = !title.info ? ''
            : `<small>${title.info}</small>`;

        let eClose = !title.close  ? ''
            : ` <button type="button" class="ml-2 mb-1 close ${config.buttonOption.class?config.buttonOption.class:''}"  ${config.buttonOption.style?'style="'+config.buttonOption.style+'"':''} data-dismiss="toast" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>`;

        return `
            <div class="toast-header ${config.headerOption.class?config.headerOption.class:''}"  ${config.headerOption.style?'style="'+config.headerOption.style+'"':''}>
                ${eImage}
                ${eTitle}
                ${eInfo}
                ${eClose}
            </div>
        `;
    }

    _makeHtml(config){
        let header = this._makeHeader(config);
        let body   = this._makeBody(config);

        return `
            <div class="toast  ${config.containerOption.class?config.containerOption.class:''}" ${config.containerOption.style?'style="'+config.containerOption.style+'"':''} role="alert" aria-live="assertive" aria-atomic="true">
                ${header}${body}
            </div>`;
    }

    _open(opt, title){
        if(typeof opt !== 'object'){
            opt = {content: opt}
            if(undefined !== title)
                opt.title = title;
        }

        const config = { ...Default, ...opt };
        const html = this._makeHtml(config)

        $(html)
            .appendTo(this._getContainer(config))
            .toast({
                animation: true,
                autohide : true,
                delay    : config.delay
            })
            .toast('show')
            .on('hidden.bs.toast', function(){
                $(this).remove()
            })
    }

    // Getters

    static get VERSION() {
        return VERSION
    }

    static get Default() {
        return Default
    }

    // Static

    static setDefault(opts){
        for(let k in opts)
            Default[k] = opts[k]
    }
}

/**
 * ------------------------------------------------------------------------
 * jQuery
 * ------------------------------------------------------------------------
 */

$[NAME] = Toaster

export default Toaster


export class ValidUtil {

    static isNullOrUndefined(data: any): boolean {
        if (null == data || undefined === data) {
            return true;
        }else {
            return false;
        }
    }
    static isEmpty(data: any): boolean {
        if (null == data || undefined === data || data?.length <= 0) {
            return true;
        }else {
            return false;
        }
    }
    static isNull(data: any): boolean {
        if (null == data) {
            return true;
        }else {
            return false;
        }
    }
    static isArray(object: any): boolean {
        if (ValidUtil.isNullOrUndefined(object)) {
            return false;
        }else {
            return  Object.prototype.toString.call(object).trim() === '[object Array]';
        }
    }

    static isNumber(object: any): boolean {
        if (ValidUtil.isNullOrUndefined(object)) {
            return false;
        }else {
            return  Object.prototype.toString.call(object).trim() === '[object Number]';
        }
    }
    static isString(object: any): boolean {
        if (ValidUtil.isNullOrUndefined(object)) {
            return false;
        }else {
            return  Object.prototype.toString.call(object).trim() === '[object String]';
        }
    }
    static isFunction(object: any): boolean {
        if (ValidUtil.isNullOrUndefined(object)) {
            return false;
        }else {
            return  Object.prototype.toString.call(object).trim() === '[object Function]';
        }
        // if (typeof object === 'function') {
        //     return true;
        // }else {
        //     return false;
        // }
    }
    static isObject(object: any): boolean {
        if (ValidUtil.isNullOrUndefined(object)) {
            return false;
        }else {
            return  Object.prototype.toString.call(object).trim() === '[object Object]';
        }
    }
    static isMap(object: any): boolean {
        if (ValidUtil.isNullOrUndefined(object)) {
            return false;
        }else {
            return  Object.prototype.toString.call(object).trim() === '[object Map]';
        }
    }
}

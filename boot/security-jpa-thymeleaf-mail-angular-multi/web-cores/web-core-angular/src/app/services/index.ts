import {AlertService} from '@web-core/app/services/ui/alert.service';
import {JsonApiService} from '@web-core/app/services/json-api.service';
import {UserService} from '@web-core/app/services/user.service';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
import {I18nPipe} from '@web-core/app/pipes/i18n/i18n.pipe';
import {ValidationService} from '@web-core/app/services/validation/validation.service';
import {CodeService} from '@web-core/app/services/code/code.service';
import {MomentService} from '@web-core/app/services/date/moment.service';

export const services = [
    AlertService,
    JsonApiService,
    I18nService,
    I18nPipe,
    UserService,
    ValidationService,
    CodeService,
    MomentService,
];
//
export * from './ui/alert.service';
export * from './json-api.service';
export * from './i18n/i18n.service';
export * from '../pipes/i18n/i18n.pipe';
export * from './user.service';
export * from './validation/validation.service';
export * from './code/code.service';
// export * from "./auth-token.service";
// export * from "./auth.service";
// export * from "./token.interceptor";
// export * from "./json-api.service";
// export * from "./user.service";
// export * from "./chat.service";
// export * from "./notification.service";
// export * from "./body.service";
// export * from "./layout.service";
// export * from "./sound.service";
// export * from "./voice";
// export * from "./cms-common.service";
// export * from "./validation.service";

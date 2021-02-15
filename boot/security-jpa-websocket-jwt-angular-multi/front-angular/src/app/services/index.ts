import {AlertService} from '@app/services/ui/alert.service';
import {JsonApiService} from '@app/services/json-api.service';
import {UserService} from '@app/services/user.service';
import {WsService} from '@app/services/ws.service';
import {MomentService} from '@app/services/date/moment.service';
import {ValidationService} from '@app/services/validation/validation.service';

export const services = [
    AlertService,
    JsonApiService,
    UserService,
    WsService,
    MomentService,
    ValidationService,
];
//
export * from './ui/alert.service';
export * from './json-api.service';
export * from './user.service';
export * from './ws.service';
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

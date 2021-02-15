import {com} from '@generate/models';
import UserToken = com.clone.chat.model.UserToken;

export class UserTokenContain extends UserToken {

    get authorizationHeaders() {
        const header = {} as {
            [name: string]: string;
        };
        header[this.tokenHeader] = this.token;
        return header;
    }


}

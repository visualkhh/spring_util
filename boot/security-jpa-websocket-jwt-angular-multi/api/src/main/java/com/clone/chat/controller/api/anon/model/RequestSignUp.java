package com.clone.chat.controller.api.anon.model;

import com.clone.chat.model.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestSignUp extends ModelBase {
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
    @NotEmpty
    private String nickName;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String statusMsg;
    MultipartFile file;

    public boolean isFile() {
        if (null == this.file){
            return false;
        } else {
            return true;
        }
    }
}

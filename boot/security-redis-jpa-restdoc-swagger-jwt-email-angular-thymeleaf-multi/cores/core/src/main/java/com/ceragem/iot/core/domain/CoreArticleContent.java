package com.ceragem.iot.core.domain;

import com.ceragem.iot.core.domain.base.ArticleContentBase;
import com.ceragem.iot.core.domain.base.AuthUrlBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="article_content")
public class CoreArticleContent extends ArticleContentBase {
}

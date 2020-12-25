package com.ceragem.iot.core.domain.base;

import com.ceragem.iot.core.model.ModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class ArticleContentBase extends ModelBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    Long no;

    @Column(name = "category_no")
    Long category_no;

    @Column(name = "style")
    String style;

    @Column(name = "regist_date")
    ZonedDateTime regist_date;

    @Column(name = "title")
    String title;

    @Column(name = "image")
    Long image;

    @Column(name = "stream")
    String stream;

    @Column(name = "stream_url")
    String stream_url;

    @Column(name = "stream_duration")
    String stream_duration;

    @Column(name = "link_url")
    String link_url;

    @Column(name = "description")
    String description;

    @Column(name = "view_count")
    Long view_count;

    @Column(name = "like_count")
    Long like_count;

    @Column(name = "play_count")
    Long play_count;

    @Column(name = "reply_count")
    Long reply_count;

    @Column(name = "is_hidden")
    Long is_hidden;

    @Column(name = "is_reply")
    Long is_reply;

    @Column(name = "extra")
    String extra;



    @PrePersist
    protected void onCreate() {
    }

}

package com.visualkhh.api.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.visualkhh.api.config.serializer.FileBaseSerializer;
import com.visualkhh.common.domain.FileBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "T_FILE")
@JsonSerialize(using = FileBaseSerializer.class)
public class File extends FileBase {
}

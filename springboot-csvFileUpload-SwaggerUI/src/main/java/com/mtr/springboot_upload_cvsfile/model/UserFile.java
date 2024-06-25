package com.mtr.springboot_upload_cvsfile.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFile {

    @Id
    public String fileId;
    public String name;
    public String color;

}

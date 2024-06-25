package com.mtr.springboot_upload_cvsfile.repository;

import com.mtr.springboot_upload_cvsfile.model.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<UserFile, String> {
}

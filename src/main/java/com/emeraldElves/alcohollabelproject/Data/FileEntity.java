package com.emeraldElves.alcohollabelproject.Data;

import org.apache.commons.io.IOUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.*;

/**
 * Created by Essam on 4/26/2017.
 */
@Entity
@Table(name = "files")
@DiscriminatorColumn(name="type")
//@Inheritance(strategy = InheritanceType.JOINED)
public class FileEntity extends BaseEntity {
    private String id;
    private byte[] file;

    @Id @GeneratedValue(generator="uuid-generator")
    @GenericGenerator(name="uuid-generator", strategy="uuid")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name="file_blob", columnDefinition="BLOB NOT NULL")
    public byte[] getFile(){return file;}
    public void setFile(byte[] file){this.file = file;}

    public FileEntity(byte[] file){
        this.file = file;
    }
    public FileEntity(String filePath) throws IOException {
        this(IOUtils.toByteArray(new FileInputStream(filePath)));
    }
    public FileEntity(File file) throws IOException {
        //FileReader freader = new FileReader(file);
        this(IOUtils.toByteArray(new FileInputStream(file)));
    }
    public FileEntity(InputStream istream) throws IOException {
        this(IOUtils.toByteArray(istream));
    }
    public FileEntity(ByteArrayInputStream istream){
        byte[] buf = new byte[istream.available()];
        istream.read(buf, 0, buf.length);
        this.file = buf;
    }
}

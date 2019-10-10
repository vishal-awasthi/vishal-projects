package com.netpay.SaveFileSystem.FormValidation.backend.data;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.*;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Indexed
@Table(name ="File_Location", schema="sys")
public class FileLocationsRequest implements Serializable
{
    private static final long SerialVersionUID = 123545855;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int pathId;

    @Column (name = "path")
    @Field
    private String path;

    public FileLocationsRequest(String path) {
        this.path = path;
    }

    public FileLocationsRequest() {}

    public double getPathId() {
        return pathId;
    }

    public void setPathId(int pathId) {
        this.pathId = pathId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

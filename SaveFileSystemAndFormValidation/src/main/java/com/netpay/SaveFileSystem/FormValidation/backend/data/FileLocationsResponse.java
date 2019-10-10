package com.netpay.SaveFileSystem.FormValidation.backend.data;

import java.io.Serializable;
import java.util.List;

public class FileLocationsResponse implements Serializable
{
    private static final long SerialVersionUID = 122485545;

    private List<String> fileLocations;

    public List<String> getFileLocations() {
        return fileLocations;
    }

    public void setFileLocations(List<String> fileLocations) {
        this.fileLocations = fileLocations;
    }

    @Override
    public String toString() {

        StringBuilder fileLocationsString = new StringBuilder();

        fileLocationsString.append("{");
        fileLocationsString.append("\n");
        for(int i=0; i< fileLocations.size();i++){
            fileLocationsString.append("\""+fileLocations.get(i)+"\"");
            if(i != fileLocations.size()-1)
                fileLocationsString.append(",");
            fileLocationsString.append("\n");
        }
        fileLocationsString.append("}");
        return fileLocationsString.toString();
    }
}

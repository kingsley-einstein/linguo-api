package api.linguo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.bson.types.ObjectId;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "pictures")
public class ProfilePic {

    @Id
    private ObjectId _id;

    private String mimeType;

    private byte[] data;

    @JsonIgnore
    @DBRef
    private User owner;

    protected ProfilePic() {}

    public ProfilePic(String mimeType, byte[] data, User owner) {
        this.mimeType = mimeType;
        this.data = data;
        this.owner = owner;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
    public byte[] getData() {
        return data;
    }

    public User getOwner() {
        return owner;
    }
}
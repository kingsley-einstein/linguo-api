package api.linguo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.core.GrantedAuthority;

import org.bson.types.ObjectId;

import java.util.List; 

@Document(collection = "users")
public class User {

    @Id
    private ObjectId _id;

    @Indexed(unique = true)
    @Field
    private String username;

    @Indexed(unique = true)
    @Field
    private String email;

    @Field
    private String password;

    @DBRef
    @Field
    private ProfilePic pic;

    private List<GrantedAuthority> grantedAuthoritiesList;

    protected User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setProfilePic(ProfilePic pic) {
        this.pic = pic;
    }

    public ProfilePic getPic() {
        return pic;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setGrantedAuthoritiesList(List<GrantedAuthority> grantedAuthoritiesList) {
        this.grantedAuthoritiesList = grantedAuthoritiesList;
    }

    public List<GrantedAuthority> getGrantedAuthoritiesList() {
        return grantedAuthoritiesList;
    }

    public void addAuthority(GrantedAuthority authority) {
        grantedAuthoritiesList.add(authority);
    }

    public String get_Id() {
        return _id.toString();
    }
}
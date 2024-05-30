package data;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateComment {

    private int id;
    private String title;
    private String body;
    private int userId;

    public UpdateComment(int id, String title, String body, int userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}

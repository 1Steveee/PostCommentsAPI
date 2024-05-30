package data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewComment {

    private String title;
    private String body;
    private int userId;

    public NewComment(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

}

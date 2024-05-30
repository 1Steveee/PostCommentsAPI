package data;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchComment {

    private String title;

    public PatchComment(String title) {
        this.title = title;
    }
}

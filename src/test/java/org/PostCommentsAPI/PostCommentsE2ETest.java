package org.PostCommentsAPI;


import data.NewComment;
import data.PatchComment;
import data.UpdateComment;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PostCommentsE2ETest extends BaseSetup {

    private final int OK_STATUS_CODE = 200;
    private final String TITLE = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
    private final int POST_ID = 1;
    private final String BODY_TEXT = "quia et suscipit\nsuscipit recusandae consequuntur expedita et " +
            "cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto";
    @Test
    public void testGetAllPost() {
        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(OK_STATUS_CODE)
                .assertThat()
                .body("size()", is(100))
                .and()
                .body("[0].title", equalTo(TITLE),
                        "[0].id", is(POST_ID),
                        "[0].body", equalTo(BODY_TEXT));

    }

    @Test
    public void testGetAllComments() {
        given()
                .when()
                .get("/comments")
                .then()
                .statusCode(OK_STATUS_CODE)
                .assertThat()
                .body("size()", is(500),
                        "[0].postId",is(1),
                        "[0].id",is(1),
                        "[0].name",equalTo("id labore ex et quam laborum"));
    }

    @Test
    public void testGetPostIdOne() {
        given()
                .when()
                .get(String.format("/posts/%s", POST_ID))
                .then()
                .statusCode(OK_STATUS_CODE)
                .assertThat()
                .body("userId", is(1),
                        "id", is(POST_ID),
                        "title", equalTo(TITLE),
                        "body", equalTo(BODY_TEXT));
    }

    @Test
    public void testPostNewComment() {
        NewComment newComment = new NewComment("New Post","Welcome to Automation tutoring",1);

        String stringBody = JsonConverter.StringConverter(newComment);

        int CREATED_STATUS_CODE = 201;
        given()
                .body(stringBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(CREATED_STATUS_CODE)
                .and()
                .assertThat()
                .body("title", equalTo(newComment.getTitle()),
                        "body",equalTo(newComment.getBody()),
                        "userId", is(newComment.getUserId()));
    }

    @Test
    public void testUpdateComment() {
        UpdateComment updateComment = new UpdateComment(POST_ID, "Update Comment", "Comment has been updated", 1);

        String stringBody = JsonConverter.StringConverter(updateComment);

        given()
                .body(stringBody)
                .when()
                .put(String.format("/posts/%s", POST_ID))
                .then()
                .statusCode(OK_STATUS_CODE)
                .and()
                .assertThat()
                .body("id", is(POST_ID),
                        "title", equalTo(updateComment.getTitle()),
                        "body", equalTo(updateComment.getBody()),
                        "userId", equalTo(updateComment.getUserId()));
    }

    @Test
    public void testPatchComment() {
        PatchComment patchComment = new PatchComment("Patching Comment");

        String stringBody = JsonConverter.StringConverter(patchComment);

        given()
                .body(stringBody)
                .when()
                .put(String.format("/posts/%s", POST_ID))
                .then()
                .statusCode(OK_STATUS_CODE)
                .and()
                .assertThat()
                .body("id", is(POST_ID),
                        "title", equalTo(patchComment.getTitle()));

    }

    @Test
    public void testDeleteComment() {

        given()
                .when()
                .delete(String.format("/posts/%s", POST_ID))
                .then()
                .statusCode(OK_STATUS_CODE);

    }


    //Push to github and use github actions
    //create another actions for code QL
}

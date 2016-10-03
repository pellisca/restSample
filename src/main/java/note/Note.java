package note;

public class Note {

    private long id;
    private String body;

    public Note(long id, String body) {
        this.id = id;
        this.body = body;
    }

    public Note() {
        this.id = 0;
        this.body = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long l) {
        this.id = l;
    }

    public String getBody() {
        return body;
    }
}

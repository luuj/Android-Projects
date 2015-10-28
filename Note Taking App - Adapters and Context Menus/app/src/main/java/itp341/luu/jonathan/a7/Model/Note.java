package itp341.luu.jonathan.a7.Model;

import java.util.Calendar;
import java.util.Date;

public class Note {

    private String title, content;
    private Date date;

    public Note(){
        title = null;
        content = null;
        date = null;
    }

    public Note(String t, String c, Date d){
        title = t;
        content = c;
        date = d;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String stringDate = (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);

        return stringDate;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

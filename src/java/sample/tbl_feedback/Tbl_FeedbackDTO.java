/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_feedback;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Tbl_FeedbackDTO implements Serializable{
    String fbId;
    String fbValue;

    public Tbl_FeedbackDTO() {
    }

    public Tbl_FeedbackDTO(String fbId, String fbValue) {
        this.fbId = fbId;
        this.fbValue = fbValue;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getFbValue() {
        return fbValue;
    }

    public void setFbValue(String fbValue) {
        this.fbValue = fbValue;
    }
    
    
}

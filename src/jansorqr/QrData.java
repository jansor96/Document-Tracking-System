/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jansorqr;

/**
 * This class holds the data found on the QR image.
 *
 * @author jansoriano acer
 */
public class QrData {

    
    //Variable declarations
    private String title;
    private String prNumber;
    private String author;
    private String src_dept;
    private String recipient;
    private String dest_dept;
    private String date;
    private String time;
    private String add_info;
    //End of variable declarations
    
    /**
     * Returns the title of the document described by the QrData.
     * @return 
     */
    public String getTitle(){
        return title;
    }
    
    /**
     * Returns the PrNumber of the document described by the QrData.
     * @return 
     */
    public String getPrNumber(){
        return prNumber;
    }
    
    /**
     * Returns the author of the document described by the QrData.
     * @return 
     */
    public String getAuthor(){
        return author;
    }
    
    /**
     * Returns the source department of the document described by the QrData.
     * @return 
     */
    public String getSourceDept(){
        return src_dept;
    }
    
    /**
     * Returns the recipient of the document described by the QrData.
     * @return 
     */
    public String getRecipient(){
        return recipient;
    }
    
    /**
     * Returns the destination department of the document described by the QrData.
     * @return 
     */
    public String getDestDept(){
        return dest_dept;
    }
    
    /**
     * Returns the date of the document described by the QrData.
     * @return 
     */
    public String getDate(){
        return date;
    }
    
    /**
     * Returns the time of the document described by the QrData.
     * @return 
     */
    public String getTime(){
        return time;
    }
    
    /**
     * Returns the additional info of the document described by the QrData.
     * @return 
     */
    public String getAddInfo(){
        return add_info;
    }
    
    /**
     * Sets the title field of the QrData.
     * @param title - the title of the document described by the QrData.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the prNumber field of the QrData.
     * @param prNumber - the prNumber of the document described by the QrData.
     */
    public void setPrNumber(String prNumber) {
        this.prNumber = prNumber;
    }
     
    /**
     * Sets the author field of the QrData.
     * @param author - the author of the document described by the QrData.
     */
    public void setAuthor(String author){
        this.author = author;
    }
    
    /**
     * Sets the source department field of the QrData.
     * @param src_dept - the source department of the document described by the QrData.
     */
    public void setSourceDept(String src_dept){
        this.src_dept = src_dept;
    }
    
    /**
     * Sets the expected recipient field of the QrData.
     * @param recipient - the recipient of the document described by the QrData.
     */
    public void setRecipient(String recipient){
        this.recipient = recipient;
    }
    
    /**
     * Sets the destination department field of the QrData.
     * @param dest_dept - the destination department of the document described by the QrData.
     */
    public void setDestDept(String dest_dept){
        this.dest_dept = dest_dept;
    }
    
    /**
     * Sets the date field of the QrData.
     * @param date - the date of the document described by the QrData.
     */
    public void setDate(String date){
        this.date = date;
    }
    
    /**
     * Sets the time field of the QrData.
     * @param time - the time of the document described by the QrData.
     */
    public void setTime(String time){
        this.time = time;
    }
    
    /**
     * Sets the additional info field of the QrData.
     * @param add_info - the additional info of the document described by the QrData.
     */
    public void setAddInfo(String add_info){
        this.add_info = add_info;
    }
}

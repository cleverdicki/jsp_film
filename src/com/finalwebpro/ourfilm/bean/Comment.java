package com.finalwebpro.ourfilm.bean;

//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import java.sql.Timestamp;
import java.util.Date;

public class Comment {
	protected int id_comment;
	protected String name_film;
	protected String distributor_film;
	protected String comment_film;
	protected String date_comment;
	//protected String millis;
	
	public Comment() {
		super();
	}

	public Comment(String name_film, String distributor_film, String comment_film, String date_comment) {
		super();
		this.name_film = name_film;
		this.distributor_film = distributor_film;
		this.comment_film = comment_film;
		this.date_comment = date_comment;
	}
	
	public Comment(int id_comment, String name_film, String distributor_film, String comment_film, String date_comment) {
		super();
		this.id_comment = id_comment;
		this.name_film = name_film;
		this.distributor_film = distributor_film;
		this.comment_film = comment_film;
		this.date_comment = date_comment;
	}

	//public void main(String[] args) {
		// src: https://ramj2ee.blogspot.com/2018/12/how-to-create-javasqltimestamp-object.html
		//long millis = System.currentTimeMillis();
        //Timestamp timestamp = new Timestamp(millis);
	//}
	
	public int getId_comment() {
		return id_comment;
	}
	public void setId_comment(int id_comment) {
		this.id_comment = id_comment;
	}
	public String getname_film() {
		return name_film;
	}
	public void setname_film(String name_film) {
		this.name_film = name_film;
	}
	public String getdistributor_film() {
		return distributor_film;
	}
	public void setdistributor_film(String distributor_film) {
		this.distributor_film = distributor_film;
	}
	public String getcomment_film() {
		return comment_film;
	}
	public void setcomment_film(String comment_film) {
		this.comment_film = comment_film;
	}
	public String getdate_comment() {
		return date_comment;
	}
	public void setdate_comment(String comment_film) {
		this.date_comment = date_comment;
	}
}
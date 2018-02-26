package com.ntg.user.mvpsample.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class SubTask {

	@SerializedName("description")
	private String description;

	@SerializedName("progress")
	private int progress;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setProgress(int progress){
		this.progress = progress;
	}

	public int getProgress(){
		return progress;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"SubTask{" +
			"description = '" + description + '\'' + 
			",progress = '" + progress + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}
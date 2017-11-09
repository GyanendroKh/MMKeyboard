package com.gyanendrokh.meiteimayek.keyboard.commons;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {

  private int mBackgroundColor;
  private int mImage;
  private String mTitle;
  private String mContent;
  private String mBtnText;
  private int mBtnAction;

  public int getBackgroundColor() {
    return mBackgroundColor;
  }

  public int getImage() {
    return mImage;
  }

  public String getTitle() {
    return mTitle;
  }

  public String getContent() {
    return mContent;
  }

  public String getBtnText() {
    return mBtnText;
  }

  public int getBtnAction() {
    return mBtnAction;
  }

  public static class Builder {

    private Step step;

    public Builder() {
      step = new Step();
    }

    public Builder setBackgroundColor(int backgroundColor) {
      step.mBackgroundColor = backgroundColor;
      return this;
    }

    public Builder setImage(int drawable) {
      step.mImage = drawable;
      return this;
    }

    public Builder setTitle(String title) {
      step.mTitle = title;
      return this;
    }

    public Builder setContent(String content) {
      step.mContent = content;
      return this;
    }

    public Builder setActionBtn(String text, int action) {
      step.mBtnText = text;
      step.mBtnAction = action;
      return this;
    }

    public Step build() {
      return step;
    }

  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.mBackgroundColor);
    dest.writeInt(this.mImage);
    dest.writeString(this.mTitle);
    dest.writeString(this.mContent);
    dest.writeString(this.mBtnText);
    dest.writeInt(this.mBtnAction);
  }

  private Step() {}

  private Step(Parcel in) {
    this.mBackgroundColor = in.readInt();
    this.mImage = in.readInt();
    this.mTitle = in.readString();
    this.mContent = in.readString();
    this.mBtnText = in.readString();
    this.mBtnAction = in.readInt();
  }

  public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
    @Override
    public Step createFromParcel(Parcel source) {
      return new Step(source);
    }

    @Override
    public Step[] newArray(int size) {
      return new Step[size];
    }
  };
}

package com.kekelian.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class KKLLessionListBean {


    /**
     * message : {"status":true,"data":{"lessonTabRecord":[{"displayOrder":1,"finishProgress":0,"lessonName":"第一课时","lessonRecordId":"a314441fac324fc79b39ccd6f17faab1","menuId":"3","userId":"ff808181478107c601478f34e1ac0303"},{"displayOrder":2,"finishProgress":0,"lessonName":"第二课时","lessonRecordId":"5e07676550cb4f80ab09376adb3efced","menuId":"3","userId":"ff808181478107c601478f34e1ac0303"},{"displayOrder":3,"finishProgress":0,"lessonName":"第三课时","lessonRecordId":"bf1a6ce25b8a423d81a5b3d638d8d6cf","menuId":"3","userId":"ff808181478107c601478f34e1ac0303"},{"displayOrder":4,"finishProgress":0,"lessonName":"第四课时","lessonRecordId":"6b3ed1a1d6974b908caaddcc1f8524b1","menuId":"3","userId":"ff808181478107c601478f34e1ac0303"}],"unitTestTabRecord":{"correctItemCount":0,"finishItemCount":0,"isLocked":true,"score":0,"totalItemCount":0,"unitTestRecordId":"18f95dd3536d488c94a5cd210dabee12"}}}
     */

    private MessageBean message;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * status : true
         * data : {"lessonTabRecord":[{"displayOrder":1,"finishProgress":0,"lessonName":"第一课时","lessonRecordId":"a314441fac324fc79b39ccd6f17faab1","menuId":"3","userId":"ff808181478107c601478f34e1ac0303"},{"displayOrder":2,"finishProgress":0,"lessonName":"第二课时","lessonRecordId":"5e07676550cb4f80ab09376adb3efced","menuId":"3","userId":"ff808181478107c601478f34e1ac0303"},{"displayOrder":3,"finishProgress":0,"lessonName":"第三课时","lessonRecordId":"bf1a6ce25b8a423d81a5b3d638d8d6cf","menuId":"3","userId":"ff808181478107c601478f34e1ac0303"},{"displayOrder":4,"finishProgress":0,"lessonName":"第四课时","lessonRecordId":"6b3ed1a1d6974b908caaddcc1f8524b1","menuId":"3","userId":"ff808181478107c601478f34e1ac0303"}],"unitTestTabRecord":{"correctItemCount":0,"finishItemCount":0,"isLocked":true,"score":0,"totalItemCount":0,"unitTestRecordId":"18f95dd3536d488c94a5cd210dabee12"}}
         */

        private boolean status;
        private DataBean data;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * lessonTabRecord : [{"displayOrder":1,"finishProgress":0,"lessonName":"第一课时","lessonRecordId":"a314441fac324fc79b39ccd6f17faab1","menuId":"3","userId":"ff808181478107c601478f34e1ac0303"},{"displayOrder":2,"finishProgress":0,"lessonName":"第二课时","lessonRecordId":"5e07676550cb4f80ab09376adb3efced","menuId":"3","userId":"ff808181478107c601478f34e1ac0303"},{"displayOrder":3,"finishProgress":0,"lessonName":"第三课时","lessonRecordId":"bf1a6ce25b8a423d81a5b3d638d8d6cf","menuId":"3","userId":"ff808181478107c601478f34e1ac0303"},{"displayOrder":4,"finishProgress":0,"lessonName":"第四课时","lessonRecordId":"6b3ed1a1d6974b908caaddcc1f8524b1","menuId":"3","userId":"ff808181478107c601478f34e1ac0303"}]
             * unitTestTabRecord : {"correctItemCount":0,"finishItemCount":0,"isLocked":true,"score":0,"totalItemCount":0,"unitTestRecordId":"18f95dd3536d488c94a5cd210dabee12"}
             */

            private UnitTestTabRecordBean unitTestTabRecord;
            private List<LessonTabRecordBean> lessonTabRecord;

            public UnitTestTabRecordBean getUnitTestTabRecord() {
                return unitTestTabRecord;
            }

            public void setUnitTestTabRecord(UnitTestTabRecordBean unitTestTabRecord) {
                this.unitTestTabRecord = unitTestTabRecord;
            }

            public List<LessonTabRecordBean> getLessonTabRecord() {
                return lessonTabRecord;
            }

            public void setLessonTabRecord(List<LessonTabRecordBean> lessonTabRecord) {
                this.lessonTabRecord = lessonTabRecord;
            }

            public static class LessonTabRecordBean implements Parcelable {
                /**
                 * displayOrder : 1
                 * finishProgress : 0
                 * lessonName : 第一课时
                 * lessonRecordId : a314441fac324fc79b39ccd6f17faab1
                 * menuId : 3
                 * userId : ff808181478107c601478f34e1ac0303
                 */


                private int displayOrder;
                private int finishProgress;
                private String lessonName;
                private String lessonRecordId;
                private String menuId;
                private String userId;

                public int getDisplayOrder() {
                    return displayOrder;
                }

                public void setDisplayOrder(int displayOrder) {
                    this.displayOrder = displayOrder;
                }

                public int getFinishProgress() {
                    return finishProgress;
                }

                public void setFinishProgress(int finishProgress) {
                    this.finishProgress = finishProgress;
                }

                public String getLessonName() {
                    return lessonName;
                }

                public void setLessonName(String lessonName) {
                    this.lessonName = lessonName;
                }

                public String getLessonRecordId() {
                    return lessonRecordId;
                }

                public void setLessonRecordId(String lessonRecordId) {
                    this.lessonRecordId = lessonRecordId;
                }

                public String getMenuId() {
                    return menuId;
                }

                public void setMenuId(String menuId) {
                    this.menuId = menuId;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.displayOrder);
                    dest.writeInt(this.finishProgress);
                    dest.writeString(this.lessonName);
                    dest.writeString(this.lessonRecordId);
                    dest.writeString(this.menuId);
                    dest.writeString(this.userId);
                }

                public LessonTabRecordBean() {
                }

                protected LessonTabRecordBean(Parcel in) {
                    this.displayOrder = in.readInt();
                    this.finishProgress = in.readInt();
                    this.lessonName = in.readString();
                    this.lessonRecordId = in.readString();
                    this.menuId = in.readString();
                    this.userId = in.readString();
                }

                public static final Parcelable.Creator<LessonTabRecordBean> CREATOR = new Parcelable.Creator<LessonTabRecordBean>() {
                    @Override
                    public LessonTabRecordBean createFromParcel(Parcel source) {
                        return new LessonTabRecordBean(source);
                    }

                    @Override
                    public LessonTabRecordBean[] newArray(int size) {
                        return new LessonTabRecordBean[size];
                    }
                };
            }
        }
    }
}

package com.kekelian.bean;

public class KekelianSuccessBean {

    /**
     * message : {"status":true,"data":{"correctItemCount":0,"displayOrder":0,"finishItemCount":0,"lessonName":"第一课时","levelRecordId":"2f85ec29f05049a5a125417a08a6727c","levelTypeName":"小试牛刀","score":0,"totalItemCount":5}}
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
         * data : {"correctItemCount":0,"displayOrder":0,"finishItemCount":0,"lessonName":"第一课时","levelRecordId":"2f85ec29f05049a5a125417a08a6727c","levelTypeName":"小试牛刀","score":0,"totalItemCount":5}
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
             * correctItemCount : 0
             * displayOrder : 0
             * finishItemCount : 0
             * lessonName : 第一课时
             * levelRecordId : 2f85ec29f05049a5a125417a08a6727c
             * levelTypeName : 小试牛刀
             * score : 0
             * totalItemCount : 5
             */

            private int correctItemCount;
            private int displayOrder;
            private int finishItemCount;
            private String lessonName;
            private String levelRecordId;
            private String levelTypeName;
            private int score;
            private int totalItemCount;

            public int getCorrectItemCount() {
                return correctItemCount;
            }

            public void setCorrectItemCount(int correctItemCount) {
                this.correctItemCount = correctItemCount;
            }

            public int getDisplayOrder() {
                return displayOrder;
            }

            public void setDisplayOrder(int displayOrder) {
                this.displayOrder = displayOrder;
            }

            public int getFinishItemCount() {
                return finishItemCount;
            }

            public void setFinishItemCount(int finishItemCount) {
                this.finishItemCount = finishItemCount;
            }

            public String getLessonName() {
                return lessonName;
            }

            public void setLessonName(String lessonName) {
                this.lessonName = lessonName;
            }

            public String getLevelRecordId() {
                return levelRecordId;
            }

            public void setLevelRecordId(String levelRecordId) {
                this.levelRecordId = levelRecordId;
            }

            public String getLevelTypeName() {
                return levelTypeName;
            }

            public void setLevelTypeName(String levelTypeName) {
                this.levelTypeName = levelTypeName;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getTotalItemCount() {
                return totalItemCount;
            }

            public void setTotalItemCount(int totalItemCount) {
                this.totalItemCount = totalItemCount;
            }
        }
    }
}

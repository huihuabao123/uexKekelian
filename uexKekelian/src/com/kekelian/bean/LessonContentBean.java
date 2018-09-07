package com.kekelian.bean;

import java.util.List;

public class LessonContentBean {


    /**
     * message : {"status":true,"data":{"blockName":"1b Listen and mime the action chant. & 4a Listen, point.","hasKP":true,"lessonRecordId":"5e07676550cb4f80ab09376adb3efced","quizList":[{"correctItemCount":0,"displayOrder":1,"finishItemCount":0,"levelRecordId":"0180b138bd564e2baf87f22bb0917335","levelTypeName":"小试牛刀","score":0,"totalItemCount":0},{"correctItemCount":0,"displayOrder":2,"finishItemCount":0,"levelRecordId":"758adf6cda504d08a82f13fce949a235","levelTypeName":"大显身手","score":0,"totalItemCount":0}]}}
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
         * data : {"blockName":"1b Listen and mime the action chant. & 4a Listen, point.","hasKP":true,"lessonRecordId":"5e07676550cb4f80ab09376adb3efced","quizList":[{"correctItemCount":0,"displayOrder":1,"finishItemCount":0,"levelRecordId":"0180b138bd564e2baf87f22bb0917335","levelTypeName":"小试牛刀","score":0,"totalItemCount":0},{"correctItemCount":0,"displayOrder":2,"finishItemCount":0,"levelRecordId":"758adf6cda504d08a82f13fce949a235","levelTypeName":"大显身手","score":0,"totalItemCount":0}]}
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
             * blockName : 1b Listen and mime the action chant. & 4a Listen, point.
             * hasKP : true
             * lessonRecordId : 5e07676550cb4f80ab09376adb3efced
             * quizList : [{"correctItemCount":0,"displayOrder":1,"finishItemCount":0,"levelRecordId":"0180b138bd564e2baf87f22bb0917335","levelTypeName":"小试牛刀","score":0,"totalItemCount":0},{"correctItemCount":0,"displayOrder":2,"finishItemCount":0,"levelRecordId":"758adf6cda504d08a82f13fce949a235","levelTypeName":"大显身手","score":0,"totalItemCount":0}]
             */

            private String blockName;
            private boolean hasKP;
            private String lessonRecordId;
            private List<QuizListBean> quizList;

            public String getBlockName() {
                return blockName;
            }

            public void setBlockName(String blockName) {
                this.blockName = blockName;
            }

            public boolean isHasKP() {
                return hasKP;
            }

            public void setHasKP(boolean hasKP) {
                this.hasKP = hasKP;
            }

            public String getLessonRecordId() {
                return lessonRecordId;
            }

            public void setLessonRecordId(String lessonRecordId) {
                this.lessonRecordId = lessonRecordId;
            }

            public List<QuizListBean> getQuizList() {
                return quizList;
            }

            public void setQuizList(List<QuizListBean> quizList) {
                this.quizList = quizList;
            }

            public static class QuizListBean {
                /**
                 * correctItemCount : 0
                 * displayOrder : 1
                 * finishItemCount : 0
                 * levelRecordId : 0180b138bd564e2baf87f22bb0917335
                 * levelTypeName : 小试牛刀
                 * score : 0
                 * totalItemCount : 0
                 */

                private int correctItemCount;
                private int displayOrder;
                private int finishItemCount;
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
}

package com.kekelian.bean;

public class UnitTestBean {


    /**
     * message : {"status":true,"data":{"userId":"ff808181478107c601478f34e1ac0303","menuId":"1","unitTestTabRecord":{"correctItemCount":0,"finishItemCount":0,"isLocked":true,"score":0,"totalItemCount":0,"unitTestRecordId":"702e8ee2d2aa47b09ff2860d21b9f438"}}}
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
         * data : {"userId":"ff808181478107c601478f34e1ac0303","menuId":"1","unitTestTabRecord":{"correctItemCount":0,"finishItemCount":0,"isLocked":true,"score":0,"totalItemCount":0,"unitTestRecordId":"702e8ee2d2aa47b09ff2860d21b9f438"}}
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
             * userId : ff808181478107c601478f34e1ac0303
             * menuId : 1
             * unitTestTabRecord : {"correctItemCount":0,"finishItemCount":0,"isLocked":true,"score":0,"totalItemCount":0,"unitTestRecordId":"702e8ee2d2aa47b09ff2860d21b9f438"}
             */

            private String userId;
            private String menuId;
            private UnitTestTabRecordBean unitTestTabRecord;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getMenuId() {
                return menuId;
            }

            public void setMenuId(String menuId) {
                this.menuId = menuId;
            }

            public UnitTestTabRecordBean getUnitTestTabRecord() {
                return unitTestTabRecord;
            }

            public void setUnitTestTabRecord(UnitTestTabRecordBean unitTestTabRecord) {
                this.unitTestTabRecord = unitTestTabRecord;
            }


        }
    }
}

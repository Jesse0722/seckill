package org.seckill.enums;

/**
 * Created by lijiajun1-sal on 2017/4/11.
 */
public enum RoleEnum {
    USER(1,"User"),
    ADMIN(2,"Admin");

    private int state;
    private String name;

    RoleEnum(int state, String name) {
        this.state = state;
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RoleEnum stateOf(int index){
        for(RoleEnum state:values()){
            if(state.getState()==index){
                return state;
            }
        }
        return null;
    }
}
